
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ChirpRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chirp;
import domain.Chorbi;
import domain.Event;
import domain.Mailer;
import domain.Manager;
import domain.RelationEvent;
import forms.ChirpForm;

@Service
@Transactional
public class ChirpService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ChirpRepository	chirpRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ChorbiService	chorbiService;

	@Autowired
	private Validator		validator;

	@Autowired
	private ManagerService	managerService;


	// Constructors -----------------------------------------------------------

	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Chirp create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));

		Chirp result;
		result = new Chirp();
		Mailer sender;

		if (userAccount.getAuthorities().contains(au)) {
			sender = chorbiService.findByPrincipal();
		} else {
			sender = managerService.findByPrincipal();
		}

		Date moment = new Date(System.currentTimeMillis() - 10);
		Collection<String> attachments = new ArrayList<String>();

		result.setSender(sender);
		result.setMoment(moment);
		result.setAttachment(attachments);
		result.setDeleteRecipient(false);
		result.setDeleteSender(false);

		return result;
	}

	public Collection<Chirp> findAll() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Chirp> result;

		result = chirpRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Chirp findOne(int chirpId) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Chirp result;

		result = chirpRepository.findOne(chirpId);
		Assert.notNull(result);

		return result;
	}

	public Chirp save(Chirp chirp) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));

		Assert.notNull(chirp);
		Chirp result;
		result = chirpRepository.save(chirp);

		return result;
	}

	public void delete(Chirp chirp) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Assert.isTrue(chirp.getRecipient().equals(chorbiService.findByPrincipal()) || chirp.getSender().equals(chorbiService.findByPrincipal()));

		Assert.notNull(chirp);
		Chorbi chorbi = chorbiService.findByPrincipal();
		Assert.isTrue(chorbi.equals(chirp.getSender()) || chorbi.equals(chirp.getRecipient()));
		Assert.isTrue(chirp.getId() != 0);

		chirpRepository.delete(chirp);
	}

	// Other business methods -------------------------------------------------

	public Collection<Chirp> chirpSentByActorId() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Chirp> result = new ArrayList<Chirp>();
		Chorbi chorbi = chorbiService.findByPrincipal();
		result = chirpRepository.chirpSentByActorId(chorbi.getId());
		return result;
	}

	public Collection<Chirp> chirpReceivedByActorId() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Chirp> result = new ArrayList<Chirp>();
		Chorbi chorbi = chorbiService.findByPrincipal();
		result = chirpRepository.chirpReceivedByActorId(chorbi.getId());
		return result;
	}

	public Mailer findSenderByChirpId(int id) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Mailer result;
		result = chirpRepository.findSenderByChirpId(id);
		return result;
	}

	public void deleteChirp(Chirp chirp) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Chorbi principal = chorbiService.findByPrincipal();

		if (chirp.getDeleteRecipient() || chirp.getDeleteSender()) {
			delete(chirp);
		} else {
			if (chirp.getRecipient().equals(principal)) {
				chirp.setDeleteRecipient(true);
				save(chirp);
			} else {
				Assert.isTrue(chirp.getSender().equals(principal));
				chirp.setDeleteSender(true);
				save(chirp);
			}
		}
	}

	// Reply --------------------------------------------------------------

	public Collection<Chorbi> reply(int messageId) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Chirp chirp = findOne(messageId);
		Chorbi chorbi = chorbiService.findByPrincipal();

		Assert.isTrue(chirp.getRecipient().equals(chorbi));

		Mailer sender = chirp.getSender();
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();

		chorbies.add((Chorbi) sender);

		return chorbies;
	}

	// Forward ------------------------------------------------------------

	public ChirpForm forward(int messageId) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		ChirpForm result = generate();
		Chirp chirp = findOne(messageId);

		Assert.isTrue(chirp.getSender().equals(chorbiService.findByPrincipal()));

		result.setAttachment(chirp.getAttachment());
		result.setText(chirp.getText());
		result.setSubject(chirp.getSubject());
		result.setSender(chorbiService.findByPrincipal());

		return result;
	}

	// Form methods ----------------------------------------------------------

	public ChirpForm generate() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));

		ChirpForm result = new ChirpForm();
		Mailer sender;

		if (userAccount.getAuthorities().contains(au)) {
			sender = chorbiService.findByPrincipal();
		} else {
			sender = managerService.findByPrincipal();
		}

		result.setSender(sender);
		return result;
	}

	public ChirpForm generate(int eventId) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));

		ChirpForm result = new ChirpForm();
		Mailer sender;

		if (userAccount.getAuthorities().contains(au)) {
			sender = chorbiService.findByPrincipal();
		} else {
			sender = managerService.findByPrincipal();
		}

		result.setSender(sender);
		result.setEventId(eventId);
		return result;
	}

	public Chirp reconstruct(ChirpForm messageForm, BindingResult binding) {
		UrlValidator url = new UrlValidator();
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Chirp result = create();

		Assert.isTrue(!messageForm.getSender().equals(messageForm.getRecipient()));
		if (!messageForm.getAttachment().isEmpty())
			for (String s : messageForm.getAttachment())
				Assert.isTrue(url.isValid(s), "badAttachment");
		result.setAttachment(messageForm.getAttachment());
		result.setRecipient(messageForm.getRecipient());
		result.setSender(chorbiService.findByPrincipal());
		result.setText(messageForm.getText());
		result.setSubject(messageForm.getSubject());

		validator.validate(result, binding);

		return result;

	}

	// Encrypt

	public String encrypt(String mensaje) {
		String result = mensaje;
		result = mensaje.replaceAll("([+]?\\d{1,3})?([ ]?(\\d{3})){3}", "***");
		result = result.replaceAll("[a-zA-Z_%.0-9-]+@[a-zA-Z]+.[a-zA-Z]{3}", "***");

		return result;
	}

	// Chirp update, delete event ----------------------------------------------------

	public void chirpUpdateEvent(Event event) {
		String message = "El evento: " + event.getTitle() + " ha sufrido modifciaciones. Compurebe dichas modifcaciones en el evento.";
		String subject = "Modificaciones en el evento: " + event.getTitle();
		Collection<RelationEvent> relationEvents = event.getRelationEvents();
		Manager manager = managerService.findByPrincipal();

		for (RelationEvent re : relationEvents) {
			Collection<String> attachement = new ArrayList<String>();
			Chirp chirp = create();
			chirp.setSender(manager);
			chirp.setRecipient(re.getChorbi());
			chirp.setText(message);
			chirp.setAttachment(attachement);
			chirp.setSubject(subject);

			save(chirp);
		}
	}

	public void chirpDeleteEvent(String eventTitle, Collection<Chorbi> chorbies) {

		String message = "El evento " + eventTitle + " ha sido eliminado por su creador";
		String subject = "El evento " + eventTitle + " ha sido eliminado.";

		Manager manager = managerService.findByPrincipal();

		for (Chorbi re : chorbies) {
			Collection<String> attachement = new ArrayList<String>();
			Chirp chirp = create();
			chirp.setSender(manager);
			chirp.setRecipient(re);
			chirp.setText(message);
			chirp.setAttachment(attachement);
			chirp.setSubject(subject);

			save(chirp);
		}

	}

	//  Chirp to chorbies register to an event --------------------------------------

	public void chirpToChorbies(Event event, ChirpForm chirpForm, BindingResult binding) {
		Collection<RelationEvent> relationEvents = event.getRelationEvents();
		Mailer sender = managerService.findByPrincipal();

		for (RelationEvent re : relationEvents) {
			Chirp chirp = create();
			chirp.setSender(sender);
			chirp.setRecipient(re.getChorbi());
			chirp.setText(chirpForm.getText());
			chirp.setAttachment(chirpForm.getAttachment());
			chirp.setSubject(chirpForm.getSubject());

			validator.validate(chirp, binding);
			save(chirp);
		}

	}

}

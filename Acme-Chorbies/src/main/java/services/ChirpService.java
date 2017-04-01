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
import forms.ChirpForm;

@Service
@Transactional
public class ChirpService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private ChirpRepository chirpRepository;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ChorbiService		chorbiService;

	@Autowired
	private Validator			validator;
	
	// Constructors -----------------------------------------------------------
	
	public ChirpService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------

		public Chirp create() {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("CHORBI");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Chirp result;
			result = new Chirp();

			Chorbi chorbi;
			chorbi = chorbiService.findByPrincipal();
			Date moment = new Date(System.currentTimeMillis() - 10);
			Collection<String> attachments = new ArrayList<String>();

			result.setSender(chorbi);
			result.setMoment(moment);
			result.setAttachment(attachments);

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

		public Chirp findOne(int messageId) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("CHORBI");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Chirp result;

			result = chirpRepository.findOne(messageId);
			Assert.notNull(result);

			return result;
		}

		public Chirp save(Chirp chirp) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("CHORBI");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(chirp);
			Chirp result;
			result = chirpRepository.save(chirp);

			return result;
		}

		public void delete(Chirp message) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("CHORBI");

			Assert.isTrue(userAccount.getAuthorities().contains(au));
			Assert.isTrue(message.getRecipient().equals(chorbiService.findByPrincipal()) || message.getSender().equals(chorbiService.findByPrincipal()));

			Assert.notNull(message);
			Chorbi chorbi = chorbiService.findByPrincipal();
			Assert.isTrue(chorbi.equals(message.getSender()) || chorbi.equals(message.getRecipient()));
			Assert.isTrue(message.getId() != 0);

			chirpRepository.delete(message);
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
			result = chorbi.getReceived();
			return result;
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

			Chorbi sender = chirp.getSender();
			Collection<Chorbi> chorbies = new ArrayList<Chorbi>();

			chorbies.add(sender);

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

			Assert.isTrue(chirp.getRecipient().equals(chorbiService.findByPrincipal()) || chirp.getSender().equals(chorbiService.findByPrincipal()));

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

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			ChirpForm result = new ChirpForm();

			result.setSender(chorbiService.findByPrincipal());

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

}

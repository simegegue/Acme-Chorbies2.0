
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.ChorbiRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chirp;
import domain.Chorbi;
import domain.RelationLike;
import domain.SearchTemplate;
import forms.ChorbiForm;

@Service
@Transactional
public class ChorbiService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ChorbiRepository		chorbiRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private SearchTemplateService	searchTemplateService;


	// Constructors -----------------------------------------------------------

	public ChorbiService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Chorbi create() {
		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.CHORBI);
		authorities.add(a);
		userAccount.addAuthority(a);
		SearchTemplate search = searchTemplateService.create();
		ArrayList<Chirp> sent = new ArrayList<Chirp>();
		ArrayList<Chirp> received = new ArrayList<Chirp>();
		ArrayList<RelationLike> likesSent = new ArrayList<RelationLike>();
		ArrayList<RelationLike> likesReceived = new ArrayList<RelationLike>();

		Chorbi result;
		result = new Chorbi();
		result.setUserAccount(userAccount);
		result.setBanned(false);
		result.setSent(sent);
		result.setLikesReceived(likesReceived);
		result.setLikesSent(likesSent);
		result.setReceived(received);
		result.setSearchTemplate(search);

		return result;
	}

	public Collection<Chorbi> findAll() {

		Collection<Chorbi> result;

		result = chorbiRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Chorbi findOne(int chorbiId) {

		Chorbi result;

		result = chorbiRepository.findOne(chorbiId);
		Assert.notNull(result);

		return result;
	}

	public Chorbi save(Chorbi chorbi) {

		Assert.notNull(chorbi);
		Chorbi result;
		result = chorbiRepository.save(chorbi);

		return result;
	}

	public void delete(Chorbi chorbi) {

		Assert.notNull(chorbi);

		Assert.isTrue(chorbi.getId() != 0);

		chorbiRepository.delete(chorbi);
	}

	// Other bussiness methods ------------------------------------------------

	public Chorbi findByPrincipal() {
		Chorbi result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		result = chorbiRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}

	public Collection<Chorbi> findChorbiesByGenre(int genre) {
		return chorbiRepository.findChorbiesByGenre(genre);
	}

	public Collection<Chorbi> findChorbiesByKindRelationship(int kindRelationship) {
		return chorbiRepository.findChorbiesByKindRelationship(kindRelationship);
	}

	public void banUnban(Chorbi chorbi) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		if (chorbi.getBanned())
			chorbi.setBanned(false);
		else
			chorbi.setBanned(true);
	}

	public void disableAccount(Chorbi chorbi) {
		if (chorbi.getBanned() == true)
			chorbi.setUserAccount(null);
	}

	public void enableAccount(Chorbi chorbi) {
		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		if (chorbi.getBanned() == false) {
			a.setAuthority(Authority.CHORBI);
			authorities.add(a);
			userAccount.addAuthority(a);
			chorbi.setUserAccount(userAccount);
		}
	}

	//Dashboard Services -------------------

	public Collection<Integer> numberOfChorbiesByCountry() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Integer> result = chorbiRepository.numberOfChorbiesByCountry();
		return result;
	}

	public Collection<Integer> numberOfChorbiesByCity() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Integer> result = chorbiRepository.numberOfChorbiesByCity();
		return result;
	}

	public Collection<Double> minMaxAvgChorbiYear() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Double> result = new ArrayList<Double>();
		Double aux;

		aux = chorbiRepository.minChorbiYear();
		result.add(aux);

		aux = chorbiRepository.maxChorbiYear();
		result.add(aux);

		aux = chorbiRepository.avgChorbiYear();
		result.add(aux);

		return result;
	}

	public Double ratioChorbiesNullCreditCard() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Double result = chorbiRepository.ratioChorbiesNullCreditCard();
		return result;
	}

	public Collection<Double> actFriLoveRatioRelationChorbi() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Double> result = new ArrayList<Double>();
		Double aux;

		aux = chorbiRepository.ratioChorbiesRelationActivities();
		result.add(aux);

		aux = chorbiRepository.ratioChorbiesRelationFriendship();
		result.add(aux);

		aux = chorbiRepository.ratioChorbiesRelationLove();
		result.add(aux);

		return result;
	}

	public Collection<Chorbi> listChorbiesbyLikes() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Chorbi> result = chorbiRepository.listChorbiesbyLikes();
		return result;
	}

	public Collection<Double> minMaxAvgReceivedLikeChorbi() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Double> result = new ArrayList<Double>();
		Double aux;

		aux = chorbiRepository.minReceivedLikeChorbi();
		result.add(aux);

		aux = chorbiRepository.maxReceivedLikeChorbi();
		result.add(aux);

		aux = chorbiRepository.avgReceivedLikeChorbi();
		result.add(aux);

		return result;
	}

	public Collection<Double> minMaxAvgReceivedChirpChorbi() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Double> result = new ArrayList<Double>();
		Double aux;

		aux = chorbiRepository.minReceivedChirpChorbi();
		result.add(aux);

		aux = chorbiRepository.maxReceivedChirpChorbi();
		result.add(aux);

		aux = chorbiRepository.avgReceivedChirpChorbi();
		result.add(aux);

		return result;
	}

	public Collection<Double> minMaxAvgSentChirpChorbi() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Double> result = new ArrayList<Double>();
		Double aux;

		aux = chorbiRepository.minSentChirpChorbi();
		result.add(aux);

		aux = chorbiRepository.maxSentChirpChorbi();
		result.add(aux);

		aux = chorbiRepository.avgSentChirpChorbi();
		result.add(aux);

		return result;
	}

	public Collection<Chorbi> moreChirpReceivedChorbies() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Chorbi> result = chorbiRepository.moreChirpReceivedChorbies();
		return result;
	}

	public Collection<Chorbi> moreChirpSentChorbies() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Chorbi> result = chorbiRepository.moreChirpSentChorbies();
		return result;
	}

	public Collection<String> auxCountry() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<String> result = chorbiRepository.auxCountry();
		return result;
	}

	public Collection<String> auxCity() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<String> result = chorbiRepository.auxCity();
		return result;
	}

	// Form methods -----------------------------------------------------------

	public ChorbiForm generate() {
		ChorbiForm result;
		result = new ChorbiForm();
		return result;
	}

	public Chorbi reconstruct(ChorbiForm chorbiForm, BindingResult binding) {
		Chorbi result = create();
		DateTime today = new DateTime();
		DateTime birthDate = new DateTime(chorbiForm.getBirthDate());
		String password = chorbiForm.getPassword();

		Assert.isTrue(chorbiForm.getPassword2().equals(password), "notEqualPassword");
		Assert.isTrue(birthDate.isBefore(today.minusYears(18)), "not18Old");
		Assert.isTrue(chorbiForm.getAgreed(), "agreedNotAccepted");

		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.CHORBI);
		authorities.add(a);
		userAccount.addAuthority(a);
		userAccount.setPassword(password);
		userAccount.setUsername(chorbiForm.getUsername());

		result.setUserAccount(userAccount);
		result.setName(chorbiForm.getName());
		result.setSurname(chorbiForm.getSurname());
		result.setEmail(chorbiForm.getEmail());
		result.setPhone(chorbiForm.getPhone());
		result.setPicture(chorbiForm.getPicture());
		result.setDescription(chorbiForm.getDescription());
		result.setBirthDate(chorbiForm.getBirthDate());
		result.setCreditCard(chorbiForm.getCreditCard());
		result.setCoordinate(chorbiForm.getCoordinate());
		result.setGenre(chorbiForm.getGenre());
		result.setKindRelationship(chorbiForm.getKindRelationship());

		return result;
	}

}

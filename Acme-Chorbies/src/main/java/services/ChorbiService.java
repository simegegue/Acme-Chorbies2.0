
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChorbiRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chorbi;

@Service
@Transactional
public class ChorbiService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ChorbiRepository	chorbiRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ChorbiService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Chorbi create() {

		Chorbi result;
		result = new Chorbi();

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

		aux = chorbiRepository.minSendedChirpChorbi();
		result.add(aux);

		aux = chorbiRepository.maxSendedChirpChorbi();
		result.add(aux);

		aux = chorbiRepository.avgSendedChirpChorbi();
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

}

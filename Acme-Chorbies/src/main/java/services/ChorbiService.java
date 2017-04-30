
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ChorbiRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chirp;
import domain.Chorbi;
import domain.CreditCard;
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

	@Autowired
	private Validator				validator;


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
		result.setAvgStars(0.0);
		result.setSearchTemplate(search);

		return result;
	}

	public Collection<Chorbi> findAll() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		Authority ai = new Authority();
		au.setAuthority("ADMIN");
		ai.setAuthority("CHORBI");

		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(ai));

		Collection<Chorbi> result;

		result = chorbiRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Chorbi findOne(int chorbiId) {

		Chorbi result;
		result = chorbiRepository.findOne(chorbiId);
		return result;
	}

	public Chorbi save(Chorbi chorbi) {

		Assert.notNull(chorbi);

		String password = chorbi.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String md5 = encoder.encodePassword(password, null);
		chorbi.getUserAccount().setPassword(md5);

		if (chorbi.getId() != 0) {
			Assert.isTrue(findByPrincipal().getId() == chorbi.getId());
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("CHORBI");
			Assert.isTrue(userAccount.getAuthorities().contains(au));
		}
		SearchTemplate search = searchTemplateService.save(chorbi.getSearchTemplate());
		chorbi.setSearchTemplate(search);
		Chorbi result = chorbiRepository.save(chorbi);

		return result;
	}

	public Chorbi save2(Chorbi chorbi) {
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

	public Chorbi findChorbiByUsername(String name) {
		return chorbiRepository.findChorbiByUsername(name);
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

		if (chorbi.getBanned() == true) {
			chorbi.setBanned(false);
		} else {
			chorbi.setBanned(true);
		}
	}

	public void disableEnable(Chorbi chorbi) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		UserAccount aux = chorbi.getUserAccount();
		if (chorbi.getBanned()) {
			chorbi.setUserAccount(null);
		} else {
			chorbi.setUserAccount(aux);
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

	public ChorbiForm generateForm(Chorbi chorbi) {
		ChorbiForm result = new ChorbiForm();

		result.setId(chorbi.getId());
		result.setUsername(chorbi.getUserAccount().getUsername());
		result.setPassword(chorbi.getUserAccount().getPassword());
		result.setPassword2(chorbi.getUserAccount().getPassword());
		result.setAgreed(true);
		result.setBirthDate(chorbi.getBirthDate());
		result.setCreditCard(chorbi.getCreditCard());
		result.setCoordinate(chorbi.getCoordinate());
		result.setDescription(chorbi.getDescription());
		result.setEmail(chorbi.getEmail());
		result.setName(chorbi.getName());
		result.setPhone(chorbi.getPhone());
		result.setPicture(chorbi.getPicture());
		result.setSurname(chorbi.getSurname());
		result.setGenre(chorbi.getGenre());
		result.setKindRelationship(chorbi.getKindRelationship());

		return result;
	}

	public Chorbi reconstructEditPersonalData(ChorbiForm chorbiForm, BindingResult binding) {
		Chorbi result;

		result = chorbiRepository.findOne(chorbiForm.getId());

		result.setName(chorbiForm.getName());
		result.setSurname(chorbiForm.getSurname());
		result.setEmail(chorbiForm.getEmail());
		result.setPhone(chorbiForm.getPhone());
		result.setPicture(chorbiForm.getPicture());
		result.setDescription(chorbiForm.getDescription());
		result.setBirthDate(chorbiForm.getBirthDate());
		if (chorbiForm.getCreditCard().getBrandName() == null) {
			result.setCreditCard(null);
		} else {
			result.setCreditCard(chorbiForm.getCreditCard());
		}
		result.setCoordinate(chorbiForm.getCoordinate());
		result.setGenre(chorbiForm.getGenre());
		result.setKindRelationship(chorbiForm.getKindRelationship());

		validator.validate(result, binding);

		return result;
	}

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
		if (chorbiForm.getCreditCard().getBrandName() == "" && chorbiForm.getCreditCard().getHolderName() == "" && chorbiForm.getCreditCard().getNumber() == "" && chorbiForm.getCreditCard().getCvv() == 0
			&& chorbiForm.getCreditCard().getExpirationMonth() == 0 && chorbiForm.getCreditCard().getExpirationYear() == 0) {
			result.setCreditCard(null);
		} else {
			if (chorbiForm.getCreditCard().getBrandName() == "" || chorbiForm.getCreditCard().getHolderName() == "" || chorbiForm.getCreditCard().getNumber() == "" || chorbiForm.getCreditCard().getCvv() == 0
				|| chorbiForm.getCreditCard().getExpirationMonth() == 0 || chorbiForm.getCreditCard().getExpirationYear() == 0) {
				Assert.isTrue(false);
			} else {
				result.setCreditCard(chorbiForm.getCreditCard());
			}
		}
		result.setCoordinate(chorbiForm.getCoordinate());
		result.setGenre(chorbiForm.getGenre());
		result.setKindRelationship(chorbiForm.getKindRelationship());

		validator.validate(result, binding);

		return result;
	}

	public Map<Chorbi, Integer> map() {
		Map<Chorbi, Integer> map = new HashMap<Chorbi, Integer>();
		List<Object[]> aux = chorbiRepository.findByAge();
		for (Object[] o : aux) {
			map.put((Chorbi) o[0], (Integer) o[1]);
		}
		return map;
	}
	public Collection<Chorbi> minusPlusFive(Integer age) {
		Collection<Chorbi> result = new ArrayList<Chorbi>();
		Map<Chorbi, Integer> minusPlusFive = map();
		for (Chorbi c : minusPlusFive.keySet()) {
			if ((minusPlusFive.get(c) <= (age + 5)) && (minusPlusFive.get(c) >= (age - 5))) {
				result.add(c);
			}
		}

		return result;
	}
	public Collection<Chorbi> ageAndkey(Collection<Chorbi> aux, String key) {
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		for (Chorbi c : aux) {
			if ((c.getName().contains(key) || c.getDescription().contains(key))) {
				chorbies.add(c);
			}
		}
		return chorbies;

	}

	public Collection<Chorbi> findByCoordinatesCountry(String keyword) {
		Collection<Chorbi> result = new ArrayList<Chorbi>();
		result = chorbiRepository.findByCoordinatesCountry(keyword);
		return result;
	}

	public Collection<Chorbi> findByCoordinatesState(String keyword) {
		Collection<Chorbi> result = new ArrayList<Chorbi>();
		result = chorbiRepository.findByCoordinatesState(keyword);
		return result;
	}

	public Collection<Chorbi> findByCoordinatesProvince(String keyword) {
		Collection<Chorbi> result = new ArrayList<Chorbi>();
		result = chorbiRepository.findByCoordinatesProvince(keyword);
		return result;
	}

	public Collection<Chorbi> findByCoordinatesCity(String keyword) {
		Collection<Chorbi> result = new ArrayList<Chorbi>();
		result = chorbiRepository.findByCoordinatesCity(keyword);
		return result;
	}
	public Collection<Chorbi> searchCoordinate(Collection<Chorbi> aux, String country, String state, String province, String city) {
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		for (Chorbi c : aux) {
			if (country != "") {
				if (state == "" && province == "" && city == "") {
					if (c.getCoordinate().getCountry().equals(country)) {
						chorbies.add(c);
					}
				} else if (state != "" && province == "" && city == "") {
					if (c.getCoordinate().getCountry().equals(country) && c.getCoordinate().getState().equals(state)) {
						chorbies.add(c);
					}
				} else if (state != "" && province != "" && city == "") {
					if (c.getCoordinate().getCountry().equals(country) && c.getCoordinate().getState().equals(state) && c.getCoordinate().getProvince().equals(province)) {
						chorbies.add(c);
					}
				} else if (state != "" && province == "" && city != "") {
					if (c.getCoordinate().getCountry().equals(country) && c.getCoordinate().getState().equals(state) && c.getCoordinate().getCity().equals(city)) {
						chorbies.add(c);
					}
				} else if (state == "" && province != "" && city != "") {
					if (c.getCoordinate().getCountry().equals(country) && c.getCoordinate().getProvince().equals(province) && c.getCoordinate().getCity().equals(city)) {
						chorbies.add(c);
					}
				} else if (state == "" && province != "" && city == "") {
					if (c.getCoordinate().getCountry().equals(country) && c.getCoordinate().getProvince().equals(province)) {
						chorbies.add(c);
					}
				} else if (state == "" && province == "" && city != "") {
					if (c.getCoordinate().getCountry().equals(country) && c.getCoordinate().getCity().equals(city)) {
						chorbies.add(c);
					}
				} else if (state != "" && province != "" && city != "") {
					if (c.getCoordinate().getCountry().equals(country) && c.getCoordinate().getState().equals(state) && c.getCoordinate().getProvince().equals(province) && c.getCoordinate().getCity().equals(city)) {
						chorbies.add(c);
					}
				}
			} else if (state != "") {
				if (province != "" && city != "") {
					if (c.getCoordinate().getState().equals(state) && c.getCoordinate().getProvince().equals(province) && c.getCoordinate().getCity().equals(city)) {
						chorbies.add(c);
					}
				} else if (province != "" && city == "") {
					if (c.getCoordinate().getState().equals(state) && c.getCoordinate().getProvince().equals(province)) {
						chorbies.add(c);
					}
				} else if (province == "" && city != "") {
					if (c.getCoordinate().getState().equals(state) && c.getCoordinate().getCity().equals(city)) {
						chorbies.add(c);
					}

				} else {
					if (c.getCoordinate().getState().equals(state)) {
						chorbies.add(c);
					}
				}
			} else if (province != "") {
				if (city != "") {
					if (c.getCoordinate().getProvince().equals(province) && c.getCoordinate().getProvince().equals(province)) {
						chorbies.add(c);
					}

				} else {
					if (c.getCoordinate().getProvince().equals(province)) {
						chorbies.add(c);
					}
				}

			} else if (city != "") {
				if (c.getCoordinate().getCity().equals(city)) {
					chorbies.add(c);
				}
			}
		}

		return chorbies;
	}

	public Collection<Chorbi> CoordinateSearch(Collection<Chorbi> aux, String country, String state, String province, String city) {
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();

		for (Chorbi c : aux) {
			if (c.getCoordinate().getCountry().compareTo(country) == 0) {
				if ((c.getCoordinate().getState().compareTo(state) != 0) && (c.getCoordinate().getProvince().compareTo(province) != 0) && (c.getCoordinate().getCity().compareTo(city) != 0)) {
					chorbies.add(c);

				} else if ((c.getCoordinate().getState().compareTo(state) == 0) && (c.getCoordinate().getProvince().compareTo(province) != 0) && (c.getCoordinate().getCity().compareTo(city) != 0)) {
					chorbies.add(c);

				} else if ((c.getCoordinate().getState().compareTo(state) != 0) && (c.getCoordinate().getProvince().compareTo(province) == 0) && (c.getCoordinate().getCity().compareTo(city) != 0)) {
					chorbies.add(c);

				} else if ((c.getCoordinate().getState().compareTo(state) != 0) && (c.getCoordinate().getProvince().compareTo(province) != 0) && (c.getCoordinate().getCity().compareTo(city) == 0)) {
					chorbies.add(c);

				} else if ((c.getCoordinate().getState().compareTo(state) == 0) && (c.getCoordinate().getProvince().compareTo(province) == 0) && (c.getCoordinate().getCity().compareTo(city) != 0)) {
					chorbies.add(c);
				} else if ((c.getCoordinate().getState().compareTo(state) == 0) && (c.getCoordinate().getProvince().compareTo(province) != 0) && (c.getCoordinate().getCity().compareTo(city) == 0)) {
					chorbies.add(c);
				} else if ((c.getCoordinate().getState().compareTo(state) != 0) && (c.getCoordinate().getProvince().compareTo(province) == 0) && (c.getCoordinate().getCity().compareTo(city) == 0)) {
					chorbies.add(c);
				} else {
					chorbies.add(c);
				}
			} else if (c.getCoordinate().getState().compareTo(state) == 0) {
				if ((c.getCoordinate().getProvince().compareTo(province) != 0) && (c.getCoordinate().getCity().compareTo(city) != 0)) {
					chorbies.add(c);
				} else if ((c.getCoordinate().getProvince().compareTo(province) == 0) && (c.getCoordinate().getCity().compareTo(city) != 0)) {
					chorbies.add(c);
				} else if ((c.getCoordinate().getProvince().compareTo(province) != 0) && (c.getCoordinate().getCity().compareTo(city) == 0)) {
					chorbies.add(c);
				} else {
					chorbies.add(c);
				}
			} else if (c.getCoordinate().getProvince().compareTo(province) == 0) {
				if (c.getCoordinate().getCity().compareTo(city) != 0) {
					chorbies.add(c);
				} else {
					chorbies.add(c);
				}
			} else if (c.getCoordinate().getCity().compareTo(city) == 0) {
				chorbies.add(c);
			}

		}
		return chorbies;

	}

	public Collection<Chorbi> findBySearchTemplate(SearchTemplate searchTemplate) {
		Collection<Chorbi> result = new ArrayList<Chorbi>();
		Collection<Chorbi> aux = new ArrayList<Chorbi>();
		Collection<Chorbi> aux2 = new ArrayList<Chorbi>();
		aux = findAll();

		//Parte de edad + keyword
		if (searchTemplate.getAge() != 0 && (searchTemplate.getKeyword() == null || searchTemplate.getKeyword().isEmpty())) {
			aux = minusPlusFive(searchTemplate.getAge());
		} else if ((searchTemplate.getAge() == 0) && (searchTemplate.getKeyword() != null)) {
			aux = ageAndkey(aux, searchTemplate.getKeyword());
		} else if ((searchTemplate.getAge() != 0) && (searchTemplate.getKeyword() != null)) {
			aux = minusPlusFive(searchTemplate.getAge());
			aux = ageAndkey(aux, searchTemplate.getKeyword());
		}
		aux2 = aux;

		//Parte de coordenadas

		if (!(searchTemplate.getCoordinate().getCity().isEmpty()) || !(searchTemplate.getCoordinate().getCountry().isEmpty()) || !(searchTemplate.getCoordinate().getProvince().isEmpty()) || !(searchTemplate.getCoordinate().getState().isEmpty())) {
			aux = searchCoordinate(aux2, searchTemplate.getCoordinate().getCountry(), searchTemplate.getCoordinate().getState(), searchTemplate.getCoordinate().getProvince(), searchTemplate.getCoordinate().getCity());
		}

		//Parte de generos y tipos de relacion
		if ((searchTemplate.getGenre().getValue().equals("none")) && (searchTemplate.getKindRelationship().getValue().equals("none"))) {
			result = aux;
		} else if (searchTemplate.getGenre().getValue().equals("none")) {
			for (Chorbi c : aux) {
				if (c.getKindRelationship().equals(searchTemplate.getKindRelationship())) {
					result.add(c);
				}
			}
		} else if (searchTemplate.getKindRelationship().getValue().equals("none")) {
			for (Chorbi c : aux) {
				if (c.getGenre().equals(searchTemplate.getGenre())) {
					result.add(c);
				}
			}
		} else {
			for (Chorbi c : aux) {
				if (c.getGenre().equals(searchTemplate.getGenre()) && c.getKindRelationship().equals(searchTemplate.getKindRelationship())) {
					result.add(c);
				}
			}
		}

		//Resultado final
		searchTemplate.setChorbies(result);
		return result;
	}

	public static boolean check(CreditCard creditCard) {
		boolean validador = false;
		int sum = 0;
		Calendar fecha = Calendar.getInstance();
		String numero = creditCard.getNumber();
		int mes = fecha.get(Calendar.MONTH) + 1;
		int año = fecha.get(Calendar.YEAR);

		if (creditCard.getExpirationYear() > año) {
			validador = true;
		} else if (creditCard.getExpirationYear() == año) {
			if (creditCard.getExpirationMonth() >= mes) {
				validador = true;
			}
		}

		if (validador) {
			validador = false;
			for (int i = numero.length() - 1; i >= 0; i--) {
				int n = Integer.parseInt(numero.substring(i, i + 1));
				if (validador) {
					n *= 2;
					if (n > 9) {
						n = (n % 10) + 1;
					}
				}
				sum += n;
				validador = !validador;
			}
			if (sum % 10 == 0) {
				validador = true;
			}
		}

		return validador;
	}

	public Boolean principalCheckCreditCard() {
		Chorbi c = findByPrincipal();
		Boolean b;
		if (c.getCreditCard() == null) {
			b = false;
		} else {
			b = ChorbiService.check(c.getCreditCard());
		}
		return b;
	}

	public String encrypt(String mensaje) {
		String result = mensaje;
		result = mensaje.replaceAll("([+]?\\d{1,3})?([ ]?(\\d{3})){3}", "***");
		result = result.replaceAll("[a-zA-Z_%.0-9-]+@[a-zA-Z]+.[a-zA-Z]{3}", "***");

		return result;
	}

	//Dashboard

	public Collection<Chorbi> chorbiesByEvents() {
		Collection<Chorbi> result = chorbiRepository.chorbiesByEvents();
		return result;
	}

	public Map<Chorbi, Double> mapChorbiFee() {
		Map<Chorbi, Double> map = new HashMap<Chorbi, Double>();
		List<Object[]> aux = chorbiRepository.chorbiFeeAmount();
		for (Object[] o : aux) {
			map.put((Chorbi) o[0], (Double) o[1]);
		}
		return map;
	}

	public Collection<Double> minMaxAvgStarsChorbi() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Double> result = new ArrayList<Double>();
		Double aux;

		aux = chorbiRepository.minStars();
		result.add(aux);

		aux = chorbiRepository.maxStars();
		result.add(aux);

		aux = chorbiRepository.avgStars();
		result.add(aux);

		return result;
	}

	public Map<Chorbi, Double> mapChorbiStars() {
		Map<Chorbi, Double> map = new HashMap<Chorbi, Double>();
		List<Object[]> aux = chorbiRepository.chorbiStars();
		for (Object[] o : aux) {
			map.put((Chorbi) o[0], (Double) o[1]);
		}
		return map;
	}

	public Collection<Chorbi> chorbiesStars() {
		Collection<Chorbi> result = chorbiRepository.chorbiStars2();
		return result;
	}

	public Double findAvgStars(Chorbi chorbi) {
		Double result;
		result = chorbiRepository.fingAvgStars(chorbi);
		return result;
	}
	
	public void restartChorbiesFee(){
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		
		Collection<Chorbi> chorbies = findAll();
		double restart = 0.0;
		
		for(Chorbi c : chorbies){
			c.setFeeAmount(restart);
			save2(c);
		}
	}

	public void updateAvgStars(Chorbi chorbi) {
		chorbi.setAvgStars(chorbiRepository.fingAvgStars(chorbi));
		save2(chorbi);
	}

}

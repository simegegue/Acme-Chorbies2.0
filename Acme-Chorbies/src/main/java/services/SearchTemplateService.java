
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SearchTemplateRepository;
import security.LoginService;
import security.UserAccount;
import domain.Chorbi;
import domain.SearchTemplate;
import forms.SearchTemplateForm;

@Service
@Transactional
public class SearchTemplateService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SearchTemplateRepository	searchTemplateRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ChorbiService				chorbiService;

	@Autowired
	private GenreService				genreService;

	@Autowired
	private KindRelationshipService		kindRelationshipService;

	@Autowired
	private Validator					validator;


	// Constructors -----------------------------------------------------------

	public SearchTemplateService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SearchTemplate create() {

		SearchTemplate result;
		result = new SearchTemplate();

		Date date = new Date(System.currentTimeMillis() - 1000);
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		result.setGenre(genreService.findOne(49));
		result.setKindRelationship(kindRelationshipService.findOne(52));
		result.setLastTimeSearched(date);
		result.setChorbies(chorbies);

		return result;
	}

	public Collection<SearchTemplate> findAll() {

		Collection<SearchTemplate> result;

		result = searchTemplateRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public SearchTemplate findOne(int searchTemplateId) {

		SearchTemplate result;

		result = searchTemplateRepository.findOne(searchTemplateId);
		Assert.notNull(result);

		return result;
	}

	public SearchTemplate save(SearchTemplate searchTemplate) {

		SearchTemplate result;
		result = searchTemplateRepository.save(searchTemplate);

		return result;
	}

	public void delete(SearchTemplate searchTemplate) {

		Assert.notNull(searchTemplate);

		Assert.isTrue(searchTemplate.getId() != 0);

		searchTemplateRepository.delete(searchTemplate);
	}

	// Other bussiness methods ------------------------------------------------

	public Collection<SearchTemplate> findSearchTemplateByGenre(int genre) {
		return searchTemplateRepository.findSearchTemplateByGenre(genre);
	}

	public Collection<SearchTemplate> findSearchTemplateByKindRelationship(int kindRelationship) {
		return searchTemplateRepository.findSearchTemplateByKindRelationship(kindRelationship);
	}

	public Boolean compareSearch(SearchTemplateForm st) {
		SearchTemplate old = findByPrincipal();
		if (st.getAge() == null) {
			st.setAge(0);
		}
		Boolean res = false;
		if (old.getAge().compareTo(st.getAge()) == 0 && old.getKeyword().compareTo(st.getKeyword()) == 0 && old.getCoordinate().getCity().compareTo(st.getCoordinate().getCity()) == 0
			&& old.getCoordinate().getCountry().compareTo(st.getCoordinate().getCountry()) == 0 && old.getCoordinate().getProvince().compareTo(st.getCoordinate().getProvince()) == 0
			&& old.getCoordinate().getState().compareTo(st.getCoordinate().getState()) == 0 && old.getKindRelationship().getValue().compareTo(st.getKindRelationship().getValue()) == 0 && old.getGenre().getValue().compareTo(st.getGenre().getValue()) == 0) {
			res = true;
		}
		return res;
	}

	public SearchTemplate findByPrincipal() {
		SearchTemplate result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = searchTemplateRepository.findByUserAccount(userAccount);

		return result;
	}

	//Form Services

	public SearchTemplateForm generateForm() {
		SearchTemplateForm result;

		result = new SearchTemplateForm();
		return result;
	}

	public SearchTemplate reconstruct(SearchTemplateForm searchTemplateForm, BindingResult binding) {
		Chorbi chorbi = chorbiService.findByPrincipal();

		SearchTemplate result = chorbi.getSearchTemplate();
		if (searchTemplateForm.getAge() == null) {
			searchTemplateForm.setAge(0);
		}
		result.setAge(searchTemplateForm.getAge());
		result.setKeyword(searchTemplateForm.getKeyword());
		result.setCoordinate(searchTemplateForm.getCoordinate());
		result.setGenre(((searchTemplateForm.getGenre())));
		result.setKindRelationship((searchTemplateForm.getKindRelationship()));

		Date d = new Date(System.currentTimeMillis() - 10000);
		result.setLastTimeSearched(d);
		validator.validate(result, binding);

		return result;
	}

	public SearchTemplateForm transform(SearchTemplate searchTemplate) {
		SearchTemplateForm result = generateForm();
		result.setAge(searchTemplate.getAge());
		result.setKeyword(searchTemplate.getKeyword());
		result.setCoordinate(searchTemplate.getCoordinate());
		result.setGenre(searchTemplate.getGenre());
		result.setKindRelationship(searchTemplate.getKindRelationship());
		return result;
	}

}

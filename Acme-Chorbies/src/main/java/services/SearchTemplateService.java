
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SearchTemplateRepository;
import security.LoginService;
import security.UserAccount;
import domain.Chorbi;
import domain.SearchTemplate;

@Service
@Transactional
public class SearchTemplateService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SearchTemplateRepository	searchTemplateRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SearchTemplateService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SearchTemplate create() {

		SearchTemplate result;
		result = new SearchTemplate();

		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();

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

		Assert.notNull(searchTemplate);
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

	public Boolean compareSearch(SearchTemplate st) {
		SearchTemplate old = findByPrincipal();
		Boolean res = false;
		if (old.getAge().compareTo(st.getAge()) != 0 
			&& old.getKeyword().compareTo(st.getKeyword()) != 0 
			&& old.getCoordinate().getCity().compareTo(st.getCoordinate().getCity()) != 0 
			&& old.getCoordinate().getCountry().compareTo(st.getCoordinate().getCountry()) != 0
			&& old.getCoordinate().getProvince().compareTo(st.getCoordinate().getProvince()) != 0
			&& old.getCoordinate().getState().compareTo(st.getCoordinate().getState()) != 0
			&& old.getKindRelationship().getValue().compareTo(st.getKindRelationship().getValue()) != 0
			&& old.getGenre().getValue().compareTo(st.getGenre().getValue()) != 0)
			res = true;
		return res;
	}

	public SearchTemplate findByPrincipal() {
		SearchTemplate result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = searchTemplateRepository.findByUserAccount(userAccount);

		return result;
	}

}

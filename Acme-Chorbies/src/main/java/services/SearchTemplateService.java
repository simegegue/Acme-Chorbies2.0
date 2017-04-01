package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SearchTemplateRepository;
import domain.SearchTemplate;

@Service
@Transactional
public class SearchTemplateService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private SearchTemplateRepository searchTemplateRepository;
	
	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SearchTemplateService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
			
	public SearchTemplate create() {

		SearchTemplate result;
		result = new SearchTemplate();

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

	public Collection<SearchTemplate> findSearchTemplateByGenre(int genre){
		return searchTemplateRepository.findSearchTemplateByGenre(genre);
	}
	
	public Collection<SearchTemplate> findSearchTemplateByKindRelationship(int kindRelationship){
		return searchTemplateRepository.findSearchTemplateByKindRelationship(kindRelationship);
	}
	
}

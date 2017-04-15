
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.KindRelationshipRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.KindRelationship;
import forms.KindRelationshipForm;

@Service
@Transactional
public class KindRelationshipService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private KindRelationshipRepository	kindRelationshipRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator					validator;


	// Constructors -----------------------------------------------------------

	public KindRelationshipService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public KindRelationship create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		KindRelationship result;
		result = new KindRelationship();

		return result;
	}

	public Collection<KindRelationship> findAll() {

		Collection<KindRelationship> result;
		result = kindRelationshipRepository.findAll();
		return result;
	}

	public KindRelationship findOne(int kindRelationshipId) {

		KindRelationship result;
		result = kindRelationshipRepository.findOne(kindRelationshipId);
		return result;
	}

	public KindRelationship save(KindRelationship kindRelationship) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(kindRelationship);
		KindRelationship result;
		for (KindRelationship k : kindRelationshipRepository.findAll()) {
			Assert.isTrue(!k.getValue().equals(kindRelationship.getValue()), "usedKindRelationship");
		}
		result = kindRelationshipRepository.save(kindRelationship);

		return result;
	}

	public void delete(KindRelationship kindRelationship) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(kindRelationship);
		Assert.isTrue(kindRelationship.getId() != 0);

		/*
		 * KindRelationship aux = findOne(53);
		 * 
		 * Collection<SearchTemplate> searches = searchTemplateService.findSearchTemplateByKindRelationship(kindRelationship.getId());
		 * Collection<Chorbi> chorbies = chorbiService.findChorbiesByKindRelationship(kindRelationship.getId());
		 * if(!chorbies.isEmpty()){
		 * for(Chorbi c : chorbies){
		 * c.setKindRelationship(aux);
		 * chorbiService.save(c);
		 * }
		 * }
		 * if(!searches.isEmpty()){
		 * for(SearchTemplate s : searches){
		 * s.setKindRelationship(aux);
		 * searchTemplateService.save(s);
		 * }
		 * }
		 */
		kindRelationshipRepository.delete(kindRelationship);
	}

	// Other bussiness methods ------------------------------------------------
	public KindRelationship findKindRelationshipByValue(String value) {
		return kindRelationshipRepository.findKindRelationshipByValue(value);
	}
	public KindRelationship findDefault() {
		return kindRelationshipRepository.findDefault();
	}

	// Form methods ----------------------------------------------------------

	public KindRelationshipForm generateForm() {
		KindRelationshipForm result = new KindRelationshipForm();

		return result;
	}

	public KindRelationship reconstruct(KindRelationshipForm kindRelationshipForm, BindingResult binding) {
		KindRelationship result;
		if (kindRelationshipForm.getId() == 0)
			result = create();
		else {
			result = kindRelationshipRepository.findOne(kindRelationshipForm.getId());
		}
		result.setValue(kindRelationshipForm.getValue());
		validator.validate(result, binding);
		return result;
	}

	public KindRelationshipForm transform(KindRelationship kindRelationship) {
		KindRelationshipForm result = generateForm();
		result.setId(kindRelationship.getId());
		result.setValue(kindRelationship.getValue());
		return result;
	}

}

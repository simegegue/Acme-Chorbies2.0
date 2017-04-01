package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.KindRelationshipRepository;
import domain.Chorbi;
import domain.KindRelationship;
import domain.SearchTemplate;
import forms.KindRelationshipForm;

@Service
@Transactional
public class KindRelationshipService {
	
	// Managed repository -----------------------------------------------------
	
		@Autowired
		private KindRelationshipRepository kindRelationshipRepository;
		
		// Supporting services ----------------------------------------------------
		
		@Autowired
		private ChorbiService chorbiService;
		
		@Autowired
		private SearchTemplateService searchTemplateService;
		
		
		@Autowired
		private Validator  validator;
		
		// Constructors -----------------------------------------------------------

		public KindRelationshipService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------
				
		public KindRelationship create() {

			KindRelationship result;
			result = new KindRelationship();

			return result;
		}

		public Collection<KindRelationship> findAll() {

			Collection<KindRelationship> result;

			result = kindRelationshipRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public KindRelationship findOne(int kindRelationshipId) {

			KindRelationship result;

			result = kindRelationshipRepository.findOne(kindRelationshipId);
			Assert.notNull(result);

			return result;
		}

		public KindRelationship save(KindRelationship kindRelationship) {

			Assert.notNull(kindRelationship);
			KindRelationship result;
			result = kindRelationshipRepository.save(kindRelationship);

			return result;
		}

		public void delete(KindRelationship kindRelationship) {

			Assert.notNull(kindRelationship);
			Assert.isTrue(kindRelationship.getId() != 0);

			KindRelationship aux = findKindRelationshipByValue("none");
					
			Collection<SearchTemplate> searches = searchTemplateService.findSearchTemplateByKindRelationship(kindRelationship.getId());
			Collection<Chorbi> chorbies = chorbiService.findChorbiesByKindRelationship(kindRelationship.getId());
			
			for(Chorbi c : chorbies){
				c.setKindRelationship(aux);
				chorbiService.save(c);
			}	
			
			for(SearchTemplate s : searches){
				s.setKindRelationship(aux);
				searchTemplateService.save(s);
			}
			
			kindRelationshipRepository.delete(kindRelationship);
		}
		
		// Other bussiness methods ------------------------------------------------
		public KindRelationship findKindRelationshipByValue(String value){
			return kindRelationshipRepository.findKindRelationshipByValue(value);
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
					for(KindRelationship k : kindRelationshipRepository.findAll()){
						Assert.isTrue(!k.getValue().equals(kindRelationshipForm.getValue()),"usedKindRelationship");
					}
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

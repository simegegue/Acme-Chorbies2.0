package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.KindRelationshipRepository;
import domain.KindRelationship;

@Service
@Transactional
public class KindRelationshipService {
	
	// Managed repository -----------------------------------------------------
	
		@Autowired
		private KindRelationshipRepository kindRelationshipRepository;
		
		// Supporting services ----------------------------------------------------

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

			kindRelationshipRepository.delete(kindRelationship);
		}
		
		// Other bussiness methods ------------------------------------------------

}

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RelationLikeRepository;
import domain.Chorbi;
import domain.RelationLike;

@Service
@Transactional
public class RelationLikeService {
	
	// Managed repository -----------------------------------------------------
	
		@Autowired
		private RelationLikeRepository relationLikeRepository;
		
		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------

		public RelationLikeService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------
				
		public RelationLike create() {

			RelationLike result;
			result = new RelationLike();

			return result;
		}

		public Collection<RelationLike> findAll() {

			Collection<RelationLike> result;

			result = relationLikeRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public RelationLike findOne(int relationLikeId) {

			RelationLike result;

			result = relationLikeRepository.findOne(relationLikeId);
			Assert.notNull(result);

			return result;
		}

		public RelationLike save(RelationLike relationLike) {

			Assert.notNull(relationLike);
			RelationLike result;
			result = relationLikeRepository.save(relationLike);

			return result;
		}

		public void delete(RelationLike relationLike) {

			Assert.notNull(relationLike);
					
			Assert.isTrue(relationLike.getId() != 0);

			relationLikeRepository.delete(relationLike);
		}
		
		// Other bussiness methods ------------------------------------------------
		public Collection<Chorbi> findByLikesSent(int id){
			Collection<Chorbi>result;
			result=relationLikeRepository.findByLikesSent(id);
			return result;
		}
		public RelationLike giveLike(Chorbi likeSender,Chorbi likeRecipient){
			RelationLike res=create();
			Boolean contains=false;
			for(RelationLike l:likeSender.getLikesSent()){
				if(l.getLikeRecipient().equals(likeRecipient)){
					contains=true;
				}
			}
			if(contains=false){
				if(!likeSender.equals(likeRecipient)){
					RelationLike rl=create();
					
					rl.setLikeSender(likeSender);
					rl.setLikeRecipient(likeRecipient);
					likeSender.getLikesSent().add(rl);
					likeRecipient.getLikesReceived().add(rl);
					res=rl;
					
				}
				
			}
			return res;
			
		}
}

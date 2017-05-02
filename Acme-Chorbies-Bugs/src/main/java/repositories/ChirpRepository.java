package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;
import domain.Mailer;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer>{
	
	@Query("select c from Chirp c where c.sender.id=?1 and c.deleteSender=false")
	Collection<Chirp> chirpSentByActorId(int actorId);

	@Query("select c from Chirp c where c.recipient.id=?1 and c.deleteRecipient=false")
	Collection<Chirp> chirpReceivedByActorId(int actorId);
	
	@Query("select c.sender from Chirp c where c.id=? and c.deleteRecipient=false")
	Mailer findSenderByChirpId(int id);

}

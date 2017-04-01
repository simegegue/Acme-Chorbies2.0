
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;

@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

	@Query("select c from Chorbi c where c.userAccount.id=?1")
	Chorbi findByUserAccountId(int id);

	//Dashboard

	@Query("select count(c) from Chorbi c group by country")
	Collection<Integer> numberOfChorbiesByCountry();

	@Query("select count(c) from Chorbi c group by city")
	Collection<Integer> numberOfChorbiesByCity();

	@Query("select min(datediff(current_date,c.birthDate)/365) from Chorbi c")
	Double minChorbiYear();

	@Query("select max(datediff(current_date,c.birthDate)/365) from Chorbi c")
	Double maxChorbiYear();

	@Query("select avg(datediff(current_date,c.birthDate)/365) from Chorbi c")
	Double avgChorbiYear();

	@Query("select 1.0*(select count(c) from Chorbi c where c.creditCard=null)/ count(c2) from Chorbi c2")
	Double ratioChorbiesNullCreditCard();

	@Query("select 1.0*(select count(c) from Chorbi c where c.kindRelationship.value='activities')/ count(c2) from Chorbi c2")
	Double ratioChorbiesRelationActivities();

	@Query("select 1.0*(select count(c) from Chorbi c where c.kindRelationship.value='friendship')/ count(c2) from Chorbi c2")
	Double ratioChorbiesRelationFriendship();

	@Query("select 1.0*(select count(c) from Chorbi c where c.kindRelationship.value='love')/ count(c2) from Chorbi c2")
	Double ratioChorbiesRelationLove();

	@Query("select c from Chorbi c order by c.likesReceived.size desc")
	Collection<Chorbi> listChorbiesbyLikes();

	@Query("select min(c.likesReceived.size)from Chorbi c")
	Double minReceivedLikeChorbi();

	@Query("select max(c.likesReceived.size)from Chorbi c")
	Double maxReceivedLikeChorbi();

	@Query("select avg(c.likesReceived.size)from Chorbi c")
	Double avgReceivedLikeChorbi();

	@Query("select min(c.received.size)from Chorbi c")
	Double minReceivedChirpChorbi();

	@Query("select max(c.received.size)from Chorbi c")
	Double maxReceivedChirpChorbi();

	@Query("select avg(c.received.size)from Chorbi c")
	Double avgReceivedChirpChorbi();

	@Query("select min(c.sended.size)from Chorbi c")
	Double minSendedChirpChorbi();

	@Query("select max(c.sended.size)from Chorbi c")
	Double maxSendedChirpChorbi();

	@Query("select avg(c.sended.size)from Chorbi c")
	Double avgSendedChirpChorbi();

	@Query("select c from Chorbi c where c.received.size=(select max(c2.received.size) from Chorbi c2)")
	Collection<Chorbi> moreChirpReceivedChorbies();

	@Query("select c from Chorbi c where c.sended.size=(select max(c2.sended.size) from Chorbi c2)")
	Collection<Chorbi> moreChirpSentChorbies();
}

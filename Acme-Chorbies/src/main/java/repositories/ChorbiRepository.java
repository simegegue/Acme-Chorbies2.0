
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;

@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

	@Query("select c from Chorbi c where c.userAccount.id=?1")
	Chorbi findByUserAccountId(int id);

	@Query("select c from Chorbi c where c.genre.id = ?1")
	Collection<Chorbi> findChorbiesByGenre(int genreId);

	@Query("select c from Chorbi c where c.kindRelationship.id = ?1")
	Collection<Chorbi> findChorbiesByKindRelationship(int kindRelationshipId);

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

	@Query("select min(c.sent.size)from Chorbi c")
	Double minSentChirpChorbi();

	@Query("select max(c.sent.size)from Chorbi c")
	Double maxSentChirpChorbi();

	@Query("select avg(c.sent.size)from Chorbi c")
	Double avgSentChirpChorbi();

	@Query("select c from Chorbi c where c.received.size=(select max(c2.received.size) from Chorbi c2)")
	Collection<Chorbi> moreChirpReceivedChorbies();

	@Query("select c from Chorbi c where c.sent.size=(select max(c2.sent.size) from Chorbi c2)")
	Collection<Chorbi> moreChirpSentChorbies();

	//Aux---------------

	@Query("select c.coordinate.country from Chorbi c group by c.coordinate.country")
	Collection<String> auxCountry();

	@Query("select c.coordinate.city from Chorbi c group by c.coordinate.city")
	Collection<String> auxCity();

	@Query("select c from Chorbi c join c.userAccount us where us.username = '?1'")
	Chorbi findChorbiByUsername(String name);

	@Query("select c, datediff(current_date,c.birthDate)/365 from Chorbi c")
	List<Object[]> findByAge();

	@Query("select c from Chorbi c where c.name like %?1% or c.description like %?1%")
	Collection<Chorbi> findByKey(String keyword);

	@Query("select c from Chorbi c where c.coordinate.country = ?1")
	Collection<Chorbi> findByCoordinatesCountry(String keyword);

	@Query("select c from Chorbi c where c.coordinate.state = ?1")
	Collection<Chorbi> findByCoordinatesState(String keyword);

	@Query("select c from Chorbi c where c.coordinate.province = ?1")
	Collection<Chorbi> findByCoordinatesProvince(String keyword);

	@Query("select c from Chorbi c where c.coordinate.city = ?1")
	Collection<Chorbi> findByCoordinatesCity(String keyword);

	@Query("select c from Chorbi c where c.genre.value = ?1")
	Collection<Chorbi> findByGenre(String keyword);

	@Query("select c from Chorbi c where c.kindRelationship.value = ?1")
	Collection<Chorbi> findByKindRelationship(String keyword);

	//Dashboard

	@Query("select c from Chorbi c order by c.relationEvents.size DESC")
	Collection<Chorbi> chorbiesByEvents();

	@Query("select m,m.feeAmount from Chorbi m")
	List<Object[]> chorbiFeeAmount();

	@Query("select min(r.stars) from Chorbi c join c.likesReceived r")
	Double minStars();

	@Query("select max(r.stars) from Chorbi c join c.likesReceived r")
	Double maxStars();

	@Query("select avg(r.stars) from Chorbi c join c.likesReceived r")
	Double avgStars();

	@Query("select c, c.likesReceived from Chorbi c")
	List<Object[]> chorbiLikes();

	@Query("select c, c.stars from RelationLike c")
	List<Object[]> likesStars();

}

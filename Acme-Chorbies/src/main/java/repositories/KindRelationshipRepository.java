package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.KindRelationship;

@Repository
public interface KindRelationshipRepository extends JpaRepository<KindRelationship, Integer>{

	@Query("select k from KindRelationship k where k.value like '?1'")
	KindRelationship findKindRelationshipByValue(String value);
}

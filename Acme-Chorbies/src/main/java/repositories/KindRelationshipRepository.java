package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.KindRelationship;

@Repository
public interface KindRelationshipRepository extends JpaRepository<KindRelationship, Integer>{

}

package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.RelationEvent;

@Repository
public interface RelationEventRepository extends JpaRepository<RelationEvent, Integer>{

}

package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.RelationLike;

@Repository
public interface RelationLikeRepository extends JpaRepository<RelationLike, Integer>{

}

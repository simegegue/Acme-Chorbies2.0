package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;
import domain.RelationLike;

@Repository
public interface RelationLikeRepository extends JpaRepository<RelationLike, Integer>{
	@Query("select r.likeSender from RelationLike r where r.likeRecipient.id=?1")
	Collection<Chorbi>findByLikesSent(int id);
}

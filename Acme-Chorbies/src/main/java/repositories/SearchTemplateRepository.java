package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Genre;
import domain.KindRelationship;
import domain.SearchTemplate;

@Repository
public interface SearchTemplateRepository extends JpaRepository<SearchTemplate, Integer>{

	@Query("select s from SearchTemplate s where s.genre = ?1")
	Collection<SearchTemplate> findSearchTemplateByGenre(Genre genre);
	
	@Query("select s from SearchTemplate s where s.kindRelationship = ?1")
	Collection<SearchTemplate> findSearchTemplateByKindRelationship(KindRelationship kindRelationship);
	
}

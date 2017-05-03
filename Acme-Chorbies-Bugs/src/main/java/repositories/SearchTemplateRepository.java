
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;
import domain.SearchTemplate;

@Repository
public interface SearchTemplateRepository extends JpaRepository<SearchTemplate, Integer> {

	@Query("select s from SearchTemplate s where s.genre.id = ?1")
	Collection<SearchTemplate> findSearchTemplateByGenre(int genre);

	@Query("select s from SearchTemplate s where s.kindRelationship.id = ?1")
	Collection<SearchTemplate> findSearchTemplateByKindRelationship(int kindRelationship);

	@Query("select c.searchTemplate from Chorbi c where c.userAccount = ?1")
	SearchTemplate findByUserAccount(UserAccount userAccount);

}

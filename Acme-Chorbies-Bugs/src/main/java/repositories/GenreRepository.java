package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{
	
	@Query("select g from Genre g where g.value like '?1'")
	Genre findGenreByValue(String value);
	
	@Query("select g from Genre g where g.value='none'")
	Genre findDefault();
}

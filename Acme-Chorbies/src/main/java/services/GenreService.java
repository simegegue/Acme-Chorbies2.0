package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.GenreRepository;
import domain.Genre;

@Service
@Transactional
public class GenreService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private GenreRepository genreRepository;
	
	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public GenreService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
			
	public Genre create() {

		Genre result;
		result = new Genre();

		return result;
	}

	public Collection<Genre> findAll() {

		Collection<Genre> result;

		result = genreRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Genre findOne(int genreId) {

		Genre result;

		result = genreRepository.findOne(genreId);
		Assert.notNull(result);

		return result;
	}

	public Genre save(Genre genre) {

		Assert.notNull(genre);
		Genre result;
		result = genreRepository.save(genre);

		return result;
	}

	public void delete(Genre genre) {

		Assert.notNull(genre);
				
		Assert.isTrue(genre.getId() != 0);

		genreRepository.delete(genre);
	}
	
	// Other bussiness methods ------------------------------------------------

}

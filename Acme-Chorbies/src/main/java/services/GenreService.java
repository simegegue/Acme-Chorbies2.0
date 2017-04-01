package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.GenreRepository;
import domain.Chorbi;
import domain.Genre;
import forms.GenreForm;

@Service
@Transactional
public class GenreService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private GenreRepository genreRepository;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ChorbiService chorbiService;
	
	@Autowired
	private Validator validator;

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
		
		Genre aux = findGenreByValue("None");
		
		// Buscar todos los chorbis qeu tengan la relacion a eliminar
		
		Collection<Chorbi> chorbies = null;
		
		for(Chorbi c : chorbies){
			c.setGenre(aux);
			chorbiService.save(c);
		}	

		genreRepository.delete(genre);
	}
	
	// Other bussiness methods ------------------------------------------------
	
	public Genre findGenreByValue(String value){
		return genreRepository.findGenreByValue(value);
	}
	
	
	// Form methods ----------------------------------------------------------

		public GenreForm generateForm() {
			GenreForm result = new GenreForm();

			return result;
		}

		public Genre reconstruct(GenreForm genreForm, BindingResult binding) {
			Genre result;
			if (genreForm.getId() == 0)
				result = create();
			else {
				result = genreRepository.findOne(genreForm.getId());
			}
			result.setValue(genreForm.getValue());
			for(Genre g : genreRepository.findAll()){
				Assert.isTrue(!g.getValue().equals(genreForm.getValue()),"usedGenre");
			}
			validator.validate(result, binding);
			return result;
		}

		public GenreForm transform(Genre genre) {
			GenreForm result = generateForm();
			result.setId(genre.getId());
			result.setValue(genre.getValue());
			return result;
		}

}

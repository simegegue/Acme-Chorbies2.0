
package controllers.Administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.GenreService;
import domain.Genre;
import forms.GenreForm;

@Controller
@RequestMapping("/administrator/genre")
public class GenreController extends AbstractController {

	//Services-------------------------

	@Autowired
	private GenreService	genreService;


	//Constructor----------------------

	public GenreController() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Genre> genres;
		genres = genreService.findAll();

		result = new ModelAndView("genre/list");
		result.addObject("genres", genres);
		result.addObject("requestURI", "administrator/genre/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		GenreForm genreForm;

		genreForm = genreService.generateForm();
		result = createEditModelAndView(genreForm, null);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int genreId) {

		ModelAndView result;
		Genre genre;

		genre = genreService.findOne(genreId);
		GenreForm genreForm=genreService.transform(genre);
		Assert.notNull(genre);
		result = new ModelAndView("genre/edit");
		result.addObject("genreForm", genreForm);
		
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid GenreForm genreForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();

		if (binding.hasErrors())
			result = createEditModelAndView(genreForm, null);
		else
			try {
				Genre genre= genreService.reconstruct(genreForm, binding);
				genreService.save(genre);
				result = list();
			} catch (Throwable oops) {
				String msgCode = "banner.save.error";
				result = createEditModelAndView(genreForm, msgCode);
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(GenreForm genreForm, BindingResult binding) {

		ModelAndView result;

		Genre genre = genreService.reconstruct(genreForm, binding);
		if (binding.hasErrors())
			result = createEditModelAndView(genre,null);
		else
			try {
				genreService.delete(genre);
				result = list();
			} catch (Throwable oops) {
				result = createEditModelAndView(genre,null);
			}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(Genre genre, String message) {
		ModelAndView result;

		result = new ModelAndView("genre/edit");
		result.addObject("genre", genre);
		result.addObject("message", message);
		return result;

	}

	protected ModelAndView createEditModelAndView(GenreForm genreForm, String message) {
		ModelAndView result;

		result = new ModelAndView("genre/edit");
		result.addObject("genreForm", genreForm);
		result.addObject("message", message);

		return result;

	}

}

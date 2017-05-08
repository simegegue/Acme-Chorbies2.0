package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.GenreService;
import services.KindRelationshipService;
import domain.Chorbi;
import domain.Genre;
import domain.KindRelationship;
import forms.ChorbiForm;

@Controller
@RequestMapping("/chorbi")
public class RegisterChorbiController extends AbstractController{
	
	// Services ------------------------------------------------

	@Autowired
	private ChorbiService		chorbiService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private KindRelationshipService kindRelationshipService;
	
	// Constructor ------------------------------------------------
	
	public RegisterChorbiController(){
		super();
	}
	
	// Register ----------------------------------------------------
	
		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			ChorbiForm chorbiForm;
			
			Collection<Genre> genres = new ArrayList<Genre>();
			Collection<KindRelationship> kindRelationships = new ArrayList<KindRelationship>();
			
			for(Genre g: genreService.findAll()){
				if(!g.getValue().equals("none")){
					genres.add(g);
				}
			}
			for(KindRelationship g: kindRelationshipService.findAll()){
				if(!g.getValue().equals("none")){
					kindRelationships.add(g);
				}
			}

			chorbiForm = chorbiService.generate();
			
			result = new ModelAndView("chorbi/register");
			result.addObject("chorbiForm", chorbiForm);
			result.addObject("genres", genres);
			result.addObject("kindRelationships", kindRelationships);

			return result;
		}

		@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid ChorbiForm chorbiForm, BindingResult binding) {
			ModelAndView result;
			Chorbi chorbi;

			if (binding.hasErrors())
				result = createEditModelAndView(chorbiForm);
			else
				try {
					chorbi = chorbiService.reconstruct(chorbiForm, binding);
					chorbiService.save(chorbi);
					result = new ModelAndView("redirect:../security/login.do");
				} catch (Throwable oops) {
					String msgCode = "chorbi.register.error";
					if (oops.getMessage().equals("notEqualPassword")){
						msgCode = "chorbi.register.notEqualPassword";
					}
					else if(oops.getMessage().equals("not18Old")){
						msgCode = "chorbi.register.not18Old";
					}
					else if(oops.getMessage().equals("agreedNotAccepted")){
						msgCode = "chorbi.register.agreedNotAccepted";
					}else if (oops.getMessage().equals("badCreditCard")){
						msgCode = "chorbi.register.badCreditCard";
					}
					result = createEditModelAndView(chorbiForm, msgCode);
				}

			return result;

		}
		
		// Ancillary methods ---------------------------------------------------

				protected ModelAndView createEditModelAndView(ChorbiForm chorbiForm) {
					ModelAndView result;

					result = createEditModelAndView(chorbiForm, null);

					return result;

				}

				protected ModelAndView createEditModelAndView(ChorbiForm chorbiForm, String message) {
					ModelAndView result;
					
					Collection<Genre> genres = genreService.findAll();
					Collection<KindRelationship> kindRelationships = kindRelationshipService.findAll();

					result = new ModelAndView("chorbi/register");
					result.addObject("chorbiForm", chorbiForm);
					result.addObject("genres", genres);
					result.addObject("kindRelationships", kindRelationships);
					result.addObject("message", message);

					return result;
				}

}


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

import services.KindRelationshipService;
import domain.KindRelationship;
import forms.KindRelationshipForm;

@Controller
@RequestMapping("/administrator/kindRelationship")
public class KindRelationshipController extends AbstractController {

	//Services-------------------------

	@Autowired
	private KindRelationshipService	kindRelationshipService;


	//Constructor----------------------

	public KindRelationshipController() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<KindRelationship> kindRelationships;
		kindRelationships = kindRelationshipService.findAll();

		result = new ModelAndView("kindRelationship/list");
		result.addObject("kindRelationships", kindRelationships);
		result.addObject("requestURI", "administrator/kindRelationship/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		KindRelationshipForm kindRelationshipForm;

		kindRelationshipForm = kindRelationshipService.generateForm();
		result = createEditModelAndView(kindRelationshipForm, null);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int kindRelationshipId) {

		ModelAndView result;
		KindRelationship kindRelationship;

		kindRelationship = kindRelationshipService.findOne(kindRelationshipId);
		KindRelationshipForm kindRelationshipForm=kindRelationshipService.transform(kindRelationship);
		Assert.notNull(kindRelationship);
		result = new ModelAndView("kindRelationship/edit");
		result.addObject("kindRelationshipForm", kindRelationshipForm);
		
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid KindRelationshipForm kindRelationshipForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();

		if (binding.hasErrors())
			result = createEditModelAndView(kindRelationshipForm, null);
		else
			try {
				KindRelationship kindRelationship= kindRelationshipService.reconstruct(kindRelationshipForm, binding);
				kindRelationshipService.save(kindRelationship);
				result = list();
			} catch (Throwable oops) {
				String msgCode = "banner.save.error";
				if (oops.getMessage().equals("usedKindRelationship"))
					msgCode = "kindRelationship.register.usedKindRelationship";
				
				result = createEditModelAndView(kindRelationshipForm, msgCode);

			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(KindRelationshipForm kindRelationshipForm, BindingResult binding) {

		ModelAndView result;

		KindRelationship kindRelationship = kindRelationshipService.reconstruct(kindRelationshipForm, binding);
		if (binding.hasErrors())
			result = createEditModelAndView(kindRelationship,null);
		else
			try {
				kindRelationshipService.delete(kindRelationship);
				result = list();
			} catch (Throwable oops) {
				result = createEditModelAndView(kindRelationship,null);
			}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(KindRelationship kindRelationship, String message) {
		ModelAndView result;

		result = new ModelAndView("kindRelationship/edit");
		result.addObject("kindRelationship", kindRelationship);
		result.addObject("message", message);
		return result;

	}

	protected ModelAndView createEditModelAndView(KindRelationshipForm kindRelationshipForm, String message) {
		ModelAndView result;

		result = new ModelAndView("kindRelationship/edit");
		result.addObject("kindRelationshipForm", kindRelationshipForm);
		result.addObject("message", message);

		return result;

	}

}

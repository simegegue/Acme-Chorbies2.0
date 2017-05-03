
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import domain.Manager;
import forms.ManagerForm;

@Controller
@RequestMapping("/managerActor")
public class RegisterManagerController extends AbstractController{

	// Services ------------------------------------------------

	@Autowired
	private ManagerService	managerService;


	// Constructor ------------------------------------------------

	public RegisterManagerController() {
		super();
	}

	// Register ----------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ManagerForm managerForm;

		managerForm = managerService.generate();

		result = new ModelAndView("managerActor/register");
		result.addObject("managerForm", managerForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ManagerForm managerForm, BindingResult binding) {
		ModelAndView result;
		Manager manager;

		if (binding.hasErrors())
			result = createEditModelAndView(managerForm);
		else
			try {
				manager = managerService.reconstruct(managerForm, binding);
				managerService.save(manager);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				String msgCode = "manager.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "manager.register.notEqualPassword";
				} else if (oops.getMessage().equals("not18Old")) {
					msgCode = "manager.register.not18Old";
				} else if (oops.getMessage().equals("agreedNotAccepted")) {
					msgCode = "manager.register.agreedNotAccepted";
				}
				result = createEditModelAndView(managerForm, msgCode);
			}

		return result;

	}

	// Ancillary methods ---------------------------------------------------

	protected ModelAndView createEditModelAndView(ManagerForm managerForm) {
		ModelAndView result;

		result = createEditModelAndView(managerForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(ManagerForm managerForm, String message) {
		ModelAndView result;

		result = new ModelAndView("managerActor/register");
		result.addObject("managerForm", managerForm);
		result.addObject("message", message);

		return result;
	}

}

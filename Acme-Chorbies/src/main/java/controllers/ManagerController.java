
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
@RequestMapping("/manager")
public class ManagerController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private ManagerService	managerService;


	// Constructor -----------------------------------------------

	public ManagerController() {
		super();
	}

	// Edit profile ------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Manager manager = managerService.findByPrincipal();
		String tipe = "edit";

		ManagerForm managerForm = managerService.generateForm(manager);

		result = new ModelAndView("manager/edit");

		result.addObject("managerForm", managerForm);
		result.addObject("tipe", tipe);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ManagerForm managerForm, BindingResult binding) {
		ModelAndView result;
		Manager manager;

		if (binding.hasErrors())
			result = createEditModelAndView(managerForm);
		else
			try {
				manager = managerService.reconstructEditPersonalData(managerForm, binding);
				managerService.save2(manager);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "manager.register.error";

				if (oops.getMessage().equals("notEqualPassword"))
					msgCode = "manager.register.notEqualPassword";
				else if (oops.getMessage().equals("not18Old"))
					msgCode = "manager.register.not18Old";
				else if (oops.getMessage().equals("agreedNotAccepted"))
					msgCode = "manager.register.agreedNotAccepted";

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

		result = new ModelAndView("manager/edit");
		result.addObject("managerForm", managerForm);
		result.addObject("message", message);

		return result;
	}

}


package controllers.Administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FeeService;
import controllers.AbstractController;
import domain.Fee;
import forms.FeeForm;

@Controller
@RequestMapping("/administrator/fee")
public class FeeController extends AbstractController {

	//Services-------------------------

	@Autowired
	private FeeService	feeService;


	//Constructor----------------------

	public FeeController() {
		super();
	}

	// Edit ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Fee fee = feeService.find();

		FeeForm feeForm = feeService.generateForm(fee);

		result = new ModelAndView("fee/edit");
		result.addObject("feeForm", feeForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FeeForm feeForm, BindingResult binding) {
		ModelAndView result;
		Fee fee;

		if (binding.hasErrors()) {
			result = createEditModelAndView(feeForm);
		} else {
			try {
				fee = feeService.reconstruct(feeForm, binding);
				feeService.save(fee);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
				result = createEditModelAndView(feeForm, msgCode);
			}
		}

		return result;
	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(FeeForm feeForm) {
		ModelAndView result;

		result = createEditModelAndView(feeForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(FeeForm feeForm, String message) {
		ModelAndView result;

		result = new ModelAndView("fee/edit");
		result.addObject("feeForm", feeForm);
		result.addObject("message", message);

		return result;

	}

}

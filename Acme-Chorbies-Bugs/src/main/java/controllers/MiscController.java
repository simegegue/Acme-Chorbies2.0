
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/misc")
public class MiscController extends AbstractController {

	// LOPD ---------------------------------------------------------------		

	@RequestMapping("/lopd")
	public ModelAndView lopd() {
		ModelAndView result;

		result = new ModelAndView("misc/lopd");

		return result;
	}

}

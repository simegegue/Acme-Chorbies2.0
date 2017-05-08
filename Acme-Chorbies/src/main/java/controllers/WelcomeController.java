/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.ChorbiService;
import domain.Banner;
import domain.Chorbi;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private BannerService	bannerService;

	@Autowired
	private ChorbiService	chorbiService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "John Doe") final String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		Chorbi chorbi = null;

		try {
			chorbi = chorbiService.findByPrincipal();
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (chorbi != null && chorbi.getBanned() == true) {
			//result = new ModelAndView("redirect:/j_spring_security_logout");
			result = new ModelAndView("welcome/index");
			result.addObject("banned", true);

		} else {

			List<Banner> banners = (List<Banner>) bannerService.findAll();
			boolean empty = banners.isEmpty();

			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			moment = formatter.format(new Date());
			
			result = new ModelAndView("welcome/index");
			result.addObject("moment", moment);
			if(!empty){
				int n = (int) (Math.random() * banners.size());
				result.addObject("banner", banners.get(n));
			}
			
			result.addObject("emptyBanner", empty);
		}
		return result;
	}
}

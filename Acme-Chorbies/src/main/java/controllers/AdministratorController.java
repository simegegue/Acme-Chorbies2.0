/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ChorbiService	chorbiService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Dashboard -----------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;

		//C
		Collection<Integer> numberOfChorbiesByCountry = chorbiService.numberOfChorbiesByCountry();
		Collection<Integer> numberOfChorbiesByCity = chorbiService.numberOfChorbiesByCity();
		Collection<Double> minMaxAvgChorbiYear = chorbiService.minMaxAvgChorbiYear();
		Double ratioChorbiesNullCreditCard = chorbiService.ratioChorbiesNullCreditCard();
		Collection<Double> actFriLoveRatioRelationChorbi = chorbiService.actFriLoveRatioRelationChorbi();

		//B

		//A

		result = new ModelAndView("administrator/dashboard");

		//C
		result.addObject("numberOfChorbiesByCountry", numberOfChorbiesByCountry);
		result.addObject("numberOfChorbiesByCity", numberOfChorbiesByCity);
		result.addObject("minMaxAvgChorbiYear", minMaxAvgChorbiYear);
		result.addObject("ratioChorbiesNullCreditCard", ratioChorbiesNullCreditCard);
		result.addObject("actFriLoveRatioRelationChorbi", actFriLoveRatioRelationChorbi);

		//B

		//A

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

}

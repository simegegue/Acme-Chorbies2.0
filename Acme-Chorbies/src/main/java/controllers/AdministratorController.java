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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.ManagerService;
import domain.Chorbi;
import domain.Manager;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ChorbiService	chorbiService;

	@Autowired
	private ManagerService	managerService;


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
		Collection<String> auxCountry = chorbiService.auxCountry();
		Collection<String> auxCity = chorbiService.auxCity();
		//----------------------
		Collection<Manager> managersByEvents = managerService.managersByEvents();
		Collection<Manager> mapManager = managerService.mapManagerFee().keySet();
		Map<Manager, Double> mapM = managerService.mapManagerFee();
		Collection<Chorbi> chorbiesByEvents = chorbiService.chorbiesByEvents();
		Collection<Chorbi> mapChorbies = chorbiService.mapChorbiFee().keySet();
		Map<Chorbi, Double> mapC = chorbiService.mapChorbiFee();

		//B
		Collection<Chorbi> listChorbiesbyLikes = chorbiService.listChorbiesbyLikes();
		Collection<Double> minMaxAvgReceivedLikeChorbi = chorbiService.minMaxAvgReceivedLikeChorbi();

		//A
		Collection<Double> minMaxAvgReceivedChirpChorbi = chorbiService.minMaxAvgReceivedChirpChorbi();
		Collection<Double> minMaxAvgSentChirpChorbi = chorbiService.minMaxAvgSentChirpChorbi();
		Collection<Chorbi> moreChirpReceivedChorbies = chorbiService.moreChirpReceivedChorbies();
		Collection<Chorbi> moreChirpSentChorbies = chorbiService.moreChirpSentChorbies();

		result = new ModelAndView("administrator/dashboard");

		//C
		result.addObject("numberOfChorbiesByCountry", numberOfChorbiesByCountry);
		result.addObject("numberOfChorbiesByCity", numberOfChorbiesByCity);
		result.addObject("minMaxAvgChorbiYear", minMaxAvgChorbiYear);
		result.addObject("ratioChorbiesNullCreditCard", ratioChorbiesNullCreditCard);
		result.addObject("actFriLoveRatioRelationChorbi", actFriLoveRatioRelationChorbi);
		result.addObject("auxCountry", auxCountry);
		result.addObject("auxCity", auxCity);
		//--------------------------------------
		result.addObject("managersByEvents", managersByEvents);
		result.addObject("mapManager", mapManager);
		result.addObject("mapM", mapM);
		result.addObject("chorbiesByEvents", chorbiesByEvents);
		result.addObject("mapChorbies", mapChorbies);
		result.addObject("mapC", mapC);

		//B
		result.addObject("listChorbiesbyLikes", listChorbiesbyLikes);
		result.addObject("minMaxAvgReceivedLikeChorbi", minMaxAvgReceivedLikeChorbi);

		//A
		result.addObject("minMaxAvgReceivedChirpChorbi", minMaxAvgReceivedChirpChorbi);
		result.addObject("minMaxAvgSentChirpChorbi", minMaxAvgSentChirpChorbi);
		result.addObject("moreChirpReceivedChorbies", moreChirpReceivedChorbies);
		result.addObject("moreChirpSentChorbies", moreChirpSentChorbies);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

}

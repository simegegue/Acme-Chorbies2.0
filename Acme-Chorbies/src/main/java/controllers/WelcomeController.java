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

import javax.security.auth.login.LoginContext;
import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Banner;
import domain.Chorbi;

import security.Authority;
import security.LoginController;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import services.BannerService;
import services.ChorbiService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private ChorbiService chorbiService;
	
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
		
		try{
		chorbi = chorbiService.findByPrincipal();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		if(chorbi!=null && chorbi.getBanned()==true){
			result = new ModelAndView("redirect:/j_spring_security_logout");
			
		}else{
		
			List<Banner> banners = (List<Banner>) bannerService.findAll();
			int n = (int) (Math.random()*banners.size());
	
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			moment = formatter.format(new Date());
	
			result = new ModelAndView("welcome/index");
			result.addObject("moment", moment);
			result.addObject("banner", banners.get(n));
		}
		return result;
	}
}

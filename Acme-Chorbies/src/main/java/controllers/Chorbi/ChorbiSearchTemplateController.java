package controllers.Chorbi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.SearchTemplateService;

import controllers.AbstractController;
import domain.Chorbi;
import domain.SearchTemplate;

@Controller
@RequestMapping("/chorbi/searchTemplate")
public class ChorbiSearchTemplateController extends AbstractController{
	
	//Services-------------------------

		@Autowired
		private SearchTemplateService	searchTemplateService;
		
		@Autowired
		private ChorbiService	chorbiService;


		

		//Constructor----------------------

		public ChorbiSearchTemplateController() {
			super();
		}

		//Display--------------------------

		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView display() {

			ModelAndView result;
			SearchTemplate searchTemplate;
			
			searchTemplate = searchTemplateService.findByPrincipal();
			Date d=new Date(System.currentTimeMillis());
			Long aux=d.getTime()-searchTemplate.getLastTimeSearched().getTime();
			if(aux>=3600000){
				Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
				searchTemplate.setChorbies(chorbies);
			}
			result = new ModelAndView("searchTemplate/display");
			result.addObject("searchTemplate", searchTemplate);
			result.addObject("chorbies",searchTemplate.getChorbies());
			result.addObject("requestURI", "chorbi/searchTemplate/display.do");

			return result;
		}

		
/*
		//Edition--------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int finderId) {

			ModelAndView result;
			SearchTemplate searchTemplate;
			
			searchTemplate = searchTemplateService.findOne(finderId);
			SearchTemplateForm finderform=searchTemplateService.transform(searchTemplate);
			Assert.notNull(finderform);
			result = createEditModelAndView(finderform);

			return result;

		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid SearchTemplateForm searchTemplateForm, BindingResult binding) {

			ModelAndView result;
			SearchTemplate searchTemplate;
			if (binding.hasErrors()) {
				result = createEditModelAndView(searchTemplateForm);
			} else {
				try {
					searchTemplate=searchTemplateService.reconstruct(searchTemplateForm, binding);
					if(searchTemplateService.compareSearch(searchTemplate)==false){
						chorbiService.findBySearchTemplate(searchTemplate);
						searchTemplateService.save(searchTemplate);
					}
					result = display();
					result.addObject("chorbies",searchTemplate.getChorbies());
				} catch (Throwable oops) {
					result = createEditModelAndView(searchTemplateForm, "master.page.commit.error");
			}

			
			}
			return result;
		}
		
		

		//Ancillary Methods---------------------------

		protected ModelAndView createEditModelAndView(SearchTemplateForm searchTemplateForm) {

			ModelAndView result;

			result = createEditModelAndView(searchTemplateForm, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(SearchTemplateForm searchTemplateForm, String message) {
			ModelAndView result;

			result = new ModelAndView("searchTemplate/edit");
			result.addObject("searchTemplate", searchTemplateForm);

			result.addObject("message", message);

			return result;

		}
		*/

}

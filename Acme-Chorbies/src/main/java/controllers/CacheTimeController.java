package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.CacheTime;
import forms.CacheTimeForm;

import services.CacheTimeService;
@Controller
@RequestMapping("/admin/cacheTime")
public class CacheTimeController extends AbstractController {
	//Service
	@Autowired
	private CacheTimeService cacheTimeService;
	//Constructor
	public CacheTimeController(){
		super();
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Collection<CacheTime>cacheTimes = cacheTimeService.findAll();
		CacheTime cacheTime=new CacheTime();
		for(CacheTime c:cacheTimes){
			cacheTime=c;
			break;
		}
		CacheTimeForm cacheTimeForm = cacheTimeService.transform(cacheTime);
		result = new ModelAndView("cacheTime/edit");
		result.addObject("cacheTimeForm", cacheTimeForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CacheTimeForm cacheTimeForm, BindingResult binding) {
		ModelAndView result;
		CacheTime cacheTime;

		if (binding.hasErrors()) {
			result = new ModelAndView("cacheTime/edit");
			result.addObject("cacheTimeForm", cacheTimeForm);
			result.addObject("message", null);
		} else {
			try {
				cacheTime = cacheTimeService.reconstruct(cacheTimeForm, binding);
				cacheTimeService.save(cacheTime);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "cacheTime.register.error";
				result = new ModelAndView("cacheTime/edit");
				result.addObject("cacheTimeForm", cacheTimeForm);
				result.addObject("message", msgCode);
			}
		}

		return result;
	}
	

}

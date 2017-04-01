package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Chorbi;

import services.ChorbiService;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController extends AbstractController{
	
	@Autowired
	private ChorbiService chorbiService;
	
	public ChorbiController(){
		super();
	}
	
	//Browse
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView browse() {
		ModelAndView result;
		Collection<Chorbi> aux = new ArrayList<Chorbi>();
		Collection<Chorbi>chorbies=new ArrayList<Chorbi>() ;
		aux = chorbiService.findAll();

		for (Chorbi c : aux)
			if (c.getBanned() == false)
				chorbies.add(c);

		result = new ModelAndView("chorbi/browse");
		result.addObject("chorbies", chorbies);
		result.addObject("requestURI", "chorbi/browse.do");

		return result;
	}

}

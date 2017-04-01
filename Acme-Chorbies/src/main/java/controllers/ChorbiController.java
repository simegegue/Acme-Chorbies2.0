
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.ChorbiService;
import services.RelationLikeService;
import domain.Chorbi;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController extends AbstractController {

	@Autowired
	private ChorbiService		chorbiService;

	@Autowired
	private RelationLikeService	relationLikeService;


	public ChorbiController() {
		super();
	}

	//Browse
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView browse() {
		ModelAndView result;
		Collection<Chorbi> aux = new ArrayList<Chorbi>();
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		aux = chorbiService.findAll();
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		if (userAccount.getAuthorities().contains(au))
			chorbies.addAll(aux);
		else
			for (Chorbi c : aux)
				if (c.getBanned() == false)
					chorbies.add(c);

		result = new ModelAndView("chorbi/browse");
		result.addObject("chorbies", chorbies);
		result.addObject("requestURI", "chorbi/browse.do");

		return result;
	}

	//Browse
	@RequestMapping(value = "/chorbieswholikethem", method = RequestMethod.GET)
	public ModelAndView chorbieswholikethem(@RequestParam int chorbiId) {
		ModelAndView result;
		Collection<Chorbi> aux = new ArrayList<Chorbi>();
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		aux = relationLikeService.findByLikesSent(chorbiId);

		for (Chorbi c : aux)
			if (c.getBanned() == false)
				chorbies.add(c);

		result = new ModelAndView("chorbi/browse");
		result.addObject("chorbies", chorbies);
		result.addObject("requestURI", "chorbi/browse.do");

		return result;
	}

	//BanUnban -----

	@RequestMapping(value = "/banUnban", method = RequestMethod.GET)
	public ModelAndView banUnban(@RequestParam int chorbiId) {
		ModelAndView result;
		Chorbi c = chorbiService.findOne(chorbiId);
		try {
			chorbiService.banUnban(c);
			result = browse();
		} catch (Throwable oops) {
			result = browse();
			result.addObject("message", "master-page.commit.error");
		}

		return result;
	}

}

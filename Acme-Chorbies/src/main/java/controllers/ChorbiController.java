
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.ChorbiService;
import services.GenreService;
import services.KindRelationshipService;
import services.RelationLikeService;
import domain.Chorbi;
import domain.Genre;
import domain.KindRelationship;
import domain.RelationLike;
import forms.ChorbiForm;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private ChorbiService			chorbiService;

	@Autowired
	private RelationLikeService		relationLikeService;

	@Autowired
	private GenreService			genreService;

	@Autowired
	private KindRelationshipService	kindRelationshipService;


	// Constructor -----------------------------------------------

	public ChorbiController() {
		super();
	}

	//Display----------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Chorbi chorbi;
		Map<Integer, String> comments = new HashMap<Integer, String>();

		chorbi = chorbiService.findByPrincipal();

		for (RelationLike r : chorbi.getLikesReceived()) {
			if (r.getComment() == null) {
				comments.put(r.getId(), "");
			} else {
				comments.put(r.getId(), relationLikeService.encrypt(r.getComment()));
			}
		}

		result = new ModelAndView("chorbi/display");
		result.addObject("chorbi", chorbi);
		result.addObject("description", chorbi.getDescription());
		result.addObject("likesReceived", chorbi.getLikesReceived());
		result.addObject("comments", comments);
		result.addObject("requestURI", "chorbi/displayById.do");

		return result;
	}

	@RequestMapping(value = "/displayById", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int chorbiId) {
		ModelAndView result;
		Chorbi chorbi;
		Map<Integer, String> comments = new HashMap<Integer, String>();

		chorbi = chorbiService.findOne(chorbiId);

		for (RelationLike r : chorbi.getLikesReceived()) {
			if (r.getComment() == null) {
				comments.put(r.getId(), "");
			} else {
				comments.put(r.getId(), relationLikeService.encrypt(r.getComment()));
			}
		}

		result = new ModelAndView("chorbi/display");
		result.addObject("chorbi", chorbi);
		result.addObject("description", chorbiService.encrypt(chorbi.getDescription()));
		result.addObject("likesReceived", chorbi.getLikesReceived());
		result.addObject("comments", comments);
		result.addObject("requestURI", "chorbi/displayById.do");

		return result;
	}
	//Browse ----------------------------------------------------

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
				if (c.getBanned() == false && !c.equals(chorbiService.findByPrincipal())) {
					chorbies.add(c);
				}
		result = new ModelAndView("chorbi/browse");
		result.addObject("chorbies", chorbies);
		result.addObject("requestURI", "chorbi/browse.do");

		return result;
	}

	@RequestMapping(value = "/browseLike", method = RequestMethod.GET)
	public ModelAndView browseLike() {
		ModelAndView result;
		Collection<Chorbi> aux = new ArrayList<Chorbi>();
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		Chorbi chorbi = chorbiService.findByPrincipal();
		int chorbiId = chorbi.getId();
		aux = relationLikeService.findByLikesSent(chorbiId);
		Boolean b = chorbiService.principalCheckCreditCard();

		for (Chorbi c : aux)
			if (c.getBanned() == false && !c.equals(chorbiService.findByPrincipal())) {
				chorbies.add(c);
			}
		result = new ModelAndView("chorbi/browseLike");
		result.addObject("chorbies", chorbies);
		result.addObject("requestURI", "chorbi/browseLike.do");
		result.addObject("validatorCreditCard", b);

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
			if (c.getBanned() == false && !c.equals(chorbiService.findByPrincipal())) {
				chorbies.add(c);
			}
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

	// Edit profile ------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Chorbi chorbi = chorbiService.findByPrincipal();
		String tipe = "edit";

		Collection<Genre> genres = genreService.findAll();
		Collection<KindRelationship> kindRelationships = kindRelationshipService.findAll();
		ChorbiForm chorbiForm = chorbiService.generateForm(chorbi);

		result = new ModelAndView("chorbi/edit");

		result.addObject("chorbiForm", chorbiForm);
		result.addObject("genres", genres);
		result.addObject("kindRelationships", kindRelationships);
		result.addObject("tipe", tipe);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ChorbiForm chorbiForm, BindingResult binding) {
		ModelAndView result;
		Chorbi chorbi;

		if (binding.hasErrors())
			result = createEditModelAndView(chorbiForm);
		else
			try {
				chorbi = chorbiService.reconstructEditPersonalData(chorbiForm, binding);
				chorbiService.save2(chorbi);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "chorbi.register.error";

				if (oops.getMessage().equals("notEqualPassword"))
					msgCode = "chorbi.register.notEqualPassword";
				else if (oops.getMessage().equals("not18Old"))
					msgCode = "chorbi.register.not18Old";
				else if (oops.getMessage().equals("agreedNotAccepted"))
					msgCode = "chorbi.register.agreedNotAccepted";

				result = createEditModelAndView(chorbiForm, msgCode);
			}

		return result;
	}

	// Ancillary methods ---------------------------------------------------

	protected ModelAndView createEditModelAndView(ChorbiForm chorbiForm) {
		ModelAndView result;

		result = createEditModelAndView(chorbiForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(ChorbiForm chorbiForm, String message) {
		ModelAndView result;

		Collection<Genre> genres = genreService.findAll();
		Collection<KindRelationship> kindRelationships = kindRelationshipService.findAll();

		result = new ModelAndView("chorbi/edit");
		result.addObject("chorbiForm", chorbiForm);
		result.addObject("genres", genres);
		result.addObject("kindRelationships", kindRelationships);
		result.addObject("message", message);

		return result;
	}

}

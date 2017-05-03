
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.RelationLikeService;
import domain.Chorbi;
import domain.RelationLike;
import forms.RelationLikeForm;

@Controller
@RequestMapping("/chorbi/relationLike")
public class RelationLikeController extends AbstractController {

	@Autowired
	private RelationLikeService	relationLikeService;

	@Autowired
	private ChorbiService		chorbiService;


	//Constructor-----------------------------------
	public RelationLikeController() {
		super();
	}

	// Like ---------------------------------------------
	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int chorbiId) {

		ModelAndView result;
		RelationLikeForm relationLikeForm;

		relationLikeForm = relationLikeService.generateForm();
		relationLikeForm.setchorbiId(chorbiId);
		result = createEditModelAndView(relationLikeForm, null);
		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid RelationLikeForm relationLikeForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();
		RelationLike relationLike;

		if (binding.hasErrors()) {
			result = createEditModelAndView(relationLikeForm, null);
		} else {
			try {
				relationLike = relationLikeService.reconstruct(relationLikeForm, binding);
				relationLike = relationLikeService.save(relationLike);
				Chorbi c = relationLike.getLikeRecipient();
				chorbiService.updateAvgStars(c);
				int id = relationLike.getLikeRecipient().getId();

				result = new ModelAndView("redirect:../displayById.do?chorbiId=" + id);

			} catch (Throwable oops) {
				String msgCode;
				msgCode = "relationLike.err";
				result = createEditModelAndView(relationLikeForm, msgCode);
			}
		}
		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int chorbiId) {
		ModelAndView result = new ModelAndView();
		Chorbi likeRecipient = chorbiService.findOne(chorbiId);
		Chorbi likeSender = chorbiService.findByPrincipal();

		relationLikeService.unLike(likeSender, likeRecipient);

		result = new ModelAndView("redirect:../displayById.do?chorbiId=" + chorbiId);
		return result;

	}
	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(RelationLikeForm relationLikeForm, String message) {
		ModelAndView result;
		Collection<Integer> stars = new ArrayList<Integer>();
		stars.add(0);
		stars.add(1);
		stars.add(2);
		stars.add(3);

		result = new ModelAndView("relationLike/edit");
		result.addObject("relationLikeForm", relationLikeForm);
		result.addObject("stars", stars);
		result.addObject("message", message);

		return result;

	}

}

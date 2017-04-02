package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Chorbi;
import domain.RelationLike;
import services.ChorbiService;
import services.RelationLikeService;

@Controller
@RequestMapping("/chorbi/relationLike")
public class RelationLikeController extends AbstractController{
	@Autowired
	private RelationLikeService relationLikeService;
	
	@Autowired
	private ChorbiService chorbiService;
	
	//Constructor-----------------------------------
	public RelationLikeController(){
		super();
	}
	
	// Like ---------------------------------------------
			@RequestMapping(value="/create", method=RequestMethod.GET)
			public ModelAndView create(@RequestParam int chorbiId) {
				ModelAndView result;
				Chorbi likeRecipient;
				likeRecipient= chorbiService.findOne(chorbiId);
				Chorbi likeSender;
				likeSender = chorbiService.findByPrincipal();
				RelationLike rl;
				rl=relationLikeService.giveLike(likeSender, likeRecipient);
				relationLikeService.save(rl);
				result = new ModelAndView("redirect:../displayById.do?chorbiId=" +chorbiId);
				return result;
				}
				
}

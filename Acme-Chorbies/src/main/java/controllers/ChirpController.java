
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

import services.ChirpService;
import services.ChorbiService;
import services.EventService;
import domain.Chirp;
import domain.Chorbi;
import domain.Event;
import domain.Mailer;
import forms.ChirpForm;

@Controller
@RequestMapping("/chirp")
public class ChirpController extends AbstractController {

	// Services --------------------------------------------

	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private ChorbiService	chorbiService;
	
	@Autowired
	private EventService eventService;


	// Constructor -----------------------------------------

	public ChirpController() {
		super();
	}

	// Listing ---------------------------------------------

	@RequestMapping(value = "/received", method = RequestMethod.GET)
	public ModelAndView listRecieved() {
		ModelAndView result;

		Collection<Chirp> chirps = chirpService.chirpReceivedByActorId();
		Map<Integer,Mailer> senders = new HashMap<Integer,Mailer>();

		
		for(Chirp c: chirps){
			senders.put(c.getId(), chirpService.findSenderByChirpId(c.getId()));
		}

		result = new ModelAndView("chirp/received");
		result.addObject("chirp", chirps);
		result.addObject("senders", senders);

		return result;
	}

	@RequestMapping(value = "/sent", method = RequestMethod.GET)
	public ModelAndView listSent() {
		ModelAndView result;

		Collection<Chirp> chirps = chirpService.chirpSentByActorId();

		result = new ModelAndView("chirp/sent");
		result.addObject("chirp", chirps);

		return result;
	}

	// View ------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int chirpId) {

		ModelAndView result;
		Chirp chirp = chirpService.findOne(chirpId);

		result = new ModelAndView("chirp/view");
		result.addObject("chirpDisplay", chirp);

		return result;

	}

	// Edition ---------------------------------------------

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView write() {
		ModelAndView result;

		ChirpForm chirpForm = chirpService.generate();
		Collection<Chorbi> chorbies = chorbiService.findAll();
		
		chorbies.remove(chorbiService.findByPrincipal());


		result = new ModelAndView("chirp/write");
		result.addObject("chirpForm", chirpForm);
		result.addObject("chorbies", chorbies);

		return result;
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST, params = "save")
	public ModelAndView send(@Valid ChirpForm chirpForm, BindingResult binding) {

		ModelAndView result;
		Chirp chirp;

		if (binding.hasErrors()) {
			result = createEditModelAndView(chirpForm);
		} else {
			try {
				chirp = chirpService.reconstruct(chirpForm, binding);
				chirp.setSubject(chirpService.encrypt(chirp.getSubject()));
				chirp.setText(chirpService.encrypt(chirp.getText()));
				chirpService.save(chirp);
				result = new ModelAndView("redirect:/chirp/sent.do");
			} catch (Throwable oops) {
				String msgCode = "chirp.error";
				if (oops.getMessage().equals("badAttachment")){
					msgCode = "chirp.attachment";
				}
				result = createEditModelAndView(chirpForm, msgCode);
			}
		}

		return result;

	}

	// Reply ---------------------------------------------------

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam int chirpId) {

		ModelAndView result;
		ChirpForm chirpForm = chirpService.generate();

		Collection<Chorbi> chorbies = chirpService.reply(chirpId);

		result = new ModelAndView("chirp/reply");
		result.addObject("requestURI", "chirp/reply.do");
		result.addObject("chirpForm", chirpForm);
		result.addObject("chorbies", chorbies);

		return result;

	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST, params = "save")
	public ModelAndView sendReply(@Valid ChirpForm chirpForm, BindingResult binding) {

		ModelAndView result;
		Chirp chirp;

		if (binding.hasErrors()) {
			Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
			chorbies.add(chirpForm.getRecipient());

			result = new ModelAndView("chirp/reply");
			result.addObject("requestURI", "chirp/reply.do");
			result.addObject("chirpForm", chirpForm);
			result.addObject("chorbies", chorbies);
		} else {
			try {
				chirp = chirpService.reconstruct(chirpForm, binding);
				chirpService.save(chirp);
				result = new ModelAndView("redirect:/chirp/sent.do");
			} catch (Throwable oops) {
				String msgCode = "chirp.error";
				
				Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
				chorbies.add(chirpForm.getRecipient());

				result = new ModelAndView("chirp/reply");
				result.addObject("requestURI", "chirp/reply.do");
				result.addObject("chirpForm", chirpForm);
				result.addObject("chorbies", chorbies);
				result.addObject("message", msgCode);
			}
		}

		return result;

	}

	// Forward ---------------------------------------------------

	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public ModelAndView forward(@RequestParam int chirpId) {

		ModelAndView result;
		ChirpForm chirpForm = chirpService.forward(chirpId);

		Collection<Chorbi> chorbies = chorbiService.findAll();
		chorbies.remove(chorbiService.findByPrincipal());

		result = new ModelAndView("chirp/forward");
		result.addObject("requestURI", "chirp/forward.do");
		result.addObject("chirpForm", chirpForm);
		result.addObject("chorbies", chorbies);

		return result;

	}

	@RequestMapping(value = "/forward", method = RequestMethod.POST, params = "save")
	public ModelAndView sendForward(@Valid ChirpForm chirpForm, BindingResult binding) {

		ModelAndView result;
		Chirp chirp;

		if (binding.hasErrors()) {
			Collection<Chorbi> chorbies = chorbiService.findAll();
			chorbies.remove(chorbiService.findByPrincipal());

			result = new ModelAndView("chirp/forward");
			result.addObject("requestURI", "chirp/forward.do");
			result.addObject("chirpForm", chirpForm);
			result.addObject("chorbies", chorbies);
			
		} else {
			try {
				chirp = chirpService.reconstruct(chirpForm, binding);
				chirpService.save(chirp);
				result = new ModelAndView("redirect:/chirp/sent.do");
			} catch (Throwable oops) {
				String msgCode = "chirp.error";
				
				Collection<Chorbi> chorbies = chorbiService.findAll();
				chorbies.remove(chorbiService.findByPrincipal());

				result = new ModelAndView("chirp/forward");
				result.addObject("requestURI", "chirp/forward.do");
				result.addObject("chirpForm", chirpForm);
				result.addObject("chorbies", chorbies);
				result.addObject("mesage", msgCode);
				
			}
		}

		return result;

	}

	// Delete ---------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam int chirpId) {

		ModelAndView result;
		Chirp chirp = chirpService.findOne(chirpId);

		try {
			chirpService.deleteChirp(chirp);
			result = new ModelAndView("redirect:/chirp/sent.do");
		} catch (Throwable oops) {
			String msgCode = "chirp.error.delete";
			result = createEditModelAndViewDelete(msgCode);
		}

		return result;

	}
	
	// Broadcast --------------------------------------------
	
	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	public ModelAndView createBroadcast(@RequestParam int eventId){
		ModelAndView result;
		ChirpForm chirpForm = chirpService.generate(eventId);
				
		result = new ModelAndView("chirp/broadcast");
		result.addObject("chirpForm", chirpForm);
				
		return result;
	}
			
	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "save")
	public ModelAndView saveBroadcast(@Valid ChirpForm chirpForm, BindingResult binding){
		ModelAndView result;
		Event event = eventService.findOne(chirpForm.getEventId());
		if (binding.hasErrors()) {
			result = createEditModelAndViewBroadcast(chirpForm);
		} else {
			try{
				chirpService.chirpToChorbies(event, chirpForm, binding);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch(Throwable oops){
				String message = "chirp.error";
				result = createEditModelAndView(chirpForm, message);
			}
		}
		return result;		
	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(ChirpForm chirpForm) {
		ModelAndView result;

		result = createEditModelAndView(chirpForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(ChirpForm chirpForm, String message) {
		ModelAndView result;
		Collection<Chorbi> chorbies = chorbiService.findAll();
		chorbies.remove(chorbiService.findByPrincipal());

		result = new ModelAndView("chirp/write");
		result.addObject("chirpForm", chirpForm);
		result.addObject("chorbies", chorbies);
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndViewReply(ChirpForm chirpForm) {
		ModelAndView result;

		result = createEditModelAndView(chirpForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndViewReply(ChirpForm chirpForm, String message) {
		ModelAndView result;
		Collection<Chorbi> chorbies = chorbiService.findAll();

		result = new ModelAndView("chirp/reply");
		result.addObject("chirpForm", chirpForm);
		result.addObject("chorbies", chorbies);
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndViewForward(ChirpForm chirpForm) {
		ModelAndView result;

		result = createEditModelAndView(chirpForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndViewForward(ChirpForm chirpForm, String message) {
		ModelAndView result;
		Collection<Chorbi> chorbies = chorbiService.findAll();

		result = new ModelAndView("chirp/forward");
		result.addObject("chirpForm", chirpForm);
		result.addObject("chorbies", chorbies);
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndViewDelete() {
		ModelAndView result;

		result = createEditModelAndView(null);

		return result;

	}

	protected ModelAndView createEditModelAndViewDelete(String message) {
		ModelAndView result;

		result = new ModelAndView("chirp/sent");

		return result;

	}
	
	protected ModelAndView createEditModelAndViewBroadcast(ChirpForm chirpForm) {
		ModelAndView result;

		result = createEditModelAndViewBroadcast(chirpForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndViewBroadcast(ChirpForm chirpForm, String message) {
		ModelAndView result;

		result = new ModelAndView("chirp/broadcast");
		result.addObject("chirpForm", chirpForm);
		result.addObject("message", message);

		return result;

	}
}

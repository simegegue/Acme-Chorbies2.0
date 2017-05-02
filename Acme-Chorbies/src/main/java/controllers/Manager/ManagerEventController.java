
package controllers.Manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChirpService;
import services.EventService;
import services.FeeService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Chorbi;
import domain.Event;
import domain.Manager;
import domain.RelationEvent;
import forms.EventForm;

@Controller
@RequestMapping("/managerActor/event")
public class ManagerEventController extends AbstractController {

	//Services-------------------------

	@Autowired
	private EventService	eventService;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private FeeService		feeService;


	//Constructor----------------------

	public ManagerEventController() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Event> events;
		events = eventService.findByPrincipal();
		Map<Event, Integer> map = eventService.mapSeats();

		result = new ModelAndView("event/list");
		result.addObject("events", events);
		result.addObject("seats", map);
		result.addObject("requestURI", "managerActor/event/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		EventForm eventForm;
		Boolean b = managerService.principalCheckCreditCard();

		eventForm = eventService.generateForm();
		result = new ModelAndView("event/create");
		result.addObject("eventForm", eventForm);
		result.addObject("validatorCreditCard", b);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int eventId) {

		ModelAndView result;
		Event event;
		Boolean b = managerService.principalCheckCreditCard();

		event = eventService.findOne(eventId);
		EventForm eventForm = eventService.transform(event);
		Assert.notNull(event);
		result = new ModelAndView("event/edit");
		result.addObject("eventForm", eventForm);
		result.addObject("validatorCreditCard", b);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid EventForm eventForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();
		Event event;
		
		if (binding.hasErrors())
			if(eventForm.getId()!=0){
				result = createEditModelAndView(eventForm, null);
			}else{
				result = createEditModelAndView2(eventForm, null);
			}
		else
			try {
				event = eventService.reconstruct(eventForm, binding);
				if (event.getId() == 0) {
					Manager m = managerService.findByPrincipal();
					Double f = m.getFeeAmount() + feeService.find().getManagerValue();
					m.setFeeAmount(f);
					Event event2 = eventService.save(event);
					chirpService.chirpUpdateEvent(event2);
					result = list();
				} else {
					Event event2 = eventService.save(event);
					chirpService.chirpUpdateEvent(event2);
					result = list();
				}
			} catch (Throwable oops) {
				String msgCode = "event.save.error";
				if (oops.getMessage().equals("nullCreditCard"))
					msgCode = "event.nullCreditCard";
				else if (oops.getMessage().equals("badCreditCard"))
					msgCode = "event.badCreditCard";
				
				if(eventForm.getId()!=0){
					result = createEditModelAndView(eventForm, msgCode);
				}else{
					result = createEditModelAndView2(eventForm, msgCode);
				}
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(EventForm eventForm, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = createEditModelAndView(eventForm, null);
		else
			try {
				Event event = eventService.reconstruct(eventForm, binding);
				String title = event.getTitle();
				Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
				for (RelationEvent re : event.getRelationEvents()) {
					chorbies.add(re.getChorbi());
				}
				eventService.delete(event);
				chirpService.chirpDeleteEvent(title, chorbies);
				result = list();
			} catch (Throwable oops) {
				result = createEditModelAndView(eventForm, null);
			}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(Event event, String message) {
		ModelAndView result;

		result = new ModelAndView("event/edit");
		result.addObject("event", event);
		result.addObject("message", message);
		return result;

	}

	protected ModelAndView createEditModelAndView(EventForm event, String message) {
		ModelAndView result;

		result = new ModelAndView("event/edit");
		result.addObject("event", event);

		result.addObject("message", message);

		return result;

	}
	
	protected ModelAndView createEditModelAndView2(EventForm event, String message) {
		ModelAndView result;

		result = new ModelAndView("event/create");
		result.addObject("event", event);
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView(Event event) {
		ModelAndView result;

		result = createEditModelAndView(event, null);

		return result;

	}

}

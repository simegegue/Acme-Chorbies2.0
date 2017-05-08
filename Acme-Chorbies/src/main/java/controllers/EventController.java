
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
import services.EventService;
import services.FeeService;
import services.ManagerService;
import services.RelationEventService;
import domain.Event;
import domain.Manager;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private EventService			eventService;

	@Autowired
	private RelationEventService	relationEventService;

	@Autowired
	private FeeService				feeService;
	
	@Autowired
	private ManagerService			managerService;
	
	@Autowired
	private ChorbiService			chorbiService;


	// Constructors -----------------------------------------------------------

	public EventController() {
		super();
	}

	// Browse ---------------------------------------------------------------		

	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView browse() {
		ModelAndView result;

		Collection<Event> eventsOneMonth = eventService.eventsInLessOneMonth();
		Collection<Event> pastEvents = eventService.pastEvents();
		Map<Event, Integer> map = eventService.mapSeats();
		Collection<Event> events = eventService.findAll();

		result = new ModelAndView("event/browse");
		result.addObject("events", events);
		result.addObject("seats", map);
		result.addObject("pastEvents", pastEvents);
		result.addObject("eventsOneMonth", eventsOneMonth);
		result.addObject("requestURI", "event/browse.do");

		return result;
	}

	@RequestMapping(value = "/browseAvailable", method = RequestMethod.GET)
	public ModelAndView browseAvailable() {
		ModelAndView result;
		Collection<Event> events = new ArrayList<Event>();
		events = eventService.eventsInLessOneMonth();
		Map<Event, Integer> map = eventService.mapSeats();

		result = new ModelAndView("event/browseAvailable");
		result.addObject("events", events);
		result.addObject("seats", map);
		result.addObject("requestURI", "event/browseAvailable.do");

		return result;
	}

	// Browse registered -------------------------------------

	@RequestMapping(value = "/browseRegistered", method = RequestMethod.GET)
	public ModelAndView browseRegistered() {
		ModelAndView result;

		Collection<Event> events = eventService.findByChorbiRegister();
		Map<Event, Integer> map = eventService.mapSeats();

		result = new ModelAndView("event/browseRegistered");
		result.addObject("events", events);
		result.addObject("seats", map);
		result.addObject("requestURI", "event/browseRegistered.do");

		return result;
	}

	// Display ----------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int eventId) {
		ModelAndView result;
		Event event;
		int register = 0;
		Collection<Event> pastEvents = eventService.pastEvents();

		event = eventService.findOne(eventId);
		boolean past = pastEvents.contains(event);
		boolean full = eventService.hasSeats(event);
		boolean creator = false;
		boolean banned = chorbiService.findByPrincipal().getBanned();
			
		try {
			UserAccount userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("CHORBI");
			Authority au2 = new Authority();
			au2.setAuthority("MANAGER");

			if (userAccount.getAuthorities().contains(au)) {
				if (relationEventService.chorbiRegister(event)) {
					register = 1;
				}
			}else{
				if(userAccount.getAuthorities().contains(au2)) {
					Manager manager = managerService.findByPrincipal(); 
					creator = event.getManager().equals(manager);
				}
			}

		} catch (Throwable oops) {
			String message = "is anonymous";
		}
		result = new ModelAndView("event/display");
		result.addObject("event", event);
		result.addObject("requestURI", "event/display.do");
		result.addObject("register", register);
		result.addObject("past", past);
		result.addObject("full", full);
		result.addObject("creator", creator);
		result.addObject("banned", banned);

		return result;
	}

	// Register -------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam int eventId) {
		ModelAndView result;
		Event event = eventService.findOne(eventId);

		try {
			int register;
			if (eventService.availableSeats(event) == 0) {
				register = 0;
			} else {
				relationEventService.register(event);
				feeService.addFeeChorbi();
				register = 1;
			}

			Collection<Event> pastEvents = eventService.pastEvents();

			boolean past = pastEvents.contains(event);
			boolean full = eventService.hasSeats(event);

			result = new ModelAndView("event/display");
			result.addObject("event", event);
			result.addObject("register", register);
			result.addObject("requestURI", "event/display.do");
			result.addObject("past", past);
			result.addObject("full", full);
		} catch (Throwable oops) {
			String message = "event.no.register";
			result = registerModelAndView(event, message);
		}

		return result;
	}
	// Register -------------------------------------------

	@RequestMapping(value = "/unRegister", method = RequestMethod.GET)
	public ModelAndView unRegister(@RequestParam int eventId) {
		ModelAndView result;
		Event event = eventService.findOne(eventId);

		try {
			int register;

			if (eventService.availableSeats(event) == event.getSeatsOffered()) {
				register = 1;
			} else {
				relationEventService.unRegister(event);
				register = 0;
			}
			
			
			Collection<Event> pastEvents = eventService.pastEvents();

			boolean past = pastEvents.contains(event);
			boolean full = eventService.hasSeats(event);

			result = new ModelAndView("event/display");
			result.addObject("event", event);
			result.addObject("register", register);
			result.addObject("requestURI", "event/display.do");
			result.addObject("past", past);
			result.addObject("full", full);
		} catch (Throwable oops) {
			String message = "event.no.unregister";
			result = unRegisterModelAndView(event, message);
		}

		return result;
	}
	//Ancillary Methods---------------------------

	protected ModelAndView registerModelAndView(Event event, String message) {
		ModelAndView result;

		result = new ModelAndView("event/display");
		result.addObject("event", event);
		result.addObject("requestURI", "event/display.do");
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView unRegisterModelAndView(Event event, String message) {
		ModelAndView result;

		result = new ModelAndView("event/display");
		result.addObject("event", event);
		result.addObject("requestURI", "event/display.do");
		result.addObject("message", message);

		return result;

	}

}

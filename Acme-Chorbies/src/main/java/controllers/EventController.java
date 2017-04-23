
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EventService;
import domain.Event;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private EventService	eventService;


	// Constructors -----------------------------------------------------------

	public EventController() {
		super();
	}

	// Browse ---------------------------------------------------------------		

	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView browse() {
		ModelAndView result;
		Collection<Event> events = new ArrayList<Event>();
		events = eventService.findAll();

		result = new ModelAndView("event/browse");
		result.addObject("events", events);
		result.addObject("requestURI", "event/browse.do");

		return result;
	}

	// Display ----------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int eventId) {
		ModelAndView result;
		Event event;

		event = eventService.findOne(eventId);

		result = new ModelAndView("event/display");
		result.addObject("event", event);
		result.addObject("requestURI", "event/display.do");

		return result;
	}

}

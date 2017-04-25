
package controllers.Manager;

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

import services.CacheTimeService;
import services.ChorbiService;
import services.EventService;
import services.GenreService;
import services.KindRelationshipService;
import controllers.AbstractController;
import domain.Event;
import forms.EventForm;

@Controller
@RequestMapping("/manager/event")
public class ManagerEventController extends AbstractController {

	//Services-------------------------

	@Autowired
	private EventService			eventService;

	@Autowired
	private ChorbiService			chorbiService;

	@Autowired
	private CacheTimeService		cacheTimeService;

	@Autowired
	private GenreService			genreService;

	@Autowired
	private KindRelationshipService	kindRelationshipService;


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
		result.addObject("requestURI", "manager/event/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		EventForm eventForm;

		eventForm = eventService.generateForm();
		result = createEditModelAndView(eventForm, null);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int eventId) {

		ModelAndView result;
		Event event;

		event = eventService.findOne(eventId);
		//EventForm eventForm = eventService.transform(event);
		Assert.notNull(event);
		result = new ModelAndView("event/edit");
		result.addObject("event", event);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Event event, BindingResult binding) {

		ModelAndView result = new ModelAndView();

		if (binding.hasErrors())
			result = createEditModelAndView(event);
		else
			try {
				event = eventService.reconstruct(event, binding);
				eventService.save(event);
				result = list();
			} catch (Throwable oops) {
				String msgCode = "event.save.error";
				result = createEditModelAndView(event, msgCode);
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Event event, BindingResult binding) {

		ModelAndView result;

		event = eventService.reconstruct(event, binding);
		if (binding.hasErrors())
			result = createEditModelAndView(event);
		else
			try {
				eventService.delete(event);
				result = list();
			} catch (Throwable oops) {
				result = createEditModelAndView(event);
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

	protected ModelAndView createEditModelAndView(Event event) {
		ModelAndView result;

		result = createEditModelAndView(event, null);

		return result;

	}

}

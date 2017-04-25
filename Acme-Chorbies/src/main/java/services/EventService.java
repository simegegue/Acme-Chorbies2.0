
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.EventRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Event;
import domain.Manager;
import domain.RelationEvent;
import forms.EventForm;

@Service
@Transactional
public class EventService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private EventRepository	eventRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private Validator		validator;


	// Constructors -----------------------------------------------------------

	public EventService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Event create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Event result;
		result = new Event();

		Collection<RelationEvent> relationEvents = new ArrayList<RelationEvent>();
		Manager manager = managerService.findByPrincipal();

		result.setRelationEvents(relationEvents);
		result.setManager(manager);

		return result;
	}

	public Collection<Event> findAll() {

		Collection<Event> result;

		result = eventRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Event findOne(int eventId) {

		Event result;

		result = eventRepository.findOne(eventId);
		Assert.notNull(result);

		return result;
	}

	public Event save(Event event) {
		Event result;
		result = eventRepository.save(event);

		return result;
	}

	public void delete(Event event) {

		Assert.notNull(event);

		Assert.isTrue(event.getId() != 0);

		eventRepository.delete(event);
	}

	//Other Services -----

	public Map<Event, Integer> map() {
		Map<Event, Integer> map = new HashMap<Event, Integer>();
		List<Object[]> aux = eventRepository.eventsInLessOneMonth();
		for (Object[] o : aux) {
			map.put((Event) o[0], (Integer) o[1]);
		}
		return map;
	}
	public Collection<Event> eventsInLessOneMonth() {
		Collection<Event> result = new ArrayList<Event>();
		Map<Event, Integer> oneMonth = map();
		for (Event e : oneMonth.keySet()) {
			if ((oneMonth.get(e) == 0) && (hasSeats(e) == true)) {
				result.add(e);
			}
		}

		return result;
	}

	public Collection<Event> pastEvents() {
		Collection<Event> result = new ArrayList<Event>();
		Map<Event, Integer> pastEvents = map();
		for (Event e : pastEvents.keySet()) {
			if ((pastEvents.get(e) < 0)) {
				result.add(e);
			}
		}

		return result;
	}

	public Integer seats(Event event) {
		Integer result = event.getRelationEvents().size();

		return result;
	}

	public Integer availableSeats(Event event) {
		Integer result = event.getSeatsOffered() - seats(event);
		return result;
	}

	public Boolean hasSeats(Event event) {

		Boolean result = false;

		if (availableSeats(event) > 0) {
			result = true;
		}

		return result;

	}

	public Map<Event, Integer> mapSeats() {
		Map<Event, Integer> map = new HashMap<Event, Integer>();
		Collection<Event> aux = findAll();
		Integer seats;
		for (Event e : aux) {
			seats = availableSeats(e);
			map.put(e, seats);
		}
		return map;
	}

	// Form-------

	public EventForm generateForm() {
		EventForm result;

		result = new EventForm();
		return result;
	}

	public Event reconstruct(EventForm eventForm, BindingResult binding) {

		Manager manager = managerService.findByPrincipal();

		Event result = create();
		result.setId(eventForm.getId());
		result.setManager(manager);
		result.setDescription(eventForm.getDescription());
		result.setMoment(eventForm.getMoment());
		result.setPicture(eventForm.getPicture());
		result.setSeatsOffered(eventForm.getSeatsOffered());
		result.setTitle(eventForm.getTitle());
		validator.validate(result, binding);

		return result;
	}

	public Event reconstruct(Event event, BindingResult binding) {
		Event result;
		Collection<RelationEvent> relationEvents = new ArrayList<RelationEvent>();

		if (event.getId() == 0) {
			Manager manager = managerService.findByPrincipal();

			result = event;
			result.setManager(manager);
			result.setRelationEvents(relationEvents);
			result.setDescription(event.getDescription());
			result.setMoment(event.getMoment());
			result.setPicture(event.getPicture());
			result.setSeatsOffered(event.getSeatsOffered());
			result.setTitle(event.getTitle());
		} else {
			Event event2 = findOne(event.getId());
			result = eventRepository.findOne(event.getId());
			result.setRelationEvents(event2.getRelationEvents());
			result.setDescription(event.getDescription());
			result.setMoment(event.getMoment());
			result.setPicture(event.getPicture());
			result.setSeatsOffered(event.getSeatsOffered());
			result.setTitle(event.getTitle());

			validator.validate(result, binding);
		}

		return result;
	}

	public EventForm transform(Event event) {
		EventForm result = generateForm();
		result.setDescription(event.getDescription());
		result.setMoment(event.getMoment());
		result.setPicture(event.getPicture());
		result.setSeatsOffered(event.getSeatsOffered());
		result.setTitle(event.getTitle());
		return result;
	}

	public Collection<Event> findByPrincipal() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Event> result = new ArrayList<Event>();
		Manager manager = managerService.findByPrincipal();
		result = eventRepository.eventByManagerId(manager.getId());
		return result;
	}
}


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
import org.springframework.validation.Validator;

import repositories.EventRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Event;
import domain.Manager;
import domain.RelationEvent;

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

	public Boolean hasSeats(Event event) {

		Boolean result = false;

		Integer seats = seats(event);

		if (event.getSeatsOffered() - seats > 0) {
			result = true;
		}

		return result;

	}
}
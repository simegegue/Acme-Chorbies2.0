package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Event;
import forms.EventForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class EventServiceTest extends AbstractTest{
	
	// The SUT -----------------------------------------------
	
	@Autowired
	private EventService	eventService;
	
	// Tests -------------------------------------------------
	
	/*
	 * List events create for a manager 
	 * 
	 * */
	
	@Test
	public void driverEventsOfManager(){
		Object testingData[][] = {
			{"manager1", null},
			{"admin", IllegalArgumentException.class},
			{null, IllegalArgumentException.class}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateEventsOfManager((String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
	}
	
	private void templateEventsOfManager(String user, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(user); // nos autenticamos como un usuario
			Collection<Event> events = eventService.findByPrincipal(); //Obtenemos los eventos creados por un maanger.
			Assert.notNull(events);
			unauthenticate(); // nos desutenticamos.
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	/*
	 * Modifiying the events that he or she organises
	 * */
	@Test
	public void driverEditEvents(){
		Object testingData[][] = {
			{"manager1", 93, "prueba", null},
			{"chorbi1", 93, "prueba", IllegalArgumentException.class},
			{null, 93,"prueba", IllegalArgumentException.class}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateEditEvents((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
		}
	}
	
	protected void templateEditEvents(String user, int id, String title, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(user);
			Event event = eventService.findOne(id); // Obtenemos el evento al cual vamos a editar.
			event.setTitle(title); // Cmabiamos el titulo del evento
			Event event2 = eventService.reconstruct(event, null);
			Assert.isTrue(event2.getTitle().equals("prueba"));
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	/*
	 * Delete an event by a manager
	 * */
	@Test
	public void driverDeleteEvents(){
		Object testingData[][] = {
			{"manager1", 93,  null},
			{"chorbi1", 93, IllegalArgumentException.class},
			{null, 93, IllegalArgumentException.class}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateDeleteEvents((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateDeleteEvents(String user, int id, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(user);
			Event event = eventService.findOne(id); // Obtenemos el evento al cual vamos a editar.
			eventService.delete(event);
			Collection<Event> events = eventService.findAll();
			Assert.isTrue(!events.contains(event));
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	/*
	 * Create an event by a manager
	 * */
	@Test
	public void driverCreateEvents() throws ParseException{
		Object testingData[][] = {
			{"manager2", "Vamos al parque", "10/07/2018 15:00", "Todos al parque que hace bueno", "http://destinosa1.com/wp-content/uploads/2015/06/BOG_Costado_occidental_del_Parque_de_la_93.jpg", 20, 0,  null},
			{"chorbi1", "Vamos al parque", "10/07/2018 15:00", "Todos al parque que hace bueno", "http://destinosa1.com/wp-content/uploads/2015/06/BOG_Costado_occidental_del_Parque_de_la_93.jpg", 20, 0, IllegalArgumentException.class},
			{null, "Vamos al parque", "10/07/2018 15:00", "Todos al parque que hace bueno", "http://destinosa1.com/wp-content/uploads/2015/06/BOG_Costado_occidental_del_Parque_de_la_93.jpg", 20, 0, IllegalArgumentException.class}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateCreateEvents((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (int) testingData[i][5], (int) testingData[i][6], (Class<?>) testingData[i][7]);
		}
	}
	
	protected void templateCreateEvents(String user, String title, String moment, String description, String picture, int seats, int id, Class<?> expected) throws ParseException{
		Class<?> caught = null;	
		
		Date date;
		final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		date = fecha.parse(moment);
		
		try{
			authenticate(user);
			EventForm eventForm = eventService.generateForm(); // Creamos el formulario y lo rellenamos
			eventForm.setId(id);
			eventForm.setDescription(description);
			eventForm.setMoment(date);
			eventForm.setPicture(picture);
			eventForm.setTitle(title);
			eventForm.setSeatsOffered(seats);
			Event event = eventService.reconstruct(eventForm, null); // Reconstruimos el evento a partir del formulario
			eventService.save(event); // Guardamos el evento 
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
}

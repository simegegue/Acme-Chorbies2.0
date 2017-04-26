package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RelationEventRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chorbi;
import domain.Event;
import domain.RelationEvent;

@Service
@Transactional
public class RelationEventService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private RelationEventRepository 	relationEventRepository;
		
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ChorbiService chorbiService;
	
	// Constructors -----------------------------------------------------------
	
	public RelationEventService(){
		super();
	}
		
	// Simple CRUD methods ----------------------------------------------------
	
	public RelationEvent create(){
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		
		RelationEvent result = new RelationEvent();
		
		return result;
	}
	
	public RelationEvent findOne(int relationEventId){
		RelationEvent result = relationEventRepository.findOne(relationEventId);
		Assert.notNull(result);
		return result;
	}
	
	public Collection<RelationEvent> findAll(){
		Collection<RelationEvent> result = relationEventRepository.findAll();
		return result;
	}
	
	public RelationEvent save(RelationEvent relationEvent){
		Assert.notNull(relationEvent);
		RelationEvent result = relationEventRepository.save(relationEvent);
		return result;
	}
	
	public void delete(RelationEvent relationEvent){
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");
		
		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));
		Assert.notNull(relationEvent);
		
		relationEventRepository.delete(relationEvent);
	}
	
	// Other methods ----------------------------------------------
	
	public RelationEvent register(Event event){
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		
		RelationEvent relationEvent = create();
		
		relationEvent.setEvent(event);
		relationEvent.setChorbi(chorbiService.findByPrincipal());
		
		RelationEvent result = save(relationEvent);
		
		Collection<RelationEvent> relationEvents = event.getRelationEvents();
		relationEvents.add(result);
		event.setRelationEvents(relationEvents);
		
		return relationEvent;
	}
	
	public void unRegister (Event event){
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		
		Chorbi chorbi = chorbiService.findByPrincipal();
		Collection<RelationEvent> relationEventsEvent = event.getRelationEvents();
		RelationEvent relationEvent = null;
		Collection<RelationEvent> relationEventsChorbi = chorbi.getRelationEvents();
		
		for(RelationEvent re : event.getRelationEvents()){
			if(re.getChorbi().equals(chorbi)){
				relationEvent = re;
				break;
			}
		}
		
		relationEventsEvent.remove(relationEvent);
		relationEventsChorbi.remove(relationEvent);
		
		chorbi.setRelationEvents(relationEventsChorbi);
		event.setRelationEvents(relationEventsEvent);
		
		delete(relationEvent);
		
	}
}

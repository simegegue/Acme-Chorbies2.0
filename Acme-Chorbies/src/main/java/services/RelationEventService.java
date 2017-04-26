package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.RelationEventRepository;

@Service
@Transactional
public class RelationEventService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private RelationEventRepository 	relationEventRepository;
		
	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	
	public RelationEventService(){
		super();
	}
		
	// Simple CRUD methods ----------------------------------------------------
}

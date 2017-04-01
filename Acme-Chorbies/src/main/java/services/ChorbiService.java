package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChorbiRepository;
import security.LoginService;
import domain.Chorbi;

@Service
@Transactional
public class ChorbiService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private ChorbiRepository chorbiRepository;
	
	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ChorbiService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
			
	public Chorbi create() {

		Chorbi result;
		result = new Chorbi();

		return result;
	}

	public Collection<Chorbi> findAll() {

		Collection<Chorbi> result;

		result = chorbiRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Chorbi findOne(int chorbiId) {

		Chorbi result;

		result = chorbiRepository.findOne(chorbiId);
		Assert.notNull(result);

		return result;
	}

	public Chorbi save(Chorbi chorbi) {

		Assert.notNull(chorbi);
		Chorbi result;
		result = chorbiRepository.save(chorbi);

		return result;
	}

	public void delete(Chorbi chorbi) {

		Assert.notNull(chorbi);
				
		Assert.isTrue(chorbi.getId() != 0);

		chorbiRepository.delete(chorbi);
	}
	
	// Other bussiness methods ------------------------------------------------
	
	public Chorbi findByPrincipal() {
		Chorbi result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		result = chorbiRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}

}

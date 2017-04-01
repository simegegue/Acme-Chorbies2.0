package services;

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

	// Simple CRUD methods ----------------------------------------------------
	
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

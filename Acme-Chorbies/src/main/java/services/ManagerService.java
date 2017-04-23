
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.ManagerRepository;
import security.LoginService;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ManagerRepository	managerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------------------------

	public ManagerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other bussiness methods ------------------------------------------------

	public Manager findByPrincipal() {
		Manager result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		result = managerRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}

}

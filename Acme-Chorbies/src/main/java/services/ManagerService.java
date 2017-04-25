
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chirp;
import domain.Event;
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

	public Manager create() {
		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.MANAGER);
		authorities.add(a);
		userAccount.addAuthority(a);
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Chirp> sent = new ArrayList<Chirp>();

		Manager result;
		result = new Manager();
		result.setUserAccount(userAccount);
		result.setEvents(events);
		result.setSent(sent);

		return result;
	}

	public Collection<Manager> findAll() {

		Collection<Manager> result;

		result = managerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Manager findOne(int managerId) {

		Manager result;

		result = managerRepository.findOne(managerId);
		Assert.notNull(result);

		return result;
	}

	public Manager save(Manager manager) {

		Assert.notNull(manager);

		String password = manager.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String md5 = encoder.encodePassword(password, null);
		manager.getUserAccount().setPassword(md5);

		if (manager.getId() != 0) {
			Assert.isTrue(findByPrincipal().getId() == manager.getId());
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("MANAGER");
			Assert.isTrue(userAccount.getAuthorities().contains(au));
		}
		Manager result = managerRepository.save(manager);

		return result;
	}

	public Manager save2(Manager manager) {
		Manager result;

		result = managerRepository.save(manager);

		return result;
	}

	public void delete(Manager manager) {

		Assert.notNull(manager);

		Assert.isTrue(manager.getId() != 0);

		managerRepository.delete(manager);
	}

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

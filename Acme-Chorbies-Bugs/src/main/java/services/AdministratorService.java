package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Administrator create() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Administrator result;
		result = new Administrator();
		result.setUserAccount(userAccount);

		return result;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;

		result = administratorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Administrator findOne(int administratorId) {
		Administrator result;

		result = administratorRepository.findOne(administratorId);
		Assert.notNull(result);

		return result;
	}

	public Administrator save(Administrator administrator) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Assert.notNull(administrator);

		Administrator result;

		result = administratorRepository.save(administrator);

		return result;
	}

	public void delete(Administrator administrator) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);

		administratorRepository.delete(administrator);
	}

	// Other business methods -------------------------------------------------

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccountId(int userAccount) {
		Assert.notNull(userAccount);
		Administrator result;

		result = administratorRepository.findByUserAccountId(userAccount);

		return result;
	}

}

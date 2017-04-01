package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.BannerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Banner;

@Service
@Transactional
public class BannerService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private BannerRepository	bannerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------------------------

	public BannerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public Banner create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Banner result;
		result = new Banner();

		

		return result;
	}

	public Collection<Banner> findAll() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Banner> result;

		result = bannerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Banner findOne(int bannerId) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Banner result;

		result = bannerRepository.findOne(bannerId);
		Assert.notNull(result);

		return result;
	}

	public Banner save(Banner banner) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(banner);
		Banner result;
		result = bannerRepository.save(banner);

		return result;
	}

	public void delete(Banner banner) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(banner);
		
		Assert.isTrue(banner.getId() != 0);

		bannerRepository.delete(banner);
	}

}

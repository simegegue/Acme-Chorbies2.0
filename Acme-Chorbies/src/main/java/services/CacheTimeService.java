package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CacheTimeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.CacheTime;
import forms.CacheTimeForm;

@Service
@Transactional
public class CacheTimeService {
	
	// Managed repository -----------------------------------------------------

		@Autowired
		private CacheTimeRepository	cacheTimeRepository;
		
		@Autowired
		private Validator validator;

		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------

		public CacheTimeService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------
		
		public CacheTime create() {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			CacheTime result;
			result = new CacheTime();

			

			return result;
		}

		public Collection<CacheTime> findAll() {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Collection<CacheTime> result;

			result = cacheTimeRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public CacheTime findOne(int cacheTimeId) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			CacheTime result;

			result = cacheTimeRepository.findOne(cacheTimeId);
			Assert.notNull(result);

			return result;
		}

		public CacheTime save(CacheTime cacheTime) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(cacheTime);
			CacheTime result;
			result = cacheTimeRepository.save(cacheTime);

			return result;
		}

		public void delete(CacheTime cacheTime) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(cacheTime);
			
			Assert.isTrue(cacheTime.getId() != 0);

			cacheTimeRepository.delete(cacheTime);
		}
		//Form services
		public CacheTimeForm generateForm() {
			CacheTimeForm result = new CacheTimeForm();

			return result;
		}

		public CacheTime reconstruct(CacheTimeForm cacheTimeForm, BindingResult binding) {
			CacheTime result=findOne(cacheTimeForm.getId());
			result.setTime(cacheTimeForm.getTime());
			validator.validate(result, binding);
			return result;
		}

		public CacheTimeForm transform(CacheTime cacheTime) {
			CacheTimeForm result = generateForm();
			result.setId(cacheTime.getId());
			result.setTime(cacheTime.getTime());
			return result;
		}
}

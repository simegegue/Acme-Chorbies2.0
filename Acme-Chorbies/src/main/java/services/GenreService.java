package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.Repository;
import domain.Banner;
import domain.;
import forms.BannerForm;

@Service
@Transactional
public class Service {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private Repository Repository;
	
	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public Service() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
			
	public  create() {

		 result;
		result = new ();

		return result;
	}

	public Collection<> findAll() {

		Collection<> result;

		result = Repository.findAll();
		Assert.notNull(result);

		return result;
	}

	public  findOne(int Id) {

		 result;

		result = Repository.findOne(Id);
		Assert.notNull(result);

		return result;
	}

	public  save( ) {

		Assert.notNull();
		 result;
		result = Repository.save();

		return result;
	}

	public void delete( ) {

		Assert.notNull();
				
		Assert.isTrue(.getId() != 0);

		Repository.delete();
	}
	
	// Other bussiness methods ------------------------------------------------
	// Form methods ----------------------------------------------------------

		public BannerForm generateForm() {
			BannerForm result = new BannerForm();

			return result;
		}

		public Banner reconstruct(BannerForm bannerForm, BindingResult binding) {
			Banner result;
			if (bannerForm.getId() == 0)
				result = create();
			else {
				result = bannerRepository.findOne(bannerForm.getId());
			}
			result.setUrl(bannerForm.getUrl());
			validator.validate(result, binding);
			return result;
		}

		public BannerForm transform(Banner banner) {
			BannerForm result = generateForm();
			result.setId(banner.getId());
			result.setUrl(banner.getUrl());
			return result;
		}


}

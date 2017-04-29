
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.FeeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chorbi;
import domain.Fee;
import forms.FeeForm;

@Service
@Transactional
public class FeeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FeeRepository	feeRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator		validator;
	
	@Autowired
	private ChorbiService 	chorbiService;


	// Constructors -----------------------------------------------------------

	public FeeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Fee create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Fee result;

		result = new Fee();

		return result;
	}

	public Collection<Fee> findAll() {
		Collection<Fee> result;

		result = feeRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Fee findOne(int feeId) {
		Assert.isTrue(feeId != 0);

		Fee result;

		result = feeRepository.findOne(feeId);
		Assert.notNull(result);

		return result;
	}

	public Fee save(Fee fee) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(fee);

		return feeRepository.save(fee);
	}

	public void delete(Fee fee) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(fee);
		Assert.isTrue(fee.getId() != 0);
		Assert.isTrue(feeRepository.exists(fee.getId()));

		feeRepository.delete(fee);
	}

	// Other bussines methods ---------------------------

	public Fee find() {
		Collection<Fee> result;
		result = feeRepository.findAll();
		return result.iterator().next();
	}

	// Forms -----

	public FeeForm generateForm(Fee fee) {
		FeeForm feeForm = new FeeForm();

		feeForm.setId(fee.getId());
		feeForm.setChorbiValue(fee.getChorbiValue());
		feeForm.setManagerValue(fee.getManagerValue());

		return feeForm;
	}

	public Fee reconstruct(FeeForm feeForm, BindingResult binding) {
		Fee result = findOne(feeForm.getId());

		result.setChorbiValue(feeForm.getChorbiValue());
		result.setManagerValue(feeForm.getManagerValue());

		validator.validate(result, binding);

		return result;
	}
	
	public void addFeeChorbi(){
		
		UserAccount userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CHORBI");
		
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Fee fee = null;
		Chorbi chorbi = chorbiService.findByPrincipal();
		for(Fee f: findAll()){
			fee = f;
		}
		
		double feeAmount = chorbi.getFeeAmount();
		feeAmount += fee.getChorbiValue();
		
		chorbi.setFeeAmount(feeAmount);
	}

}

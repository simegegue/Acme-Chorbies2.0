
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chirp;
import domain.CreditCard;
import domain.Event;
import domain.Manager;
import forms.ManagerForm;

@Service
@Transactional
public class ManagerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ManagerRepository	managerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ChorbiService		chorbiService;

	
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

	public boolean check(CreditCard creditCard) {
		boolean validador = false;
		int sum = 0;
		Calendar fecha = Calendar.getInstance();
		String numero = creditCard.getNumber();
		int mes = fecha.get(Calendar.MONTH) + 1;
		int año = fecha.get(Calendar.YEAR);

		if (creditCard.getExpirationYear() > año) {
			validador = true;
		} else if (creditCard.getExpirationYear() == año) {
			if (creditCard.getExpirationMonth() >= mes) {
				validador = true;
			}
		}

		if(numero.length()<15){
			validador = false;
		}
		
		if (validador) {
			validador = false;
			int sumatorio = 0;
			for (int i = numero.length() - 1; i >= 0; i--) {
				int n = Integer.parseInt(numero.substring(i, i + 1));
				if (validador) {
					n *= 2;
					if (n > 9) {
						n = (n % 10) + 1;
					}
				}
				sum += n;
				validador = !validador;
				sumatorio += n;
			}
			if(sumatorio == 0){
				validador = false;
			}else if (sum % 10 == 0) {
				validador = true;
			}
		}

		return validador;
	}

	public Boolean principalCheckCreditCard() {
		Manager m = findByPrincipal();
		Boolean b;
		if (m.getCreditCard() == null) {
			b = false;
		} else {
			b = chorbiService.check(m.getCreditCard());
		}
		return b;
	}

	// Form methods -----------------------------------------------------------

	public ManagerForm generateForm(Manager manager) {
		ManagerForm result = new ManagerForm();

		result.setId(manager.getId());
		result.setUsername(manager.getUserAccount().getUsername());
		result.setPassword(manager.getUserAccount().getPassword());
		result.setPassword2(manager.getUserAccount().getPassword());
		result.setAgreed(true);

		result.setCreditCard(manager.getCreditCard());

		result.setEmail(manager.getEmail());
		result.setName(manager.getName());
		result.setPhone(manager.getPhone());
		result.setVat(manager.getVat());
		result.setCompany(manager.getCompany());
		result.setSurname(manager.getSurname());

		return result;
	}

	public Manager reconstructEditPersonalData(ManagerForm managerForm, BindingResult binding) {
		Manager result;
		
		Assert.isTrue(managerForm.getPassword2().equals(managerForm.getPassword()), "notEqualPassword");
		Assert.isTrue(managerForm.getAgreed(), "agreedNotAccepted");
		Assert.isTrue(check(managerForm.getCreditCard()), "badCreditCard");

		result = managerRepository.findOne(managerForm.getId());

		result.setName(managerForm.getName());
		result.setSurname(managerForm.getSurname());
		result.setEmail(managerForm.getEmail());
		result.setPhone(managerForm.getPhone());
		result.setVat(managerForm.getVat());
		result.setCompany(managerForm.getCompany());
		result.setCreditCard(managerForm.getCreditCard());

		validator.validate(result, binding);

		return result;
	}

	public ManagerForm generate() {
		ManagerForm result;
		result = new ManagerForm();

		return result;
	}

	public Manager reconstruct(ManagerForm managerForm, BindingResult binding) {
		Manager result = create();

		String password = managerForm.getPassword();

		Assert.isTrue(managerForm.getPassword2().equals(password), "notEqualPassword");
		Assert.isTrue(managerForm.getAgreed(), "agreedNotAccepted");
		Assert.isTrue(check(managerForm.getCreditCard()), "badCreditCard");

		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.MANAGER);
		authorities.add(a);
		userAccount.addAuthority(a);
		userAccount.setPassword(password);
		userAccount.setUsername(managerForm.getUsername());

		result.setUserAccount(userAccount);
		result.setName(managerForm.getName());
		result.setSurname(managerForm.getSurname());
		result.setEmail(managerForm.getEmail());
		result.setPhone(managerForm.getPhone());
		result.setVat(managerForm.getVat());
		result.setCompany(managerForm.getCompany());
		result.setFeeAmount(0.0);
		result.setCreditCard(managerForm.getCreditCard());

		validator.validate(result, binding);

		return result;
	}

	//Dashboard

	public Collection<Manager> managersByEvents() {
		Collection<Manager> result = managerRepository.managersByEvents();
		return result;
	}

	public Map<Manager, Double> mapManagerFee() {
		Map<Manager, Double> map = new HashMap<Manager, Double>();
		List<Object[]> aux = managerRepository.managerFeeAmount();
		for (Object[] o : aux) {
			map.put((Manager) o[0], (Double) o[1]);
		}
		return map;
	}

}

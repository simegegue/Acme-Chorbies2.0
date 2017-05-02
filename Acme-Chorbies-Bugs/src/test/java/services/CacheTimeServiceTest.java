package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.CacheTime;
import forms.CacheTimeForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CacheTimeServiceTest extends AbstractTest{
	
	// Thw SUT -------------------------------------------------
	
	@Autowired
	private CacheTimeService cacheTimeService;	
	
	// Tests ---------------------------------------------------
	
	/*Test
	 *  An actor who is authenticated as an administrator must be able to change 
	 *  the banners that are displayed on the welcome page and the time that the
		results of search templates are cached. The time must be expressed in hours,
		minutes, and seconds.
	 * */
	
	@Test
	public void driverEditCacheTime(){
		Object testingData[][] = {
			{"admin", "14:30:00", null},
			{"admin", "14:25", NullPointerException.class},
			{"chorbi2", "14:30:00", IllegalArgumentException.class},
			{null, "14:30:00", IllegalArgumentException.class}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateEditCacheTime((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateEditCacheTime(String user, String time, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(user); // nos autenticamos como usuario.
			CacheTimeForm cacheTimeForm = cacheTimeService.transform(cacheTimeService.findOne(70)); // Creamos un objeto bannerForm
			cacheTimeForm.setTime(time); // Le asignamos la url
			CacheTime cacheTime = cacheTimeService.reconstruct(cacheTimeForm, null); // Reconstruimos el Banner por medio del BannerForm
			Assert.notNull(cacheTime); // Comprobamos que banner no es nulo
			cacheTimeService.save(cacheTime); // Guardamos banner
			unauthenticate(); // Nos desautenticamos
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
}

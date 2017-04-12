package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Banner;
import forms.BannerForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class BannerServiceTest extends AbstractTest{
	
	// Thw SUT -------------------------------------------------
	
	@Autowired
	private BannerService bannerService;	
	
	// Tests ---------------------------------------------------
	
	/*Test
	 *  An actor who is authenticated as an administrator must be able to change the banners
		that are displayed on the welcome page and the time that the results
		 of search templates are cached.
	 * */
	
	@Test
	public void driverCreateBanner(){
		Object testingData[][] = {
			{"admin", "http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306", null},
			{"admin", "jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306", NullPointerException.class},
			{"chorbi2", "http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306", IllegalArgumentException.class},
			{null, "http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306", IllegalArgumentException.class}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateCreateBanner((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateCreateBanner(String user, String url, Class<?> expected){
		Class<?> caught = null;
		Banner banner = null;
		try{
			authenticate(user); // nos autenticamos como usuario.
			BannerForm bannerForm = bannerService.generateForm(); // Creamos un objeto bannerForm
			bannerForm.setUrl(url); // Le asignamos la url
			banner = bannerService.reconstruct(bannerForm, null); // Reconstruimos el Banner por medio del BannerForm
			Assert.notNull(banner); // Comprobamos que banner no es nulo
			bannerService.save(banner); // Guardamos banner
			unauthenticate(); // Nos desautenticamos
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}


	/* An actor who is authenticated as an administrator must be able to change the banners
		that are displayed on the welcome page and the time that the results
		 of search templates are cached.
	 */
	
	@Test
	public void driverDeleteBanner(){
		Object testingData[][] = {
			{"admin", 45, null},
			{"admin", 200, IllegalArgumentException.class},
			{"chorbi2", 45, IllegalArgumentException.class},
			{null, 45, IllegalArgumentException.class}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateDeleteBanner((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateDeleteBanner(String user, int id, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(user); // nos autenticamos como usuario.
			Banner banner = bannerService.findOne(id); // Buscamos el banner
			Assert.notNull(banner); // Comprobamos que banner no es nulo
			bannerService.delete(banner); // Eliminamos el banner
			unauthenticate(); // Nos desautenticamos
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
}

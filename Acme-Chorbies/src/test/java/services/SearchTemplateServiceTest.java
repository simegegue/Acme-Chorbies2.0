
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.SearchTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SearchTemplateServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private SearchTemplateService	searchTemplateService;


	// Tests --------------------------------------------------

	/*
	 * Create a SearchTemplate
	 * 
	 * Para ello vamos a probar crear un searchTemplate como administrador, estando registrado en el sistema y sin estar registrado en el sistema.
	 */
	@Test
	public void driverSave() {
		Object testingData[][] = {
			{
				"chorbi1", null
			}, {
				"admin", NullPointerException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateSave((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateSave(String username, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			SearchTemplate searchTemplate = searchTemplateService.findByPrincipal(); // Buscamos la searchTemplate del usuario logueado
			searchTemplateService.save(searchTemplate); // Guardamos la search template.
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

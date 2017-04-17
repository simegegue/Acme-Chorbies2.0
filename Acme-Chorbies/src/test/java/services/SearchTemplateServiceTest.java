
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
	 * Change his or her search template
	 * 
	 * Para ello vamos a editar una search template como chorbi, como administrador y sin estar registrado en el sistema.
	 */
	@Test
	public void driverSave() {
		Object testingData[][] = {
			{//Guardaremos un search template como un chorbi
				"chorbi1", null
			}, {//Como un administrador (No tiene)
				"admin", NullPointerException.class
			}, {//Como un usuario no registrado (No puede)
				null, IllegalArgumentException.class
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
			searchTemplate.setAge(23); // Modificamos la edad
			searchTemplateService.searchTemplateNull(searchTemplate); //Si la search template que recibe esta vacia (Campos a null) los pasa a vacios
			searchTemplateService.save(searchTemplate); // Guardamos la search template
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

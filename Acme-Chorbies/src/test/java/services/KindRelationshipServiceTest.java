
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.KindRelationship;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class KindRelationshipServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private KindRelationshipService	kindRelationshipService;


	// Tests --------------------------------------------------

	/*
	 * Create a KindRelationship
	 * 
	 * Para ello vamos a probar crear un kindRelationship como administrador, estando registrado en el sistema y sin estar registrado en el sistema.
	 */
	@Test
	public void driverCreate() {
		Object testingData[][] = {
			{
				"admin", null
			}, {
				"chorbi1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateCreate(String username, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			kindRelationshipService.create(); // Intentamos crear un kindRelationship nuevo.
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Delete a kindRelationship
	 */
	@Test
	public void driverDelete() {
		Object testingData[][] = {
			{
				"admin", 52, null
			}, // En este primer caso intentaremos borrar un kindRelationship que ningun actor tiene.
			{
				"chorbi1", 52, IllegalArgumentException.class
			}
		// Probamos ahora a borrar un kindRelationship que no tiene un actor como chorbi.
		};

		for (int i = 0; i < testingData.length; i++)
			templateDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDelete(String username, int id, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			KindRelationship kindRelationship = kindRelationshipService.findOne(id); // Obtenemos el kindRelationship a borrar.
			kindRelationshipService.delete(kindRelationship); // Borramos el kindRelationship
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

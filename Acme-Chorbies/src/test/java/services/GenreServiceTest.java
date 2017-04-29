
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Genre;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class GenreServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private GenreService	genreService;


	// Tests --------------------------------------------------

	/*
	 * Create a Genre
	 * 
	 * Para ello vamos a probar crear un genre como administrador, estando registrado en el sistema y sin estar registrado en el sistema.
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
			genreService.create(); // Intentamos crear un genre nuevo.
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Delete a genre
	 */
	@Test
	public void driverDelete() {
		Object testingData[][] = {
			{
				"admin", 72, null
			}, // En este primer caso intentaremos borrar un genero que ningun actor tiene.
			{
				"chorbi1", 72, IllegalArgumentException.class
			},
		// Probamos ahora a borrar un genero que no tiene un actor como chorbi.
			{
				null, 72, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDelete(String username, int id, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			Genre genre = genreService.findOne(id); // Obtenemos el genre a borrar.
			genreService.delete(genre); // Borramos el genre
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

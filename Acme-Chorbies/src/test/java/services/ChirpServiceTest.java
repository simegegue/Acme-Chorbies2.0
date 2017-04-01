
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Chirp;
import domain.Chorbi;
import forms.ChirpForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ChirpServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private ChirpService	chirpService;


	// Tests --------------------------------------------------

	/*
	 * Chirp to another chorbi.
	 * 
	 * Para ello vamos a probar crear un chirp estando registrado en el sistema y sin estar registrado en el sistema.
	 */
	@Test
	public void driverCreate() {
		Object testingData[][] = {
			{
				"chorbi1", null}, 
				{null, IllegalArgumentException.class}
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateCreate(String username, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			chirpService.create(); // Intentamos crear un chirp nuevo.
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Browse the list of chirps that he or she's sent.
	 */
	@Test
	public void driverListSent() {
		Object testingData[][] = {
			{"chorbi1", null}, // En este primer caso obtenemos los mensajes enviados por el chorbi 1.
			{null, IllegalArgumentException.class}// Probamos ahora a solicitar los mensajes de un usuario no registrado y no debería permitirnoslo.
		};

		for (int i = 0; i < testingData.length; i++)
			templateListSent((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListSent(String username, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			Collection<Chirp> chirps = chirpService.chirpSentByActorId(); // Obtenemos los chirp enviados por el usuario conectado.
			Assert.isTrue(!chirps.isEmpty()); // Comprobamos que la lista de chirps no esta vacía
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Browse the list of chirps that he or she's got.
	 */
	@Test
	public void driverListRecieved() {
		Object testingData[][] = {
			{"chorbi1", null}, // En este primer caso obtenemos los chirp recibidos por el customer 1.
			{null, IllegalArgumentException.class}// Probamos ahora a solicitar los chirp de un usuario no registrado y no debería permitirnoslo.
		};

		for (int i = 0; i < testingData.length; i++)
			templateListRecieved((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListRecieved(String username, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			Collection<Chirp> chirps = chirpService.chirpReceivedByActorId(); // Obtenemos los chirp recibidos por el usuario conectado.
			Assert.notNull(chirps); // Comprobamos que la lista de chirps no esta vacía
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 *Re-send any of the chirp that he or she's got.
	 */
	@Test
	public void driverForward() {
		Object testingData[][] = {
			{"chorbi1", 65, null}, // Reenviamos el chirp con id 65 del cual customer 1 es sender o recipient.
			{"chorbi3", 65, IllegalArgumentException.class}//Probamos a reenviar un el chirp con id 65 que no ha sido enviado o recibido por el customer 3.
		};

		for (int i = 0; i < testingData.length; i++)
			templateForward((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateForward(String username, int id, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			ChirpForm chirpForm = chirpService.forward(id); // Reenviamos el mensaje dado.
			Assert.isTrue(!chirpForm.getText().equals(null)); // Comprobamos que los campos del mensaje no son nulos.
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Reply a chirp that he or she's got.
	 */
	@Test
	public void driverReply() {
		Object testingData[][] = {
			{"chorbi2", 65, null}, // Respondemos el chirp con id 65 del cual customer 2 es recipient.
			{"chorbi3", 55, IllegalArgumentException.class}//Probamos a responder un el chirp con id 65 que no ha sido enviado o recibido por el customer 3.
		};

		for (int i = 0; i < testingData.length; i++)
			templateReply((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateReply(String username, int id, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			Collection<Chorbi> chorbies = chirpService.reply(id); // Reenviamos el mensaje dado.
			Assert.isTrue(!chorbies.isEmpty() && chorbies.size() == 1); // Comprobamos que la lista de actores no es vacía y tiene longitud 1.
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Erase a chirp that he or she's got.
	 */
	@Test
	public void driverErase() {
		Object testingData[][] = {
			{"chorbi2", 65, null}, // Eliminamos el chirp con id 55 del cual customer 2 es recipient.
			{"chorbi3", 65, IllegalArgumentException.class}//Probamos a eliminar un el chirp con id 65 que no ha sido enviado o recibido por el customer 3.
		};

		for (int i = 0; i < testingData.length; i++)
			templateErase((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateErase(String username, int id, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			Chirp chirp = chirpService.findOne(id);
			chirpService.deleteChirp(chirp); // eliminamos el mensaje dado.
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

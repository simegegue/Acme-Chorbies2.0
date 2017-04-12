
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Chorbi;
import forms.ChorbiForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ChorbiServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private ChorbiService	chorbiService;


	// Tests --------------------------------------------------

	/*
	 * Register as chorbi
	 * 
	 * Vamos a probar a registrarnos con distintos datos para probar el registro
	 */
	@Test
	public void driverCreate() {
		Object testingData[][] = {
			{
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965456321", null
			}, {
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "965456321", null
			}, {
				"username", "password", "password", "Nombre", "Apellidos", false, "correo@gmail.com", "+34 965456321", IllegalArgumentException.class
			}, {
				"username", "password", "passwor", "Nombre", "Apellidos", true, "correo@gmail.com", "957645231", IllegalArgumentException.class
			}, {
				"username", "password", "password", "Nombre", "Apellidos", true, "correomail.com", "957654123", NullPointerException.class
			}, {
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@mail.com", "4123", NullPointerException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Boolean) testingData[i][5], (String) testingData[i][6], (String) testingData[i][7],
				(Class<?>) testingData[i][8]);
	}

	protected void templateCreate(String username, String password, String password2, String name, String surname, Boolean agreed, String email, String phone, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			//authenticate(none); // Nos autenticamos como el usuario

			ChorbiForm chorbiForm;
			chorbiForm = chorbiService.generate();
			chorbiForm.setUsername(username);
			chorbiForm.setPassword(password);
			chorbiForm.setPassword2(password2);
			chorbiForm.setAgreed(agreed);
			chorbiForm.setName(name);
			chorbiForm.setSurname(surname);
			chorbiForm.setEmail(email);
			chorbiForm.setPhone(phone);
			Chorbi chorbi = chorbiService.reconstruct(chorbiForm, null);
			Assert.isTrue(chorbi != null);
			chorbiService.save(chorbi); // Guardamos chorbi

			//unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

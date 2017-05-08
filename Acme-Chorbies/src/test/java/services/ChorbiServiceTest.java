
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Chorbi;
import domain.Coordinate;
import domain.CreditCard;
import domain.Genre;
import domain.KindRelationship;
import forms.ChorbiForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ChorbiServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private ChorbiService			chorbiService;

	// Other services

	@Autowired
	private GenreService			genreService;

	@Autowired
	private KindRelationshipService	kindRelationshipService;


	// Tests --------------------------------------------------

	/*
	 * Register as chorbi
	 * 
	 * Vamos a probar a registrarnos con distintos datos para probar el registro
	 */
	@Test
	public void driverCreate() throws ParseException {
		Object testingData[][] = {
			{//Chorbi con todos los datos correctos
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description",
				"05/05/1993", "Country", "State", "Province", "City", "Visa", 580, 10, 2019, "Nombre", "4079978752719950", 50, 53, null
			}, {//Chorbi que no acepta terminos y condiciones
				"username", "password", "password", "Nombre", "Apellidos", false, "correo@gmail.com", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description",
				"05/05/1993", "Country", "State", "Province", "City", "Visa", 580, 10, 2019, "Nombre", "4079978752719950", 50, 53, IllegalArgumentException.class
			}, {//Chorbi con email incorrecto
				"username", "password", "password", "Nombre", "Apellidos", true, "correo", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description", "05/05/1993",
				"Country", "State", "Province", "City", "Visa", 580, 10, 2019, "Nombre", "4079978752719950", 50, 53, NullPointerException.class
			}, {//Chorbi con telefono incorrecto
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description", "05/05/1993",
				"Country", "State", "Province", "City", "Visa", 580, 10, 2019, "Nombre", "4079978752719950", 50, 53, NullPointerException.class
			}, {//Chorbi menor de edad
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description",
				"05/05/2001", "Country", "State", "Province", "City", "Visa", 580, 10, 2019, "Nombre", "4079978752719950", 50, 53, IllegalArgumentException.class
			}, {//Chorbi que no pone Country
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description",
				"05/05/1993", "", "State", "Province", "City", "Visa", 580, 10, 2019, "Nombre", "4079978752719950", 50, 53, null
			}, {//Chorbi que no pone State
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description",
				"05/05/1993", "Country", "", "Province", "City", "Visa", 580, 10, 2019, "Nombre", "4079978752719950", 50, 53, null
			}, {//Chorbi que no pone City
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description",
				"05/05/1993", "Country", "State", "Province", "", "Visa", 580, 10, 2019, "Nombre", "4079978752719950", 50, 53, NullPointerException.class
			}, {//Chorbi con todos los datos correctos pero sin credit card
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description",
				"05/05/1993", "Country", "State", "Province", "City", "", 0, 0, 0, "", "", 50, 53, null
			}, {//Chorbi con todos los datos correctos pero con credit Card incompleta
				"username", "password", "password", "Nombre", "Apellidos", true, "correo@gmail.com", "+34 965456321", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", "Description",
				"05/05/1993", "Country", "State", "Province", "City", "Visa", 0, 0, 0, "", "", 50, 53, IllegalArgumentException.class
			},

		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Boolean) testingData[i][5], (String) testingData[i][6], (String) testingData[i][7],
				(String) testingData[i][8], (String) testingData[i][9], (String) testingData[i][10], (String) testingData[i][11], (String) testingData[i][12], (String) testingData[i][13], (String) testingData[i][14], (String) testingData[i][15],
				(int) testingData[i][16], (int) testingData[i][17], (int) testingData[i][18], (String) testingData[i][19], (String) testingData[i][20], (int) testingData[i][21], (int) testingData[i][22], (Class<?>) testingData[i][23]);
	}

	protected void templateCreate(String username, String password, String password2, String name, String surname, Boolean agreed, String email, String phone, String picture, String description, String birthDateD, String country, String state,
		String province, String city, String brandName, int cvv, int expirationMonth, int expirationYear, String holderName, String number, int genreV, int kindRelationshipV, Class<?> expected) throws ParseException {
		Class<?> caught;

		Coordinate coordinate = new Coordinate();
		coordinate.setCountry(country);
		coordinate.setState(state);
		coordinate.setProvince(province);
		coordinate.setCity(city);

		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName(brandName);
		creditCard.setCvv(cvv);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setHolderName(holderName);
		creditCard.setNumber(number);

		Genre genre = genreService.findOne(genreV);

		KindRelationship kindRelationship = kindRelationshipService.findOne(kindRelationshipV);

		Date birthDate;
		final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		birthDate = fecha.parse(birthDateD);

		caught = null;
		try {

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

			chorbiForm.setPicture(picture);
			chorbiForm.setDescription(description);
			chorbiForm.setBirthDate(birthDate);
			chorbiForm.setCoordinate(coordinate);
			chorbiForm.setCreditCard(creditCard);
			chorbiForm.setGenre(genre);
			chorbiForm.setKindRelationship(kindRelationship);

			Chorbi chorbi = chorbiService.reconstruct(chorbiForm, null);
			Assert.isTrue(chorbi != null);
			chorbiService.save(chorbi);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Browse the list of chorbies who have registered to the system.
	 * 
	 * Vamos a logear como un chorbi/admin y listar los chorbis
	 * y vamos a hacer lo mismo como usuario no autenticado
	 */
	@Test
	public void driverList() {
		Object testingData[][] = {
			{
				"chorbi1", 85, null
			}, // Obtenedremos la lista de chorbies al ser un chorbi y navegaremos hacia un chorbi que existe.
			{
				"chorbi1", 84, IllegalArgumentException.class
			}, // Obtenedremos la lista de chorbies al ser un chorbi pero luego navegaremos hacia un chorbi que no existe.
			{
				"admin", 85, null
			}, // Obtendremos todas los chorbies como admin y navegaremos hacia un chorbi que existe.
			{
				"admin", 84, IllegalArgumentException.class
			}, // Obtendremos todas los chorbies como admin pero luego navegaremos hacia un chorbi que no existe.
			{
				null, 85, IllegalArgumentException.class
			}
		// Y tambien lo haremos como usuario no autenticado.
		};

		for (int i = 0; i < testingData.length; i++)
			templateList((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateList(String username, int id, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username); // Nos autenticamos como el usuario
			Collection<Chorbi> chorbies = chorbiService.findAll(); // Obtenemos los chorbies.
			Assert.isTrue(!chorbies.isEmpty()); // Comprobamos que la lista de chorbies no esta vacía
			Chorbi chorbi = chorbiService.findOne(id);
			Assert.notNull(chorbi);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

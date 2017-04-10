package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Chorbi;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest{
	
	// The SUT ----------------------------------------------------
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private ChorbiService chorbiService;
	
	// Tests ------------------------------------------------------
	
	/*Test 
	 * Dashboard level A
	 * 
	 *  The minimum, the maximum, and the average number of chirps that a chorbi
		receives from other chorbies.
	 *  The minimum, the maximum, and the average number of chirps that a chorbi
	 *	sends to other chorbies.
	 *  The chorbies who have got more chirps.
	 *  The chorbies who have sent more chirps.
	 * */
	
	@Test
	public void driverDashboardA(){
		Object testingData[][] = {
			{"admin", null},
			{"chorbi1", IllegalArgumentException.class},
			{null, IllegalArgumentException.class}
		};
		for(int i = 0; i < testingData.length; i++){
			templateDashboardA((String) testingData[i][0],(Class<?>) testingData[i][1]);
		}
	}
	
	protected void templateDashboardA(String user, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(user); // Nos autenticamos como usuario.
			Collection<Double> d = chorbiService.minMaxAvgReceivedChirpChorbi(); // Obtenemos el min, max, avg de chirps recividas por los chorbies.
			Collection<Double> f = chorbiService.minMaxAvgSentChirpChorbi(); // Obtenemos el min, max, avg de chirps enviadas por los chorbies.
			Collection<Chorbi> c = chorbiService.moreChirpReceivedChorbies(); // Obtenemos los chorbies que hayan recibido mas chirps.
			Collection<Chorbi> a = chorbiService.moreChirpSentChorbies(); // Obtenemos los chorbies que hayan enviado mas chirps;
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	/*Test 
	 * Dashboard level B
	 * 
	 *  The list of chorbies, sorted by the number of likes they have got.
	 *  The minimum, the maximum, and the average number of likes per chorbi.
	 * */
	
	@Test
	public void driverDashboardB(){
		Object testingData[][] = {
			{"admin", null},
			{"chorbi2", IllegalArgumentException.class},
			{null, IllegalArgumentException.class}
		};
		for(int i = 0; i < testingData.length; i++){
			templateDashboardB((String) testingData[i][0],(Class<?>) testingData[i][1]);
		}
	}
	
	protected void templateDashboardB(String user, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(user); // Nos autenticamos como usuario.
			Collection<Double> d = chorbiService.minMaxAvgReceivedLikeChorbi(); // Obtenemos el min, max, avg de likes recividas por los chorbies.
			Collection<Chorbi> a = chorbiService.listChorbiesbyLikes(); // Obtenemos los chorbies ordenados segun el numero de likes que tienen;
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	/*Test 
	 * Dashboard level C
	 * 
	 *  A listing with the number of chorbies per country and city.
	 *  The minimum, the maximum, and the average ages of the chorbies.
	 *  The ratio of chorbies who have not registered a credit card or have regis-tered an invalid credit card.
	 *  The ratios of chorbies who search for 'activities', 'friendship', and 'love'.
	 * */
	
	@Test
	public void driverDashboardC(){
		Object testingData[][] = {
			{"admin", null},
			{"chorbi1", IllegalArgumentException.class},
			{null, IllegalArgumentException.class}
		};
		for(int i = 0; i < testingData.length; i++){
			templateDashboardC((String) testingData[i][0],(Class<?>) testingData[i][1]);
		}
	}
	
	protected void templateDashboardC(String user, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(user); // Nos autenticamos como usuario.
			Collection<Integer> d = chorbiService.numberOfChorbiesByCity(); // Obtenemos el numero de chorbies opr ciudad.
			Collection<Integer> t = chorbiService.numberOfChorbiesByCountry(); // Obtenemos el numero de chorbies por país.
			Collection<Double> f = chorbiService.minMaxAvgChorbiYear(); // Obtenemos el min, max, avg de la edad de los chorbies.
			Double c = chorbiService.ratioChorbiesNullCreditCard(); // Obtenemos el ratio de chorbies sin tarjeta de credito.
			Collection<Double> a = chorbiService.actFriLoveRatioRelationChorbi(); // Obtenemos los ratios de chorbies en funcion de su kindRelationship;
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	/* Test
	 * 
	 * Ban a chorbi, that is, to disable his or her account.
	 * Unban a chorbi, wich means that his or her account is re-enabled.
	 * 
	 */
	
	 @Test
	 public void driverBanUnbanChorbi(){
		 Object testingData[][] = {
			 {"admin", 62, null},
			 {"chorbi2", 66, IllegalArgumentException.class},
			 {null, 65, IllegalArgumentException.class}
		 };
		 for(int i = 0; i < testingData.length; i++){
			 templateBanUnbanChorbi((String) testingData[i][0],(int) testingData[i][1],(Class<?>) testingData[i][2]);
		 }
	 }
	 
	 protected void templateBanUnbanChorbi(String user, int chorbiId, Class<?> expected){
		 Class<?> caught = null;
		 try{
			 authenticate(user); // Nos autenticamos como el usuario
			 Chorbi chorbi = chorbiService.findOne(chorbiId); // Obtenemos el chorbi a banear
			 chorbiService.banUnban(chorbi); // Baneamos el chorbi indicado.
			 chorbiService.banUnban(chorbi); // Desbaneamos al usuario baneado anteriormente.
			 unauthenticate();
		 }catch(Throwable oops){
			 caught = oops.getClass();
		 }
		 checkExceptions(expected, caught);
	 }

}

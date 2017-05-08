
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class RelationLikeServiceTest extends AbstractTest {

	// The SUT -------------------------------------------------

	@Autowired
	private ChorbiService		chorbiService;

	@Autowired
	private RelationLikeService	relationLikeService;


	// Tests ---------------------------------------------------

	/*
	 * Test
	 * An actor who is authenticated as chorbi must be able to like another chorbi;
	 * a like may be cancelled at any time.
	 */

	@Test
	public void driverLikeUnlikeChorbi() {
		Object testingData[][] = {
			{
				"chorbi1", 87, 86, null
			}, {
				"chorbi2", 89, 86, null
			}, {
				"admin", 85, 86, IllegalArgumentException.class
			}, {
				null, 85, 86, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateLikeUnlikeChorbi((String) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (Class<?>) testingData[i][3]);
		}
	}

	protected void templateLikeUnlikeChorbi(String user, int chorbiLike, int chorbiUnlike, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(user); // nos autenticamos como usuario.
			Chorbi likeSender = chorbiService.findByPrincipal(); // Obtenemos el chorbi logeado en el sistema
			Chorbi likeRecipient = chorbiService.findOne(chorbiLike); // Obtenemos el chorbi al cual se le quiere hacer like.
			Chorbi likeRecipient2 = chorbiService.findOne(chorbiUnlike); // Obtenemos el chorbi al cual queremos hacer unlike.
			relationLikeService.giveLike(likeSender, likeRecipient); // Se le da like al chorbi.
			relationLikeService.unLike(likeSender, likeRecipient2); // Le damos unlike al segundo chorbi
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	/*An actor who is authenticated as a chorbi must be able to:
		o Browse the catalogue of chorbies who have liked him or her as long as 
		he or she has registered a valid credit card. If he or she's not register a valid card,
		then the system must ask him or her to do so; the system must inform the chorbies that 
		their credit cards will not be charged.*/
	
	@Test
	public void driverListChorbiesbyLikes() {
		Object testingData[][] = {
			{
				"chorbi1", 85 , null
			}, {
				"chorbi5", 89, IllegalArgumentException.class
			}, {
				"admin", 85, IllegalArgumentException.class
			}, {
				null, 85, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateListChorbiesbyLikes((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}

	protected void templateListChorbiesbyLikes(String user, int id, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(user); // nos autenticamos como usuario.
			Assert.isTrue(chorbiService.principalCheckCreditCard()); // Comprobamos la tarjeta de credito
			relationLikeService.findByLikesSent(id); // obtenemos la lista de chorbies que le han dado like al usuario actual
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

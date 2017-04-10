package services;

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
public class RelationLikeServiceTest extends AbstractTest{
	
	// Thw SUT -------------------------------------------------
	
	@Autowired
	private ChorbiService chorbiService;
	
	@Autowired
	private RelationLikeService relationLikeService;
	
	
	// Tests ---------------------------------------------------
	
	/*Test
	 *  An actor who is authenticated as chorbi must be able to like another chorbi;
	 *  a like may be cancelled at any time.
	 * */
	
	@Test
	public void driverLikeUnlikeChorbi(){
		Object testingData[][] = {
			{"chorbi1", 64, 63, null},
			{"chorbi2", 66, 64, null},
			{"admin", 62, 63, IllegalArgumentException.class},
			{null, 62, 63, IllegalArgumentException.class}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateLikeUnlikeChorbi((String) testingData[i][0], (int) testingData[i][1],(int) testingData[i][2], (Class<?>) testingData[i][3]);
		}
	}
	
	protected void templateLikeUnlikeChorbi(String user, int chorbiLike, int chorbiUnlike, Class<?> expected){
		Class<?> caught = null;
		
		try{
			authenticate(user); // nos autenticamos como usuario.
			Chorbi likeSender = chorbiService.findByPrincipal(); // Obtenemos el chorbi logeado en el sistema
			Chorbi likeRecipient = chorbiService.findOne(chorbiLike); // Obtenemos el chorbi al cual se le quiere hacer like.
			Chorbi likeRecipient2 = chorbiService.findOne(chorbiUnlike); // Obtenemos el chorbi al cual queremos hacer unlike.
			relationLikeService.giveLike(likeSender, likeRecipient); // Se le da like al chorbi.
			relationLikeService.unLike(likeSender, likeRecipient2); // Le damos unlike al segundo chorbi
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

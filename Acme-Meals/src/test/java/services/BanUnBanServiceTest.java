package services;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.User;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class BanUnBanServiceTest extends AbstractTest{
	@Autowired
	private UserService		userService;
	//Test
	/*Ban or unban a user.
	 * 
	 * Banear  un usuario.
	 */
	@Test
	public void driverBan() throws ParseException {
		Object testingData[][] = {
		{//Manager con todos los datos correctos
			"admin",97 ,null},
		{//Usuario creando una category
			"user1",97,IllegalArgumentException.class},
		{//Usuario sin autenticar creando una category
			null,97 ,IllegalArgumentException.class},
};

		
		for (int i = 0; i < testingData.length; i++)
			templateBan((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
		protected void templateBan(String username,Integer id, Class<?> expected) throws ParseException {
		
			Class<?> caught = null;
			
			try {
				authenticate(username); // Iniciamos sesión con el usuario
				
				User u=userService.findOne(id);
				userService.ban(u);
				Assert.isTrue(u.getBanned()==true);
				

				unauthenticate();
			} catch (Throwable oops) {
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
		}
		//Test
		/*Ban or unban a user.
		 * 
		 * Desbanear  un usuario.
		 */
		@Test
		public void driverUnban() throws ParseException {
			Object testingData[][] = {
			{//Manager con todos los datos correctos
				"admin",97 ,null},
			{//Usuario creando una category
				"user1",97,IllegalArgumentException.class},
			{//Usuario sin autenticar creando una category
				null,97 ,IllegalArgumentException.class},
	};

			
			for (int i = 0; i < testingData.length; i++)
				templateBan((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
		}
			protected void templateUnban(String username,Integer id, Class<?> expected) throws ParseException {
			
				Class<?> caught = null;
				
				try {
					authenticate(username); // Iniciamos sesión con el usuario
					
					User u=userService.findOne(id);
					u.setBanned(true);
					userService.unban(u);
					Assert.isTrue(u.getBanned()==false);
					

					unauthenticate();
				} catch (Throwable oops) {
					caught = oops.getClass();
				}
				checkExceptions(expected, caught);
			}


}

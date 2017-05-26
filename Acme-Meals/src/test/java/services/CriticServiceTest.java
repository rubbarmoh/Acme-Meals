package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Critic;
import forms.CriticForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@Transactional
public class CriticServiceTest extends AbstractTest{
	
	// The SUT ----------------------------------------------------------
	
	@Autowired
	private CriticService	criticService;
	
	// Tests ------------------------------------------------------------
	
	/*Test
	 * Register a critic into the system. 
	 * */
	@Test
	public void driverCreateCritic(){
		Object testingData[][] = {
			{"null", "critic1", "critic1", "critic1", "Lucas", "López", "example@gmail.com", "+34 123456789", "calle falsa, 123", "EBBE", IllegalArgumentException.class}, // Intentamos ahcer el registro sin estar registrado
			{"user1", "critic1", "critic1", "critic1", "Lucas", "López", "example@gmail.com", "+34 123456789", "calle falsa, 123", "EBBE", IllegalArgumentException.class}, // Intentamos hacer el registro siendo un user.
			{"manager1", "critic1", "critic1", "critic1", "Lucas", "López", "example@gmail.com", "+34 123456789", "calle falsa, 123", "EBBE", IllegalArgumentException.class}, // Intentamos hacer el registro siendo un manager.
			{"admin", "critic1", "critic1", "critic1", "Lucas", "López", "examplegmail.com", "+34 123456789", "calle falsa, 123", "EBBE", IllegalArgumentException.class}, // Intentamos hacer el registro con un email erroneo.
			{"admin", "critic1", "critic1", "critic1", "Lucas", "López", "examplegmail.com", "", "calle falsa, 123", "EBBE", IllegalArgumentException.class}, // Intentamos hacer el registro sin telefono.
			{"admin", "critic1", "critic1", "critic1", "Lucas", "López", "example@gmail.com", "+34 123456789", "calle falsa, 123", "EBBE", null}
		};
		
		for(int i = 0; i< testingData.length; i++){
			templateCreateCritic((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5],(String) testingData[i][6],(String) testingData[i][7],(String) testingData[i][8],(String) testingData[i][9], (Class<?>) testingData[i][10]);
		}
	}
	
	protected void templateCreateCritic(String username, String username2, String password, String password2,  String name, String surname, String email, String phone, String address, String companyName, Class<?> expected){
		Class<?> caught = null;
		try{
			authenticate(username); // Nos autenticamos
			
			// Rellenamos el formulario.
			
			CriticForm criticForm = criticService.generate();
			criticForm.setAddress(address);
			criticForm.setCompanyName(companyName);
			criticForm.setEmail(email);
			criticForm.setName(name);
			criticForm.setPassword(password);
			criticForm.setPassword2(password2);
			criticForm.setUsername(username2);
			criticForm.setSurname(surname);
			criticForm.setPhone(phone);
			
			// Reconstruimos el critico y lo guardamos en el sistema.
			
			Critic critic = criticService.reconstruct(criticForm, null);
			Assert.isTrue(critic != null);
			criticService.save(critic);
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}


package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.CreditCard;
import domain.User;
import forms.UserForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class UserServiceTest extends AbstractTest {

	// The SUT ----------------------------------------------------------

	@Autowired
	private UserService	userService;


	// Tests ------------------------------------------------------------

	/*
	 * Register to the system as a user.
	 */
	@Test
	public void driverRegisterUser() {
		Object testingData[][] = {
			{
				"user25", "user25", "user25", "Pedro", "Test", "example@gmail.com", "+34 123456789", "calle falsa, 123", true, "Visa", 580, 10, 2019, "Nombre", "", IllegalArgumentException.class
			}, // Intentamos hacer el registro sin el numero de tarjeta de credito.
			{
				"user25", "user25", "user25", "Pedro", "Test", "example@gmail.com", "+34 123456789", "calle falsa, 123", false, "Visa", 580, 10, 2019, "Nombre", "4079978752719950", IllegalArgumentException.class
			}, // Intentamos registro sin marcar la casilla de agreed
			{
				"user25", "user25", "user25", "Pedro", "Test", "example@gmail.com", "+34 123456789", "calle falsa, 123", true, "Visa", 580, 10, 2019, "Nombre", "4079978752719950", null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateCreateUser((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (String) testingData[i][7],
				(boolean) testingData[i][8], (String) testingData[i][9], (int) testingData[i][10], (int) testingData[i][11], (int) testingData[i][12], (String) testingData[i][13], (String) testingData[i][14], (Class<?>) testingData[i][15]);
		}
	}

	protected void templateCreateUser(String username2, String password, String password2, String name, String surname, String email, String phone, String address, boolean agreed, String brandName, int cvv, int expirationMonth, int expirationYear,
		String holderName, String number, Class<?> expected) {
		Class<?> caught = null;
		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName(brandName);
		creditCard.setCvv(cvv);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setHolderName(holderName);
		creditCard.setNumber(number);
		try {

			// Rellenamos el formulario.

			UserForm userForm = userService.generate();
			userForm.setAddress(address);
			userForm.setEmail(email);
			userForm.setName(name);
			userForm.setPassword(password);
			userForm.setPassword2(password2);
			userForm.setAgreed(agreed);
			userForm.setUsername(username2);
			userForm.setSurname(surname);
			userForm.setPhone(phone);
			userForm.setCreditCard(creditCard);

			// Reconstruimos el critico y lo guardamos en el sistema.

			User user = userService.reconstruct(userForm, null);
			Assert.isTrue(user != null);
			userService.save(user);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Register to the system as a user.
	 */
	@Test
	public void driverEditUser() {
		Object testingData[][] = {
			{
				null, "", IllegalArgumentException.class
			}, // Editamos perfil con un usuario no autenticado
			{
				"user1", "", IllegalArgumentException.class
			}, // Editamos perfil pero dejamos en blanco la casilla de edicion
			{
				"user1", "newName", null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateEditUser((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	protected void templateEditUser(String username, String name, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username); // Nos autenticamos
			User user = userService.findByPrincipal();
			user.setName(name);
			Assert.isTrue(user != null);
			userService.save2(user);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

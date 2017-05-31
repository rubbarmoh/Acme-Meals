
package services;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Manager;

import utilities.AbstractTest;
import forms.ManagerForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ManagerServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private ManagerService			managerService;

	// Other services

	

	// Tests --------------------------------------------------

	/*
	 * Register as manager
	 * 
	 * Vamos a probar a registrarnos con distintos datos para probar el registro
	 */
	@Test
	public void driverCreate() throws ParseException {
		Object testingData[][] = {
			{//Manager con todos los datos correctos
				"username", "password", "password", "Nombre", "Apellidos", "correo@gmail.com", "+34 965456321", "Direccion",
				"Visa", 580, 10, 2019, "Nombre", "4079978752719950", true, null},
			{//Manager con todos los datos correctos con otro formato de telefono
				"username", "password", "password", "Nombre", "Apellidos", "correo@gmail.com", "965456321", "Direccion",
				"Visa", 580, 10, 2019, "Nombre", "4079978752719950", true, null},
			{//Manager con tarjeta de credito caducada
				"username", "password", "password", "Nombre", "Apellidos", "correo@gmail.com", "965456321", "Direccion",
				"Visa", 580, 10, 2016, "Nombre", "4079978752719950", true, IllegalArgumentException.class},
			{//Manager con tarjeta de credito caducada
				"username", "password", "password", "Nombre", "Apellidos", "correo@gmail.com", "965456321", "Direccion",
				"Visa", 580, 3, 2017, "Nombre", "4079978752719950", true, IllegalArgumentException.class},
			{//Manager con numero de tarjeta incorrecto
				"username", "password", "password", "Nombre", "Apellidos", "correo@gmail.com", "965456321", "Direccion",
				"Visa", 580, 10, 2019, "Nombre", "4079897752719950", true, IllegalArgumentException.class},
		
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (int) testingData[i][9], (int) testingData[i][10], (int) testingData[i][11], (String) testingData[i][12], (String) testingData[i][13], (Boolean) testingData[i][14], (Class<?>) testingData[i][15]);
	}

	protected void templateCreate(String username, String password, String password2, String name, String surname, String email, String phone, String address,
			String brandName, int cvv, int expirationMonth, int expirationYear, String holderName, String number, Boolean agred, Class<?> expected) throws ParseException {
	
		Class<?> caught = null;


		CreditCard creditCard = new CreditCard();
		creditCard.setBrandName(brandName);
		creditCard.setCvv(cvv);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setHolderName(holderName);
		creditCard.setNumber(number);
		
		
		try {

			ManagerForm managerForm;
			managerForm = managerService.generate();
			managerForm.setUsername(username);
			managerForm.setPassword(password);
			managerForm.setPassword2(password2);
			managerForm.setName(name);
			managerForm.setSurname(surname);
			managerForm.setEmail(email);
			managerForm.setPhone(phone);
			managerForm.setAddress(address);
			managerForm.setCreditCard(creditCard);
			managerForm.setAgreed(true);

			Manager manager = managerService.reconstruct(managerForm, null);
			Assert.isTrue(manager != null);
			managerService.save(manager);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

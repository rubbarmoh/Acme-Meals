
package services;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.VATNumber;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class VATNumberServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private VATNumberService	vatNumberService;


	// Other services

	// Tests --------------------------------------------------

	/*
	 * Manage the VAT number of Acme Meals.
	 * 
	 * 
	 * Editar
	 */
	@Test
	public void driverEdit() throws ParseException {
		Object testingData[][] = {

			{// Editar siendo admin
				"admin", 95, "ES-21212121", null
			}, {// Editar siendo usuario
				"user1", 95, "ES-21212121", IllegalArgumentException.class
			}, {// Editar sin estar autenticado
				null, 95, "ES-21212121", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateEdit((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateEdit(String username, int vatId, String vat, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario
			VATNumber vn = vatNumberService.findOne(vatId);
			vn.setValue(vat);
			vn = vatNumberService.save(vn);
			Assert.notNull(vn);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

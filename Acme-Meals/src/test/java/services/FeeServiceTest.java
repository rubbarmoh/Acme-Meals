
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
import domain.Fee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class FeeServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private FeeService	feeService;


	// Other services

	// Tests --------------------------------------------------

	/*
	 * Manage the fee.
	 * 
	 * 
	 * 
	 * Editar
	 */
	@Test
	public void driverEdit() throws ParseException {
		Object testingData[][] = {

			{// Editar siendo admin
				"admin", 94, 2.0, null
			}, {// Editar siendo usuario
				"user1", 94, 2.0, IllegalArgumentException.class
			}, {// Editar sin estar autenticado
				null, 94, 2.0, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateEdit((String) testingData[i][0], (int) testingData[i][1], (Double) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateEdit(String username, int feeId, Double fee, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario
			Fee f = feeService.findOne(feeId);
			f.setValue(fee);
			f = feeService.save(f);
			Assert.notNull(f);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

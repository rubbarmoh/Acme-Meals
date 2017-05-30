
package services;

import java.text.ParseException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.MonthlyBill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MonthlyBillServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private MonthlyBillService	monthlyBillService;


	// Other services

	// Tests --------------------------------------------------

	/*
	 * List paid and unpaid monthly bills.
	 * 
	 * Listar
	 */
	@Test
	public void driverList() throws ParseException {
		Object testingData[][] = {

			{// Listar monthlyBills como admin
				"admin", null
			}, {// Listar monthlyBills como user
				"user1", IllegalArgumentException.class
			}, {// Listar monthlyBills como usuario no autenticado
				null, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateList((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateList(String username, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario

			Collection<MonthlyBill> MonthlyBills = monthlyBillService.findAll();
			Assert.notEmpty(MonthlyBills);

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

	/*
	 * Generate the monthly bills.
	 * 
	 * 
	 * Generar
	 */
	@Test
	public void driverGenerate() throws ParseException {
		Object testingData[][] = {

			{// Listar monthlyBills como admin
				"admin", null
			}, {// Listar monthlyBills como user
				"user1", IllegalArgumentException.class
			}, {// Listar monthlyBills como usuario no autenticado
				null, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateGenerate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateGenerate(String username, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario
			Collection<MonthlyBill> monthlyBills = monthlyBillService.findAll();

			monthlyBillService.generateMonthlyBills();
			monthlyBillService.generateMonthlyBillsLD();

			Collection<MonthlyBill> monthlyBills1 = monthlyBillService.findAll();
			Assert.isTrue(monthlyBills.size() < monthlyBills1.size());

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

	/*
	 * List monthly bills.
	 * 
	 * Listar
	 */
	@Test
	public void driverListManager() throws ParseException {
		Object testingData[][] = {

			{// Listar monthlyBills como admin
				"manager1", null
			}, {// Listar monthlyBills como user
				"user1", IllegalArgumentException.class
			}, {// Listar monthlyBills como usuario no autenticado
				null, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateListManager((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListManager(String username, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario

			Collection<MonthlyBill> MonthlyBills = monthlyBillService.findByPrincipal();
			Assert.notEmpty(MonthlyBills);

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}


package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.MealOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MealOrderServiceTest extends AbstractTest {

	// The SUT ----------------------------------------------------------

	@Autowired
	private MealOrderService	mealOrderService;


	// Tests ------------------------------------------------------------

	/*
	 * List Orders
	 */
	@Test
	public void driverOrderList() {
		Object testingData[][] = {
			{
				null, IllegalArgumentException.class
			}, // Listamos nuestros pedidos con usuario no autenticado
			{
				"admin", IllegalArgumentException.class
			}, // Listamos nuestros pedidos con admin
			{
				"user1", null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateOrderList((String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
	}
	protected void templateOrderList(String username, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username); // Nos autenticamos
			Collection<MealOrder> orders = mealOrderService.findCurrentlyByUser();

			Assert.notNull(orders);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Display an order and delete if status is draft
	 */
	@Test
	public void driverOrderDisplay() {
		Object testingData[][] = {
			{
				null, 157, IllegalArgumentException.class
			}, // Mostramos pedido con usuario no autenticado
			{
				"user1", 156, IllegalArgumentException.class
			}, // Mostramos un pedido con id incorrecta
			{
				"user1", 157, IllegalArgumentException.class
			}, // Mostramos un pedido con status no draft
			{
				"user1", 158, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateOrderDisplay((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	protected void templateOrderDisplay(String username, int id, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username); // Nos autenticamos
			MealOrder order = mealOrderService.findOne(id);
			Assert.notNull(order);
			mealOrderService.delete(order);
			Assert.isTrue(!mealOrderService.findAll().contains(order));
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}


package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.MealOrder;
import domain.Quantity;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MealOrderServiceTest extends AbstractTest {

	// The SUT ----------------------------------------------------------

	@Autowired
	private MealOrderService	mealOrderService;

	@Autowired
	private MealService			mealService;

	@Autowired
	private UserService			userService;

	@Autowired
	private RestaurantService	restaurantService;


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
			MealOrder orderaux = mealOrderService.findOne(id);
			Assert.isTrue(orderaux == null);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Make an order to a restaurant
	 */
	@Test
	public void driverMakeOrder() {
		Object testingData[][] = {
			{
				null, "C/Falsa, 123", false, 107, 153, IllegalArgumentException.class
			}, // Hacemos pedido con usuario no autenticado
			{
				"user1", "C/Falsa, 123", false, 107, 150, IllegalArgumentException.class
			}, // Hacemos pedido con un plato que no existe
			{
				"user1", "C/Falsa, 123", false, 107, 153, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateMakeOrder((String) testingData[i][0], (String) testingData[i][1], (boolean) testingData[i][2], (int) testingData[i][3], (int) testingData[i][4], (Class<?>) testingData[i][5]);
		}
	}
	protected void templateMakeOrder(String username, String deliveryAddress, boolean pickUp, int restaurantId, int mealId, Class<?> expected) {
		Class<?> caught = null;
		Collection<Quantity> quantities = new ArrayList<Quantity>();
		Quantity quantity = new Quantity();
		Date date = new Date(System.currentTimeMillis() - 1);
		try {
			authenticate(username); // Nos autenticamos
			User user = userService.findByPrincipal();
			MealOrder order = mealOrderService.create();

			quantity.setMeal(mealService.findOne(mealId));
			quantity.setMealOrder(order);
			quantity.setQuantity(2);
			Double amount = quantity.getMeal().getPrice() * quantity.getQuantity();
			quantities.add(quantity);

			order.setAmount(amount);
			order.setQuantities(quantities);
			order.setDeliveryAddress(deliveryAddress);
			order.setMoment(date);
			order.setPickUp(pickUp);
			order.setRestaurant(restaurantService.findOne(restaurantId));
			order.setUser(user);

			order = mealOrderService.save(order);
			Assert.notNull(order);
			unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests ------------------------------------------------------------

	/*
	 * Manage received orders. Only can change the status
	 */
	@Test
	public void driverOrderNext() {
		Object testingData[][] = {
			{
				"manager1", 157, null
			}, // Listamos pedidos y modificamos un order en depending
			{
				"manager1", 158, IllegalArgumentException.class
			}, // Listamos pedidos e intentamos modificar un order en draft
			{
				"admin", 157, IllegalArgumentException.class
			}, // Intentamos modificar siendo admin
			{
				"user1", 157, IllegalArgumentException.class
			}
		// Intentamos modificar siendo user
		};

		for (int i = 0; i < testingData.length; i++) {
			templateOrderNext((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	protected void templateOrderNext(String username, int mealOrderId, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username); // Nos autenticamos
			MealOrder m = mealOrderService.findOne(mealOrderId);
			mealOrderService.step(m);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

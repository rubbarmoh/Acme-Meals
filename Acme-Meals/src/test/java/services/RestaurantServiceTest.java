
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
import domain.Restaurant;
import forms.RestaurantForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class RestaurantServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private RestaurantService			restaurantService;

	// Other services

	

	// Tests --------------------------------------------------

	/*
	 * Manage his/her restaurants, which includes creating, listing, editing and deleting them.
	 * 
	 * Vamos a probar a crear restaurantes 
	 */
	@Test
	public void driverCreate() throws ParseException {
		Object testingData[][] = {
				
			{//Manager con todos los datos correctos
				"manager1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla n� 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg",
				3.5, true, 1.0, 10.0, false, null},
			{//Manager con todos los datos correctos
				"manager1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla n� 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg",
				3.5, false, null, null, false, null},
			{// Intentamos crear un restaurante siendo un usuario
				"user1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla n� 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg",
				3.5, false, null, null, false, IllegalArgumentException.class},
			{// Intentamos crear un restaurante sin autenticar
				null, "Restaurante", "954632145", "Sevilla", "C/ Sevilla n� 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg",
				3.5, false, null, null, false, IllegalArgumentException.class},
		};
		
		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (Double) testingData[i][7],
				(Boolean) testingData[i][8], (Double) testingData[i][9], (Double) testingData[i][10], (Boolean) testingData[i][11], (Class<?>) testingData[i][12]);
	}
	

	protected void templateCreate(String username, String name, String phone, String city, String address, String email,
			String picture, Double avgStars, Boolean deliveryService, Double costDelivery,  Double minimunAmount, Boolean erased, Class<?> expected) throws ParseException {
	
		Class<?> caught = null;
		
		try {
			authenticate(username); // Iniciamos sesi�n con el usuario
			
			RestaurantForm restaurantForm;
			restaurantForm = restaurantService.generateForm();
			
			restaurantForm.setName(name);
			restaurantForm.setAddress(address);
			restaurantForm.setCity(city);
			restaurantForm.setEmail(email);
			restaurantForm.setDeliveryService(deliveryService);
			restaurantForm.setCostDelivery(costDelivery);
			restaurantForm.setErased(erased);
			restaurantForm.setPicture(picture);
			restaurantForm.setMinimunAmount(minimunAmount);
			restaurantForm.setPhone(phone);
			
			Restaurant restaurant = restaurantService.reconstruct(restaurantForm, null);
			Assert.isTrue(restaurant != null);
			restaurantService.save(restaurant);

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

		/*
		 * Manage his/her restaurants, which includes creating, listing, editing and deleting them.
		 * 
		 * Vamos a probar a listar los restaurantes
		 */
		@Test
		public void driverList() throws ParseException {
			Object testingData[][] = {
					
				{// Listar restaurantes propios siendo manager1
					"manager1", null},
				{// Listar restaurantes propios siendo manager2
					"manager2", null},
				{// Listar los restaurantes propios siendo un usuario
					"user1", IllegalArgumentException.class},
				{// Listar los restaurantes propios sin autenticar
					null, IllegalArgumentException.class},
			};
			
			for (int i = 0; i < testingData.length; i++)
				templateList((String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
		

		protected void templateList(String username, Class<?> expected) throws ParseException {
		
			Class<?> caught = null;
			
			try {
				authenticate(username); // Iniciamos sesi�n con el usuario
				
				Collection<Restaurant> restaurants = restaurantService.findByPrincipal();
				Assert.isTrue(!restaurants.isEmpty());

				unauthenticate();
			} catch (Throwable oops) {
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
		}

	// Tests --------------------------------------------------

			/*
			 * Manage his/her restaurants, which includes creating, listing, editing and deleting them.
			 * 
			 * Vamos a probar a editar los restaurantes
			 */
			@Test
			public void driverEdit() throws ParseException {
				Object testingData[][] = {
						
					{// Listar restaurantes propios siendo manager1
						"manager1", null},
					{// Listar restaurantes propios siendo manager2
						"manager2", null},
					{// Listar los restaurantes propios siendo un usuario
						"user1", IllegalArgumentException.class},
					{// Listar los restaurantes propios sin autenticar
						null, IllegalArgumentException.class},
				};
				
				for (int i = 0; i < testingData.length; i++)
					templateEdit((String) testingData[i][0], (Class<?>) testingData[i][1]);
			}
			

			protected void templateEdit(String username, Class<?> expected) throws ParseException {
			
				Class<?> caught = null;
				
				try {
					authenticate(username); // Iniciamos sesi�n con el usuario
					
					Collection<Restaurant> restaurants = restaurantService.findByPrincipal();
					Assert.isTrue(!restaurants.isEmpty());

					unauthenticate();
				} catch (Throwable oops) {
					caught = oops.getClass();
				}
				checkExceptions(expected, caught);
			}

}

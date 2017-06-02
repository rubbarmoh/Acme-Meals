
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
	private RestaurantService	restaurantService;


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
				"manager1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, true, 1.0, 10.0, false, null}, 
			{//Manager con todos los datos correctos con telefono +34
				"manager1", "Restaurante", "+34 954632145", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, true, 1.0, 10.0, false, null}, 
			{//Manager con todos los datos correctos sin deliverable
				"manager1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, false, null, null, false, null}, 
			{//Manager con todos los datos correctos con deliverable y sin precio mínimo
				"manager1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, true, 1.0, 0.0, false, null}, 
			{// Intentamos registrar un restaurante con telefono inváldo
				"manager1", "Restaurante", "12", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, true, 1.0, 10.0, false,NullPointerException.class}, 
			{// Intentamos registrar un restaurante sin direccion
				"manager1", "Restaurante", "954632145", "Sevilla", "", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, true, 1.0, 10.0, false, NullPointerException.class}, 
			{// Intentamos registrar un restaurante con correo inválido
				"manager1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla nº 19", "correo.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, true, 1.0, 10.0, false, NullPointerException.class}, 
			{// Intentamos crear un restaurante siendo un usuario
				"user1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, false, null, null, false,IllegalArgumentException.class}, 
			{// Intentamos crear un restaurante sin autenticar
				null, "Restaurante", "954632145", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, false, null, null, false,IllegalArgumentException.class},
			{// Intentamos crear un restaurante siendo un admin
				"admin", "Restaurante", "954632145", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, false, null, null, false,IllegalArgumentException.class},
			{// Intentamos crear un restaurante siendo un critic
				"critic1", "Restaurante", "954632145", "Sevilla", "C/ Sevilla nº 19", "correo@gmail.com", "http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg", 3.5, false, null, null, false,IllegalArgumentException.class},

		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (Double) testingData[i][7],
				(Boolean) testingData[i][8], (Double) testingData[i][9], (Double) testingData[i][10], (Boolean) testingData[i][11], (Class<?>) testingData[i][12]);
	}

	protected void templateCreate(String username, String name, String phone, String city, String address, String email, String picture, Double avgStars, Boolean deliveryService, Double costDelivery, Double minimunAmount, Boolean erased, Class<?> expected)
		throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario

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
				"manager1", null
			}, {// Listar restaurantes propios siendo manager2
				"manager2", null
			}, {// Listar los restaurantes propios siendo un usuario
				"user1", IllegalArgumentException.class
			}, {// Listar los restaurantes propios sin autenticar
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

			{// Editar un restaurante propio
				"manager1", 106, "Prueba", null
			}, {// Editar un restaurante siendo manager, pero no su propietario
				"manager2", 106, "Prueba", IllegalArgumentException.class
			}, {// Editar un restaurante siendo usuario
				"user1", 106, "Prueba", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateEdit((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateEdit(String username, int restaurantId, String nombre, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario
			Restaurant s;
			Restaurant r = restaurantService.findOne(restaurantId);
			r.setName(nombre);
			Assert.isTrue(restaurantService.check(r) == true);
			s = restaurantService.save(r);
			Assert.isTrue(s != null);
			Assert.isTrue(s.getName().equals(nombre));

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

	/*
	 * Manage his/her restaurants, which includes creating, listing, editing and disabled them.
	 * 
	 * Vamos a probar a deshabilitar los restaurantes
	 */
	@Test
	public void driverDisabled() throws ParseException {
		Object testingData[][] = {

			{// Listar restaurantes propios siendo manager1
				"manager1", 106, null
			}, {// Listar restaurantes propios siendo manager2
				"manager2", 106, IllegalArgumentException.class
			}, {// Listar los restaurantes propios siendo un usuario
				"user1", 106, IllegalArgumentException.class
			}, {// Listar los restaurantes propios sin autenticar
				null, 106, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateDisabled((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDisabled(String username, int restaurantId, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario
			Restaurant r = restaurantService.findOne(restaurantId);
			restaurantService.delete(r);
			Restaurant s = restaurantService.findOne(restaurantId);
			Assert.isTrue(s != null);
			Assert.isTrue(s.getErased());

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

	/*
	 * Search restaurants by a keyword. This keyword must appear in their names,
	 * categories or addresses.
	 */
	@Test
	public void driverSearch() throws ParseException {
		Object testingData[][] = {

			{	// Buscar una palabra que no existe
				"5000", IllegalArgumentException.class
			}, {// Buscar una palabra que no existe
				"No es una categoria", IllegalArgumentException.class
			}, {
				"Cadiz", null
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateSearch((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateSearch(String key, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			Assert.notNull(key);
			Collection<Restaurant> rs = restaurantService.findByKey(key);
			Assert.notEmpty(rs);

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

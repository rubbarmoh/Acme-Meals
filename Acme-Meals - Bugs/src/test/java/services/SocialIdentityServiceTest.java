
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
import domain.Manager;
import domain.SocialIdentity;
import forms.SocialIdentityForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SocialIdentityServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private SocialIdentityService	socialIdentityService;

	// Other services

	@Autowired
	private ManagerService			managerService;


	// Tests --------------------------------------------------

	/*
	 * Create a social identity
	 * 
	 * Crear
	 */
	@Test
	public void driverCreate() throws ParseException {
		Object testingData[][] = {

			{//Critico que crea review bien
				"manager1", 108, "https://twitter.com", "nick123", "https://imagen.com", "Twitter", null
			}, {//Critico que crea review con fallos
				"user1", 108, "https://twitter.com", "nick123", "https://imagen.com", "Twitter", IllegalArgumentException.class
			}, {// usuario no autenticado intentando crear review
				null, 108, "https://twitter.com", "nick123", "https://imagen.com", "Twitter", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	protected void templateCreate(String username, int restId, String link, String nick, String picture, String socialNetwork, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario

			SocialIdentityForm socialIdentityForm;
			socialIdentityForm = socialIdentityService.generateForm(restId);

			socialIdentityForm.setLink(link);
			socialIdentityForm.setNick(nick);
			socialIdentityForm.setPicture(picture);
			socialIdentityForm.setSocialNetwork(socialNetwork);

			SocialIdentity socialIdentity = socialIdentityService.reconstruct(socialIdentityForm, null);
			Assert.isTrue(socialIdentity != null);
			socialIdentityService.save(socialIdentity);

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

	/*
	 * List social identities
	 * 
	 * Listar
	 */
	@Test
	public void driverList() throws ParseException {
		Object testingData[][] = {

			{// Listar social identities propias
				"manager1", null
			}, {// Listar social identities como usuario
				"user1", IllegalArgumentException.class
			}, {// Listar social identities como usuario no autenticado
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
			Manager manager = managerService.findByPrincipal();
			Collection<SocialIdentity> socialIdentities = socialIdentityService.findByRestaurant(manager);
			Assert.notEmpty(socialIdentities);

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

	/*
	 * Edit social identity
	 * 
	 * Editar
	 */
	@Test
	public void driverEdit() throws ParseException {
		Object testingData[][] = {

			{// Editar un social identity propio
				"manager1", 118, "Prueba", null
			}, {// Editar un social identity siendo user
				"user1", 118, "Prueba", IllegalArgumentException.class
			}, {// Editar un social identity siendo user sin autenticar
				null, 118, "Prueba", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateEdit((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateEdit(String username, int siId, String text, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario
			SocialIdentity s = socialIdentityService.findOne(siId);
			s.setNick(text);
			s = socialIdentityService.save(s);
			Assert.notNull(s);

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	/*
	 * Delete a social identity
	 */
	@Test
	public void driverDelete() {
		Object testingData[][] = {
			{
				null, 118, IllegalArgumentException.class
			}, // Intentamos borrar como usuario no autenticado
			{
				"user1", 118, IllegalArgumentException.class
			}, // Intentamos borrar como usuario

			{
				"manager1", 118, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateDelete((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	protected void templateDelete(String username, int id, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username); // Nos autenticamos
			SocialIdentity socialIdentity = socialIdentityService.findOne(id);
			Assert.notNull(socialIdentity);
			socialIdentityService.delete(socialIdentity);
			Assert.isTrue(!socialIdentityService.findAll().contains(socialIdentity));
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

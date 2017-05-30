
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
import domain.Review;
import forms.ReviewForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ReviewServiceTest extends AbstractTest {

	// The SUT -----------------------------------------------

	@Autowired
	private ReviewService	reviewService;


	// Other services

	// Tests --------------------------------------------------

	/*
	 * Manage his or her reviews this include creating, editing and listing them.
	 * 
	 * Crear
	 */
	@Test
	public void driverCreate() throws ParseException {
		Object testingData[][] = {

			{//Critico que crea review bien
				"critic2", 4, 107, "Review al restaurante", "Review", null
			}, {//Critico que crea review con fallos
				"critic2", 4, 107, "", "Review", NullPointerException.class
			}, {// usuario no autenticado intentando crear review
				null, 4, 107, "Review al restaurante", "Review", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (Integer) testingData[i][1], (int) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	protected void templateCreate(String username, Integer rate, int restId, String text, String title, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario

			ReviewForm reviewForm;
			reviewForm = reviewService.generateForm();

			reviewForm.setRate(rate);
			reviewForm.setRestId(restId);
			reviewForm.setText(text);
			reviewForm.setTitle(title);

			Review review = reviewService.reconstruct(reviewForm, null);
			Assert.isTrue(review != null);
			reviewService.save(review);

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

	/*
	 * Manage his or her reviews this include creating, editing and listing them.
	 * 
	 * Listar
	 */
	@Test
	public void driverList() throws ParseException {
		Object testingData[][] = {

			{// Listar reviews propios siendo critic1
				"critic1", null
			}, {// Listar reviews propios siendo critic2
				"critic2", null
			}, {// Listar los reviews propios siendo un usuario
				"user1", IllegalArgumentException.class
			}, {// Listar los reviews propios sin autenticar
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

			Collection<Review> reviews = reviewService.findByPrincipal();
			Assert.isTrue(!reviews.isEmpty());

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	// Tests --------------------------------------------------

	/*
	 * Manage his/her reviews, which includes creating, listing, editing and deleting them.
	 * 
	 * Editar
	 */
	@Test
	public void driverEdit() throws ParseException {
		Object testingData[][] = {

			{// Editar un review propio
				"critic1", 121, "Prueba", null
			}, {// Editar un review siendo critic, pero no su propietario
				"critic2", 121, "Prueba", IllegalArgumentException.class
			}, {// Editar un review siendo usuario
				"user1", 121, "Prueba", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			templateEdit((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateEdit(String username, int reviewId, String text, Class<?> expected) throws ParseException {

		Class<?> caught = null;

		try {
			authenticate(username); // Iniciamos sesión con el usuario
			Review s;
			Review r = reviewService.findOne(reviewId);
			r.setText(text);
			Assert.isTrue(reviewService.check(r) == true);
			s = reviewService.save(r);
			Assert.isTrue(s != null);
			Assert.isTrue(s.getText().equals(text));

			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

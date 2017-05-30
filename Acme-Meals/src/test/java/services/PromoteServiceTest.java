
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Promote;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PromoteServiceTest extends AbstractTest {

	// The SUT ----------------------------------------------------------

	@Autowired
	private PromoteService	promoteService;
	
	@Autowired
	private RestaurantService	restaurantService;

	// Tests --------------------------------------------------

		/*
		 * Promote a restaurant
		 *
		 */
		@Test
		public void driverCreate() throws ParseException {
			Object testingData[][] = {
				{// Promote correcta
					"manager1", 106, "30/05/2018", "5/06/2018", null
				},
				{// Intentamos hacer promote con un restaurante que no pertenece al manager
					"manager2", 106, "30/05/2018", "5/06/2018", IllegalArgumentException.class
				},
				{// Intentamos hacer promote con una fecha pasada
					"manager1", 106, "30/05/2016", "5/06/2016", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				templateCreate((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
		}

		protected void templateCreate(String user, int restaurantId, String checkIn, String checkOut, Class<?> expected) throws ParseException {

			Class<?> caught = null;

			try {
				authenticate(user); // Nos autenticamos como usuario.
				Promote p = promoteService.create();
				SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
				p.setBeginning(fecha.parse(checkIn));
				p.setEnding(fecha.parse(checkOut));
				p.setRestaurant(restaurantService.findOne(restaurantId));
				Promote s = promoteService.save(p);
				Assert.isTrue(s!=null);
				unauthenticate();
			} catch (Throwable oops) {
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
		}


}

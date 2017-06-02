
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.RelationDislike;
import domain.Review;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class RelationDislikeServiceTest extends AbstractTest {

	// The SUT ----------------------------------------------------------

	@Autowired
	private RelationDislikeService	relationDislikeService;

	@Autowired
	private UserService				userService;

	@Autowired
	private ReviewService			reviewService;


	// Tests ------------------------------------------------------------

	/*
	 * Give Dislike to a review.
	 */
	@Test
	public void driverGiveDislike() {
		Object testingData[][] = {
			{
				null, 124, IllegalArgumentException.class
			}, // Damos like con usuario no autenticado
			{
				"admin", 124, IllegalArgumentException.class
			}, // Damos like con admin
			{
				"user1", 121, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateEditUser((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}
	protected void templateEditUser(String username, int id, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username); // Nos autenticamos
			User user = userService.findByPrincipal();
			Review review = reviewService.findOne(id);
			RelationDislike redislike = relationDislikeService.create();
			redislike.setReview(review);
			redislike.setUser(user);

			redislike = relationDislikeService.save(redislike);
			Assert.notNull(redislike);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}


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
import domain.Restaurant;
import domain.Review;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	// The SUT ----------------------------------------------------

	@Autowired
	private UserService			userService;

	@Autowired
	private RestaurantService	restaurantService;

	@Autowired
	private CriticService		criticService;

	@Autowired
	private ReviewService		reviewService;

	@Autowired
	private ManagerService		managerService;


	// Tests ------------------------------------------------------

	/*
	 * Test
	 * Dashboard Admin
	 */

	@Test
	public void driverDashboard() {
		Object testingData[][] = {
			{
				"admin", null
			}, {
				"user1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++) {
			templateDashboard((String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
	}

	protected void templateDashboard(String user, Class<?> expected) {
		Class<?> caught = null;
		try {
			authenticate(user); // Nos autenticamos como usuario.
			Collection<Double> minMaxAVGOrdersPerUser = userService.minMaxAVGOrdersPerUser();
			Collection<Restaurant> restaurantMoreOrders = restaurantService.restaurantMoreOrders();
			Collection<Restaurant> restaurantLessOrders = restaurantService.restaurantLessOrders();
			Double ratioRestaurantWithSocialIdentity = restaurantService.ratioRestaurantWithSocialIdentity();
			Collection<Double> minMaxAvgReviewsPerCritic = criticService.minMaxAvgReviewsPerCritic();
			Collection<Restaurant> restaurantWithMoreReviews = restaurantService.restaurantWithMoreReviews();
			Collection<Restaurant> restaurantWithLessReviews = restaurantService.restaurantWithLessReviews();
			Collection<Review> reviewMoreLikes = reviewService.reviewMoreLikes();
			Collection<Double> minMaxAvgMonthlyBillsPerManager = managerService.minMaxAvgMonthlyBillsPerManager();
			Double ratioRestaurantsPromoted = restaurantService.ratioRestaurantsPromoted();
			Collection<Restaurant> restaurantMoreStars = restaurantService.restaurantMoreStars();
			Collection<User> usersMorethan10PercentOrders = userService.usersMorethan10PercentOrders();
			Collection<User> usersLessthan10PercentOrders = userService.usersLessthan10PercentOrders();
			Collection<User> usersMoreThan10PercentComments = userService.usersMoreThan10PercentComments();
			Collection<User> usersLessThan10PercentComments = userService.usersLessThan10PercentComments();

			Assert.notNull(minMaxAVGOrdersPerUser);
			Assert.notNull(restaurantMoreOrders);
			Assert.notNull(restaurantLessOrders);
			Assert.notNull(ratioRestaurantWithSocialIdentity);
			Assert.notNull(minMaxAvgReviewsPerCritic);
			Assert.notNull(restaurantWithMoreReviews);
			Assert.notNull(restaurantWithLessReviews);
			Assert.notNull(reviewMoreLikes);
			Assert.notNull(minMaxAvgMonthlyBillsPerManager);
			Assert.notNull(ratioRestaurantsPromoted);
			Assert.notNull(restaurantMoreStars);
			Assert.notNull(usersMorethan10PercentOrders);
			Assert.notNull(usersLessthan10PercentOrders);
			Assert.notNull(usersMoreThan10PercentComments);
			Assert.notNull(usersLessThan10PercentComments);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

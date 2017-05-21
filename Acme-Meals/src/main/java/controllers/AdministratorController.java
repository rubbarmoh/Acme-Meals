/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CriticService;
import services.ManagerService;
import services.RestaurantService;
import services.ReviewService;
import services.UserService;
import domain.Restaurant;
import domain.Review;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

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


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Dashboard -----------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;

		Collection<Double> minMaxAVGOrdersPerUser = userService.minMaxAVGOrdersPerUser();
		Collection<Restaurant> restaurantMoreOrders = restaurantService.restaurantMoreOrders();
		Collection<Restaurant> restaurantLessOrders = restaurantService.restaurantLessOrders();
		Double ratioRestaurantWithSocialIdentity = restaurantService.ratioRestaurantWithSocialIdentity();
		Collection<Double> minMaxAvgReviewsPerCritic = criticService.minMaxAvgReviewsPerCritic();
		Collection<Restaurant> restaurantWithMoreReviews = restaurantService.restaurantWithMoreReviews();
		Collection<Restaurant> restaurantWithLessReviews = restaurantService.restaurantWithLessReviews();
		Collection<Review> reviewMoreLikes = reviewService.reviewMoreLikes();
		Collection<Double> minMaxAvgMonthlyBillsPerManager = managerService.minMaxAvgMonthlyBillsPerManager();

		result = new ModelAndView("administrator/dashboard");

		result.addObject("minMaxAVGOrdersPerUser", minMaxAVGOrdersPerUser);
		result.addObject("restaurantMoreOrders", restaurantMoreOrders);
		result.addObject("restaurantLessOrders", restaurantLessOrders);
		result.addObject("ratioRestaurantWithSocialIdentity", ratioRestaurantWithSocialIdentity);
		result.addObject("minMaxAvgReviewsPerCritic", minMaxAvgReviewsPerCritic);
		result.addObject("restaurantWithMoreReviews", restaurantWithMoreReviews);
		result.addObject("restaurantWithLessReviews", restaurantWithLessReviews);
		result.addObject("reviewMoreLikes", reviewMoreLikes);
		result.addObject("minMaxAvgMonthlyBillsPerManager", minMaxAvgMonthlyBillsPerManager);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

}

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Meal;
import domain.Restaurant;


import services.MealService;
import services.RestaurantService;


@Controller
@RequestMapping("/meal")
public class MealController extends AbstractController{
	// Services ------------------------------------------------

		@Autowired
		private MealService	mealService;
		
		@Autowired
		private RestaurantService	restaurantService;
	// Constructor ---------------------------------------------
		public MealController(){
			super();
		}
		//Browse-------------------------------------------------------
		
			@RequestMapping(value = "/browse", method = RequestMethod.GET)
			public ModelAndView browse(@RequestParam int restaurantId) {
				ModelAndView result;
				Collection<Meal>meals;
				Restaurant r=restaurantService.findOne(restaurantId);
				meals = mealService.mealPerRestaurant(r);

				result = new ModelAndView("meal/browse");
				result.addObject("meals", meals);
				result.addObject("requestURI", "meal/browse.do");

				return result;
			}
}

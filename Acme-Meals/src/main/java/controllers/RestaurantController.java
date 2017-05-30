
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.CommentService;
import services.MealService;
import services.RestaurantService;
import domain.Comment;
import domain.Meal;
import domain.Restaurant;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private RestaurantService	restaurantService;

	@Autowired
	private CommentService	commentService;

	@Autowired
	private MealService	mealService;

	// Constructors -----------------------------------------------------------

	public RestaurantController() {
		super();
	}

	// Display ----------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int restaurantId) {
		ModelAndView result;
		Restaurant restaurant;
		Collection<Meal> meals;
		
		restaurant = restaurantService.findOne(restaurantId);
		
		try{
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("MANAGER");
						
			if(userAccount.getAuthorities().contains(au) 
					&& restaurant.getManager().getUserAccount().getUsername().equals(LoginService.getPrincipal().getUsername())){
				meals = mealService.mealPerRestaurant(restaurant);
			}else{
				meals = mealService.mealAvailablePerRestaurant(restaurant);
			}
		}catch (Exception e) {
			meals = mealService.mealAvailablePerRestaurant(restaurant);
		}
		
	
		
		Collection<Comment> comments=commentService.findAllOrderByMoment(restaurant);
		result = new ModelAndView("restaurant/display");
		result.addObject("restaurant", restaurant);
		result.addObject("comments",comments);
		result.addObject("meals",meals);
		result.addObject("requestURI", "restaurant/display.do");

		return result;
	}
	
	//Browse-------------------------------------------------------
	
		@RequestMapping(value = "/browse", method = RequestMethod.GET)
		public ModelAndView browse() {
			ModelAndView result;
			Collection<Restaurant>restaurants;

			restaurants = restaurantService.findEnabledRestaurants();

			result = new ModelAndView("restaurant/browse");
			result.addObject("restaurants", restaurants);
			result.addObject("requestURI", "restaurant/browse.do");

			return result;
		}
		
	// Search ------------------------------------------------------
		
		@RequestMapping(value = "/search", method = RequestMethod.GET)
		public ModelAndView search(@RequestParam String key){
			ModelAndView result;
			String requestUri = "restaurant/search.do?key="+key;
			try{
				Collection<Restaurant> restaurants = restaurantService.findByKey(key);
				result = new ModelAndView("restaurant/browse");
				result.addObject("restaurants", restaurants);
				result.addObject("requestURI", requestUri);
			}catch(Throwable oops){
				result = browse();
			}
			return result;
		}

	//Ancillary Methods---------------------------

}

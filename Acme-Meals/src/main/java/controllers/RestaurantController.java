
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RestaurantService;
import domain.Comment;
import domain.Restaurant;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private RestaurantService	restaurantService;

	@Autowired
	private CommentService	commentService;


	// Constructors -----------------------------------------------------------

	public RestaurantController() {
		super();
	}

	// Display ----------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int restaurantId) {
		ModelAndView result;
		Restaurant restaurant;

		restaurant = restaurantService.findOne(restaurantId);
		Collection<Comment> comments=commentService.findAllOrderByMoment(restaurant);
		result = new ModelAndView("restaurant/display");
		result.addObject("restaurant", restaurant);
		result.addObject("comments",comments);
		result.addObject("requestURI", "restaurant/display.do");

		return result;
	}
	
	//Browse-------------------------------------------------------
	
		@RequestMapping(value = "/browse", method = RequestMethod.GET)
		public ModelAndView browse() {
			ModelAndView result;
			Collection<Restaurant>restaurants;

			restaurants = restaurantService.findAll();

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

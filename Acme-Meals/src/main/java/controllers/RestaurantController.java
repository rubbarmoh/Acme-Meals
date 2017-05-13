
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RestaurantService;
import domain.Restaurant;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private RestaurantService	restaurantService;


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

		result = new ModelAndView("restaurant/display");
		result.addObject("restaurant", restaurant);
		result.addObject("requestURI", "restaurant/display.do");

		return result;
	}

	//Ancillary Methods---------------------------

}


package controllers.manager;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RestaurantService;
import controllers.AbstractController;
import domain.Restaurant;
import forms.RestaurantForm;

@Controller
@RequestMapping("/managerActor/restaurant")
public class ManagerRestaurantController extends AbstractController {

	//Services-------------------------

	@Autowired
	private RestaurantService	restaurantService;


	//Constructor----------------------

	public ManagerRestaurantController() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Restaurant> restaurants;
		restaurants = restaurantService.findByPrincipal();

		result = new ModelAndView("restaurant/list");
		result.addObject("restaurants", restaurants);
		result.addObject("requestURI", "managerActor/restaurant/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		RestaurantForm restaurantForm;

		restaurantForm = restaurantService.generateForm();
		result = new ModelAndView("restaurant/create");
		result.addObject("restaurantForm", restaurantForm);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int restaurantId) {

		ModelAndView result;
		Restaurant restaurant;

		restaurant = restaurantService.findOne(restaurantId);
		RestaurantForm restaurantForm = restaurantService.transform(restaurant);
		Assert.notNull(restaurant);
		result = new ModelAndView("restaurant/edit");
		result.addObject("restaurantForm", restaurantForm);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid RestaurantForm restaurantForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();
		Restaurant restaurant;

		if (binding.hasErrors())
			if (restaurantForm.getId() != 0) {
				result = createEditModelAndView(restaurantForm, null);
			} else {
				result = createEditModelAndView2(restaurantForm, null);
			}
		else
			try {
				restaurant = restaurantService.reconstruct(restaurantForm, binding);

				restaurantService.save(restaurant);
				result = list();

			} catch (Throwable oops) {
				String msgCode = "restaurant.save.error";
				if (oops.getMessage().equals("nullCreditCard"))
					msgCode = "restaurant.nullCreditCard";
				else if (oops.getMessage().equals("badCreditCard"))
					msgCode = "restaurant.badCreditCard";

				if (restaurantForm.getId() != 0) {
					result = createEditModelAndView(restaurantForm, msgCode);
				} else {
					result = createEditModelAndView2(restaurantForm, msgCode);
				}
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(RestaurantForm restaurantForm, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = createEditModelAndView(restaurantForm, null);
		else
			try {
				Restaurant restaurant = restaurantService.reconstruct(restaurantForm, binding);
				restaurantService.delete(restaurant);
				result = list();
			} catch (Throwable oops) {
				String msgCode = "restaurant.save.error";

				if (oops.getMessage().equals("nullCreditCard"))
					msgCode = "restaurant.nullCreditCard";
				else if (oops.getMessage().equals("badCreditCard"))
					msgCode = "restaurant.badCreditCard";

				result = createEditModelAndView(restaurantForm, msgCode);
			}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(Restaurant restaurant, String message) {
		ModelAndView result;

		result = new ModelAndView("restaurant/edit");
		result.addObject("restaurant", restaurant);
		result.addObject("message", message);
		return result;

	}

	protected ModelAndView createEditModelAndView(RestaurantForm restaurant, String message) {
		ModelAndView result;

		result = new ModelAndView("restaurant/edit");
		result.addObject("restaurant", restaurant);

		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView2(RestaurantForm restaurant, String message) {
		ModelAndView result;

		result = new ModelAndView("restaurant/create");
		result.addObject("restaurant", restaurant);
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView(Restaurant restaurant) {
		ModelAndView result;

		result = createEditModelAndView(restaurant, null);

		return result;

	}

}

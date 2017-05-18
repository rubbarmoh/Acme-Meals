
package controllers;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import services.RestaurantService;
import domain.Manager;
import domain.Restaurant;
import forms.ManagerForm;

@Controller
@RequestMapping("/managerActor")
public class ManagerController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private RestaurantService	restaurantService;


	// Constructor -----------------------------------------------

	public ManagerController() {
		super();
	}

	// Dashboard -----------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;

		Manager manager = managerService.findByPrincipal();

		Map<Restaurant, Integer> ordersPerRestaurant = restaurantService.ordersPerRestaurant(manager);
		Collection<Restaurant> restaurants1 = ordersPerRestaurant.keySet();

		Collection<Restaurant> restaurantMoreStars = restaurantService.restaurantMoreStars(manager);
		Collection<Restaurant> restaurantLessStars = restaurantService.restaurantLessStars(manager);

		Double avgProfitMyRestaurants = restaurantService.avgProfitMyRestaurants(manager);
		Map<Restaurant, Double> profitByRestaurant = restaurantService.profitByRestaurant(manager);
		Collection<Restaurant> restaurants = profitByRestaurant.keySet();

		Collection<Restaurant> restaurantMoreProfit = restaurantService.restaurantMoreProfit(manager);
		Collection<Restaurant> restaurantsWithMore10PercentOrders = restaurantService.restaurantsWithMore10PercentOrders(manager);
		Collection<Restaurant> restaurantsWithLess10PercentOrders = restaurantService.restaurantsWithLess10PercentOrders(manager);

		result = new ModelAndView("manager/dashboard");

		result.addObject("ordersPerRestaurant", ordersPerRestaurant);
		result.addObject("restaurants1", restaurants1);

		result.addObject("restaurantMoreStars", restaurantMoreStars);
		result.addObject("restaurantLessStars", restaurantLessStars);
		result.addObject("avgProfitMyRestaurants", avgProfitMyRestaurants);

		result.addObject("profitByRestaurant", profitByRestaurant);
		result.addObject("restaurants", restaurants);

		result.addObject("restaurantMoreProfit", restaurantMoreProfit);
		result.addObject("restaurantsWithMore10PercentOrders", restaurantsWithMore10PercentOrders);
		result.addObject("restaurantsWithLess10PercentOrders", restaurantsWithLess10PercentOrders);

		result.addObject("requestURI", "managerActor/dashboard.do");

		return result;
	}

	// Edit profile ------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Manager manager = managerService.findByPrincipal();
		String tipe = "edit";

		ManagerForm managerForm = managerService.generateForm(manager);
		result = new ModelAndView("managerActor/edit");

		result.addObject("managerForm", managerForm);
		result.addObject("tipe", tipe);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ManagerForm managerForm, BindingResult binding) {
		ModelAndView result;
		Manager manager;

		if (binding.hasErrors())
			result = createEditModelAndView(managerForm);
		else
			try {
				manager = managerService.reconstructEditPersonalData(managerForm, binding);
				managerService.save2(manager);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "manager.register.error";

				if (oops.getMessage().equals("notEqualPassword"))
					msgCode = "manager.register.notEqualPassword";
				else if (oops.getMessage().equals("not18Old"))
					msgCode = "manager.register.not18Old";
				else if (oops.getMessage().equals("agreedNotAccepted"))
					msgCode = "manager.register.agreedNotAccepted";
				else if (oops.getMessage().equals("badCreditCard"))
					msgCode = "manager.register.badCreditCard";

				result = createEditModelAndView(managerForm, msgCode);
			}

		return result;
	}

	// Ancillary methods ---------------------------------------------------

	protected ModelAndView createEditModelAndView(ManagerForm managerForm) {
		ModelAndView result;

		result = createEditModelAndView(managerForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(ManagerForm managerForm, String message) {
		ModelAndView result;

		result = new ModelAndView("managerActor/edit");
		result.addObject("managerForm", managerForm);
		result.addObject("message", message);

		return result;
	}

}

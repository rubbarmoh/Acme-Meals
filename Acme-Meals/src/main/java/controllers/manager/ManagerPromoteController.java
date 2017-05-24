
package controllers.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PromoteService;
import services.RestaurantService;
import controllers.AbstractController;
import domain.Promote;
import domain.Restaurant;

@Controller
@RequestMapping("/managerActor/promote")
public class ManagerPromoteController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private PromoteService		promoteService;

	@Autowired
	private RestaurantService	restaurantService;


	// Constructors -----------------------------------------------------------

	public ManagerPromoteController() {
		super();
	}

	// Generate ----------------------------------

	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public ModelAndView generate() {
		ModelAndView result;
		Promote promote = promoteService.create();

		result = new ModelAndView("promote/generate");
		result.addObject("restaurants", getRestaurants());
		result.addObject("promote", promote);
		result.addObject("requestURI", "managerActor/promote/generate.do");

		return result;
	}

	@RequestMapping(value = "/generate", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Promote promote, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(promote);
		} else {
			try {
				promoteService.save(promote);
				result = list();
			} catch (Throwable oops) {
				String msg = "Error";
				result = createEditModelAndView(promote, msg);
			}
		}

		return result;
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Promote> promotes = promoteService.findByPrincipal();
		Collection<Promote> promotesActiveP = promoteService.promotesActivePrincipal(promotes);
		Collection<Promote> promotesNActiveP = promoteService.promotesNActivePrincipal(promotes);
		result = new ModelAndView("promote/list");
		result.addObject("promotes", promotes);
		result.addObject("promotesActiveP", promotesActiveP);
		result.addObject("promotesNActiveP", promotesNActiveP);
		result.addObject("requestURI", "managerActor/promote/list.do");

		return result;
	}

	private ModelAndView createEditModelAndView(Promote promote, String msg) {
		ModelAndView result;

		result = new ModelAndView("promote/generate");
		result.addObject("restaurants", getRestaurants());
		result.addObject("promote", promote);
		result.addObject("message", msg);
		return result;
	}

	protected ModelAndView createEditModelAndView(Promote promote) {
		ModelAndView result;

		result = createEditModelAndView(promote, null);

		return result;
	}

	private Map<Integer, String> getRestaurants() {

		Collection<Restaurant> rs;
		rs = restaurantService.findByPrincipal();
		Collection<Promote> promotes = promoteService.findByPrincipal();
		Collection<Promote> promotesActiveP = promoteService.promotesActivePrincipal(promotes);
		Collection<Promote> def = new ArrayList<Promote>();
		def.addAll(promotes);
		def.removeAll(promotesActiveP);

		Map<Integer, String> restaurants = new HashMap<Integer, String>();
		for (Restaurant r : rs) {
			for (Promote p : def) {
				if (p.getRestaurant() == r || r.getPromotes().isEmpty()) {
					restaurants.put(r.getId(), r.getName());
				}
			}
		}
		return restaurants;
	}

}

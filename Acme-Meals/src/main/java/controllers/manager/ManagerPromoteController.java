
package controllers.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
		Date date = new Date(System.currentTimeMillis() - 86400000);

		if (binding.hasErrors()) {
			result = createEditModelAndView(promote);
		} else {
			try {
				Assert.isTrue(!(promote.getBeginning().equals(promote.getEnding())), "promoteoneday");
				Assert.isTrue(promote.getBeginning().before(promote.getEnding()), "promotedatecorrectTime");
				Assert.isTrue(promote.getBeginning().after(date), "promoteafternow");
				Assert.notNull(promote.getRestaurant(), "promoterestaurantavailable");

				promoteService.save(promote);
				result = list();
			} catch (Throwable oops) {
				String msg = "promote.save.error";
				if (oops.getMessage().equals("promotedatecorrectTime")) {
					msg = "promote.date.correctTime";
				} else if (oops.getMessage().equals("promoteoneday")) {
					msg = "promote.date.oneday";
				} else if (oops.getMessage().equals("promoteafternow")) {
					msg = "promote.date.afternow";
				} else if (oops.getMessage().equals("promoterestaurantavailable")) {
					msg = "promote.restaurant.available";
				}
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
		Collection<Promote> promotesNActiveP = promoteService.promotesActivePrincipal(promotes);
		Collection<Promote> def = new ArrayList<Promote>();
		for (Promote p : promotes) {
			if (!promotesActiveP.contains(p) && promotesNActiveP.contains(p)) {
				def.add(p);
			}
		}
		Map<Integer, String> restaurants = new HashMap<Integer, String>();
		for (Restaurant r : rs) {
			if (def.isEmpty()) {
				if (r.getPromotes().isEmpty()) {
					restaurants.put(r.getId(), r.getName());
				}
			} else {
				for (Promote p : def) {
					if (p.getRestaurant() == r || r.getPromotes().isEmpty()) {
						restaurants.put(r.getId(), r.getName());
					}
				}
			}

		}
		return restaurants;
	}

}

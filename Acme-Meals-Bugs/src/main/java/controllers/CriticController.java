
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CriticService;
import domain.Critic;

@Controller
@RequestMapping("/critic")
public class CriticController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private CriticService	criticService;


	// Constructor -----------------------------------------------

	public CriticController() {
		super();
	}

	//display ----

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int criticId) {
		ModelAndView result;
		Critic critic;

		critic = criticService.findOne(criticId);
		result = new ModelAndView("critic/display");
		result.addObject("critic", critic);
		result.addObject("requestURI", "critic/display.do");

		return result;
	}

}

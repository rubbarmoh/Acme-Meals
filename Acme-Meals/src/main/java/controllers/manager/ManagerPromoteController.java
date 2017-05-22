
package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PromoteService;
import controllers.AbstractController;
import domain.Promote;

@Controller
@RequestMapping("/managerActor/promote")
public class ManagerPromoteController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private PromoteService	promoteService;


	// Constructors -----------------------------------------------------------

	public ManagerPromoteController() {
		super();
	}

	// Generate ----------------------------------

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Promote> promotes;
		promotes = promoteService.findByPrincipal();

		result = new ModelAndView("promote/list");
		result.addObject("promotes", promotes);
		result.addObject("requestURI", "managerActor/promote/list.do");

		return result;
	}

}


package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MonthlyBillService;
import controllers.AbstractController;
import domain.MonthlyBill;

@Controller
@RequestMapping("/managerActor/monthlyBill")
public class ManagerMonthlyBillController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private MonthlyBillService	monthlyBillService;


	// Constructors -----------------------------------------------------------

	public ManagerMonthlyBillController() {
		super();
	}

	// Display ----------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int monthlyBillId) {
		ModelAndView result;
		MonthlyBill monthlyBill;

		monthlyBill = monthlyBillService.findOne(monthlyBillId);

		result = new ModelAndView("monthlyBill/display");
		result.addObject("monthlyBill", monthlyBill);
		result.addObject("requestURI", "managerActor/monthlyBill/display.do");

		return result;
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<MonthlyBill> monthlyBills;
		monthlyBills = monthlyBillService.findByPrincipal();

		result = new ModelAndView("monthlyBill/list");
		result.addObject("monthlyBills", monthlyBills);
		result.addObject("requestURI", "managerActor/monthlyBill/list.do");

		return result;
	}

}

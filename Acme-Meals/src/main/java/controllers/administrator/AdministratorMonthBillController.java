
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MonthlyBillService;
import controllers.AbstractController;

@Controller
@RequestMapping("/administrator/monthlyBill")
public class AdministratorMonthBillController extends AbstractController {

	//Services-------------------------

	@Autowired
	private MonthlyBillService	monthlyBillService;


	//Constructor----------------------

	public AdministratorMonthBillController() {
		super();
	}

	// Edit ----------------------------------------------------------------

	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public ModelAndView generate() {
		ModelAndView result;
		monthlyBillService.generateMonthlyBills();
		result = new ModelAndView("redirect:../../welcome/index.do");

		return result;
	}

}

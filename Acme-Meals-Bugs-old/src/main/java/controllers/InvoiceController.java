
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InvoiceService;
import domain.Invoice;

@Controller
@RequestMapping("/invoice")
public class InvoiceController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private InvoiceService	invoiceService;


	// Constructors -----------------------------------------------------------

	public InvoiceController() {
		super();
	}

	// Display ----------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int invoiceId) {
		ModelAndView result;
		Invoice invoice;

		invoice = invoiceService.findOne(invoiceId);

		result = new ModelAndView("invoice/display");
		result.addObject("invoice", invoice);
		result.addObject("requestURI", "invoice/display.do");

		return result;
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Invoice> invoices;
		invoices = invoiceService.findByPrincipal();

		result = new ModelAndView("invoice/list");
		result.addObject("invoices", invoices);
		result.addObject("requestURI", "invoice/list.do");

		return result;
	}

	//Ancillary Methods---------------------------

}

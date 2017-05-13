
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.VATNumberService;
import controllers.AbstractController;
import domain.VATNumber;
import forms.VATNumberForm;

@Controller
@RequestMapping("/administrator/vatNumber")
public class AdministratorVATNumberController extends AbstractController {

	//Services-------------------------

	@Autowired
	private VATNumberService	vatNumberService;


	//Constructor----------------------

	public AdministratorVATNumberController() {
		super();
	}

	// Edit ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		VATNumber vatNumber = vatNumberService.find();

		VATNumberForm vatNumberForm = vatNumberService.generateForm(vatNumber);

		result = new ModelAndView("vatNumber/edit");
		result.addObject("vatNumberForm", vatNumberForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid VATNumberForm vatNumberForm, BindingResult binding) {
		ModelAndView result;
		VATNumber vatNumber;

		if (binding.hasErrors()) {
			result = createEditModelAndView(vatNumberForm);
		} else {
			try {
				vatNumber = vatNumberService.reconstruct(vatNumberForm, binding);
				vatNumberService.save(vatNumber);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "vatNumber.edit.error";
				result = createEditModelAndView(vatNumberForm, msgCode);
			}
		}

		return result;
	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(VATNumberForm vatNumberForm) {
		ModelAndView result;

		result = createEditModelAndView(vatNumberForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(VATNumberForm vatNumberForm, String message) {
		ModelAndView result;

		result = new ModelAndView("vatNumber/edit");
		result.addObject("vatNumberForm", vatNumberForm);
		result.addObject("message", message);

		return result;

	}

}

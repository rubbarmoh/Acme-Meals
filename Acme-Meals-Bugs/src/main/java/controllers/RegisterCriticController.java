
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CriticService;
import domain.Critic;
import forms.CriticForm;

@Controller
@RequestMapping("/critic")
public class RegisterCriticController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private CriticService	criticService;


	// Constructor ------------------------------------------------

	public RegisterCriticController() {
		super();
	}

	// Register ----------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		CriticForm criticForm;

		criticForm = criticService.generate();

		result = new ModelAndView("critic/register");
		result.addObject("criticForm", criticForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CriticForm criticForm, BindingResult binding) {
		ModelAndView result;
		Critic critic;

		if (binding.hasErrors())
			result = createEditModelAndView(criticForm);
		else
			try {
				critic = criticService.reconstruct(criticForm, binding);
				criticService.save(critic);
				result = new ModelAndView("redirect:../welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "critic.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "critic.register.notEqualPassword";
				}
				result = createEditModelAndView(criticForm, msgCode);
			}

		return result;

	}

	// Ancillary methods ---------------------------------------------------

	protected ModelAndView createEditModelAndView(CriticForm criticForm) {
		ModelAndView result;

		result = createEditModelAndView(criticForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(CriticForm criticForm, String message) {
		ModelAndView result;

		result = new ModelAndView("critic/register");
		result.addObject("criticForm", criticForm);
		result.addObject("message", message);

		return result;
	}

}


package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class RegisterUserController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private UserService	userService;


	// Constructor ------------------------------------------------

	public RegisterUserController() {
		super();
	}

	// Register ----------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		UserForm userForm;

		userForm = userService.generate();

		result = new ModelAndView("user/register");
		result.addObject("userForm", userForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UserForm userForm, BindingResult binding) {
		ModelAndView result;
		User user;

		if (binding.hasErrors())
			result = createEditModelAndView(userForm);
		else
			try {
				user = userService.reconstruct(userForm, binding);
				userService.save(user);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				String msgCode = "user.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "user.register.notEqualPassword";
				} else if (oops.getMessage().equals("not18Old")) {
					msgCode = "user.register.not18Old";
				} else if (oops.getMessage().equals("agreedNotAccepted")) {
					msgCode = "user.register.agreedNotAccepted";
				} else if (oops.getMessage().equals("badCreditCard")) {
					msgCode = "user.register.badCreditCard";
				}
				result = createEditModelAndView(userForm, msgCode);
			}

		return result;

	}

	// Ancillary methods ---------------------------------------------------

	protected ModelAndView createEditModelAndView(UserForm userForm) {
		ModelAndView result;

		result = createEditModelAndView(userForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(UserForm userForm, String message) {
		ModelAndView result;

		result = new ModelAndView("user/register");
		result.addObject("userForm", userForm);
		result.addObject("message", message);

		return result;
	}

}


package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private UserService	userService;


	// Constructor -----------------------------------------------

	public UserController() {
		super();
	}

	// Edit profile ------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		User user = userService.findByPrincipal();
		String tipe = "edit";

		UserForm userForm = userService.generateForm(user);
		result = new ModelAndView("user/edit");

		result.addObject("userForm", userForm);
		result.addObject("tipe", tipe);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UserForm userForm, BindingResult binding) {
		ModelAndView result;
		User user;

		if (binding.hasErrors())
			result = createEditModelAndView(userForm);
		else
			try {
				user = userService.reconstructEditPersonalData(userForm, binding);
				userService.save2(user);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "user.register.error";

				if (oops.getMessage().equals("notEqualPassword"))
					msgCode = "user.register.notEqualPassword";
				else if (oops.getMessage().equals("not18Old"))
					msgCode = "user.register.not18Old";
				else if (oops.getMessage().equals("agreedNotAccepted"))
					msgCode = "user.register.agreedNotAccepted";
				else if (oops.getMessage().equals("badCreditCard"))
					msgCode = "user.register.badCreditCard";

				result = createEditModelAndView(userForm, msgCode);
			}

		return result;
	}
	//display ----

		@RequestMapping(value = "/displayById", method = RequestMethod.GET)
		public ModelAndView displayById(@RequestParam int userId) {
			ModelAndView result;
			User user;

			user = userService.findOne(userId);
			result = new ModelAndView("user/display");
			result.addObject("user", user);
			result.addObject("requestURI", "user/display.do");

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

		result = new ModelAndView("user/edit");
		result.addObject("userForm", userForm);
		result.addObject("message", message);

		return result;
	}

}

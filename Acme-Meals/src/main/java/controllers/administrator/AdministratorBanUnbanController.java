
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import services.VATNumberService;
import controllers.AbstractController;
import domain.Meal;
import domain.Restaurant;
import domain.User;
import domain.VATNumber;
import forms.VATNumberForm;

@Controller
@RequestMapping("/administrator/banUnban")
public class AdministratorBanUnbanController extends AbstractController {

	//Services-------------------------

	@Autowired
	private UserService	userService;


	//Constructor----------------------

	public AdministratorBanUnbanController() {
		super();
	}

	//Browse-------------------------------------------------------
	
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView browse() {
		ModelAndView result;
		Collection<User> users;
		users = userService.findReported();

		result = new ModelAndView("meal/browse");
		result.addObject("users", users);
		result.addObject("requestURI", "meal/browse.do");

		return result;
	}
	
	
	
	//BanUnban -----

		@RequestMapping(value = "/banUnban", method = RequestMethod.GET)
		public ModelAndView banUnban(@RequestParam int userId) {
			ModelAndView result;
			User user = userService.findOne(userId);
			try {
				userService.banUnban(user);
				result = browse();
			} catch (Throwable oops) {
				result = browse();
				result.addObject("message", "master-page.commit.error");
			}

			return result;
		}


}

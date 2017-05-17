
package controllers.administrator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import controllers.AbstractController;
import domain.User;

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
		Map<User,Long> map;
		map = userService.findReported();

		result = new ModelAndView("user/browseReported");
		result.addObject("users", map.keySet());
		result.addObject("num", map);
		result.addObject("requestURI", "user/browseReported.do");

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

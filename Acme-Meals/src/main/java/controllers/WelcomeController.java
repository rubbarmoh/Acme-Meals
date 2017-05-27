/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.PromoteService;
import services.ReviewService;
import services.UserService;
import domain.Restaurant;
import domain.Review;
import domain.User;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private UserService		userService;

	@Autowired
	private PromoteService	promoteService;

	@Autowired
	private ReviewService	reviewService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "John Doe") final String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		User user = null;
		UserAccount userAccount = null;
		try {
			user = userService.findByPrincipal();
			userAccount = LoginService.getPrincipal();
		} catch (Exception e) {
		}

		Restaurant r = null;
		Review re = null;

		if (user != null || userAccount == null) {
			r = promoteService.findRandom();
			re = reviewService.findRandom();
		}

		if (user != null && user.getBanned() == true) {
			//result = new ModelAndView("redirect:/j_spring_security_logout");
			result = new ModelAndView("welcome/index");
			result.addObject("banned", true);

		} else {
			result = new ModelAndView("welcome/index");
			result.addObject("name", name);
			result.addObject("moment", moment);
			result.addObject("restaurant", r);
			result.addObject("review", re);
		}

		return result;
	}
}

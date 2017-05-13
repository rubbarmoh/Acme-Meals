
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ReviewService;
import domain.Review;

@Controller
@RequestMapping("/review")
public class ReviewController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private ReviewService	reviewService;


	// Constructors -----------------------------------------------------------

	public ReviewController() {
		super();
	}

	// Browse ---------------------------------------------------------------		

	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView browse() {
		ModelAndView result;

		Collection<Review> reviews = reviewService.findAll();

		result = new ModelAndView("review/browse");
		result.addObject("reviews", reviews);
		result.addObject("requestURI", "review/browse.do");

		return result;
	}

}

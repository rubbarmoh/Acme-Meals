
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReviewService;
import domain.Review;

@Controller
@RequestMapping("/review")
public class ReviewController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private ReviewService		reviewService;

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

	// Display ----------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int reviewId) {
		ModelAndView result;
		Review review = reviewService.findOne(reviewId);
		Integer like, dislike;
		like = review.getRelationLikes().size();
		dislike = review.getRelationDislikes().size();

		result = new ModelAndView("review/display");
		result.addObject("review", review);
		result.addObject("like", like);
		result.addObject("dislike", dislike);
		result.addObject("requestURI", "review/display.do");

		return result;
	}

}

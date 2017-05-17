
package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RelationDislikeService;
import services.RelationLikeService;
import services.ReviewService;
import services.UserService;
import domain.RelationDislike;
import domain.RelationLike;
import domain.Review;
import domain.User;

@Controller
@RequestMapping("user/review/dislike")
public class ReviewDislikeController {

	//Services--------------------------------------------------

	@Autowired
	private UserService				userService;

	@Autowired
	private RelationLikeService		relationLikeService;

	@Autowired
	private RelationDislikeService	relationDislikeService;

	@Autowired
	private ReviewService			reviewService;


	//Constructor-----------------------------------------------

	public ReviewDislikeController() {
		super();
	}

	// Like ---------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int reviewId) {
		ModelAndView result;
		RelationLike relationLike;
		User user;
		RelationDislike relationDislike;
		Review review;

		review = reviewService.findOne(reviewId);

		user = userService.findByPrincipal();

		relationDislike = relationDislikeService.create();
		relationDislike.setReview(review);
		relationDislike.setUser(user);

		relationDislikeService.save(relationDislike);

		relationLike = relationLikeService.findRelationLike(review);

		if (relationLike != null)
			relationLikeService.delete(relationLike);
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

	// UnLike ---------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int reviewId) {
		ModelAndView result;
		RelationDislike relationDislike;
		Review review;

		review = reviewService.findOne(reviewId);

		relationDislike = relationDislikeService.findRelationDislike(review);

		if (relationDislike != null)
			relationDislikeService.delete(relationDislike);
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


package controllers.critic;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RestaurantService;
import services.ReviewService;
import controllers.AbstractController;
import domain.Restaurant;
import domain.Review;
import forms.ReviewForm;

@Controller
@RequestMapping("/critic/review")
public class CriticReviewController extends AbstractController {

	//Services-------------------------

	@Autowired
	private ReviewService		reviewService;

	@Autowired
	private RestaurantService	restaurantService;


	//Constructor----------------------

	public CriticReviewController() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Review> reviews;
		reviews = reviewService.findByPrincipal();

		result = new ModelAndView("review/list");
		result.addObject("reviews", reviews);
		result.addObject("requestURI", "critic/review/list.do");

		return result;
	}

	//List--------------------------

	@RequestMapping(value = "/listLike", method = RequestMethod.GET)
	public ModelAndView listLike() {
		ModelAndView result;
		Collection<Review> reviews;
		Collection<Review> reviewsLike;
		reviews = reviewService.findByPrincipal();
		reviewsLike = reviewService.reviewCriticMoreLikes();
		reviewsLike.retainAll(reviews);

		result = new ModelAndView("review/list");
		result.addObject("reviews", reviewsLike);
		result.addObject("requestURI", "critic/review/listLike.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int restaurantId) {

		ModelAndView result;
		ReviewForm reviewForm;

		Collection<Integer> stars = new ArrayList<Integer>();
		stars.add(1);
		stars.add(2);
		stars.add(3);
		stars.add(4);
		stars.add(5);

		reviewForm = reviewService.generateForm();
		reviewForm.setRestId(restaurantId);
		result = new ModelAndView("review/create");
		result.addObject("reviewForm", reviewForm);
		result.addObject("stars", stars);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int reviewId) {

		ModelAndView result;
		Review review;
		Collection<Integer> stars = new ArrayList<Integer>();
		stars.add(1);
		stars.add(2);
		stars.add(3);
		stars.add(4);
		stars.add(5);

		review = reviewService.findOne(reviewId);
		ReviewForm reviewForm = reviewService.transform(review);
		Assert.notNull(review);
		result = new ModelAndView("review/edit");
		result.addObject("stars", stars);
		result.addObject("reviewForm", reviewForm);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ReviewForm reviewForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();
		Review review;
		Review aux;

		if (binding.hasErrors())
			if (reviewForm.getId() != 0) {
				result = createEditModelAndView(reviewForm, null);
			} else {
				result = createEditModelAndView2(reviewForm, null);
			}
		else
			try {
				review = reviewService.reconstruct(reviewForm, binding);

				aux = reviewService.save(review);
				Restaurant r = aux.getRestaurant();
				restaurantService.updateAvgStarsR(r.getId());
				result = list();

			} catch (Throwable oops) {
				String msgCode = "review.save.error";

				if (reviewForm.getId() != 0) {
					result = createEditModelAndView(reviewForm, msgCode);
				} else {
					result = createEditModelAndView2(reviewForm, msgCode);
				}
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(ReviewForm reviewForm, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = createEditModelAndView(reviewForm, null);
		else
			try {
				Review review = reviewService.reconstruct(reviewForm, binding);
				reviewService.delete(review);
				result = list();
			} catch (Throwable oops) {
				String msgCode = "review.save.error";

				result = createEditModelAndView(reviewForm, msgCode);
			}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(Review review, String message) {
		ModelAndView result;
		Collection<Integer> stars = new ArrayList<Integer>();
		stars.add(1);
		stars.add(2);
		stars.add(3);
		stars.add(4);
		stars.add(5);
		result = new ModelAndView("review/edit");
		result.addObject("review", review);
		result.addObject("stars", stars);
		result.addObject("message", message);
		return result;

	}

	protected ModelAndView createEditModelAndView(ReviewForm review, String message) {
		ModelAndView result;
		Collection<Integer> stars = new ArrayList<Integer>();
		stars.add(1);
		stars.add(2);
		stars.add(3);
		stars.add(4);
		stars.add(5);

		result = new ModelAndView("review/edit");
		result.addObject("review", review);
		result.addObject("stars", stars);
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView2(ReviewForm review, String message) {
		ModelAndView result;
		Collection<Integer> stars = new ArrayList<Integer>();
		stars.add(1);
		stars.add(2);
		stars.add(3);
		stars.add(4);
		stars.add(5);
		result = new ModelAndView("review/create");
		result.addObject("review", review);

		result.addObject("stars", stars);
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView(Review review) {
		ModelAndView result;

		result = createEditModelAndView(review, null);

		return result;

	}

}

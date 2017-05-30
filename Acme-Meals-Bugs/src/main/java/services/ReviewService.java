
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ReviewRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Critic;
import domain.RelationDislike;
import domain.RelationLike;
import domain.Review;
import forms.ReviewForm;

@Service
@Transactional
public class ReviewService {

	//Managed repository--------------------------------
	@Autowired
	private ReviewRepository	reviewRepository;
	//Supporting Services-------------------------------

	@Autowired
	private Validator			validator;

	@Autowired
	private CriticService		criticService;

	@Autowired
	private RestaurantService	restaurantService;


	//Constructor---------------------------------------
	public ReviewService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	public Review create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au2 = new Authority();
		au2.setAuthority("CRITIC");
		Assert.isTrue(userAccount.getAuthorities().contains(au2));
		Critic critic = criticService.findByPrincipal();
		Review result;
		result = new Review();
		result.setCritic(critic);
		result.setRelationLikes(new ArrayList<RelationLike>());
		result.setRelationDislikes(new ArrayList<RelationDislike>());

		return result;
	}

	public Collection<Review> findAll() {

		Collection<Review> result;

		result = reviewRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Review findOne(int reviewId) {

		Review result;

		result = reviewRepository.findOne(reviewId);
		Assert.notNull(result);

		return result;
	}

	public Review save(Review review) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au2 = new Authority();
		au2.setAuthority("CRITIC");

		Assert.isTrue(userAccount.getAuthorities().contains(au2));

		Review result;
		result = reviewRepository.save(review);

		return result;
	}

	public void delete(Review review) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CRITIC");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(review);

		Assert.isTrue(review.getId() != 0);
		reviewRepository.delete(review);
	}
	// Other bussiness methods ----------------------------------------------------

	public List<Review> reviewMoreLikes() {
		List<Review> result = reviewRepository.reviewMoreLikes();
		return result;
	}

	public List<Review> reviewCriticMoreLikes() {
		List<Review> result = reviewRepository.reviewCriticMoreLikes();
		return result;
	}

	// Forms ----------------------------------------------------------

	public ReviewForm generateForm() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CRITIC");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		ReviewForm result;

		result = new ReviewForm();
		return result;
	}

	public Review reconstruct(ReviewForm reviewForm, BindingResult binding) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CRITIC");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Review result;

		if (reviewForm.getId() == 0)
			result = create();
		else
			result = findOne(reviewForm.getId());

		result.setId(reviewForm.getId());
		result.setRestaurant(restaurantService.findOne(reviewForm.getRestId()));
		result.setTitle(reviewForm.getTitle());
		result.setText(reviewForm.getText());
		result.setRate(reviewForm.getRate());

		validator.validate(result, binding);

		return result;
	}

	public ReviewForm transform(Review review) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CRITIC");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		ReviewForm result = generateForm();
		result.setId(review.getId());
		result.setTitle(review.getTitle());
		result.setText(review.getText());
		result.setRate(review.getRate());
		result.setRestId(review.getRestaurant().getId());

		return result;
	}

	public Collection<Review> findByPrincipal() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("CRITIC");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Review> result = new ArrayList<Review>();
		Critic critic = criticService.findByPrincipal();
		result = reviewRepository.reviewByCriticId(critic.getId());
		return result;
	}

	public Review findRandom() {
		Review r = new Review();
		Object[] ts = findAll().toArray();
		int min = 0;
		int max = ts.length;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max);
		r = (Review) ts[randomNum];
		return r;
	}

}

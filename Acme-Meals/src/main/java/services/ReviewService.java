package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReviewRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.RelationDislike;
import domain.RelationLike;
import domain.Review;

@Service
@Transactional
public class ReviewService {
	//Managed repository--------------------------------
		@Autowired
		private ReviewRepository reviewRepository;
		//Supporting Services-------------------------------
		
		//Constructor---------------------------------------
		public ReviewService(){
			super();
		}
		// Simple CRUD methods ----------------------------------------------------

		public Review create() {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au2 = new Authority();
			au2.setAuthority("CRITIC");
			Assert.isTrue(userAccount.getAuthorities().contains(au2));
			Review result;
			result = new Review();
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
	}


package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RestaurantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Meal;
import domain.MealOrder;
import domain.Promote;
import domain.Restaurant;
import domain.Review;
import domain.SocialIdentity;

@Service
@Transactional
public class RestaurantService {
	//Managed repository--------------------------------
	@Autowired
	private RestaurantRepository restaurantRepository;
	//Supporting Services-------------------------------
	
	//Constructor---------------------------------------
	public RestaurantService(){
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	public Restaurant create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au2));
		Restaurant result;
		result = new Restaurant();
		result.setComments(new ArrayList<Comment>());
		result.setMealOrders(new ArrayList<MealOrder>());
		result.setMeals(new ArrayList<Meal>());
		result.setReviews(new ArrayList<Review>());
		result.setPromotes(new ArrayList<Promote>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		return result;
	}

	public Collection<Restaurant> findAll() {

		Collection<Restaurant> result;

		result = restaurantRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Restaurant findOne(int restaurantId) {

		Restaurant result;

		result = restaurantRepository.findOne(restaurantId);
		Assert.notNull(result);

		return result;
	}

	public Restaurant save(Restaurant restaurant) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au2));

		Restaurant result;
		result = restaurantRepository.save(restaurant);

		return result;
	}

	public void delete(Restaurant restaurant) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(restaurant);

		Assert.isTrue(restaurant.getId() != 0);
		restaurantRepository.delete(restaurant);
	}
	// Other bussiness methods ----------------------------------------------------
}




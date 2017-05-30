
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RestaurantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Manager;
import domain.Meal;
import domain.MealOrder;
import domain.Promote;
import domain.Restaurant;
import domain.Review;
import domain.SocialIdentity;
import forms.RestaurantForm;

@Service
@Transactional
public class RestaurantService {

	//Managed repository--------------------------------
	@Autowired
	private RestaurantRepository	restaurantRepository;

	//Supporting Services-------------------------------

	@Autowired
	private Validator				validator;

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private MealService				mealService;


	//Constructor---------------------------------------
	public RestaurantService() {
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
		result.setAvgStars(0.0);
		result.setComments(new ArrayList<Comment>());
		result.setMealOrders(new ArrayList<MealOrder>());
		result.setMeals(new ArrayList<Meal>());
		result.setReviews(new ArrayList<Review>());
		result.setPromotes(new ArrayList<Promote>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		result.setErased(false);
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
		Authority au = new Authority();
		au.setAuthority("USER");
		Authority au3 = new Authority();
		au3.setAuthority("CRITIC");

		Assert.isTrue(userAccount.getAuthorities().contains(au2) || userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au3));
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
		Assert.isTrue(restaurant.getManager().getUserAccount().equals(userAccount));
		restaurant.setErased(true);
	}
	public void enable(Restaurant restaurant) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(restaurant);
		Assert.isTrue(restaurant.getId() != 0);
		restaurant.setErased(false);
	}

	// Other bussiness methods ----------------------------------------------------

	public Collection<Restaurant> findByPrincipal() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Restaurant> result = new ArrayList<Restaurant>();
		Manager manager = managerService.findByPrincipal();
		result = restaurantRepository.restaurantByManagerId(manager.getId());
		return result;
	}

	public Collection<Restaurant> findByKey(String key) {
		Collection<Restaurant> result = restaurantRepository.findByKey(key);
		Collection<Restaurant> restaurantsByCategory = mealService.searchByCategory(key);
		for (Restaurant r : restaurantsByCategory) {
			if (!result.contains(r)) {
				result.add(r);
			}
		}
		return result;
	}

	public Collection<Restaurant> restaurantByManagerId(int id) {
		Collection<Restaurant> result = restaurantRepository.restaurantByManagerId(id);
		return result;
	}

	public Map<Restaurant, Integer> ordersPerRestaurant(Manager manager) {
		Map<Restaurant, Integer> map = new HashMap<Restaurant, Integer>();
		List<Object[]> aux = restaurantRepository.ordersPerRestaurant(manager);
		for (Object[] o : aux) {
			map.put((Restaurant) o[0], (Integer) o[1]);
		}
		return map;
	}

	public List<Restaurant> restaurantMoreStars(Manager manager) {
		List<Restaurant> result = restaurantRepository.restaurantMoreStars(manager);
		return result;
	}

	public List<Restaurant> restaurantLessStars(Manager manager) {
		List<Restaurant> result = restaurantRepository.restaurantLessStars(manager);
		return result;
	}

	public Double avgProfitMyRestaurants(Manager manager) {
		Double result = restaurantRepository.avgProfitMyRestaurants(manager);
		return result;
	}

	public Map<Restaurant, Double> profitByRestaurant(Manager manager) {
		Map<Restaurant, Double> map = new HashMap<Restaurant, Double>();
		List<Object[]> aux = restaurantRepository.profitByRestaurant(manager);
		for (Object[] o : aux) {
			map.put((Restaurant) o[0], (Double) o[1]);
		}
		return map;
	}

	public List<Restaurant> restaurantMoreProfit(Manager manager) {
		List<Restaurant> result = new ArrayList<Restaurant>();
		Double aux = 0.;
		List<Object[]> obj = restaurantRepository.restaurantMoreProfit(manager);
		for (Object[] o : obj) {
			if ((Double) o[1] > aux) {
				result = new ArrayList<Restaurant>();
				result.add((Restaurant) o[0]);
				aux = (Double) o[1];
			} else if ((Double) o[1] == aux) {
				result.add((Restaurant) o[0]);
			}
		}

		return result;
	}

	public List<Restaurant> restaurantsWithMore10PercentOrders(Manager manager) {
		List<Restaurant> result = restaurantRepository.restaurantsWithMore10PercentOrders(manager);
		return result;
	}

	public List<Restaurant> restaurantsWithLess10PercentOrders(Manager manager) {
		List<Restaurant> result = restaurantRepository.restaurantsWithLess10PercentOrders(manager);
		return result;
	}

	public List<Restaurant> restaurantMoreOrders() {
		List<Restaurant> result = restaurantRepository.restaurantMoreOrders();
		return result;
	}

	public List<Restaurant> restaurantLessOrders() {
		List<Restaurant> result = restaurantRepository.restaurantLessOrders();
		return result;
	}

	public Double ratioRestaurantWithSocialIdentity() {
		Double result = restaurantRepository.ratioRestaurantWithSocialIdentity();
		return result;
	}

	public List<Restaurant> restaurantWithMoreReviews() {
		List<Restaurant> result = restaurantRepository.restaurantWithMoreReviews();
		return result;
	}

	public List<Restaurant> restaurantWithLessReviews() {
		List<Restaurant> result = restaurantRepository.restaurantWithLessReviews();
		return result;
	}

	public Double ratioRestaurantsPromoted() {
		Double result = restaurantRepository.ratioRestaurantsPromoted();
		return result;
	}

	public List<Restaurant> restaurantMoreStars() {
		List<Restaurant> result = restaurantRepository.restaurantMoreStars();
		return result;
	}
	// Forms ----------------------------------------------------------

	public RestaurantForm generateForm() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		RestaurantForm result;

		result = new RestaurantForm();
		return result;
	}

	public Restaurant reconstruct(RestaurantForm restaurantForm, BindingResult binding) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Manager manager = managerService.findByPrincipal();

		Restaurant result;

		if (restaurantForm.getId() == 0)
			result = create();
		else
			result = findOne(restaurantForm.getId());

		result.setId(restaurantForm.getId());
		result.setManager(manager);
		result.setName(restaurantForm.getName());
		result.setPhone(restaurantForm.getPhone());
		result.setCity(restaurantForm.getCity());
		result.setAddress(restaurantForm.getAddress());
		result.setEmail(restaurantForm.getEmail());
		result.setPicture(restaurantForm.getPicture());
		restaurantForm.setErased(false);
		result.setErased(restaurantForm.getErased());
		result.setDeliveryService(restaurantForm.getDeliveryService());
		if (restaurantForm.getDeliveryService()) {
			result.setCostDelivery(restaurantForm.getCostDelivery());
			result.setMinimunAmount(restaurantForm.getMinimunAmount());
		} else {
			result.setCostDelivery(null);
			result.setMinimunAmount(null);
		}

		validator.validate(result, binding);

		return result;
	}

	public RestaurantForm transform(Restaurant restaurant) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		RestaurantForm result = generateForm();
		result.setId(restaurant.getId());
		result.setName(restaurant.getName());
		result.setPhone(restaurant.getPhone());
		result.setCity(restaurant.getCity());
		result.setAddress(restaurant.getAddress());
		result.setEmail(restaurant.getEmail());
		result.setPicture(restaurant.getPicture());
		result.setDeliveryService(restaurant.getDeliveryService());
		result.setCostDelivery(restaurant.getCostDelivery());
		result.setMinimunAmount(restaurant.getMinimunAmount());
		result.setErased(restaurant.getErased());
		return result;
	}
	public void updateAvgStars(int restaurantId) {
		Restaurant r = restaurantRepository.findOne(restaurantId);
		r.setAvgStars(restaurantRepository.findAvgStars(restaurantId));
		save(r);
	}

	public void updateAvgStarsR(int restaurantId) {
		Restaurant r = restaurantRepository.findOne(restaurantId);
		r.setAvgStars(restaurantRepository.findAvgStarsR(restaurantId));
		save(r);
	}
	public Collection<Restaurant> findEnabledRestaurants() {
		Collection<Restaurant> result;
		result = restaurantRepository.findEnabledRestaurants();
		return result;
	}

	public Boolean check(Restaurant r) {

		Boolean result = false;

		Manager manager = managerService.findByPrincipal();

		if (r.getManager().equals(manager)) {
			result = true;
		}
		return result;
	}

}


package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MealRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Meal;
import domain.Quantity;
import domain.Restaurant;
import forms.MealForm;

@Service
@Transactional
public class MealService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private MealRepository		mealRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator			validator;

	@Autowired
	private RestaurantService	restaurantService;


	// Constructors -----------------------------------------------------------

	public MealService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Meal create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		ArrayList<Quantity> quantities = new ArrayList<Quantity>();

		Meal result;

		result = new Meal();
		result.setErased(false);
		result.setQuantities(quantities);
		return result;
	}

	public Collection<Meal> findAll() {
		Collection<Meal> result;

		result = mealRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Meal findOne(int mealId) {
		Assert.isTrue(mealId != 0);

		Meal result;

		result = mealRepository.findOne(mealId);
		Assert.notNull(result);

		return result;
	}

	public Meal save(Meal meal) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(meal);
		
		Assert.isTrue(userAccount.getAuthorities().contains(au) && 
				meal.getRestaurant().getManager().getUserAccount().getUsername().equals(userAccount.getUsername()));
			
		return mealRepository.save(meal);
	}

	public void disable(Meal meal) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(meal);
		Assert.isTrue(meal.getId() != 0);
		

		if(meal.getErased()){
			meal.setErased(false);
		}else{
			meal.setErased(true);
		}
		mealRepository.save(meal);
	}
	public Collection<Meal> mealPerRestaurant(Restaurant r) {
		Collection<Meal> result;
		result = mealRepository.mealsPerRestaurant(r.getId());
		return result;
	}
	
	public Collection<Meal> mealAvailablePerRestaurant(Restaurant r) {
		Collection<Meal> result;
		result = mealRepository.mealsAvailablePerRestaurant(r.getId());
		return result;
	}

	// Forms ----------------------------------------------------------

	public MealForm generateForm() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		MealForm result;

		result = new MealForm();
		return result;
	}

	public Meal reconstruct(MealForm mealForm, BindingResult binding) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Assert.isTrue(!mealForm.getPrice().isNaN());
		Restaurant restaurant = restaurantService.findOne(mealForm.getrId());

		Meal result;

		if (mealForm.getId() == 0)
			result = create();
		else
			result = findOne(mealForm.getId());

		result.setId(mealForm.getId());
		result.setRestaurant(restaurant);
		result.setTitle(mealForm.getTitle());
		result.setDescription(mealForm.getDescription());
		result.setPrice(mealForm.getPrice());
		result.setCategory(mealForm.getCategory());
		Assert.isTrue(result.getRestaurant().getManager().getUserAccount().getUsername().equals(userAccount.getUsername()));
		validator.validate(result, binding);

		return result;
	}

	public MealForm transform(Meal meal) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));
		MealForm result = generateForm();
		result.setrId(meal.getRestaurant().getId());
		result.setId(meal.getId());
		result.setTitle(meal.getTitle());
		result.setDescription(meal.getDescription());
		result.setPrice(meal.getPrice());
		result.setCategory(meal.getCategory());
		return result;
	}

	public Collection<Restaurant> searchByCategory(String name) {
		Collection<Meal> meals = mealRepository.mealsByCategory(name);
		Collection<Restaurant> result = new ArrayList<Restaurant>();
		;

		for (Meal m : meals) {
			result.add(m.getRestaurant());
		}

		return result;
	}

	public Collection<Meal> mealsByCategory(String name) {
		Collection<Meal> meals = mealRepository.mealsByCategory(name);

		return meals;
	}

}

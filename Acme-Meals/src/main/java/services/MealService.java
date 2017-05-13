package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MealRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Meal;
import domain.Quantity;

@Service
@Transactional
public class MealService {

	// Managed repository -----------------------------------------------------

			@Autowired
			private MealRepository	mealRepository;

			// Supporting services ----------------------------------------------------


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

				return mealRepository.save(meal);
			}

			public void delete(Meal meal) {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("MANAGER");
				Assert.isTrue(userAccount.getAuthorities().contains(au));

				Assert.notNull(meal);
				Assert.isTrue(meal.getId() != 0);
				Assert.isTrue(mealRepository.exists(meal.getId()));

				mealRepository.delete(meal);
			}
}
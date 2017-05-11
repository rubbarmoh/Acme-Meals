package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MealOrderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.MealOrder;
import domain.Quantity;

@Service
@Transactional
public class MealOrderService {
	// Managed repository -----------------------------------------------------

			@Autowired
			private MealOrderRepository	mealOrderRepository;

			// Supporting services ----------------------------------------------------


			// Constructors -----------------------------------------------------------

			public MealOrderService() {
				super();
			}

			// Simple CRUD methods ----------------------------------------------------

			public MealOrder create() {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("ADMIN");
				Assert.isTrue(userAccount.getAuthorities().contains(au));
				ArrayList<Quantity> quantities = new ArrayList<Quantity>();
				
				MealOrder result;

				result = new MealOrder();
				result.setQuantities(quantities);

				return result;
			}

			public Collection<MealOrder> findAll() {
				Collection<MealOrder> result;

				result = mealOrderRepository.findAll();
				return result;
			}

			public MealOrder findOne(int mealOrderId) {
				MealOrder result;
				result = mealOrderRepository.findOne(mealOrderId);
				return result;
			}

			public MealOrder save(MealOrder mealOrder) {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("USER");
				Authority au2 = new Authority();
				au.setAuthority("MANAGER");
				Assert.isTrue(userAccount.getAuthorities().contains(au)||
						userAccount.getAuthorities().contains(au2));

				Assert.notNull(mealOrder);

				return mealOrderRepository.save(mealOrder);
			}

			public void delete(MealOrder mealOrder) {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("USER");
				Assert.isTrue(userAccount.getAuthorities().contains(au));

				Assert.notNull(mealOrder);
				Assert.isTrue(mealOrder.getId() != 0);
				Assert.isTrue(mealOrderRepository.exists(mealOrder.getId()));

				mealOrderRepository.delete(mealOrder);
			}
}

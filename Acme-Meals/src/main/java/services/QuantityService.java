
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.QuantityRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Meal;
import domain.MealOrder;
import domain.Quantity;
import forms.QuantityForm;

@Service
@Transactional
public class QuantityService {

	//Managed Repository-------------------------------------------
	@Autowired
	private QuantityRepository	quantityRepository;

	//Supporting services------------------------------------------
	@Autowired
	private MealOrderService	mealOrderService;

	@Autowired
	private MealService			mealService;
	@Autowired
	private Validator			validator;


	//Constructor -------------------------------------------------
	public QuantityService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	public Quantity create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Quantity result;
		result = new Quantity();
		return result;
	}

	public Collection<Quantity> findAll() {

		Collection<Quantity> result;

		result = quantityRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Quantity findOne(int quantityId) {

		Quantity result;

		result = quantityRepository.findOne(quantityId);
		Assert.notNull(result);

		return result;
	}

	public Quantity save(Quantity quantity) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Quantity result;
		result = quantityRepository.save(quantity);

		return result;
	}

	public void delete(Quantity quantity) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(quantity);

		Assert.isTrue(quantity.getId() != 0);

		quantityRepository.delete(quantity);
	}
	// Other bussiness methods ----------------------------------------------------
	public QuantityForm generateForm() {
		QuantityForm quantityForm = new QuantityForm();
		return quantityForm;
	}
	public Quantity reconstruct(QuantityForm quantityForm, BindingResult binding) {
		Quantity result;
		result = create();
		MealOrder mo = mealOrderService.findOne(quantityForm.getMealOrderId());
		Meal m = mealService.findOne(quantityForm.getMealId());
		result.setMeal(m);
		result.setMealOrder(mo);
		result.setQuantity(quantityForm.getQuantity());
		validator.validate(result, binding);
		return result;
	}
}

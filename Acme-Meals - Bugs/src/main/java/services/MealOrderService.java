
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MealOrderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Manager;
import domain.MealOrder;
import domain.Quantity;
import domain.User;
import forms.MealOrderForm;

@Service
@Transactional
public class MealOrderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private MealOrderRepository	mealOrderRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService			userService;

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private QuantityService		quantityService;

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------------------------

	public MealOrderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public MealOrder create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		ArrayList<Quantity> quantities = new ArrayList<Quantity>();

		MealOrder result;

		result = new MealOrder();
		result.setQuantities(quantities);
		result.setStatus("DRAFT");

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
		au2.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));

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

		for (Quantity q : mealOrder.getQuantities()) {
			quantityService.delete(q);
		}
		if (mealOrder.getStatus().equals("DRAFT")) {
			mealOrderRepository.delete(mealOrder);
		}
	}

	// Other bussiness methods ----------------------------------------------

	public Collection<MealOrder> findByUser() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		User user = userService.findByPrincipal();

		Collection<MealOrder> mealOrders = mealOrderRepository.findByUser(user.getId());

		return mealOrders;

	}

	public Collection<MealOrder> findCurrentlyByUser() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		User user = userService.findByPrincipal();

		Collection<MealOrder> mealOrders = mealOrderRepository.findCurrentlyByUser(user.getId());

		return mealOrders;

	}

	public Collection<MealOrder> findByManager() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Manager manager = managerService.findByPrincipal();

		Collection<MealOrder> mealOrders = mealOrderRepository.findByManager(manager.getId());

		return mealOrders;

	}

	public Collection<MealOrder> findCurrentlyByManager() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Manager manager = managerService.findByPrincipal();

		Collection<MealOrder> mealOrders = mealOrderRepository.findCurrentlyByManager(manager.getId());

		return mealOrders;

	}
	public void updateAmount(int mealOrderId) {
		MealOrder m = mealOrderRepository.findOne(mealOrderId);
		Double aux = 0.0;
		for (Quantity q : m.getQuantities()) {
			aux = aux + (q.getQuantity() * q.getMeal().getPrice());
		}
		m.setAmount(aux);
		save(m);
	}
	public MealOrderForm generateForm() {
		MealOrderForm result = new MealOrderForm();
		return result;
	}
	public MealOrder reconstruct(MealOrderForm mealOrderForm, BindingResult binding) {
		MealOrder result;
		result = mealOrderRepository.findOne(mealOrderForm.getMealOrderId());
		result.setDeliveryAdress(mealOrderForm.getDeliveryAdress());
		if (mealOrderForm.getPickUp()) {
			result.setPickUp(true);
			Assert.isTrue((result.getDeliveryAdress().equals("")), "pickupMarked");
		} else {
			result.setPickUp(false);
			result.setDeliveryAdress(mealOrderForm.getDeliveryAdress());
			Assert.isTrue(!(result.getDeliveryAdress().equals("")), "adressNotValid");
			Assert.isTrue(result.getRestaurant().getDeliveryService(), "noDeliveryService");
		}

		validator.validate(result, binding);
		return result;
	}
	
	public void step(MealOrder mealOrder){
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Assert.isTrue(mealOrder.getRestaurant().getManager().getUserAccount().getUsername().equals(userAccount.getUsername()));
		Assert.isTrue(!mealOrder.getStatus().equals("DRAFT")&&!mealOrder.getStatus().equals("FINISHED"));		
		
		if(mealOrder.getStatus().equals("PENDING")){
			mealOrder.setStatus("INPROGRESS");
		}else{
			mealOrder.setStatus("FINISHED");
		}
		
		save(mealOrder);
	}
}

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.Quantity;

import repositories.QuantityRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class QuantityService {
	//Managed Repository-------------------------------------------
	@Autowired
	private QuantityRepository quantityRepository;
	
	//Supporting services------------------------------------------
	
	//Constructor -------------------------------------------------
	public QuantityService(){
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
}

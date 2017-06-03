
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SocialIdentityRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Manager;
import domain.Restaurant;
import domain.SocialIdentity;
import forms.SocialIdentityForm;

@Service
@Transactional
public class SocialIdentityService {

	//Managed repository--------------------------------
	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;

	@Autowired
	private RestaurantService			restaurantService;

	@Autowired
	private Validator					validator;


	//Supporting Services-------------------------------

	//Constructor---------------------------------------
	public SocialIdentityService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	public SocialIdentity create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au2));
		SocialIdentity result;
		result = new SocialIdentity();
		return result;
	}

	public Collection<SocialIdentity> findAll() {

		Collection<SocialIdentity> result;

		result = socialIdentityRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public SocialIdentity findOne(int socialIdentityId) {

		SocialIdentity result;

		result = socialIdentityRepository.findOne(socialIdentityId);
		Assert.notNull(result);

		return result;
	}

	public SocialIdentity save(SocialIdentity socialIdentity) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au2 = new Authority();
		au2.setAuthority("MANAGER");

		Assert.isTrue((userAccount.getAuthorities().contains(au2)) && (socialIdentity.getRestaurant().getManager().getUserAccount().getUsername().equals(userAccount.getUsername())));

		SocialIdentity result;
		result = socialIdentityRepository.save(socialIdentity);

		return result;
	}
	public void delete(SocialIdentity socialIdentity) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(socialIdentity);

		Assert.isTrue(socialIdentity.getId() != 0);
		socialIdentityRepository.delete(socialIdentity);
	}

	public Collection<SocialIdentity> findByRestaurant(Manager manager) {
		Collection<SocialIdentity> result;
		result = socialIdentityRepository.findByRestaurant(manager.getId());
		return result;
	}
	// Other bussiness methods ----------------------------------------------------
	public SocialIdentityForm generateForm(int restaurantId) {
		SocialIdentityForm result;

		result = new SocialIdentityForm();
		result.setRestaurantId(restaurantId);
		return result;
	}

	public SocialIdentity reconstruct(SocialIdentityForm socialIdentityForm, BindingResult binding) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		SocialIdentity result;

		if (socialIdentityForm.getId() == 0)
			result = create();
		else
			result = findOne(socialIdentityForm.getId());

		Restaurant r = restaurantService.findOne(socialIdentityForm.getRestaurantId());
		result.setRestaurant(r);
		result.setNick(socialIdentityForm.getNick());
		result.setSocialNetwork(socialIdentityForm.getSocialNetwork());
		result.setLink(socialIdentityForm.getLink());
		result.setPicture(socialIdentityForm.getPicture());

		validator.validate(result, binding);

		return result;
	}
	public SocialIdentityForm transform(SocialIdentity socialIdentity) {
		SocialIdentityForm result = generateForm(socialIdentity.getRestaurant().getId());
		result.setRestaurantId(socialIdentity.getRestaurant().getId());
		result.setId(socialIdentity.getId());
		result.setNick(socialIdentity.getNick());
		result.setSocialNetwork(socialIdentity.getSocialNetwork());
		result.setLink(socialIdentity.getLink());
		result.setPicture(socialIdentity.getPicture());

		return result;
	}
}

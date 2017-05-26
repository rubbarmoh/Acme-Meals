
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PromoteRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Manager;
import domain.Promote;
import domain.Restaurant;

@Service
@Transactional
public class PromoteService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private PromoteRepository	promoteRepository;
	// Supporting services ----------------------------------------------------

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private RestaurantService	restaurantService;


	// Constructors -----------------------------------------------------------

	public PromoteService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	public Promote create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Promote result;
		result = new Promote();
		result.setTimesDisplayed(0);
		return result;
	}

	public Collection<Promote> findAll() {

		Collection<Promote> result;

		result = promoteRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Promote findOne(int promoteId) {

		Promote result;

		result = promoteRepository.findOne(promoteId);
		Assert.notNull(result);

		return result;
	}

	public Promote save(Promote promote) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Promote result;
		result = promoteRepository.save(promote);

		return result;
	}

	public void delete(Promote promote) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(promote);

		Assert.isTrue(promote.getId() != 0);

		promoteRepository.delete(promote);
	}
	// Other bussiness methods ----------------------------------------------------

	public Collection<Promote> findByPrincipal() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Promote> result = new ArrayList<Promote>();
		Manager manager = managerService.findByPrincipal();
		result = promoteRepository.promoteByManagerId(manager.getId());
		return result;
	}

	public Boolean isActive(Promote p) {
		Boolean b = false;
		Date date = new Date(System.currentTimeMillis());
		if ((p.getBeginning().before(date)) && (p.getEnding().after(date))) {
			b = true;
		}
		return b;
	}

	public Collection<Restaurant> promoteActive(Collection<Promote> promotes) {

		Collection<Restaurant> rest = new ArrayList<Restaurant>();
		Collection<Restaurant> result = new ArrayList<Restaurant>();
		rest = restaurantService.findByPrincipal();

		for (Restaurant r : rest) {
			for (Promote p : r.getPromotes()) {
				if (isActive(p) == true) {
					result.add(r);
				}
			}

		}

		return result;
	}

	public Collection<Restaurant> promoteActiveBanner() {
		Collection<Restaurant> rest = new ArrayList<Restaurant>();
		Collection<Restaurant> result = new ArrayList<Restaurant>();
		rest = restaurantService.findAll();

		for (Restaurant r : rest) {
			for (Promote p : r.getPromotes()) {
				if (isActive(p) == true) {
					result.add(r);
				}
			}
		}

		return result;
	}

	public Collection<Promote> promotesActive() {
		Collection<Promote> rest = new ArrayList<Promote>();
		Collection<Promote> result = new ArrayList<Promote>();
		rest = findAll();

		for (Promote p : rest) {
			if (isActive(p) == true) {
				result.add(p);
			}
		}

		return result;
	}

	public Collection<Promote> promotesActivePrincipal(Collection<Promote> promotes) {

		Collection<Promote> result = new ArrayList<Promote>();

		for (Promote p : promotes) {
			if (isActive(p) == true) {
				result.add(p);
			}
		}

		return result;
	}

	public Collection<Promote> promotesNActivePrincipal(Collection<Promote> promotes) {

		Collection<Promote> result = new ArrayList<Promote>();

		for (Promote p : promotes) {
			if (isActive(p) == false) {
				result.add(p);
			}
		}

		return result;
	}

	public Restaurant findRandom() {

		Restaurant r = new Restaurant();
		Object[] ts = promoteActiveBanner().toArray();
		int min = 0;
		int max = ts.length;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max);
		r = (Restaurant) ts[randomNum];
		for (Promote p : promotesActive()) {
			if (p.getRestaurant() == r) {

				p.setTimesDisplayed(p.getTimesDisplayed() + 1);

			}
		}
		return r;
	}
}

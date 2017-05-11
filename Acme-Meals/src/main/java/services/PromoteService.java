package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Promote;

import repositories.PromoteRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class PromoteService {
	// Managed repository -----------------------------------------------------
	@Autowired
	private PromoteRepository promoteRepository;
	// Supporting services ----------------------------------------------------


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
}


package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CriticRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Critic;
import domain.Review;
import forms.CriticForm;

@Service
@Transactional
public class CriticService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CriticRepository	criticRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------------------------

	public CriticService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Critic create() {
		UserAccount userAccounta;
		userAccounta = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccounta.getAuthorities().contains(au));

		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.CRITIC);
		authorities.add(a);
		userAccount.addAuthority(a);

		ArrayList<Review> reviews = new ArrayList<Review>();

		Critic result;
		result = new Critic();
		result.setReviews(reviews);
		result.setUserAccount(userAccount);
		return result;
	}

	public Collection<Critic> findAll() {
		Collection<Critic> result;

		result = criticRepository.findAll();
		return result;
	}

	public Critic findOne(int criticId) {
		Critic result;

		result = criticRepository.findOne(criticId);
		Assert.notNull(result);

		return result;
	}

	public Critic save(Critic critic) {

		Assert.notNull(critic);

		String password = critic.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String md5 = encoder.encodePassword(password, null);
		critic.getUserAccount().setPassword(md5);

		if (critic.getId() != 0) {
			Assert.isTrue(findByPrincipal().getId() == critic.getId());
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("CRITIC");
			Assert.isTrue(userAccount.getAuthorities().contains(au));
		}
		Critic result = criticRepository.save(critic);

		return result;
	}

	public Critic save2(Critic critic) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Authority au2 = new Authority();
		au.setAuthority("CRITIC");
		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));
		Assert.notNull(critic);

		Critic result;

		result = criticRepository.save(critic);

		return result;
	}

	public void delete(Critic critic) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Assert.notNull(critic);
		Assert.isTrue(critic.getId() != 0);

		criticRepository.delete(critic);
	}

	// Other business methods -------------------------------------------------

	public Critic findByPrincipal() {
		Critic result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public Critic findByUserAccountId(int userAccount) {
		Assert.notNull(userAccount);
		Critic result;

		result = criticRepository.findByUserAccountId(userAccount);

		return result;
	}

	public Collection<Double> minMaxAvgReviewsPerCritic() {

		Collection<Double> result = new ArrayList<Double>();
		Double aux;

		aux = criticRepository.minReviewsPerCritic();
		result.add(aux);

		aux = criticRepository.maxReviewsPerCritic();
		result.add(aux);

		aux = criticRepository.avgReviewsPerCritic();
		result.add(aux);

		return result;
	}

	//Forms----------

	public CriticForm generateForm(Critic critic) {
		CriticForm result = new CriticForm();

		result.setId(critic.getId());
		result.setUsername(critic.getUserAccount().getUsername());
		result.setPassword(critic.getUserAccount().getPassword());
		result.setPassword2(critic.getUserAccount().getPassword());

		result.setEmail(critic.getEmail());
		result.setName(critic.getName());
		result.setPhone(critic.getPhone());
		result.setAddress(critic.getAddress());
		result.setSurname(critic.getSurname());
		result.setCompanyName(critic.getCompanyName());

		return result;
	}

	public Critic reconstructEditPersonalData(CriticForm criticForm, BindingResult binding) {
		Critic result;

		Assert.isTrue(criticForm.getPassword2().equals(criticForm.getPassword()), "notEqualPassword");

		result = criticRepository.findOne(criticForm.getId());

		result.setName(criticForm.getName());
		result.setSurname(criticForm.getSurname());
		result.setEmail(criticForm.getEmail());
		result.setPhone(criticForm.getPhone());
		result.setAddress(criticForm.getAddress());
		result.setCompanyName(criticForm.getCompanyName());

		validator.validate(result, binding);

		return result;
	}

	public CriticForm generate() {
		CriticForm result;
		result = new CriticForm();

		return result;
	}

	public Critic reconstruct(CriticForm criticForm, BindingResult binding) {
		Critic result = create();

		String password = criticForm.getPassword();

		Assert.isTrue(criticForm.getPassword2().equals(password), "notEqualPassword");

		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.CRITIC);
		authorities.add(a);
		userAccount.addAuthority(a);
		userAccount.setPassword(password);
		userAccount.setUsername(criticForm.getUsername());

		result.setUserAccount(userAccount);
		result.setName(criticForm.getName());
		result.setSurname(criticForm.getSurname());
		result.setEmail(criticForm.getEmail());
		result.setPhone(criticForm.getPhone());
		result.setAddress(criticForm.getAddress());
		result.setCompanyName(criticForm.getCompanyName());

		validator.validate(result, binding);

		return result;
	}

}

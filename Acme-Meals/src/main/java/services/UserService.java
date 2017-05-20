
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.CreditCard;
import domain.MealOrder;
import domain.RelationDislike;
import domain.RelationLike;
import domain.Report;
import domain.User;
import forms.UserForm;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserRepository	userRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator		validator;


	// Constructors -----------------------------------------------------------

	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public User create() {
		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.USER);
		authorities.add(a);
		userAccount.addAuthority(a);
		User result;

		result = new User();
		result.setBanned(false);
		result.setUserAccount(userAccount);
		result.setComments(new ArrayList<Comment>());
		result.setMealOrders(new ArrayList<MealOrder>());
		result.setRelationDislikes(new ArrayList<RelationDislike>());
		result.setRelationLikes(new ArrayList<RelationLike>());
		result.setReports(new ArrayList<Report>());
		return result;
	}

	public Collection<User> findAll() {

		Collection<User> result;

		result = userRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public User findOne(int userId) {

		User result;
		result = userRepository.findOne(userId);
		return result;
	}

	public User save(User user) {

		Assert.notNull(user);

		String password = user.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String md5 = encoder.encodePassword(password, null);
		user.getUserAccount().setPassword(md5);

		if (user.getId() != 0) {
			Assert.isTrue(findByPrincipal().getId() == user.getId());
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("USER");
			Assert.isTrue(userAccount.getAuthorities().contains(au));
		}
		User result = userRepository.save(user);

		return result;
	}

	public User save2(User user) {
		User result;

		result = userRepository.save(user);

		return result;
	}

	public void delete(User user) {

		Assert.notNull(user);

		Assert.isTrue(user.getId() != 0);

		userRepository.delete(user);
	}

	// Other bussiness methods ------------------------------------------------

	public User findByPrincipal() {
		User result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		result = userRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}

	public boolean check(CreditCard creditCard) {
		boolean validador = false;
		int sum = 0;
		Calendar fecha = Calendar.getInstance();
		String numero = creditCard.getNumber();
		int mes = fecha.get(Calendar.MONTH) + 1;
		int año = fecha.get(Calendar.YEAR);

		if (creditCard.getExpirationYear() > año) {
			validador = true;
		} else if (creditCard.getExpirationYear() == año) {
			if (creditCard.getExpirationMonth() >= mes) {
				validador = true;
			}
		}

		if (numero.length() < 15) {
			validador = false;
		}

		if (validador) {
			validador = false;
			int sumatorio = 0;
			for (int i = numero.length() - 1; i >= 0; i--) {
				int n = Integer.parseInt(numero.substring(i, i + 1));
				if (validador) {
					n *= 2;
					if (n > 9) {
						n = (n % 10) + 1;
					}
				}
				sum += n;
				validador = !validador;
				sumatorio += n;
			}
			if (sumatorio == 0) {
				validador = false;
			} else if (sum % 10 == 0) {
				validador = true;
			}
		}

		return validador;
	}

	public Collection<Double> minMaxAVGOrdersPerUser() {

		Collection<Double> result = new ArrayList<Double>();
		Double aux;

		aux = userRepository.minOrdersPerUser();
		result.add(aux);

		aux = userRepository.maxOrdersPerUser();
		result.add(aux);

		aux = userRepository.avgOrdersPerUser();
		result.add(aux);

		return result;
	}

	public List<User> usersMorethan10PercentOrders() {
		List<User> result = userRepository.usersMorethan10PercentOrders();
		return result;
	}

	public List<User> usersLessthan10PercentOrders() {
		List<User> result = userRepository.usersLessthan10PercentOrders();
		return result;
	}

	public List<User> usersMoreThan10PercentComments() {
		List<User> result = userRepository.usersMoreThan10PercentComments();
		return result;
	}

	public List<User> usersLessThan10PercentComments() {
		List<User> result = userRepository.usersLessThan10PercentComments();
		return result;
	}

	public Map<User, Long> findReported() {
		Map<User, Long> result = new HashMap<User, Long>();

		List<Object[]> aux = userRepository.findReported();
		;
		for (Object[] o : aux) {
			if ((Long) o[1] >= 2)
				result.put((User) o[0], (Long) o[1]);
		}

		return result;
	}

	public Map<User, Long> findBanned() {
		Map<User, Long> result = new HashMap<User, Long>();

		List<Object[]> aux = userRepository.findBanned();
		for (Object[] o : aux) {
			result.put((User) o[0], (Long) o[1]);
		}

		return result;
	}

	public void banUnban(User user) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		if (user.getBanned() == true) {
			user.setBanned(false);
		} else {
			user.setBanned(true);
		}
		save2(user);
	}

	//Forms----------

	public UserForm generateForm(User user) {
		UserForm result = new UserForm();

		result.setId(user.getId());
		result.setUsername(user.getUserAccount().getUsername());
		result.setPassword(user.getUserAccount().getPassword());
		result.setPassword2(user.getUserAccount().getPassword());
		result.setAgreed(true);

		result.setCreditCard(user.getCreditCard());

		result.setEmail(user.getEmail());
		result.setName(user.getName());
		result.setPhone(user.getPhone());
		result.setAddress(user.getAddress());
		result.setSurname(user.getSurname());

		return result;
	}

	public User reconstructEditPersonalData(UserForm userForm, BindingResult binding) {
		User result;

		Assert.isTrue(userForm.getPassword2().equals(userForm.getPassword()), "notEqualPassword");
		Assert.isTrue(userForm.getAgreed(), "agreedNotAccepted");
		Assert.isTrue(check(userForm.getCreditCard()), "badCreditCard");
		Assert.isTrue(userForm.getCreditCard().getHolderName() != "", "badCreditCard");

		result = userRepository.findOne(userForm.getId());

		result.setName(userForm.getName());
		result.setSurname(userForm.getSurname());
		result.setEmail(userForm.getEmail());
		result.setPhone(userForm.getPhone());
		result.setAddress(userForm.getAddress());
		result.setCreditCard(userForm.getCreditCard());

		validator.validate(result, binding);

		return result;
	}

	public UserForm generate() {
		UserForm result;
		result = new UserForm();

		return result;
	}

	public User reconstruct(UserForm userForm, BindingResult binding) {
		User result = create();

		String password = userForm.getPassword();

		Assert.isTrue(userForm.getPassword2().equals(password), "notEqualPassword");
		Assert.isTrue(userForm.getAgreed(), "agreedNotAccepted");
		Assert.isTrue(check(userForm.getCreditCard()), "badCreditCard");
		Assert.isTrue(userForm.getCreditCard().getHolderName() != "", "badCreditCard");

		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.USER);
		authorities.add(a);
		userAccount.addAuthority(a);
		userAccount.setPassword(password);
		userAccount.setUsername(userForm.getUsername());

		result.setUserAccount(userAccount);
		result.setName(userForm.getName());
		result.setSurname(userForm.getSurname());
		result.setEmail(userForm.getEmail());
		result.setPhone(userForm.getPhone());
		result.setAddress(userForm.getAddress());
		result.setCreditCard(userForm.getCreditCard());

		validator.validate(result, binding);

		return result;
	}

}

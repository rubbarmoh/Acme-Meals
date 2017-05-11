package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;

import domain.MealOrder;
import domain.RelationDislike;
import domain.RelationLike;
import domain.Report;
import domain.User;

@Service
@Transactional
public class UserService {
	// Managed repository -----------------------------------------------------

			@Autowired
			private UserRepository	userRepository;

			// Supporting services ----------------------------------------------------

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

		
	}



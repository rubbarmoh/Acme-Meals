package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CriticRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Critic;
import domain.Review;

@Service
@Transactional
public class CriticService {

	// Managed repository -----------------------------------------------------

			@Autowired
			private CriticRepository	criticRepository;


			// Supporting services ----------------------------------------------------

			// Constructors -----------------------------------------------------------

			public CriticService() {
				super();
			}

			// Simple CRUD methods ----------------------------------------------------

			public Critic create() {
				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("ADMIN");
				Assert.isTrue(userAccount.getAuthorities().contains(au));
				
				ArrayList<Review> reviews = new ArrayList<Review>();
				
				Critic result;
				result = new Critic();
				result.setReviews(reviews);
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
				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("ADMIN");
				Authority au2 = new Authority();
				au.setAuthority("CRITIC");
				Assert.isTrue(userAccount.getAuthorities().contains(au)||userAccount.getAuthorities().contains(au2));
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
			
			public List<Double> minMaxAvgReviewsPerCritic(){
				List<Double> result = criticRepository.minMaxAvgReviewsPerCritic();
				return result;
			}
			
			
}

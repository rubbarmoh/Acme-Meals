package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MonthlyBillRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.MonthlyBill;

@Service
@Transactional
public class MonthlyBillService {

	// Managed repository -----------------------------------------------------

			@Autowired
			private MonthlyBillRepository	monthlyBillRepository;

			// Supporting services ----------------------------------------------------


			// Constructors -----------------------------------------------------------

			public MonthlyBillService() {
				super();
			}

			// Simple CRUD methods ----------------------------------------------------

			public MonthlyBill create() {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("ADMIN");
				Assert.isTrue(userAccount.getAuthorities().contains(au));

				MonthlyBill result;

				result = new MonthlyBill();

				return result;
			}

			public Collection<MonthlyBill> findAll() {
				Collection<MonthlyBill> result;

				result = monthlyBillRepository.findAll();
				Assert.notNull(result);

				return result;
			}

			public MonthlyBill findOne(int monthlyBillId) {
				Assert.isTrue(monthlyBillId != 0);

				MonthlyBill result;

				result = monthlyBillRepository.findOne(monthlyBillId);
				Assert.notNull(result);

				return result;
			}

			public MonthlyBill save(MonthlyBill monthlyBill) {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("ADMIN");
				Assert.isTrue(userAccount.getAuthorities().contains(au));

				Assert.notNull(monthlyBill);

				return monthlyBillRepository.save(monthlyBill);
			}

			public void delete(MonthlyBill monthlyBill) {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("ADMIN");
				Assert.isTrue(userAccount.getAuthorities().contains(au));

				Assert.notNull(monthlyBill);
				Assert.isTrue(monthlyBill.getId() != 0);
				Assert.isTrue(monthlyBillRepository.exists(monthlyBill.getId()));

				monthlyBillRepository.delete(monthlyBill);
			}
}

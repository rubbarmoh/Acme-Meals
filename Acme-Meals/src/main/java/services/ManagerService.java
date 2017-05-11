package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;
import domain.Manager;
import domain.MonthlyBill;
import domain.Report;
import domain.Restaurant;

@Service
@Transactional
public class ManagerService {

	// Managed repository -----------------------------------------------------

		@Autowired
		private ManagerRepository	managerRepository;

		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------

		public ManagerService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------

		public Manager create() {
			UserAccount userAccount = new UserAccount();
			List<Authority> authorities = new ArrayList<Authority>();
			Authority a = new Authority();
			a.setAuthority(Authority.MANAGER);
			authorities.add(a);
			userAccount.addAuthority(a);
			ArrayList<MonthlyBill> monthlyBills = new ArrayList<MonthlyBill>();
			ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
			ArrayList<Report> reports = new ArrayList<Report>();
			
			Manager result;
			result = new Manager();
			result.setUserAccount(userAccount);
			result.setRestaurants(restaurants);
			result.setReports(reports);
			result.setMonthlyBills(monthlyBills);
			return result;
		}

		public Collection<Manager> findAll() {

			Collection<Manager> result;

			result = managerRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public Manager findOne(int managerId) {

			Manager result;
			result = managerRepository.findOne(managerId);
			return result;
		}

		public Manager save(Manager manager) {

			Assert.notNull(manager);

			String password = manager.getUserAccount().getPassword();
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			String md5 = encoder.encodePassword(password, null);
			manager.getUserAccount().setPassword(md5);

			if (manager.getId() != 0) {
				Assert.isTrue(findByPrincipal().getId() == manager.getId());
				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("MANAGER");
				Assert.isTrue(userAccount.getAuthorities().contains(au));
			}
			Manager result = managerRepository.save(manager);

			return result;
		}

		public Manager save2(Manager manager) {
			Manager result;

			result = managerRepository.save(manager);

			return result;
		}

		public void delete(Manager manager) {

			Assert.notNull(manager);

			Assert.isTrue(manager.getId() != 0);

			managerRepository.delete(manager);
		}

		// Other bussiness methods ------------------------------------------------

		public Manager findByPrincipal() {
			Manager result;
			int userAccountId;

			userAccountId = LoginService.getPrincipal().getId();
			result = managerRepository.findByUserAccountId(userAccountId);

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

			if(numero.length()<15){
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
				if(sumatorio == 0){
					validador = false;
				}else if (sum % 10 == 0) {
					validador = true;
				}
			}

			return validador;
		}
}

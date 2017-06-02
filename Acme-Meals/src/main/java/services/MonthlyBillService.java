
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MonthlyBillRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Fee;
import domain.Manager;
import domain.MonthlyBill;
import domain.Promote;

@Service
@Transactional
public class MonthlyBillService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private MonthlyBillRepository	monthlyBillRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ManagerService			managerService;

	@Autowired
	PromoteService					promoteService;

	@Autowired
	FeeService						feeService;


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
		Authority au1 = new Authority();
		au1.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au1));

		MonthlyBill result;

		result = new MonthlyBill();

		return result;
	}

	public Collection<MonthlyBill> findAll() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

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

	public Collection<MonthlyBill> findByPrincipal() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<MonthlyBill> result = new ArrayList<MonthlyBill>();
		Manager manager = managerService.findByPrincipal();
		result = monthlyBillRepository.monthlyBillByManagerId(manager.getId());
		return result;
	}

	public void generateMonthlyBills() {
		MonthlyBill mb = create();
		Date date = new Date(System.currentTimeMillis() - 1);
		Double cost = 0.;
		Fee fee = feeService.find();
		for (Promote p : promoteService.promotesActive()) {
			if (p.getTimesDisplayed() > 0) {
				cost = p.getTimesDisplayed() * fee.getValue();
				mb.setMoment(date);
				mb.setCost(cost);
				mb.setManager(p.getRestaurant().getManager());
				save(mb);
				p.setTimesDisplayed(0);
			}
		}

	}

	public void generateMonthlyBillsLD() {
		MonthlyBill mb = create();
		Date date = new Date(System.currentTimeMillis() - 1);
		Double cost = 0.;
		Fee fee = feeService.find();
		for (Promote p : promoteService.promotesNActive()) {
			if (p.getTimesDisplayed() > 0) {
				cost = p.getTimesDisplayed() * fee.getValue();
				mb.setMoment(date);
				mb.setCost(cost);
				mb.setManager(p.getRestaurant().getManager());
				save(mb);
				p.setTimesDisplayed(0);
			}
		}

	}

	public void payMonthlyBill(MonthlyBill mb) {
		Date date = new Date(System.currentTimeMillis() - 1);
		mb.setPaidMoment(date);
	}
}


package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.userAccount.id=?1")
	Manager findByUserAccountId(int userAccountId);
	
	@Query("select min(m.monthlyBills.size),max(m.monthlyBills.size),avg(m.monthlyBills.size) from Manager m")
	List<Double> minMaxAvgMonthlyBillsPerManager();
}

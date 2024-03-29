
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.userAccount.id=?1")
	Manager findByUserAccountId(int userAccountId);

	@Query("select min(m.monthlyBills.size) from Manager m")
	Double minMonthlyBillsPerManager();

	@Query("select max(m.monthlyBills.size) from Manager m")
	Double maxMonthlyBillsPerManager();

	@Query("select avg(m.monthlyBills.size) from Manager m")
	Double avgMonthlyBillsPerManager();
}

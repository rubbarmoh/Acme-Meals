
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MonthlyBill;

@Repository
public interface MonthlyBillRepository extends JpaRepository<MonthlyBill, Integer> {

	@Query("select m from MonthlyBill m where m.manager.id=?1")
	Collection<MonthlyBill> monthlyBillByManagerId(int id);

}

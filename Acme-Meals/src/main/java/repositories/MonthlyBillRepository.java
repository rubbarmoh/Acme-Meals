
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.MonthlyBill;

@Repository
public interface MonthlyBillRepository extends JpaRepository<MonthlyBill, Integer> {

}

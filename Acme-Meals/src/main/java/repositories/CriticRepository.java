
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.MonthlyBill;

@Repository
public interface CriticRepository extends JpaRepository<MonthlyBill, Integer> {

}

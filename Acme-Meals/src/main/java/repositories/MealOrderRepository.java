
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.MealOrder;

@Repository
public interface MealOrderRepository extends JpaRepository<MealOrder, Integer> {

}

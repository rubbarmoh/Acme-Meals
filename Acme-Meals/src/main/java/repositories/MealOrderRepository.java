
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MealOrder;

@Repository
public interface MealOrderRepository extends JpaRepository<MealOrder, Integer> {
	
	@Query("select mo from MealOrder mo where mo.user.id=?1")
	Collection<MealOrder> findByUser(int userId);

}

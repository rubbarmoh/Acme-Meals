
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MealOrder;

@Repository
public interface MealOrderRepository extends JpaRepository<MealOrder, Integer> {
	
	@Query("select mo from MealOrder mo where mo.user.id=?1 order by mo.moment")
	Collection<MealOrder> findByUser(int userId);
	
	@Query("select mo from MealOrder mo where mo.user.id=?1 and (mo.status like 'PENDING' or mo.status like 'INPROGRESS') order by mo.restaurant.name")
	Collection<MealOrder> findCurrentlyByUser(int userId);
	
	@Query("select mo from MealOrder mo where mo.restaurant.manager.id=?1 order by mo.restaurant.name")
	Collection<MealOrder> findByManager(int managerId);
	
	@Query("select mo from MealOrder mo where mo.restaurant.manager.id=?1 and (mo.status like 'PENDING' or mo.status like 'INPROGRESS') order by mo.restaurant.name")
	Collection<MealOrder> findCurrentlyByManager(int managerId);
	
	
}

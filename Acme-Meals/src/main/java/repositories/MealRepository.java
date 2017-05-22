
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {
	
	@Query("select m from Meal m where m.category.name like %?1%")
	List<Meal> mealsByCategory(String category);
	
	@Query("select m from Meal m where m.restaurant.id=?1")
	List<Meal> mealsPerRestaurant(int restaurantId);
}

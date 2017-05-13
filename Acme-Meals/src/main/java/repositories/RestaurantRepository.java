
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	@Query("select r from Restaurant r where r.manager.id=?1")
	Collection<Restaurant> restaurantByManagerId(int id);

}

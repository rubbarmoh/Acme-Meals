
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;
import domain.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	@Query("select r from Restaurant r where r.manager.id=?1")
	Collection<Restaurant> restaurantByManagerId(int id);
	
	@Query("select r.orders.size from Restaurant r where r.manager=?1")
	List<Integer> ordersPerRestaurant(Manager manager);
	
	@Query("select r from Restaurant r where r.manager=?1 && r.avgStars>= all(select r2.avgStars from Restaurant r2 where r2.manager=?1)")
	List<Restaurant> restaurantMoreStars(Manager manager);
	
	@Query("select r from Restaurant r where r.manager=?1 && r.avgStars<= all(select r2.avgStars from Restaurant r2 where r2.manager=?1)")
	List<Restaurant> restaurantLessStars(Manager managger);
	
	@Query("select 1.0*(select sum(o.amount) from MealOrder o where o.status='FINISHED' && o.restaurant.manager=?1 && o.restaurant=?2)/sum(o2.amount) from MealOrder o2 where o2.status='FINISHED' && o2.restaurant.manager=?1")
	Double avgProfitMyRestaurants(Manager manager ,Restaurant restaurant);
	
	@Query("select sum(o.amount) from MealOrder o where o.status='FINISHED' && o.manager=?1")
	Double minMaxProfitByRestaurant(Manager m);
	
	@Query("select o.restaurant,sum(o.amount) from MealOrder o where o.manager=?1")
	List<Object[]> restaurantMoreProfit(Manager m);
	
	@Query("select r from Restaurant r where r.manager=?1 && r.mealOrders.size >= 0.10*(select avg(r2.mealOrders.size) from Restaurant r2 where r2.manager=?1)")
	List<Restaurant> restaurantsWithMore10PercentOrders(Manager manager);
	
	@Query("select r from Restaurant r where r.manager=?1 && r.mealOrders.size <= 0.10*(select avg(r2.mealOrders.size) from Restaurant r2 where r2.manager=?1)")
	List<Restaurant> restaurantsWithLess10PercentOrders(Manager manager);
	
	@Query("select r from Restaurant r where r.mealOrders.size >= all( select r2.mealOrders.size from Restaurant r2)")
	List<Restaurant> restaurantMoreOrders();
	
	@Query("select r from Restaurant r where r.mealOrders.size <= all( select r2.mealOrders.size from Restaurant r2)")
	List<Restaurant> restaurantLessOrders();
	
	@Query("select 1.0*(select count(r) from Restaurant r where  r.socialIdentities.size>0)/count(r2) from Restaurant r2")
	Double ratioRestaurantWithSocialIdentity();
	
	@Query("select r from Restaurant r where r.reviews.size >= all(select r2.reviews.size from Restaurant r2)")
	List<Restaurant> restaurantWithMoreReviews();
	
	@Query("select r from Restaurant r where r.reviews.size <= all(select r2.reviews.size from Restaurant r2)")
	List<Restaurant> restaurantWithLessReviews();
	
	@Query("select 1.0*(select count(r) from Restaurant r where r.promotes.size>0)/count(r2) from Restaurant r2")
	Double ratioRestaurantsPromoted();
	
	@Query("select r from Restaurant r where r.avgStars>=all(select r2.avgStars from Restaurant r2)")
	List<Restaurant> restaurantMoreStars();
}

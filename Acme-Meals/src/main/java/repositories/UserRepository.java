
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select m from User m where m.userAccount.id=?1")
	User findByUserAccountId(int userAccountId);
	
	@Query("select min(u.mealOrders.size),max(u.mealOrders.size),avg(u.mealOrders.size) from User u")
	List<Double>minMaxAVGOrdersPerUser();
	
	@Query("select u from User u where u.mealOrders.size >= 0.1*(select avg(u2.mealOrders.size) from User u2)")
	List<User> usersMorethan10PercentOrders();
	
	@Query("select u from User u where u.mealOrders.size <= 0.1*(select avg(u2.mealOrders.size) from User u2)")
	List<User> usersLessthan10PercentOrders();
	
	@Query("select u from User u where u.comments.size >= 0.1*(select avg(u2.comments.size) from User u2)")
	List<User> usersMoreThan10PercentComments();
	
	@Query("select u from User u where u.comments.size <= 0.1*(select avg(u2.comments.size) from User u2)")
	List<User> usersLessThan10PercentComments();
	
	@Query("select c.user, sum(c.reports.size) from Comment c group by c.user")
	List<Object[]> findReported();
	
	@Query("select c.user, sum(c.reports.size) from Comment c  where c.user.banned=true group by c.user")
	List<Object[]> findBanned();
	
	
	
	
}


package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	@Query("select r from Review r where r.relationLikes.size>= all(select r2.relationLikes.size  from Review r2)")
	List<Review> reviewMoreLikes();

	@Query("select r from Review r where r.critic.id=?1")
	Collection<Review> reviewByCriticId(int id);

	@Query("select r from Review r order by r.relationLikes.size DESC")
	List<Review> reviewCriticMoreLikes();

}

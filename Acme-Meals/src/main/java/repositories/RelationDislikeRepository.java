
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RelationDislike;
import domain.Review;
import domain.User;

@Repository
public interface RelationDislikeRepository extends JpaRepository<RelationDislike, Integer> {

	@Query("select r from RelationDislike r where r.user=?1 and r.review=?2")
	RelationDislike findRelationDislike(User user, Review review);
	
}

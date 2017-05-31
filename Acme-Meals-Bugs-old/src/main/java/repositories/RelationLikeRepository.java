package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RelationLike;
import domain.Review;
import domain.User;

@Repository
public interface RelationLikeRepository extends JpaRepository<RelationLike, Integer>{

	@Query("select r from RelationLike r where r.user=?1 and r.review=?2")
	RelationLike findRelationLike(User user, Review review);
	
}


package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query("select c from Comment c where c.user.banned=false order by c.moment DESC")
	List<Comment> findAllOrderByMoment();
}

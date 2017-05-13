
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Critic;

@Repository
public interface CriticRepository extends JpaRepository<Critic, Integer> {

	@Query("select a from Critic a where a.userAccount.id=?1")
	Critic findByUserAccountId(int id);
	
	@Query("select min(c.reviews.size),max(c.reviews.size),avg(c.reviews.size) from Critic c")
	List<Double> minMaxAvgReviewsPerCritic();
}

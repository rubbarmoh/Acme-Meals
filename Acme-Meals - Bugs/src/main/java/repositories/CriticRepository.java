
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Critic;

@Repository
public interface CriticRepository extends JpaRepository<Critic, Integer> {

	@Query("select a from Critic a where a.userAccount.id=?1")
	Critic findByUserAccountId(int id);

	@Query("select min(c.reviews.size) from Critic c")
	Double minReviewsPerCritic();

	@Query("select max(c.reviews.size) from Critic c")
	Double maxReviewsPerCritic();

	@Query("select avg(c.reviews.size) from Critic c")
	Double avgReviewsPerCritic();
}


package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reporter;


@Repository
public interface ReporterRepository extends JpaRepository<Reporter, Integer> {
	@Query("select m from Reporter m where m.userAccount.id=?1")
	Reporter findByUserAccountId(int userAccountId);
}

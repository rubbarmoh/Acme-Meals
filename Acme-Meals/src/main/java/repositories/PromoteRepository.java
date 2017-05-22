
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Promote;

@Repository
public interface PromoteRepository extends JpaRepository<Promote, Integer> {

	@Query("select p from Promote p where p.restaurant.manager.id=?1")
	Collection<Promote> promoteByManagerId(int id);

}

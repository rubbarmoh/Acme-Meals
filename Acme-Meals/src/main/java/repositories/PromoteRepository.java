
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Promote;

@Repository
public interface PromoteRepository extends JpaRepository<Promote, Integer> {

}

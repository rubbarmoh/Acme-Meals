
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Reporter;

@Repository
public interface ReporterRepository extends JpaRepository<Reporter, Integer> {

}

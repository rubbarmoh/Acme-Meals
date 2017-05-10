
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.VATNumber;

@Repository
public interface VATNumberRepository extends JpaRepository<VATNumber, Integer> {

}

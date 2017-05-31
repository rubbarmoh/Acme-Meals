
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer> {
	@Query("select r.socialIdentities from Restaurant r where r.manager.id=?1")
	List<SocialIdentity> findByRestaurant(int id);
}

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RelationLikeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.RelationLike;
import domain.Review;
import domain.User;

@Service
@Transactional
public class RelationLikeService {
	//Managed repository--------------------------------
	@Autowired
	private RelationLikeRepository relationLikeRepository;
	
	//Supporting Services-------------------------------
	
	@Autowired
	private UserService userService;
	
	//Constructor---------------------------------------
	public RelationLikeService(){
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	public RelationLike create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		RelationLike result;
		result = new RelationLike();
		return result;
	}

	public Collection<RelationLike> findAll() {

		Collection<RelationLike> result;

		result = relationLikeRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public RelationLike findOne(int relationLikeId) {

		RelationLike result;

		result = relationLikeRepository.findOne(relationLikeId);
		Assert.notNull(result);

		return result;
	}

	public RelationLike save(RelationLike relationLike) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		RelationLike result;
		result = relationLikeRepository.save(relationLike);

		return result;
	}

	public void delete(RelationLike relationLike) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(relationLike);

		Assert.isTrue(relationLike.getId() != 0);

		
		relationLikeRepository.delete(relationLike);
	}
	// Other bussiness methods ----------------------------------------------------
	
	public RelationLike findRelationLike(Review review){
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		
		User user = userService.findByPrincipal();
		RelationLike result = relationLikeRepository.findRelationLike(user, review);
		return result;
	}
}



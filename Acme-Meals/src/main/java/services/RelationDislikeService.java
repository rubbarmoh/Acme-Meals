package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.RelationDislike;
import domain.Review;
import domain.User;

import repositories.RelationDislikeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class RelationDislikeService {
	//Managed repository--------------------------------
	@Autowired
	private RelationDislikeRepository relationDislikeRepository;
	
	//Supporting Services-------------------------------
	
		@Autowired
		private UserService userService;
	
	//Constructor---------------------------------------
	public RelationDislikeService(){
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	public RelationDislike create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		RelationDislike result;
		result = new RelationDislike();
		return result;
	}

	public Collection<RelationDislike> findAll() {

		Collection<RelationDislike> result;

		result = relationDislikeRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public RelationDislike findOne(int relationDislikeId) {

		RelationDislike result;

		result = relationDislikeRepository.findOne(relationDislikeId);
		Assert.notNull(result);

		return result;
	}

	public RelationDislike save(RelationDislike relationDislike) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		RelationDislike result = null;
		if(findRelationDislike(relationDislike.getReview())==null){
			result = relationDislikeRepository.save(relationDislike);
		}

		return result;
	}

	public void delete(RelationDislike relationDislike) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(relationDislike);

		Assert.isTrue(relationDislike.getId() != 0);
		
		if(findRelationDislike(relationDislike.getReview())!=null){
			relationDislikeRepository.delete(relationDislike);
		}
		
	}
	// Other bussiness methods ----------------------------------------------------
	
	
	public RelationDislike findRelationDislike(Review review){
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("USER");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		
		User user = userService.findByPrincipal();
		RelationDislike result = relationDislikeRepository.findRelationDislike(user, review);
		return result;
	}
	
}

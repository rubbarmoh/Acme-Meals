package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.SocialIdentity;


import repositories.SocialIdentityRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;


@Service
@Transactional
public class SocialIdentityService {
	//Managed repository--------------------------------
		@Autowired
		private SocialIdentityRepository socialIdentityRepository;
		//Supporting Services-------------------------------
		
		//Constructor---------------------------------------
		public SocialIdentityService(){
			super();
		}
		// Simple CRUD methods ----------------------------------------------------

		public SocialIdentity create() {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au2 = new Authority();
			au2.setAuthority("MANAGER");
			Assert.isTrue(userAccount.getAuthorities().contains(au2));
			SocialIdentity result;
			result = new SocialIdentity();
			return result;
		}

		public Collection<SocialIdentity> findAll() {

			Collection<SocialIdentity> result;

			result = socialIdentityRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public SocialIdentity findOne(int socialIdentityId) {

			SocialIdentity result;

			result = socialIdentityRepository.findOne(socialIdentityId);
			Assert.notNull(result);

			return result;
		}

		public SocialIdentity save(SocialIdentity socialIdentity) {
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au2 = new Authority();
			au2.setAuthority("MANAGER");

			Assert.isTrue(userAccount.getAuthorities().contains(au2));

			SocialIdentity result;
			result = socialIdentityRepository.save(socialIdentity);

			return result;
		}

		public void delete(SocialIdentity socialIdentity) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("MANAGER");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(socialIdentity);

			Assert.isTrue(socialIdentity.getId() != 0);
			socialIdentityRepository.delete(socialIdentity);
		}
		// Other bussiness methods ----------------------------------------------------
	}


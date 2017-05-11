package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.VATNumber;

import repositories.VATNumberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class VATNumberService {
	// Managed repository -----------------------------------------------------

		@Autowired
		private VATNumberRepository	vatNumberRepository;

		// Supporting services ----------------------------------------------------

		


		// Constructors -----------------------------------------------------------

		public VATNumberService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------

		public VATNumber create() {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			VATNumber result;

			result = new VATNumber();

			return result;
		}

		public Collection<VATNumber> findAll() {
			Collection<VATNumber> result;

			result = vatNumberRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public VATNumber findOne(int vatId) {
			Assert.isTrue(vatId != 0);

			VATNumber result;

			result = vatNumberRepository.findOne(vatId);
			Assert.notNull(result);

			return result;
		}

		public VATNumber save(VATNumber vat) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(vat);

			return vatNumberRepository.save(vat);
		}

		public void delete(VATNumber vat) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(vat);
			Assert.isTrue(vat.getId() != 0);
			Assert.isTrue(vatNumberRepository.exists(vat.getId()));

			vatNumberRepository.delete(vat);
		}

		// Other bussines methods ---------------------------

		
}

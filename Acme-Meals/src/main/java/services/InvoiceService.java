package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InvoiceRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Invoice;

@Service
@Transactional
public class InvoiceService {
	
	// Managed repository -----------------------------------------------------

			@Autowired
			private InvoiceRepository	invoiceRepository;

			// Supporting services ----------------------------------------------------


			// Constructors -----------------------------------------------------------

			public InvoiceService() {
				super();
			}

			// Simple CRUD methods ----------------------------------------------------

			public Invoice create() {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("USER");
				Assert.isTrue(userAccount.getAuthorities().contains(au));
				Invoice result;
				result = new Invoice();

				return result;
			}

			public Collection<Invoice> findAll() {
				Collection<Invoice> result;

				result = invoiceRepository.findAll();
				return result;
			}

			public Invoice findOne(int invoiceId) {
				Assert.isTrue(invoiceId != 0);

				Invoice result;
				result = invoiceRepository.findOne(invoiceId);

				return result;
			}

			public Invoice save(Invoice invoice) {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("USER");
				Assert.isTrue(userAccount.getAuthorities().contains(au));

				Assert.notNull(invoice);

				return invoiceRepository.save(invoice);
			}

			public void delete(Invoice invoice) {

				UserAccount userAccount;
				userAccount = LoginService.getPrincipal();
				Authority au = new Authority();
				au.setAuthority("USER");
				Assert.isTrue(userAccount.getAuthorities().contains(au));

				Assert.notNull(invoice);
				Assert.isTrue(invoice.getId() != 0);
				Assert.isTrue(invoiceRepository.exists(invoice.getId()));

				invoiceRepository.delete(invoice);
			}
}

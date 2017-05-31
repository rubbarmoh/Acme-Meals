
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Invoice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class InvoiceServiceTest extends AbstractTest {

	// The SUT ----------------------------------------------------------

	@Autowired
	private InvoiceService	invoiceService;


	// Tests ------------------------------------------------------------

	/*
	 * List Invoices
	 */
	@Test
	public void driverListInvoice() {
		Object testingData[][] = {
			{
				null, IllegalArgumentException.class
			}, // Editamos perfil con un usuario no autenticado
			{
				"admin", IllegalArgumentException.class
			}, // Editamos perfil pero dejamos en blanco la casilla de edicion
			{
				"user1", null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateListInvoice((String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
	}
	protected void templateListInvoice(String username, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username); // Nos autenticamos
			Collection<Invoice> invoices = invoiceService.findByPrincipal();
			Assert.notNull(invoices);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

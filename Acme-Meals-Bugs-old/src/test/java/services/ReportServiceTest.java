
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Report;
import forms.ReportForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
public class ReportServiceTest extends AbstractTest {

	// The SUT ----------------------------------------------------------

	@Autowired
	private ReportService	reportService;


	// Tests ------------------------------------------------------------

	/*
	 * Report comment as user/manager
	 */
	@Test
	public void driverReportComment() {
		Object testingData[][] = {
			{
				null, 140, "texto", IllegalArgumentException.class
			}, // Reportamos con un usuario no autenticado
			{
				"admin", 140, "texto", IllegalArgumentException.class
			}, // Reportamos con un admin
			{
				"critic1", 140, "texto", IllegalArgumentException.class
			}, // Reportamos con un critico 
			{
				"user1", 139, "texto", IllegalArgumentException.class
			}, // Reportamos a un comentario con id incorrecta
			{
				"manager1", 140, "texto", null
			}, {
				"user1", 140, "texto", null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			templateReportComment((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
		}
	}
	protected void templateReportComment(String username, int comId, String text, Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username); // Nos autenticamos
			ReportForm reportForm = reportService.generateForm();
			reportForm.setCommentId(comId);
			reportForm.setText(text);

			Report report = reportService.reconstruct(reportForm, null);
			Assert.isTrue(report != null);
			reportService.save(report);
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
}

package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


import repositories.ReportRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Report;
import domain.Reporter;
import forms.ReportForm;

@Service
@Transactional
public class ReportService {
	//Managed repository--------------------------------
		@Autowired
		private ReportRepository reportRepository;
		//Supporting Services-------------------------------
		@Autowired
		private ReporterService reporterService;
		
		@Autowired
		private CommentService commentService;
		
		@Autowired
		private Validator validator;
		//Constructor---------------------------------------
		public ReportService(){
			super();
		}
		// Simple CRUD methods ----------------------------------------------------

		public Report create() {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("USER");
			Authority au2 = new Authority();
			au2.setAuthority("MANAGER");
			Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));
			Report result;
			result = new Report();
			return result;
		}

		public Collection<Report> findAll() {

			Collection<Report> result;

			result = reportRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public Report findOne(int reportId) {

			Report result;

			result = reportRepository.findOne(reportId);
			Assert.notNull(result);

			return result;
		}

		public Report save(Report report) {
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("USER");
			Authority au2 = new Authority();
			au2.setAuthority("MANAGER");

			Assert.isTrue(userAccount.getAuthorities().contains(au) || userAccount.getAuthorities().contains(au2));

			Report result;
			result = reportRepository.save(report);

			return result;
		}

		public void delete(Report report) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");

			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(report);

			Assert.isTrue(report.getId() != 0);
			reportRepository.delete(report);
		}
		// Other bussiness methods ----------------------------------------------------
		// Form methods ----------------------------------------------------------

				public ReportForm generateForm() {
					ReportForm result = new ReportForm();

					return result;
				}

				public Report reconstruct(ReportForm reportForm, BindingResult binding) {
					Report result;
					result = create();
					Reporter r=reporterService.findByPrincipal();
					Comment c=commentService.findOne(reportForm.getCommentId());
					result.setText(reportForm.getText());
					result.setReporter(r);
					result.setComment(c);
					Date d=new Date(System.currentTimeMillis()-1000);
					result.setMoment(d);
					validator.validate(result, binding);
					return result;
				}
	}


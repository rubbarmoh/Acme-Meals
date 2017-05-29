
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;
import domain.Report;
import forms.ReportForm;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractController {

	//Services--------------------------------------------------

	@Autowired
	private ReportService	reportService;


	//Constructor-----------------------------------------------

	public ReportController() {
		super();
	}
	//Creation-------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int commentId) {

		ModelAndView result;
		ReportForm reportForm;

		reportForm = reportService.generateForm();
		reportForm.setCommentId(commentId);
		result = createEditModelAndView(reportForm, null);
		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ReportForm reportForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();
		Report report;
		Report aux;
		if (binding.hasErrors()) {
			result = createEditModelAndView(reportForm, null);
		} else {
			try {
				report = reportService.reconstruct(reportForm, binding);
				aux = reportService.save(report);
				int id = aux.getComment().getRestaurant().getId();
				result = new ModelAndView("redirect:../restaurant/display.do?restaurantId=" + id);

			} catch (Throwable oops) {
				String msgCode;
				msgCode = "report.err";
				result = createEditModelAndView(reportForm, msgCode);
			}
		}
		return result;
	}
	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(ReportForm reportForm, String message) {
		ModelAndView result;

		result = new ModelAndView("report/edit");
		result.addObject("reportForm", reportForm);
		result.addObject("message", message);

		return result;

	}
}

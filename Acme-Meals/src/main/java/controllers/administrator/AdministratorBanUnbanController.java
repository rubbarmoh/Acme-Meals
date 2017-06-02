
package controllers.administrator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.ReportService;
import services.UserService;
import controllers.AbstractController;
import domain.Comment;
import domain.Report;
import domain.User;

@Controller
@RequestMapping("/administrator/banUnban")
public class AdministratorBanUnbanController extends AbstractController {

	//Services-------------------------

	@Autowired
	private UserService		userService;

	@Autowired
	private CommentService	commentService;

	@Autowired
	private ReportService	reportService;


	//Constructor----------------------

	public AdministratorBanUnbanController() {
		super();
	}

	//Browse-------------------------------------------------------

	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView browse() {
		ModelAndView result;
		Map<User, Long> map;
		map = userService.findReported();

		result = new ModelAndView("user/browseReported");
		result.addObject("users", map.keySet());
		result.addObject("num", map);
		result.addObject("requestURI", "user/browseReported.do");

		return result;
	}

	//Browse-------------------------------------------------------

	@RequestMapping(value = "/browseBanned", method = RequestMethod.GET)
	public ModelAndView browseBanned() {
		ModelAndView result;
		Map<User, Long> map;
		map = userService.findBanned();

		result = new ModelAndView("user/browseBanned");
		result.addObject("users", map.keySet());
		result.addObject("num", map);
		result.addObject("requestURI", "user/browseBanned.do");

		return result;
	}

	// Comment reported -------------------------------------------

	@RequestMapping(value = "/commentReported", method = RequestMethod.GET)
	public ModelAndView commentReported(@RequestParam int userId) {
		ModelAndView result;
		List<Comment> comments;
		comments = commentService.findReportedComment(userId);

		Map<Comment, List<Report>> mapa = new HashMap<Comment, List<Report>>();

		for (Comment c : comments) {
			mapa.put(c, (List<Report>) c.getReports());
		}

		result = new ModelAndView("comment/browseReported");
		result.addObject("comments", comments);
		result.addObject("mapa", mapa);
		result.addObject("requestURI", "comment/browseReported.do");

		return result;
	}

	//BanUnban -----

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam int userId) {
		ModelAndView result;
		User user = userService.findOne(userId);
		try {
			if(user.getBanned()==false){
				userService.ban(user);
			
			}
			result = browse();
		}catch (Throwable oops) {
			result = browse();
			result.addObject("message", "master-page.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam int userId) {
		ModelAndView result;
		User user = userService.findOne(userId);
		try {
			if(user.getBanned()==true){
				userService.unban(user);
			}
			result = browse();
		
		} catch (Throwable oops) {
			result = browse();
			result.addObject("message", "master-page.commit.error");
		}

		return result;
	}

	// Delete report -------------------------------------------------------
	@RequestMapping(value = "/deleteReport", method = RequestMethod.GET)
	public ModelAndView deleteReport(@RequestParam int reportId) {
		ModelAndView result;
		Report report = reportService.findOne(reportId);
		try {
			reportService.delete(report);
			result = browse();
		} catch (Throwable oops) {
			result = browse();
			result.addObject("message", "master-page.commit.error");
		}

		return result;
	}
}

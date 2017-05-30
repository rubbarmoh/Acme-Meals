package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RestaurantService;
import controllers.AbstractController;
import domain.Comment;
import domain.Restaurant;
import forms.CommentForm;

@Controller
@RequestMapping("user/comment/")
public class CommentController extends AbstractController{
	//Services--------------------------------------------------
	
	@Autowired 
	private CommentService commentService;
	
	@Autowired 
	private RestaurantService restaurantService;
	
	//Constructor-----------------------------------------------
	
	public CommentController(){
			super();
	}
	//Creation-------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int restaurantId) {

			ModelAndView result;
			CommentForm commentForm;

			commentForm = commentService.generateForm();
			commentForm.setRestaurantId(restaurantId);
			result = createEditModelAndView(commentForm, null);
			return result;

		}
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid CommentForm commentForm, BindingResult binding) {

			ModelAndView result = new ModelAndView();
			Comment comment;
			Comment aux;
			if (binding.hasErrors()) {
				result = createEditModelAndView(commentForm, null);
			} else {
				try {
					comment = commentService.reconstruct(commentForm, binding);
					aux = commentService.save(comment);
					Restaurant r = aux.getRestaurant();
					restaurantService.updateAvgStars(r.getId());
					int id = aux.getRestaurant().getId();
					result = new ModelAndView("redirect:../../restaurant/display.do?restaurantId="+id);

				} catch (Throwable oops) {
					String msgCode;
					msgCode = "comment.err";
					result = createEditModelAndView(commentForm, msgCode);
				}
			}
			return result;
		}
		//Ancillary Methods---------------------------

		protected ModelAndView createEditModelAndView(CommentForm commentForm, String message) {
			ModelAndView result;
			Collection<Integer> stars = new ArrayList<Integer>();
			stars.add(1);
			stars.add(2);
			stars.add(3);
			stars.add(4);
			stars.add(5);

			result = new ModelAndView("comment/edit");
			result.addObject("commentForm", commentForm);
			result.addObject("stars", stars);
			result.addObject("message", message);

			return result;

		}
}

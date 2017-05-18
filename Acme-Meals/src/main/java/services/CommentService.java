package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CommentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Restaurant;
import domain.User;
import forms.CommentForm;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------

		@Autowired
		private CommentRepository	commentRepository;


		// Supporting services ----------------------------------------------------
		@Autowired
		private UserService	userService;
		@Autowired
		private RestaurantService	restaurantService;
		@Autowired
		private Validator	validator;
		// Constructors -----------------------------------------------------------

		public CommentService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------

		public Comment create() {
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("USER");
			Assert.isTrue(userAccount.getAuthorities().contains(au));
			Comment result;
			result = new Comment();

			return result;
		}

		public Collection<Comment> findAll() {
			Collection<Comment> result;

			result = commentRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public Comment findOne(int commentId) {
			Comment result;

			result = commentRepository.findOne(commentId);
			Assert.notNull(result);

			return result;
		}

		public Comment save(Comment comment) {
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("USER");
			Assert.isTrue(userAccount.getAuthorities().contains(au));
			Assert.notNull(comment);

			Comment result;

			result = commentRepository.save(comment);

			return result;
		}

		public void delete(Comment comment) {
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("MANAGER");
			Assert.isTrue(userAccount.getAuthorities().contains(au));
			Assert.notNull(comment);
			Assert.isTrue(comment.getId() != 0);

			commentRepository.delete(comment);
		}
		public Collection<Comment> findAllOrderByMoment(Restaurant r){
			Collection<Comment>result;
			result=commentRepository.findAllOrderByMoment(r.getId());
			return result;
		}
		
		public List<Comment> findReportedComment(int userId){
			return commentRepository.findReportedComment(userId);
		}
		// Form methods ----------------------------------------------------------

		public CommentForm generateForm() {
			CommentForm result = new CommentForm();

			return result;
		}

		public Comment reconstruct(CommentForm commentForm, BindingResult binding) {
			Comment result;
			result = create();
			User u=userService.findByPrincipal();
			Restaurant r=restaurantService.findOne(commentForm.getRestaurantId());
			result.setUser(u);
			result.setRestaurant(r);
			result.setTitle(commentForm.getTitle());
			result.setText(commentForm.getText());
			result.setStars(commentForm.getStars());
			Date d=new Date(System.currentTimeMillis()-1000);
			result.setMoment(d);
			validator.validate(result, binding);
			return result;
		}

}

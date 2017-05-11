package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------

		@Autowired
		private CommentRepository	commentRepository;


		// Supporting services ----------------------------------------------------

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
}

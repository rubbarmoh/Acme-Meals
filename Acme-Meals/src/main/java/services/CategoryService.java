package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Category;

@Service
@Transactional
public class CategoryService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private CategoryRepository	categoryRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public CategoryService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Category create() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Category result;
		result = new Category();

		return result;
	}

	public Collection<Category> findAll() {
		Collection<Category> result;

		result = categoryRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Category findOne(int categoryId) {
		Category result;

		result = categoryRepository.findOne(categoryId);
		Assert.notNull(result);

		return result;
	}

	public Category save(Category category) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Assert.notNull(category);

		Category result;

		result = categoryRepository.save(category);

		return result;
	}

	public void delete(Category category) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Assert.notNull(category);
		Assert.isTrue(category.getId() != 0);

		categoryRepository.delete(category);
	}
}

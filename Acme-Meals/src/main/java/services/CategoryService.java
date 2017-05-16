package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CategoryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Category;
import domain.Manager;
import forms.CategoryForm;

@Service
@Transactional
public class CategoryService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private CategoryRepository	categoryRepository;


	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ManagerService	managerService;
	
	@Autowired
	private Validator		validator;
	


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
	
	// Other bussinnes methods ------------------------------------
	
	public Collection<Category> findCategoryByManager(){
		
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		
		Manager manager = managerService.findByPrincipal();
		
		Collection<Category> categories = categoryRepository.findCategoryByManger(manager.getId());
		
		return categories;
	}
	
	// Form methods -----------------------------------------------
	
	public CategoryForm generate(){
		
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		
		CategoryForm result = new CategoryForm();
		
		return result;
	}
	
	public CategoryForm generate(int categoryId){
		
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		
		CategoryForm categoryForm = new CategoryForm();
		Category category = findOne(categoryId);
		
		categoryForm.setId(categoryId);
		categoryForm.setName(category.getName());
		
		return categoryForm;
		
	}
	
	public Category reconstruct(CategoryForm categoryForm, BindingResult binding){
		
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		
		Manager manager = managerService.findByPrincipal();
		
		Category category = create();
		category.setManager(manager);
		category.setName(categoryForm.getName());
		
		validator.validate(category, binding);
		
		return category;
	}
	
}

package controllers.manager;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;
import forms.CategoryForm;

@Controller
@RequestMapping("/managerActor/category")
public class ManagerCategoryController extends AbstractController{
	
	// Services -----------------------------------------
	
	@Autowired
	private CategoryService categoryService;
	
	// Constructor --------------------------------------
	
	public ManagerCategoryController(){
		super();
	}
	
	// List ------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		
		Collection<Category> categories = categoryService.findCategoryByManager();
		
		result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		
		return result;
	}
	
	// Create -----------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		
		CategoryForm	categoryForm = categoryService.generate();
		result = new ModelAndView("category/edit");
		result.addObject("categoryForm", categoryForm);
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid CategoryForm categoryForm, BindingResult binding){
		ModelAndView result;
		Category category;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(categoryForm);
		}else{
			try{
				category = categoryService.reconstruct(categoryForm, binding);
				Assert.isTrue(!categoryService.exist(category));
				categoryService.save(category);
				result = new ModelAndView("redirect:/managerActor/category/list.do");
			}catch(Throwable oops){
				String msg = "category.edit.error";
				result = createEditModelAndView(categoryForm, msg);
			}
		}
		
		return result;
	}
	
	// Edit -------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId){
		ModelAndView result;
		
		CategoryForm	categoryForm = categoryService.generate(categoryId);
		result = new ModelAndView("category/edit");
		result.addObject("categoryForm", categoryForm);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult binding){
		ModelAndView result;
		Category category;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(categoryForm);
		}else{
			try{
				category = categoryService.reconstruct(categoryForm, binding);
				categoryService.save(category);
				result = new ModelAndView("redirect:/managerActor/category/list.do");
			}catch(Throwable oops){
				String msg = "category.edit.error";
				result = createEditModelAndView(categoryForm, msg);
			}
		}
		
		return result;
	}
	
	// Delete -----------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam int categoryId){
		ModelAndView result;
		Category category = categoryService.findOne(categoryId);
		
		try{
			Assert.isTrue(!categoryService.categoryUsed(category));
			categoryService.delete(category);
			result = new ModelAndView("redirect:/managerActor/category/list.do");			
		}catch(Throwable oops){
			String msgCode = "category.error.delete";
			result = createEditModelAndViewDelete(msgCode);
		}
		
		
		return result;
	}
	
	// Ancillary methods ---------------------------------------------------

	protected ModelAndView createEditModelAndView(CategoryForm categoryForm) {
		ModelAndView result;

		result = createEditModelAndView(categoryForm, null);

		return result;

	}
	
	protected ModelAndView createEditModelAndView(CategoryForm categoryForm, String message) {
		ModelAndView result;

		result = new ModelAndView("category/edit");
		result.addObject("categoryForm", categoryForm);
		result.addObject("message", message);

		return result;

	}
	
	protected ModelAndView createEditModelAndViewDelete(String message) {
		ModelAndView result;
		Collection<Category> categories = categoryService.findCategoryByManager();

		result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		result.addObject("message", message);

		return result;

	}

}

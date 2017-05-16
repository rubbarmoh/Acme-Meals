package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	// Edit -------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId){
		ModelAndView result;
		
		CategoryForm	categoryForm = categoryService.generate(categoryId);
		result = new ModelAndView("category/edit");
		result.addObject("categoryForm", categoryForm);
		
		return result;
	}
	
	// Delete -----------------------------------------------

}

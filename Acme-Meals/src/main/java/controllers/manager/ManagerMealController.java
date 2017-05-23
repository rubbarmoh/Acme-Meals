
package controllers.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import services.MealService;
import controllers.AbstractController;
import domain.Category;
import domain.Meal;
import forms.MealForm;

@Controller
@RequestMapping("/managerActor/meal")
public class ManagerMealController extends AbstractController {

	//Services-------------------------

	@Autowired
	private MealService		mealService;

	@Autowired
	private CategoryService	categoryService;


	//Constructor----------------------

	public ManagerMealController() {
		super();
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int restaurantId) {

		ModelAndView result;
		MealForm mealForm;

		mealForm = mealService.generateForm();

		mealForm.setrId(restaurantId);

		result = new ModelAndView("meal/create");
		result.addObject("mealForm", mealForm);
		result.addObject("categories", getCategories());

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int mealId) {

		ModelAndView result;
		Meal meal;

		meal = mealService.findOne(mealId);
		MealForm mealForm = mealService.transform(meal);
		Assert.notNull(meal);
		result = new ModelAndView("meal/edit");
		result.addObject("mealForm", mealForm);
		result.addObject("categories", getCategories());

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid MealForm mealForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();
		Meal meal;

		if (binding.hasErrors())
			if (mealForm.getId() != 0) {
				result = createEditModelAndView(mealForm, null);
			} else {
				result = createEditModelAndView2(mealForm, null);
			}
		else
			try {
				meal = mealService.reconstruct(mealForm, binding);

				mealService.save(meal);
				result = new ModelAndView("redirect:../../meal/browse.do?restaurantId=" + mealForm.getrId());

			} catch (Throwable oops) {
				String msgCode = "meal.save.error";

				if (mealForm.getId() != 0) {
					result = createEditModelAndView(mealForm, msgCode);
				} else {
					result = createEditModelAndView2(mealForm, msgCode);
				}
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(MealForm mealForm, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = createEditModelAndView(mealForm, null);
		else
			try {
				Meal meal = mealService.reconstruct(mealForm, binding);
				mealService.delete(meal);
				result = new ModelAndView("redirect:../../meal/browse.do?restaurantId=" + mealForm.getrId());
			} catch (Throwable oops) {
				String msgCode = "meal.save.error";

				result = createEditModelAndView(mealForm, msgCode);
			}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(Meal meal, String message) {
		ModelAndView result;

		result = new ModelAndView("meal/edit");
		result.addObject("meal", meal);
		result.addObject("message", message);
		result.addObject("categories", getCategories());
		return result;

	}

	protected ModelAndView createEditModelAndView(MealForm meal, String message) {
		ModelAndView result;

		result = new ModelAndView("meal/edit");
		result.addObject("meal", meal);
		result.addObject("categories", getCategories());
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView2(MealForm meal, String message) {
		ModelAndView result;

		result = new ModelAndView("meal/create");
		result.addObject("meal", meal);
		result.addObject("categories", getCategories());
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView(Meal meal) {
		ModelAndView result;

		result = createEditModelAndView(meal, null);

		return result;

	}

	private Map<Integer, String> getCategories() {
		Collection<Category> cs;
		cs = categoryService.findCategoryByManager();

		Map<Integer, String> categories = new HashMap<Integer, String>();
		for (Category c : cs) {
			categories.put(c.getId(), c.getName());
		}
		return categories;
	}

}

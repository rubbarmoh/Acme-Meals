package controllers.user;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.MealOrder;
import domain.Quantity;
import forms.QuantityForm;
import services.MealOrderService;
import services.QuantityService;
@Controller
@RequestMapping("user/quantity")
public class QuantityController extends AbstractController{
	//Service
		@Autowired
		private QuantityService quantityService;
		@Autowired
		private MealOrderService mealOrderService;
		//Constructor-----------------------------------------------

public QuantityController(){
		super();
}
//Creation-------------------------

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam int mealOrderId,int mealId,int restaurantId) {

		ModelAndView result;
		QuantityForm quantityForm;

		quantityForm = quantityService.generateForm();
		quantityForm.setRestaurantId(restaurantId);
		quantityForm.setMealId(mealId);
		quantityForm.setMealOrderId(mealOrderId);
		result = createEditModelAndView(quantityForm, null);
		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid QuantityForm quantityForm, BindingResult binding) {

		ModelAndView result = new ModelAndView();
		Quantity quantity;
		Quantity aux;
		
		MealOrder mealOrder=mealOrderService.findOne(quantityForm.getMealOrderId());
		if (binding.hasErrors()) {
			result = createEditModelAndView(quantityForm, null);
		} else {
			try {
				quantity =quantityService.reconstruct(quantityForm, binding);
				aux = quantityService.save(quantity);
				mealOrder=aux.getMealOrder();
				mealOrderService.updateAmount(mealOrder.getId());
				int id = quantityForm.getRestaurantId();
				result = new ModelAndView("redirect:../mealOrder/morder.do?restaurantId="+id);

			} catch (Throwable oops) {
				String msgCode;
				msgCode = "quantity.err";
				result = createEditModelAndView(quantityForm, msgCode);
			}
		}
		return result;
	}
	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(QuantityForm quantityForm, String message) {
		ModelAndView result;
		

		result = new ModelAndView("quantity/edit");
		result.addObject("quantityForm",quantityForm);
		result.addObject("message", message);

		return result;

	}
	}

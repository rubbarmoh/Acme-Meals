package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MealOrderService;
import domain.MealOrder;

@Controller
@RequestMapping("/mealOrder")
public class MealOrderController extends AbstractController{
	
	// Services --------------------------------------------
	
	@Autowired
	private MealOrderService	mealOrderService;
	
	// Constructor ------------------------------------------
	
	public MealOrderController(){
		super();
	}
	
	// Browse ----------------------------------------------
	
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView browse(){
		ModelAndView result;
		
		Collection<MealOrder> mealOrders = mealOrderService.findByUser();
		
		result = new ModelAndView("mealOrder/browse");
		result.addObject("mealOrders", mealOrders);
		result.addObject("requestURI", "mealOrder/browse.do");
		
		return result;
	}
	
	@RequestMapping(value="/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int mealOrderId){
		ModelAndView result;
		
		MealOrder mealOrder = mealOrderService.findOne(mealOrderId);
		
		result = new ModelAndView("mealOrder/display");
		result.addObject("mealOrder", mealOrder);
		result.addObject("quantities", mealOrder.getQuantities());
		
		return result;
	}

}

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
	
	@RequestMapping(value = "/browseByUser", method = RequestMethod.GET)
	public ModelAndView browseByUser(){
		ModelAndView result;
		
		Collection<MealOrder> mealOrders = mealOrderService.findByUser();
		
		result = new ModelAndView("mealOrder/browse");
		result.addObject("mealOrders", mealOrders);
		result.addObject("requestURI", "mealOrder/browseByUser.do");
		
		return result;
	}
	
	@RequestMapping(value = "/browseCurrentlyByUser", method = RequestMethod.GET)
	public ModelAndView browseCurrentlyByUser(){
		ModelAndView result;
		
		Collection<MealOrder> mealOrders = mealOrderService.findCurrentlyByUser();
		
		result = new ModelAndView("mealOrder/browseCurrently");
		result.addObject("mealOrders", mealOrders);
		result.addObject("requestURI", "mealOrder/browseCurrentlyByUser.do");
		
		return result;
	}
	
	@RequestMapping(value = "/browseByManager", method = RequestMethod.GET)
	public ModelAndView browseByManager(){
		ModelAndView result;
		
		Collection<MealOrder> mealOrders = mealOrderService.findByManager();
		
		result = new ModelAndView("mealOrder/browse");
		result.addObject("mealOrders", mealOrders);
		result.addObject("requestURI", "mealOrder/browseByManager.do");
		
		return result;
	}
	
	@RequestMapping(value = "/browseCurrentlyByManager", method = RequestMethod.GET)
	public ModelAndView browseCurrentlyByManager(){
		ModelAndView result;
		
		Collection<MealOrder> mealOrders = mealOrderService.findCurrentlyByManager();
		
		result = new ModelAndView("mealOrder/browseCurrently");
		result.addObject("mealOrders", mealOrders);
		result.addObject("requestURI", "mealOrder/browseCurrentlyByManager.do");
		
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
	
	// Steps -----------------------------------------------------
	@RequestMapping(value = "/step", method = RequestMethod.GET)
	public ModelAndView step(@RequestParam int mealOrderId){
		ModelAndView result;
		MealOrder	mealOrder;
		
		mealOrder = mealOrderService.findOne(mealOrderId);
		
		if(mealOrder.getStatus().equals("PENDING")){
			mealOrder.setStatus("INPROGRESS");
		}else{
			mealOrder.setStatus("FINISHED");
		}
		
		mealOrderService.save(mealOrder);
		
		Collection<MealOrder> mealOrders = mealOrderService.findByUser();
		
		result = new ModelAndView("mealOrder/browse");
		result.addObject("mealOrders", mealOrders);
		result.addObject("requestURI", "mealOrder/browse.do");
		
		return result;
	}

}

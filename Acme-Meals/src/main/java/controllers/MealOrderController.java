package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
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
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("MANAGER");
		Authority au2 = new Authority();
		au2.setAuthority("USER");
		MealOrder mealOrder = mealOrderService.findOne(mealOrderId);		
		
		if((mealOrder!=null && userAccount.getAuthorities().contains(au) && mealOrder.getRestaurant().getManager().getUserAccount().getUsername().equals(LoginService.getPrincipal().getUsername()))
				|| ( userAccount.getAuthorities().contains(au2) && mealOrder!=null && mealOrder.getUser().getUserAccount().getUsername().equals(LoginService.getPrincipal().getUsername()))){
			result = new ModelAndView("mealOrder/display");
			result.addObject("mealOrder", mealOrder);
			result.addObject("quantities", mealOrder.getQuantities());
			
		}else{
			if(userAccount.getAuthorities().contains(au2)){
				String msgCode;
				msgCode = "mealOrder.notAuthored";
				
				result = browseByUser();
				result.addObject("message", msgCode);
			}else{
				String msgCode;
				msgCode = "mealOrder.notAuthored";
				
				result = browseByManager();
				result.addObject("message", msgCode);
			}
			
		}
		
		return result;
	}
	
	// Steps -----------------------------------------------------
	
	@RequestMapping(value = "/step", method = RequestMethod.GET)
	public ModelAndView step(@RequestParam int mealOrderId){
		MealOrder	mealOrder;
		
		mealOrder = mealOrderService.findOne(mealOrderId);
		
		mealOrderService.step(mealOrder);
		
		return browseCurrentlyByManager();
	}
	
	// Delete -----------------------------------------
	
	@RequestMapping(value="/display", method = RequestMethod.POST, params ="delete")
	public ModelAndView delete(@RequestParam int mealOrderId){
		ModelAndView result;
		MealOrder mealOrder = mealOrderService.findOne(mealOrderId);
		
		try{
			mealOrderService.delete(mealOrder);
			result = new ModelAndView("redirect:/mealOrder/browseByUser.do");
		}catch(Throwable oops){
			String msgCode = "mealOrder.error.delete";
			result = createEditModelAndViewDelete(msgCode);
		}
		
		return result;
	}
	
	// Ancillary methods ---------------------------------------
	
	protected ModelAndView createEditModelAndViewDelete() {
		ModelAndView result;

		result = createEditModelAndViewDelete(null);

		return result;

	}

	protected ModelAndView createEditModelAndViewDelete(String message) {
		ModelAndView result;

		result = new ModelAndView("mealOrder/browse");

		return result;
	}
}

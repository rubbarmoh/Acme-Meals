package controllers.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MealOrderService;
import services.QuantityService;
import services.RestaurantService;
import services.UserService;
import controllers.AbstractController;
import domain.MealOrder;
import domain.Quantity;
import domain.Restaurant;
import domain.User;

@Controller
@RequestMapping("user/mealOrder")
public class UserMealOrderController extends AbstractController{
	//Service
	@Autowired
	private MealOrderService mealOrderService;
	
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private QuantityService quantityService;
	
	@Autowired
	private UserService userService;
	
	//Constructor
	public UserMealOrderController(){
		super();
	}
	//Make Order
	// Like ---------------------------------------------
		@RequestMapping(value = "/morder", method = RequestMethod.GET)
		public ModelAndView morder(@RequestParam int restaurantId) {
			ModelAndView result;
			MealOrder mealOrder=new MealOrder();
			
			User user;
			Restaurant restaurant;

			restaurant = restaurantService.findOne(restaurantId);
			Boolean aux=false;
			user = userService.findByPrincipal();
			for(MealOrder m: user.getMealOrders()){
				if(m.getRestaurant().equals(restaurant) && m.getStatus().equals("DRAFT")){
					mealOrder=m;
					aux=true;
				}
			}
			if(aux.equals(false)){
				mealOrder = mealOrderService.create();
				mealOrder.setRestaurant(restaurant);
				mealOrder.setUser(user);
				mealOrder.setStatus("DRAFT");
				mealOrder.setAmount(0.0);
				mealOrder.setPickUp(false);
				Date d=new Date(System.currentTimeMillis()-1000);
				mealOrder.setMoment(d);
				mealOrder=mealOrderService.save(mealOrder);
			}			
			result = new ModelAndView("mealOrder/morder");
			result.addObject("restaurant", restaurant);
			result.addObject("mealOrder", mealOrder);
			result.addObject("meals", restaurant.getMeals());
			result.addObject("quantities", mealOrder.getQuantities());
			result.addObject("requestURI", "mealOrder/morder.do");

			return result;
		}
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@RequestParam int mealOrderId) {

			ModelAndView result= new ModelAndView();
			MealOrder mealOrder;
			mealOrder=mealOrderService.findOne(mealOrderId);
			int restaurantId=mealOrder.getRestaurant().getId();
			if(mealOrder.getStatus().equals("DRAFT")){
				for(Quantity q:mealOrder.getQuantities()){
					quantityService.delete(q);
				}
				mealOrderService.delete(mealOrder);
				
				result = new ModelAndView("redirect:../../restaurant/display.do?restaurantId="+restaurantId);
			}
				
			
			return result;

		}

}

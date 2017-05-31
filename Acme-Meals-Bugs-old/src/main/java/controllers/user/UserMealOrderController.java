package controllers.user;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InvoiceService;
import services.MealOrderService;
import services.QuantityService;
import services.RestaurantService;
import services.UserService;
import services.VATNumberService;
import controllers.AbstractController;
import domain.Invoice;
import domain.MealOrder;
import domain.Quantity;
import domain.Restaurant;
import domain.User;
import domain.VATNumber;
import forms.MealOrderForm;


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
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private VATNumberService vatNumberService;
	
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
		//Creation-------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int mealOrderId,int restaurantId) {

			ModelAndView result;
			MealOrderForm mealOrderForm;

			mealOrderForm = mealOrderService.generateForm();
			mealOrderForm.setRestaurantId(restaurantId);
			mealOrderForm.setMealOrderId(mealOrderId);


			result = createEditModelAndView(mealOrderForm, null);
			return result;

		}
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid MealOrderForm mealOrderForm, BindingResult binding) {

			ModelAndView result = new ModelAndView();
			MealOrder mealOrder;
			MealOrder aux;
			Invoice invoice;
			Invoice aux2;
			String v="";
			Collection<VATNumber>vat;
			
			vat=vatNumberService.findAll();
			for(VATNumber c:vat){
				if(c!=null){
					v=c.getValue();
					break;
				}
			}
			if (binding.hasErrors()) {
				result = createEditModelAndView(mealOrderForm, null);
			} else {
				try {
					mealOrder =mealOrderService.reconstruct(mealOrderForm, binding);
					Restaurant r=restaurantService.findOne(mealOrderForm.getRestaurantId());
					mealOrder.setStatus("PENDING");
					if(r.getMinimunAmount()!=null){
						Double am=mealOrder.getAmount()+r.getCostDelivery();
						mealOrder.setAmount(am);
					}
					aux = mealOrderService.save(mealOrder);
					invoice=invoiceService.create();
					invoice.setMealOrder(aux);
					Date d= new Date(System.currentTimeMillis()-1000);
					invoice.setMoment(d);
					invoice.setName(aux.getUser().getName());
					invoice.setSurname(aux.getUser().getSurname());
					invoice.setVatNumber(v);
					invoice.setDescription("Se ha realizado el pago con la tarjeta ************"+ mealOrder.getUser().getCreditCard().getNumber().substring(12)+ " una cantidad de "+mealOrder.getAmount());
					aux2=invoiceService.save(invoice);
					int id = aux2.getId();
					result = new ModelAndView("redirect:../../invoice/display.do?invoiceId="+id);

				} catch (Throwable oops) {
					String msgCode;
					msgCode = "mealOrder.err";
					if (oops.getMessage().equals("pickUpMarked")) {
						msgCode = "mealOrder.pickUpMarked";
					} else if (oops.getMessage().equals("adressNotValid")) {
						msgCode = "mealOrder.adressNotValid";
					} else if (oops.getMessage().equals("noDeliveryService")){
						msgCode="mealOrder.noDeliveryService";
					}
					result = createEditModelAndView(mealOrderForm, msgCode);
				}
			}
			return result;
		}
		//Ancillary Methods---------------------------

		protected ModelAndView createEditModelAndView(MealOrderForm mealOrderForm, String message) {
			ModelAndView result;
			

			result = new ModelAndView("mealOrder/edit");
			result.addObject("mealOrderForm",mealOrderForm);
			result.addObject("message", message);

			return result;

		}

}

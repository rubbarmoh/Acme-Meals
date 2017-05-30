package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class MealOrderForm {
	private String deliveryAdress;
	private Boolean pickUp;
	private int mealOrderId;
	private int restaurantId;
	@NotNull
	public int getMealOrderId() {
		return mealOrderId;
	}
	public void setMealOrderId(int mealOrderId) {
		this.mealOrderId = mealOrderId;
	}
	@NotNull
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDeliveryAdress() {
		return deliveryAdress;
	}
	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}
	public Boolean getPickUp() {
		return pickUp;
	}
	public void setPickUp(Boolean pickUp) {
		this.pickUp = pickUp;
	}
	

}

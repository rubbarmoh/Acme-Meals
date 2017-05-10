
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Quantity extends DomainEntity {

	// Attributes --------------------------------------

	private Integer	quantity;


	// Getters and Setters -----------------------------

	@NotNull
	@Min(1)
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	// Relationships -----------------------------------
	private MealOrder mealOrder;
	private Meal meal;

	@Valid
	@ManyToOne(optional = false)
	public MealOrder getMealOrder() {
		return mealOrder;
	}
	public void setMealOrder(MealOrder mealOrder) {
		this.mealOrder = mealOrder;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	
	
}

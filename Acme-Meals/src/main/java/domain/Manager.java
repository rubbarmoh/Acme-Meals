
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Reporter {

	// Attributes --------------------------------------

	private CreditCard	creditCard;


	// Getters and Setters -----------------------------

	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	// Relationships -----------------------------------
	private Collection<MonthlyBill> monthlyBills;
	private Collection<Restaurant> restaurants;

	@Valid
	@OneToMany(mappedBy = "manager")
	public Collection<MonthlyBill> getMonthlyBills() {
		return monthlyBills;
	}
	public void setMonthlyBills(Collection<MonthlyBill> monthlyBills) {
		this.monthlyBills = monthlyBills;
	}

	@Valid
	@OneToMany(mappedBy = "manager")
	public Collection<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(Collection<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	
}


package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Reporter {

	// Attributes --------------------------------------

	// Getters and Setters -----------------------------

	// Relationships -----------------------------------
	private Collection<MonthlyBill>	monthlyBills;
	private Collection<Restaurant>	restaurants;
	private Collection<Category>	categories;


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
	
	@Valid
	@OneToMany(mappedBy = "manager")
	public Collection<Category> getCategories() {
		return categories;
	}
	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

}

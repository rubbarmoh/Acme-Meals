
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Meal extends DomainEntity {

	// Attributes --------------------------------------

	private String	title;
	private String	description;
	private Double	price;
	private Boolean	erased;


	// Getters and Setters -----------------------------

	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@NotNull
	public Boolean getErased() {
		return erased;
	}
	public void setErased(Boolean erased) {
		this.erased = erased;
	}

	// Relationships -----------------------------------
	private Collection<Quantity> quantities;
	private Restaurant restaurant;
	private Category category;

	@Valid
	@OneToMany(mappedBy = "meal")
	public Collection<Quantity> getQuantities() {
		return quantities;
	}
	public void setQuantities(Collection<Quantity> quantities) {
		this.quantities = quantities;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}

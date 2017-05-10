
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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

	@NotNull
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
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

}

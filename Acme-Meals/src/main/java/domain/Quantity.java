
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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

}

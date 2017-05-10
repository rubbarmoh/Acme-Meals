
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class VATNumber extends DomainEntity {

	// Attributes --------------------------------------

	private String	value;


	// Getters and Setters -----------------------------

	@NotNull
	@NotBlank
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	// Relationships -----------------------------------

}

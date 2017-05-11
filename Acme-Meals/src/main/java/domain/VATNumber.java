
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class VATNumber extends DomainEntity {

	// Attributes --------------------------------------

	private String	value;


	// Getters and Setters -----------------------------

	@NotNull
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	// Relationships -----------------------------------

}


package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Reporter {

	// Attributes --------------------------------------

	private Boolean	banned;


	// Getters and Setters -----------------------------

	@NotNull
	public Boolean getBanned() {
		return banned;
	}

	public void setBanned(Boolean banned) {
		this.banned = banned;
	}

	// Relationships -----------------------------------

}

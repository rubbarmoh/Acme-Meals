
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
public class Reporter extends Actor {

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
	private Collection<Report>	reports;


	@Valid
	@OneToMany(mappedBy = "reporter")
	public Collection<Report> getReports() {
		return reports;
	}
	public void setReports(Collection<Report> reports) {
		this.reports = reports;
	}

}

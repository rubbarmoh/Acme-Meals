
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Order extends DomainEntity {

	// Attributes --------------------------------------

	private Date		moment;
	private CreditCard	creditCard;
	private Double		amount;
	private String		status;
	private Boolean		pickUp;


	// Getters and Setters -----------------------------

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Pattern(regexp = "^DRAFT$|^PENDING$|^INPROGRESS$|^FINISHED$")
	@NotNull
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@NotNull
	public Boolean getPickUp() {
		return pickUp;
	}
	public void setPickUp(Boolean pickUp) {
		this.pickUp = pickUp;
	}

	// Relationships -----------------------------------

}

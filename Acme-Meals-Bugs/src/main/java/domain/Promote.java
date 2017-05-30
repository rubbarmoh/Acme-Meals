
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Promote extends DomainEntity {

	// Attributes --------------------------------------

	private Date	beginning;
	private Date	ending;
	private Integer	timesDisplayed;
	private Integer	totalDisplayed;


	// Getters and Setters -----------------------------

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getBeginning() {
		return beginning;
	}
	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEnding() {
		return ending;
	}
	public void setEnding(Date ending) {
		this.ending = ending;
	}

	@Min(0)
	@NotNull
	public Integer getTimesDisplayed() {
		return timesDisplayed;
	}
	public void setTimesDisplayed(Integer timesDisplayed) {
		this.timesDisplayed = timesDisplayed;
	}

	@Min(0)
	@NotNull
	public Integer getTotalDisplayed() {
		return totalDisplayed;
	}
	public void setTotalDisplayed(Integer totalDisplayed) {
		this.totalDisplayed = totalDisplayed;
	}


	// Relationships -----------------------------------
	private Restaurant	restaurant;


	@Valid
	@ManyToOne(optional = false)
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}

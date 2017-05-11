
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	// Attributes --------------------------------------

	private String	text;
	private Date	moment;


	// Getters and Setters -----------------------------

	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	// Relationships -----------------------------------
	private Reporter reporter;
	private Comment comment;

	@Valid
	@ManyToOne(optional = false)
	public Reporter getReporter() {
		return reporter;
	}
	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
}

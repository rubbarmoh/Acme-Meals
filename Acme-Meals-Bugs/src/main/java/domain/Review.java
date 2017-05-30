
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Review extends DomainEntity {

	// Attributes --------------------------------------

	private String	title;
	private String	text;
	private Integer	rate;


	// Getters and Setters -----------------------------

	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Range(min = 1, max = 5)
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}


	// Relationships -----------------------------------

	private Critic						critic;
	private Restaurant					restaurant;
	private Collection<RelationLike>	relationLikes;
	private Collection<RelationDislike>	relationDislikes;


	@Valid
	@ManyToOne(optional = false)
	public Critic getCritic() {
		return critic;
	}
	public void setCritic(Critic critic) {
		this.critic = critic;
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
	@OneToMany(mappedBy = "review")
	public Collection<RelationLike> getRelationLikes() {
		return relationLikes;
	}
	public void setRelationLikes(Collection<RelationLike> relationLikes) {
		this.relationLikes = relationLikes;
	}

	@Valid
	@OneToMany(mappedBy = "review")
	public Collection<RelationDislike> getRelationDislikes() {
		return relationDislikes;
	}
	public void setRelationDislikes(Collection<RelationDislike> relationDislikes) {
		this.relationDislikes = relationDislikes;
	}
}

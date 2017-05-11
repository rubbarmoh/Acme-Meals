
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
	private Collection<RelationLike> relationLikes;
	private Collection<RelationDislike> relationDislikes;
	private Collection<MealOrder> mealOrders;
	private Collection<Comment> comments;

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<RelationLike> getRelationLikes() {
		return relationLikes;
	}
	public void setRelationLikes(Collection<RelationLike> relationLikes) {
		this.relationLikes = relationLikes;
	}
	
	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<RelationDislike> getRelationDislikes() {
		return relationDislikes;
	}
	public void setRelationDislikes(Collection<RelationDislike> relationDislikes) {
		this.relationDislikes = relationDislikes;
	}

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<MealOrder> getMealOrders() {
		return mealOrders;
	}
	public void setMealOrders(Collection<MealOrder> mealOrders) {
		this.mealOrders = mealOrders;
	}

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	
}

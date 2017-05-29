
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints =
	{ @UniqueConstraint(columnNames = {"name", "address", "city", "manager_id"})})
public class Restaurant extends DomainEntity {

	// Attributes --------------------------------------

	private String	name;
	private String	phone;
	private String	city;
	private String	address;
	private String	email;
	private String	picture;
	private Double	avgStars;
	private Boolean	deliveryService;
	private Double	costDelivery;
	private Double	minimunAmount;
	private Boolean erased;


	// Getters and Setters -----------------------------
	@NotNull
	public Boolean getErased() {
		return erased;
	}
	public void setErased(Boolean erased) {
		this.erased = erased;
	}
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Pattern(regexp = "^([+]\\d{1,3})?[ ]?(\\d{9})")
	@NotNull
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotBlank
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@URL
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@NotNull
	@Min(0)
	public Double getAvgStars() {
		return avgStars;
	}
	public void setAvgStars(Double avgStars) {
		this.avgStars = avgStars;
	}

	@NotNull
	public Boolean getDeliveryService() {
		return deliveryService;
	}
	public void setDeliveryService(Boolean deliveryService) {
		this.deliveryService = deliveryService;
	}

	public Double getCostDelivery() {
		return costDelivery;
	}
	public void setCostDelivery(Double costDelivery) {
		this.costDelivery = costDelivery;
	}

	public Double getMinimunAmount() {
		return minimunAmount;
	}
	public void setMinimunAmount(Double minimunAmount) {
		this.minimunAmount = minimunAmount;
	}


	// Relationships -----------------------------------
	private Manager						manager;
	private Collection<MealOrder>		mealOrders;
	private Collection<Meal>			meals;
	private Collection<Comment>			comments;
	private Collection<SocialIdentity>	socialIdentities;
	private Collection<Review>			reviews;
	private Collection<Promote>			promotes;


	@Valid
	@ManyToOne(optional = false)
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Valid
	@OneToMany(mappedBy = "restaurant")
	public Collection<MealOrder> getMealOrders() {
		return mealOrders;
	}
	public void setMealOrders(Collection<MealOrder> mealOrders) {
		this.mealOrders = mealOrders;
	}

	@Valid
	@OneToMany(mappedBy = "restaurant")
	public Collection<Meal> getMeals() {
		return meals;
	}
	public void setMeals(Collection<Meal> meals) {
		this.meals = meals;
	}

	@Valid
	@OneToMany(mappedBy = "restaurant")
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@OneToMany(mappedBy = "restaurant")
	public Collection<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}
	public void setSocialIdentities(Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	@Valid
	@OneToMany(mappedBy = "restaurant")
	public Collection<Review> getReviews() {
		return reviews;
	}
	public void setReviews(Collection<Review> reviews) {
		this.reviews = reviews;
	}

	@Valid
	@OneToMany(mappedBy = "restaurant")
	public Collection<Promote> getPromotes() {
		return promotes;
	}
	public void setPromotes(Collection<Promote> promotes) {
		this.promotes = promotes;
	}

}

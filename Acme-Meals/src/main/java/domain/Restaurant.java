
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
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


	// Getters and Setters -----------------------------

	@NotBlank
	@NotNull
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
	@NotNull
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@NotBlank
	@NotNull
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@NotBlank
	@NotNull
	@URL
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@NotNull
	@URL
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@NotNull
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

}

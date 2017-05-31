
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Embeddable
@Access(AccessType.PROPERTY)
public class RestaurantForm {

	private int		id;

	private String	name;
	private String	phone;
	private String	city;
	private String	address;
	private String	email;
	private String	picture;

	private Boolean	deliveryService;
	private Double	costDelivery;
	private Double	minimunAmount;
	private Boolean erased;
	
	public Boolean getErased() {
		return erased;
	}
	public void setErased(Boolean erased) {
		this.erased = erased;
	}
	@NotNull
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^([+]\\d{1,3})?[ ]?(\\d{9})")
	@NotNull
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@URL
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
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

}

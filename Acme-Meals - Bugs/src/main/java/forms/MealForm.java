
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Category;

@Embeddable
@Access(AccessType.PROPERTY)
public class MealForm {

	private int			id;

	private int			rId;

	private String		title;
	private String		description;
	private Double		price;
	private Category	category;


	@Valid
	@NotNull
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@NotNull
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}

}


package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class VATNumberForm {

	private int		id;
	private String	value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	@Pattern(regexp = "([A-Z]{2}-\\d{2,12})")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}

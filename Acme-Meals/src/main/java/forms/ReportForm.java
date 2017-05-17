package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class ReportForm {
	// Attributes --------------------------------------
	
	private int		commentId;
	private String	text;
	
	
	// Getters and Setters -----------------------------
	@NotNull
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}

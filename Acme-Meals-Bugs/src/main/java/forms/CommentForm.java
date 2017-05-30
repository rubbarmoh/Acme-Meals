package forms;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class CommentForm {
	// Attributes --------------------------------------
	
		private int		restaurantId;
		private String	title;
		private String	text;
		private Integer	stars;
		
		// Getters and Setters -----------------------------
		@NotNull
		public int getRestaurantId() {
			return restaurantId;
		}
		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}
			
		
		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}

		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}

		

		@Range(min = 1, max = 5)
		public Integer getStars() {
			return stars;
		}
		public void setStars(Integer stars) {
			this.stars = stars;
		}
}

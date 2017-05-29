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
public class SocialIdentityForm {
	// Attributes ----------------------------------------------------

		private int		id;
		private int restaurantId;
		private String	nick;
		private String	socialNetwork;
		private String	link;
		private String  picture;


		// Constructor --------------------------------------------------

		public SocialIdentityForm() {
			super();
		}

		@NotNull
		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}

		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getNick() {
			return nick;
		}


		public void setNick(String nick) {
			this.nick = nick;
		}

		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getSocialNetwork() {
			return socialNetwork;
		}


		public void setSocialNetwork(String socialNetwork) {
			this.socialNetwork = socialNetwork;
		}

		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getLink() {
			return link;
		}


		public void setLink(String link) {
			this.link = link;
		}

		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getPicture() {
			return picture;
		}


		public void setPicture(String picture) {
			this.picture = picture;
		}
		@NotNull
		public int getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}
		

}

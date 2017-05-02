package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Embeddable
@Access(AccessType.PROPERTY)
public class CacheTimeForm {
	// Attributes --------------------------------------
		private int id;
		private String time;

		
		// Getters and Setters -----------------------------

		
		@NotNull
		@Pattern(regexp="^(?:([01]?\\d|2[0-3]):([0-5]?\\d):)?([0-5]?\\d)$")
		public String getTime() {
			return time;
		}

		

		public void setTime(String time) {
			this.time = time;
		}
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
}

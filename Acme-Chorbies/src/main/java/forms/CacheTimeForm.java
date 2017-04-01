package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Embeddable
@Access(AccessType.PROPERTY)
public class CacheTimeForm {
	// Attributes --------------------------------------
		private int id;
		private Integer time;

		
		// Getters and Setters -----------------------------

		
		@NotNull
		@Min(0)
		public Integer getTime() {
			return time;
		}

		

		public void setTime(Integer time) {
			this.time = time;
		}
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
}

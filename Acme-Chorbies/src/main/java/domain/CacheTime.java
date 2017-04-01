
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Access(AccessType.PROPERTY)
public class CacheTime extends DomainEntity {

	// Attributes --------------------------------------

	private String	time;


	// Getters and Setters -----------------------------

	@NotNull
	@Pattern(regexp = "^(?:([01]?\\d|2[0-3]):([0-5]?\\d):)?([0-5]?\\d)$")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	// Relationships -----------------------------------

}

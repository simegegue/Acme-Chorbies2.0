
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class CacheTime extends DomainEntity {

	// Attributes --------------------------------------

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
	
	// Relationships -----------------------------------

}

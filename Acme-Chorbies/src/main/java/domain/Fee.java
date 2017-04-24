
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity {

	// Attributes --------------------------------------

	private Double	chorbiValue;
	private Double	managerValue;


	// Getters and Setters -----------------------------

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getChorbiValue() {
		return chorbiValue;
	}
	public void setChorbiValue(Double chorbiValue) {
		this.chorbiValue = chorbiValue;
	}

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getManagerValue() {
		return managerValue;
	}
	public void setManagerValue(Double managerValue) {
		this.managerValue = managerValue;
	}

	// Relationships -----------------------------------

}

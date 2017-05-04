
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity {

	// Attributes --------------------------------------

	private Double	chorbiValue;
	private Double	managerValue;


	// Getters and Setters -----------------------------

	public Double getChorbiValue() {
		return chorbiValue;
	}
	public void setChorbiValue(Double chorbiValue) {
		this.chorbiValue = chorbiValue;
	}

	public Double getManagerValue() {
		return managerValue;
	}
	public void setManagerValue(Double managerValue) {
		this.managerValue = managerValue;
	}

	// Relationships -----------------------------------

}

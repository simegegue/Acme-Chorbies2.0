
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public class ChorbiFee extends DomainEntity {

	// Attributes --------------------------------------

	private Double	value;


	// Getters and Setters -----------------------------

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getValue() {
		return this.value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

	// Relationships -----------------------------------

}

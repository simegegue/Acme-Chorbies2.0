
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Genre extends DomainEntity {

	// Attributes --------------------------------------

	private String	value;


	// Getters and Setters -----------------------------

	@NotBlank
	@Pattern(regexp = "^man$|^woman$|^none$")
	@Column(unique = true)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// Relationships -----------------------------------

}

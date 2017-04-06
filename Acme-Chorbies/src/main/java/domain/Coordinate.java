
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Coordinate {

	// Attributes --------------------------------------

	private String	country;
	private String	state;
	private String	province;
	private String	city;


	// Getters and Setters -----------------------------

	@NotBlank
	@Column(nullable=true)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@NotBlank
	@Column(nullable=true)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(nullable=true)
	public String getProvince() {
		return this.province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}

	@NotBlank
	@Column(nullable=true)	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// Relationships -----------------------------------

}

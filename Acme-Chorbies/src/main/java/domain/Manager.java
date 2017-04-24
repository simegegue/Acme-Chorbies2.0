
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Senders {

	// Attributes ---------------------------------------------

	private String		company;
	private String		vat;
	private CreditCard	creditCard;
	private Double		feeAmount;


	// Getters and Setters ------------------------------------

	@NotBlank
	public String getCompany() {
		return this.company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	@NotBlank
	public String getVat() {
		return this.vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getFeeAmount() {
		return this.feeAmount;
	}
	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}


	// Relationships -----------------------------------

	private Collection<Event>	events;


	@Valid
	@OneToMany(mappedBy = "manager")
	public Collection<Event> getEvents() {
		return events;
	}
	public void setEvents(Collection<Event> events) {
		this.events = events;
	}

}

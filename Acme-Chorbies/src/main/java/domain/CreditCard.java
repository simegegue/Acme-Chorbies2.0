
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

	// Attributes --------------------------------------

	private String	holderName;
	private String	brandName;
	private String	number;
	private int		expirationMonth;
	private int		expirationYear;
	private int		cvv;


	// Getters and Setters -----------------------------

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Column(nullable=true)
	public String getHolderName() {
		return this.holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@Pattern(regexp = "^Visa$|^MasterCard$|^Discover$|^Dinners$|^Amex$")
	@Column(nullable=true)
	public String getBrandName() {
		return this.brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(nullable=true)
	public String getNumber() {
		return this.number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	@Range(min = 1, max = 12)
	@Column(nullable=true)
	public int getExpirationMonth() {
		return this.expirationMonth;
	}
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@Min(2017)
	@Column(nullable=true)
	public int getExpirationYear() {
		return this.expirationYear;
	}
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	@Column(nullable=true)
	public int getCvv() {
		return this.cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	// Relationships -----------------------------------

}

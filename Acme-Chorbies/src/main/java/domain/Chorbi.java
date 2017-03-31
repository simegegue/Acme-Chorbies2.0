
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Chorbi extends Actor {

	// Attributes ---------------------------------------------

	private String					picture;
	private String					description;
	private Date					birthDate;
	private CreditCard				creditCard;
	private Genre					genre;
	private KindRelationShip		kindRelationship;
	private Coordinate				coordinate;
	private Boolean					banned;


	// Getters and Setters ------------------------------------

	@URL
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Past
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getBirthDate() {
		return this.birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Valid
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Valid
	public Genre getGenre() {
		return this.genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Valid
	public KindRelationShip getKindRelationship() {
		return this.kindRelationship;
	}
	public void setKindRelationship(KindRelationShip kindRelationship) {
		this.kindRelationship = kindRelationship;
	}

	@Valid
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Boolean getBanned() {
		return this.banned;
	}
	public void setBanned(Boolean banned) {
		this.banned = banned;
	}

	// Relationships -----------------------------------

}

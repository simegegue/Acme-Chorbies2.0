
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Chorbi extends Actor {

	// Attributes ---------------------------------------------

	private String		picture;
	private String		description;
	private Date		birthDate;
	private CreditCard	creditCard;
	private Coordinate	coordinate;
	private Boolean		banned;
	private Double		feeAmount;


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

	
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
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
	private Genre						genre;
	private KindRelationship			kindRelationship;
	private Collection<Chirp>			sent;
	private Collection<Chirp>			received;
	private Collection<RelationLike>	likesSent;
	private Collection<RelationLike>	likesReceived;
	private SearchTemplate				searchTemplate;
	private Collection<RelationEvent> relationEvent;



	@Valid
	@ManyToOne(optional = false)
	public Genre getGenre() {
		return this.genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Valid
	@ManyToOne(optional = false)
	public KindRelationship getKindRelationship() {
		return this.kindRelationship;
	}
	public void setKindRelationship(KindRelationship kindRelationship) {
		this.kindRelationship = kindRelationship;
	}

	@Valid
	@OneToMany(mappedBy = "sender")
	public Collection<Chirp> getSent() {
		return sent;
	}
	public void setSent(Collection<Chirp> sent) {
		this.sent = sent;
	}
	@Valid
	@OneToMany(mappedBy = "recipient")
	public Collection<Chirp> getReceived() {
		return received;
	}
	public void setReceived(Collection<Chirp> received) {
		this.received = received;
	}

	@Valid
	@OneToMany(mappedBy = "likeSender")
	public Collection<RelationLike> getLikesSent() {
		return likesSent;
	}
	public void setLikesSent(Collection<RelationLike> likesSent) {
		this.likesSent = likesSent;
	}
	@Valid
	@OneToMany(mappedBy = "likeRecipient")
	public Collection<RelationLike> getLikesReceived() {
		return likesReceived;
	}
	public void setLikesReceived(Collection<RelationLike> likesReceived) {
		this.likesReceived = likesReceived;
	}

	@Valid
	@OneToOne(optional = false)
	public SearchTemplate getSearchTemplate() {
		return searchTemplate;
	}
	public void setSearchTemplate(SearchTemplate searchTemplate) {
		this.searchTemplate = searchTemplate;
	}
	
	@Valid
	@OneToMany(mappedBy = "chorbi")
	public Collection<RelationEvent> getRelationEvents() {
		return relationEvent;
	}
	public void setRelationEvents(Collection<RelationEvent> relationEvent) {
		this.relationEvent = relationEvent;
	}

}

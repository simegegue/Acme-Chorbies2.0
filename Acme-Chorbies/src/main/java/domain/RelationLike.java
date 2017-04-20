
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class RelationLike extends DomainEntity {

	// Attributes --------------------------------------

	private Date	moment;
	private String	comment;
	private Integer	stars;


	// Getters and Setters -----------------------------

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


	// Relationships -----------------------------------
	private Chorbi	likeSender;
	private Chorbi	likeRecipient;


	@Valid
	@ManyToOne(optional = false)
	public Chorbi getLikeSender() {
		return likeSender;
	}
	public void setLikeSender(Chorbi likeSender) {
		this.likeSender = likeSender;
	}
	@Valid
	@ManyToOne(optional = false)
	public Chorbi getLikeRecipient() {
		return likeRecipient;
	}
	public void setLikeRecipient(Chorbi likeRecipient) {
		this.likeRecipient = likeRecipient;
	}

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Integer getStars() {
		return this.stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}

}

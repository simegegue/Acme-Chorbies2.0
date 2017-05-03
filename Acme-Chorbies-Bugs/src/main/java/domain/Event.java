
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Event extends DomainEntity {

	// Attributes ---------------------------------------------

	private String	title;
	private Date	moment;
	private String	description;
	private String	picture;
	private Integer	seatsOffered;


	// Getters and Setters ------------------------------------

	@NotNull
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

	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotNull
	public Integer getSeatsOffered() {
		return this.seatsOffered;
	}
	public void setSeatsOffered(Integer seatsOffered) {
		this.seatsOffered = seatsOffered;
	}


	// Relationships -----------------------------------

	private Manager						manager;
	private Collection<RelationEvent>	relationEvents;


	@Valid
	@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Valid
	@OneToMany(mappedBy = "event")
	public Collection<RelationEvent> getRelationEvents() {
		return relationEvents;
	}
	public void setRelationEvents(Collection<RelationEvent> relationEvents) {
		this.relationEvents = relationEvents;
	}

}


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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Event extends Actor {

	// Attributes ---------------------------------------------

	private String		title;
	private Date		moment;
	private String		description;
	private String		picture;
	private Integer		siteOffered;



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

	@Past
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotNull
	@Min(1)
	public Integer getSiteOffered() {
		return this.siteOffered;
	}
	public void setFeeAmount(Integer siteOffered) {
		this.siteOffered = siteOffered;
	}


	// Relationships -----------------------------------
	
	private Manager manager;
	private Collection<RelationEvent> relationEvent;
	
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
		return relationEvent;
	}
	public void setRelationEvents(Collection<RelationEvent> relationEvent) {
		this.relationEvent = relationEvent;
	}
	
}

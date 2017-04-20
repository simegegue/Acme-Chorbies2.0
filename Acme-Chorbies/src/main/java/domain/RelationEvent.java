
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
@Entity
@Access(AccessType.PROPERTY)
public class RelationEvent extends Actor {

	// Attributes ---------------------------------------------


	// Getters and Setters ------------------------------------


	// Relationships -----------------------------------
	
	private Event event;
	private Chorbi chorbi;
	
	@Valid
	@ManyToOne(optional = false)
	public Event getEvent() {
		return this.event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Chorbi getChorbi() {
		return this.chorbi;
	}
	public void setChorbi(Chorbi chorbi) {
		this.chorbi = chorbi;
	}
}

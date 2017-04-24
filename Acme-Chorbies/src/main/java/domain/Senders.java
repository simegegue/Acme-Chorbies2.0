package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Senders extends Actor{
	
	// Attributes -------------------------------------------
	
	// Getters and Setters ----------------------------------
	
	// Relationships ----------------------------------------
	
	private Collection<Chirp> sent;
	
	@Valid
	@OneToMany(mappedBy = "sender")
	public Collection<Chirp> getSent() {
		return sent;
	}
	public void setSent(Collection<Chirp> sent) {
		this.sent = sent;
	}

}

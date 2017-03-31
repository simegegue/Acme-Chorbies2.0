
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Chirp extends DomainEntity {

	// Attributes --------------------------------------

	private Date	moment;
	private String	subject;
	private String	text;
	private Collection<String>	attachment;


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

	@NotBlank
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ElementCollection
	public Collection<String> getAttachment() {
		return attachment;
	}
	public void setAttachment(Collection<String> attachment) {
		this.attachment = attachment;
	}

	// Relationships -----------------------------------
	private Chorbi	sender;
	private Chorbi	recipient;


	@Valid
	@ManyToOne(optional = false)
	public Chorbi getSender() {
		return sender;
	}
	public void setSender(Chorbi sender) {
		this.sender = sender;
	}
	@Valid
	@ManyToOne(optional = false)
	public Chorbi getRecipient() {
		return recipient;
	}
	public void setRecipient(Chorbi recipient) {
		this.recipient = recipient;
	}

}

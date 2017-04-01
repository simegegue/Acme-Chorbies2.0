
package forms;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Chorbi;

@Embeddable
@Access(AccessType.PROPERTY)
public class ChirpForm {

	// Attributes -----------------------------------------

	private String				subject;
	private String				text;
	private Collection<String>	attachment;
	private Chorbi				sender;
	private Chorbi				recipient;


	// Getters and Setters --------------------------------
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSubject() {
		return subject;
	}
	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Collection<String> getAttachment() {
		return attachment;
	}
	public void setAttachment(Collection<String> attachement) {
		this.attachment = attachement;
	}

	public Chorbi getSender() {
		return sender;
	}
	public void setSender(final Chorbi sender) {
		this.sender = sender;
	}

	public Chorbi getRecipient() {
		return recipient;
	}

	public void setRecipient(final Chorbi recipient) {
		this.recipient = recipient;
	}

}

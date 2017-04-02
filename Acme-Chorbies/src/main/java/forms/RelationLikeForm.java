package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class RelationLikeForm {

	// Attributes ----------------------------------------------------
	
	private int chorbiId;
	private String comment;
	
	@NotNull
	public int getchorbiId() {
		return chorbiId;
	}
	public void setchorbiId(int chorbiId) {
		this.chorbiId = chorbiId;
	}
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getcomment() {
		return comment;
	}
	public void setcomment(String comment) {
		this.comment = comment;
	}

	
}
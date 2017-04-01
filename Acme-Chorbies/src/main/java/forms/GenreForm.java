
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class GenreForm {

	private int		id;
	private String	value;

	// Constructor --------------------------------------------------

	public GenreForm() {
		super();
	}

	// Getters and Setters --------------------------------

	@NotBlank
	@Pattern(regexp = "^man$|^woman$")
	@Column(unique = true)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@NotNull
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

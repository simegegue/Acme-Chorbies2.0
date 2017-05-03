
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.PROPERTY)
public class BannerForm {

	private int		id;
	private String	url;


	// Constructor --------------------------------------------------

	public BannerForm() {
		super();
	}

	// Getters and Setters --------------------------------

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@NotNull
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}

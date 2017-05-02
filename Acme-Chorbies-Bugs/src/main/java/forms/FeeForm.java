
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.PROPERTY)
public class FeeForm {

	private int		id;

	private Double	chorbiValue;
	private Double	managerValue;


	@NotNull
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getChorbiValue() {
		return chorbiValue;
	}
	public void setChorbiValue(Double chorbiValue) {
		this.chorbiValue = chorbiValue;
	}

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getManagerValue() {
		return managerValue;
	}
	public void setManagerValue(Double managerValue) {
		this.managerValue = managerValue;
	}

}

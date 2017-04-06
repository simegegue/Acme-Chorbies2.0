
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.Valid;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Coordinate;
import domain.Genre;
import domain.KindRelationship;

@Embeddable
@Access(AccessType.PROPERTY)
public class SearchTemplateForm {

	// Attributes ------------------------------
	private Integer				age;
	private String				keyword;
	private Coordinate			coordinate;
	private Genre				genre;
	private KindRelationship				kindRelationship;


	// Constructor --------------------------------------------------

	public SearchTemplateForm() {
		super();
	}

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	
	public KindRelationship getKindRelationship() {
		return kindRelationship;
	}

	public void setKindRelationship(KindRelationship kindRelationship) {
		this.kindRelationship = kindRelationship;
	}

}

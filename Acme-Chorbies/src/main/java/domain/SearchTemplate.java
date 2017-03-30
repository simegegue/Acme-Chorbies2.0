
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SearchTemplate extends DomainEntity {

	// Attributes --------------------------------------

	private String		genre;
	private Integer		age;
	private String		kindRelationship;
	private String		keyword;
	private Coordinate	coordinate;
	private Date		lastTimeSearched;


	// Getters and Setters -----------------------------

	@Pattern(regexp = "^man$|^woman$")
	public String getGenre() {
		return this.genre;
	}

	public void setGenre(final String genre) {
		this.genre = genre;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(final Integer age) {
		this.age = age;
	}

	@Pattern(regexp = "^activities$|^friendship$|^love$")
	public String getKindRelationship() {
		return this.kindRelationship;
	}

	public void setKindRelationship(final String kindRelationship) {
		this.kindRelationship = kindRelationship;
	}

	@NotBlank
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(final Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getLastTimeSearched() {
		return this.lastTimeSearched;
	}

	public void setLastTimeSearched(final Date lastTimeSearched) {
		this.lastTimeSearched = lastTimeSearched;
	}

	// Relationships -----------------------------------

}

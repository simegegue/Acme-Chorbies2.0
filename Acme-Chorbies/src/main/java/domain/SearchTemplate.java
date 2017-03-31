
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SearchTemplate extends DomainEntity {

	// Attributes --------------------------------------

	private Genre				genre;
	private Integer				age;
	private KindRelationShip	kindRelationship;
	private String				keyword;
	private Coordinate			coordinate;
	private Date				lastTimeSearched;


	// Getters and Setters -----------------------------

	@Valid
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Valid
	public KindRelationShip getKindRelationship() {
		return this.kindRelationship;
	}

	public void setKindRelationship(KindRelationShip kindRelationship) {
		this.kindRelationship = kindRelationship;
	}

	@NotBlank
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getLastTimeSearched() {
		return this.lastTimeSearched;
	}

	public void setLastTimeSearched(Date lastTimeSearched) {
		this.lastTimeSearched = lastTimeSearched;
	}

	// Relationships -----------------------------------

}

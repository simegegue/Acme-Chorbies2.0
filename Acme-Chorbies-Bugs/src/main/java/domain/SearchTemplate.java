
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SearchTemplate extends DomainEntity {

	// Attributes --------------------------------------


	private Integer				age;
	private String				keyword;
	private Coordinate			coordinate;
	private Date				lastTimeSearched;


	// Getters and Setters -----------------------------

	public Integer getAge() {
		return this.age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

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
	
	private Genre				genre;
	private KindRelationship	kindRelationship;
	private Collection<Chorbi> chorbies;
	
	
	@Valid
	@ManyToOne(optional= true)
	public Genre getGenre() {
		return this.genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Valid
	@ManyToOne(optional= true)
	public KindRelationship getKindRelationship() {
		return this.kindRelationship;
	}
	public void setKindRelationship(KindRelationship kindRelationship) {
		this.kindRelationship = kindRelationship;
	}
		
	@Valid
	@ManyToMany
	public Collection<Chorbi> getChorbies(){
		return chorbies;
	}
	public void setChorbies(Collection<Chorbi> chorbies){
		this.chorbies = chorbies;
	}
}

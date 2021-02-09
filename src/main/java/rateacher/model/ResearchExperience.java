package rateacher.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "researchexperiences")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResearchExperience extends BaseEntity{
	
	//Attributes
	@NotBlank
	private String research;

	
	
	//Relationships

}

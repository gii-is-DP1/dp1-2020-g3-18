package rateacher.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "professionalexperiences")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessionalExperience extends BaseEntity{

	//Attributes
	@NotBlank
	private String university;

	private String comment;




	

	
}

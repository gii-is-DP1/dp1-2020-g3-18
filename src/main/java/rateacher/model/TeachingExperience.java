package rateacher.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="teachingexperiences")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeachingExperience extends BaseEntity{

	
	//Attributes
	@NotBlank
	private String titulation;
	

	private String comment;
	



	
	
}

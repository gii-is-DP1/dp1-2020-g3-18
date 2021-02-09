package rateacher.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personalexperiences")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalExperience extends BaseEntity{

	//Attributes
	@NotBlank
	private String 		name;
	//Relationships
	@OneToOne(optional = true)
	@JoinColumn(name = "res_experience")
	private ResearchExperience researchExperience;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "tea_experience")
	private TeachingExperience teachingExperience;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "pro_experience")
	private ProfessionalExperience professionalExperience;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "ext_evaluation")
	private ExternalEvaluation externalEvaluation;

}

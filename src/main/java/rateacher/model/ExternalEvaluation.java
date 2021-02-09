package rateacher.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "externalevaluations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalEvaluation extends BaseEntity{

	//Attributes
	@NotNull
	@Range(min = 1, max = 5)
	private Integer note;
	
	@NotBlank
	private String evaluationReport;
	
	@NotBlank
	private String comment;
	


	
	
}

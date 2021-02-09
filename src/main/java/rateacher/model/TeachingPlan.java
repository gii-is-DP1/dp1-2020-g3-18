package rateacher.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teaching_plans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeachingPlan extends BaseEntity{

	@NotBlank
	private String 		name;
}

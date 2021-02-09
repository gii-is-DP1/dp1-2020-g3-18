package rateacher.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "colleges")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class College extends BaseEntity{
	
	//Attributes 
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String city;
	
	//Relationships
	
	@ManyToMany 
	@JoinTable(name = "colleges_teachers", joinColumns = @JoinColumn(name = "college_id"),
	inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private List<Teacher> teachers;

	
	

}

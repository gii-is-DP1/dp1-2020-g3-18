package rateacher.model;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher extends Person{
	
	//Attributes
	private String name;
	
	//Relationships
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
	private User user;
	
	@ManyToMany 
	private Collection<College> colleges;
	
	@OneToOne (optional = true)
	@JoinColumn(name = "personal_experience_id")
	private PersonalExperience personalExperience;
	
	@ManyToMany(fetch = FetchType.EAGER) 
	@Size(min=1, max=3)//Rela de Negocio 7
	private Collection<Department> departments;
	
	@ManyToMany
	@JoinTable(name = "teachers_subjects", joinColumns = @JoinColumn(name = "teacher_id"),
	inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Collection<Subject> subjects;
	

	public Collection<Subject> getSubjects(){
		return this.subjects;
	}

	



}
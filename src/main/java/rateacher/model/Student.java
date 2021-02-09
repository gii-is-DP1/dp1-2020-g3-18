package rateacher.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student extends Person{
	
	//Attributes
	@NotBlank
	private String name;
	
//	@Email
	private String email;
	
	//Relationships
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
	private User user;
	
	@ManyToMany (cascade = CascadeType.REMOVE)
	@JoinTable(name = "students_subjects", joinColumns = @JoinColumn(name = "student_id"),
	inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Collection<Subject> subjects;
	
	
	//Profesores con misma asignatura
	@ManyToMany 
	@JoinTable(name = "students_teachers", joinColumns = @JoinColumn(name = "student_id"),
	inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private Collection<Teacher> teachers;



	
}

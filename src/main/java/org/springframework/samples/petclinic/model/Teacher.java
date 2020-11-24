package org.springframework.samples.petclinic.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "teachers")
@AllArgsConstructor @NoArgsConstructor
public class Teacher extends Person{
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")

	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_scores", joinColumns = @JoinColumn(name = "teachers_id"),
	inverseJoinColumns = @JoinColumn(name = "scores_id"))
	private Set<Score> scores;
	
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_colleges", joinColumns = @JoinColumn(name = "teachers_id"),
	inverseJoinColumns = @JoinColumn(name = "colleges_id"))
	private List<College> colleges;
	
	@OneToOne (optional = true)
	private PersonalExperience personalExperience;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	protected Set<Score> getScoresInternal() {
		if (this.scores == null) {
			this.scores = new HashSet<>();
		}
		return this.scores;
	}
	
	public void addScore(Score score) {
		getScoresInternal().add(score);
		score.setTeacher(this);
	}
	



}
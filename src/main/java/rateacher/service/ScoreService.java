package rateacher.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.repository.ScoreRepository;

@Service
public class ScoreService {
	
	private ScoreRepository scoreRepository;
	private StudentService studentService;

	@Autowired
	public ScoreService(ScoreRepository scoreRepository, StudentService studentService) {
		this.scoreRepository = scoreRepository;
		this.studentService = studentService;
	}
	
	@Transactional(readOnly = true)	
	public Score findScoreById(int id){
		return scoreRepository.findById(id);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Score> findAll(){
		return scoreRepository.findAll();
	}
	
	@Transactional(readOnly = true)	
	public Collection<Score> findAllScoresByTeacherId(int id){
		return scoreRepository.findAllScoreByTeacherId(id);
	}
	
	
	@Transactional()	
	public void saveScore(@Valid Score score)  {
		scoreRepository.save(score);
	}	
	
	@Transactional()	
	public void saveScore2Create(@Valid Score score, int teacherId) {	
		Collection<Score> scores = findAllScoresByTeacherId(teacherId);//
		List<Student> students = new ArrayList<>(); 
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		Student student = this.studentService.findStudentByUsername(principal);
		score.setStudent(student);
		for(Score s: scores) {
			students.add(s.getStudent());
		}
		if(!students.contains(score.getStudent())) {
			scoreRepository.save(score);
		}
	}
	
//	@Transactional(readOnly = true)	
//	public void removeScore(@Valid Score score){
//		scoreRepository.delete(score);
//	}
	
	public void delete(Score score) {
		scoreRepository.delete(score);		
	}
}

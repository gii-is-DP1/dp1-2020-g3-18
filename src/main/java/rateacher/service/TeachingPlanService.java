package rateacher.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.TeachingPlan;
import rateacher.repository.TeachingPlanRepository;

@Service
public class TeachingPlanService {

	private TeachingPlanRepository teachingPlanRepository;
	
	@Autowired
	public TeachingPlanService(TeachingPlanRepository teachingPlanRepository) {
		this.teachingPlanRepository = teachingPlanRepository;
	}

	@Transactional(readOnly = true)
	public TeachingPlan findTeachingPlanById(int id) throws DataAccessException {
		TeachingPlan teachingPlan = teachingPlanRepository.findById(id);
		return teachingPlan;
	}

	@Transactional(readOnly = true)
	public Collection<TeachingPlan> findTeachingPlans() throws DataAccessException {
		return teachingPlanRepository.findAll();
	}

	@Transactional
	public void save(TeachingPlan teachingPlan) {
		teachingPlanRepository.save(teachingPlan);
	}

	@Transactional
	public void delete(TeachingPlan teachingPlan) {
		teachingPlanRepository.delete(teachingPlan);
	}
	
}

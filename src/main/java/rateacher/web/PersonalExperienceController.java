package rateacher.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rateacher.model.ExternalEvaluation;
import rateacher.model.PersonalExperience;
import rateacher.model.ProfessionalExperience;
import rateacher.model.ResearchExperience;
import rateacher.model.Teacher;
import rateacher.model.TeachingExperience;
import rateacher.service.ExternalEvaluationService;
import rateacher.service.PersonalExperienceService;
import rateacher.service.ProfessionalExperienceService;
import rateacher.service.ResearchExperienceService;
import rateacher.service.TeacherService;
import rateacher.service.TeachingExperienceService;
import rateacher.util.ExternalEvaluationValidator;

@Controller
public class PersonalExperienceController {

	private final PersonalExperienceService personalExperienceService;
	private final TeacherService teacherService;
	private final ResearchExperienceService researchExperienceService;
	private final TeachingExperienceService teachingExperienceService;
	private final ProfessionalExperienceService professionalExperienceService;
	private final ExternalEvaluationService externalEvaluationService;

	@Autowired
	public PersonalExperienceController(PersonalExperienceService personalExperienceService,
			TeacherService teacherService, ResearchExperienceService researchExperienceService,
			TeachingExperienceService teachingExperienceService,
			ProfessionalExperienceService professionalExperienceService,
			ExternalEvaluationService externalEvaluationService) {
		this.personalExperienceService = personalExperienceService;
		this.teacherService = teacherService;
		this.researchExperienceService = researchExperienceService;
		this.teachingExperienceService = teachingExperienceService;
		this.professionalExperienceService = professionalExperienceService;
		this.externalEvaluationService = externalEvaluationService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("externalEvaluation")
	public void initPersonalExperienceBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ExternalEvaluationValidator());
	}

	
	
	@GetMapping(value = { "/teachers/{teacherId}/newPersonalExperience" })
	public String initCreationPersonalExperienceForm(@PathVariable int teacherId, ModelMap model) { // vista.
		PersonalExperience personalExperience = new PersonalExperience();
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		model.put("personalExperience", personalExperience);
		model.put("teacher", teacher);
		return "personalExperience/createPersonalExperienceForm";
	}

	@PostMapping(value = "/teachers/{teacherId}/newPersonalExperience")
	public String processCreationPersonalExperienceForm(@PathVariable int teacherId,
			@Valid PersonalExperience personalExperience, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			Teacher teacher = teacherService.findTeacherById(teacherId);
			model.put("teacher", teacher);
			return "personalExperience/createPersonalExperienceForm";
		} else {
			personalExperienceService.save(personalExperience);
			Teacher teacher = teacherService.findTeacherById(teacherId);
			teacher.setPersonalExperience(personalExperience);
			teacherService.save(teacher);
			model.put("personalExperience", personalExperience);
			model.put("teacher", teacher);
			Collection<Teacher> teachers = this.teacherService.findTeachers();
			model.put("teachers", teachers);
			return "teachers/teachersList";
		}
	}

	@GetMapping(value = "/personalExperience/{personalExperienceId}")
	public String showPersonalExperience(@PathVariable("personalExperienceId") int personalExperieneId,
			ModelMap model) {
		PersonalExperience p = personalExperienceService.findById(personalExperieneId);
		model.put("personalExperience", p);
		return "personalExperience/personalExperience";
	}

	@GetMapping(value = { "/personalExperience/{personalExperienceId}/newResearchExperience" })
	public String initCreationResearchExperienceForm(@PathVariable int personalExperienceId, ModelMap model) { // vista.
		PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
		ResearchExperience re = new ResearchExperience();
		model.put("personalExperience", personalExperience);
		model.put("researchExperience", re);
		return "personalExperience/researchExperience";
	}

	@PostMapping(value = "/personalExperience/{personalExperienceId}/newResearchExperience")
	public String processCreationResearchExperienceForm(@PathVariable int personalExperienceId,
			@Valid ResearchExperience researchExperience, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
			model.put("personalExperience", personalExperience);
			return "personalExperience/researchExperience";
		} else {
			researchExperienceService.save(researchExperience);
			PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
			personalExperience.setResearchExperience(researchExperience);
			personalExperienceService.save(personalExperience);
			model.put("personalExperience", personalExperience);
			model.put("researchExperience", researchExperience);
			PersonalExperience p = personalExperienceService.findById(personalExperienceId);
			model.put("personalExperience", p);
			return "personalExperience/personalExperience";
		}
	}

	@GetMapping(value = { "/personalExperience/{personalExperienceId}/newTeachingExperience" })
	public String initCreationTeachingExperienceForm(@PathVariable int personalExperienceId, ModelMap model) { // vista.
		PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
		TeachingExperience te = new TeachingExperience();
		model.put("personalExperience", personalExperience);
		model.put("teachingExperience", te);
		return "personalExperience/teachingExperience";
	}

	@PostMapping(value = "/personalExperience/{personalExperienceId}/newTeachingExperience")
	public String processCreationTeachingExperienceForm(@PathVariable int personalExperienceId,
			@Valid TeachingExperience teachingExperience, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
			model.put("personalExperience", personalExperience);
			return "personalExperience/teachingExperience";
		} else {
			teachingExperienceService.save(teachingExperience);
			PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
			personalExperience.setTeachingExperience(teachingExperience);
			personalExperienceService.save(personalExperience);
			model.put("personalExperience", personalExperience);
			model.put("teachingExperience", teachingExperience);
			return "personalExperience/personalExperience";
		}
	}

	@GetMapping(value = { "/personalExperience/{personalExperienceId}/newProfessionalExperience" })
	public String initCreationProfessionalExperienceForm(@PathVariable int personalExperienceId, ModelMap model) { // vista.
		PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
		ProfessionalExperience pro = new ProfessionalExperience();
		model.put("personalExperience", personalExperience);
		model.put("professionalExperience", pro);
		return "personalExperience/professionalExperience";
	}

	@PostMapping(value = "/personalExperience/{personalExperienceId}/newProfessionalExperience")
	public String processCreationProfessionalExperienceForm(@PathVariable int personalExperienceId,
			@Valid ProfessionalExperience professionalExperience, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
			model.put("personalExperience", personalExperience);
			return "personalExperience/profesionalExperience";
		} else {
			professionalExperienceService.save(professionalExperience);
			PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
			personalExperience.setProfessionalExperience(professionalExperience);
			personalExperienceService.save(personalExperience);
			model.put("personalExperience", personalExperience);
			model.put("professionalExperience", professionalExperience);
			return "personalExperience/personalExperience";
		}
	}

	@GetMapping(value = { "/personalExperience/{personalExperienceId}/newExternalEvaluation" })
	public String initCreationExternalEvaluationForm(@PathVariable int personalExperienceId, ModelMap model) { // vista.
		PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
		ExternalEvaluation ex = new ExternalEvaluation();
		model.put("personalExperience", personalExperience);
		model.put("externalEvaluation", ex);
		return "personalExperience/externalEvaluation";
	}

	@PostMapping(value = "/personalExperience/{personalExperienceId}/newExternalEvaluation")
	public String processCreationExternalEvaluationForm(@PathVariable int personalExperienceId,
			@Valid ExternalEvaluation externalEvaluation, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
			model.put("personalExperience", personalExperience);
			return "personalExperience/externalEvaluation";
		} else {
			externalEvaluationService.save(externalEvaluation);
			PersonalExperience personalExperience = personalExperienceService.findById(personalExperienceId);
			personalExperience.setExternalEvaluation(externalEvaluation);
			personalExperienceService.save(personalExperience);
			model.put("personalExperience", personalExperience);
			model.put("externalEvaluation", externalEvaluation);
			return "personalExperience/personalExperience";
		}
	}

}

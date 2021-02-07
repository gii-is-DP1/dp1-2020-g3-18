package rateacher.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rateacher.model.Report;
import rateacher.model.Score;
import rateacher.model.Student;
import rateacher.model.Teacher;
import rateacher.service.ReportService;
import rateacher.service.ScoreService;
import rateacher.service.StudentService;
import rateacher.service.TeacherService;
import rateacher.util.ReportValidator;
import rateacher.util.ScoreValidator;

@Controller
public class ReportController {
	
	private static final String VIEW_REPORT_CREATE_FORM ="reports/createReport";
	
	private ScoreService scoreService;

	private ReportService reportService;

	@Autowired
	public ReportController(ScoreService scoreService, ReportService reportService) {
		this.scoreService = scoreService;
		this.reportService = reportService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("report")
	public void initReportBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ReportValidator(reportService));
	}
	
	@GetMapping(value = {"/reports/new/{scoreId}"})
	public String initCreationForm(@PathVariable int scoreId, ModelMap model) {

		Score score = this.scoreService.findScoreById(scoreId);
		Report report = new Report();
		report.setScore(score);
		model.put("score", score);
		model.put("report", report);
		return VIEW_REPORT_CREATE_FORM;
	}	
	
	@PostMapping(value = {"/reports/new/{scoreId}"})
	public String processCreationForm(@PathVariable int scoreId, @Valid Report report, BindingResult result,
	ModelMap model) {
		if (result.hasErrors()) {
			Score score = this.scoreService.findScoreById(scoreId);
			report.setScore(score);
			model.put("report", report);
			return VIEW_REPORT_CREATE_FORM;
		} else {
			Score score = this.scoreService.findScoreById(scoreId);
			report.setScore(score);
			this.reportService.saveReport(report);
			return "redirect:/reports";
		}
	}	
	
	@GetMapping(value = { "reports" })
	public String showReportList(Map<String, Object> model) {

		Collection<Report> reports = this.reportService.findReports();
		model.put("reports", reports);
		return "reports/reportsList";
	}
}

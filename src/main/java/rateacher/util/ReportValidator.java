package rateacher.util;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rateacher.model.Report;
import rateacher.service.ReportService;

public class ReportValidator implements Validator{
	
	private final ReportService reportService;

	
	@Autowired
	public ReportValidator(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Report report = (Report) target;
		List<Report> reports = (List<Report>) this.reportService.findReports();
		Integer reportsOfTheSameScore = 0;
		for(Report r: reports) {
			if(report.getScore()==r.getScore()) {
				reportsOfTheSameScore ++;
			}
		}
		if(reportsOfTheSameScore >= 3) {
			errors.rejectValue("score", "too many reports", "There are already 3 reports made for this comment, you cannot report it again");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Report.class.isAssignableFrom(clazz);
	}
}

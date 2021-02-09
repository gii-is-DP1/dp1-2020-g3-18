package rateacher.service;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rateacher.model.Report;
import rateacher.repository.ReportRepository;

@Service
public class ReportService {
	
	private ReportRepository reportRepository;

	@Autowired
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	@Transactional(readOnly = true)	
	public Report findReportById(int id){
		Report report = reportRepository.findById(id);
		assert report != null;
		return report;
	}
	
	
	@Transactional()	
	public void saveReport(@Valid Report report)  {
		reportRepository.save(report);
	}

	@Transactional(readOnly = true)
	public Collection<Report> findReports() throws DataAccessException {
		return reportRepository.findAll();
	}
}

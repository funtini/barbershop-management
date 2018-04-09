package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.model.Report;
import bsmanagement.model.ReportService;

@RestController
public class ReportRestController {

	@Autowired
	ReportService reportService;

	public ReportRestController() {

	}
	
	@RequestMapping("/reports")
	List<Report> listAllReports()
	{
		return reportService.getReports();
	}
	
}

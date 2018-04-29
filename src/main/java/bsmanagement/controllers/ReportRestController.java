package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.model.Report;
import bsmanagement.model.ReportSaleExpenseService;

@RestController
public class ReportRestController {

	@Autowired
	ReportSaleExpenseService reportSaleExpenseService;

	public ReportRestController() {

	}
	
	@RequestMapping("/reports")
	List<Report> listAllReports()
	{
		return reportSaleExpenseService.getAllReports();
	}
	
}

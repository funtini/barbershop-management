package bsmanagement.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.ReportRestDTO;
import bsmanagement.model.Report;
import bsmanagement.model.ReportSaleExpenseService;

@RestController
public class StatisticReportRestController {

	@Autowired
	ReportSaleExpenseService reportSaleExpenseService;
	
	
	/**
	 * Rest Controller to get ROI of all-time
	 * 
	 * @return ResponseEntity with status code OK and a ReportRestDTO with ROI
	 */
	@RequestMapping("/reports/roi")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<ReportRestDTO> calculateRoiAllTime()
	{
		ReportRestDTO roi = new ReportRestDTO();
		roi.setRoi(reportSaleExpenseService.calculateRoiAllTime());
		return new ResponseEntity<>(roi,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get ROI monthly average
	 * 
	 * @return ResponseEntity with status code OK and a ReportRestDTO with ROI average
	 */
	@RequestMapping("/reports/roiavg")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<ReportRestDTO> calculateRoiAverage()
	{
		ReportRestDTO roi = new ReportRestDTO();
		roi.setRoi(reportSaleExpenseService.calculateAvgMonthlyRoi());
		return new ResponseEntity<>(roi,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get Income monthly average
	 * 
	 * @return ResponseEntity with status code OK and a ReportRestDTO with Income average
	 */
	@RequestMapping("/reports/incomeavg")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<ReportRestDTO> calculateIncomeAverage()
	{
		ReportRestDTO roi = new ReportRestDTO();
		roi.setRoi(reportSaleExpenseService.calculateAvgMonthlySalesAmount());
		return new ResponseEntity<>(roi,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get Expense monthly average
	 * 
	 * @return ResponseEntity with status code OK and a ReportRestDTO with Expense average
	 */
	@RequestMapping("/reports/expenseavg")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<ReportRestDTO> calculateExpenseAverage()
	{
		ReportRestDTO roi = new ReportRestDTO();
		roi.setRoi(reportSaleExpenseService.calculateAvgMonthlyExpensesValue());
		return new ResponseEntity<>(roi,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get Profit monthly average
	 * 
	 * @return ResponseEntity with status code OK and a ReportRestDTO with Profit average
	 */
	@RequestMapping("/reports/profitavg")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<ReportRestDTO> calculateProfitAverage()
	{
		ReportRestDTO roi = new ReportRestDTO();
		roi.setRoi(reportSaleExpenseService.calculateAvgMonthlyProfit());
		return new ResponseEntity<>(roi,HttpStatus.OK);
	}
}

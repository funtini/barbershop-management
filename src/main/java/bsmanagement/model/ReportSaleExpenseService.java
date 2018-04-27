package bsmanagement.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.jparepositories.ExpenseRepository;
import bsmanagement.model.jparepositories.PaymentRepository;
import bsmanagement.model.jparepositories.ReportRepository;
import bsmanagement.model.jparepositories.SaleRepository;

/**
 * <h1> ReportSaleExpenseService </h1>
 * <p>
 * ReportSaleExpenseService is a super service class to manage all Sales, Expenses and Reports in a barbershop business
 * context. This class contains information like:
 * <ul>
 * <li>reportRepository - JpaRepository of all reports
 * <li>expRepository - JpaRepository of all expenses
 * <li>saleRepository - JpaRepository of all sales
 * </ul>
 * <p>
 * Both repositories have annotation autowired of springframework framework.
 * 
 * @author JOAO GOMES
 *
 */
@Service
public class ReportSaleExpenseService {
	
	@Autowired
	private ReportRepository reportRepo;
	
	@Autowired
	private ExpenseRepository expRepo;
	
	@Autowired
	private SaleRepository saleRepo;
	
	@Autowired
	private PaymentRepository paymentRepository;

	public ReportSaleExpenseService() {
	
	}

	public void setReportRepo(ReportRepository reportRepo) {
		this.reportRepo = reportRepo;
	}

	public void setExpRepo(ExpenseRepository expRepo) {
		this.expRepo = expRepo;
	}

	public void setSaleRepo(SaleRepository saleRepo) {
		this.saleRepo = saleRepo;
	}
	
	public void setPaymentRepository(PaymentRepository paymentRepository) {
		this.paymentRepository=paymentRepository;
	}
	
	
	/**
	 * Method to Add new Report
	 * 
	 * @return true, if report was sucessfull added, and false if there is already a report with this yearMonth in ReportList
	 */
	public boolean addReport(YearMonth yearMonth)
	{
		Report rep = new Report(yearMonth);
		if (!reportRepo.existsById(rep.getYearMonth().toString()))
		{
			reportRepo.save(rep);
			loadFixedExpenses(rep);
			return true;
		}
			
		return false;
		
	}
	
	/**
	 * Method to update report
	 * 
	 * @param report
	 */
	public void updateReport(Report report)
	{
		reportRepo.save(report);
	}
	
	
	/**
	 * Get a list of all monthly reports
	 * 
	 * @return the reports
	 */
	public List<Report> getReports() {
		List<Report> reportList = new ArrayList<>();
		for (Report r : reportRepo.findAll())
		{
			r.setYearMonth(YearMonth.parse(r.getId()));
			reportList.add(r);
		}
			
		return reportList;
	}
	
	/**
	 * Method to load a sale to report list
	 * 
	 * Sale is added to report associated to sale's date.
	 * If there is no report with this sale's date, a new report is created with YearMonth associated to this sale's date.
	 * 
	 * @param Sale 
	 * 
	 * @return true if new Report has been created, false if already exist a report associated with this date.
	 */
	public boolean loadSale(Sale s)
	{
		YearMonth saleDate = YearMonth.of(s.getDate().getYear(), s.getDate().getMonth());
		boolean occurence = false;
		boolean newReportAdded = false;
		for(Report rep: getReports())
		{
			if (rep.addSale(s))
			{
				occurence = true;
				rep.updateBusinessDays();
				updateReport(rep);
			}
		}
		
		if (!occurence)
		{
			newReportAdded = true;
			this.addReport(saleDate);
			Report newReport = this.getReport(saleDate);
			newReport.addSale(s);
			newReport.updateBusinessDays();
			updateReport(newReport);
		}
		
		return newReportAdded;
	}
	
	
	/**
	 * Method to load an expense to report list
	 * 	
	 * <p>FOR ONEOFF EXPENSE: Expense is added to report associated to expense's date. if there is no report with this expense's date, a new report is created with YearMonth associated to this expense's date.</p>
	 * <p>FOR FIXED EXPENSE: Same as ONEOFF, but it also add to all reports between today and expense's date.
	 * </p>
	 * @param Expense 
	 * 
	 * @return true if new Report has been created, false if already exist a report associated with this date.
	 */
	public boolean loadExpense(Expense e)
	{
		YearMonth expDate = YearMonth.of(e.getDate().getYear(), e.getDate().getMonth());
		YearMonth repDate;
		boolean occurence = false;
		boolean newReportAdded = false;
		for(Report rep: getReports())
		{
			repDate = YearMonth.parse(rep.getId());
			if ((repDate.equals(expDate) && rep.addExpense(e)))
			{
				occurence = true ;
				updateReport(rep);
			}
			if (repDate.isAfter(expDate) && e.getType().equals(expenseType.FIXED))
			{
				int year = repDate.getYear();
				int month = repDate.getMonthValue();	
				Expense newExp = new Expense(e.getName(), e.getType(), e.getValue(), e.getDate().withYear(year).withMonth(month));
				rep.addExpense(newExp);
				updateReport(rep);
			}
				
		}
		
		if (!occurence)
		{
			newReportAdded = true;
			this.addReport(expDate);
			Report newReport = this.getReport(expDate);
			newReport.addExpense(e);
			updateReport(newReport);
		}
		
		return newReportAdded;
	}
	
	/**
	 * Method to load fixed expenses to report
	 * 	
	 * <p>FIXED EXPENSES are loaded from previous month and put in new report
	 * </p>
	 * @param report 
	 * 
	 */
	public void loadFixedExpenses(Report report)
	{
		
		String idPreviousMonth = report.getYearMonth().minusMonths(1).toString();
		YearMonth reportDate = YearMonth.parse(report.getId());

		if (reportRepo.existsById(idPreviousMonth))
		{
			for(Expense exp: reportRepo.findById(idPreviousMonth).get().getExpenses())
			{
				if(exp.getType().equals(expenseType.FIXED))
				{
					int year = reportDate.getYear();
					int month = reportDate.getMonthValue();
					Expense newExp = new Expense(exp.getName(), exp.getType(), exp.getValue(), exp.getDate().withYear(year).withMonth(month));
					report.addExpense(newExp);
					updateReport(report);
				}
					
			}
		}
		
	}

	/**
	 * Method to get a report of a specific YearMonth
	 * 
	 * @param yearMonth
	 * 
	 * @return Report
	 */
	public Report getReport(YearMonth ym) {
		for (Report rep: getReports())
		{
			if (rep.getId().equals(ym.toString()))
				return rep;
		}
		return null;
	}
	
	
	/**
	 * Method to get current open report 
	 * 
	 * @return Report
	 */
	public Report getCurrentOpenReport() {
		for (Report rep: getReports())
		{
			if (rep.getId().equals(YearMonth.now().toString()))
				return rep;
		}
		return null;
	}
	
	
	/*
	 * ***************************************
	 * EXPENSE SERVICE
	 * 
	 * ***************************************
	 * */
	
	
	/**
	 * @return the listOfExpenses
	 */
	public List<Expense> getExpenses() {
		List<Expense> expenses = new ArrayList<>();
		for (Expense e: expRepo.findAll())
			expenses.add(e);
		return expenses;
	}
	
	/**
	 * Method to create a new instance of expense with name, type, value, and date
	 * 
	 * @param name - Name of Expense
	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
	 * @param value - Value in Euros
	 * @param date - Date of Expense's Payment
	 * 
	 * @return Expense
	 */
	public Expense createExpense(String name, expenseType type, double value, LocalDate date) {
		Expense e = new Expense(name,type,value,date);
		return e;
	}
	
	
	/**
	 * Method to create a new instance of expense with name, type, value, date and description
	 * 
	 * @param name - Name of Expense
	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
	 * @param value - Value in Euros
	 * @param date - Date of Expense's Payment
	 * @param description - Description of Expense
	 * 
	 * @return Expense
	 */
	public Expense createExpense(String name, expenseType type, double value, LocalDate date,String description) {
		Expense e = new Expense(name,type,value,date,description);
		return e;
	}
	
	
	/**
	 * Method to add a Expense to expenseList 
	 * 
	 * @param expense - Instance of Expense class
	 */
	public void addExpense(Expense expense) {
		expRepo.save(expense);
		loadExpense(expense);
	}
	
	/**
	 * Method to remove a Expense from repository - Only OneOff Expenses can be removed
	 * 
	 * @param expense - Instance of Expense class
	 * 
	 * @return true if expense is successful removed - (OneOff Expense) - false if was just disabled (Fixed Expense)
	 */
	public boolean removeExpense(Expense expense) {
		getCurrentOpenReport().removeExpense(expense);
		expRepo.delete(expense);
		return true;
	}
	
	
	/**
	 * Method to get available payment methods
	 * 
	 * @return List of PaymentMethod
	 */
	public List<PaymentMethod> getAvailablePaymentMethods()
	{
		return paymentRepository.findAll();
	}
	
	/**
	 * Method to get available payment methods
	 * 
	 * @return List of PaymentMethod
	 */
	public void addPaymentMethod(PaymentMethod payment)
	{
		paymentRepository.save(payment);
	}
	
	
	
	
	
	
	
	
	

}

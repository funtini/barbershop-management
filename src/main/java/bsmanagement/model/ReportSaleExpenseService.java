package bsmanagement.model;

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
	

	/**
	 * Method to Add new Report
	 * 
	 * @return true, if report was successfully added, and false if there is already a report with this yearMonth in ReportList
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
		if (report.getReportState().isOpen())
			report.changeStatus();	
		reportRepo.save(report);
	}
	
	
	/**
	 * Method to refresh all report status, except reports that are waiting confirmation to be closed
	 * 
	 */
	public void refreshReportStatus()
	{
		for (Report r: getAllReports())
		{
			if (r.getReportState().isOpen())
				r.changeStatus();
		}
	}
	
	
	/**
	 * Method to close report that are waiting for confirmation. Open reports cant be closed.
	 * 
	 * @param report
	 */
	public boolean closeReport(Report report)
	{
		return report.setStatusClosed();
	}
	
	
	
	/**
	 * Get a list of all monthly reports
	 * 
	 * @return the reports
	 */
	public List<Report> getAllReports() {
		List<Report> reportList = new ArrayList<>();
		for (Report r : reportRepo.findAll())
		{
			r.setYearMonth(YearMonth.parse(r.getId()));
			reportList.add(r);
		}
			
		return reportList;
	}
	
	/**
	 * Get a list of all non-closed monthly reports
	 * 
	 * @return the reports
	 */
	public List<Report> getOpenReports() {
		List<Report> reportList = new ArrayList<>();
		for (Report r : reportRepo.findAll())
		{
			if (!r.getReportState().isClosed())
			{
				r.setYearMonth(YearMonth.parse(r.getId()));
				reportList.add(r);
			}
		}
			
		return reportList;
	}
	
	/**
	 * Get a list of all closed monthly reports
	 * 
	 * @return the reports
	 */
	public List<Report> getClosedReports() {
		List<Report> reportList = new ArrayList<>();
		for (Report r : reportRepo.findAll())
		{
			if (r.getReportState().isClosed())
			{
				r.setYearMonth(YearMonth.parse(r.getId()));
				reportList.add(r);
			}
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
		for(Report rep: getOpenReports())
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
		for(Report rep: getOpenReports())
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
				Expense newExp = expRepo.save(new Expense(e.getName(), e.getType(), e.getValue(), e.getDate().withYear(year).withMonth(month)));
//				Expense newExp = new Expense(e.getName(), e.getType(), e.getValue(), e.getDate().withYear(year).withMonth(month));
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
					expRepo.save(newExp);
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
		for (Report rep: getAllReports())
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
		for (Report rep: getAllReports())
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
	 * Method to add a new Expense and load it to respective report
	 * 
	 * @param expense - Instance of Expense class
	 */
	public void addExpense(Expense expense) {
		Expense e = expRepo.save(expense);
		loadExpense(e);
	}
	
	/**
	 * <p>Method to remove a Expense from repository.</p>
	 * Only expenses of non-closed reports can be removed.
	 * 
	 * @param expense - Instance of Expense class
	 * 
	 * @return true if expense is successful removed , false if report of expense is already closed
	 */
	public boolean removeExpense(Expense expense) {
		Report report = getReport(YearMonth.of(expense.getDate().getYear(), expense.getDate().getMonth()));
		if (report.getReportState().isClosed())
			return false;
		report.removeExpense(expense);	
		expRepo.delete(expense);
		//updateReport(report);
		return true;
	}
	

	/*
	 * ***************************************
	 * SALE SERVICE
	 * 
	 * ***************************************
	 * */
	
	/**
	 * Method to add a new sale 
	 * 
	 * @param sale - Instance of Sale class
	 */
	public boolean addSale(Sale sale)
	{
		if(saleRepo.existsById(sale.getId()))
			return false;
		Sale s = saleRepo.save(sale);
		loadSale(s);
		return true;
	}
	

	
	/********************************
	 * 
	 * STATISTIC/OPERATION METHODS
	 * 
	 * ******************************
	 */

	
	/**
	 * Method to calculate the total ROI of all reports, expect current monthly report
	 * 
	 * @return ROI AVEGARAGE (%) - double
	 */
	public double calculateRoiAllTime()
	{
	
		if (this.getAllReports().isEmpty())
			return 0;
		if (this.getAllReports().get(0).getId().equals(YearMonth.now().toString()))
			return 0;
		double sumExpenses = 0;
		double sumIncome = 0;
		for (Report r: getAllReports())
			if (!r.getId().equals(YearMonth.now().toString()))
			{
				sumExpenses= sumExpenses+r.calculateTotalExpensesValue();
				sumIncome= sumIncome+r.calculateTotalSalesAmount();
			}
			
		return (((sumIncome-sumExpenses)/sumExpenses)*100);
	}
	
	
	
	/**
	 * Method to calculate the average ROI of all reports, expect current monthly report
	 * 
	 * @return ROI AVEGARAGE (%) - double
	 */
	public double calculateAvgMonthlyRoi()
	{
		if (this.getAllReports().isEmpty())
			return 0;
		if (this.getAllReports().get(0).getId().equals(YearMonth.now().toString()))
			return 0;
		double sum = 0;
		double count = 0;
		for (Report r: getAllReports())
			if (!r.getId().equals(YearMonth.now().toString()))
			{
				sum=sum+r.calculateRoi();
				count++;
			}
			
		return (sum/count);
	}
	
	
	/**
	 * Method to calculate the average PROFIT of all reports, expect current monthly report
	 * 
	 * <p>Profit: All Sales - All Expenses of all closed reports</p>
	 * 
	 * @return PROFIT AVEGARAGE (%) - double
	 */
	public double calculateAvgMonthlyProfit()
	{
		if (getAllReports().isEmpty())
			return 0;
		if (getAllReports().get(0).getId().equals(YearMonth.now().toString()))
			return 0;
		double sum = 0;
		double count = 0;
		for (Report r: getAllReports())
			if (!r.getId().equals(YearMonth.now().toString()))
			{
				sum=sum+r.calculateProfit();
				count++;
			}
			
		return (sum/count);
	}
	
	
	/**
	 * Method to calculate the average Income of all reports, expect current monthly report
	 * 
	 * <p>Income: Sum of all sales amount </p>
	 * 
	 * @return INCOME AVEGARAGE (%) - double
	 */
	public double calculateAvgMonthlySalesAmount()
	{
		if (getAllReports().isEmpty())
			return 0;
		if (getAllReports().get(0).getId().equals(YearMonth.now().toString()))
			return 0;
		double sum = 0;
		double count = 0;
		for (Report r: getAllReports())
			if (!r.getId().equals(YearMonth.now().toString()))
			{
				sum=sum+r.calculateTotalSalesAmount();
				count++;
			}
			
		return (sum/count);
	}
	
	
	/**
	 * Method to calculate the average Expenses Value of all reports, expect current monthly report
	 * 
	 * <p>Expenses: Sum of all expenses value </p>
	 * 
	 * @return EXPENSES AVEGARAGE (%) - double
	 */
	public double calculateAvgMonthlyExpensesValue()
	{
		if (getAllReports().isEmpty())
			return 0;
		if (getAllReports().get(0).getId().equals(YearMonth.now().toString()))
			return 0;
		double sum = 0;
		double count = 0;
		for (Report r: getAllReports())
			if (!r.getId().equals(YearMonth.now().toString()))
			{
				sum=sum+r.calculateTotalExpensesValue();
				count++;
			}
			
		return (sum/count);
	}
	

}

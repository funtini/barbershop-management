//package bsmanagement.model;
//
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import bsmanagement.model.Expense.expenseType;
//import bsmanagement.model.jparepositories.ReportRepository;
//
///**
// * <h1> ReportService </h1>
// * <p>
// * Report Service is a class to manage all reports in a business
// * context. This class contains information like:
// * <ul>
// * <li>ReportRepository - Repository of Monthly Reports
// * </ul>
// * <p>
// * To create an instance of ReportList you just need to invoke the empty constructor
// * 
// * @author JOAO GOMES
// *
// */
//@Service
//public class ReportService {
//	
//	@Autowired
//	private ReportRepository reportRepo;
//
//	/**
//	 * Constructor of report registry
//	 */
//	public ReportService() {
//	
//	}
//
//	public void refreshReportStatus()
//	{
//		for (Report r: getAllReports())
//		{
//			if (r.getReportState().isOpen())
//				r.changeStatus();
//		}
//	}
//	
//	public void updateReport(Report report)
//	{
//		if (report.getReportState().isOpen())
//			report.changeStatus();	
//		reportRepo.save(report);
//	}
//	
//	/**
//	 * Get a list of all monthly reports
//	 * 
//	 * @return the reports
//	 */
//	public List<Report> getAllReports() {
//		List<Report> reportList = new ArrayList<>();
//		for (Report r : reportRepo.findAll())
//		{
//			r.setYearMonth(YearMonth.parse(r.getId()));
//			reportList.add(r);
//		}
//			
//		return reportList;
//	}
//	
//	/**
//	 * Get a list of all non-closed monthly reports
//	 * 
//	 * @return the reports
//	 */
//	public List<Report> getOpenReports() {
//		List<Report> reportList = new ArrayList<>();
//		for (Report r : reportRepo.findAll())
//		{
//			if (!r.getReportState().isClosed())
//			{
//				r.setYearMonth(YearMonth.parse(r.getId()));
//				reportList.add(r);
//			}
//		}
//			
//		return reportList;
//	}
//	
//	
//	/**
//	 * Method to Add new Report
//	 * 
//	 * @return true, if report was sucessfull added, and false if there is already a report with this yearMonth in ReportList
//	 */
//	public boolean addReport(YearMonth yearMonth)
//	{
//		Report rep = new Report(yearMonth);
//		if (!reportRepo.existsById(rep.getYearMonth().toString()))
//		{
//			reportRepo.save(rep);
//			loadFixedExpenses(rep);
//			return true;
//		}
//			
//		return false;
//		
//	}
//	
//	/**
//	 * Method to load a sale to report list
//	 * 
//	 * Sale is added to report associated to sale's date.
//	 * If there is no report with this sale's date, a new report is created with YearMonth associated to this sale's date.
//	 * 
//	 * @param Sale 
//	 * 
//	 * @return true if new Report has been created, false if already exist a report associated with this date.
//	 */
//	public boolean loadSale(Sale s)
//	{
//		YearMonth saleDate = YearMonth.of(s.getDate().getYear(), s.getDate().getMonth());
//		boolean occurence = false;
//		boolean newReportAdded = false;
//		for(Report rep: getAllReports())
//		{
//			if (rep.addSale(s))
//			{
//				occurence = true;
//				rep.updateBusinessDays();
//				updateReport(rep);
//			}
//		}
//		
//		if (!occurence)
//		{
//			newReportAdded = true;
//			this.addReport(saleDate);
//			Report newReport = this.getReport(saleDate);
//			newReport.addSale(s);
//			newReport.updateBusinessDays();
//			updateReport(newReport);
//		}
//		
//		return newReportAdded;
//	}
//	
//	
//	
//	/**
//	 * Method to load an expense to report list
//	 * 	
//	 * <p>FOR ONEOFF EXPENSE: Expense is added to report associated to expense's date. if there is no report with this expense's date, a new report is created with YearMonth associated to this expense's date.</p>
//	 * <p>FOR FIXED EXPENSE: Same as ONEOFF, but it also add to all reports between today and expense's date.
//	 * </p>
//	 * @param Expense 
//	 * 
//	 * @return true if new Report has been created, false if already exist a report associated with this date.
//	 */
//	public boolean loadExpense(Expense e)
//	{
//		YearMonth expenseDate = YearMonth.of(e.getDate().getYear(), e.getDate().getMonth());
//		YearMonth reportDate;
//		boolean occurence = false;
//		boolean newReportAdded = false;
//		for(Report rep: getAllReports())
//		{
//			reportDate = YearMonth.parse(rep.getId());
//			if ((reportDate.equals(expenseDate) && rep.addExpense(e)))
//			{
//				occurence = true ;
//				updateReport(rep);
//			}
//			if (reportDate.isAfter(expenseDate) && e.getType().equals(expenseType.FIXED))
//			{
//				int year = reportDate.getYear();
//				int month = reportDate.getMonthValue();	
//				Expense newExp = new Expense(e.getName(), e.getType(), e.getValue(), e.getDate().withYear(year).withMonth(month));
//				rep.addExpense(newExp);
//				updateReport(rep);
//			}
//				
//		}
//		
//		if (!occurence)
//		{
//			newReportAdded = true;
//			this.addReport(expenseDate);
//			Report newReport = this.getReport(expenseDate);
//			newReport.addExpense(e);
//			updateReport(newReport);
//		}
//		
//		return newReportAdded;
//	}
//	
//	
//	/**
//	 * Method to load fixed expenses to report
//	 * 	
//	 * <p>FIXED EXPENSES are loaded from previous month and put in new report
//	 * </p>
//	 * @param report 
//	 * 
//	 */
//	public void loadFixedExpenses(Report report)
//	{
//		
//		String idPreviousMonth = report.getYearMonth().minusMonths(1).toString();
//		YearMonth reportDate = YearMonth.parse(report.getId());
//
//		if (reportRepo.existsById(idPreviousMonth))
//		{
//			for(Expense exp: reportRepo.findById(idPreviousMonth).get().getExpenses())
//			{
//				if(exp.getType().equals(expenseType.FIXED))
//				{
//					int year = reportDate.getYear();
//					int month = reportDate.getMonthValue();
//					Expense newExp = new Expense(exp.getName(), exp.getType(), exp.getValue(), exp.getDate().withYear(year).withMonth(month));
//					report.addExpense(newExp);
//					updateReport(report);
//				}
//					
//			}
//		}
//		
//	}
//	
//	//TODO: remover metodo
//	/**
//	 * Method to add a new expense with name, type, value, and date
//	 * 
//	 * @param name - Name of Expense
//	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
//	 * @param value - Value in Euros
//	 * @param date - Date of Expense's Payment
//	 * 
//	 * @return false if year/month dont match with this report, true if expense is sucessfuly added to this report
//	 */
//	public boolean addExpense(Expense e) {
//		return loadExpense(e);
//		
//	}
//
//	//TODO: remover metodo
//	/**
//	 * Method to add a new expense with name, type, value, and date
//	 * 
//	 * @param name - Name of Expense
//	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
//	 * @param value - Value in Euros
//	 * @param date - Date of Expense's Payment
//	 * 
//	 * @return false if year/month dont match with this report, true if expense is sucessfuly added to this report
//	 */
//	public boolean addExpense(String name, expenseType type, double value, LocalDate date) {
//		Expense e = new Expense(name,type,value,date);
//
//		return loadExpense(e);
//		
//	}
//	
//	//TODO: remover metodo
//	/**
//	 * Method to add a new expense with name, type, value, date and description
//	 * 
//	 * @param name - Name of Expense
//	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
//	 * @param value - Value in Euros
//	 * @param date - Date of Expense's Payment
//	 * @param description - Description of Expense
//	 * 
//	 * @return false if year/month dont match with this report, true if expense is sucessfuly added to this report
//	 */
//	public boolean addExpense(String name, expenseType type, double value, LocalDate date, String description) {
//		Expense e = new Expense(name,type,value,date,description);
//		return loadExpense(e);
//	}
//	
//	
//	public void removeExpense(Expense e)
//	{
//		getCurrentOpenReport().removeExpense(e);
//	}
//	
//	/**
//	 * Method to get a report of a specific YearMonth
//	 * 
//	 * @param yearMonth
//	 * 
//	 * @return Report
//	 */
//	public Report getReport(YearMonth ym) {
//		for (Report rep: getAllReports())
//		{
//			if (rep.getId().equals(ym.toString()))
//				return rep;
//		}
//		return null;
//	}
//	
//	
//	/**
//	 * Method to get current open report 
//	 * 
//	 * @param yearMonth
//	 * 
//	 * @return Report
//	 */
//	public Report getCurrentOpenReport() {
//		for (Report rep: getAllReports())
//		{
//			if (rep.getId().equals(YearMonth.now().toString()))
//				return rep;
//		}
//		return null;
//	}
//	
//	
//	
//	/**
//	 * Method to calculate the total ROI of all reports, expect current monthly report
//	 * 
//	 * @return ROI AVEGARAGE (%) - double
//	 */
//	public double calculateRoiAllTime()
//	{
//	
//		if (this.getAllReports().isEmpty())
//			return 0;
//		if (this.getAllReports().get(0).getId().equals(YearMonth.now().toString()))
//			return 0;
//		double sumExpenses = 0;
//		double sumIncome = 0;
//		for (Report r: getAllReports())
//			if (!r.getId().equals(YearMonth.now().toString()))
//			{
//				sumExpenses= sumExpenses+r.calculateTotalExpensesValue();
//				sumIncome= sumIncome+r.calculateTotalSalesAmount();
//			}
//			
//		return (((sumIncome-sumExpenses)/sumExpenses)*100);
//	}
//	
//	
//	
//	/**
//	 * Method to calculate the average ROI of all reports, expect current monthly report
//	 * 
//	 * @return ROI AVEGARAGE (%) - double
//	 */
//	public double calculateAvgMonthlyRoi()
//	{
//		if (this.getAllReports().isEmpty())
//			return 0;
//		if (this.getAllReports().get(0).getId().equals(YearMonth.now().toString()))
//			return 0;
//		double sum = 0;
//		double count = 0;
//		for (Report r: getAllReports())
//			if (!r.getId().equals(YearMonth.now().toString()))
//			{
//				sum=sum+r.calculateRoi();
//				count++;
//			}
//			
//		return (sum/count);
//	}
//	
//	
//	/**
//	 * Method to calculate the average PROFIT of all reports, expect current monthly report
//	 * 
//	 * <p>Profit: All Sales - All Expenses of all closed reports</p>
//	 * 
//	 * @return PROFIT AVEGARAGE (%) - double
//	 */
//	public double calculateAvgMonthlyProfit()
//	{
//		if (getAllReports().isEmpty())
//			return 0;
//		if (getAllReports().get(0).getId().equals(YearMonth.now().toString()))
//			return 0;
//		double sum = 0;
//		double count = 0;
//		for (Report r: getAllReports())
//			if (!r.getId().equals(YearMonth.now().toString()))
//			{
//				sum=sum+r.calculateProfit();
//				count++;
//			}
//			
//		return (sum/count);
//	}
//	
//	
//	/**
//	 * Method to calculate the average Income of all reports, expect current monthly report
//	 * 
//	 * <p>Income: Sum of all sales amount </p>
//	 * 
//	 * @return INCOME AVEGARAGE (%) - double
//	 */
//	public double calculateAvgMonthlySalesAmount()
//	{
//		if (getAllReports().isEmpty())
//			return 0;
//		if (getAllReports().get(0).getId().equals(YearMonth.now().toString()))
//			return 0;
//		double sum = 0;
//		double count = 0;
//		for (Report r: getAllReports())
//			if (!r.getId().equals(YearMonth.now().toString()))
//			{
//				sum=sum+r.calculateTotalSalesAmount();
//				count++;
//			}
//			
//		return (sum/count);
//	}
//	
//	
//	/**
//	 * Method to calculate the average Expenses Value of all reports, expect current monthly report
//	 * 
//	 * <p>Expenses: Sum of all expenses value </p>
//	 * 
//	 * @return EXPENSES AVEGARAGE (%) - double
//	 */
//	public double calculateAvgMonthlyExpensesValue()
//	{
//		if (getAllReports().isEmpty())
//			return 0;
//		if (getAllReports().get(0).getId().equals(YearMonth.now().toString()))
//			return 0;
//		double sum = 0;
//		double count = 0;
//		for (Report r: getAllReports())
//			if (!r.getId().equals(YearMonth.now().toString()))
//			{
//				sum=sum+r.calculateTotalExpensesValue();
//				count++;
//			}
//			
//		return (sum/count);
//	}
//
//
//	public void setRepository(ReportRepository reportRepository) {
//		this.reportRepo = reportRepository;	
//	}
//	
//	
//
//}

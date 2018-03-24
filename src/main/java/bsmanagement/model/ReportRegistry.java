package bsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.jparepositories.ReportRepository;

/**
 * <h1> ReportRegistry </h1>
 * <p>
 * SaleList is the abstract class to manage a list of Sales in a business
 * context. This class contains information like:
 * <ul>
 * <li>ReportList - List of Monthly Reports
 * </ul>
 * <p>
 * To create an instance of ReportList you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
@Service
public class ReportRegistry {
	
	@Autowired
	private ReportRepository reportRepo;
//	@Autowired
//	private SaleRegistry saleRegistry;
//	@Autowired
//	private ExpenseRegistry expenseRegistry;

	/**
	 * Constructor of report registry
	 */
	public ReportRegistry() {
	
	}

	
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
			reportList.add(r);
		return reportList;
	}
	
	
//	/**
//	 * Get List of Sales Registry
//	 * 
//	 * @return the saleRegistry
//	 */
//	public SaleRegistry getSaleRegistry() {
//		return saleRegistry;
//	}


//	/**
//	 * Get the ExpenseRegistry
//	 * 
//	 * @return the expenseRegistry
//	 */
//	public ExpenseRegistry getExpenseRegistry() {
//		return expenseRegistry;
//	}
	
	
//	/**
//	 * Method to add a new sale with unknown customer
//	 * 
//	 * Sale is added to report associated to sale's date.
//	 * If there is no report with this sale's date, a new report is created with YearMonth associated to this sale's date.
//	 * 
//	 * @param date - DateTime of sale
//	 * @param product - Product sold
//	 * @param payment - Payment type
//	 * 
//	 * @return true if new Report has been created, false if already exist a report associated with this date.
//	 */
//	public boolean addSale(LocalDateTime date, Product product, PaymentMethod payment)
//	{
//		Sale s = saleRegistry.createSale(date, product, payment);
//		saleRegistry.addSale(s);
//		
//		return loadSale(s);
//	}
//	
	
	/**
	 * Method to Add new Report
	 * 
	 * @return true, if report was sucessfull added, and false if there is already a report with this yearMonth in ReportList
	 */
	public boolean addReport(YearMonth yearMonth)
	{
		Report rep = new Report(yearMonth);
		if (!reportRepo.exists(rep.getYearMonth().toString()))
		{
			reportRepo.save(rep);
			loadFixedExpenses(rep);
			return true;
		}
			
		return false;
		
	}
	
//	/**
//	 * Method to add a new sale with customer
//	 * 
//	 * Sale is added to report associated to sale's date.
//	 * If there is no report with this sale's date, a new report is created with YearMonth associated to this sale's date.
//	 * 
//	 * @param date - DateTime of sale
//	 * @param customer - Customer that bought the product
//	 * @param product - Product sold
//	 * @param payment - Payment type
//	 * 
//	 * @return true if new Report has been created, false if already exist a report associated with this date.
//	 */
//	public boolean addSale(LocalDateTime date,Customer customer, Product product, PaymentMethod payment)
//	{
//		Sale s = saleRegistry.createSale(date,customer,product, payment);
//		saleRegistry.addSale(s);
//		
//		return loadSale(s);
//	}
	
	
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
			}
		}
		
		if (!occurence)
		{
			newReportAdded = true;
			this.addReport(saleDate);
			this.getReport(saleDate).addSale(s);
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
			}
			if (repDate.isAfter(expDate))
			{
				int year = repDate.getYear();
				int month = repDate.getMonthValue();	
				Expense newExp = new Expense(e.getName(), e.getType(), e.getValue(), e.getDate().withYear(year).withMonth(month));
//				expenseRegistry.addExpense(newExp);
				rep.addExpense(newExp);
			}
				
		}
		
		if (!occurence)
		{
			newReportAdded = true;
			this.addReport(expDate);
			this.getReport(expDate).addExpense(e);
		}
		
		return newReportAdded;
	}
	
	
	
//	public boolean loadExpense(Expense e)
//	{
//		YearMonth expDate = YearMonth.of(e.getDate().getYear(), e.getDate().getMonth());
//		YearMonth repDate;
//		boolean occurence = false;
//		boolean newReportAdded = false;
//		for(Report rep: getReports())
//		{
//			repDate = YearMonth.parse(rep.getId());
//			if ((repDate.equals(expDate) && rep.addExpense(e)))
//			{
//				occurence = true;
//			}
//			if (repDate.isAfter(expDate))
//				rep.addExpense(e);
//		}
//		
//		if (!occurence)
//		{
//			newReportAdded = true;
//			this.addReport(expDate);
//			this.getReport(expDate).addExpense(e);
//		}
//		
//		return newReportAdded;
//	}
	
	
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
		if (reportRepo.findOne(idPreviousMonth)!=null)
		{
			for(Expense exp: reportRepo.findOne(idPreviousMonth).getExpensesList())
			{
				if(exp.getType().equals(expenseType.FIXED))
					report.addExpense(exp);
			}
		}
		
	}
	
	
	
	/**
	 * Method to add a new expense with name, type, value, and date
	 * 
	 * @param name - Name of Expense
	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
	 * @param value - Value in Euros
	 * @param date - Date of Expense's Payment
	 * 
	 * @return false if year/month dont match with this report, true if expense is sucessfuly added to this report
	 */
	public boolean addExpense(String name, expenseType type, double value, LocalDate date) {
		Expense e = new Expense(name,type,value,date);

		return loadExpense(e);
		
	}
	
	/**
	 * Method to add a new expense with name, type, value, date and description
	 * 
	 * @param name - Name of Expense
	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
	 * @param value - Value in Euros
	 * @param date - Date of Expense's Payment
	 * @param description - Description of Expense
	 * 
	 * @return false if year/month dont match with this report, true if expense is sucessfuly added to this report
	 */
	public boolean addExpense(String name, expenseType type, double value, LocalDate date, String description) {
		Expense e = new Expense(name,type,value,date,description);
		return loadExpense(e);
	}
	
//	/**
//	 * Method to remove an expense
//	 * 
//	 * @param Expense
//	 * 
//	 * @return True if expense was successful removed, false otherwise
//	 */
//	public boolean removeExpense(Expense expense) {
//		
//		YearMonth ym = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth());
//		for(Report rep: getReports())
//		{
//	
//			if (rep.getYearMonth().isAfter(ym))
//				rep.removeExpense(expense);
//		}
//		
//		return expenseRegistry.removeExpense(expense);
//	}
	
	
//	/**
//	 * Method to get a report of a specific YearMonth
//	 * 
//	 * @param yearMonth
//	 * 
//	 * @return Report
//	 */
//	public Report getReport(YearMonth ym) {
//		int index=-1;
//		for (Report rep: getReports())
//		{
//			if (rep.getId().equals(ym.toString()))
//				index=getReports().indexOf(rep);
//		}
//		if (index==-1)
//			return null; 
//		
//		return getReports().get(index);
//	}
	
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
	 * Method to calculate the average ROI of all reports, expect current monthly report
	 * 
	 * @return ROI AVEGARAGE (%) - double
	 */
	public double calculateAvgMonthlyRoi()
	{
		if (this.getReports().isEmpty())
			return 0;
		if (this.getReports().get(0).getYearMonth().equals(YearMonth.now()))
			return 0;
		double sum = 0;
		double count = 0;
		for (Report r: getReports())
			if (!r.getYearMonth().equals(YearMonth.now()))
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
		if (getReports().isEmpty())
			return 0;
		if (getReports().get(0).getYearMonth().equals(YearMonth.now()))
			return 0;
		double sum = 0;
		double count = 0;
		for (Report r: getReports())
			if (!r.getYearMonth().equals(YearMonth.now()))
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
		if (getReports().isEmpty())
			return 0;
		if (getReports().get(0).getYearMonth().equals(YearMonth.now()))
			return 0;
		double sum = 0;
		double count = 0;
		for (Report r: getReports())
			if (!r.getYearMonth().equals(YearMonth.now()))
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
		if (getReports().isEmpty())
			return 0;
		if (getReports().get(0).getYearMonth().equals(YearMonth.now()))
			return 0;
		double sum = 0;
		double count = 0;
		for (Report r: getReports())
			if (!r.getYearMonth().equals(YearMonth.now()))
			{
				sum=sum+r.calculateTotalExpensesValue();
				count++;
			}
			
		return (sum/count);
	}
	
	
	
	/**
	 * Method to update all reports
	 * 
	 * <p>For each report: Sales,Expenses and BusinessDays are updated</p>
	 */
//	public void updateReportList()
//	{
//		for(Report rep: reportList)
//		{
//			rep.setSales(saleList.findSalesOf(rep.getYearMonth()));
//			rep.setExpenses(expenseList.findExpensesOf(rep.getYearMonth()));
//			rep.updateBusinessDays();
//		}
//	}
	
	

}

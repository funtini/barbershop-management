package dbcmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import dbcmanagement.model.Expense.expenseType;

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
public class ReportRegistry {
	
	private List<Report> reportList;
	private SaleRegistry saleList;
	private ExpenseRegistry expenseList;

	/**
	 * Constructor of report registry
	 */
	public ReportRegistry() {
		reportList = new ArrayList<Report>();
		saleList = new SaleRegistry();
		expenseList = new ExpenseRegistry();
	}

	/**
	 * Get a list of all monthly reports
	 * 
	 * @return the reportList
	 */
	public List<Report> getReportList() {
		return reportList;
	}
	
	
	/**
	 * Get List of Sales
	 * 
	 * @return the saleList
	 */
	public SaleRegistry getSaleList() {
		return saleList;
	}


	/**
	 * Get the ExpenseList
	 * 
	 * @return the expenseList
	 */
	public ExpenseRegistry getExpenseList() {
		return expenseList;
	}
	
	
	/**
	 * Method to update all reports
	 * 
	 * <p>For each report: Sales,Expenses and BusinessDays are updated</p>
	 */
	public void updateReportList()
	{
		for(Report rep: reportList)
		{
			rep.setSales(saleList.findSalesOf(rep.getYearMonth()));
			rep.setExpenses(expenseList.findExpensesOf(rep.getYearMonth()));
			rep.updateBusinessDays();
		}
	}
	
	
	/**
	 * Method to add a new sale with unknown customer
	 * 
	 * @param date - DateTime of sale
	 * @param product - Product sold
	 * 
	 * @return false if year/month dont match with this report, true if sale is sucessfuly added to this report
	 */
	public void addSale(LocalDateTime date, Product product)
	{
		Sale s = saleList.createSale(date, product);
		saleList.addSale(s);
		YearMonth ym = YearMonth.of(date.getYear(), date.getMonth());
		boolean occurence = false;
		for(Report rep: reportList)
		{
			if (rep.addSale(s))
			{
				occurence = true;
			}
		}
		
		if (!occurence)
		{
			this.addReport(ym);
			this.getReport(ym).addSale(s);
		}
	}
	
	
	/**
	 * Method to Add new Report
	 * 
	 * @return true, if report was sucessfull added, and false if there is already a report with this yearMonth in ReportList
	 */
	public boolean addReport(YearMonth yearMonth)
	{
		Report rep = new Report(yearMonth);
		if (!reportList.contains(rep))
		{
			return reportList.add(rep);
		}
			
		return false;
		
	}
	
	/**
	 * Method to add a new sale with customer
	 * 
	 * @param date - DateTime of sale
	 * @param customer - Customer that bought the product
	 * @param product - Product sold
	 * 
	 * @return false if year/month dont match with this report, true if sale is sucessfuly added to this report
	 */
	public void addSale(LocalDateTime date,Customer customer, Product product)
	{
		Sale s = saleList.createSale(date,customer,product);
		saleList.addSale(s);
		YearMonth ym = YearMonth.of(date.getYear(), date.getMonth());
		boolean occurence = false;
		for(Report rep: reportList)
		{
			if (rep.addSale(s))
			{
				occurence = true;
			}
		}
		
		if (!occurence)
		{
			this.addReport(ym);
			this.getReport(ym).addSale(s);
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
	public void addExpense(String name, expenseType type, double value, LocalDate date) {
		Expense e = expenseList.createExpense(name,type,value,date);
		
		expenseList.addExpense(e);
		YearMonth ym = YearMonth.of(date.getYear(), date.getMonth());
		boolean occurence = false;
		for(Report rep: reportList)
		{
			if ((rep.getYearMonth().equals(ym) && rep.addExpense(e)))
			{
				occurence = true;
			}
			if (rep.getYearMonth().isAfter(ym))
				rep.addExpense(e);
		}
		
		if (!occurence)
		{
			this.addReport(ym);
			this.getReport(ym).addExpense(e);
		}
		
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
	public void addExpense(String name, expenseType type, double value, LocalDate date, String description) {
		Expense e = expenseList.createExpense(name,type,value,date,description);
		
		expenseList.addExpense(e);
		YearMonth ym = YearMonth.of(date.getYear(), date.getMonth());
		boolean occurence = false;
		for(Report rep: reportList)
		{
			if (rep.getYearMonth().equals(ym)&&rep.addExpense(e) || rep.getYearMonth().isAfter(ym)&&rep.addExpense(e))
			{
				occurence = true;
			}
		}
		
		if (!occurence)
		{
			this.addReport(ym);
			this.getReport(ym).addExpense(e);
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
		int index=-1;
		for (Report rep: reportList)
		{
			if (rep.getYearMonth().equals(ym))
				index=reportList.indexOf(rep);
		}
		if (index==-1)
			return null; 
		
		return reportList.get(index);
	}
	
	
	
	public double calculateAvgMonthlyRoi()
	{
		return 0;
	}
	
	public double calculateAvgMonthlyProfit()
	{
		return 0;
	}
	
	public double calculateAvgMonthlySalesAmount()
	{
		return 0;
	}
	
	public double calculateAvgMonthlyExpensesValue()
	{
		return 0;
	}
	
	
	
	

}

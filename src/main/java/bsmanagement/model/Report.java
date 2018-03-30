package bsmanagement.model;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import bsmanagement.model.Expense.expenseType;


/**
 * <h1> Report </h1>
 * <p>
 * Report is the abstract base class for all monthly reports of a business
 * context. This class contains information like:
 * <ul>
 * <li>YearMonth - Year and Month of Report
 * <li>salesList - A list of all sales made in this month, at this year
 * <li>expenseList - A list of all expenses made in this month, at this year
 * <li>businessDays - Number of business days in a month. It represents all working days in a certain month/year
 * </ul>
 * <p>
 * To create an instance of Report you need a Year, Month and a number of working/business days.
 * <p>
 * The salesList and expensesList must be updated after creating an instance of this class
 * 
 * @author JOAO GOMES
 *
 */
@Entity
public class Report {
	

	@Id
	private String id;
	@Transient
	private YearMonth yearMonth;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "report_id")
	private List<Sale> sales;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "report_id")
	private List<Expense> expenses;
	private int businessDays;
	
	
	/**
	 * Constructor of Report with a Year, Month and a number of business days
	 * 
	 * @param year - Year of this report
	 * @param month - Month of this report
	 * @param businessDays - number of business days
	 */
	public Report(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
		this.id = yearMonth.toString();
		this.sales = new ArrayList<>();
		this.expenses = new ArrayList<>();
		this.businessDays = 0;
	}
	
	protected Report ()
	{
		
	}
	
	/**
	 * @return the yearMonth
	 */
	public String getId() {
		return id;
	}
	
	
	/**
	 * @return the yearMonth
	 */
	public YearMonth getYearMonth() {
		return yearMonth;
	}
	
	/**
	 * @return the list of sales
	 */
	public List<Sale> getSalesList() {
		return sales;
	}
	
	/**
	 * @return the list of expenses
	 */
	public List<Expense> getExpensesList() {
		return expenses;
	}
	
	/**
	 * @return the business days
	 */
	public int getBusinessDays() {
		return businessDays;
	}

	/**
	 * Set report's YearMonth
	 * 
	 * @param year 
	 */
	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	/**
	 * Method to add a new sale to report
	 * 
	 * @param Sale - Instance of sale class
	 * 
	 * @return false if year/month dont match with this report, true if sale is sucessfuly added to this report
	 */
	public boolean addSale(Sale sale) {
		YearMonth reportId = YearMonth.parse(id);
		if (sale.getDate().getYear()!=reportId.getYear())
			return false;
		if (sale.getDate().getMonthValue()!=reportId.getMonthValue())
			return false;
		this.sales.add(sale);
		updateBusinessDays();
		return true;
	}
	
	/**
	 * Method to add a new expense with name, type, value, and date
	 * 
	 * @param expense - Instance of Expense class
	 * 
	 * @return false if year/month dont match with report, true if expense is sucessfuly added to report
	 */
	public boolean addExpense(Expense expense) {
		YearMonth reportId = YearMonth.parse(id);
		if (expenses.contains(expense))
			return false;
		if (expense.getType().equals(expenseType.FIXED))
		{
			this.expenses.add(expense);
			return true;
		}
		if (expense.getDate().getYear()!=reportId.getYear())
			return false;
		if (expense.getDate().getMonthValue()!=reportId.getMonthValue())
			return false;
		this.expenses.add(expense);
		return true;	
	}
	
	/**
	 * Method to remove an expense 
	 * 
	 * @param expense 
	 * 
	 */
	public void removeExpense(Expense expense) {
		expenses.remove(expense);
	}

	
	/**
	 * Set business days 
	 * 
	 * @param businessDays
	 */
	public void setBusinessDays(int businessDays) {
		this.businessDays = businessDays;
	}
	
	/**
	 * Check, and update if necessary, the business Days of Company in this YearMonth
	 * <p>The number of businessDays are calculated by counting the number of different day sales in this month
	 * 
	 * @return true if Business was updated, false if still same
	 */
	public boolean updateBusinessDays()
	{
		YearMonth repDate = YearMonth.parse(id);
		int count = 0;
		for (int i=1; i<=repDate.getMonth().length(Year.of(repDate.getYear()).isLeap());i++)
		{
			for (Sale s: getSalesList())
			{
				if (s.getDate().getDayOfMonth()==i)
				{
					count++;
					break;
				}
			}
		}
		if (this.businessDays==count)
			return false;
		this.businessDays=count;
		return true;
	}
	
	
	/**
	 * Calculate Current Profit/Loss of Report
	 * 
	 * @return double value
	 */
	public double calculateProfit()
	{
		return calculateTotalSalesAmount()-calculateTotalExpensesValue();

	}
	
	/**
	 * Calculate Current Return On Investment(ROI) of Report
	 * 
	 * @return double value (%)
	 */
	public double calculateRoi()
	{
		double income = calculateTotalSalesAmount();
		double expense = calculateTotalExpensesValue();
		if (expense == 0)
			return 0;
		
		return (((income-expense)/expense)*100);
	}
	
	/**
	 * Calculate Total Expenses of Report
	 * 
	 * @return double value
	 */
	public double calculateTotalExpensesValue()
	{
		double sum = 0;
		for (Expense e: getExpensesList())
			sum=sum+e.getValue();
		return sum;
	}
	
	
	/**
	 * Calculate Total Sales of Report
	 * 
	 * @return double value
	 */
	public double calculateTotalSalesAmount()
	{
		double sum = 0;
		for (Sale s: getSalesList())
			sum=sum+s.getAmount();
		return sum;
	}
	
	


	/**
	 * @return hashCode of Report
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((yearMonth == null) ? 0 : yearMonth.hashCode());
		return result;
	}


	/**
	 * @param Report
	 * 
	 * @return true if the Reports are the same, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (yearMonth == null) {
			if (other.yearMonth != null)
				return false;
		} else if (!yearMonth.equals(other.yearMonth))
			return false;
		return true;
	}


	/**
	 * @return String with sale attributes information
	 */
	@Override
	public String toString() {
		return "Report [year=" + yearMonth.getYear() + ", month=" + yearMonth.getMonth().toString() + ", sales=" + sales + ", expenses=" + expenses + ", businessDays="
				+ businessDays + "]";
	}

	
}

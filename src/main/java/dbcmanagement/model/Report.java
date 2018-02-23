package dbcmanagement.model;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import dbcmanagement.model.Expense.expenseType;


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
public class Report {
	

	private YearMonth yearMonth;
	private SaleRegistry salesList;
	private ExpenseRegistry expensesList;
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
		this.salesList = new SaleRegistry();
		this.expensesList = new ExpenseRegistry();
		this.businessDays = 0;
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
	public SaleRegistry getSalesList() {
		return salesList;
	}
	
	/**
	 * @return the list of expenses
	 */
	public ExpenseRegistry getExpensesList() {
		return expensesList;
	}
	
	/**
	 * @return the business days
	 */
	public int getBusinessDays() {
		return businessDays;
	}

	/**
	 * @param sales the sales to set
	 */
	public void setSales(List<Sale> salesList) {
		this.salesList.setListOfSales(salesList);
	}

	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(List<Expense> expensesList) {
		this.expensesList.setExpenseList(expensesList);
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
		if (sale.getDate().getYear()!=this.yearMonth.getYear())
			return false;
		if (sale.getDate().getMonthValue()!=this.yearMonth.getMonthValue())
			return false;
		this.salesList.addSale(sale);
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
		if (expense.getType().equals(expenseType.FIXED))
		{
			this.expensesList.addExpense(expense);
			return true;
		}
		if (expense.getDate().getYear()!=this.yearMonth.getYear())
			return false;
		if (expense.getDate().getMonthValue()!=this.yearMonth.getMonthValue())
			return false;
		this.expensesList.addExpense(expense);
		return true;	
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
		int count = 0;
		for (int i=1; i<=this.yearMonth.getMonth().length(Year.of(this.yearMonth.getYear()).isLeap());i++)
		{
			for (Sale s: salesList.getSaleList())
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
	
	public double calculateProfit()
	{
		return 0;
	}
	
	public double calculateRoi()
	{
		return 0;
	}
	
	public double calculateTotalExpenseValue()
	{
		return 0;
	}
	
	public double calculateTotalSalesAmount()
	{
		return 0;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((yearMonth == null) ? 0 : yearMonth.hashCode());
		return result;
	}


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


	@Override
	public String toString() {
		return "Report [year=" + yearMonth.getYear() + ", month=" + yearMonth.getMonth().toString() + ", sales=" + salesList + ", expenses=" + expensesList + ", businessDays="
				+ businessDays + "]";
	}

}

package dbcmanagement.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import dbcmanagement.model.Expense.expenseType;


/**
 * <h1> ExpenseRegistry </h1>
 * <p>
 * ExpenseRegistry is the abstract class to manage a list of Expenses in a business
 * context. This class contains information like:
 * <ul>
 * <li>listOfExpenses - List of expenses
 * </ul>
 * <p>
 * To create an instance of ExpenseRegistry you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
public class ExpenseRegistry {
	
	private List<Expense> expenseList;
	
	/**
	 * Constructor of list of expenses
	 */
	public ExpenseRegistry() {
		expenseList = new ArrayList<>();
	}

	/**
	 * @return the listOfExpenses
	 */
	public List<Expense> getExpenseList() {
		return expenseList;
	}

	/**
	 * Set a List of Expenses
	 * 
	 * @param listOfExpenses
	 */
	public void setExpenseList(List<Expense> listOfExpenses) {
		this.expenseList = listOfExpenses;
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
		this.expenseList.add(expense);
	}

	/**
	 * Method to get a list of expenses in that YearMonth
	 * 
	 * @param yearMonth 
	 * 
	 * @return list of Expense
	 */
	public List<Expense> findExpensesOf(YearMonth yearMonth) {
		List<Expense> listExp = new ArrayList<>();
		for (Expense e: this.expenseList)
		{
			if (e.getDate().getYear()==yearMonth.getYear() && e.getDate().getMonth().equals(yearMonth.getMonth()))
				listExp.add(e);
		}
		
		return listExp;
	}
	
	/**
	 * Method to get a list of expenses of specific expenseType
	 * 
	 * @param expenseType {FIXED / ONEOFF} 
	 * 
	 * @return list of Expense
	 */
	public List<Expense> findExpensesByType(expenseType type)
	{
		List<Expense> listExp = new ArrayList<>();
		for (Expense e: this.expenseList)
		{
			if (e.getType().equals(type))
				listExp.add(e);
		}
		
		return listExp;
	}
	
	
	
	public List<Expense> filterExpensesBelowValue(double value)
	{
		return null;
	}
	
	public List<Expense> filterExpensesAboveValue(double value)
	{
		return null;
	}
	
	public Expense findExpenseById(int id)
	{
		return null;
	}
	
	public List<Expense> searchExpenseByName(String name)
	{
		return null;
	}
	
	public List<Expense> findExpensesBetweenDates(LocalDate startDate,LocalDate endDate)
	{
		return null;
	}
	
	public double sumAllExpensesValue()
	{
		return 0;
	}
	
	

	

}

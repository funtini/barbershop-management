package bsmanagement.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.jparepositories.ExpenseRepository;


/**
 * <h1> ExpenseService </h1>
 * <p>
 * ExpenseService is a base class to manage a list of Expenses in a business
 * context. This class contains information like:
 * <ul>
 * <li>expRepo - Expense Repository
 * </ul>
 * <p>
 * To create an instance of ExpenseRegistry you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expRepo;
	
	/**
	 * Default Constructor of Expense Service
	 */
	public ExpenseService() {
		
	}
	
	
	
	public ExpenseService(ExpenseRepository expRepo) {
		this.expRepo = expRepo;
	}



	public boolean updateExpense(Expense expense)
	{
		if (expRepo.existsById(expense.getId())) {
            expRepo.save(expense);
            return true;
        }
        return false;
	}

	/**
	 * @return the listOfExpenses
	 */
	public List<Expense> getExpenses() {
		List<Expense> expenses = new ArrayList<>();
		for (Expense e: expRepo.findAll())
			expenses.add(e);
		return expenses;
	}
//	
//	/**
//	 * Method to create a new instance of expense with name, type, value, and date
//	 * 
//	 * @param name - Name of Expense
//	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
//	 * @param value - Value in Euros
//	 * @param date - Date of Expense's Payment
//	 * 
//	 * @return Expense
//	 */
//	public Expense createExpense(String name, expenseType type, double value, LocalDate date) {
//		Expense e = new Expense(name,type,value,date);
//		return e;
//	}
	
	
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
	 * Method to add a new Expense and load it to respective report
	 * 
	 * @param expense - Instance of Expense class
	 */
	public Expense addExpense(Expense expense) {
		return expRepo.save(expense);
	}
	
	/**
	 * <p>Method to delete a Expense from repository.</p>
	 * 
	 */
	public void removeExpense(Expense expense) {
		expRepo.delete(expense);
	}
	
	
	/**
	 * Method to get a list of expenses in that YearMonth
	 * 
	 * @param yearMonth 
	 * 
	 * @return list of Expense
	 */
	public List<Expense> findExpensesOfMonth(YearMonth yearMonth) {
		List<Expense> listExp = new ArrayList<Expense>();
		for (Expense e: getExpenses())
		{
			if (e.getDate().getYear()==yearMonth.getYear() && e.getDate().getMonth().equals(yearMonth.getMonth()))
				listExp.add(e);
		}
		
		return listExp;
	}
	
	/**
	 * Method to get a list of expenses in specific day
	 * 
	 * @param LocalDate 
	 * 
	 * @return list of Expense
	 */
	public List<Expense> findExpensesOfDay(LocalDate day) {
		List<Expense> listExp = new ArrayList<Expense>();
		for (Expense e: getExpenses())
		{
			if (e.getDate().equals(day))
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
		List<Expense> listExp = new ArrayList<Expense>();
		for (Expense e: getExpenses())
		{
			if (e.getType().equals(type))
				listExp.add(e);
		}
		
		return listExp;
	}
	
	
	/**
	 * Method to filter a list of expenses of below certain value
	 * 
	 * @param value
	 * 
	 * @return list of Expense
	 */
	public List<Expense> filterExpensesBelowValue(double value)
	{
		List<Expense> listExp = new ArrayList<Expense>();
		for (Expense e: getExpenses())
		{
			if (e.getValue()<=value)
				listExp.add(e);
		}
		
		return listExp;
	}
	
	
	/**
	 * Method to filter a list of expenses above certain value
	 * 
	 * @param value
	 * 
	 * @return list of Expense
	 */
	public List<Expense> filterExpensesAboveValue(double value)
	{
		List<Expense> listExp = new ArrayList<Expense>();
		for (Expense e: getExpenses())
		{
			if (e.getValue()>=value)
				listExp.add(e);
		}
		
		return listExp;
	}
	
	
	/**
	 * Method to find a expense by his ID
	 * 
	 * @param id
	 * 
	 * @return Expense
	 */
	public Expense findExpenseById(int id)
	{
		for (Expense e: getExpenses())
		{
			if (e.getId()==id)
				return e;
		}
		
		return null;
	}
	
	
	/**
	 * Method to search expenses filtering name
	 * 
	 * @param name
	 * 
	 * @return list of Expense
	 */
	public List<Expense> searchExpenseByName(String name)
	{
		List<Expense> listExp = new ArrayList<Expense>();
		for (Expense e: getExpenses())
		{
			if (e.getName().equals(name))
				listExp.add(e);
		}
		
		return listExp;
	}
	
	
	/**
	 * Method to get a list of expenses between two specific dates 
	 * 
	 * @param expenseType {FIXED / ONEOFF} 
	 * 
	 * @return list of Expense
	 */
	public List<Expense> findExpensesBetweenDates(LocalDate startDate,LocalDate endDate)
	{
		List<Expense> listExp = new ArrayList<Expense>();
		for (Expense e: getExpenses())
		{
			if (e.getDate().isAfter(startDate.minusDays(1)) && e.getDate().isBefore(endDate.plusDays(1)))
				listExp.add(e);
		}
		
		return listExp;
	}
	
	
	/**
	 * Method to get a sum of all expense's value
	 * 
	 * @return sum
	 */
	public double sumAllExpensesValue()
	{
		double sum = 0;
		for (Expense e: getExpenses())
		{
			sum=sum+e.getValue();
		}
		
		return sum;
	}
	
	
	/**
	 * Method to get a sum of all expense's value of certain expenseType
	 * 
	 * @param expenseTYpe
	 * 
	 * @return sum
	 */
	public double sumExpensesValueByType(expenseType type)
	{
		double sum = 0;
		for (Expense e: getExpenses())
		{
			if (e.getType().equals(type))
				sum=sum+e.getValue();		
		}	
		return sum;
	}

	public void setRepository(ExpenseRepository expenseRepository) {
		this.expRepo = expenseRepository;
		
	}


}

package bsmanagement.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <h1> Expense </h1>
 * <p>
 * Expense is the abstract base class for all expenses registered in a shop
 * context. This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier
 * <li>Name - Name of Expense
 * <li>Type - An enum constant of expenseType: FIXED or ONEOFF
 * <li>Value - Value of expense's payment in Euros
 * <li>Date - Date of Expense. This date can be null if this is a fixed expense
 * <li>Amount - Value of Product Sold at the time of Sale
 * </ul>
 * <p>
 * An instance of Product is needed to construct a Sale but it can be constructed without any associated customer, like an anonymous's sale.
 * <p>
 * In certain sales, the value of amount can be different of product's price along time, which means
 * that the product's price today can be different of yesterday, and the value of sale's amount is always the same along time.
 * 
 * @author JOAO GOMES
 */
@Entity
public class Expense implements Comparable<Expense>{
	
	
	/**
	 * Type of Expenses:
	 * 
	 * <li>{@link #FIXED}</li>
	 * <li>{@link #ONEOFF}</li>
	 * 
	 * @author JOAO GOMES
	 *
	 */
	public enum expenseType {
		
		/**
		 *  Fixed expenses are those that do not fluctuate with sales volume,
		 *  and every month, in a certain day, the shopper has that expense.
		 *  
		 *  This costs include such expenses as rent, insurance, dues and subscriptions,
		 *  equipment leases, payments on loans, depreciation, management salaries, and advertising. 
		 */
		FIXED, 
		
		/**
		 * One-Off expenses or variable costs are those that respond directly and proportionately to changes in activity level or volume,
		 * such as raw materials, hourly production wages, sales commissions, inventory, packaging supplies, and shipping costs.
		 * 
		 */
		ONEOFF
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private expenseType type;
	private double valueOfPayment;
	private LocalDate dateOfPayment;
	private String description;
	
	private static AtomicInteger idGenerator=new AtomicInteger();
	
	/**
	 * Constructor of Expense with name, type, value, and date
	 * 
	 * @param name - Name of Expense
	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
	 * @param value - Value in Euros
	 * @param date - Date of Expense's Payment
	 */
	public Expense(String name, expenseType type, double value, LocalDate date) {
		this.name = name;
		this.type = type;
		this.valueOfPayment = value;
		this.dateOfPayment = date;
		this.description = "";
		
	}
	
	/**
	 * Constructor of Expense with name, type, value, date and description
	 * 
	 * @param name - Name of Expense
	 * @param type - Type of Expense, if FIXED the date is set null, if ONEOFF the date is set to today
	 * @param value - Value in Euros
	 * @param date - Date of Expense's Payment
	 * @param description - Description of Expense
	 */
	public Expense(String name, expenseType type, double value, LocalDate date, String description) {
		this.name = name;
		this.type = type;
		this.valueOfPayment = value;
		this.description = description;
		this.dateOfPayment = date;
	}
	
	protected Expense()
	{
		
	}
	
	
	
	
	/**
	 * @return the id of Expense
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name of Expense
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type of Expense
	 */
	public expenseType getType() {
		return type;
	}

	/**
	 * @return the value of Expense
	 */
	public double getValue() {
		return valueOfPayment;
	}

	/**
	 * @return the date of Expense
	 */
	public LocalDate getDate() {
		return dateOfPayment;
	}

	/**
	 * @return the description of Expense
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set Expense's Id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set new name to Expense
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Change type of Expense
	 * 
	 * @param type - if FIXED, the expense's date is automatically set to NULL
	 */
	public void setType(expenseType type) {
		if (type == expenseType.FIXED)
			this.dateOfPayment = null;
		else
			this.dateOfPayment = LocalDate.now();
		
		this.type = type;
	}

	/**
	 * Set Value in Euros to Expense
	 * 
	 * @param value
	 */
	public void setValue(double value) {
		this.valueOfPayment = value;
	}

	/**
	 * Set new Date to Expense
	 * 
	 * @param date
	 * 
	 */
	public void setDate(LocalDate date) {
	
		this.dateOfPayment = date;		
	}

	/**
	 * Set Expense's description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
//	/**
//	 * 
//	 * @return activation status, reports can load this expense
//	 */
//	public boolean isActive()
//	{
//		return isActive;
//	}
//	
//	/**
//	 * disable expense for next reports
//	 */
//	public void disable()
//	{
//		this.isActive=false;
//	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Expense other = (Expense) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", type=" + type + ", value=" + valueOfPayment + "]";
	}

	@Override
	public int compareTo(Expense expense)
	{
		if (this.dateOfPayment==null)
			return 1;
		if (expense.getDate()==null)
			return -1;
		if (this.dateOfPayment.isBefore(expense.getDate()))
			return 1;
		if (this.dateOfPayment.isAfter(expense.getDate()))
			return -1;
		
		return 0;
	}

	public static void setStartIdGenerator(int i) {
		idGenerator.set(i-1);
		
	}

	public static int getAndIncrementId() {
		return idGenerator.incrementAndGet();
	}
	
	
	
	
	
	
}



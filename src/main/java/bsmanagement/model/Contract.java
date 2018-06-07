package bsmanagement.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Embeddable;



/**
 * 
 * <h1> Contract </h1>
 * <p>
 * Contract is a class for manage user's contracts.
 * This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier
 * <li>BaseSalary - Base Salary of contract
 * <li>SalesComission - Sales Comission by all sales
 * <li>StartDate - Start Date of contract
 * <li>CloseDate - End Date of contract
 * <li>
 * </ul>
 * <p>
 * 
 * @author JOAO GOMES
 *
 */
@Embeddable
public class Contract {
	
	private static AtomicInteger idGenerator=new AtomicInteger();
	
	private int id=idGenerator.incrementAndGet();;
	private double baseSalary;
	private double salesComission;
	private LocalDate startDate;
	private LocalDate closeDate;
	
	
	/**
	 * Class constructor.
	 *
	 * @param baseSalary
	 *            Base Monthly Salary of contract.
	 * @param salesComission
	 *            Comission per sale in %.
	 *            
	 */
	public Contract(double baseSalary, double salesComission) {
		this.baseSalary = baseSalary;
		if (salesComission < 0.0)
			salesComission = 0.0;
		if (salesComission > 100)
			salesComission = 100.0;
		this.salesComission = salesComission;
		this.startDate = LocalDate.now();
		
	}
	
	protected Contract() {
	}
	
	/**
	 * Static method to set new start id generator
	 * 
	 * @param num
	 */
	public static void setStartIdGenerator(int num)
	{
		idGenerator.set(num-1);
	}

	public int getId()
	{
		return id;
	}

	public double getBaseSalary() {
		return baseSalary;
	}


	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}


	public double getSalesComission() {
		return salesComission;
	}


	public void setSalesComission(double salesComission) {
		if (salesComission < 0.0)
			salesComission = 0.0;
		if (salesComission > 100)
			salesComission = 100.0;
		this.salesComission = salesComission;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getCloseDate() {
		return closeDate;
	}
	

	
	/**
	 * <p>Method to check if contract is open</p>
	 * A contract is open if closedate still empty or null.
	 * 
	 *  @return true if contract is open, false otherwise
	 *            
	 */
	public boolean isOpen()
	{
		if (this.closeDate == null)
			return true;
		return false;
	}
	

	/**
	 * <p>Method to close contract</p>
	 * The closeDate of contract is setted to LocalDate.now().
	 *     
	 * @return true if contract was sucessfully closed, false if contract is already closed
	 */
	public boolean close()
	{
		if (isOpen())
		{
			this.closeDate=LocalDate.now();
			return true;
		}
		return false;
	}
	
	
	/**
	 * <p>Method to close contract at specific date</p>
	 *  
	 *  @return true if operation is valid (date is after startDate), false otherwise
	 */
	public boolean closeAt(LocalDate date)
	{
		if (startDate.isBefore(date))
		{
			this.closeDate=date;
			return true;
		}
		return false;
		
	}
	
	

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
		Contract other = (Contract) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", baseSalary=" + baseSalary + ", salesComission=" + salesComission
				+ ", startDate=" + startDate + ", closeDate=" + closeDate + "]";
	}
	
	
	

}

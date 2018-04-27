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
	
	
	public Contract(double baseSalary, double salesComission) {
		this.baseSalary = baseSalary;
		this.salesComission = salesComission;
		this.startDate = LocalDate.now();
		
	}
	
	protected Contract() {
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

	public boolean isOpen()
	{
		if (this.closeDate == null)
			return false;
		return true;
	}
	

	public boolean close()
	{
		if (isOpen())
		{
			this.closeDate=LocalDate.now();
			return true;
		}
		return false;
	}
	
	
	

}

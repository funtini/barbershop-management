package dbcmanagement.model;

import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1> SaleRegistry </h1>
 * <p>
 * SaleRegistry is the abstract class to manage a list of Sales in a business
 * context. This class contains information like:
 * <ul>
 * <li>listOfSales - List of sales
 * </ul>
 * <p>
 * To create an instance of SaleRegistry you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
public class SaleRegistry {
	
	private List<Sale> saleList;


	/**
	 * Constructor of list of sales
	 */
	public SaleRegistry() {
		saleList = new ArrayList<Sale>();
	}

	/**
	 * @return the listOfSales
	 */
	public List<Sale> getSaleList() {
		return saleList;
	}

	/**
	 * @param listOfSales the listOfSales to set
	 */
	public void setListOfSales(List<Sale> listOfSales) {
		this.saleList = listOfSales;
	}
	
	/**
	 * Method to create a new instance of sale with known customer
	 * 
	 * @param date - DateTime of sale
	 * @param product - Product sold
	 */
	public Sale createSale(LocalDateTime date, Product product)
	{
		Sale s = new Sale(date,product);
		
		return s;
	}
	
	/**
	 * Method to create a new instance of sale with unknown customer
	 * 
	 * @param date - DateTime of sale
	 * @param product - Product sold
	 */
	public Sale createSale(LocalDateTime date, Customer customer, Product product) {
		Sale s = new Sale(date,customer,product);
		return s;
	}
	
	
	/**
	 * Method to add a new sale 
	 * 
	 * @param sale - Instance of Sale class
	 */
	public boolean addSale(Sale sale)
	{
		if(this.saleList.contains(sale))
			return false;
		return this.saleList.add(sale);
	}
	

	/**
	 * Method to find a list of sales of specific YearMonth
	 * 
	 * @param yearMonth
	 * 
	 * @return List of Sales
	 */
	public List<Sale> findSalesOf(YearMonth yearMonth) {
		
		List<Sale> listSale = new ArrayList<Sale>();
		for (Sale s: saleList)
		{
			if (s.getDate().getYear()== yearMonth.getYear() && s.getDate().getMonth().equals(yearMonth.getMonth()))
				listSale.add(s);
		}
		return listSale;
	}
	
	/**
	 * Method to find a list of sales of specific Customer
	 * 
	 * @param Customer
	 * 
	 * @return List of Sales
	 */
	public List<Sale> findSalesByCustomer(Customer customer)
	{
		List<Sale> listSale = new ArrayList<Sale>();
		for (Sale s: saleList)
		{
			if (customer.equals(s.getCustomer())) 
				listSale.add(s);
		}
		return listSale;
	}
	
	/**
	 * Method that sum all amounts of all sales
	 * 
	 * @return sum
	 */
	public double sumAllAmounts()
	{
		double sum = 0;
		for (Sale s: saleList)
		{
			sum=sum+s.getAmount();
		}
		return sum;
	}
	
	/**
	 * Method to find a Sale by ID
	 * 
	 * @param Id
	 * 
	 * @return Sale if exist, null if dont exist
	 */
	public Sale findSaleById(int id)
	{
		for (Sale s: saleList)
		{
			if (s.getId()==id)
				return s;
		}
		return null;
	}
	
	/**
	 * Method to find a list of sales between two specific Dates
	 * 
	 * <p>ATTENTION</p> if startDate is older than endDate, the return value is an emptyList
	 * 
	 * @param startDate
	 * @param endDate
	 * 
	 * @return List of Sales
	 */
	public List<Sale> findSalesBetweenDates(LocalDate startDate,LocalDate endDate)
	{
		List<Sale> listSale = new ArrayList<Sale>();
		if (startDate.isAfter(endDate))
			return listSale;
		LocalDate saleDate;
		for (Sale s: saleList)
		{
			saleDate=s.getDate().toLocalDate();
			if (!(saleDate.isBefore(startDate) || saleDate.isAfter(endDate))) 
				listSale.add(s);
		}
		return listSale;
	}
	
	

}

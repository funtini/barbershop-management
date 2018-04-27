package bsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.jparepositories.SaleRepository;

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
@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepo;
	private List<PaymentMethod> availablePaymentMethods;


	/**
	 * Constructor of SaleRegistry
	 */
	public SaleService() {
		availablePaymentMethods = new ArrayList<>();
	}

	/**
	 * @return list of sales
	 */
	public List<Sale> getSales() {
		List<Sale> saleList = new ArrayList<>();
		for (Sale s : saleRepo.findAll())
			saleList.add(s);
		return saleList;
	}
	
	/**
	 * @return List of available payment methods
	 */
	public List<PaymentMethod> getAvailablePaymentMethods() {
		return availablePaymentMethods;
	}

	/**
	 * Add new payment method
	 * 
	 * @param PaymentMethod
	 * 
	 * @return true if NameType of payment doesnt existe, false if name already exist or if name is empty/null
	 */
	public boolean addPaymentMethod(PaymentMethod payment)
	{
		if (payment.isValid() && !getAvailablePaymentMethods().contains(payment)) {
			availablePaymentMethods.add(payment);
			return true;
		}
			
		return false;
	}

	
	/**
	 * Method to create a new payment method
	 * 
	 * @param name
	 * @param fee
	 * @param minFeeValue
	 * 
	 * @return PaymentMethod instance
	 */
	public PaymentMethod createPaymentMethod(String name, double fee, double minFeeValue)
	{
		PaymentMethod p = new PaymentMethod(name,fee,minFeeValue);
		
		return p;
	}
	
	
	/**
	 * Method to create a new instance of sale with known customer
	 * 
	 * @param date - DateTime of sale
	 * @param product - Product sold
	 * @param payment - type of payment
	 */
	public Sale createSale(LocalDateTime date, Product product, PaymentMethod payment, User user)
	{
		Sale s = new Sale(date,product,payment, user);
		
		return s;
	}
	
	/**
	 * Method to create a new instance of sale with unknown customer
	 * 
	 * @param date - DateTime of sale
	 * @param product - Product sold
	 * @param payment - type of payment
	 */
	public Sale createSale(LocalDateTime date, Customer customer, Product product, PaymentMethod payment, User user) {
		Sale s = new Sale(date,customer,product,payment, user);
		return s;
	}
	
	
	/**
	 * Method to add a new sale 
	 * 
	 * @param sale - Instance of Sale class
	 */
	public boolean addSale(Sale sale)
	{
		if(saleRepo.existsById(sale.getId()))
			return false;
		saleRepo.save(sale);
		return true;
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
		for (Sale s: getSales())
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
		for (Sale s: getSales())
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
		for (Sale s: getSales())
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
	 * @return Sale if exist, null if don't exists
	 */
	public Sale findSaleById(int id)
	{
		for (Sale s: getSales())
		{
			if (s.getId()==id)
				return s;
		}
		return null;
	}
	
	/**
	 * Method to get sales payed by a specific payment method
	 * 
	 * @param PaymentMethod
	 * 
	 * @return List of Sales
	 */
	public List<Sale> findSalesPayedBy(PaymentMethod payment)
	{
		List<Sale> salesList = new ArrayList<Sale>();
		for (Sale s: getSales())
		{
			if (s.getPayment().equals(payment))
				salesList.add(s);
		}
		return salesList;
	}
	
	
	
	/**
	 * Method to get a all amount payed by a specific payment method
	 * 
	 * @param PaymentMethod
	 * 
	 * @return double value - total amount of a specific payment method
	 */
	public double sumAllAmountsPayedBy(PaymentMethod payment)
	{
		double sum = 0;
		for (Sale s: getSales())
		{
			if (s.getPayment().equals(payment))
				sum=sum+s.getAmount();
		}
		return sum;
	}
	
	/**
	 * Method to get total amount of fee's payed
	 * 
	 * @param PaymentMethod
	 * 
	 * @return double value - total amount of a specific payment method
	 */
	public double calculateTotalFeeAmount()
	{
		double sum = 0;
		for (Sale s: getSales())
		{
			sum=sum+s.calculateFeeValue();
		}
		return sum;
	}
	
	
	/**
	 * Method to find a list of sales between two specific Dates
	 * 
	 * <p>ATTENTION: if startDate is older than endDate, the return value is an emptyList</p>
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
		for (Sale s: getSales())
		{
			saleDate=s.getDate().toLocalDate();
			if (!(saleDate.isBefore(startDate) || saleDate.isAfter(endDate))) 
				listSale.add(s);
		}
		return listSale;
	}

	public void setRepository(SaleRepository saleRepository) {
		this.saleRepo = saleRepository;
	}
	
	

}

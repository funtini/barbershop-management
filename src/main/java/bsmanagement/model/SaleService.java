package bsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.jparepositories.PaymentRepository;
import bsmanagement.model.jparepositories.SaleRepository;

/**
 * <h1> SaleService </h1>
 * <p>
 * SaleService is a service class to manage a list of Sales in a business
 * context. This class contains information like:
 * <ul>
 * <li>saleRepo - SaleRepository
 * <li>paymentRepository - PaymentRepository
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
	@Autowired
	private PaymentRepository paymentRepository;


	/**
	 * Constructor of SaleRegistry
	 */
	public SaleService() {

	}
	
	
	
	public SaleService(SaleRepository saleRepo, PaymentRepository paymentRepository) {
		super();
		this.saleRepo = saleRepo;
		this.paymentRepository = paymentRepository;
	}



	public void setPaymentRepository(PaymentRepository paymentRepository) {
		this.paymentRepository=paymentRepository;
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
		return paymentRepository.findAll();
	}
	
	/**
	 * @return payment method if id is valid, null otherwise
	 */
	public PaymentMethod findPaymentMethodById(String paymentName) {
		for (PaymentMethod payment : paymentRepository.findAll())
		{
			if(payment.getType().equals(paymentName))
				return payment;
		}
		return null;
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
		if (payment.isValid() && !paymentRepository.existsById(payment.getType())) {
			paymentRepository.save(payment);
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
		if (p.getType()!= null)
			p.setType(name.toUpperCase());
		
		return p;
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
		if(sale.getProduct()==null || sale.getDate()==null)
			return false;
			
		saleRepo.save(sale);
		return true;
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
	 * Method to find a list of sales of specific YearMonth
	 * 
	 * @param yearMonth
	 * 
	 * @return List of Sales
	 */
	public List<Sale> findSalesOfMonth(YearMonth yearMonth) {
		
		List<Sale> listSale = new ArrayList<Sale>();
		for (Sale s: getSales())
		{
			if (s.getDate().getYear()== yearMonth.getYear() && s.getDate().getMonth().equals(yearMonth.getMonth()))
				listSale.add(s);
		}
		return listSale;
	}
	
	/**
	 * Method to get a list of sales of specific day
	 * 
	 * @param day (LocalDate)
	 * 
	 * @return List of Sales
	 */
	public List<Sale> findSalesOfDay(LocalDate day) {
		
		List<Sale> listSale = new ArrayList<Sale>();
		for (Sale s: getSales())
		{
			if (s.getDate().toLocalDate().equals(day))
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
	
	/**
	 * Method to find a list of sales of specific User
	 * 
	 * @param User
	 * 
	 * @return List of Sales
	 */
	public List<Sale> findSalesByUser(User user)
	{
		List<Sale> listSale = new ArrayList<Sale>();
		for (Sale s: getSales())
		{
			if (user.equals(s.getUser())) 
				listSale.add(s);
		}
		return listSale;
	}
	
	
	
	/**
	 * Method to calculate total accumulate comission's amount of specific month 
	 * 
	 * @param user
	 * @param month
	 * @return
	 */
	public double calculateUserComissionAmountInMonth(User user,YearMonth yearMonth)
	{
		List<Sale> sales = new ArrayList<Sale>();
		for (Sale s: findSalesByUser(user))
		{
			YearMonth saleDate = YearMonth.from(s.getDate());
			if(saleDate.equals(yearMonth))
				sales.add(s);
		}
		double totalAmount = 0.0;
		for (Sale s: sales)
		{
			totalAmount=totalAmount+s.getAmount()*user.getSalesCommissionOfMonth(yearMonth);
		}
		Math.round(totalAmount);
		return totalAmount/100;
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
	 * Method to get total amount of fee's payed
	 * 
	 * @param PaymentMethod
	 * 
	 * @return double value - total amount of a specific payment method
	 */
	public double calculateAllTimeFeeAmount()
	{
		double sum = 0;
		for (Sale s: getSales())
		{
			sum=sum+s.calculateFeeValue();
		}
		return sum;
	}
	
	/**
	 * Method to update a PaymentMethod
	 * 
	 * @param payment
	 * @return
	 */
	public boolean updatePaymentMethod(PaymentMethod payment) {
		if (paymentRepository.existsById(payment.getType()))
		{
			paymentRepository.save(payment);
			return true;
		}
		return false;
	}


	public void setSaleRepository(SaleRepository saleRepository) {
		this.saleRepo = saleRepository;
	}

	public void removePaymentMethod(PaymentMethod p) {
		paymentRepository.delete(p);
	}

	public void removeSale(Sale sale) {
		saleRepo.delete(sale);
		
	}
	
	

}

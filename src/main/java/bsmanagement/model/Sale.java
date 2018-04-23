package bsmanagement.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * <h1> Sale </h1>
 * <p>
 * Sale is the abstract base class for all sales registered in a shop
 * context. This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier
 * <li>Date - Date and Time of Sale
 * <li>Customer - An instance of Customer that contains information about the buyer
 * <li>Product - An instance of Product that contains information about product sold
 * <li>Amount - Value of Product Sold at the time of Sale
 * </ul>
 * <p>
 * An instance of Product is needed to construct a Sale but it can be constructed without any associated customer, like an anonymous's sale.
 * <p>
 * In certain sales, the value of amount can be different of product's price along time, which means
 * that the product's price today can be different of yesterday, and the value of sale's amount is always the same along time.
 * 
 * @author JOAO GOMES
 *
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sale implements Comparable<Sale>{
	

	@Id
	private int id=idGenerator.incrementAndGet();
	private LocalDateTime date;
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
	private Customer customer;
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
	private Product product;
	private double amount;
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
	private PaymentMethod payment;
	
	private static AtomicInteger idGenerator=new AtomicInteger();

	

	
	
	/**
	 * Constructor of Sale with Date, Customer and Product
	 * 
	 * @param date - DateTime of sale
	 * @param customer - Customer that bought the product
	 * @param product - Product sold
	 * @param payment - type of payment
	 */
	public Sale(LocalDateTime date, Customer customer, Product product, PaymentMethod payment) {
		this.date = date;
		this.customer = customer;
		this.product = product;
		this.amount = product.getPrice();
		this.payment = payment;
	}
	
	
	/**
	 * Constructor of Sale with Date and Product
	 * 
	 * @param date - DateTime of sale
	 * @param product - Product sold
	 */
	public Sale(LocalDateTime date, Product product, PaymentMethod payment) {
		this.date = date;
		this.product = product;
		this.customer = null;
		this.amount = product.getPrice();
		this.payment = payment;
	}
	
	protected Sale()
	{
		
	}

	/**
	 * @return the amount of sale
	 */
	public double getAmount() {
		return amount;
	}


	/**
	 * Set amount of sale
	 * 
	 * @param amount 
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}


	/**
	 * @return the sale's ID
	 */
	public int getId() {
		return id;
	}
	
	
	
	/**
	 * @return the payment method
	 */
	public PaymentMethod getPayment() {
		return payment;
	}


	/**
	 * Set the payment method
	 * 
	 * @param payment the payment to set
	 * 
	 * @return Sale 
	 */
	public Sale payedBy(PaymentMethod payment) {
		this.payment = payment;
		
		return this;
	}


	/**
	 * @return sale's date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @return the customer that bought the product
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * @return the product sold in this sale
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * Set sale's ID
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Set new DateTime to sale
	 * 
	 * @param date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	/**
	 * Set Customer to Sale
	 * 
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	/**
	 * Set Product to Sale
	 * 
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * Calculate Fee Amount Value
	 * 
	 * @return Fee amount value in EUROS (double value)
	 */
	public double calculateFeeValue() {
		double feeValue = this.payment.getFee()*this.getAmount()/100;
		if (feeValue < this.payment.getMinFeeValue())
			return this.payment.getMinFeeValue();
		return feeValue;
	}


	/**
	 * @return hashCode of Sale
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * @param Sale
	 * 
	 * @return true if the sales are the same, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (id != other.id)
			return false;
		return true;
	}


	/**
	 * @return String with sale attributes information
	 */
	@Override
	public String toString() {
		return "Sale [id=" + id + ", date=" + date + ", customer=" + customer + ", product=" + product + ", amount="
				+ amount + "]";
	}

	
	/**
	 * Method that compares to another sale his dates
	 * 
	 * @return 0 if they have the same date, 1 if this date is older and -1 if is newest
	 */
	@Override
	public int compareTo(Sale sale)
	{
		if (this.date.isAfter(sale.getDate()))
			return -1;
		if (this.date.isBefore(sale.getDate()))
			return 1;
		return 0;
	}


	public static void setStartIdGenerator(int i) {
		idGenerator.set(i-1);
		
	}
	
	
	
	
}
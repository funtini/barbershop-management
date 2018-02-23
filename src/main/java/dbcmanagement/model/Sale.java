package dbcmanagement.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

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
public class Sale {
	
	private static AtomicInteger idGenerator=new AtomicInteger();
	
	private int id;
	private LocalDateTime date;
	private Customer customer;
	private Product product;
	private double amount;
	

	/**
	 * Constructor of Sale with Date, Customer and Product
	 * 
	 * @param date - DateTime of sale
	 * @param customer - Customer that bought the product
	 * @param product - Product sold
	 */
	public Sale(LocalDateTime date, Customer customer, Product product) {
		id=idGenerator.incrementAndGet();
		this.date = date;
		this.customer = customer;
		this.product = product;
		this.amount = product.getPrice();
	}
	
	
	/**
	 * Constructor of Sale with Date and Product
	 * 
	 * @param date - DateTime of sale
	 * @param product - Product sold
	 */
	public Sale(LocalDateTime date, Product product) {
		id=idGenerator.incrementAndGet();
		this.date = date;
		this.product = product;
		this.customer = null;
		this.amount = product.getPrice();
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
		Sale other = (Sale) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Sale [id=" + id + ", date=" + date + ", customer=" + customer + ", product=" + product + ", amount="
				+ amount + "]";
	}


	
	
	
	
}

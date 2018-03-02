package dbcmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1> CustomerRegistry </h1>
 * <p>
 * CustomerRegistry is the abstract class to manage a list of Customers in a business
 * context. This class contains information like:
 * <ul>
 * <li>listOfCustomers - List of Costumers Registered in database
 * </ul>
 * <p>
 * To create an instance of CustomerRegistry you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
public class CustomerRegistry {
	
private List<Customer> listOfCustomers;
	
	/**
	 * Constructor of list of customers
	 */
	public CustomerRegistry() {
		listOfCustomers = new ArrayList<Customer>();
	}

	/**
	 * @return the listOfCustomers
	 */
	public List<Customer> getCustomerList() {
		return listOfCustomers;
	}

	/**
	 * Set CustomerRegistry method
	 * 
	 * @param listOfCustomers
	 */
	public void setCustomerList(List<Customer> listOfCustomers) {
		this.listOfCustomers = listOfCustomers;
	}
	
	/**
	 * Add Customer to CustomerRegistry
	 * 
	 * @return true if customer was successfully added, false if customer is already on list
	 */
	public boolean addCustomer(Customer customer) {
		if (this.listOfCustomers.contains(customer))
			return false;
		return this.listOfCustomers.add(customer);
	}
	
	/**
	 * Create an Instance of Customer
	 * 
	 * @param name - Name of Customer
	 * @param birth - Birthdate of Customer
	 * @param address - Address or location of customer 
	 * @param phone - Phone number of Customer 
	 *  
	 * @return The Instance of Customer Created
	 */
	public Customer createCustomer(String name, LocalDate birth, String address, String phone) {
		Customer c = new Customer(name,birth,address,phone);
		return c;
	}
	
	/**
	 * Create an Instance of Customer
	 * 
	 * @param name
	 * 
	 * @return The Instance of Customer Created
	 */
	public Customer createCustomer(String name) {
		Customer c = new Customer(name);
		return c;
	}


}

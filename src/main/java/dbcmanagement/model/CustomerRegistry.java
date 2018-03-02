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
	public List<Customer> getCustomerRegistry() {
		return listOfCustomers;
	}

	/**
	 * Set CustomerRegistry method
	 * 
	 * @param listOfCustomers
	 */
	public void setCustomerRegistry(List<Customer> listOfCustomers) {
		this.listOfCustomers = listOfCustomers;
	}
	
	/**
	 * Add Customer to CustomerRegistry
	 * 
	 * @param listOfCustomers
	 */
	public boolean addCustomer(Customer customer) {
		return true;
	}
	
	/**
	 * Create an Instance of Customer
	 * 
	 * @param listOfCustomers
	 */
	public Customer createCustomer(String name, LocalDate birth, String address, String phone) {
		return null;
	}
	
	/**
	 * Create an Instance of Customer
	 * 
	 * @param listOfCustomers
	 */
	public Customer createCustomer(String name) {
		return null;
	}


}

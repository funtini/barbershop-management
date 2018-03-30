package bsmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.jparepositories.CustomerRepository;

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
@Service
public class CustomerService {

@Autowired
private CustomerRepository customersRepo;
	
	/**
	 * Constructor of list of customers
	 */
	public CustomerService() {
		
	}

	/**
	 * @return the listOfCustomers
	 */
	public List<Customer> getCustomers() {
		List<Customer> customersList = new ArrayList<>();
		for (Customer c : customersRepo.findAll())
			customersList.add(c);
		return customersList;
	}
	
	/**
	 * Add Customer to CustomerRegistry
	 * 
	 * @return true if customer was successfully added, false if customer is already on list
	 */
	public boolean addCustomer(Customer customer) {
		if (customersRepo.exists(customer.getId()))
			return false;
		customersRepo.save(customer);
		return true;
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

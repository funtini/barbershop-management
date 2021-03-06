package bsmanagement.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

import bsmanagement.dto.rest.CustomerRestDTO;

/**
 *
 * <h1> Customer </h1>
 * <p>
 * Customer is a base class for a client registered in a shop
 * context. This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier
 * <li>Name - Name of Customer
 * <li>Birth date - Date of his Birth
 * <li>Address/Location - Name of Costumer's Address or Location 
 * <li>Phone Number - Number of Costumer's Phone
 * </ul>
 * 
 * @author JOAO GOMES
 *
 */
@Entity
//@Proxy(lazy = false)
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private LocalDate birth;
	private String address;
	private String phone;
	
	private static AtomicInteger idGenerator=new AtomicInteger();
	
	
	
	/**
	 * Constructor of a customer with name, birth, address/location and phone number
	 * 
	 * @param name - Name of Customer
	 * 
	 * @param birth - Birthdate of Customer
	 * 
	 * @param address - Address or location of customer
	 * 
	 * @param phone - Phone number of Customer 
	 */
	public Customer(String name, LocalDate birth, String address, String phone) {

		this.name = name;
		this.birth = birth;
		this.address = address;
		this.phone = phone;
		this.email = "";
	}
	
	
	/**
	 * Constructor of a customer with name, birth, address/location and phone number
	 * 
	 * @param name - Name of Customer
	 * 
	 * @param birth - Birthdate of Customer
	 * 
	 * @param address - Address or location of customer
	 * 
	 * @param phone - Phone number of Customer 
	 * 
	 * @param email = email address of Customer
	 */
	public Customer(String name, LocalDate birth, String address, String phone, String email) {

		this.name = name;
		this.birth = birth;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}


	/**
	 * Constructor of a customer with name
	 * 
	 * @param name
	 */
	public Customer(String name) {
		
		this.name = name;
		this.email = "";
		this.phone = "";
	}

	protected Customer() {

	}
	

	/**
	 * @return the id of Customer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set Customer's Id
	 * 
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return name of Customer
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set new name to Customer
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return birthdate of Customer
	 */
	public LocalDate getBirth() {
		return birth;
	}

	/**
	 * Set new birthdate to a customer
	 * 
	 * @param birth
	 */
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	/**
	 * 
	 * @return address of Customer
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set new address to Customer
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set new phone number to Customer
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
		
	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set new email address to Customer
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Method to convert customer in a RestDTO
	 * 
	 * @return CustomerRestDTO
	 */
	public CustomerRestDTO toRestDTO() {
		CustomerRestDTO customer = new CustomerRestDTO();
		customer.setAddress(this.address);
		customer.setBirth(this.birth);
		customer.setBookingId(this.id);
		customer.setEmail(this.email);
		customer.setName(this.name);
		customer.setPhone(this.phone);
		return customer;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}

	public static void setStartIdGenerator(int i) {
		idGenerator.set(i-1);
	}


	public static int getAndIncrementId() {
		return idGenerator.incrementAndGet();
	}


	

}



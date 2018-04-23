package bsmanagement.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * <h1> Customer </h1>
 * <p>
 * Customer is the abstract base class for a client registered in a shop
 * context. This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier
 * <li>Name - Name of Customer
 * <li>Birth date - Date of his Birth
 * <li>Address/Location - Name of Costumer's Address or Location 
 * <li>Phone Number - Number of Costumer's Phone
 * </ul>
 * <p>
 * Only name of customer is absolutely necessary to construct an instance of Customer.
 * <p>
 * 
 * @author JOAO GOMES
 *
 */
@Entity
public class Customer {
	
	@Id
	private int id=idGenerator.incrementAndGet();
	private String name;
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
	}


	/**
	 * Constructor of a customer with name
	 * 
	 * @param name
	 */
	public Customer(String name) {
		
		this.name = name;
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

}



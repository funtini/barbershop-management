package bsmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import system.dto.UserLoginDTO;

/**
 * <h1> User </h1>
 * <p>
 * User is the abstract base class for an user registered in business application.
 * This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier (int)
 * <li>Name - String
 * <li>BirthDate - LocalDate
 * <li>Phone - String
 * <li>Tax Payer Id - String
 * <li>Password - String
 * <li>AddressList - List of Addresses
 * <li>profile - userProfile.
 * <li>Activation Status - userProfile
 * </ul>
 * <p>
 * To create an instance of CustomerRegistry you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
public class User {
	
	public enum UserProfile {
		ADMINISTRATOR, EMPLPOYER
	}
	
	private static AtomicInteger idGenerator=new AtomicInteger();
	
	private int id;
	private String name;
	private LocalDate birth;
	private String email;
	private String phone;
	private String taxPayerId;
	private List<Address> addressList;
	private String password;
	private boolean activationStatus;
	private UserProfile profile;
	
	
	
	
	/**
	 * Constructor of User
	 * 
	 * @param id
	 * @param name
	 * @param birth
	 * @param email
	 * @param phone
	 * @param taxPayerId
	 * @param addressList
	 * @param password
	 * @param activationStatus
	 * @param profile
	 */
	public User(String name, LocalDate birth, String email, String phone, String taxPayerId) {
		this.id=idGenerator.incrementAndGet();
		this.name = name;
		this.birth = birth;
		//TODO: email.getEmailAddress().validate();
		this.email = email;
		this.phone = phone;
		this.taxPayerId = taxPayerId;
		this.addressList = new ArrayList<>();
		this.activationStatus = true;
		this.profile = UserProfile.EMPLPOYER;
	}
	
	
	/**
	 * Default Constructor of User for JPA implementation
	 * 
	 */
	protected User ()
	{
		this.addressList = new ArrayList<>();
		this.profile = UserProfile.EMPLPOYER;
	}
	
	
	/**
	 * Check if address parameters are correct. (if email has an '@' or name is not
	 * null, e.g.).
	 *
	 * @return boolean (true if address is correct, false if it isn't).
	 */
	public boolean isValid() {
		if (getAddressList().isEmpty())
			return false;
		if (!(addressList.get(0).isValid()))
			return false;
		//TODO: if email.isValid(): return false;
		return !(name == null || name.isEmpty() || phone == null || phone.isEmpty());
	}
	
	/**
	 * Create a new address
	 *
	 * @param addressDescription
	 *            Description of address like 'home', 'work', 'vacation address', etc
	 * @param street
	 *            Street of the address.
	 * @param postalCode
	 *            Postal code number of the address.
	 * @param city
	 *            City of the address.
	 * @param country
	 *            Country of the address.
	 * @return
	 */
	public Address createAddress(String addressDescription, String street, String postalCode, String city, String country) {

		return new Address(addressDescription, street, postalCode, city, country);
	}


	/**
	 * Add address to user's adressList.
	 *
	 * @param address
	 * @return boolean (true if address is added, false if it isn't).
	 */
	public boolean addAddress(Address address) {

		if (!address.isValid())
			return false;
		for (Address ad : addressList)
			if (ad.equals(address))
				return false;
		return addressList.add(address);
	}
	
	/**
	 * Remove user's address from Address list.
	 *
	 * @param address
	 *            user's address.
	 * @return boolean (true if it is, false if it isn't).
	 */
	public boolean removeAddress(Address address) {

		return addressList.remove(address);
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
	 * Method to get Id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * Method to get name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Method to get birthdate
	 * 
	 * @return the birth
	 */
	public LocalDate getBirthDate() {
		return birth;
	}


	/**
	 * Method to get email address
	 * 
	 * @return the email address
	 */
	public String getEmailAddress() {
		return email;
	}


	/**
	 * Method to get phone number
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * Method to get tax payer id
	 * 
	 * @return the taxPayerId
	 */
	public String getTaxPayerId() {
		return taxPayerId;
	}


	/**
	 * Method to get addressList
	 * 
	 * @return the addressList
	 */
	public List<Address> getAddressList() {
		return addressList;
	}

	/**
	 * Method to return the user status
	 * 
	 * @return the isActive
	 */
	public boolean isActive() {
		return activationStatus;
	}


	/**
	 * Method to get user's profile
	 * 
	 * @return the profile
	 */
	public UserProfile getProfile() {
		return profile;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @param birth the birth to set
	 */
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @param taxPayerId the taxPayerId to set
	 */
	public void setTaxPayerId(String taxPayerId) {
		this.taxPayerId = taxPayerId;
	}

	/**
	 * Method to set user's password
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Method to activate user
	 */
	public void setActive() {
		this.activationStatus = true;
	}
	
	/**
	 * Method to deactivate user
	 */
	public void setInactive() {
		this.activationStatus = false;
	}


	/**
	 * Method to set profile employer
	 * 
	 * @param profile 
	 */
	public void setProfileEmployer() {
		this.profile = UserProfile.EMPLPOYER;
	}
	
	/**
	 * Method to set profile Administrator
	 * 
	 * @param profile 
	 */
	public void setProfileAdmin() {
		this.profile = UserProfile.ADMINISTRATOR;
	}

	/**
	 * Checks if user password is equal to the given parameter.
	 *
	 * @param password
	 *            Parameter to compare user password to.
	 * @return String containing user name and user profile, if successful, or
	 *         error message otherwise.
	 */
	public UserLoginDTO validatePassword(String password) {

		if (this.password.equals(password)) {
			return new UserLoginDTO(name, email, profile.toString(),
					"\n" + profile.toString() + " " + name + " Successfully Logged\n");
		}
		return new UserLoginDTO("Invalid Email or Password\n");
	}


	/**
	 * Outputs this Address as a String.
	 * 
	 * The output will be in the format Address [id]-[name, birth, email, phone, taxPayerId, ActivationStatus, profile]
	 * 
	 * @return a string representation of this object.
	 */
	@Override
	public String toString() {
		return "User [" + id + "]-[name: " + name + ", birth: " + birth + ", email: " + email + ", phone: " + phone
				+ ", taxPayerId: " + taxPayerId + ", ActivationStatus: " + activationStatus + ", profile: " + profile + "]";
	}



	/** 
	 * get class hash code for this User
	 * 
	 * @return a hash code value for this object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	/** 
	 * Compares this User Instance to the specified object. 
	 * The result is true if and only if the argument is not null and is an User object that contains the same long ID value as this User.
	 * 
	 * @param object to compare with.
	 * 
	 * @return true if the Users are the same; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

	
	
	
}

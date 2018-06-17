package bsmanagement.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.Email;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import bsmanagement.dto.rest.UserRestDTO;
import bsmanagement.model.roles.Role;
import bsmanagement.model.roles.RoleName;

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
@Entity
@Proxy(lazy = false)
public class User{
	
	
		
	@Id
	@Email
	private String email;
	private String name;
	private LocalDate birth;
	private String phone;
	private String taxPayerId;
	@Embedded
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Address> addressList;
	private String password;
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean activationStatus;
	@Embedded
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	List<Contract> contracts;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();
	private String username;
	
	/**
	 * Method to get last contract of this User
	 * 
	 * @return Contract if this user has at least one contract, NULL if has no contracts
	 */
	public Contract getLastContract()
	{
		if (contracts.isEmpty())
			return null;
		return contracts.get(contracts.size()-1);
	}
	
	/**
	 * Method to check if user has a open contract
	 * 
	 * @return true if user has an open contract, false if has all contracts closed
	 */
	public boolean hasOpenContract()
	{
		if (contracts.isEmpty())
			return false;
		return getLastContract().isOpen();
	}
	
//	/**
//	 * Method to add a new contract to user. If 
//	 * 
//	 * @param baseSalary
//	 * @param salesComission
//	 * 
//	 * @return true if new contract was sucessfully added, false otherwise
//	 */
//	public Contract createContract(double baseSalary, double salesComission)
//	{
//		return new Contract(baseSalary,salesComission);
//	}
//	
	/**
	 * Method to add a new contract to user. If 
	 * 
	 * @param baseSalary
	 * @param salesComission
	 * 
	 * @return true if new contract was sucessfully added, false otherwise
	 */
	public boolean addContract(Contract contract)
	{
		if (!hasOpenContract())
		{
			contracts.add(contract);
			return true;
		}
		return false;	
	}
	
	/**
	 * Method to close current contract
	 * 
	 * @return false if user has all contracts already closed, true if contract was sucessfully closed
	 */
	public boolean closeContract()
	{
		if (contracts.isEmpty())
			return false;
		return getLastContract().close();
	}
	
	/**
	 * Method to return all contracts of user
	 * 
	 * @return List of Contracts
	 */
	public List<Contract> getAllContracts()
	{
		return contracts;
	}
	
	/**
	 * Method to return current sales comission in contract
	 * 
	 * @return double [if user doesn't has contract, the value returned is 0.0]
	 */
	public double getCurrentSalesComission()
	{
		if (getLastContract()!=null && getLastContract().isOpen())
			return getLastContract().getSalesComission();
		return 0.0;
	}
	
	/**
	 * Method to return sales comission in contract of specific yearMonth
	 * 
	 * @return double [if user doesn't has contract in this yearMonth, the value returned is 0.0]
	 */
	public double getSalesComissionOfMonth(YearMonth yearMonth)
	{
		for (Contract c: this.contracts)
		{
			YearMonth startDate = YearMonth.from(c.getStartDate());
			YearMonth endDate;
			if (!c.isOpen())
			{
				endDate = YearMonth.from(c.getCloseDate());
				if (!yearMonth.isBefore(startDate) && !yearMonth.isAfter(endDate))
					return c.getSalesComission();
			}
			else
			{
				if (!yearMonth.isBefore(startDate))
					return c.getSalesComission();
			}
		}
		
		return 0.0;
	}
	
	
	/**
	 * Constructor of User
	 * 
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
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.taxPayerId = taxPayerId;
		this.addressList = new ArrayList<>();
		this.activationStatus = true;
		this.contracts = new ArrayList<>();
		this.username = email;
	}
	
	/**
	 * Constructor of User
	 * 
	 * @param name
	 * @param birth
	 * @param email
	 * @param phone
	 * @param taxPayerId
	 * @param password
	 */
	public User(String name, LocalDate birth, String email, String phone, String taxPayerId, String password) {
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.taxPayerId = taxPayerId;
		this.addressList = new ArrayList<>();
		this.password = password;
		this.activationStatus = true;
		this.contracts = new ArrayList<>();
		this.username = email;
	}
	
	
	/**
	 * Default Constructor of User for JPA implementation
	 * 
	 */
	protected User ()
	{
		this.addressList = new ArrayList<>();
		this.contracts = new ArrayList<>();
	}
	
	
	/**
	 * Check if address parameters are correct. (if email has an '@' or name is not
	 * null, e.g.).
	 *
	 * @return boolean (true if address is correct, false if it isn't).
	 */
	public boolean isValid() {
		if(!hasValidEmail())
			return false;
		return !(name == null || name.isEmpty() || phone == null || phone.isEmpty());
	}
	
	
	/**
	 * Method to validate emailaddress
	 *
	 * @param email
	 * 
	 * @return true if is valid, false if dont
	 */
	public boolean hasValidEmail() {
	    boolean stricterFilter = true; 
	    String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	    String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
	    String emailRegex = stricterFilter ? stricterFilterString : laxString;
	    Pattern p = Pattern.compile(emailRegex);
	    Matcher m = p.matcher(this.email);
	    return m.matches();
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


//	/**
//	 * Method to get user's profile
//	 * 
//	 * @return the profile
//	 */
//	public UserProfile getProfile() {
//		return profile;
//	}

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
	public void deactivate() {
		this.activationStatus = false;
	}


	/**
	 * Method to set profile Employer
	 * 
	 * @param profile 
	 */
	public void setProfileEmployer() {
		Role collaborator = new Role(RoleName.ROLE_USER);
        roles.add(collaborator);
	}
	
	/**
	 * Method to set profile Store Manager
	 * 
	 * @param profile 
	 */
	public void setProfileStoreManager() {
		Role collaborator = new Role(RoleName.ROLE_STOREMANAGER);
        roles.add(collaborator);
	}
	
	/**
	 * Method to set profile Administrator
	 * 
	 * @param profile 
	 */
	public void setProfileAdmin() {
		Role collaborator = new Role(RoleName.ROLE_ADMINISTRATOR);
        roles.add(collaborator);
	}
	
	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    /**
     * Method to check if user has role registered user.
     *
     * @return true if he has, false otherwise.
     */
    public boolean hasRoleStoreManager() {
        for (Role r : roles) {
            if (r.isStoreManager()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if user has role administrator user.
     *
     * @return true if he has, false otherwise.
     */
    public boolean hasRoleAdministrator() {
        for (Role r : roles) {
            if (r.isAdministrator()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if user has role collaborator user.
     *
     * @return true if he has, false otherwise.
     */
    public boolean hasRoleUser() {
        for (Role r : roles) {
            if (r.isUser()) {
                return true;
            }
        }
        return false;
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
		return "User [" + email + "]-[name: " + name + ", birth: " + birth + ", phone: " + phone
				+ ", taxPayerId: " + taxPayerId + ", ActivationStatus: " + activationStatus + ", profile: " + roles + "]";
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public UserRestDTO toDTO() {
		UserRestDTO userDTO = new UserRestDTO();
		userDTO.setName(this.name);
		userDTO.setBirth(this.birth);
		userDTO.setEmail(this.email);
		userDTO.setPhone(this.phone);
		userDTO.setProfile(this.roles.toString());
		userDTO.setTaxPayerId(this.taxPayerId);
		userDTO.setActivationStatus(this.activationStatus);		
		
		return userDTO;
	}

	public String getPassword() {
		return this.password;
	}


	public String getUsername() {
        return username;
    }

	
	
	
}

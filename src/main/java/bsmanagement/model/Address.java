package bsmanagement.model;

import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Embeddable;

import bsmanagement.dto.rest.AddressRestDTO;

/**
 * 
 * <h1> Address </h1>
 * <p>
 * Address is a class for manage address's information.
 * This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier
 * <li>Street - Street description
 * <li>AddressDescription - Address description
 * <li>City - City
 * <li>Country - Country
 * <li>Postal Code - Postal Code
 * </ul>
 * <p>
 * 
 * @author JOAO GOMES
 *
 */
@Embeddable
public class Address {
	
	private static AtomicInteger idGenerator=new AtomicInteger();

	private int id=idGenerator.incrementAndGet();
	private String addressDescription;
	private String street;
	private String city;
	private String country;
	private String postalCode;


	
	/**
	 * Class constructor.
	 *
	 * @param id
	 *            Unique ID for address.
	 * @param street
	 *            Street of the address.
	 * @param postalCode
	 *            Postal code number of the address.
	 * @param city
	 *            City of the address.
	 * @param country
	 *            Country of the address.
	 */
	public Address(String addressDescription, String street, String postalCode, String city, String country) {
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
		this.addressDescription = addressDescription;

	}
	
	
	/**
	 * Default constructor for jpa implementation
	 *
	 */
	protected Address() {
		
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
	 * Static method to set new address description
	 * 
	 * @param address description
	 */
	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}
	
	/**
	 * Get City name.
	 *
	 * @return String City.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Get Country.
	 *
	 * @return String country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Get ID for address.
	 *
	 * @return String with unique address ID.
	 */
	public String getAddressDescription() {
		return addressDescription;
	}

	/**
	 * Get Postal Code.
	 *
	 * @return String Postal Code.
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Get Street.
	 *
	 * @return String street.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Validates address - Requires valid city, street, postalCode and addressDescription.
	 *
	 * @return True if address is valid, false otherwise.
	 */
	public boolean isValid() {

		if (city == null || city.isEmpty() || postalCode == null || postalCode.isEmpty())
			return false;

		return !(street == null || street.isEmpty() || addressDescription == null || addressDescription.isEmpty());
	}

	/**
	 * Set City name.
	 *
	 * @param city
	 *            name of the city.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Set name of country.
	 *
	 * @param country
	 *            String country.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Set ID for address.
	 *
	 * @param id
	 *            Long represents unique ID.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return unique ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set Postal Code.
	 *
	 * @param postalCode
	 *            String represents Postal Code.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Set Street.
	 *
	 * @param street
	 *            String represents street.
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * Method to convert address in Rest DTO
	 * 
	 * @return Address Rest DTO
	 */
	public AddressRestDTO toRestDTO()
	{
		AddressRestDTO address = new AddressRestDTO();
		address.setAddressDescription(addressDescription);
		address.setCity(city);
		address.setCountry(country);
		address.setId(id);
		address.setPostalCode(postalCode);
		address.setStreet(street);
		
		return address;
	}


	/**
	 * Outputs this Address as a String.
	 * 
	 * The output will be in the format Address [id]-[addressDescription, street, city, country, postalCode]
	 * 
	 * @return a string representation of this object.
	 */
	@Override
	public String toString() {
		return "Address ["+ id + "]-[addressDescription: "+ addressDescription + ", street: " + street + ", city: "
				+ city + ", country: " + country + ", postalCode: " + postalCode + "]"; 
		
	}


	/** 
	 * get class hash code for this Address
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
	 * Compares this Address Instance to the specified object. 
	 * The result is true if and only if the argument is not null and is an Address object that contains the same long ID value as this Address.
	 * 
	 * @param object to compare with.
	 * 
	 * @return true if the Addresses are the same; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}

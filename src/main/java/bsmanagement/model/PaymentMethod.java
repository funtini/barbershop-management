package bsmanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <h1> PaymentMethod </h1>
 * <p>
 * PaymentMethod is a class for all kind of payments in a business
 * context. This class contains information like:
 * <ul>
 * <li>Name - Type of Payment
 * <li>Fee - Payment's Fee on each purchase (%)
 * <li>MinFeeValue - Minimum Fee's Value for each purchase (€)
 * </ul>
 * <p>
 * To create an instance of Payment you have to define a Type and Fee's value
 * 
 * @author JOAO GOMES
 */
@Entity
public class PaymentMethod {
	
	@Id
	private String name;
	private double fee;
	private double minFeeValue;
	
	
	/**
	 * Payment constructor - If payment has no Fee's put 0 values on field of Fee's
	 * 
	 * @param type
	 * @param fee
	 * @param minFeeValue
	 */
	public PaymentMethod(String type, double fee, double minFeeValue) {
		this.name = type;
		this.fee = fee;
		this.minFeeValue = minFeeValue;
	}
	
	protected PaymentMethod()
	{
		
	}
	
	/**
	 * @return the type name of Payment
	 */
	public String getType() {
		return name;
	}
	
	/**
	 * @return the Payment's fee
	 */
	public double getFee() {
		return fee;
	}
	
	/**
	 * @return the minimum Value of Fee
	 */
	public double getMinFeeValue() {
		return minFeeValue;
	}
	
	
	/**
	 * Method to set new type name
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.name = type;
	}
	
	/**
	 * Method to set new Fee
	 * 
	 * @param fee
	 */
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	/**
	 * Method to set new minimum fee's value
	 * 
	 * @param minFeeValue
	 */
	public void setMinFeeValue(double minFeeValue) {
		this.minFeeValue = minFeeValue;
	}
	
	
	/**
	 * Method to check if paymentMethod is valid
	 * 
	 * @param True if payment method is valid, false otherwise
	 */
	public boolean isValid() {
		if (this.name == null || this.name.isEmpty())
			return false;
		return true;
	}
	
	/** 
	 * Get class hash code for this PaymentMethod
	 * 
	 * @return a hash code value for this object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/** 
	 * Compares this User Instance to the specified object. 
	 * The result is true if and only if the argument is not null and is an User object that contains the same ID value as this PaymentMethod.
	 * 
	 * @param object to compare with.
	 * 
	 * @return true if the PaymentMethod are the same; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PaymentMethod))
			return false;
		PaymentMethod other = (PaymentMethod) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Outputs this PaymentType as a String.
	 * 
	 * The output will be in the format PaymentMethod [id]-[name, fee, minFeeValue]
	 * 
	 * @return a string representation of this object.
	 */
	@Override
	public String toString() {
		return "PaymentMethod [name=" + name + ", fee=" + fee + ", minFeeValue=" + minFeeValue + "]";
	}
	
	

}

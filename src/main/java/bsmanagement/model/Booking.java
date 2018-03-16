package bsmanagement.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * <h1> Booking </h1>
 * <p>
 * Booking is the abstract base class for all bookings in a barber shop
 * context. This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier
 * <li>Date - Date and Time of Booking
 * <li>Customer - An instance of Customer that made the booking
 * </ul>
 * <p>
 * <p>
 * @author JOAO GOMES
 *
 */
public class Booking {
	
	private static AtomicInteger idGenerator=new AtomicInteger();
	
	private int id;
	private LocalDateTime date;
	private Customer customer;
	
	
	/**
	 * Constructor of a new booking with date, time and customer
	 * 
	 * @param date - Date of Booking
	 * 
	 * @param time - Time of booking
	 * 
	 * @param customer - Instance of Customer
	 * 
	 */
	public Booking(LocalDateTime date, Customer customer) {
		
		id=idGenerator.incrementAndGet();
		this.date = date;
		this.customer = customer;
		
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
	 * Set booking's Id
	 */
	public void setId(int id) {
		this.id=id;
	}
	
	/**
	 * @return booking's Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return booking's date
	 */
	public LocalDateTime getDate() {
		return date.truncatedTo(ChronoUnit.MINUTES);
	}
	
	/**
	 * Set new date for booking
	 * 
	 * @param date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	/**
	 * 
	 * @return booking's customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Replace customer in this booking
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "Booking [id=" + id + ", date=" + date.toLocalDate() + ", time=" + date.toLocalTime().truncatedTo(ChronoUnit.MINUTES) + ", customer=" + customer + "]";
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
		if (!(obj instanceof Booking))
			return false;
		Booking other = (Booking) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
	
}

package dbcmanagement.model;

import java.time.LocalDate;
import java.time.LocalTime;
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
	private LocalDate date;
	private LocalTime time;
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
	public Booking(LocalDate date, LocalTime time, Customer customer) {
		
		id=idGenerator.incrementAndGet();
		this.date = date;
		this.time = time;
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
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Set new date for booking
	 * 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * 
	 * @return time of booking
	 */
	public LocalTime getTime() {
		return time;
	}
	
	/**
	 * Set new time for booking
	 * 
	 * @param time
	 */
	public void setTime(LocalTime time) {
		this.time = time;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Booking other = (Booking) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Booking [id=" + id + ", date=" + date + ", time=" + time + ", customer=" + customer + "]";
	}


	
	
}

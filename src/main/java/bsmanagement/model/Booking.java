package bsmanagement.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;
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
@Entity
@Proxy(lazy = false)
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime date;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private static AtomicInteger idGenerator=new AtomicInteger();
	
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
		
		this.date = date;
		this.customer = customer;
		
	}
	
	protected Booking() {

	}
	
	public static int getAndIncrementId()
	{
		return idGenerator.incrementAndGet();
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

	public static void setStartIdGenerator(int i) {
		idGenerator.set(i-1);
	}


	
	
}

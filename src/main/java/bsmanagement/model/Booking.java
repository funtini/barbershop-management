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

import bsmanagement.dto.rest.BookingRestDTO;
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
public class Booking implements Comparable<Booking>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime date;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
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
	public Booking(LocalDateTime date, Customer customer, User user) {
		
		this.date = date;
		this.customer = customer;
		this.user = user;
		
	}
	
	protected Booking() {

	}
	
	public static int getAndIncrementId()
	{
		return idGenerator.incrementAndGet();
	}
	
	
	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Set user
	 */
	public void setUser(User user) {
		this.user = user;
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
		if (date == null)
			return null;
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
	
	/**
	 * Method to convert booking in a DTO to use in API REST CONTROLLERS
	 * 
	 * @return BookingRestDTO
	 */
	public BookingRestDTO toRestDTO()
	{
		BookingRestDTO booking = new BookingRestDTO();
		if (this.customer != null)
			booking.setCustomerId(this.customer.getId());
		
		booking.setDate(this.date);
		booking.setBookingId(this.id);
		if (this.user != null)
			booking.setUserId(this.user.getEmailAddress());
	
		return booking;
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

	
	/**
	 * Method that compares dates with another booking
	 * 
	 * @return 0 if they have the same date, 1 if this date is older and -1 if is newest
	 */
	@Override
	public int compareTo(Booking booking) {
		if (this.date.isAfter(booking.getDate()))
			return -1;
		if (this.date.isBefore(booking.getDate()))
			return 1;
		return 0;
	}


	
	
}

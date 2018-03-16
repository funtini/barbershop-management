package bsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1> BookingRegistry </h1>
 * <p>
 * BookingRegistry is the abstract class to manage all Bookings in a barbershop business
 * context. This class contains information like:
 * <ul>
 * <li>listOfBookings - List of all bookings
 * </ul>
 * <p>
 * To create an instance of BookingRegistry you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
public class BookingRegistry {
	
private List<Booking> bookings;
	
	/**
	 * Constructor of BookingRegistry
	 */
	public BookingRegistry() {
		bookings = new ArrayList<>();
	}

	/**
	 * @return the listOfBookings
	 */
	public List<Booking> getBookings() {
		return bookings;
	}

	/**
	 * Set a List of Bookings
	 * 
	 * @param listOfBookings
	 */
	public void setBookings(List<Booking> listOfBookings) {
		this.bookings= listOfBookings;
	}
	
	
	/**
	 * Method to create a new Instance of Booking
	 * 
	 * @param date - Date of Booking
	 * @param time - Time of booking
	 * @param customer - Instance of Customer
	 * 
	 * @return Booking - instance
	 */
	public Booking createBooking(LocalDateTime date, Customer customer) {
		Booking book = new Booking(date,customer);
		return book;
	}
	
	/**
	 * Method to add a new booking
	 * 
	 * @param Booking - Instance of Booking
	 * 
	 * @return boolean - true if booking was successfully added, false otherwise
	 */
	public boolean addBooking(Booking booking) {
		if (booking.getDate().isBefore(LocalDateTime.now()))
			return false;
		return bookings.add(booking);
	}
	
	/**
	 * Method to get all next bookings order by time
	 * 
	 * @return List of bookings - with today's date
	 */
	public List<Booking> getNextBookings() {
		List<Booking> bookingList = new ArrayList<>();
		for (Booking b: this.bookings)
		{
			if (b.getDate().isEqual(LocalDateTime.now()) || b.getDate().isAfter(LocalDateTime.now()))
				bookingList.add(b);
		}
		return bookingList;
	}
	
	/**
	 * Method to get all bookings of a specific day, order by time
	 * 
	 * @return List of bookings
	 */
	public List<Booking> getBookingsOf(LocalDate date) {
		List<Booking> bookingList = new ArrayList<>();
		for (Booking b: this.bookings)
		{
			if (b.getDate().toLocalDate().isEqual(date))
				bookingList.add(b);
		}
		return bookingList;
	}
	
	/**
	 * get next booking of specific customer 
	 * 
	 * @return Booking if customer was found, return null if not
	 */
	public Booking getNextBookingOf(Customer customer) {
		for (Booking b: this.getNextBookings())
		{
			if (b.getCustomer().equals(customer))
				return b;
		}
		return null;
	}
	
	

}

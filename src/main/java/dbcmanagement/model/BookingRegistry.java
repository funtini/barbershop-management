package dbcmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	
private List<Booking> listOfBookings;
	
	/**
	 * Constructor of BookingRegistry
	 */
	public BookingRegistry() {
		listOfBookings = new ArrayList<>();
	}

	/**
	 * @return the listOfBookings
	 */
	public List<Booking> getBookingRegistry() {
		return listOfBookings;
	}

	/**
	 * Set a List of Bookings
	 * 
	 * @param listOfBookings
	 */
	public void setBookingRegistry(List<Booking> listOfBookings) {
		this.listOfBookings= listOfBookings;
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
	public Booking createBooking(LocalDate date, LocalTime time, Customer customer) {
		Booking book = new Booking(date,time,customer);
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
		if (booking.getDate().isBefore(LocalDate.now()))
			return false;
		if (booking.getDate().isEqual(LocalDate.now()) && booking.getTime().isBefore(LocalTime.now()))
			return false;
		return listOfBookings.add(booking);
	}
	
	/**
	 * Method to get all next bookings order by time
	 * 
	 * @return List of bookings - with today's date
	 */
	public List<Booking> getNextBookings() {
		List<Booking> bookingList = new ArrayList<>();
		for (Booking b: this.listOfBookings)
		{
			if (b.getDate().isEqual(LocalDate.now()) || b.getDate().isAfter(LocalDate.now()))
				bookingList.add(b);
		}
		return bookingList;
	}
	
	/**
	 * Method to get all bookings of a specific day, order by time
	 * 
	 * @return List of bookings
	 */
	public List<Booking> getBookingListOfaDay(LocalDate date) {
		List<Booking> bookingList = new ArrayList<>();
		for (Booking b: this.listOfBookings)
		{
			if (b.getDate().isEqual(date))
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

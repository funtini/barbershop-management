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
	 * Method to add a new booking
	 * 
	 * @param date - Date of Booking
	 * @param time - Time of booking
	 * @param customer - Instance of Customer
	 * 
	 * @return boolean - true if booking was sucessfull added, false otherwise
	 */
	public boolean addBooking(LocalDate date, LocalTime time, Customer customer) {
		if (date.isBefore(LocalDate.now()))
			return false;
		if (time.isBefore(LocalTime.now()))
			return false;
		Booking book = new Booking(date,time,customer);
		return listOfBookings.add(book);
	}
	
	/**
	 * Method to get all today's bookings order by time
	 * 
	 * 
	 * @return List of bookings - with today's date
	 */
	public List<Booking> getTodaySchedule() {
		return null;
	}
	
	/**
	 * Method to get all bookings of a specific day, order by time
	 * 
	 * @return List of bookings
	 */
	public List<Booking> getBookingListOfaDay(LocalDateTime date) {
		return null;
	}
	
	/**
	 * get a booking list of specific customer 
	 * 
	 * @return List of bookings
	 */
	public List<Booking> getNextBookingListOf(Customer customer) {
		return null;
	}
	
	

}

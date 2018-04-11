package bsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.dto.rest.BookingRestDTO;
import bsmanagement.model.jparepositories.BookingRepository;
import bsmanagement.model.jparepositories.CustomerRepository;

/**
 * <h1> BookingCustomerService </h1>
 * <p>
 * BookingCustomerService is a service class to manage all Customers and Bookings in a barbershop business
 * context. This class contains information like:
 * <ul>
 * <li>bookRepository - JpaRepository of all bookings
 * <li>customerRepository - JpaRepository of all customers
 * </ul>
 * <p>
 * Both repositories have annotation autowired of springframework, so the constructor of this service is empty and protected
 * 
 * @author JOAO GOMES
 *
 */
@Service
public class BookingCustomerService {
	
	@Autowired
	private BookingRepository bookRepository;
	
	@Autowired
	private CustomerRepository customersRepository;

	protected BookingCustomerService() {
		
	}
	
	/*
	 * -----------------
	 * CUSTOMER SERVICE
	 * -----------------
	 */
	
	
	/**
	 * @return the listOfCustomers
	 */
	public List<Customer> getAllCustomers() {
		List<Customer> customersList = new ArrayList<>();
		for (Customer c : customersRepository.findAll())
			customersList.add(c);
		return customersList;
	}
	
	/**
	 * Add Customer to CustomerRegistry
	 * 
	 * @return true if customer was successfully added, false if customer is already on list
	 */
	public boolean addCustomer(Customer customer) {
		if (customersRepository.existsById(customer.getId()))
			return false;
		customersRepository.save(customer);
		return true;
	}
	
	/**
	 * Create an Instance of Customer
	 * 
	 * @param name - Name of Customer
	 * @param birth - Birthdate of Customer
	 * @param address - Address or location of customer 
	 * @param phone - Phone number of Customer 
	 *  
	 * @return The Instance of Customer Created
	 */
	public Customer createCustomer(String name, LocalDate birth, String address, String phone) {
		Customer c = new Customer(name,birth,address,phone);
		return c;
	}
	
	/**
	 * Create an Instance of Customer
	 * 
	 * @param name
	 * 
	 * @return The Instance of Customer Created
	 */
	public Customer createCustomer(String name) {
		Customer c = new Customer(name);
		return c;
	}
	
	
	/**
	 * Find Customer By ID
	 * 
	 * @param id
	 * 
	 * @return An Instance of Customer if ID exists, null otherwise.
	 */
	public Customer findCustomerById(int id)
	{
		if (customersRepository.existsById(id))
			return customersRepository.getOne(id);
		else
			return null;
	}
	
	
	
	/*
	 * ------------------
	 * BOOKING SERVICE
	 * ------------------
	 */
	
	
	/**
	 * @return the listOfBookings
	 */
	public List<Booking> getBookings() {
		List<Booking> bookings = new ArrayList<>();
		for (Booking b : bookRepository.findAll())
			bookings.add(b);
		return bookings;
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
		bookRepository.save(booking);
		return true;
	}
	
	
	
	
	
	/**
	 * Method to get all next bookings order by time
	 * 
	 * @return List of bookings - with today's date
	 */
	public List<Booking> getNextBookings() {
		List<Booking> bookingList = new ArrayList<>();
		for (Booking b: getBookings())
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
		for (Booking b: getBookings())
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
	
	
	/*
	 * --------------------------
	 * 		MERGED SERVICE
	 * --------------------------
	 */
	
	/**
	 * Method to add a new booking
	 * 
	 * @param BookingDTO - Instance of BookingRestDTO
	 * 
	 * @return boolean - true if booking was successfully added, false otherwise
	 */
	public boolean addBooking(BookingRestDTO bookingDTO) {
		if (bookingDTO.getDate().isBefore(LocalDateTime.now()))
		{
			bookingDTO.setMessage("Booking not added : INVALID DATE");
			return false;
		}
		bookingDTO.setMessage("Booking Sucessfully Added");	
		Customer customer = findCustomerById(bookingDTO.getCustomerId());
		Booking booking = createBooking(bookingDTO.getDate(),customer);
		bookRepository.save(booking);
		return true;
	}

}

package bsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.jparepositories.BookingRepository;
import bsmanagement.model.jparepositories.CustomerRepository;

/**
 * <h1> BookingCustomerService </h1>
 * <p>
 * BookingCustomerService is a super service class to manage all Customers and Bookings in a barbershop business
 * context. This class contains information like:
 * <ul>
 * <li>bookRepository - JpaRepository of all bookings
 * <li>customerRepository - JpaRepository of all customers
 * </ul>
 * <p>
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

	public BookingCustomerService() {
		
	}
	
	
	
	public BookingCustomerService(BookingRepository bookRepository, CustomerRepository customersRepository) {
		this.bookRepository = bookRepository;
		this.customersRepository = customersRepository;
	}



	public void setBookRepository(BookingRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void setCustomersRepository(CustomerRepository customersRepository) {
		this.customersRepository = customersRepository;
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
	
	
	//TODO: Unused constructor on controller
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
	
	//TODO: Unused method on controller
	/**
	 * Find Customer By Name
	 * 
	 * @param name
	 * 
	 * @return An Instance of Customer if ID exists, null otherwise.
	 */
	public Customer findCustomerByName(String name)
	{
		if (customersRepository.existsByName(name))
			return customersRepository.getOneByName(name);
		else
			return null;
	}
	
	//TODO: Unused method on controller
	/**
	 * Find Customer By Email
	 * 
	 * @param name
	 * 
	 * @return An Instance of Customer if ID exists, null otherwise.
	 */
	public Customer findCustomerByEmail(String email)
	{
		if (customersRepository.existsByEmail(email))
			return customersRepository.getOneByEmail(email);
		else
			return null;
	}
	
	/**
	 * Remove Customer
	 * 
	 * @param customerId
	 * 
	 * @return true if customer was successfully removed, false otherwise.
	 */
	public boolean removeCustomer(int customerId)
	{
		Customer c = findCustomerById(customerId);
		if (c!=null)
			{
			customersRepository.delete(c);
			return true;
			}
		return false;
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
		bookings.sort(Collections.reverseOrder());
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
	public Booking createBooking(LocalDateTime date, Customer customer, User user) {
		Booking book = new Booking(date,customer,user);
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
		if (booking.getDate()==null)
			return false;
		if (booking.getDate().isBefore(LocalDateTime.now()))
			return false;
		bookRepository.save(booking);
		return true;
	}
	
	/**
	 * Method to remove Booking by ID
	 * 
	 * @return true, if booking was sucessfully removed, false otherwise
	 */
	public boolean removeBooking(int bookingId) {

		for (Booking b: getBookings())
		{
			if (b.getId()==bookingId)
				{
				bookRepository.delete(b);
				return true;
				}
		}
		return false;
	}
	
	/**
	 * Method to find Booking by ID
	 * 
	 * @return booking if Id exists, null if dont
	 */
	public Booking findBookingById(int id) {

		for (Booking b: getBookings())
		{
			if (b.getId()==id)
				return b;
		}
		return null;
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
		bookingList.sort(Collections.reverseOrder());
		return bookingList;
	}
	
	/**
	 * Method to get all next bookings order by time
	 * 
	 * @return List of bookings - with today's date
	 */
	public List<Booking> getNextBookingsOf(String userId) {
		List<Booking> bookingList = new ArrayList<>();
		for (Booking b: getNextBookings())
		{
			if (b.getUser().getEmailAddress().equals(userId))
				bookingList.add(b);
		}
		bookingList.sort(Collections.reverseOrder());
		return bookingList;
	}
	
	/**
	 * Method to set date on specific booking
	 * 
	 * @param bookingId (integer)
	 * 
	 * @param date (LocalDateTime)
	 * 
	 * @return List of bookings - with today's date
	 */
	public boolean setBookingDate(int bookingId, LocalDateTime date) {
		for (Booking b: getNextBookings())
		{
			if (b.getId()==bookingId)
			{
				b.setDate(date);
				return true;
			}
				
		}
		return false;
	}
	
	
	/**
	 * Method to get all bookings of a specific day, order by time
	 * 
	 * @return List of bookings
	 */
	public List<Booking> getBookingsOfDay(LocalDate date) {
		List<Booking> bookingList = new ArrayList<>();
		for (Booking b: getBookings())
		{
			if (b.getDate().toLocalDate().isEqual(date))
				bookingList.add(b);
		}
		bookingList.sort(Collections.reverseOrder());
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
	 * -----------------------------
	 * 	 UPDATE OBJECTS IN DATABASE
	 * -----------------------------
	 */
	

	public boolean updateBooking(Booking b) {
		if (bookRepository.existsById(b.getId()) && !b.getDate().isBefore(LocalDateTime.now())) {
            bookRepository.save(b);
            return true;
        }
        return false;
		
	}
	

	
	public boolean updateCustomer(Customer c) {
		if (customersRepository.existsById(c.getId())) {
			customersRepository.save(c);
            return true;
        }
        return false;
		
	}

}

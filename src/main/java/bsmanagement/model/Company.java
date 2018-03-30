package bsmanagement.model;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <h1> Company </h1>
 * <p>
 * Company is the main class to manage all business. This class manage abstract subclasses like:
 * <ul>
 * <li>BookingRegistry - manage all Bookings
 * <li>CustomerRegistry - manage all Customers
 * <li>ReportRegistry - manage all financial reports
 * <li>UserRegistry - manage all users of application
 * </ul>
 * <p>
 * Company Class has only one instance (Singleton Pattern), to get the single instance of Company you just need to invoke the static method getInstance()
 * 
 * @author JOAO GOMES
 *
 */
public class Company {
	
	
	private BookingService bookingRegistry;
	private CustomerService customerRegistry;
	private ReportService reportRegistry;
	@Autowired
	private UserService userRegistry;
	
	static Company theInstance;
	
	/**
	 * Constructor for Company. Instantiates UserRegistry and ProjectRegistry.
	 */
	private Company() {
		bookingRegistry = new BookingService();
		customerRegistry = new CustomerService();
		reportRegistry = new ReportService();
		userRegistry = new UserService();
	}
	
	
	/**
	 * Static method that ensure that Company has only one instance.
	 *
	 * @return the single instance of the class Company.
	 */
	public static synchronized Company getTheInstance() {
		if (theInstance == null)
			theInstance = new Company();

		return theInstance;
	}


	/**
	 * Get the Booking Registry
	 * 
	 * @return the bookingRegistry
	 */
	public BookingService getBookingRegistry() {
		return bookingRegistry;
	}


	/**
	 * Get the Customer Registry
	 * 
	 * @return the customerRegistry
	 */
	public CustomerService getCustomerRegistry() {
		return customerRegistry;
	}


	/**
	 * Get the reportRegistry
	 * 
	 * @return the reportRegistry
	 */
	public ReportService getReportRegistry() {
		return reportRegistry;
	}
	
	/**
	 * Get the userRegistry
	 * 
	 * @return the userRegistry
	 */
	public UserService getUserRegistry() {
		return userRegistry;
	}



}

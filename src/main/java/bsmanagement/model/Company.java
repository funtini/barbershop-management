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
	
	
	private BookingCustomerService bookingCustomerService;
	private ReportService reportService;
	@Autowired
	private UserService userService;
	
	static Company theInstance;
	
	/**
	 * Constructor for Company. Instantiates UserRegistry and ProjectRegistry.
	 */
	private Company() {
		bookingCustomerService = new BookingCustomerService();
		reportService = new ReportService();
		userService = new UserService();
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
	 * Get the BookingCustomerService
	 * 
	 * @return the bookingCustomerService
	 */
	public BookingCustomerService getBookingCustomerService() {
		return bookingCustomerService;
	}


	/**
	 * Get the reportRegistry
	 * 
	 * @return the reportRegistry
	 */
	public ReportService getReportService() {
		return reportService;
	}
	
	/**
	 * Get the userRegistry
	 * 
	 * @return the userRegistry
	 */
	public UserService getUserService() {
		return userService;
	}



}

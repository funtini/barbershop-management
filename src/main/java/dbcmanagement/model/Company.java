package dbcmanagement.model;



/**
 * <h1> Company </h1>
 * <p>
 * Company is the main class to manage all business. This class manage abstract subclasses like:
 * <ul>
 * <li>BookingRegistry - manage all Bookings
 * <li>CustomerRegistry - manage all Customers
 * <li>SaleList - manage all sales
 * <li>ExpenseList - manage all expenses
 * <li>ReportRegistry - manage all financial reports
 * </ul>
 * <p>
 * Company Class has only one instance (Singleton Pattern), to get the single instance of Company you just need to invoke the static method getInstance()
 * 
 * @author JOAO GOMES
 *
 */
public class Company {
	
	private BookingRegistry bookingRegistry;
	private CustomerRegistry customerRegistry;
	private ReportRegistry reportRegistry;
	
	static Company theInstance;
	
	/**
	 * Constructor for Company. Instantiates UserRegistry and ProjectRegistry.
	 */
	private Company() {
		bookingRegistry = new BookingRegistry();
		customerRegistry = new CustomerRegistry();
		reportRegistry = new ReportRegistry();
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
	public BookingRegistry getBookingRegistry() {
		return bookingRegistry;
	}


	/**
	 * Get the Customer Registry
	 * 
	 * @return the customerRegistry
	 */
	public CustomerRegistry getCustomerRegistry() {
		return customerRegistry;
	}


	/**
	 * Get the reportRegistry
	 * 
	 * @return the reportRegistry
	 */
	public ReportRegistry getReportRegistry() {
		return reportRegistry;
	}



}

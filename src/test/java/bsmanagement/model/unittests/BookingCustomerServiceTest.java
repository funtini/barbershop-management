package bsmanagement.model.unittests;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import bsmanagement.jparepositories.classtests.BookingRepositoryClass;
import bsmanagement.jparepositories.classtests.CustomerRepositoryClass;
import bsmanagement.model.Booking;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;


/**
 * 
 * Unit tests for BookingCustomer Service Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class BookingCustomerServiceTest {

	
	
	LocalDate birthdate1;
	LocalDate birthdate2;
	Customer c1;
	Customer c2;
	Customer c3;
	
	LocalDateTime dt1;
	LocalDateTime dt2;
	LocalDateTime dt3;
	
	LocalDate bd1;
	LocalDate bd2;
	LocalDate bd3;
	
	
	
	Booking b1;
	Booking b2;
	Booking b3;
	
	BookingCustomerService bcService;
	CustomerRepositoryClass customerRepository;
	BookingRepositoryClass bookingRepository;
	List<Booking> expectBookings;
	List<Booking> resultBookings;
	List<Customer> expect;

	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>BirthDate [bd1] : 30/11/1989 </p>
	 * <p>BirthDate [bd2] : 15/02/1984 </p>
	 * 
	 * <p>Customer [c1] -> ["Joao",'30/11/1989',"Mangualde",914047935] </p>
	 * <p>Customer [c2] -> ["Ana",'15/02/1984',"Porto",966677722] </p>
	 * <p>Customer [c3] -> ["Pedro",'25/05/1992',"Mangualde",932444333] </p>
	 * 
	 * <p>DateTime [dt1] : Today's Date + 10 days</p>
	 * <p>DateTime [dt2] : Today's Date + 30 mins</p>
	 * <p>DateTime [dt3] : Today's Date + 1 month</p>
	 * 
	 * <p>Booking [b1] ->  [dt1,"Joao"] </p>
	 * <p>Booking [b2] ->  [dt2,"Ana"] </p>
	 * <p>Booking [b3] ->  [dt3,"Joao"] </p>
	 * 
	 * 
	 * 
	 * 
	 */
	@Before
	public void setUp(){
		
		bcService = new BookingCustomerService();
		customerRepository = new CustomerRepositoryClass();
		bookingRepository = new BookingRepositoryClass();
		bcService.setBookRepository(bookingRepository);
		bcService.setCustomersRepository(customerRepository);
		expectBookings = new ArrayList<>();
		resultBookings = new ArrayList<>();
		
		
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		
		bd1=LocalDate.of(1989, 11, 30);
		bd2=LocalDate.of(1984, 02, 15);
		bd3=LocalDate.of(1992, 05, 25);
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		c3 = new Customer("Pedro",bd3,"Mangualde","932444333");
		
		dt1 = LocalDateTime.of(LocalDate.now(),LocalTime.of(10, 10)).plusDays(10);
		dt2 = LocalDateTime.now().plusMinutes(30);
		dt3 = LocalDateTime.of(LocalDate.now(),LocalTime.of(10, 10)).plusMonths(1);
		
		b1 = new Booking(dt1,c1);
		b2 = new Booking(dt2,c2);
		b3 = new Booking(dt3,c1);

		expect = new ArrayList<>();
	}

	
	/**
	 * <h2>getBookingRegistry() method test</h2>
	 */
	@Test
	public void testGetBookings() {
		//Given: empty list's
		assertEquals(bcService.getBookings().isEmpty(),true);
	
		//When: add bookings to list
		bcService.addBooking(b1);
		bcService.addBooking(b2);
		bcService.addBooking(b3);
		expectBookings.add(b1);
		expectBookings.add(b2);
		expectBookings.add(b3);
		//Then: get a list with that 3 bookings
		resultBookings = bcService.getBookings();
		assertEquals(resultBookings,expectBookings);
			
	}


	/**
	 * <h2>setBookingRegistry() method test</h2>
	 */
	@Test
	public void testSetBookingRepository() {
		//Given: empty service;
		assertEquals(bcService.getBookings().isEmpty(),true);
		BookingRepositoryClass bookRepoTest = new BookingRepositoryClass();
		bookRepoTest.save(b1);
		bookRepoTest.save(b2);
		bookRepoTest.save(b3);
		
		//When: set new bookingRepo with 3 bookings
		expectBookings.add(b1);
		expectBookings.add(b2);
		expectBookings.add(b3);
		bcService.setBookRepository(bookRepoTest);
		//Then: get a list with that 3 bookings
		resultBookings = bcService.getBookings();
		assertEquals(resultBookings,expectBookings);		
	}


	/**
	 * <h2>createBooking() method test</h2>
	 */
	@Test
	public void testCreateBooking() {
		//Given: empty list's
		assertEquals(bcService.getBookings().isEmpty(),true);
		
		//When: create instances of bookings by bookingRegistry
		Booking result1 = bcService.createBooking(dt1, c1);
		Booking result2 = bcService.createBooking(dt2, c2);
		//Then:
		assertEquals(result1.getDate(),b1.getDate());
		assertEquals(result2.getDate(),b2.getDate());	
	}
	
	/**
	 * <h2>addBooking() method test</h2>
	 * 
	 * True - Valid Cases
	 */
	@Test
	public void testAddBookingTrue() {
		//Given: empty list on bookingRegistry
		assertEquals(bcService.getBookings().isEmpty(),true);
		
		//When: add 3 instances of bookings in bookingRegistry
		assertEquals(bcService.addBooking(b1),true);
		assertEquals(bcService.addBooking(b2),true);
		assertEquals(bcService.addBooking(b3),true);
		expectBookings.add(b1);
		expectBookings.add(b2);
		expectBookings.add(b3);
		resultBookings = bcService.getBookings();
		//Then:
		assertEquals(resultBookings,expectBookings);		
	}
	
	/**
	 * <h2>addBooking() method test</h2>
	 * 
	 * True - Valid Cases
	 */
	@Test
	public void testAddBookingFalseInvalidDate() {
		//Given: empty list on bookingRegistry and bookings out of date
		assertEquals(bcService.getBookings().isEmpty(),true);
		b1.setDate(LocalDateTime.now().minusDays(11));
		b2.setDate(LocalDateTime.now().minusDays(1));
		//When: add 3 instances of bookings 
		assertEquals(bcService.addBooking(b1),false);
		assertEquals(bcService.addBooking(b2),false);
		assertEquals(bcService.addBooking(b3),true);
		expectBookings.add(b3);
		resultBookings = bcService.getBookings();
		//Then:
		assertEquals(resultBookings,expectBookings);			
	}
	
	/**
	 * <h2>addBooking() method test</h2>
	 * 
	 * True - Valid Cases
	 */
	@Test
	public void testAddBookingFalseInvalidTime() {
		//Given: empty list on bookingRegistry and bookings of today
		assertEquals(bcService.getBookings().isEmpty(),true);
		b1.setDate(LocalDateTime.now().minusDays(11));
		//When: add 2 instances of booking with 1 hour before and one hour after in bookingRegistry
		assertEquals(bcService.addBooking(b1),false);
		assertEquals(bcService.addBooking(b2),true);
		expectBookings.add(b2);
		resultBookings = bcService.getBookings();
		//Then:
		assertEquals(resultBookings,expectBookings);				
	}


	/**
	 * <h2>getNextBookings() method test</h2>
	 */
	@Test
	public void testGetNextBookings() {
		//Given: empty list of bookings
		assertEquals(bcService.getBookings().isEmpty(),true);
		assertEquals(bcService.addBooking(b1),true);
		assertEquals(bcService.addBooking(b2),true);
		assertEquals(bcService.addBooking(b3),true);
		assertEquals(bcService.getBookings().size(),3);
		bcService.getBookings().get(0).setDate(LocalDateTime.now().minusDays(11));
		//When: get next bookings in bookingRegistry
		expectBookings.add(b2);
		expectBookings.add(b3);
		resultBookings = bcService.getNextBookings();
		//Then:
		assertEquals(resultBookings,expectBookings);			
	}


	/**
	 * <h2>getBookingListOfaDay() method test</h2>
	 */
	@Test
	public void testGetBookingListOfaDay() {
		//Given: empty list on bookingRegistry and bookings 
		assertEquals(bcService.getBookings().isEmpty(),true);
		assertEquals(bcService.addBooking(b1),true);
		assertEquals(bcService.addBooking(b2),true);
		assertEquals(bcService.addBooking(b3),true);
		assertEquals(bcService.getBookings().size(),3);
		bcService.getBookings().get(1).setDate(dt1);
		//When: get bookings of day 11 in bookingRegistry
		expectBookings.add(b1);
		expectBookings.add(b2);
		resultBookings = bcService.getBookingsOf(dt1.toLocalDate());
		//Then:
		assertEquals(resultBookings,expectBookings);		
	}


	/**
	 * <h2>getNextBookingOf() method test</h2>
	 */
	@Test
	public void testGetNextBookingOf() {
		//Given: empty list on bookingRegistry and bookings 
		assertEquals(bcService.getBookings().isEmpty(),true);
		assertEquals(bcService.addBooking(b1),true);
		assertEquals(bcService.addBooking(b2),true);
		assertEquals(bcService.addBooking(b3),true);
		assertEquals(bcService.getBookings().size(),3);
		bcService.getBookings().get(1).setDate(dt1);
		//When: get bookings of day 11 in bookingRegistry
		
		Booking result = bcService.getNextBookingOf(c1);
		Booking result2 = bcService.getNextBookingOf(c2);
		//Then:
		assertEquals(result,b1);	
		assertEquals(result2,b2);
		
	}
	
	/**
	 * <h2>getNextBookingOf() method test</h2>
	 * 
	 * null case - no booking found
	 */
	@Test
	public void testGetNextBookingOfNoBookingFound() {
		//Given: empty list on bookingRegistry and bookings 
		assertEquals(bcService.getBookings().isEmpty(),true);
		//When: get bookings of day 11 in bookingRegistry
		
		Booking result = bcService.getNextBookingOf(c1);
		//Then:
		assertEquals(result,null);	
		
	}
	
	/**
	 * <h2>getCustomerList() method test</h2>
	 * 
	 */
	@Test
	public void testGetCustomerList() {
		//Given: empty list
		assertEquals(bcService.getAllCustomers().isEmpty(),true);
		//When: add customer
		bcService.addCustomer(c1);
		expect.add(c1);
		//Then: get that customer
		assertEquals(expect,bcService.getAllCustomers());
		
	}

	/**
	 * <h2>setCustomerList() method test</h2>
	 * 
	 */
	@Test
	public void testSetCustomerList() {
		//Given: empty list
		assertEquals(bcService.getAllCustomers().isEmpty(),true);
		//When: add customer
		CustomerRepositoryClass cRepo =  new CustomerRepositoryClass();
		cRepo.save(c1);
		cRepo.save(c2);
		expect.add(c1);
		expect.add(c2);
		bcService.setCustomersRepository(cRepo);
		
		//Then: get that customer
		assertEquals(expect,bcService.getAllCustomers());		
	}

	/**
	 * <h2>addCustomer() method test</h2>
	 * 
	 */
	@Test
	public void testAddCustomer() {
		//Given: empty list
		assertEquals(bcService.getAllCustomers().isEmpty(),true);
		//When: add 3 customer
		assertEquals(bcService.addCustomer(c1),true);
		assertEquals(bcService.addCustomer(c2),true);
		assertEquals(bcService.addCustomer(c3),true);
		
		//Then: cant add the customers twice to the list
		assertEquals(bcService.addCustomer(c1),false);
		assertEquals(bcService.addCustomer(c2),false);
		assertEquals(bcService.addCustomer(c3),false);		
	}

	/**
	 * <h2>createCustomer() method test</h2>
	 * 
	 * full data
	 */
	@Test
	public void testCreateCustomerFullData() {
		//Given: instance of Customer (Ana) - c2
		Customer expect = c2;
		//When: we create by cReg a customer (Joao) and set the same ID
		Customer result = bcService.createCustomer("Ana",bd2,"Porto","966677722");
		
		expect.setId(result.getId());
		
		//Then: they are equals
		assertEquals(result,expect);			
				
	}

	/**
	 * <h2>createCustomer() method test</h2>
	 * 
	 * only name as input data
	 */
	@Test
	public void testCreateCustomerOnlyName() {
		//Given: instance of Customer (Joao)
		Customer expect = new Customer("Joao");
		//When: we create by cReg a customer (Joao) and set the same ID
		Customer result = bcService.createCustomer("Joao");
		
		expect.setId(result.getId());
		
		//Then: they are equals
		assertEquals(result,expect);		
	}

}

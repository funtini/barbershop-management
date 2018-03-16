package bsmanagement.model.unittests;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Booking;
import bsmanagement.model.BookingRegistry;
import bsmanagement.model.Customer;


/**
 * 
 * Unit tests for BookingRegistry Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class BookingRegistryTest {

	
	
	LocalDate birthdate1;
	LocalDate birthdate2;
	Customer c1;
	Customer c2;	
	LocalDateTime dt1;
	LocalDateTime dt2;
	LocalDateTime dt3;
	
	
	Booking b1;
	Booking b2;
	Booking b3;
	
	BookingRegistry bookReg;
	List<Booking> expect;
	List<Booking> result;
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>BirthDate [bd1] : 30/11/1989 </p>
	 * <p>BirthDate [bd2] : 15/02/1984 </p>
	 * 
	 * <p>Customer [c1] -> ["Joao",'30/11/1989',"Mangualde",914047935] </p>
	 * <p>Customer [c2] -> ["Ana",'15/02/1984',"Porto",966677722] </p>
	 * 
	 * <p>DateTime [dt1] : Today's Date + 10 days</p>
	 * <p>DateTime [dt2] : Today's Date + 10 days + 30 seconds</p>
	 * <p>DateTime [dt3] : Today's Date + 1 month</p>
	 * 
	 * <p>Booking [b1] ->  [dt1,"Joao"] </p>
	 * <p>Booking [b2] ->  [dt2,"Ana"] </p>
	 * <p>Booking [b3] ->  [dt3,"Joao"] </p>
	 * 
	 * 
	 * 
	 */
	@Before
	public void setUp(){
		
		bookReg = new BookingRegistry();
		expect = new ArrayList<>();
		result = new ArrayList<>();
		
		Booking.setStartIdGenerator(1);
		
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		
		
		
		dt1 = LocalDateTime.now().plusDays(10);
		dt2 = LocalDateTime.now().plusDays(10).plusSeconds(30);
		dt3 = LocalDateTime.now().plusMonths(1);
		
		b1 = new Booking(dt1,c1);
		b2 = new Booking(dt2,c2);
		b3 = new Booking(dt3,c1);
	}

	
	/**
	 * <h2>getBookingRegistry() method test</h2>
	 */
	@Test
	public void testGetBookingRegistry() {
		//Given: empty list's
		assertEquals(bookReg.getBookings().isEmpty(),true);
	
		//When: add bookings to list
		bookReg.getBookings().add(b1);
		bookReg.getBookings().add(b2);
		bookReg.getBookings().add(b3);
		expect.add(b1);
		expect.add(b2);
		expect.add(b3);
		//Then: get a list with that 3 bookings
		result = bookReg.getBookings();
		assertEquals(result,expect);
			
	}


	/**
	 * <h2>setBookingRegistry() method test</h2>
	 */
	@Test
	public void testSetBookingRegistry() {
		//Given: empty list's
		assertEquals(bookReg.getBookings().isEmpty(),true);
		
		//When: add list of bookings to bookingRegistry
		expect.add(b1);
		expect.add(b2);
		expect.add(b3);
		bookReg.setBookings(expect);
		//Then: get a list with that 3 bookings
		result = bookReg.getBookings();
		assertEquals(result,expect);		
	}


	/**
	 * <h2>createBooking() method test</h2>
	 */
	@Test
	public void testCreateBooking() {
		//Given: empty list's
		assertEquals(bookReg.getBookings().isEmpty(),true);
		
		//When: create instances of bookings by bookingRegistry
		Booking.setStartIdGenerator(1);
		Booking result1 = bookReg.createBooking(dt1, c1);
		Booking result2 = bookReg.createBooking(dt2, c2);
		//Then:
		assertEquals(result1,b1);
		assertEquals(result2,b2);	
	}
	
	/**
	 * <h2>addBooking() method test</h2>
	 * 
	 * True - Valid Cases
	 */
	@Test
	public void testAddBookingTrue() {
		//Given: empty list on bookingRegistry
		assertEquals(bookReg.getBookings().isEmpty(),true);
		
		//When: add 3 instances of bookings in bookingRegistry
		assertEquals(bookReg.addBooking(b1),true);
		assertEquals(bookReg.addBooking(b2),true);
		assertEquals(bookReg.addBooking(b3),true);
		expect.add(b1);
		expect.add(b2);
		expect.add(b3);
		result = bookReg.getBookings();
		//Then:
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>addBooking() method test</h2>
	 * 
	 * True - Valid Cases
	 */
	@Test
	public void testAddBookingFalseInvalidDate() {
		//Given: empty list on bookingRegistry and bookings out of date
		assertEquals(bookReg.getBookings().isEmpty(),true);
		b1.setDate(dt1.minusMonths(2));
		b2.setDate(dt2.minusMonths(1));
		//When: add 3 instances of bookings in bookingRegistry
		assertEquals(bookReg.addBooking(b1),false);
		assertEquals(bookReg.addBooking(b2),false);
		assertEquals(bookReg.addBooking(b3),true);
		expect.add(b3);
		result = bookReg.getBookings();
		//Then:
		assertEquals(result,expect);			
	}
	
	/**
	 * <h2>addBooking() method test</h2>
	 * 
	 * True - Valid Cases
	 */
	@Test
	public void testAddBookingFalseInvalidTime() {
		//Given: empty list on bookingRegistry and bookings of today
		assertEquals(bookReg.getBookings().isEmpty(),true);
		b1.setDate(LocalDateTime.now().minusMinutes(1));
		b2.setDate(LocalDateTime.now().plusMinutes(1));
		//When: add 2 instances of booking with 1 hour before and one hour after in bookingRegistry
		assertEquals(bookReg.addBooking(b1),false);
		assertEquals(bookReg.addBooking(b2),true);
		expect.add(b2);
		result = bookReg.getBookings();
		//Then:
		assertEquals(result,expect);				
	}


	/**
	 * <h2>getNextBookings() method test</h2>
	 */
	@Test
	public void testGetNextBookings() {
		//Given: empty list on bookingRegistry and bookings 
		assertEquals(bookReg.getBookings().isEmpty(),true);
		assertEquals(bookReg.addBooking(b1),true);
		assertEquals(bookReg.addBooking(b2),true);
		assertEquals(bookReg.addBooking(b3),true);
		assertEquals(bookReg.getBookings().size(),3);
		bookReg.getBookings().get(0).setDate(dt1.minusMonths(1));
		//When: get next bookings in bookingRegistry
		expect.add(b2);
		expect.add(b3);
		result = bookReg.getNextBookings();
		//Then:
		assertEquals(result,expect);			
	}


	/**
	 * <h2>getBookingListOfaDay() method test</h2>
	 */
	@Test
	public void testGetBookingListOfaDay() {
		//Given: empty list on bookingRegistry and bookings 
		assertEquals(bookReg.getBookings().isEmpty(),true);
		assertEquals(bookReg.addBooking(b1),true);
		assertEquals(bookReg.addBooking(b2),true);
		assertEquals(bookReg.addBooking(b3),true);
		assertEquals(bookReg.getBookings().size(),3);
		bookReg.getBookings().get(1).setDate(dt1);
		//When: get bookings of day 11 in bookingRegistry
		expect.add(b1);
		expect.add(b2);
		result = bookReg.getBookingsOf(dt1.toLocalDate());
		//Then:
		assertEquals(result,expect);		
	}


	/**
	 * <h2>getNextBookingOf() method test</h2>
	 */
	@Test
	public void testGetNextBookingOf() {
		//Given: empty list on bookingRegistry and bookings 
		assertEquals(bookReg.getBookings().isEmpty(),true);
		assertEquals(bookReg.addBooking(b1),true);
		assertEquals(bookReg.addBooking(b2),true);
		assertEquals(bookReg.addBooking(b3),true);
		assertEquals(bookReg.getBookings().size(),3);
		bookReg.getBookings().get(1).setDate(dt1);
		//When: get bookings of day 11 in bookingRegistry
		
		Booking result = bookReg.getNextBookingOf(c1);
		Booking result2 = bookReg.getNextBookingOf(c2);
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
		assertEquals(bookReg.getBookings().isEmpty(),true);
		//When: get bookings of day 11 in bookingRegistry
		
		Booking result = bookReg.getNextBookingOf(c1);
		//Then:
		assertEquals(result,null);	
		
	}

}

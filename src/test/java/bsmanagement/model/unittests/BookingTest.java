package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Booking;
import bsmanagement.model.Customer;

/**
 * 
 * Unit tests for Booking Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class BookingTest {
	
	LocalDate birthdate1;
	LocalDate birthdate2;
	Customer c1;
	Customer c2;
	
	LocalDateTime dt1;
	LocalDateTime dt2;
	Booking b1;
	Booking b2;
	
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Date [d1] : 11/03/2018</p>
	 * <p>Date [d2] : 12/03/2018</p>
	 * <p>Time [t1] : 10:30</p>
	 * <p>Time [t2] : 14:30</p>
	 * <p>Customer [c1] : ['Joao','30/11/1989','Mangualde','914047935'] </p>
	 * <p>Customer [c2] : ['Ana','15/02/1984','Porto','966677722'] </p>
	 * 
	 * <p>Booking [b1] -> [d1,t1,c1] : ['11/03/2018','10:30',c1] </p>
	 * <p>Booking [b1] -> [d2,t2,c2] : ['12/03/2018','14:30',c2] </p>
	 * 
	 */
	@Before
	public void setUp() {
		Booking.setStartIdGenerator(1);
		
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		
		dt1 = LocalDateTime.now().plusDays(10);
		dt2 = LocalDateTime.now().plusDays(10).plusMinutes(1);
		
		b1 = new Booking(dt1,c1);
		b2 = new Booking(dt2,c2);
		
	}
	
	
	/**
	 * <h2>getId() method test</h2>
	 * 
	 * <p>Description
	 * 
	 */
	@Test 
	public void testGetId() {
		
		assertEquals(b1.getId(),1);
		
		assertEquals(b2.getId(),2);
	}
	
	
	/**
	 * <h2>setStartIdGenerator() method test</h2>
	 */
	@Test 
	public void testSetStartIdGenerator() {
		
		//Given
		assertEquals(b1.getId(),1);
		assertEquals(b2.getId(),2);
		//When
		Booking.setStartIdGenerator(10);
		Booking b3 = new Booking(dt2,c1);
		//Then
		assertEquals(b3.getId(),10);
	}
	
	
	/**
	 * <h2>getDate() and setDate() method test</h2>
	 * 
	 */
	@Test 
	public void testGetnSetDate() {
		
		//Given
		LocalDateTime expect = dt1;
		assertEquals(b1.getDate(),expect.truncatedTo(ChronoUnit.MINUTES));
		assertEquals(b2.getDate(),expect.plusMinutes(1).truncatedTo(ChronoUnit.MINUTES));
		//When
		LocalDateTime whenDate = LocalDateTime.of(2018, 3, 15,10,30);
		b1.setDate(whenDate);
		b2.setDate(whenDate);
		//Then
		assertEquals(b1.getDate(),LocalDateTime.of(2018, 3, 15,10,30));
		assertEquals(b2.getDate(),LocalDateTime.of(2018, 3, 15,10,30));
	}
	

	
	/**
	 * <h2>getCustomer() and setCustomer() method test</h2>
	 * 
	 */
	@Test 
	public void testGetnSetCustomer() {
		
		//Given
		Customer expect = new Customer("Joao",birthdate1,"Mangualde","914047935");
		assertEquals(b1.getCustomer(),expect);
		//When
		Customer whenCost = new Customer("Pedro",birthdate2,"Lisboa","977888771");
		b1.setCustomer(whenCost);
		//Then
		Customer thenCost = new Customer("Pedro",birthdate2,"Lisboa","977888771");
		assertEquals(b1.getCustomer(),thenCost);
		
	}

	/**
	 * <h2>equals() method test</h2>
	 * <p>test true cases</p>
	 */
	@Test
	public void testEqualsTrueSameInstance() {
		assertEquals(b1.equals(b1),true);
		assertEquals(b2.equals(b2),true);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases</p>
	 */
	@Test
	public void testEqualsFalse() {
		assertEquals(b1.equals(b2),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null cases</p>
	 */
	@Test
	public void testEqualsNull() {
		assertEquals(b1.equals(null),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null cases</p>
	 */
	@Test
	public void testEqualsDifferentInstance() {
		assertEquals(b1.equals(c1),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null cases</p>
	 */
	@Test
	public void testEqualsSameID() {
		
		b2.setId(1);
		assertEquals(b1.equals(b2),true);
	}
	
	/**
	 * <h2>hashCode() method test</h2>
	 */
	@Test
	public void testHashCode() {
		Booking b3 = new Booking(dt2,c2);
		b3.setId(2);
		assertEquals(b3.hashCode(),b2.hashCode());
		
		assertNotEquals(b1.hashCode(),b2.hashCode());
	}
	
	/**
	 * <h2>toString() method test</h2>
	 */
	@Test
	public void testToString() {
		String expect = "Booking [id=" + b1.getId() + ", date=" + b1.getDate().toLocalDate() + ", time=" + b1.getDate().toLocalTime() + ", customer=" + b1.getCustomer() + "]";
		assertEquals(b1.toString(),expect);
	}
	
	
}

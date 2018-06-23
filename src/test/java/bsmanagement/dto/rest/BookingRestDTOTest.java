package bsmanagement.dto.rest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class BookingRestDTOTest {
	
	BookingRestDTO bookingDTO;

	@Before
	public void setUp(){
		bookingDTO = new BookingRestDTO();
		bookingDTO.setBookingId(1);
		bookingDTO.setCustomerId(1);
		bookingDTO.setCustomerName("joao");
		bookingDTO.setDate(LocalDateTime.of(2029, 12, 22,10,10));
		bookingDTO.setUserId("pedro@gmail.com");
		bookingDTO.setUserName("pedro");
	}

	@Test
	public void testGetBookingId() {
		assertEquals(bookingDTO.getBookingId(),1);
	}
	
	@Test
	public void testGetCustomerId() {
		assertEquals(bookingDTO.getCustomerId(),1);
	}
	
	@Test
	public void testGetCustomerName() {
		assertEquals(bookingDTO.getCustomerName(),"joao");
	}
	
	@Test
	public void testGetDate() {
		assertEquals(bookingDTO.getDate(),LocalDateTime.of(2029, 12, 22,10,10));
	}

	@Test
	public void testGetUserId() {
		assertEquals(bookingDTO.getUserId(),"pedro@gmail.com");
	}
	
	@Test
	public void testGetUserName() {
		assertEquals(bookingDTO.getUserName(),"pedro");
	}

}

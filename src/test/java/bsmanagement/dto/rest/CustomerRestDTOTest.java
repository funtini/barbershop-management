package bsmanagement.dto.rest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class CustomerRestDTOTest {
	
	CustomerRestDTO customer = new CustomerRestDTO();

	@Before
	public void setUp() {
		customer.setAddress("Mangualde");
		customer.setBirth(LocalDate.of(1999, 11, 11));
		customer.setBookingId(5);
		customer.setEmail("jj@gmail.com");
		customer.setName("Joao");
		customer.setPhone("914047935");
	}

	@Test
	public void testGetBookingId() {
		assertEquals(customer.getBookingId(),5);
	}

	@Test
	public void testGetName() {
		assertEquals(customer.getName(),"Joao");
	}

	@Test
	public void testGetBirth() {
		assertEquals(customer.getBirth(),LocalDate.of(1999, 11, 11));
	}

	@Test
	public void testGetAddress() {
		assertEquals(customer.getAddress(),"Mangualde");
	}

	@Test
	public void testGetPhone() {
		assertEquals(customer.getPhone(),"914047935");
	}

	@Test
	public void testGetEmail() {
		assertEquals(customer.getEmail(),"jj@gmail.com");
	}

}

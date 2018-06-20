package bsmanagement.controllers;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bsmanagement.dto.rest.BookingRestDTO;
import bsmanagement.jparepositories.classtests.BookingRepositoryClass;
import bsmanagement.jparepositories.classtests.CustomerRepositoryClass;
import bsmanagement.model.Booking;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;
import bsmanagement.model.User;

public class BookingRestControllerTest {
	
	BookingRestController brc;
	
	LocalDate birthdate1;
	LocalDate birthdate2;
	Customer c1;
	Customer c2;
	Customer c3;
	
	LocalDateTime dt1;
	LocalDateTime dt2;
	LocalDateTime dt3;
	LocalDateTime dt4;
	
	LocalDate bd1;
	LocalDate bd2;
	LocalDate bd3;
	
	User u1;
	User u2;
	
	Booking b1;
	Booking b2;
	Booking b3;
	Booking b4;
	
	BookingRestDTO b1_DTO;
	BookingRestDTO b2_DTO;
	BookingRestDTO b3_DTO;
	BookingRestDTO b4_DTO;
	
	BookingCustomerService bcService;
	CustomerRepositoryClass customerRepository;
	BookingRepositoryClass bookingRepository;
	
	List<BookingRestDTO> bookings;
	ResponseEntity<BookingRestDTO> response;
	ResponseEntity<BookingRestDTO> expected;
	ResponseEntity<List<BookingRestDTO>> responseList;
	ResponseEntity<List<BookingRestDTO>> expectedList;
	
	
	
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
	 * <p>User [u1] : ["JOAO",birth1,"joao@domain.com","914047935","324666433"] </p>
	 * <p>User [u2] : ["PEDRO",birth2,"pedro@dgmail.uk","915557911","123555433"] </p>
	 * 
	 * <p>DateTime [dt1] : Today's Date + 30 mins</p>
	 * <p>DateTime [dt2] : Today's Date + 10 days</p>
	 * <p>DateTime [dt3] : Today's Date + 1 month</p>
	 * 
	 * <p>Booking [b1] ->  [dt1,"Joao"] </p>
	 * <p>Booking [b2] ->  [dt2,"Ana"] </p>
	 * <p>Booking [b3] ->  [dt3,"Joao"] </p>
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
		brc = new BookingRestController(bcService);
		bookings = new ArrayList<>();
		Booking.setStartIdGenerator(1);
		Customer.setStartIdGenerator(1);
		
		
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		
		bd1=LocalDate.of(1989, 11, 30);
		bd2=LocalDate.of(1984, 02, 15);
		bd3=LocalDate.of(1992, 05, 25);
		
		u1 = new User("JOAO",birthdate1,"joao@domain.com","914047935","324666433");
		u2 = new User("PEDRO",birthdate2,"pedro@gmail.uk","915557911","123555433");
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		c3 = new Customer("Pedro",bd3,"Mangualde","932444333");
		
		dt1 = LocalDateTime.now().plusMinutes(30);
		dt2 = LocalDateTime.of(LocalDate.now(),LocalTime.of(10, 10)).plusDays(10);
		dt3 = LocalDateTime.of(LocalDate.now(),LocalTime.of(14, 30)).plusDays(10);
		dt4 = LocalDateTime.of(LocalDate.now(),LocalTime.of(10, 10)).plusMonths(1);
		bcService.addCustomer(c1);
		bcService.addCustomer(c2);
		bcService.addCustomer(c3);
		c1 = bcService.findCustomerById(1);
		c2 = bcService.findCustomerById(2);
		c3 = bcService.findCustomerById(3);
		
		b1 = new Booking(dt1,c1,u1);
		b2 = new Booking(dt2,c2,u1);
		b3 = new Booking(dt3,c3,u1);
		b4 = new Booking(dt4,c1,u2);
		
		bcService.addBooking(b1);
		bcService.addBooking(b2);
		bcService.addBooking(b4);
		bcService.addBooking(b3);
		
		b1 = bcService.findBookingById(1);
		b2 = bcService.findBookingById(2);
		b3 = bcService.findBookingById(4);
		b4 = bcService.findBookingById(3);
		
		b1_DTO = b1.toRestDTO();
		b1_DTO.setCustomerName(b1.getCustomer().getName());
		b1_DTO.setUserName(b1.getUser().getName());
		
		b2_DTO = b2.toRestDTO();
		b2_DTO.setCustomerName(b2.getCustomer().getName());
		b2_DTO.setUserName(b2.getUser().getName());
		
		b3_DTO = b3.toRestDTO();
		b3_DTO.setCustomerName(b3.getCustomer().getName());
		b3_DTO.setUserName(b3.getUser().getName());
		
		b4_DTO = b4.toRestDTO();
		b4_DTO.setCustomerName(b4.getCustomer().getName());
		b4_DTO.setUserName(b4.getUser().getName());
	}

	
	/**
	 * testListNextBookings() controller
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call listNextBookings() controller</p>
	 * <p>THEN: Get a list of those bookings order by time</p>
	 */
	@Test
	public void testListNextBookings() {
		//GIVEN
		bookings.add(b1_DTO);
		bookings.add(b2_DTO);
		bookings.add(b3_DTO);
		bookings.add(b4_DTO);
		
		//WHEN
		responseList = brc.listNextBookings();
		
		//THEN
		
		assertEquals(bookings,responseList.getBody());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		
	}

	
	/**
	 * testListTodayBookings() controller
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call listTodayBookings() controller</p>
	 * <p>THEN: Get a list of a single booking</p>
	 */
	@Test
	public void testListTodayBookings() {
		//GIVEN
		bookings.add(b1_DTO);
		
		//WHEN
		responseList = brc.listTodayBookings();
		
		//THEN		
		assertEquals(bookings,responseList.getBody());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		
	}

	/**
	 * testListBookingsOfDate() controller
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call listBookingsOfDate(today+10days) controller</p>
	 * <p>THEN: Get a list of two bookings of today+10days</p>
	 */
	@Test
	public void testListBookingsOfDateSuccess() {
		//GIVEN
		bookings.add(b2_DTO);
		bookings.add(b3_DTO);
		
		//WHEN
		responseList = brc.listBookingsOfDate("2018-06-30");
		
		//THEN
		
		assertEquals(bookings,responseList.getBody());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
					
	}
	
	/**
	 * testListBookingsOfDate() controller
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call listBookingsOfDate() controller with invalid date</p>
	 * <p>THEN: Get an BADREQUEST</p>
	 */
	@Test
	public void testListBookingsOfDateInvalidDate() {
		//GIVEN
		
		
		//WHEN
		responseList = brc.listBookingsOfDate("2018-026-3023");
		
		//THEN
		
		assertEquals(HttpStatus.BAD_REQUEST,responseList.getStatusCode());
					
	}

	
	/**
	 * testListBookingsOfUser() controller
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call listBookingsOfUser() controller</p>
	 * <p>THEN: Get a list of 3 bookings of that user</p>
	 */
	@Test
	public void testListBookingsOfUserSuccess() {
		//GIVEN
		bookings.add(b1_DTO);
		bookings.add(b2_DTO);
		bookings.add(b3_DTO);
		
		//WHEN
		responseList = brc.listBookingsOfUser("joao@domain.com");
		
		//THEN
		
		assertEquals(bookings,responseList.getBody());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());		
	}
	
	
	/**
	 * testListBookingsOfCustomer() controller - OK case
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call listBookingsOfCustomer() controller</p>
	 * <p>THEN: get the booking of that customer</p>
	 */
	@Test
	public void testListBookingsOfCustomerSuccess() {
		//GIVEN
		bookings.add(b1_DTO);
		bookings.add(b2_DTO);
		bookings.add(b3_DTO);
		
		//WHEN
		response = brc.listBookingsOfCustomer(2);
		
		//THEN
		
		assertEquals(b2_DTO,response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());				
	}
	
	/**
	 * testListBookingsOfCustomer() controller - NOT FOUND case
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call listBookingsOfCustomer() controller with a non existing ID</p>
	 * <p>THEN: get NOT_FOUND reponse</p>
	 */
	@Test
	public void testListBookingsOfCustomerNotFound() {
		//GIVEN
		
		//WHEN
		response = brc.listBookingsOfCustomer(4);
		
		//THEN
		
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());				
	}

	
	/**
	 * testGetBookingById() controller - OK case
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call getBookingById() controller with a valid ID</p>
	 * <p>THEN: get OK reponse and respective BookingRestDTO</p>
	 */
	@Test
	public void testGetBookingByIdSuccess() {
		//GIVEN
		
		//WHEN
		response = brc.getBookingById(1);
		
		//THEN
		
		assertEquals(b1_DTO,response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());						
	}
	
	
	/**
	 * testGetBookingById() controller - OK case
	 * 
	 * <p>GIVEN: List of 4 bookings converted in RestDTO</p>
	 * <p>WHEN: call getBookingById() controller with a non existing ID</p>
	 * <p>THEN: get NOT_FOUND reponse</p>
	 */
	@Test
	public void testGetBookingByIdNotFOund() {
		//GIVEN
		
		//WHEN
		response = brc.getBookingById(44);
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());						
	}

}

package bsmanagement.controllers.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.BookingRestDTO;
import bsmanagement.model.Booking;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;
import bsmanagement.model.User;
import bsmanagement.model.UserService;

@RestController
public class BookingRestController {

	@Autowired
	BookingCustomerService bookingCustomerService;
	
	@Autowired
	UserService userService;
	
	public BookingRestController(BookingCustomerService bookingCustomerService, UserService userService) {
		this.bookingCustomerService = bookingCustomerService;
		this.userService = userService;
	}
	
	/**
	 * Rest Controller to get a list of all next bookings
	 * 
	 * @return ResponseEntity with a List of BookingRestDTO
	 */
	@RequestMapping("/bookings")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<BookingRestDTO>> listNextBookings()
	{
		List<BookingRestDTO> bookingsRestDTO = new ArrayList<>();
		for (Booking b : bookingCustomerService.getNextBookings())
		{
			BookingRestDTO booking = b.toRestDTO();
			if (b.getCustomer() != null)
				booking.setCustomerName(b.getCustomer().getName());
			if (b.getUser() != null)
				booking.setUserName(b.getUser().getName());
			bookingsRestDTO.add(booking);
			
			
		}
		return new ResponseEntity<>(bookingsRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a list of today bookings
	 * 
	 * @return ResponseEntity with a List of BookingRestDTO
	 */
	@RequestMapping("/bookings/today")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<BookingRestDTO>> listTodayBookings()
	{
		List<BookingRestDTO> bookingsRestDTO = new ArrayList<>();
		for (Booking b : bookingCustomerService.getBookingsOfDay(LocalDate.now()))
		{
			BookingRestDTO booking = b.toRestDTO();
			if (b.getCustomer() != null)
				booking.setCustomerName(b.getCustomer().getName());
			if (b.getUser() != null)
				booking.setUserName(b.getUser().getName());
			bookingsRestDTO.add(booking);
			
			
		}
		return new ResponseEntity<>(bookingsRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a list of bookings of specific day
	 * 
	 * <p>If date is invalid, response entity will be returned as a BAD_REQUEST</p>
	 * 
	 * @return If date is valid : ResponseEntity with a List of BookingRestDTO and a OK httpstatus. Otherwise, BadRequest Status returned
	 */
	@GetMapping(value = "bookings", params = "date")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<BookingRestDTO>> listBookingsOfDate(@RequestParam(value = "date", required = true) String date)
	{
		LocalDate dateConverted;
		List<BookingRestDTO> bookingsRestDTO = new ArrayList<>();
		try {
			dateConverted = LocalDate.parse(date);
		}
		catch(DateTimeParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		for (Booking b : bookingCustomerService.getBookingsOfDay(dateConverted))
		{
			BookingRestDTO booking = b.toRestDTO();
			if (b.getCustomer() != null)
				booking.setCustomerName(b.getCustomer().getName());
			if (b.getUser() != null)
				booking.setUserName(b.getUser().getName());
			bookingsRestDTO.add(booking);
			
			
		}
		return new ResponseEntity<>(bookingsRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a booking of specific customer
	 * 
	 * <p>If customer ID doesn't exist, the response entity will be returned as NOT_FOUND</p>
	 * 
	 * @return HttpStatus OK with a bookingRestDTO if customerID is valid, otherwise HttpStatus NOT_FOUND
	 */
	@GetMapping(value = "bookings", params = "customerId")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<BookingRestDTO> listBookingsOfCustomer(@RequestParam(value = "customerId", required = true) int customerId)
	{
			Booking b = bookingCustomerService.getNextBookingOf(bookingCustomerService.findCustomerById(customerId));
			if (b == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			BookingRestDTO booking = b.toRestDTO();
			if (b.getCustomer() != null)
				booking.setCustomerName(b.getCustomer().getName());
			if (b.getUser() != null)
				booking.setUserName(b.getUser().getName());
	
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a List of bookings of specific user
	 * 
	 * <p>If user ID doesn't exist, the response entity will be returned as NOT_FOUND</p>
	 * 
	 * @return HttpStatus OK with a bookingRestDTO if userID is valid, otherwise HttpStatus NOT_FOUND
	 */
	@GetMapping(value = "bookings", params = "userId")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<BookingRestDTO>> listBookingsOfUser(@RequestParam(value = "userId", required = true) String userId)
	{
		List<BookingRestDTO> bookingsRestDTO = new ArrayList<>();
		for (Booking b : bookingCustomerService.getNextBookingsOf(userId))
		{
			BookingRestDTO booking = b.toRestDTO();
			if (b.getCustomer() != null)
				booking.setCustomerName(b.getCustomer().getName());
			if (b.getUser() != null)
				booking.setUserName(b.getUser().getName());
			bookingsRestDTO.add(booking);		
			
		}
		return new ResponseEntity<>(bookingsRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a single booking by ID
	 * 
	 * @return HttpStatus.OK  with a BookingRestDTO if ID exists, otherwise, return http status of NOT_FOUND
	 */
	@RequestMapping("/bookings/{bookingId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<BookingRestDTO> getBookingById(@PathVariable(value = "bookingId") int bookingId)
	{
		
			Booking b = bookingCustomerService.findBookingById(bookingId);
			if (b==null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			BookingRestDTO booking = b.toRestDTO();
			if (b.getCustomer() != null)
				booking.setCustomerName(b.getCustomer().getName());
			if (b.getUser() != null)
				booking.setUserName(b.getUser().getName());
			
			
		
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to create a new booking
	 * 
	 * @return ResponseEntity CREATED if date is valid, otherwise return BAD_REQUEST
	 */
	@PostMapping("/bookings")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<BookingRestDTO> createBooking(@RequestBody BookingRestDTO bookingDTO)
	{
		Customer c = bookingCustomerService.findCustomerById(bookingDTO.getCustomerId());
		User u = userService.findUserByEmail(bookingDTO.getUserId());
		Booking b = bookingCustomerService.createBooking(bookingDTO.getDate(), c, u);
		if (!bookingCustomerService.addBooking(b))
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		BookingRestDTO booking = b.toRestDTO();
		if (b.getCustomer() != null)
			booking.setCustomerName(b.getCustomer().getName());
		if (b.getUser() != null)
			booking.setUserName(b.getUser().getName());	
		
		return new ResponseEntity<>(booking,HttpStatus.CREATED);
	}
	
	/**
	 * Rest Controller to edit booking
	 * 
	 * @return ResponseEntity OK if bookingId is valid, otherwise return NOT_FOUND
	 */
	@PutMapping("/bookings/{bookingId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<BookingRestDTO> editBooking(@PathVariable(value = "bookingId") int bookingId,@RequestBody BookingRestDTO bookingDTO)
	{
		Booking b = bookingCustomerService.findBookingById(bookingId);
		if (b==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (bookingDTO.getDate().isBefore(LocalDateTime.now()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (bookingDTO.getCustomerId() != 0)
			b.setCustomer(bookingCustomerService.findCustomerById(bookingDTO.getCustomerId()));
		if (bookingDTO.getUserId() != null)
			b.setUser(userService.findUserByEmail(bookingDTO.getUserId()));
		
		if (bookingDTO.getDate() != null)
			b.setDate(bookingDTO.getDate());
		
		bookingCustomerService.updateBooking(b);
		
		
		return new ResponseEntity<>(b.toRestDTO(),HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to delete a single booking by ID
	 * 
	 * @return HttpStatus.OK if booking was deleted, otherwise, return http status of NOT_FOUND
	 */
	@DeleteMapping("/bookings/{bookingId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<BookingRestDTO> removeBooking(@PathVariable(value = "bookingId") int bookingId)
	{	
			if (!bookingCustomerService.removeBooking(bookingId))
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
//	@PostMapping("/bookings")
//	public String addBooking(@RequestBody BookingRestDTO bookingDTO) {
//		
//		bookingCustomerService.addBooking(bookingDTO);
//		return bookingDTO.getMessage();
//	}
//	
}

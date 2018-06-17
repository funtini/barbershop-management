package bsmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.BookingRestDTO;
import bsmanagement.model.Booking;
import bsmanagement.model.BookingCustomerService;

@RestController
public class BookingRestController {

	@Autowired
	BookingCustomerService bookingCustomerService;
	
	public BookingRestController() {
		
	}
	
	@RequestMapping("/bookings")
	@PreAuthorize("hasRole('USER') || hasRole('MANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<BookingRestDTO>> listNextBookings()
	{
		List<BookingRestDTO> bookingsRestDTO = new ArrayList<>();
		for (Booking b : bookingCustomerService.getNextBookings())
		{
			bookingsRestDTO.add(b.toRestDTO());
		}
		return new ResponseEntity<>(bookingsRestDTO,HttpStatus.OK);
	}
	
//	@PostMapping("/bookings")
//	public String addBooking(@RequestBody BookingRestDTO bookingDTO) {
//		
//		bookingCustomerService.addBooking(bookingDTO);
//		return bookingDTO.getMessage();
//	}
//	
}

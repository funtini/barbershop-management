package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Booking> listNextBookings()
	{
		return bookingCustomerService.getNextBookings();
	}
	
//	@PostMapping("/bookings")
//	public String addBooking(@RequestBody BookingRestDTO bookingDTO) {
//		
//		bookingCustomerService.addBooking(bookingDTO);
//		return bookingDTO.getMessage();
//	}
//	
}

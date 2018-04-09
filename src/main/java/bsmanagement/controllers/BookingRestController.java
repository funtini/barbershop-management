package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.model.Booking;
import bsmanagement.model.BookingService;

@RestController
public class BookingRestController {

	@Autowired
	BookingService bookingService;
	
	public BookingRestController() {
		
	}
	
	@RequestMapping("/bookings")
	List<Booking> listNextBookings()
	{
		return bookingService.getNextBookings();
	}
	
}

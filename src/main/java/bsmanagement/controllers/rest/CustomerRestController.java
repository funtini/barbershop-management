package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.CustomerRestDTO;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;

@RestController
public class CustomerRestController {
	
	@Autowired
	BookingCustomerService bookingCustomerService;
	

	public CustomerRestController()
	{
		
	}
	
	@RequestMapping("/customers")
	public List<Customer> listAllCustomers()
	{
		return bookingCustomerService.getAllCustomers();
	}
	
	@PostMapping("/customers")
	public String addCustomer(@RequestBody CustomerRestDTO customerDTO)
	{
		bookingCustomerService.addCustomer(customerDTO);
		return customerDTO.getSuccessMessage();
	}
	
	
	

}

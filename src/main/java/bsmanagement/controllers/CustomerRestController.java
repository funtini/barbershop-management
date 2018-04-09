package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.model.Customer;
import bsmanagement.model.CustomerService;

@RestController
public class CustomerRestController {
	
	@Autowired
	CustomerService customerService;
	

	public CustomerRestController()
	{
		
	}
	
	@RequestMapping("/customers")
	public List<Customer> listAllCustomers()
	{
		return customerService.getCustomers();
	}

}

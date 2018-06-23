package bsmanagement.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	

	public CustomerRestController(BookingCustomerService bookingCustomerService)
	{
		this.bookingCustomerService = bookingCustomerService;
	}
	
	
	/**
	 * Rest Controller to get a list of all customers
	 * 
	 * @return ResponseEntity with a List of CustomerRestDTO
	 */
	@RequestMapping("/customers")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<CustomerRestDTO>> getAllCustomers()
	{
		List<CustomerRestDTO> customersRestDTO = new ArrayList<>();
		for (Customer c : bookingCustomerService.getAllCustomers())
		{
			CustomerRestDTO customer = c.toRestDTO();
			customersRestDTO.add(customer);
		}
		return new ResponseEntity<>(customersRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to create a new customer
	 * 
	 * @return ResponseEntity CREATED if name is valid, otherwise return BAD_REQUEST
	 */
	@PostMapping("/customers")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<CustomerRestDTO> addCustomer(@RequestBody CustomerRestDTO customerDTO)
	{
		if(customerDTO.getName()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Customer c = bookingCustomerService.createCustomer(customerDTO.getName(), customerDTO.getBirth(), customerDTO.getAddress(), customerDTO.getPhone());
		if (customerDTO.getEmail()!= null)
			c.setEmail(customerDTO.getEmail());
		bookingCustomerService.addCustomer(c);
		
		return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
	}
	
	/**
	 * Rest Controller to edit customer information
	 * 
	 * @return ResponseEntity OK if customer exists , otherwise return NOT_FOUND
	 */
	@PutMapping("/customers/{customerId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<CustomerRestDTO> editCustomer(@PathVariable(value = "customerId") int customerId,@RequestBody CustomerRestDTO customerDTO)
	{
		Customer c = bookingCustomerService.findCustomerById(customerId);
		if(c==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (customerDTO.getEmail()!= null)
			c.setEmail(customerDTO.getEmail());
		if (customerDTO.getBirth()!= null)
			c.setBirth(customerDTO.getBirth());
		if (customerDTO.getAddress()!= null)
			c.setAddress(customerDTO.getAddress());
		if (customerDTO.getName()!= null)
			c.setName(customerDTO.getName());
		if (customerDTO.getPhone()!= null)
			c.setPhone(customerDTO.getPhone());
		bookingCustomerService.updateCustomer(c);
		return new ResponseEntity<>(c.toRestDTO(),HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a customer by id
	 * 
	 * @return ResponseEntity with a CustomerRestDTO if id is valid, otherwise return NOT_FOUND
	 */
	@RequestMapping("/customers/{customerId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<CustomerRestDTO> findCustomerById(@PathVariable(value = "customerId") int customerId)
	{
		Customer c = bookingCustomerService.findCustomerById(customerId);
		if(c==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(c.toRestDTO(),HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a customer by id
	 * 
	 * @return ResponseEntity with a CustomerRestDTO if id is valid, otherwise return NOT_FOUND
	 */
	@DeleteMapping("/customers/{customerId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<CustomerRestDTO> removeCustomer(@PathVariable(value = "customerId") int customerId)
	{
		if(!bookingCustomerService.removeCustomer(customerId))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

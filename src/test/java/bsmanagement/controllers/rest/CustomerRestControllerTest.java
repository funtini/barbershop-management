package bsmanagement.controllers.rest;

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
import bsmanagement.dto.rest.CustomerRestDTO;
import bsmanagement.jparepositories.classtests.BookingRepositoryClass;
import bsmanagement.jparepositories.classtests.CustomerRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.Booking;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;
import bsmanagement.model.User;
import bsmanagement.model.UserService;

public class CustomerRestControllerTest {

	CustomerRestController crc;

	Customer c1;
	Customer c2;
	Customer c3;
	
	LocalDate bd1;
	LocalDate bd2;
	LocalDate bd3;
	
	CustomerRestDTO c1_DTO;
	CustomerRestDTO c2_DTO;
	CustomerRestDTO c3_DTO;

	
	BookingCustomerService bcService;
	CustomerRepositoryClass customerRepository;
	BookingRepositoryClass bookingRepository;
	
	List<CustomerRestDTO> expectedCustomers;
	ResponseEntity<CustomerRestDTO> response;
	ResponseEntity<CustomerRestDTO> expected;
	ResponseEntity<List<CustomerRestDTO>> responseList;
	ResponseEntity<List<CustomerRestDTO>> expectedList;
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * 
	 * <p>Customer [c1] -> ["Joao",'30/11/1989',"Mangualde",914047935] </p>
	 * <p>Customer [c2] -> ["Ana",'15/02/1984',"Porto",966677722] </p>
	 * <p>Customer [c3] -> ["Pedro",'25/05/1992',"Mangualde",932444333] </p>
	 * 
	 */
	@Before
	public void setUp() {
		bcService = new BookingCustomerService();
		customerRepository = new CustomerRepositoryClass();
		bookingRepository = new BookingRepositoryClass();
		bcService.setBookRepository(bookingRepository);
		bcService.setCustomersRepository(customerRepository);
		expectedCustomers = new ArrayList<>();
		Booking.setStartIdGenerator(1);
		Customer.setStartIdGenerator(1);
	
		crc = new CustomerRestController(bcService);
		bd1=LocalDate.of(1989, 11, 30);
		bd2=LocalDate.of(1984, 02, 15);
		bd3=LocalDate.of(1992, 05, 25);

		c1 = new Customer("Joao",bd1,"Mangualde","914047935");
		c2 = new Customer("Ana",bd2,"Porto","966677722");
		c3 = new Customer("Pedro",bd3,"Mangualde","932444333");
		

	}

	
	/**
	 * testGetAllCustomers() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call getAllCustomers() controller</p>
	 * <p>THEN: Get a list of those customers</p>
	 */
	@Test
	public void testGetAllCustomers() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);
		
		//WHEN
		responseList = crc.getAllCustomers();
		
		//THEN
		assertEquals(responseList.getBody().size(),3);
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		
		expectedCustomers.add(bcService.findCustomerById(1).toRestDTO());
		expectedCustomers.add(bcService.findCustomerById(2).toRestDTO());
		expectedCustomers.add(bcService.findCustomerById(3).toRestDTO());
		assertEquals(responseList.getBody(), expectedCustomers);
	}

	
	/**
	 * testAddCustomer() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call addCustomer() controller</p>
	 * <p>THEN: the list of customer will increase to 4</p>
	 */
	@Test
	public void testAddCustomerSuccess() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);		
		
		//WHEN
		CustomerRestDTO customer = new CustomerRestDTO();
		customer.setName("FILIPE SOUSA");
		customer.setPhone("9191911");
		customer.setEmail("jajaaj@gmail.com");
		response = crc.addCustomer(customer);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
		assertEquals(bcService.getAllCustomers().size(),4);
		
	}
	
	
	/**
	 * testAddCustomer() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call addCustomer() controller with no name</p>
	 * <p>THEN: the list of customers still unchanged</p>
	 */
	@Test
	public void testAddCustomerBadRequest() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);		
		
		//WHEN
		CustomerRestDTO customer = new CustomerRestDTO();
		customer.setPhone("9191911");
		customer.setEmail("jajaaj@gmail.com");
		response = crc.addCustomer(customer);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
		assertEquals(bcService.getAllCustomers().size(),3);
		
	}
	
	/**
	 * testEditCustomer() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call editCustomer() controller with invalid ID</p>
	 * <p>THEN: response an httpstatus NOT_FOUND</p>
	 */
	@Test
	public void testEditCustomerNotFound() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);		
		
		//WHEN
		CustomerRestDTO customer = new CustomerRestDTO();
		customer.setPhone("9191911");
		customer.setEmail("jajaaj@gmail.com");
		response = crc.editCustomer(55,customer);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
		assertEquals(bcService.findCustomerById(55),null);
		
	}

	/**
	 * testEditCustomer() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call editCustomer() controller with a valid ID</p>
	 * <p>THEN: response an httpstatus OK and edited sucessfuly the customer's information</p>
	 */
	@Test
	public void testEditCustomerSuccess() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);
		assertEquals(bcService.findCustomerById(2).getPhone(),"966677722");
		assertEquals(bcService.findCustomerById(2).getAddress(),"Porto");
		
		//WHEN
		CustomerRestDTO customer = new CustomerRestDTO();
		customer.setPhone("9191911");
		customer.setAddress("Mangualde");
		response = crc.editCustomer(2,customer);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(bcService.findCustomerById(2).getPhone(),"9191911");
		assertEquals(bcService.findCustomerById(2).getAddress(),"Mangualde");
		
	}

	
	/**
	 * testFindCustomerById() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call findCustomerById() controller with a valid ID</p>
	 * <p>THEN: return a httpstatus OK and a respective customer</p>
	 */
	@Test
	public void testFindCustomerByIdSuccess() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);	
		
		//WHEN		
		response = crc.findCustomerById(2);
		
		//THEN
		CustomerRestDTO customerDTO = bcService.findCustomerById(2).toRestDTO();
		assertEquals(response.getBody(),customerDTO);
		assertEquals(response.getStatusCode(),HttpStatus.OK);		
	}
	
	/**
	 * testFindCustomerById() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call findCustomerById() controller with an invalid ID</p>
	 * <p>THEN: return a httpstatus NOT_FOUND </p>
	 */
	@Test
	public void testFindCustomerByIdNotFound() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);	
		
		//WHEN		
		response = crc.findCustomerById(22);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);		
	}

	/**
	 * testRemoveCustomer() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call removeCustomer() controller with an invalid ID</p>
	 * <p>THEN: return a httpstatus NOT_FOUND and customers list still unchanged</p>
	 */
	@Test
	public void testRemoveCustomerNotFound() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);	
		
		//WHEN		
		response = crc.removeCustomer(22);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
		assertEquals(bcService.getAllCustomers().size(),3);	
	}
	
	/**
	 * testRemoveCustomer() controller
	 * 
	 * <p>GIVEN: Added 3 customers to service</p>
	 * <p>WHEN: call removeCustomer() controller with an invalid ID</p>
	 * <p>THEN: return a httpstatus OK and list of customer is decreased to 2 </p>
	 */
	@Test
	public void testRemoveCustomerSuccess() {
		//GIVEN
		assertEquals(bcService.getAllCustomers().size(),0);
		assertEquals(bcService.addCustomer(c1), true);
		assertEquals(bcService.addCustomer(c2), true);
		assertEquals(bcService.addCustomer(c3), true);
		assertEquals(bcService.getAllCustomers().size(),3);	
		
		//WHEN		
		response = crc.removeCustomer(2);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		
		assertEquals(bcService.getAllCustomers().size(),2);	
	}

}

package bsmanagement.controllers.rest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bsmanagement.dto.rest.SaleRestDTO;
import bsmanagement.jparepositories.classtests.BookingRepositoryClass;
import bsmanagement.jparepositories.classtests.CustomerRepositoryClass;
import bsmanagement.jparepositories.classtests.PaymentRepositoryClass;
import bsmanagement.jparepositories.classtests.ProductRepositoryClass;
import bsmanagement.jparepositories.classtests.SaleRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Contract;
import bsmanagement.model.Customer;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.Product.productType;
import bsmanagement.model.ProductService;

public class SaleRestControllerTest {

	
	Customer c1;
	Customer c2;
	
	LocalDate birthdate1;
	LocalDate birthdate2;
	
	LocalDateTime d1;
	LocalDateTime d2;
	LocalDateTime d3;
	LocalDateTime d4;

	Product p1;
	Product p2;
	
	PaymentMethod cash;
	PaymentMethod credit;
	
	Sale s1;
	Sale s2;
	Sale s3;
	Sale s4;
	
	User u1;
	User u2;
	
	ResponseEntity<List<SaleRestDTO>> responseList;
	List<SaleRestDTO> expectedList;
	ResponseEntity<SaleRestDTO> response;

	SaleService saleService;
	BookingCustomerService bookingCustomerService;
	CustomerRepositoryClass customerRepository;
	BookingRepositoryClass bookingRepository;
	SaleRepositoryClass saleRepository;
	ProductRepositoryClass productRepository;
	PaymentRepositoryClass paymentRepository;
	ProductService productService;
	UserService userService;
	UserRepositoryClass userRepository;
	SaleRestController src;

	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>DateTime [d1] : 2018/03/11 - 10:30 </p>
	 * <p>DateTime [d2] : 2018/03/12 - 11:35 </p>
	 * <p>DateTime [d3] : 2018/03/12 - 12:15 </p>
	 * <p>DateTime [d4] : 2018/02/22 - 15:15 </p>
	 * 
	 * <p>Product [p1] : ["CORTE COM LAVAGEM",'HAIRCUT',15] </p>
	 * <p>Product [p2] : ["CORTE SIMPLES",'HAIRCUT',10] </p>
	 * 
	 * <p>Customer [c1] -> ['Joao',birthdate1,"Mangualde","914047935"] </p>
	 * <p>Customer [c2] -> ['Ana',birthdate2,"Porto","966677722"] </p>
	 * 
	 * <p>User [u1] : ["JOAO",birth1,"joao@domain.com","914047935","324666433"] </p>
	 * <p>User [u2] : ["PEDRO",birth2,"pedro@dgmail.uk","915557911","123555433"] </p>
	 * 
	 * <p>Payment [cash] -> ['CASH',0,0] </p>
	 * <p>Payment [credit] -> ['CREDIT',0.5,0.5] </p>
	 * 
	 * <p>Sale [s1] -> [d1,c1,p1] </p>
	 * <p>Sale [s2] -> [d2,c2,p2] </p>
	 * <p>Sale [s3] -> [d3,p1] </p>
	 * <p>Sale [s3] -> [d4,c1,p2] </p>
	 */
	@Before
	public void setUp(){
		
		expectedList = new ArrayList<>();
		saleService = new SaleService();
		userService= new UserService();
		productService = new ProductService();
		bookingCustomerService = new BookingCustomerService();
		userRepository = new UserRepositoryClass();
		saleRepository = new SaleRepositoryClass();
		paymentRepository = new PaymentRepositoryClass();
		customerRepository = new CustomerRepositoryClass();
		bookingRepository = new BookingRepositoryClass();
		productRepository = new ProductRepositoryClass();
		productService.setRepository(productRepository);
		bookingCustomerService.setBookRepository(bookingRepository);
		bookingCustomerService.setCustomersRepository(customerRepository);
		saleService.setSaleRepository(saleRepository);
		saleService.setPaymentRepository(paymentRepository);
		userService.setUserRepository(userRepository);
		src = new SaleRestController(saleService, productService, bookingCustomerService, userService);
	
		
		Sale.setStartIdGenerator(1);
		Customer.setStartIdGenerator(1);
		Product.setStartIdGenerator(1);
		
		//add 2 users
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		
		u1 = userService.createUser("JOAO",birthdate1,"joao@domain.com","914047935","324666433");
		u2 = userService.createUser("PEDRO",birthdate2,"pedro@gmail.uk","915557911","123555433");
		
		u1.addContract(new Contract(800,25));
		u1.getLastContract().setStartDate(LocalDate.of(2017,10, 1));
		u1.getLastContract().closeAt(LocalDate.of(2018,2, 1));
		u1.addContract(new Contract(800,55));
		u1.getLastContract().setStartDate(LocalDate.of(2018,3, 1));
		
		u2.addContract(new Contract(500,75));
		u2.getLastContract().setStartDate(LocalDate.of(2018,2, 1));
		
		userService.addUser(u1);
		userService.addUser(u2);
		
		u1 = userService.findUserByEmail("joao@domain.com");
		u2 = userService.findUserByEmail("pedro@gmail.uk");
		
		//add 2 customers
		c1 = bookingCustomerService.createCustomer("Joao",birthdate1,"Mangualde","914047935");
		c2 = bookingCustomerService.createCustomer("Ana",birthdate2,"Porto","966677722");
		bookingCustomerService.addCustomer(c1);
		bookingCustomerService.addCustomer(c2);
		c1 = bookingCustomerService.findCustomerById(1);
		c2 = bookingCustomerService.findCustomerById(2);
		
		//add 2 products
		p1 = productService.createProduct("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = productService.createProduct("CORTE SIMPLES",productType.HAIRCUT,10);
		productService.addProduct(p1);
		productService.addProduct(p2);
		p1 = productService.findProductById(1);
		p2 = productService.findProductById(2);

		
		d1 = LocalDateTime.of(2018, 3, 11,10,30);
		d2 = LocalDateTime.of(2018, 3, 12,11,35);
		d3 = LocalDateTime.of(2018, 3, 12,12,15);
		d4 = LocalDateTime.of(2018, 2, 22,15,15);
		
		cash = new PaymentMethod("CASH",0.0,0.0);
		credit = new PaymentMethod("CREDIT", 0.5, 0.5);
		saleService.addPaymentMethod(cash);
		saleService.addPaymentMethod(credit);
		cash = saleService.findPaymentMethodById("CASH");
		credit = saleService.findPaymentMethodById("CREDIT");
		
		s1 = saleService.createSale(d1,c1,p1,cash,u1);
		s2 = saleService.createSale(d2,c2,p2,credit,u1);
		s3 = saleService.createSale(d3,p1,cash,u2);
		s4 = saleService.createSale(d4,c1,p2,cash,u1);
		
		System.out.println(saleService.getSales());
		System.out.println(s1);

	}

	
	/**
	 * testListAllSales() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service</p>
	 * <p>WHEN: call listAllSales() controller</p>
	 * <p>THEN: Get a list of those sales</p>
	 */
	@Test
	public void testListAllSales() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listAllSales();
		
		//THEN
		expectedList.add(saleService.findSaleById(1).toRestDTO());
		expectedList.add(saleService.findSaleById(2).toRestDTO());
		expectedList.add(saleService.findSaleById(3).toRestDTO());
		expectedList.add(saleService.findSaleById(4).toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(expectedList,responseList.getBody());

	}

	/**
	 * testAddSale() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service</p>
	 * <p>WHEN: call AddSale() controller filled with valid Date and Valid product </p>
	 * <p>THEN: The list of those sales has increased to 5</p>
	 */
	@Test
	public void testAddSaleSuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		SaleRestDTO newSale = new SaleRestDTO();
		newSale.setDate(LocalDateTime.of(2018, 5, 5,10,10));
		newSale.setProductId(1);
		response = src.addSale(newSale);
		
		//THEN
		assertEquals(saleService.getSales().size(),5);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}
	
	/**
	 * testAddSale() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service</p>
	 * <p>WHEN: call AddSale() controller filled with valid Date but invalid product </p>
	 * <p>THEN: The list of those sales still 4 and returned value is BAD_REQUEST</p>
	 */
	@Test
	public void testAddSaleInvalidProduct() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		SaleRestDTO newSale = new SaleRestDTO();
		newSale.setDate(LocalDateTime.of(2018, 5, 5,10,10));
		newSale.setProductId(11);
		response = src.addSale(newSale);
		
		//THEN
		assertEquals(saleService.getSales().size(),4);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}
	
	/**
	 * testAddSale() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service</p>
	 * <p>WHEN: call AddSale() controller filled with valid product but invalid date </p>
	 * <p>THEN: The list of those sales still 4 and returned value is BAD_REQUEST</p>
	 */
	@Test
	public void testAddSaleInvalidDate() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		SaleRestDTO newSale = new SaleRestDTO();

		newSale.setProductId(1);
		response = src.addSale(newSale);
		
		//THEN
		assertEquals(saleService.getSales().size(),4);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	/**
	 * testFindSaleById() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service</p>
	 * <p>WHEN: call findSaleById() controller filled with valid ID </p>
	 * <p>THEN: Get response code OK and respective saleRestDTO</p>
	 */
	@Test
	public void testFindSaleByIdSuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		SaleRestDTO sale = saleService.findSaleById(1).toRestDTO();
		response = src.findSaleById(1);
		
		//THEN	
		assertEquals(HttpStatus.OK, response.getStatusCode());	
		assertEquals(sale,response.getBody());
	}
	
	/**
	 * testFindSaleById() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service</p>
	 * <p>WHEN: call findSaleById() controller filled with valid ID </p>
	 * <p>THEN: Get response code OK and respective saleRestDTO</p>
	 */
	@Test
	public void testFindSaleByIdNotFound() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		response = src.findSaleById(11);
		
		//THEN	
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
	
	}

	
	/**
	 * testListSalesOfCustomer() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different customers</p>
	 * <p>WHEN: call listSaleOfCustomer() controller filled with valid customerID </p>
	 * <p>THEN: Get response code OK and respective list of saleRestDTO</p>
	 */
	@Test
	public void testListSalesOfCustomerSuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfCustomer(1);
		
		//THEN
		expectedList.add(saleService.findSaleById(1).toRestDTO());
		expectedList.add(saleService.findSaleById(4).toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(expectedList,responseList.getBody());		
	}
	
	/**
	 * testListSalesOfCustomer() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different customers</p>
	 * <p>WHEN: call listSaleOfCustomer() controller filled with invalid customerID </p>
	 * <p>THEN: Get response code NOT_FOUND because of unknown customerId</p>
	 */
	@Test
	public void testListSalesOfCustomerNotFound() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfCustomer(12);
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,responseList.getStatusCode());
	}

	
	/**
	 * testListSalesOfUser() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different users</p>
	 * <p>WHEN: call listSaleOfCustomer() controller filled with valid userID </p>
	 * <p>THEN: Get response code OK and respective list of saleRestDTO</p>
	 */
	@Test
	public void testListSalesOfUserSuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfUser("joao@domain.com");
		
		//THEN
		expectedList.add(saleService.findSaleById(1).toRestDTO());
		expectedList.add(saleService.findSaleById(2).toRestDTO());
		expectedList.add(saleService.findSaleById(4).toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(expectedList,responseList.getBody());		
	}
	
	
	/**
	 * testListSalesOfUser() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different users</p>
	 * <p>WHEN: call listSaleOfCustomer() controller filled with invalid userID </p>
	 * <p>THEN: Get response code NOT_FOUND</p>
	 */
	@Test
	public void testListSalesOfUserInvalidEmail() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfUser("invalid@gmail.com");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,responseList.getStatusCode());
	
	}

	
	/**
	 * testListSalesOfDay() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSaleOfDay() controller filled with valid date </p>
	 * <p>THEN: Get response code OK and respective list of saleRestDTO</p>
	 */
	@Test
	public void testListSalesOfDaySuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfDay("2018-03-12");
		
		//THEN
		expectedList.add(saleService.findSaleById(2).toRestDTO());
		expectedList.add(saleService.findSaleById(3).toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(expectedList,responseList.getBody());		
	}
	
	/**
	 * testListSalesOfDay() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSaleOfDay() controller filled with invalid date format </p>
	 * <p>THEN: Get response code BAD_REQUEST</p>
	 */
	@Test
	public void testListSalesOfDayInvalidDateFormat() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfDay("2018/03-12");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,responseList.getStatusCode());
	
	}
	
	/**
	 * testListSalesOfDay() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSaleOfDay() controller filled with invalid date value(future dates) </p>
	 * <p>THEN: Get response code BAD_REQUEST</p>
	 */
	@Test
	public void testListSalesOfDayInvalidDateValue() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfDay("2035-03-12");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,responseList.getStatusCode());
	
	}

	
	/**
	 * testListSalesOfMonth() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSaleOfMonth() controller filled with valid date </p>
	 * <p>THEN: Get response code OK and respective list of saleRestDTO</p>
	 */
	@Test
	public void testListSalesOfMonthSuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfMonth("2018-03");
		
		//THEN
		expectedList.add(saleService.findSaleById(1).toRestDTO());
		expectedList.add(saleService.findSaleById(2).toRestDTO());
		expectedList.add(saleService.findSaleById(3).toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(expectedList,responseList.getBody());		
	}
	
	/**
	 * testListSalesOfMonth() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSaleOfMonth() controller filled with invalid date format </p>
	 * <p>THEN: Get response code BAD_REQUEST</p>
	 */
	@Test
	public void testListSalesOfMonthInvalidDateFormat() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfMonth("2018/03");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,responseList.getStatusCode());	
	}
	
	/**
	 * testListSalesOfMonth() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSaleOfMonth() controller filled with invalid date value (future dates) </p>
	 * <p>THEN: Get response code BAD_REQUEST</p>
	 */
	@Test
	public void testListSalesOfMonthInvalidDateValue() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesOfMonth("2035-03");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,responseList.getStatusCode());	
	}

	
	/**
	 * testListSalesBetweenDates() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSalesBetweenDates() controller filled with valid dates </p>
	 * <p>THEN: Get response code OK and respective list of saleRestDTO</p>
	 */
	@Test
	public void testListSalesBetweenDatesSuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesBetweenDates("2018-03-11","2018-03-12");
		
		//THEN
		expectedList.add(saleService.findSaleById(1).toRestDTO());
		expectedList.add(saleService.findSaleById(2).toRestDTO());
		expectedList.add(saleService.findSaleById(3).toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(expectedList,responseList.getBody());				
	}
	
	/**
	 * testListSalesBetweenDates() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSalesBetweenDates() controller filled with invalid date formats </p>
	 * <p>THEN: Get response code BAD_REQUEST</p>
	 */
	@Test
	public void testListSalesBetweenDatesInvalidDatesFormat() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesBetweenDates("2018/03-11","2018-03-12");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,responseList.getStatusCode());		
	}
	
	/**
	 * testListSalesBetweenDates() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSalesBetweenDates() controller filled with invalid dates values </p>
	 * <p>THEN: Get response code BAD_REQUEST</p>
	 */
	@Test
	public void testListSalesBetweenDatesInvalidDatesValues() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesBetweenDates("2018-03-13","2018-03-12");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,responseList.getStatusCode());		
	}
	
	/**
	 * testListSalesBetweenDates() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service of different days</p>
	 * <p>WHEN: call listSalesBetweenDates() controller filled with invalid dates values </p>
	 * <p>THEN: Get response code BAD_REQUEST</p>
	 */
	@Test
	public void testListSalesBetweenDatesInvalidDatesValues2() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesBetweenDates("2038-08-13","2038-11-12");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,responseList.getStatusCode());		
	}

	
	/**
	 * testListSalesPayedBy() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service payed by different paymentMethods</p>
	 * <p>WHEN: call listSalesPayedBy() controller filled with valid payment method </p>
	 * <p>THEN: Get response code OK and respective list of saleRestDTO</p>
	 */
	@Test
	public void testListSalesPayedBySuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesPayedBy("CASH");
		
		//THEN
		expectedList.add(saleService.findSaleById(1).toRestDTO());
		expectedList.add(saleService.findSaleById(3).toRestDTO());
		expectedList.add(saleService.findSaleById(4).toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(expectedList,responseList.getBody());			
	}
	
	
	/**
	 * testListSalesPayedBy() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service payed by different paymentMethods</p>
	 * <p>WHEN: call listSalesPayedBy() controller filled with invalid payment method </p>
	 * <p>THEN: Get response code NOT_FOUND</p>
	 */
	@Test
	public void testListSalesPayedByNotFound() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		responseList = src.listSalesPayedBy("VISA");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,responseList.getStatusCode());	
	}
	
	/**
	 * testDeleteSaleById() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service</p>
	 * <p>WHEN: call deleteSaleById() controller with valid ID</p>
	 * <p>THEN: Get a response code OK and sales list has decreased to 3</p>
	 */
	@Test
	public void testDeleteSaleByIdSuccess() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		response = src.deleteSaleById(1);
		
		//THEN
	
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(saleService.getSales().size(),3);

	}
	
	/**
	 * testDeleteSaleById() controller
	 * 
	 * <p>GIVEN: Added 4 sales to service</p>
	 * <p>WHEN: call deleteSaleById() controller with invalid ID</p>
	 * <p>THEN: Get a response code OK and sales still the same size</p>
	 */
	@Test
	public void testDeleteSaleByIdNotFound() {
		//GIVEN
		assertEquals(saleService.getSales().isEmpty(),true);
		assertEquals(saleService.addSale(s1),true);
		assertEquals(saleService.addSale(s2),true);
		assertEquals(saleService.addSale(s3),true);
		assertEquals(saleService.addSale(s4),true);
		assertEquals(saleService.getSales().size(),4);
		
		//WHEN
		response = src.deleteSaleById(11);
		
		//THEN
	
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
		assertEquals(saleService.getSales().size(),4);

	}


}

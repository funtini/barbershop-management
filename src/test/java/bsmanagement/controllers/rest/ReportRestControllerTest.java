package bsmanagement.controllers.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bsmanagement.dto.rest.ExpenseRestDTO;
import bsmanagement.dto.rest.ReportRestDTO;
import bsmanagement.dto.rest.SaleRestDTO;
import bsmanagement.jparepositories.classtests.BookingRepositoryClass;
import bsmanagement.jparepositories.classtests.CustomerRepositoryClass;
import bsmanagement.jparepositories.classtests.ExpenseRepositoryClass;
import bsmanagement.jparepositories.classtests.PaymentRepositoryClass;
import bsmanagement.jparepositories.classtests.ProductRepositoryClass;
import bsmanagement.jparepositories.classtests.ReportRepositoryClass;
import bsmanagement.jparepositories.classtests.SaleRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;
import bsmanagement.model.Expense;
import bsmanagement.model.ExpenseService;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.ProductService;
import bsmanagement.model.Report;
import bsmanagement.model.ReportSaleExpenseService;
import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.Product.productType;

public class ReportRestControllerTest {
	
	UserService userService;
	ProductService prodService;
	SaleService saleService;
	ExpenseService expenseService;
	BookingCustomerService bookingCustomerService;
	SaleRepositoryClass saleRepository;
	ExpenseRepositoryClass expenseRepository;
	ReportRepositoryClass reportRepository;
	UserRepositoryClass userRepository;
	ProductRepositoryClass productRepository;
	BookingRepositoryClass bookRepository;
	CustomerRepositoryClass customerRepository;
	ReportSaleExpenseService repSaleExpService;
	PaymentRepositoryClass paymentRepository;
	
	ReportRestController rrc;
	
	YearMonth ym17;
	YearMonth ym18;
	LocalDateTime dt1;
	LocalDateTime dt2;
	LocalDateTime dt3;
	LocalDateTime dt4;
	LocalDate birthdate1;
	LocalDate birthdate2;
	LocalDate d1;
	LocalDate d2;
	LocalDate d3;
	LocalDate d4;
	LocalDate d5;
	Product p1;
	Product p2;
	Customer c1;
	Customer c2;
	Expense e1;
	Expense e2;
	Expense e3;
	Expense e4;
	PaymentMethod cash;
	PaymentMethod card;
	Sale s1;
	Sale s2;
	Sale s3;
	Sale s4;
	User u1;
	User u2;
	
	Report r1;
	Report r2;
	Report r3;
	
	ResponseEntity<ReportRestDTO> response;
	ResponseEntity<List<ReportRestDTO>> responseList;
	ResponseEntity<ReportRestDTO> expect;
	List<ReportRestDTO> expenseList;

	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Date [d1] : 05/03/2018 </p>
	 * <p>Date [d2] : 10/04/2018 </p>
	 * <p>ExpenseType [et1] : FIXED </p>
	 * <p>ExpenseType [et2] : ONE-OFF</p>
	 * 
	 * <p>Payment [cash] -> ['CASH',0,0] </p>
	 * <p>Payment [card] -> ['CREDIT CARD',0,0] </p>
	 * 
	 * <p>Expense [e1] : ["Agua",'FIXED','35'] </p>
	 * <p>Expense [e2] : ["Internet",'FIXED','50',d2,"6 meses de contrato"] </p>
	 * <p>Expense [e3] : ["Secadores",'ONE-OFF','80',d2,"3 unidades"] </p>
	 * <p>Expense [e4] : ["Shampoos",'ONE-OFF','50',d2,"3 unidades"] </p>
	 * 
	 */
	@Before
	public void setUp(){
		userService = new UserService();
		prodService = new ProductService();
		expenseService = new ExpenseService();
		bookingCustomerService = new BookingCustomerService();
		repSaleExpService = new ReportSaleExpenseService();
		saleService = new SaleService();
		
		expenseRepository = new ExpenseRepositoryClass();
		saleRepository = new SaleRepositoryClass();
		reportRepository = new ReportRepositoryClass();
		userRepository = new UserRepositoryClass();
		productRepository = new ProductRepositoryClass();
		bookRepository = new BookingRepositoryClass();
		customerRepository = new CustomerRepositoryClass();
		paymentRepository = new PaymentRepositoryClass();
		expenseList = new ArrayList<>();
	
		
		userService.setUserRepository(userRepository);
		repSaleExpService.setExpRepo(expenseRepository);
		repSaleExpService.setReportRepo(reportRepository);
		repSaleExpService.setSaleRepo(saleRepository);
		expenseService.setRepository(expenseRepository);
		prodService.setRepository(productRepository);
		saleService.setSaleRepository(saleRepository);
		saleService.setPaymentRepository(paymentRepository);
		bookingCustomerService.setBookRepository(bookRepository);
		bookingCustomerService.setCustomersRepository(customerRepository);

		rrc = new ReportRestController(repSaleExpService, prodService, bookingCustomerService, userService, expenseService,saleService);
		
		Expense.setStartIdGenerator(1);
		Sale.setStartIdGenerator(1);
		Product.setStartIdGenerator(1);
		Customer.setStartIdGenerator(1);
		
		ym17 = YearMonth.of(2017, 12);
		ym18= YearMonth.of(2018, 1);
		
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
	
		
		u1 = new User("JOAO",birthdate1,"joao@domain.com","914047935","324666433");
		u2 = new User("PEDRO",birthdate2,"pedro@hotmail.com","914047935","324666433");
		userService.addUser(u1);
		userService.addUser(u2);

		repSaleExpService.addReport(ym17);
		repSaleExpService.addReport(ym18);
		
		r1 = repSaleExpService.getReport(ym17);
		r2 = repSaleExpService.getReport(ym18);
		
		repSaleExpService.refreshReportStatus();
		repSaleExpService.closeReport(r1);
		repSaleExpService.closeReport(r2);
		
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		prodService.addProduct(p1);
		prodService.addProduct(p2);
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");	
		bookingCustomerService.addCustomer(c1);
		bookingCustomerService.addCustomer(c2);
		
		cash = new PaymentMethod("CASH",0.0,0.0);
		card = new PaymentMethod("CASH",1.5,0.5);
		
		saleService.addPaymentMethod(cash);
		saleService.addPaymentMethod(card);
		
		dt1 = LocalDateTime.now();
		dt2 = LocalDateTime.now();
		dt3 = LocalDateTime.now();
		dt4 = LocalDateTime.now();
		
		d1 = LocalDate.now();
		d2 = LocalDate.now();
		d3 = LocalDate.now();
		d4 = LocalDate.now();
		d5 = LocalDate.now();
		
		e1 = expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null);
		e2 = expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
		e3 = expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades");
		
		s1 = saleService.createSale(dt1, c1,p1,cash,u1);

		
		repSaleExpService.addSale(s1);
		r3 = repSaleExpService.getReport(YearMonth.now());
		
	}
	
	/**
	 * Smoke test to all services in use on Report Rest Controller
	 * 
	 */
	@Test
	public void smokeTestUserService() {
		assertThat(userService).isNotNull();
		assertThat(prodService).isNotNull();
		assertThat(bookingCustomerService).isNotNull();
		assertThat(repSaleExpService).isNotNull();
		assertThat(expenseService).isNotNull();
		assertThat(saleService).isNotNull();
		assertThat(paymentRepository).isNotNull();
	}
	
	/**
	 * testListAllReports() controller
	 * 
	 * <p>GIVEN: 3 reports added to service </p>
	 * <p>WHEN: get a list of all reports</p>
	 * <p>THEN: controller return response code OK with respective list of ReportRestDTO in body</p>
	 */
	@Test
	public void testListAllReports() {
		//GIVEN
		assertEquals(repSaleExpService.getAllReports().size(),3);
		
		//WHEN
		responseList = rrc.listAllReports();
		
		//THEN
		expenseList.add(r1.toRestDTO());
		expenseList.add(r2.toRestDTO());
		expenseList.add(r3.toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(responseList.getBody().size(),3);
		assertEquals(expenseList,responseList.getBody());
	}

	/**
	 * testListOpenReports() controller
	 * 
	 * <p>GIVEN: 3 reports added to service which one is opened </p>
	 * <p>WHEN: get a list of open reports</p>
	 * <p>THEN: controller return response code OK with respective list of ReportRestDTO in body</p>
	 */
	@Test
	public void testListOpenReports() {
		//GIVEN
		assertEquals(repSaleExpService.getAllReports().size(),3);
		
		//WHEN
		responseList = rrc.listOpenReports();
		
		//THEN
		expenseList.add(r3.toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(responseList.getBody().size(),1);
		assertEquals(expenseList,responseList.getBody());		
	}

	
	/**
	 * testListClosedReports() controller
	 * 
	 * <p>GIVEN: 3 reports added to service which two are closed </p>
	 * <p>WHEN: get a list of closed reports</p>
	 * <p>THEN: controller return response code OK with respective list of ReportRestDTO in body</p>
	 */
	@Test
	public void testListClosedReports() {
		//GIVEN
		assertEquals(repSaleExpService.getAllReports().size(),3);
		
		//WHEN
		responseList = rrc.listClosedReports();
		
		//THEN
		expenseList.add(r1.toRestDTO());
		expenseList.add(r2.toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(responseList.getBody().size(),2);
		assertEquals(expenseList,responseList.getBody());	
	}

	
	/**
	 * testGetReportById() controller
	 * 
	 * <p>GIVEN: 3 reports added to service </p>
	 * <p>WHEN: get a report by id</p>
	 * <p>THEN: controller return response code OK with respective ReportRestDTO in body</p>
	 */
	@Test
	public void testGetReportByIdSuccess() {
		//GIVEN
		assertEquals(repSaleExpService.getAllReports().size(),3);
		
		//WHEN
		response = rrc.getReportById("2017-12");
		
		//THEN
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(r1.toRestDTO(),response.getBody());		
	}
	
	/**
	 * testGetReportById() controller
	 * 
	 * <p>GIVEN: 3 reports added to service </p>
	 * <p>WHEN: get a report by id that doesnt exist</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testGetReportByIdNotFound() {
		//GIVEN
		assertEquals(repSaleExpService.getAllReports().size(),3);
		
		//WHEN
		response = rrc.getReportById("2018-05");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());		
	}
	
	/**
	 * testGetReportById() controller
	 * 
	 * <p>GIVEN: 3 reports added to service </p>
	 * <p>WHEN: get a report by invalid ID</p>
	 * <p>THEN: controller return response code BAD REQUEST</p>
	 */
	@Test
	public void testGetReportByIdBadRequest() {
		//GIVEN
		assertEquals(repSaleExpService.getAllReports().size(),3);
		
		//WHEN
		response = rrc.getReportById("2018/0001");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());		
	}

	/**
	 * testAddSale() controller
	 * 
	 * <p>GIVEN: 1 sale added to service </p>
	 * <p>WHEN: add new sale</p>
	 * <p>THEN: controller return response code CREATED and respective saleRestDTO in body</p>
	 */
	@Test
	public void testAddSale() {
		//GIVEN
		assertEquals(saleService.getSales().size(),1);
		assertEquals(r3.getSales().size(),1);
		
		//WHEN
		SaleRestDTO newSale = new SaleRestDTO();
		newSale.setDate(LocalDateTime.now());
		newSale.setProductId(1);
		ResponseEntity<SaleRestDTO> respSale = rrc.addSale(newSale);
		
		//THEN
		assertEquals(saleService.getSales().size(),2);
		assertEquals(repSaleExpService.getCurrentOpenReport().getSales().size(),2);

		assertEquals(respSale.getBody(),saleService.findSaleById(2).toRestDTO());
		assertEquals(respSale.getStatusCode(),HttpStatus.CREATED);
		
	}
	
	
	/**
	 * testAddSale() controller
	 * 
	 * <p>GIVEN: 1 sale added to service </p>
	 * <p>WHEN: add sale with invalid product id</p>
	 * <p>THEN: controller return response code BAD REQUEST and sales size still 1</p>
	 */
	@Test
	public void testAddSaleBadRequestInvalidProduct() {
		//GIVEN
		assertEquals(saleService.getSales().size(),1);
		assertEquals(r3.getSales().size(),1);
		
		//WHEN
		SaleRestDTO newSale = new SaleRestDTO();
		newSale.setDate(LocalDateTime.of(2018, 5, 5,10,10));
		newSale.setProductId(11);
		ResponseEntity<SaleRestDTO> respSale = rrc.addSale(newSale);
		
		//THEN
		assertEquals(respSale.getStatusCode(),HttpStatus.BAD_REQUEST);
		assertEquals(saleService.getSales().size(),1);
		assertEquals(repSaleExpService.getCurrentOpenReport().getSales().size(),1);

		
		
	}
	
	/**
	 * testAddSale() controller
	 * 
	 * <p>GIVEN: 1 sale added to service </p>
	 * <p>WHEN: add sale with invalid DATE</p>
	 * <p>THEN: controller return response code BAD REQUEST</p>
	 */
	@Test
	public void testAddSaleBadRequestInvalidDate() {
		//GIVEN
		assertEquals(saleService.getSales().size(),1);
		assertEquals(r3.getSales().size(),1);
		
		//WHEN
		SaleRestDTO newSale = new SaleRestDTO();
		newSale.setProductId(1);
		ResponseEntity<SaleRestDTO> respSale = rrc.addSale(newSale);
		
		//THEN
		assertEquals(respSale.getStatusCode(),HttpStatus.BAD_REQUEST);
		assertEquals(saleService.getSales().size(),1);
		assertEquals(repSaleExpService.getCurrentOpenReport().getSales().size(),1);
		
	}

	
	/**
	 * testAddExpense() controller
	 * 
	 * <p>GIVEN: empty expense service </p>
	 * <p>WHEN: add expense to current month</p>
	 * <p>THEN: controller return response code CREATED and respective expenseRestDTO in Body</p>
	 */
	@Test
	public void testAddExpenseSuccess() {
		//GIVEN
		assertEquals(expenseService.getExpenses().size(),0);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		
		//WHEN
		ExpenseRestDTO expenseDTO = new ExpenseRestDTO();
		expenseDTO.setName("Expense Test");
		expenseDTO.setType("fiXed");
		expenseDTO.setValueOfPayment(50);
		expenseDTO.setDateOfPayment(LocalDate.now());
		ResponseEntity<ExpenseRestDTO> respExp = rrc.addExpense(expenseDTO);
		
		//THEN
		assertEquals(expenseService.getExpenses().size(),1);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),1);
		assertEquals(respExp.getStatusCode(),HttpStatus.CREATED);
	}
	
	
	/**
	 * testAddExpense() controller
	 * 
	 * <p>GIVEN: empty expense service</p>
	 * <p>WHEN: call listAddExpense() controller with invalid input name</p>
	 * <p>THEN: the list of expenses still empty</p>
	 */
	@Test
	public void testAddExpenseBadRequestEmptyName() {
		//GIVEN
		assertEquals(expenseService.getExpenses().size(),0);
		
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		
		//WHEN
		ExpenseRestDTO expenseDTO = new ExpenseRestDTO();
		expenseDTO.setType("fiXed");
		expenseDTO.setValueOfPayment(50);
		expenseDTO.setDateOfPayment(LocalDate.now());
		ResponseEntity<ExpenseRestDTO> respExp = rrc.addExpense(expenseDTO);
		
		//THEN
		assertEquals(expenseService.getExpenses().size(),0);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		assertEquals(respExp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * testAddExpense() controller
	 * 
	 * <p>GIVEN: empty expense service</p>
	 * <p>WHEN: call listAddExpense() controller with invalid input value of Payment</p>
	 * <p>THEN: the list of expenses still empty</p>
	 */
	@Test
	public void testAddExpenseBadRequestEmptyValue() {
		//GIVEN
		assertEquals(expenseService.getExpenses().size(),0);
		
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		
		//WHEN
		ExpenseRestDTO expenseDTO = new ExpenseRestDTO();
		expenseDTO.setName("Expense Test");
		expenseDTO.setType("fiXed");
		expenseDTO.setDateOfPayment(LocalDate.now());
		ResponseEntity<ExpenseRestDTO> respExp = rrc.addExpense(expenseDTO);
		
		//THEN
		assertEquals(expenseService.getExpenses().size(),0);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		assertEquals(respExp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * testAddExpense() controller
	 * 
	 * <p>GIVEN: empty expense service</p>
	 * <p>WHEN: call listAddExpense() controller with invalid input type of Expense</p>
	 * <p>THEN: the list of expenses still empty</p>
	 */
	@Test
	public void testAddExpenseBadRequestEmptyType() {
		//GIVEN
		assertEquals(expenseService.getExpenses().size(),0);
		
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		
		//WHEN
		ExpenseRestDTO expenseDTO = new ExpenseRestDTO();
		expenseDTO.setName("Expense Test");
		expenseDTO.setValueOfPayment(50);
		expenseDTO.setDateOfPayment(LocalDate.now());
		ResponseEntity<ExpenseRestDTO> respExp = rrc.addExpense(expenseDTO);
		
		//THEN
		assertEquals(expenseService.getExpenses().size(),0);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		assertEquals(respExp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * testAddExpense() controller
	 * 
	 * <p>GIVEN: empty expense service</p>
	 * <p>WHEN: call listAddExpense() controller with invalid input type of Expense</p>
	 * <p>THEN: the list of expenses still empty</p>
	 */
	@Test
	public void testAddExpenseBadRequestInvalidType() {
		//GIVEN
		assertEquals(expenseService.getExpenses().size(),0);
		
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		
		//WHEN
		ExpenseRestDTO expenseDTO = new ExpenseRestDTO();
		expenseDTO.setName("Expense Test");
		expenseDTO.setType("Invalid Type");
		expenseDTO.setValueOfPayment(50);
		expenseDTO.setDateOfPayment(LocalDate.now());
		ResponseEntity<ExpenseRestDTO> respExp = rrc.addExpense(expenseDTO);
		
		//THEN
		assertEquals(expenseService.getExpenses().size(),0);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		assertEquals(respExp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * testAddExpense() controller
	 * 
	 * <p>GIVEN: empty expense service</p>
	 * <p>WHEN: call listAddExpense() controller with invalid input date of Expense</p>
	 * <p>THEN: the list of expenses still empty</p>
	 */
	@Test
	public void testAddExpenseBadRequestInvalidDate() {
		//GIVEN
		assertEquals(expenseService.getExpenses().size(),0);
		
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		
		//WHEN
		ExpenseRestDTO expenseDTO = new ExpenseRestDTO();
		expenseDTO.setName("Expense Test");
		expenseDTO.setType("fixed");
		expenseDTO.setValueOfPayment(50);
		ResponseEntity<ExpenseRestDTO> respExp = rrc.addExpense(expenseDTO);
		
		//THEN
		assertEquals(expenseService.getExpenses().size(),0);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),0);
		assertEquals(respExp.getStatusCode(),HttpStatus.BAD_REQUEST);
	}

	/**
	 * testRemoveExpense() controller
	 * 
	 * <p>GIVEN: Added 3 Expenses to service</p>
	 * <p>WHEN: call removeExpense() controller with invalid Id</p>
	 * <p>THEN: return response code OK and the list of expenses has decreased to 2</p>
	 */
	@Test
	public void testRemoveExpenseSuccess() {
		//GIVEN
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		repSaleExpService.addExpense(e1);
		repSaleExpService.addExpense(e2);
		repSaleExpService.addExpense(e3);
		assertEquals(expenseService.getExpenses().size(),3);
		//WHEN
		ResponseEntity<ExpenseRestDTO> respExp = rrc.removeExpense(3);
		//THEN
		assertEquals(expenseService.getExpenses().size(),2);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),2);
		assertEquals(respExp.getStatusCode(),HttpStatus.OK);		
	}
	
	
	/**
	 * testRemoveExpense() controller
	 * 
	 * <p>GIVEN: Added 3 Expenses to service</p>
	 * <p>WHEN: call removeExpense() controller with invalid Id</p>
	 * <p>THEN: the list of expenses still 4 and response returned is NOT_FOUND</p>
	 */
	@Test
	public void testRemoveExpenseNotFound() {
		//GIVEN
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		repSaleExpService.addExpense(e1);
		repSaleExpService.addExpense(e2);
		repSaleExpService.addExpense(e3);
		assertEquals(expenseService.getExpenses().size(),3);
		//WHEN
		ResponseEntity<ExpenseRestDTO> respExp = rrc.removeExpense(13);
		//THEN
		assertEquals(expenseService.getExpenses().size(),3);
		assertEquals(repSaleExpService.getCurrentOpenReport().getExpenses().size(),3);
		assertEquals(respExp.getStatusCode(),HttpStatus.NOT_FOUND);		
	}


}

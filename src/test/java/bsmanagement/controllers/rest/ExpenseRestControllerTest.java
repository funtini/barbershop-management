package bsmanagement.controllers.rest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bsmanagement.dto.rest.ExpenseRestDTO;
import bsmanagement.jparepositories.classtests.ExpenseRepositoryClass;
import bsmanagement.model.Expense;
import bsmanagement.model.ExpenseService;
import bsmanagement.model.Expense.expenseType;

public class ExpenseRestControllerTest {
	
	ExpenseRestController erc;
	LocalDate d1;
	LocalDate d2;
	LocalDate d3;
	
	Expense e1;
	Expense e2;
	Expense e3;
	Expense e4;
	
	ResponseEntity<ExpenseRestDTO> response;
	List<ExpenseRestDTO> expectedList;
	ExpenseRestDTO expenseDTO;
	ResponseEntity<List<ExpenseRestDTO>> responseList;
	ExpenseService expenseService;
	ExpenseRepositoryClass expenseRepository;

	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Date [d1] : 05/03/2018 </p>
	 * <p>Date [d2] : 10/04/2018 </p>
	 * <p>Date [d3] : 15/04/2018 </p>
	 * <p>ExpenseType [et1] : FIXED </p>
	 * <p>ExpenseType [et2] : ONE-OFF</p>
	 * 
	 * <p>Expense [e1] : ["Agua",'FIXED','35', d1] </p>
	 * <p>Expense [e2] : ["Internet",'FIXED','50',d2,"6 meses de contrato"] </p>
	 * <p>Expense [e3] : ["Secadores",'ONE-OFF','90',d3,"3 unidades"] </p>
	 * <p>Expense [e4] : ["Shampoos",'ONE-OFF','60',d2,"10 unidades"] </p>
	 * 
	 * 
	 * 
	 */
	@Before
	public void setUp(){
		
		
		expectedList = new ArrayList<>();

		expenseDTO = new ExpenseRestDTO();
		expenseService = new ExpenseService();
		expenseRepository = new ExpenseRepositoryClass();
		expenseService.setRepository(expenseRepository);

		erc = new ExpenseRestController(expenseService);
		expectedList.clear();
		
		Expense.setStartIdGenerator(1);
		
		d1 = LocalDate.of(2018, 3, 5);
		d2 = LocalDate.of(2018, 4, 10);
		d3 = LocalDate.of(2018, 4, 15);
		
		e1 = new Expense("Agua",expenseType.FIXED,35,d1);
		e2 = new Expense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
		e3 = new Expense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades");
		e4 = new Expense("Shampoos",expenseType.ONEOFF,60,d2,"10 unidades");	
		
	}

	
	/**
	 * testListAllExpenses() controller
	 * 
	 * <p>GIVEN: Added 4 Expenses to service</p>
	 * <p>WHEN: call listAllExpenses() controller</p>
	 * <p>THEN: Get a list of those expenses</p>
	 */
	@Test
	public void testListAllExpenses() {
		//GIVEN
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		expenseService.addExpense(e1);
		expenseService.addExpense(e2);
		expenseService.addExpense(e3);
		expenseService.addExpense(e4);
		assertEquals(expenseService.getExpenses().size(),4);
		//WHEN
		responseList = erc.listAllExpenses();
		//THEN
		expectedList.add(expenseService.findExpenseById(1).toRestDTO());
		expectedList.add(expenseService.findExpenseById(2).toRestDTO());
		expectedList.add(expenseService.findExpenseById(3).toRestDTO());
		expectedList.add(expenseService.findExpenseById(4).toRestDTO());
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(responseList.getBody(),expectedList);

	}

	
	/**
	 * testListCurrentMonthExpenses() controller
	 * 
	 * <p>GIVEN: Added 6 Expenses to service, 2 of them of current month</p>
	 * <p>WHEN: call listCurrentMonthExpenses() controller</p>
	 * <p>THEN: Get a list of those 2 expenses</p>
	 */
	@Test
	public void testListCurrentMonthExpenses() {
		//GIVEN
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		expenseService.addExpense(e1);
		expenseService.addExpense(e2);
		expenseService.addExpense(e3);
		expenseService.addExpense(e4);
		expenseService.addExpense(expenseService.createExpense("Expense ONEOFF", expenseType.ONEOFF, 55, LocalDate.now(), null));
		expenseService.addExpense(expenseService.createExpense("Expense FIXED", expenseType.FIXED, 55, LocalDate.now(), null));
		assertEquals(expenseService.getExpenses().size(),6);
		//WHEN
		responseList = erc.listCurrentMonthExpenses();
		//THEN
		expectedList.add(expenseService.findExpenseById(5).toRestDTO());
		expectedList.add(expenseService.findExpenseById(6).toRestDTO());
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(responseList.getBody(),expectedList);		
	}

	
	/**
	 * testListExpensesOfMonth() controller
	 * 
	 * <p>GIVEN: Added 4 Expenses to service, 3 of them of month 2018/04</p>
	 * <p>WHEN: call listExpensesOfMonth() controller</p>
	 * <p>THEN: Get a list of those 3 expenses</p>
	 */
	@Test
	public void testListExpensesOfMonthSuccess() {
		//GIVEN
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		expenseService.addExpense(e1);
		expenseService.addExpense(e2);
		expenseService.addExpense(e3);
		expenseService.addExpense(e4);
		assertEquals(expenseService.getExpenses().size(),4);
		//WHEN
		responseList = erc.listExpensesOfMonth("2018-04");
		//THEN
		expectedList.add(expenseService.findExpenseById(2).toRestDTO());
		expectedList.add(expenseService.findExpenseById(3).toRestDTO());
		expectedList.add(expenseService.findExpenseById(4).toRestDTO());
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(responseList.getBody(),expectedList);		
	}
	
	/**
	 * testListExpensesOfMonth() controller
	 * 
	 * <p>GIVEN: Added 4 Expenses to service, 3 of them of month 2018/04</p>
	 * <p>WHEN: call listExpensesOfMonth() controller with invalid input date</p>
	 * <p>THEN: Get a a response code BAD_REQUEST</p>
	 */
	@Test
	public void testListExpensesOfMonthBadRequestDate() {
		//GIVEN
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		expenseService.addExpense(e1);
		expenseService.addExpense(e2);
		expenseService.addExpense(e3);
		expenseService.addExpense(e4);
		assertEquals(expenseService.getExpenses().size(),4);
		//WHEN
		responseList = erc.listExpensesOfMonth("2018/04");
		//THEN
		assertEquals(responseList.getStatusCode(),HttpStatus.BAD_REQUEST);
			
	}

//	/**
//	 * testAddExpense() controller
//	 * 
//	 * <p>GIVEN: Added 4 Expenses to service</p>
//	 * <p>WHEN: call listAddExpense() controller with valid input fields</p>
//	 * <p>THEN: the list of expenses has increased to 5</p>
//	 */
//	@Test
//	public void testAddExpenseSuccess() {
//		//GIVEN
//		assertEquals(expenseService.getExpenses().isEmpty(),true);
//		expenseService.addExpense(e1);
//		expenseService.addExpense(e2);
//		expenseService.addExpense(e3);
//		expenseService.addExpense(e4);
//		assertEquals(expenseService.getExpenses().size(),4);
//		//WHEN
//		expenseDTO.setName("Expense Test");
//		expenseDTO.setType("fiXed");
//		expenseDTO.setValueOfPayment(50);
//		expenseDTO.setDateOfPayment(LocalDate.now());
//		response = erc.addExpense(expenseDTO);
//		//THEN
//		assertEquals(response.getStatusCode(),HttpStatus.OK);
//		assertEquals(expenseService.getExpenses().size(),5);
//	}
//	
//	/**
//	 * testAddExpense() controller
//	 * 
//	 * <p>GIVEN: Added 4 Expenses to service</p>
//	 * <p>WHEN: call listAddExpense() controller with invalid input name</p>
//	 * <p>THEN: the list of expenses still 4</p>
//	 */
//	@Test
//	public void testAddExpenseBadRequestEmptyName() {
//		//GIVEN
//		assertEquals(expenseService.getExpenses().isEmpty(),true);
//		expenseService.addExpense(e1);
//		expenseService.addExpense(e2);
//		expenseService.addExpense(e3);
//		expenseService.addExpense(e4);
//		assertEquals(expenseService.getExpenses().size(),4);
//		//WHEN
//		
//		expenseDTO.setType("fiXed");
//		expenseDTO.setValueOfPayment(50);
//		expenseDTO.setDateOfPayment(LocalDate.now());
//		response = erc.addExpense(expenseDTO);
//		//THEN
//		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
//		assertEquals(expenseService.getExpenses().size(),4);
//	}
//	
//	/**
//	 * testAddExpense() controller
//	 * 
//	 * <p>GIVEN: Added 4 Expenses to service</p>
//	 * <p>WHEN: call listAddExpense() controller with invalid input value of Payment</p>
//	 * <p>THEN: the list of expenses still 4</p>
//	 */
//	@Test
//	public void testAddExpenseBadRequestEmptyValue() {
//		//GIVEN
//		assertEquals(expenseService.getExpenses().isEmpty(),true);
//		expenseService.addExpense(e1);
//		expenseService.addExpense(e2);
//		expenseService.addExpense(e3);
//		expenseService.addExpense(e4);
//		assertEquals(expenseService.getExpenses().size(),4);
//		//WHEN
//		expenseDTO.setName("Expense Test");
//		expenseDTO.setType("fiXed");
//	
//		expenseDTO.setDateOfPayment(LocalDate.now());
//		response = erc.addExpense(expenseDTO);
//		//THEN
//		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
//		assertEquals(expenseService.getExpenses().size(),4);
//	}
//	
//	/**
//	 * testAddExpense() controller
//	 * 
//	 * <p>GIVEN: Added 4 Expenses to service</p>
//	 * <p>WHEN: call listAddExpense() controller with invalid input type of Expense</p>
//	 * <p>THEN: the list of expenses still 4</p>
//	 */
//	@Test
//	public void testAddExpenseBadRequestEmptyType() {
//		//GIVEN
//		assertEquals(expenseService.getExpenses().isEmpty(),true);
//		expenseService.addExpense(e1);
//		expenseService.addExpense(e2);
//		expenseService.addExpense(e3);
//		expenseService.addExpense(e4);
//		assertEquals(expenseService.getExpenses().size(),4);
//		//WHEN
//		expenseDTO.setName("Expense Test");
//		
//		expenseDTO.setValueOfPayment(50);
//		expenseDTO.setDateOfPayment(LocalDate.now());
//		response = erc.addExpense(expenseDTO);
//		//THEN
//		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
//		assertEquals(expenseService.getExpenses().size(),4);
//	}
//	
//	/**
//	 * testAddExpense() controller
//	 * 
//	 * <p>GIVEN: Added 4 Expenses to service</p>
//	 * <p>WHEN: call listAddExpense() controller with invalid input type of Expense</p>
//	 * <p>THEN: the list of expenses still 4</p>
//	 */
//	@Test
//	public void testAddExpenseBadRequestInvalidType() {
//		//GIVEN
//		assertEquals(expenseService.getExpenses().isEmpty(),true);
//		expenseService.addExpense(e1);
//		expenseService.addExpense(e2);
//		expenseService.addExpense(e3);
//		expenseService.addExpense(e4);
//		assertEquals(expenseService.getExpenses().size(),4);
//		//WHEN
//		expenseDTO.setName("Expense Test");
//		expenseDTO.setType("Invalid Type");
//		expenseDTO.setValueOfPayment(50);
//		expenseDTO.setDateOfPayment(LocalDate.now());
//		response = erc.addExpense(expenseDTO);
//		//THEN
//		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
//		assertEquals(expenseService.getExpenses().size(),4);
//	}
//	
//	/**
//	 * testAddExpense() controller
//	 * 
//	 * <p>GIVEN: Added 4 Expenses to service</p>
//	 * <p>WHEN: call listAddExpense() controller with invalid input date of Expense</p>
//	 * <p>THEN: the list of expenses still 4</p>
//	 */
//	@Test
//	public void testAddExpenseBadRequestInvalidDate() {
//		//GIVEN
//		assertEquals(expenseService.getExpenses().isEmpty(),true);
//		expenseService.addExpense(e1);
//		expenseService.addExpense(e2);
//		expenseService.addExpense(e3);
//		expenseService.addExpense(e4);
//		assertEquals(expenseService.getExpenses().size(),4);
//		//WHEN
//		expenseDTO.setName("Expense Test");
//		expenseDTO.setType("fixed");
//		expenseDTO.setValueOfPayment(50);
//	
//		response = erc.addExpense(expenseDTO);
//		//THEN
//		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
//		assertEquals(expenseService.getExpenses().size(),4);
//	}
//
//	/**
//	 * testRemoveExpense() controller
//	 * 
//	 * <p>GIVEN: Added 4 Expenses to service</p>
//	 * <p>WHEN: call removeExpense() controller with valid Id</p>
//	 * <p>THEN: the list of expenses has decreased to 3</p>
//	 */
//	@Test
//	public void testRemoveExpenseSuccess() {
//		//GIVEN
//		assertEquals(expenseService.getExpenses().isEmpty(),true);
//		expenseService.addExpense(e1);
//		expenseService.addExpense(e2);
//		expenseService.addExpense(e3);
//		expenseService.addExpense(e4);
//		assertEquals(expenseService.getExpenses().size(),4);
//		//WHEN	
//		response = erc.removeExpense(1);
//		//THEN
//		assertEquals(response.getStatusCode(),HttpStatus.OK);
//		assertEquals(expenseService.getExpenses().size(),3);		
//	}
//	
//	/**
//	 * testRemoveExpense() controller
//	 * 
//	 * <p>GIVEN: Added 4 Expenses to service</p>
//	 * <p>WHEN: call removeExpense() controller with invalid Id</p>
//	 * <p>THEN: the list of expenses still 4 and response returned is NOT_FOUND</p>
//	 */
//	@Test
//	public void testRemoveExpenseNotFoundId() {
//		//GIVEN
//		assertEquals(expenseService.getExpenses().isEmpty(),true);
//		expenseService.addExpense(e1);
//		expenseService.addExpense(e2);
//		expenseService.addExpense(e3);
//		expenseService.addExpense(e4);
//		assertEquals(expenseService.getExpenses().size(),4);
//		//WHEN	
//		response = erc.removeExpense(21);
//		//THEN
//		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
//		assertEquals(expenseService.getExpenses().size(),4);		
//	}

	
	/**
	 * testGetExpenseById() controller
	 * 
	 * <p>GIVEN: Added 4 Expenses to service</p>
	 * <p>WHEN: call getExpenseById() controller with valid Id</p>
	 * <p>THEN: return OK response and respective ExpenseRestDTO</p>
	 */
	@Test
	public void testGetExpenseByIdSuccess() {
		//GIVEN
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		expenseService.addExpense(e1);
		expenseService.addExpense(e2);
		expenseService.addExpense(e3);
		expenseService.addExpense(e4);
		assertEquals(expenseService.getExpenses().size(),4);
		//WHEN	
		response = erc.getExpenseById(1);
		//THEN
		expenseDTO = expenseService.findExpenseById(1).toRestDTO();
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(expenseDTO,response.getBody());		
	}
	
	/**
	 * testGetExpenseById() controller
	 * 
	 * <p>GIVEN: Added 4 Expenses to service</p>
	 * <p>WHEN: call getExpenseById() controller with invalid Id</p>
	 * <p>THEN: return NOT_FOUND</p>
	 */
	@Test
	public void testGetExpenseByIdInvalidId() {
		//GIVEN
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		expenseService.addExpense(e1);
		expenseService.addExpense(e2);
		expenseService.addExpense(e3);
		expenseService.addExpense(e4);
		assertEquals(expenseService.getExpenses().size(),4);
		//WHEN	
		response = erc.getExpenseById(12);
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
		
	}

}

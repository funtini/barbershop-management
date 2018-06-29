package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.jparepositories.classtests.ExpenseRepositoryClass;
import bsmanagement.model.Expense;
import bsmanagement.model.ExpenseService;
import bsmanagement.model.Expense.expenseType;



/**
 * 
 * Unit tests for Expense Service Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ExpenseServiceTest {
		
	LocalDate d1;
	LocalDate d2;
	LocalDate d3;
	
	Expense e1;
	Expense e2;
	Expense e3;
	Expense e4;
	
	List<Expense> expect;
	List<Expense> result;
	List<Expense> emptyList;
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
		
		expect = new ArrayList<Expense>();
		result = new ArrayList<Expense>();
		emptyList = new ArrayList<Expense>();
		expenseService = new ExpenseService();
		expenseRepository = new ExpenseRepositoryClass();
		expenseService.setRepository(expenseRepository);

		expect.clear();
		result.clear();
		
		Expense.setStartIdGenerator(1);
		
		d1 = LocalDate.of(2018, 3, 5);
		d2 = LocalDate.of(2018, 4, 10);
		d3 = LocalDate.of(2018, 4, 15);
		
		e1 = new Expense("Agua",expenseType.FIXED,35,d1);
		e2 = new Expense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
		e3 = new Expense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades");
		e4 = new Expense("Shampoos",expenseType.ONEOFF,60,d2,"10 unidades");
		e1.setId(1);
		e2.setId(2);
		e3.setId(3);
		e4.setId(4);
		
		
	}
	
	
	/**
	 * <h2>setStartIdGenerator() method test</h2>
	 * 
	 */
	@Test 
	public void testSetStartIdGenerator() {
		expenseService.addExpense(e1);
		expenseService.addExpense(e2);
		expenseService.addExpense(e3);
		
		//Given
		assertEquals(e1.getId(),1);
		assertEquals(e2.getId(),2);
		assertEquals(e3.getId(),3);
		//When
		Expense.setStartIdGenerator(10);
		Expense e4 = new Expense("Luz",expenseType.FIXED,35,d2);
		expenseService.addExpense(e4);
		//Then
		assertEquals(e4.getId(),10);
	}

	/**
	 * <h2>setRepository method test</h2>
	 */
	@Test 
	public void testSetRepository() {
		
		//Given: empty service, and a repository with 3 products
		assertEquals(expenseService.getExpenses().isEmpty(),true);
		ExpenseRepositoryClass expenseRepoTest = new ExpenseRepositoryClass();
		expenseRepoTest.save(e1);
		expenseRepoTest.save(e2);
		expenseRepoTest.save(e3);
		
		//When: set repository to service
		expect.add(e1);
		expect.add(e2);
		expect.add(e3);
		expenseService.setRepository(expenseRepoTest);
		//Then: service has 3 products
		result = expenseService.getExpenses();
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>addExpense() method test</h2>
	 */
	@Test 
	public void testAddExpense() {
		
		//Given: empty list's
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: add 3 expenses
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		expect.add(e1);
		expect.add(e2);
		expect.add(e3);
		//Then: get a list with that 3 expenses
		result = expenseService.getExpenses();
		assertEquals(result,expect);
	}
	
	
	
	/**
	 * <h2>removeExpense() method test</h2>
	 */
	@Test 
	public void testRemoveExpense() {
		
		//Given: list with 3 expenses
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
	
		//When: remove 1 expense
		expenseService.removeExpense(e1);
		expect.add(e2);
		expect.add(e3);
		//Then: get a list with  2 expenses
		result = expenseService.getExpenses();
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>getExpenseListByType() method test</h2>
	 * <p>Test filter FIXED expenses</p>
	 */
	@Test 
	public void testFilterExpenseListByTypeFixed() {
		
		//Given: empty list's
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		//When: add 3 expenses - (2)fixed and (1)oneoff
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//Then: get a list of FIXED expenses 
		result = expenseService.findExpensesByType(expenseType.FIXED);
		expect.add(e1);
		expect.add(e2);
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>getExpenseListByType() method test</h2>
	 * <p>Test filter ONEOFF expenses</p>
	 */
	@Test 
	public void testFilterExpenseListByTypeOneOff() {
		//Given: empty list's
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		//When: add 3 expenses - (2)fixed and (1)oneoff
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//Then: get a list of FIXED expenses 
		result = expenseService.findExpensesByType(expenseType.ONEOFF);
		expect.add(e3);
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>getExpenseListAboveValue() method test</h2>
	 */
	@Test 
	public void testGetExpenseListAboveValue() {
		
		//Given: empty list's
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: add 3 expenses
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//Then: get a list with expenses above 55 value
		result = expenseService.filterExpensesAboveValue(55);
		expect.add(e3);
		assertEquals(result,expect);
		//		clear test lists, and repeat test with expenses above value 50
		result.clear();
		expect.clear();
		result = expenseService.filterExpensesAboveValue(50);
		expect.add(e2);
		expect.add(e3);
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>getExpenseListUnderBelow() method test</h2>
	 */
	@Test 
	public void testGetExpenseListUnderBelow() {
		
		//Given: empty list's
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: add 3 expenses
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//Then: get a list with expenses below 40 value
		result = expenseService.filterExpensesBelowValue(40);
		expect.add(e1);
		assertEquals(result,expect);
		//		clear test lists, and repeat test with expenses below value 50
		result.clear();
		expect.clear();
		result = expenseService.filterExpensesBelowValue(50);
		expect.add(e1);
		expect.add(e2);
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>findExpensesOfMonth() method test</h2>
	 */
	@Test 
	public void testFindExpensesOfMonth() {
		
		//Given: 1 expense of March, 2 expenses of April and 1 expenses of December/2017
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		Expense e17 = expenseService.createExpense("Shampoo",expenseType.ONEOFF,120,LocalDate.of(2017, 12, 22),"15 unidades");
		expenseService.addExpense(e17);
		//When: get a list of April/2018 
		
		result = expenseService.findExpensesOfMonth(YearMonth.of(2018, 4));
		expect.add(e2);
		expect.add(e3);
		assertEquals(result,expect);
		//		clear test lists, and repeat test for December/2017 expenses
		result.clear();
		expect.clear();
		result = expenseService.findExpensesOfMonth(YearMonth.of(2017, 12));
		expect.add(e4);
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>findExpensesOfDay() method test</h2>
	 */
	@Test 
	public void testFindExpensesOfDay() {
		
		//Given: 1 expense of March, 2 expenses of April and 1 expenses of December/2017
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		Expense e17 = expenseService.createExpense("Shampoo",expenseType.ONEOFF,120,d2,"15 unidades");
		expenseService.addExpense(e17);
		//When: get a list of 10/April/2018 
		
		result = expenseService.findExpensesOfDay(LocalDate.of(2018, 4,10));
		expect.add(e2);
		expect.add(e17);
		assertEquals(result,expect);
		//		clear test lists, and repeat test for 15/April/2018 expenses
		result.clear();
		expect.clear();
		result = expenseService.findExpensesOfDay(LocalDate.of(2018, 4,15));
		expect.add(e3);
		assertEquals(result,expect);		
	}
	
	
	/**
	 * <h2>findExpenseById() method test</h2>
	 */
	@Test 
	public void testFindExpenseById() {
		
		//Given: 3 expenses
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		
		//When: get a expense by ID
		
		Expense result = expenseService.findExpenseById(1);
		Expense resultNull = expenseService.findExpenseById(33);
		Expense expect = e1;
		//Then: get a expense by ID
		assertEquals(result,expect);
		assertEquals(resultNull,null);
	}
	
	/**
	 * <h2>searchExpenseByName() method test</h2>
	 */
	@Test 
	public void testSearchExpenseByName() {
		
		//Given: 3 expenses
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		
		//When: get a expense by Name
		
		result = expenseService.searchExpenseByName("Internet");
		expect.add(e2);
		//Then: get a expense by ID
		assertEquals(result,expect);					
	}

	/**
	 * <h2>findExpenseBetweenDates() method test</h2>
	 */
	@Test 
	public void testFindExpenseBetweenDates() {
		
		//Given: 1 expense of March, 2 expenses of April and 1 expenses of December/2017
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		Expense e17 = expenseService.createExpense("Shampoo",expenseType.ONEOFF,120,LocalDate.of(2017, 12, 22),"15 unidades");
		expenseService.addExpense(e17);
		//When: get a list between march and 12 of April
		
		result = expenseService.findExpensesBetweenDates(d1.minusDays(5), d3.minusDays(1));
		expect.add(e1);
		expect.add(e2);
		assertEquals(result,expect);
		//		clear test lists, and repeat test for 1/December/2017 and 1/April/2018
		result.clear();
		expect.clear();
		result = expenseService.findExpensesBetweenDates(LocalDate.of(2017, 12, 1), LocalDate.of(2018, 4, 1));
		expect.add(e1);
		expect.add(e17);
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>findExpenseBetweenDates() method test</h2>
	 * 
	 * - Invalid Date's Difference
	 */
	@Test 
	public void testFindExpenseBetweenDatesInvalid() {
		
		//Given: 1 expense of March, 2 expenses of April and 1 expenses of December/2017
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		Expense e17 = expenseService.createExpense("Shampoo",expenseType.ONEOFF,120,LocalDate.of(2017, 12, 22),"15 unidades");
		expenseService.addExpense(e17);
		//When: get a list between may and 12 of April (invalid difference between dates)
		
		result = expenseService.findExpensesBetweenDates(d3.plusDays(5), d3.minusDays(1));
		//Then: get empty list
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>sumAllExpensesValue() method test</h2>
	 */
	@Test 
	public void testSumAllExpensesValue() {
		
		//Given: 3 expenses
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//When: get the sum of all expenses value
		double result = expenseService.sumAllExpensesValue();
		double expect = 175;
		//Then: 
		assertEquals(result,expect,0.0);				
	}
	
	/**
	 * <h2>sumExpensesValueByType() method test</h2>
	 */
	@Test 
	public void testSumExpensesValueByType() {
		
		//Given: 3 expenses
		assertEquals(expenseService.getExpenses(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseService.addExpense(expenseService.createExpense("Agua",expenseType.FIXED,35,d1,null));
		expenseService.addExpense(expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//When: get the sum of all expenses value of a specific Type
		double result = expenseService.sumExpensesValueByType(expenseType.FIXED);
		double result2 = expenseService.sumExpensesValueByType(expenseType.ONEOFF);
		double expect = 85;
		double expect2 = 90;
		//Then:
		assertEquals(result,expect,0.0);		
		assertEquals(result2,expect2,0.0);
	}

}

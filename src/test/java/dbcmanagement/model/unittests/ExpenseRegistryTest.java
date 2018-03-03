package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dbcmanagement.model.Expense;
import dbcmanagement.model.ExpenseRegistry;
import dbcmanagement.model.Expense.expenseType;



/**
 * 
 * Unit tests for ExpenseRegistry Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ExpenseRegistryTest {
		
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
	ExpenseRegistry expenseList;

	
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
		expenseList = new ExpenseRegistry();
		expenseList.getExpenseList().clear();
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
		
	}

	/**
	 * <h2>getExpenseList() and setExpenseList method test</h2>
	 */
	@Test 
	public void testGetnSetExpenseList() {
		
		//Given: empty list's
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: set a list with 3 expenses
		expect.add(e1);
		expect.add(e2);
		expect.add(e3);
		expenseList.setExpenseList(expect);
		//Then: get a list with that 3 expenses
		result = expenseList.getExpenseList();
		assertEquals(result,expect);
		assertEquals(expect.equals(emptyList),false);
	}
	
	/**
	 * <h2>addExpense() method test</h2>
	 */
	@Test 
	public void testAddExpense() {
		
		//Given: empty list's
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: add 3 expenses
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		expect.add(e1);
		expect.add(e2);
		expect.add(e3);
		//Then: get a list with that 3 expenses
		result = expenseList.getExpenseList();
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>getExpenseListByType() method test</h2>
	 * <p>Test filter FIXED expenses</p>
	 */
	@Test 
	public void testFilterExpenseListByTypeFixed() {
		
		//Given: empty list's
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
		//When: add 3 expenses - (2)fixed and (1)oneoff
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//Then: get a list of FIXED expenses 
		result = expenseList.findExpensesByType(expenseType.FIXED);
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
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
		//When: add 3 expenses - (2)fixed and (1)oneoff
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//Then: get a list of FIXED expenses 
		result = expenseList.findExpensesByType(expenseType.ONEOFF);
		expect.add(e3);
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>getExpenseListOf() method test</h2>
	 */
	@Test 
	public void testGetExpenseListAboveValue() {
		
		//Given: empty list's
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: add 3 expenses
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//Then: get a list with expenses above 55 value
		result = expenseList.filterExpensesAboveValue(55);
		expect.add(e3);
		assertEquals(result,expect);
		//		clear test lists, and repeat test with expenses above value 50
		result.clear();
		expect.clear();
		result = expenseList.filterExpensesAboveValue(50);
		expect.add(e2);
		expect.add(e3);
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>getExpenseListByMaxValue() method test</h2>
	 */
	@Test 
	public void testGetExpenseListUnderBelow() {
		
		//Given: empty list's
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: add 3 expenses
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//Then: get a list with expenses below 40 value
		result = expenseList.filterExpensesBelowValue(40);
		expect.add(e1);
		assertEquals(result,expect);
		//		clear test lists, and repeat test with expenses below value 50
		result.clear();
		expect.clear();
		result = expenseList.filterExpensesBelowValue(50);
		expect.add(e1);
		expect.add(e2);
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>getExpenseListByMaxValue() method test</h2>
	 */
	@Test 
	public void testFindExpensesOfYearMonth() {
		
		//Given: 1 expense of March, 2 expenses of April and 1 expenses of December/2017
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		Expense e17 = expenseList.createExpense("Shampoo",expenseType.ONEOFF,120,LocalDate.of(2017, 12, 22),"15 unidades");
		expenseList.addExpense(e17);
		//When: get a list of April/2018 
		
		result = expenseList.findExpensesOf(YearMonth.of(2018, 4));
		expect.add(e2);
		expect.add(e3);
		assertEquals(result,expect);
		//		clear test lists, and repeat test for December/2017 expenses
		result.clear();
		expect.clear();
		result = expenseList.findExpensesOf(YearMonth.of(2017, 12));
		expect.add(e4);
		assertEquals(result,expect);		
	}
	
	
	/**
	 * <h2>getExpenseListByMaxValue() method test</h2>
	 */
	@Test 
	public void testFindExpenseById() {
		
		//Given: 3 expenses
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		
		//When: get a expense by ID
		
		Expense result = expenseList.findExpenseById(1);
		Expense expect = e1;
		//Then: get a expense by ID
		assertEquals(result,expect);			
	}
	
	/**
	 * <h2>getExpenseListByMaxValue() method test</h2>
	 */
	@Test 
	public void testSearchExpenseByName() {
		
		//Given: 3 expenses
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		
		//When: get a expense by Name
		
		result = expenseList.searchExpenseByName("Internet");
		expect.add(e2);
		//Then: get a expense by ID
		assertEquals(result,expect);					
	}

	/**
	 * <h2>getExpenseListByMaxValue() method test</h2>
	 */
	@Test 
	public void testFindExpenseBetweenDates() {
		
		//Given: 1 expense of March, 2 expenses of April and 1 expenses of December/2017
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		Expense e17 = expenseList.createExpense("Shampoo",expenseType.ONEOFF,120,LocalDate.of(2017, 12, 22),"15 unidades");
		expenseList.addExpense(e17);
		//When: get a list between march and 12 of April
		
		result = expenseList.findExpensesBetweenDates(d1.minusDays(5), d3.minusDays(1));
		expect.add(e1);
		expect.add(e2);
		assertEquals(result,expect);
		//		clear test lists, and repeat test for 1/December/2017 and 1/April/2018
		result.clear();
		expect.clear();
		result = expenseList.findExpensesBetweenDates(LocalDate.of(2017, 12, 1), LocalDate.of(2018, 4, 1));
		expect.add(e1);
		expect.add(e17);
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>getExpenseListByMaxValue() method test</h2>
	 * 
	 * - Invalid Date's Difference
	 */
	@Test 
	public void testFindExpenseBetweenDatesInvalid() {
		
		//Given: 1 expense of March, 2 expenses of April and 1 expenses of December/2017
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		Expense e17 = expenseList.createExpense("Shampoo",expenseType.ONEOFF,120,LocalDate.of(2017, 12, 22),"15 unidades");
		expenseList.addExpense(e17);
		//When: get a list between may and 12 of April (invalid difference between dates)
		
		result = expenseList.findExpensesBetweenDates(d3.plusDays(5), d3.minusDays(1));
		//Then: get empty list
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>getExpenseListByMaxValue() method test</h2>
	 */
	@Test 
	public void testSumAllExpensesValue() {
		
		//Given: 3 expenses
		assertEquals(expenseList.getExpenseList(),emptyList);
		assertEquals(emptyList,expect);
		Expense.setStartIdGenerator(1);
		expenseList.addExpense(expenseList.createExpense("Agua",expenseType.FIXED,35,d1));
		expenseList.addExpense(expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato"));
		expenseList.addExpense(expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades"));
		//When: get the sum of all expenses value
		double result = expenseList.sumAllExpensesValue();
		double expect = 175;
		//Then: get empty list
		assertEquals(result,expect,0.0);				
	}

}

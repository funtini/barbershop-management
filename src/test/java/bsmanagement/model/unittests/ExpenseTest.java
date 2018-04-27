package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Customer;
import bsmanagement.model.Expense;
import bsmanagement.model.Expense.expenseType;



/**
 * 
 * Unit tests for Expense Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ExpenseTest {

	LocalDate d1;
	LocalDate d2;
	
	Expense e1;
	Expense e2;
	Expense e3;
	
	
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Date [d1] : 05/03/2018 </p>
	 * <p>Date [d2] : 10/04/2018 </p>
	 * <p>ExpenseType [et1] : FIXED </p>
	 * <p>ExpenseType [et2] : ONE-OFF</p>
	 * 
	 * <p>Expense [e1] : ["Agua",'FIXED','35',d1,"none"] </p>
	 * <p>Expense [e2] : ["Internet",'FIXED','50',d2,"6 meses de contrato"] </p>
	 * <p>Expense [e3] : ["Secadores",'ONE-OFF','80',d2,"3 unidades"] </p>
	 * 
	 */
	@Before
	public void setUp() {
		
		Expense.setStartIdGenerator(1);
		
		d1 = LocalDate.of(2018, 3, 5);
		d2 = LocalDate.of(2018, 4, 10);
		
		
		e1 = new Expense("Agua",expenseType.FIXED,35,null);
		e2 = new Expense("Internet",expenseType.FIXED,50,null,"6 meses de contrato");
		e3 = new Expense("Secadores",expenseType.ONEOFF,90,d1,"3 unidades");
		
		e1.setId(1);
		e2.setId(2);
		e3.setId(3);

	}
	
	
	/**
	 * <h2>getId() and setId() method test</h2>
	 * 
	 * <p>Description
	 * 
	 */
	@Test 
	public void testGetnSetId() {
		//Given: 3 expenses
		assertEquals(e1.getId(),1);	
		assertEquals(e2.getId(),2);
		assertEquals(e3.getId(),3);
		//When: we set new Id's
		e1.setId(10);
		e2.setId(20);
		e3.setId(33);
		//Then:
		assertEquals(e1.getId(),10);
		assertEquals(e2.getId(),20);
		assertEquals(e3.getId(),33);
		
	}
	
	
	
	
	/**
	 * <h2>getName() and setName method test</h2>
	 */
	@Test 
	public void testGetnSetName() {
		
		//Given
		assertEquals(e1.getName(),"Agua");
		assertEquals(e2.getName(),"Internet");
		assertEquals(e3.getName(),"Secadores");
		//When
		e1.setName("Luz");
		e3.setName("Secadores e Candeeiro");
		//Then
		assertEquals(e1.getName(),"Luz");
		assertEquals(e3.getName(),"Secadores e Candeeiro");
	}
	
	/**
	 * <h2>getType() and setType method test</h2>
	 */
	@Test 
	public void testGetnSetType() {
		
		//Given
		assertEquals(e1.getType(),expenseType.FIXED);
		assertEquals(e3.getType(),expenseType.ONEOFF);
		
		//When
		e1.setType(expenseType.ONEOFF);
		e3.setType(expenseType.FIXED);
		
		//Then
		assertEquals(e1.getType(),expenseType.ONEOFF);
		assertEquals(e3.getType(),expenseType.FIXED);
	
	}

	/**
	 * <h2>getDate() and setDate method test</h2>
	 */
	@Test 
	public void testGetnSetDate() {
		
		//Given
		assertEquals(e1.getDate(),null);
		assertEquals(e2.getDate(),null);
		assertEquals(e3.getDate(),d1);
		//When
		e1.setDate(d2);
		e3.setDate(LocalDate.of(1999, 11, 5));
		//Then
		assertEquals(e1.getDate(),d2);
		assertEquals(e3.getDate(),LocalDate.of(1999, 11, 5));
	}
	
	/**
	 * <h2>getValue() and setValue method test</h2>
	 */
	@Test 
	public void testGetnSetValue() {
		
		//Given
		assertEquals(e1.getValue(),35,0.0);
		assertEquals(e2.getValue(),50,0.0);
		assertEquals(e3.getValue(),90,0.0);
		//When
		e1.setValue(44.5);
		e3.setValue(111);
		//Then
		assertEquals(e1.getValue(),44.5,0.0);
		assertEquals(e3.getValue(),111,0.0);
	}
	
	
	/**
	 * <h2>getDescription() and setDescription method test</h2>
	 */
	@Test 
	public void testGetnSetDescription() {
		
		//Given
		assertEquals(e1.getDescription(),"");
		assertEquals(e2.getDescription(),"6 meses de contrato");
		assertEquals(e3.getDescription(),"3 unidades");
		//When
		e1.setDescription("teste");
		e3.setDescription("teste & teste");
		//Then
		assertEquals(e1.getDescription(),"teste");
		assertEquals(e3.getDescription(),"teste & teste");
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test true cases</p>
	 */
	@Test
	public void testEqualsTrue() {
		assertEquals(e1.equals(e1),true);
		assertEquals(e2.equals(e2),true);

	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - different instances</p>
	 */
	@Test
	public void testEqualsFalse() {
		assertEquals(e1.equals(e2),false);
		assertEquals(e3.equals(e2),false);
		assertEquals(e2.equals(e3),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null cases</p>
	 */
	@Test
	public void testEqualsNull() {
		assertEquals(e1.equals(null),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - different classes</p>
	 */
	@Test
	public void testEqualsFalseDifferentClass() {
		Customer c1 = new Customer("Joao");
		assertEquals(e1.equals(c1),false);
	}
	
	/**
	 * <h2>toString() method test</h2>
	 */
	@Test
	public void testToString() {
		String expect = "Expense [id=" + 1 + ", name=" + e1.getName() + ", type=" + e1.getType() + ", value=" + e1.getValue() + "]";
		assertEquals(e1.toString(),expect);
	}
	
	/**
	 * <h2>hashCode() method test</h2>
	 */
	@Test
	public void testHashCode() {
		Expense e4 = new Expense("Secadores",expenseType.ONEOFF,90,d1,"3 unidades");
		e4.setId(3);
		assertEquals(e3.hashCode(),e4.hashCode());
		assertNotEquals(e1.hashCode(),e2.hashCode());
	}
	
	/**
	 * <h2>compareTo() method test</h2>
	 * 
	 * Negative cases - null date on parameter expense
	 */
	@Test
	public void testCompareToNegative() {
		assertEquals(e3.compareTo(e2),-1);
		
	}
	
	/**
	 * <h2>compareTo() method test</h2>.
	 * 
	 * Positive cases - the expense's instance has null date
	 */
	@Test
	public void testCompareToPositive() {
		assertEquals(e1.compareTo(e2),1);
	}
	
	/**
	 * <h2>compareTo() method test</h2>.
	 * 
	 * zero cases - the expenses have the same dates
	 */
	@Test
	public void testCompareToSameDate() {
		Expense e4 = new Expense("Secadores",expenseType.ONEOFF,90,d1,"3 unidades");
		assertEquals(e3.compareTo(e4),0);
	}
	
	/**
	 * <h2>compareTo() method test</h2>.
	 * 
	 * two valid dates
	 */
	@Test
	public void testCompareToTwoValidDates() {
		Expense e4 = new Expense("Secadores",expenseType.ONEOFF,90,d2,"3 unidades");
		assertEquals(e3.compareTo(e4),1);
		assertEquals(e4.compareTo(e3),-1);
	}
	
}

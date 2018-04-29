package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Address;
import bsmanagement.model.Booking;
import bsmanagement.model.Contract;
import bsmanagement.model.Customer;
import bsmanagement.model.User;

/**
 * 
 * Unit tests for Contract Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ContractTest {
	
	LocalDate birth1;
	LocalDate birth2;
	
	User u1;
	User u2;
	
	Contract c1;
	Contract c2;
	Contract c3;
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Date [birth1] : 1998/03/17 </p>
	 * <p>Date [birth2] : 1988/07/21 </p>
	 * 
	 * <p>User [u1] : ["JOAO",birth1,"joao@domain.com","914047935","324666433"] </p>
	 * <p>User [u2] : ["PEDRO",birth2,"pedro@dgmail.uk","915557911","123555433"] </p>
	 * 
	 * 
	 * <p>Contract [c1] -> ['700','10'] </p>
	 * <p>Contract [c2] -> ['350','60'] </p>
	 * <p>Contract [c3] -> ['350','50'] </p>
	 * 
	 */
	@Before
	public void setUp(){
		
		Contract.setStartIdGenerator(1);
		birth1 = LocalDate.of(1998, 3, 17);
		birth2 = LocalDate.of(1988, 7, 21);
		
		u1 = new User("JOAO",birth1,"joao@domain.com","914047935","324666433");
		u2 = new User("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		
		c1 = u1.createContract(700, 10);
		c2 = u2.createContract(350,	60);
		
		u1.addContract(c1);
		u2.addContract(c2);
		
	}

	@Test
	public void testHashCode() {
		
		Contract.setStartIdGenerator(1);
		Contract c3 = u1.createContract(700, 10);
		assertEquals(c3.hashCode(),c1.hashCode());
	}

	@Test
	public void testGetId() {
		assertEquals(c1.getId(),1);
		assertEquals(c2.getId(),2);
	}

	@Test
	public void testGetAndSetBaseSalary() {
		assertEquals(c1.getBaseSalary(),700,0.0);
		c1.setBaseSalary(500);
		assertEquals(c1.getBaseSalary(),500,0.0);
	}

	@Test
	public void testGetAndSetSalesComission() {
		assertEquals(c1.getSalesComission(),10,0.0);
		c1.setSalesComission(30);
		assertEquals(c1.getSalesComission(),30,0.0);
	}


	@Test
	public void testGetAndSetStartDate() {
		assertEquals(c1.getStartDate(),LocalDate.now());
		c1.setStartDate(LocalDate.of(2018, 03, 1));
		assertEquals(c1.getStartDate(),LocalDate.of(2018, 03, 1));
	}

	@Test
	public void testGetAndSetCloseDate() {
		
		assertEquals(c1.getCloseDate(),null);
		assertEquals(c1.close(),true);
		assertEquals(c1.getCloseDate(),LocalDate.now());
		assertEquals(c1.close(),false);
	}

	@Test
	public void testIsOpen() {
		assertEquals(c1.isOpen(),true);
		assertEquals(c1.close(),true);
		assertEquals(c1.isOpen(),false);
	}


	@Test
	public void testEqualsSuccess() {
		Contract.setStartIdGenerator(1);
		Contract c3 = u1.createContract(700, 10);
		assertEquals(c1.equals(c3),true);
		assertEquals(c1.equals(c2),false);
		c3=null;
		assertEquals(c1.equals(c3),false);
		assertEquals(c1.equals(u1),false);
	}

	@Test
	public void testToString() {
		String expected = "Contract [id=" + c1.getId() + ", baseSalary=" + c1.getBaseSalary() + ", salesComission=" + c1.getSalesComission()
				+ ", startDate=" + c1.getStartDate() + ", closeDate=" + c1.getCloseDate() + "]";
		
		assertEquals(expected,c1.toString());
	}

}

/**
 * 
 */
package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dbcmanagement.model.Customer;
import dbcmanagement.model.CustomerRegistry;

/**
 * 
 * Unit tests for CustomerRegistry Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class CustomerRegistryTest {
	
	LocalDate bd1;
	LocalDate bd2;
	LocalDate bd3;
	Customer c1;
	Customer c2;
	Customer c3;
	CustomerRegistry cReg;
	List<Customer> expect;

	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>BirthDate [bd1] : 30/11/1989 </p>
	 * <p>BirthDate [bd2] : 15/02/1984 </p>
	 * <p>BirthDate [bd3] : 25/05/1992 </p>
	 * 
	 * <p>Customer [c1] -> ["Joao",'30/11/1989',"Mangualde",914047935] </p>
	 * <p>Customer [c2] -> ["Ana",'15/02/1984',"Porto",966677722] </p>
	 * <p>Customer [c3] -> ["Pedro",'25/05/1992',"Mangualde",932444333] </p>
	 * 
	 * <p>CustomerRegistry [cReg] </p>
	 * 
	 * 
	 */
	@Before
	public void setUp(){
		cReg = new CustomerRegistry();
		
		bd1=LocalDate.of(1989, 11, 30);
		bd2=LocalDate.of(1984, 02, 15);
		bd3=LocalDate.of(1992, 05, 25);
		
		c1 = new Customer("Joao",bd1,"Mangualde","914047935");
		c2 = new Customer("Ana",bd2,"Porto","966677722");
		c3 = new Customer("Pedro",bd3,"Mangualde","932444333");
		
		expect = new ArrayList<>();
		
	}

	/**
	 * <h2>getCustomerList() method test</h2>
	 * 
	 */
	@Test
	public void testGetCustomerList() {
		//Given: empty list
		assertEquals(cReg.getCustomerList().isEmpty(),true);
		//When: add customer
		cReg.getCustomerList().add(c1);
		expect.add(c1);
		//Then: get that customer
		assertEquals(expect,cReg.getCustomerList());
		
	}

	/**
	 * <h2>setCustomerList() method test</h2>
	 * 
	 */
	@Test
	public void testSetCustomerList() {
		//Given: empty list
		assertEquals(cReg.getCustomerList().isEmpty(),true);
		//When: add customer
		expect.add(c1);
		expect.add(c2);
		cReg.setCustomerList(expect);
		
		//Then: get that customer
		assertEquals(expect,cReg.getCustomerList());		
	}

	/**
	 * <h2>addCustomer() method test</h2>
	 * 
	 */
	@Test
	public void testAddCustomer() {
		//Given: empty list
		assertEquals(cReg.getCustomerList().isEmpty(),true);
		//When: add 3 customer
		assertEquals(cReg.addCustomer(c1),true);
		assertEquals(cReg.addCustomer(c2),true);
		assertEquals(cReg.addCustomer(c3),true);
		
		//Then: cant add the customers twice to the list
		assertEquals(cReg.addCustomer(c1),false);
		assertEquals(cReg.addCustomer(c2),false);
		assertEquals(cReg.addCustomer(c3),false);		
	}

	/**
	 * <h2>createCustomer() method test</h2>
	 * 
	 * full data
	 */
	@Test
	public void testCreateCustomerFullData() {
		//Given: instance of Customer (Ana) - c2
		Customer expect = c2;
		//When: we create by cReg a customer (Joao) and set the same ID
		Customer result = cReg.createCustomer("Ana",bd2,"Porto","966677722");
		
		expect.setId(result.getId());
		
		//Then: they are equals
		assertEquals(result,expect);			
				
	}

	/**
	 * <h2>createCustomer() method test</h2>
	 * 
	 * only name as input data
	 */
	@Test
	public void testCreateCustomerOnlyName() {
		//Given: instance of Customer (Joao)
		Customer expect = new Customer("Joao");
		//When: we create by cReg a customer (Joao) and set the same ID
		Customer result = cReg.createCustomer("Joao");
		
		expect.setId(result.getId());
		
		//Then: they are equals
		assertEquals(result,expect);		
	}

}

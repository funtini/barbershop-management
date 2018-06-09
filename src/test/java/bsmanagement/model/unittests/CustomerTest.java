package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Customer;




/**
 * 
 * Unit tests for Customer Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class CustomerTest {
	
	
	LocalDate d1;
	LocalDate d2;
	Customer c1;
	Customer c2;
	Customer c3;

	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>BirthDate [d1] : 30/11/1989 </p>
	 * <p>BirthDate [d2] : 15/02/1984 </p>
	 * <p>Address [a1] : Mangualde </p>
	 * <p>Address [a2] : Porto </p>
	 * <p>Phone [p1] : 914047935 </p>
	 * <p>Phone [p2] : 966677722 </p>
	 * 
	 * <p>Customer [c1] -> ['Joao',d1,a1,p1] </p>
	 * <p>Customer [c2] -> ['Ana',d2,a2,p2] </p>
	 * 
	 */
	@Before
	public void setUp() {
		
	Customer.setStartIdGenerator(1);
		
	d1 = LocalDate.of(1989, 11, 30);
	d2 = LocalDate.of(1984, 02, 15);
	
	c1 = new Customer("Joao",d1,"Mangualde","914047935");
	c2 = new Customer("Ana",d2,"Porto","966677722");
	c3 = new Customer("Joaquim",d2,"Lisboa","914447833","joaquim@gmail.com");
	
	c1.setId(1);
	c2.setId(2);
	
		
	}
	
	
	/**
	 * getId() method test
	 */
	@Test 
	public void testGetId() {
		
		assertEquals(c1.getId(),1);
		
		assertEquals(c2.getId(),2);
		
	}	
	
	/**
	 * getEmail() and setEmail method test
	 */
	@Test 
	public void testSetAndGetEmail() {
		c2.setEmail("ana@gmail.com");
		assertEquals(c2.getEmail(),"ana@gmail.com");
		
		assertEquals(c3.getEmail(),"joaquim@gmail.com");
		
	}	
	
	/**
	 * getName() and setName() method test
	 */
	@Test 
	public void testGetnSetName() {
		//Given
		assertEquals(c1.getName(),"Joao");
		assertEquals(c2.getName(),"Ana");
		//When
		c1.setName("Pedro");
		c2.setName("Luisa");
		//Then
		assertEquals(c1.getName(),"Pedro");
		assertEquals(c2.getName(),"Luisa");
		
	}
	
	/**
	 * getAddress() and setAddress() method test
	 */
	@Test 
	public void testGetnSetAddress() {
		
		//Given
		assertEquals(c1.getAddress(),"Mangualde");
		assertEquals(c2.getAddress(),"Porto");
		//When
		c1.setAddress("Viseu");
		c2.setAddress("Londres");
		//Then
		assertEquals(c1.getAddress(),"Viseu");
		assertEquals(c2.getAddress(),"Londres");
		
	}
	
	/**
	 * getPhone() and setPhone() method test
	 */
	@Test 
	public void testGetnSetPhone() {
		
		//Given
		assertEquals(c1.getPhone(),"914047935");
		assertEquals(c2.getPhone(),"966677722");
		//When
		String newPhoneNr = "999777666";
		c1.setPhone(newPhoneNr);
		//Then
		assertEquals(c1.getPhone(),newPhoneNr);
	}
	
	
	/**
	 * getPhone() and setPhone() method test
	 */
	@Test 
	public void testGetnSetBirthDate() {
		
		//Given
		assertEquals(c1.getBirth(),d1);
		assertEquals(c2.getBirth(),d2);
		//When
		LocalDate d3 = LocalDate.of(2000, 1, 22);
		c1.setBirth(d3);
		c2.setBirth(d1);
		//Then
		assertEquals(c1.getBirth(),d3);
		assertEquals(c2.getBirth(),d1);
	}
	


	/**
	 * <h2>equals() method test</h2>
	 * <p>test true cases - Same Instance</p>
	 */
	@Test
	public void testEqualsTrueSameInstance() {
		assertEquals(c1.equals(c1),true);
		assertEquals(c2.equals(c2),true);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test False cases - Same Instance</p>
	 */
	@Test
	public void testEqualsFalseSameName() {
		Customer test = new Customer("Joao");
		assertEquals(c1.equals(test),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test true cases - Same Instance</p>
	 */
	@Test
	public void testEqualsTrueDifferentCity() {
		Customer test = new Customer("Joao",d1,"Porto","914047935");
		assertEquals(c1.equals(test),true);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - different instances</p>
	 */
	@Test
	public void testEqualsFalseDifferentInstance() {
		assertEquals(c1.equals(c2),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - null name</p>
	 */
	@Test
	public void testEqualsFalseNullName() {
		Customer test = new Customer(null,d1,"Porto","914047935");
		assertEquals(test.equals(c2),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - null name</p>
	 */
	@Test
	public void testEqualsFalseNullPhone() {
		Customer test = new Customer("Joao",d1,"Porto",null);
		assertEquals(test.equals(c1),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - different class</p>
	 */
	@Test
	public void testEqualsFalseDifferentClass() {
		LocalDate d1 = LocalDate.now();
		assertEquals(c1.equals(d1),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null cases</p>
	 */
	@Test
	public void testEqualsNull() {
		assertEquals(c1.equals(null),false);
	}
	
	/**
	 * <h2>toString() method test</h2>
	 */
	@Test
	public void testToString() {
		String expect = "Customer [id=" + 1 + ", name=Joao" + ", phone=914047935]";
		assertEquals(c1.toString(),expect);
	}
	

	/**
	 * <h2>hashCode() method test</h2>
	 */
	@Test
	public void testHashCode() {
		Customer c3 = new Customer("Ana",d2,"Porto","966677722");
		c3.setId(2);
		assertEquals(c3.hashCode(),c2.hashCode());
		c3.setName(null);
		c2.setName(null);
		assertEquals(c3.hashCode(),c2.hashCode());
		assertNotEquals(c1.hashCode(),c2.hashCode());
	}
	
	/**
	 * <h2>hashCode() method test</h2>
	 * 
	 * Null phone 
	 */
	@Test
	public void testHashCodeNullCases() {
		Customer c3 = new Customer("Ana",d2,"Porto","966677722");
		c3.setId(2);
		c3.setPhone(null);
		c2.setPhone(null);
		assertEquals(c3.hashCode(),c2.hashCode());
		c3.setName(null);
		assertNotEquals(c3.hashCode(),c2.hashCode());
	}
	
}

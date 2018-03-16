/**
 * 
 */
package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Address;

/**
 * 
 * Unit tests for Address Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class AddressTest {
	
	Address a1;
	Address a2;
	Address a3;

	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Address [a1] -> ['CASA','RUA DO AMARO','3550-444','VISEU','PORTUGAL'] </p>
	 * <p>Address [a2] -> ['TRABALHO','RUA DO PASSAL','3530-194','MANGUALDE','PORTUGAL'] </p>
	 * <p>Address [a2] -> ['TRABALHO','RUA DO PASSAL','NULL','MANGUALDE','PORTUGAL'] </p>
	 * 
	 */
	@Before
	public void setUp(){
		Address.setStartIdGenerator(1);
		a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		a3 = new Address("TRABALHO","RUA DO PASSAL",null,"MANGUALDE","PORTUGAL");
		
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Address test = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		test.setId(1);
		assertEquals(a1.hashCode(),test.hashCode());
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#getCity()}.
	 */
	@Test
	public void testGetCity() {
		assertEquals(a1.getCity(),"VISEU");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#getCountry()}.
	 */
	@Test
	public void testGetCountry() {
		assertEquals(a1.getCountry(),"PORTUGAL");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#getAddressDescription()}.
	 */
	@Test
	public void testGetAddressDescription() {
		assertEquals(a1.getAddressDescription(),"CASA");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#getPostalCode()}.
	 */
	@Test
	public void testGetPostalCode() {
		assertEquals(a1.getPostalCode(),"3550-444");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#getStreet()}.
	 */
	@Test
	public void testGetStreet() {
		assertEquals(a1.getStreet(),"RUA DO AMARO");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#isValid()}.
	 */
	@Test
	public void testIsValid() {
		assertEquals(a1.isValid(),true);
		assertEquals(a3.isValid(),false);
	}
	
	/**
	 * Test method for {@link bsmanagement.model.Address#isValid()}.
	 */
	@Test
	public void testIsValidNullFields() {
		assertEquals(a3.isValid(),false);
		a2.setCity(null);
		a1.setAddressDescription(null);
		assertEquals(a2.isValid(),false);
		assertEquals(a1.isValid(),false);
		a3.setPostalCode("2222");
		a3.setStreet(null);
		assertEquals(a3.isValid(),false);
	}
	
	/**
	 * Test method for {@link bsmanagement.model.Address#isValid()}.
	 */
	@Test
	public void testIsValidEmptyFields() {
		a3.setPostalCode("");
		assertEquals(a3.isValid(),false);
		a2.setCity("");
		a1.setAddressDescription("");
		assertEquals(a2.isValid(),false);
		assertEquals(a1.isValid(),false);
		a3.setPostalCode("2222");
		a3.setStreet("");
		assertEquals(a3.isValid(),false);
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#setCity(java.lang.String)}.
	 */
	@Test
	public void testSetCity() {
		a2.setCity("PORTO");
		assertEquals(a2.getCity(), "PORTO");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#setCountry(java.lang.String)}.
	 */
	@Test
	public void testSetCountry() {
		a2.setCountry("ESPANHA");
		assertEquals(a2.getCountry(), "ESPANHA");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#setId(java.lang.Long)}.
	 */
	@Test
	public void testSetId() {
		a2.setId(5);
		assertEquals(a2.getId(), 5);
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#getId()}.
	 */
	@Test
	public void testGetId() {
		
		assertEquals(a2.getId(), 2);
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#setPostalCode(java.lang.String)}.
	 */
	@Test
	public void testSetPostalCode() {
		a2.setPostalCode("2222");
		assertEquals(a2.getPostalCode(), "2222");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#setStreet(java.lang.String)}.
	 */
	@Test
	public void testSetStreet() {
		a2.setStreet("NELAS");
		assertEquals(a2.getStreet(), "NELAS");
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObjectFalse() {
		assertEquals(a2.equals(a1),false);
	}
	
	/**
	 * Test method for {@link bsmanagement.model.Address#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObjectFalseWrongObject() {
		assertEquals(a2.equals(LocalDate.of(1999, 2, 1)),false);
	}
	
	/**
	 * Test method for {@link bsmanagement.model.Address#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObjectFalseNullCases() {
		assertEquals(a2.equals(null),false);
	}
	
	
	
	/**
	 * Test method for {@link bsmanagement.model.Address#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObjectTrue() {
		Address add = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		add.setId(1);
		assertEquals(add.equals(a1),true);
	}
	
	/**
	 * Test method for {@link bsmanagement.model.Address#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObjectSameObejct() {
	
		assertEquals(a1.equals(a1),true);
	}

	/**
	 * Test method for {@link bsmanagement.model.Address#toString()}.
	 */
	@Test
	public void testToString() {
		String expect = "Address ["+ 1 + "]-[addressDescription: "+ "CASA" + ", street: " + "RUA DO AMARO" + ", city: "
				+ "VISEU" + ", country: " + "PORTUGAL" + ", postalCode: " + "3550-444" + "]"; 
		assertEquals(a1.toString(),expect);
	}

}

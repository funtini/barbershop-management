package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Product.productType;
import bsmanagement.model.Customer;
import bsmanagement.model.Product;


/**
 * 
 * Unit tests for Product Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ProductTest {
	
	Product p1;
	Product p2;
	Product p3;
	Product p4;
	

	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * 
	 * <p>Product [p1] -> ["CORTE COM LAVAGEM",'HAIRCUT',15] </p>
	 * <p>Product [p2] -> ["CORTE SIMPLES",'HAIRCUT',10] </p>
	 * <p>Product [p3] -> ["BARBA",'SHAVE',7] </p>
	 * <p>Product [p4] -> ["SHAMPOO MEN",'EXTRA',12] </p>
	 * 
	 */
	@Before
	public void setUp() {
		
		Product.setStartIdGenerator(1);
		
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		p3 = new Product("BARBA",productType.SHAVE,7);
		p4 = new Product("SHAMPOO MEN",productType.EXTRA,12);
		
		p1.setId(1);
		p2.setId(2);
		p3.setId(3);
		p4.setId(4);
	}
	
	
	/**
	 * <h2>getId() and setId() method test</h2>
	 * 
	 * <p>Description
	 * 
	 */
	@Test 
	public void testGetnSetId() {
		//Given:
		assertEquals(p1.getId(),1);	
		assertEquals(p2.getId(),2);	
		assertEquals(p3.getId(),3);
		//When:
		p1.setId(12);
		p2.setId(23);
		p3.setId(34);
		//Then:
		assertEquals(p1.getId(),12);	
		assertEquals(p2.getId(),23);	
		assertEquals(p3.getId(),34);
	}
	
	
	/**
	 * <h2>getName() and setName method test</h2>
	 */
	@Test 
	public void testGetnSetName() {
		
		//Given
		assertEquals(p1.getName(),"CORTE COM LAVAGEM");
		assertEquals(p2.getName(),"CORTE SIMPLES");
		assertEquals(p3.getName(),"BARBA");
		//When
		p1.setName("CORTE DELUXE");
		p3.setName("APARAR BARBA");
		//Then
		assertEquals(p1.getName(),"CORTE DELUXE");
		assertEquals(p3.getName(),"APARAR BARBA");
	}
	
	
	/**
	 * <h2>getType() and setType method test</h2>
	 */
	@Test 
	public void testGetnSetType() {
		
		//Given
		assertEquals(p1.getType(),productType.HAIRCUT);
		assertEquals(p3.getType(),productType.SHAVE);
		
		//When
		p1.setType(productType.SHAVE);
		p3.setType(productType.HAIRCUT);
		
		//Then
		assertEquals(p1.getType(),productType.SHAVE);
		assertEquals(p3.getType(),productType.HAIRCUT);
		
	}
	
	/**
	 * <h2>getPrice() and setPrice method test</h2>
	 */
	@Test 
	public void testGetnSetPrice() {
		//Given
		assertEquals(p1.getPrice(),15,0.0);
		assertEquals(p3.getPrice(),7,0.0);
		//When
		p1.setPrice(15.2);
		p3.setPrice(22.5);	
		//Then
		assertEquals(p1.getPrice(),15.2,0.0);
		assertEquals(p3.getPrice(),22.5,0.0);
	}
	
	/**
	 * <h2>getPrice() and setPrice method test</h2>
	 */
	@Test 
	public void testActivateDeactivate() {
		assertEquals(p1.isActive(),true);
		p1.deactivate();
		assertEquals(p1.isActive(),false);
		p1.activate();
		assertEquals(p1.isActive(),true);
		
	}
	

	/**
	 * <h2>equals() method test</h2>
	 * <p>test true cases</p>
	 */
	@Test
	public void testEqualsTrue() {
		assertEquals(p1.equals(p1),true);
		assertEquals(p2.equals(p2),true);

	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - different instances</p>
	 */
	@Test
	public void testEqualsFalseDifferentInstance() {
		assertEquals(p1.equals(p2),false);
		assertEquals(p3.equals(p2),false);
		assertEquals(p2.equals(p4),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null cases</p>
	 */
	@Test
	public void testEqualsNull() {
		assertEquals(p1.equals(null),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - different classes</p>
	 */
	@Test
	public void testEqualsFalseDifferentClasses() {
		Customer c1 = new Customer("Joao");
		assertEquals(p1.equals(c1),false);
	}

	/**
	 * <h2>toString() method test</h2>
	 */
	@Test
	public void testToString() {
		String expect = "Product [id=" + 1 + ", name=CORTE COM LAVAGEM" + ", type=" + p1.getType() + ", price=" + p1.getPrice() + "]";
		assertEquals(p1.toString(),expect);
	}
	
	/**
	 * <h2>hashCode() method test</h2>
	 */
	@Test
	public void testHashCode() {
		Product p5 = new Product("SHAMPOO MEN",productType.EXTRA,12);
		p5.setId(4);
		assertEquals(p4.hashCode(),p5.hashCode());
		assertNotEquals(p1.hashCode(),p2.hashCode());
	}

}

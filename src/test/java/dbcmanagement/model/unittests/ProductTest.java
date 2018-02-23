package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dbcmanagement.model.Product.productType;
import dbcmanagement.model.Product;


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
	}
	
	
	/**
	 * <h2>getId() method test</h2>
	 * 
	 * <p>Description
	 * 
	 */
	@Test 
	public void testGetId() {
		
		assertEquals(p1.getId(),1);	
		assertEquals(p2.getId(),2);	
		assertEquals(p3.getId(),3);
	}
	
	
	/**
	 * <h2>setStartIdGenerator() method test</h2>
	 * 
	 */
	@Test 
	public void testSetStartIdGenerator() {
		
		//Given
		assertEquals(p1.getId(),1);
		assertEquals(p2.getId(),2);
		assertEquals(p3.getId(),3);
		//When
		Product.setStartIdGenerator(10);
		Product e4 = new Product("GEL",productType.EXTRA,9);
		//Then
		assertEquals(e4.getId(),10);
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

}

package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dbcmanagement.model.Product;
import dbcmanagement.model.Product.productType;
import dbcmanagement.model.ProductList;


/**
 * 
 * Unit tests for ProductList Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ProductListTest {
	
	Product p1;
	Product p2;
	Product p3;
	Product p4;
	
	ProductList pList;
	List<Product> expect;
	List<Product> result;
	
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * 
	 * <p>Product [p1] -> ["CORTE COM LAVAGEM",'HAIRCUT',15] </p>
	 * <p>Product [p2] -> ["CORTE SIMPLES",'HAIRCUT',10] </p>
	 * <p>Product [p3] -> ["BARBA",'SHAVE',7] </p>
	 * <p>Product [p4] -> ["SHAMPOO MEN",'EXTRA',12] </p>
	 * 
	 * 
	 * 
	 */
	@Before
	public void setUp(){
		Product.setStartIdGenerator(1);
		
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		p3 = new Product("BARBA",productType.SHAVE,7);
		p4 = new Product("SHAMPOO MEN",productType.EXTRA,12);
		
		pList = new ProductList();
		expect = new ArrayList<>();
		
	}


	/**
	 * <h2>setListOfProducts() and getListOfProducts method test</h2>
	 * 
	 */
	@Test
	public void testGetandSetListOfProducts() {
		//Given
		assertEquals(pList.getListOfProducts().isEmpty(),true);
		//When
		expect.add(p1);
		expect.add(p2);
		pList.setListOfProducts(expect);
		//Then
		assertEquals(pList.getListOfProducts(),expect);		
	}

	

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}

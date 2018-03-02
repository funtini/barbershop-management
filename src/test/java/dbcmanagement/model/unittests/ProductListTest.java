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
		expect = new ArrayList<Product>();
		
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
	
	/**
	 * <h2>CreateProduct() method test</h2>
	 * 
	 */
	@Test
	public void testCreateProduct() {
		//Given: 
		Product pp = pList.createProduct("test", productType.EXTRA, 10);
		//When
		Product expect = new Product("test",productType.EXTRA,10);
		expect.setId(pp.getId());
		//Then
		assertEquals(pp,expect);		
	}
	
	/**
	 * <h2>addProduct() method test</h2>
	 * 
	 */
	@Test
	public void testAddProduct() {
		//Given: empty list
		assertEquals(pList.getListOfProducts().isEmpty(),true);
		//When: add 2 products
		expect.add(p1);
		expect.add(p2);
		pList.addProduct(p1);
		pList.addProduct(p2);
		//Then: 2 products are added to listofproducts
		assertEquals(pList.getListOfProducts(),expect);		
	}
	
	/**
	 * <h2>findProductById() method test</h2>
	 * 
	 */
	@Test
	public void testFindProductById() {
		//Given: list with 3 Products
		assertEquals(pList.getListOfProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p2);
		pList.addProduct(p3);
		assertEquals(pList.getListOfProducts().size(),3);
		
		//When: find products by ID
		Product expect1 = pList.findProductById(1);
		Product expect3 = pList.findProductById(3);
		
		//Then: get correct products 
		assertEquals(p1,expect1);	
		assertEquals(p3,expect3);		
	}
	
	/**
	 * <h2>searchProductByName() method test</h2>
	 * 
	 */
	@Test
	public void testSearchProductByName() {
		//Given: list with 3 Products
		assertEquals(pList.getListOfProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p2);
		pList.addProduct(p3);
		assertEquals(pList.getListOfProducts().size(),3);		
		//When: find products by name
		List<Product> expect1 = new ArrayList<>();
		List<Product> expect2 = new ArrayList<>();
		expect1.add(p1);
		expect2.add(p2);
		List<Product> result = pList.searchProductByName("CORTE COM LAVAGEM");
		List<Product> result2 = pList.searchProductByName("CORTE SIMPLES");
		
		//Then: get correct products 
		assertEquals(result,expect1);	
		assertEquals(result2,expect2);					
	}
	
	/**
	 * <h2>getProductListByType() method test</h2>
	 * 
	 */
	@Test
	public void testGetProductListByType() {
		//Given: list with 3 Products
		assertEquals(pList.getListOfProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p2);
		pList.addProduct(p3);
		assertEquals(pList.getListOfProducts().size(),3);		
		//When: find products by name
		List<Product> expect1 = new ArrayList<>();
		List<Product> expect2 = new ArrayList<>();
		expect1.add(p1);
		expect1.add(p2);
		expect2.add(p3);
		List<Product> result = pList.getProductListByType(productType.HAIRCUT);
		List<Product> result2 = pList.getProductListByType(productType.SHAVE);
		
		//Then: get correct products 
		assertEquals(result,expect1);	
		assertEquals(result2,expect2);					
	}

	

}

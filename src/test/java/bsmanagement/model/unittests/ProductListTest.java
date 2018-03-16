package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Product;
import bsmanagement.model.Product.productType;
import bsmanagement.model.ProductList;


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
		assertEquals(pList.getProducts().isEmpty(),true);
		//When
		expect.add(p1);
		expect.add(p2);
		pList.setProducts(expect);
		//Then
		assertEquals(pList.getProducts(),expect);		
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
		assertEquals(pList.getProducts().isEmpty(),true);
		//When: add 2 products (one of them is added 2 times to get a false result)
		expect.add(p1);
		expect.add(p2);
		assertEquals(pList.addProduct(p1),true);
		assertEquals(pList.addProduct(p1),false);//already in inventory
		assertEquals(pList.addProduct(p2),true);
		//Then: 2 products are added to listofproducts
		assertEquals(pList.getProducts(),expect);		
	}
	
	/**
	 * <h2>removeProduct() method test</h2>
	 * 
	 */
	@Test
	public void testRemoveProduct() {
		//Given: list with 2 products
		assertEquals(pList.getProducts().isEmpty(),true);
		assertEquals(pList.addProduct(p1),true);
		assertEquals(pList.addProduct(p2),true);
		//When: remove 1 product (2 times to get a false result)
		expect.add(p1);

		assertEquals(pList.removeProduct(p2),true);
		assertEquals(pList.removeProduct(p2),false);//already removed
		
		//Then: 1 products still in list
		assertEquals(pList.getProducts(),expect);		
	}
	
	/**
	 * <h2>findProductById() method test</h2>
	 * 
	 */
	@Test
	public void testFindProductById() {
		//Given: list with 3 Products
		assertEquals(pList.getProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p2);
		pList.addProduct(p3);
		assertEquals(pList.getProducts().size(),3);
		
		//When: find products by ID
		Product expect1 = pList.findProductById(1);
		Product expect3 = pList.findProductById(3);
		
		//Then: get correct products 
		assertEquals(p1,expect1);	
		assertEquals(p3,expect3);		
	}
	
	/**
	 * <h2>findProductById() method test</h2>
	 * 
	 * null case - no product found
	 */
	@Test
	public void testFindProductByIdNoProductFound() {
		//Given: list with 3 Products
		assertEquals(pList.getProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p2);
		pList.addProduct(p3);
		assertEquals(pList.getProducts().size(),3);
		
		//When: find products by ID
		Product expect = pList.findProductById(13);
		
		//Then: no product found = null result 
		assertEquals(null,expect);			
	}
	
	/**
	 * <h2>searchProductByName() method test</h2>
	 * 
	 */
	@Test
	public void testSearchProductByName() {
		//Given: list with 3 Products
		assertEquals(pList.getProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p2);
		pList.addProduct(p3);
		assertEquals(pList.getProducts().size(),3);		
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
		assertEquals(pList.getProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p2);
		pList.addProduct(p3);
		assertEquals(pList.getProducts().size(),3);		
		//When: find products by name
		List<Product> expect1 = new ArrayList<>();
		List<Product> expect2 = new ArrayList<>();
		expect1.add(p1);
		expect1.add(p2);
		expect2.add(p3);
		List<Product> result = pList.getProductsByType(productType.HAIRCUT);
		List<Product> result2 = pList.getProductsByType(productType.SHAVE);
		
		//Then: get correct products 
		assertEquals(result,expect1);	
		assertEquals(result2,expect2);					
	}
	
	/**
	 * <h2>getProductListOrderByPrice() method test</h2>
	 * 
	 */
	@Test
	public void testGetProductListOrderByPrice() {
		//Given: list with 3 Products
		assertEquals(pList.getProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p3);
		pList.addProduct(p2);
		p4.setPrice(10);
		pList.addProduct(p4);
		assertEquals(pList.getProducts().size(),4);		
		//When: find products by name
		List<Product> expect = new ArrayList<>();
		expect.add(p1);
		expect.add(p2);
		expect.add(p4);
		expect.add(p3);
		List<Product> result = pList.getProductsOrderByPrice();
		
		//Then: get correct products 
		assertEquals(result,expect);	
					
	}
	
	/**
	 * <h2>getProductListOrderByPrice() method test</h2>
	 * 
	 */
	@Test
	public void testGetProductListOrderByPriceOfType() {
		//Given: list with 3 Products
		assertEquals(pList.getProducts().isEmpty(),true);
		pList.addProduct(p1);
		pList.addProduct(p3);
		pList.addProduct(p2);
		assertEquals(pList.getProducts().size(),3);		
		//When: find products by name
		List<Product> expect = new ArrayList<>();
		expect.add(p1);
		expect.add(p2);
		List<Product> result = pList.getProductsOrderByPriceOfType(productType.HAIRCUT);
		//Then: get correct products 
		assertEquals(result,expect);	
					
	}

	

}

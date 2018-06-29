package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.jparepositories.classtests.ProductRepositoryClass;
import bsmanagement.model.Product;
import bsmanagement.model.Product.productType;
import bsmanagement.model.ProductService;


/**
 * 
 * Unit tests for ProductList Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ProductServiceTest {
	
	Product p1;
	Product p2;
	Product p3;
	Product p4;
	
	ProductService productService;
	ProductRepositoryClass productRepository;
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
		
		productService = new ProductService();
		productRepository = new ProductRepositoryClass();
		productService.setRepository(productRepository);
		expect = new ArrayList<Product>();
		
	}
	

	
	/**
	 * <h2>setStartIdGenerator() method test</h2>
	 * 
	 */
	@Test 
	public void testSetStartIdGenerator() {
		
		//Given
		productService.addProduct(p1);
		productService.addProduct(p2);
		productService.addProduct(p3);
		assertEquals(p1.getId(),1);
		assertEquals(p2.getId(),2);
		assertEquals(p3.getId(),3);
		//When
		Product.setStartIdGenerator(10);
		Product p5 = new Product("GEL",productType.EXTRA,9);
		productService.addProduct(p5);
		//Then
		assertEquals(p5.getId(),10);
	}


	/**
	 * <h2>setRepository()  method test</h2>
	 * 
	 */
	@Test
	public void testSetRepository() {
		//Given: empty service, and a repository with 3 products
		assertEquals(productService.getAllProducts().isEmpty(),true);
		ProductRepositoryClass productRepoTest = new ProductRepositoryClass();
		productRepoTest.save(p1);
		productRepoTest.save(p2);
		productRepoTest.save(p3);
		
		//When: set repository to service
		expect.add(p1);
		expect.add(p2);
		expect.add(p3);
		productService.setRepository(productRepoTest);
		//Then: service has 3 products
		result = productService.getAllProducts();
		assertEquals(result,expect);			
	}
	
	/**
	 * <h2>CreateProduct() method test</h2>
	 * 
	 */
	@Test
	public void testCreateProduct() {
		//Given: 
		Product pp = productService.createProduct("test", productType.EXTRA, 10);
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
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		//When: add 2 products (one of them is added 2 times to get a false result)
		expect.add(p1);
		expect.add(p2);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p1),false);//already in inventory
		assertEquals(productService.addProduct(p2),true);
		//Then: 2 products are added to listofproducts
		assertEquals(productService.getActiveProducts(),expect);		
	}
	
	/**
	 * <h2>removeProduct() method test</h2>
	 * 
	 */
	@Test
	public void testRemoveProduct() {
		//Given: list with 2 products
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		//When: remove 1 product (2 times to get a false result)
		expect.add(p1);

		productService.disableProduct(p2);
		
		//Then: 1 products still in list
		assertEquals(productService.getActiveProducts(),expect);
		expect.remove(p1);
		expect.add(p2);
		assertEquals(productService.getDisabledProducts(),expect);
	}
	
	/**
	 * <h2>findProductById() method test</h2>
	 * 
	 */
	@Test
	public void testFindProductById() {
		//Given: list with 3 Products
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		productService.addProduct(p1);
		productService.addProduct(p2);
		productService.addProduct(p3);
		assertEquals(productService.getActiveProducts().size(),3);
		
		//When: find products by ID
		Product expect1 = productService.findProductById(1);
		Product expect3 = productService.findProductById(3);
		
		//Then: get correct products 
		assertEquals(p1,expect1);	
		assertEquals(p3,expect3);		
	}
	
	
	/**
	 * <h2>updateProduct() method test</h2>
	 * 
	 */
	@Test
	public void testUpdateProduct() {
		//Given: list with 3 Products
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		assertEquals(productService.updateProduct(p1),false);
		productService.addProduct(p1);
		assertEquals(productService.getActiveProducts().size(),1);
		assertEquals(p1.getPrice(),15.0,0.0);
		Product pp1 = productService.findProductById(1);
		pp1.setPrice(10);
		
		//When: update product and find by ID

		assertEquals(productService.updateProduct(pp1),true);
		Product expect = productService.findProductById(1);
		
		//Then: the price has been updated 
		assertEquals(expect.getPrice(),10.0,0.0);	
	
		
	}
	
	/**
	 * <h2>findProductById() method test</h2>
	 * 
	 * null case - no product found
	 */
	@Test
	public void testFindProductByIdNoProductFound() {
		//Given: list with 3 Products
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		productService.addProduct(p1);
		productService.addProduct(p2);
		productService.addProduct(p3);
		assertEquals(productService.getActiveProducts().size(),3);
		
		//When: find products by ID
		Product expect = productService.findProductById(13);
		
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
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		productService.addProduct(p1);
		productService.addProduct(p2);
		productService.addProduct(p3);
		assertEquals(productService.getActiveProducts().size(),3);		
		//When: find products by name
		List<Product> expect1 = new ArrayList<>();
		List<Product> expect2 = new ArrayList<>();
		expect1.add(p1);
		expect2.add(p2);
		List<Product> result = productService.searchProductByName("CORTE COM LAVAGEM");
		List<Product> result2 = productService.searchProductByName("CORTE SIMPLES");
		
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
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		productService.addProduct(p1);
		productService.addProduct(p2);
		productService.addProduct(p3);
		assertEquals(productService.getActiveProducts().size(),3);		
		//When: find products by name
		List<Product> expect1 = new ArrayList<>();
		List<Product> expect2 = new ArrayList<>();
		expect1.add(p1);
		expect1.add(p2);
		expect2.add(p3);
		List<Product> result = productService.getProductsByType(productType.HAIRCUT);
		List<Product> result2 = productService.getProductsByType(productType.SHAVE);
		
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
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		productService.addProduct(p1);
		productService.addProduct(p3);
		productService.addProduct(p2);
		p4.setPrice(10);
		productService.addProduct(p4);
		assertEquals(productService.getActiveProducts().size(),4);		
		//When: find products by name
		List<Product> expect = new ArrayList<>();
		expect.add(p1);
		expect.add(p2);
		expect.add(p4);
		expect.add(p3);
		List<Product> result = productService.getProductsOrderByPrice();
		
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
		assertEquals(productService.getActiveProducts().isEmpty(),true);
		productService.addProduct(p1);
		productService.addProduct(p3);
		productService.addProduct(p2);
		assertEquals(productService.getActiveProducts().size(),3);		
		//When: find products by name
		List<Product> expect = new ArrayList<>();
		expect.add(p1);
		expect.add(p2);
		List<Product> result = productService.getProductsOrderByPriceOfType(productType.HAIRCUT);
		//Then: get correct products 
		assertEquals(result,expect);	
					
	}

	

}

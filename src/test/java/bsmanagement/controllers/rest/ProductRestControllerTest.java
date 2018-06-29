package bsmanagement.controllers.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bsmanagement.dto.rest.ProductRestDTO;
import bsmanagement.jparepositories.classtests.ProductRepositoryClass;
import bsmanagement.model.Product;
import bsmanagement.model.ProductService;
import bsmanagement.model.Product.productType;

public class ProductRestControllerTest {

	Product p1;
	Product p2;
	Product p3;
	Product p4;
	
	ProductService productService;
	ProductRepositoryClass productRepository;
	ProductRestController prc;
	List<ProductRestDTO> expectedList;
	ProductRestDTO expectedRestDTO;
	ResponseEntity<List<ProductRestDTO>> responseList;
	ResponseEntity<ProductRestDTO> response;
	
	
	/**
	 * <h2>Setup for all controller tests: </h2>
	 * 
	 * <p>Product [p1] -> ["CORTE COM LAVAGEM",'HAIRCUT',15] </p>
	 * <p>Product [p2] -> ["CORTE SIMPLES",'HAIRCUT',10] </p>
	 * <p>Product [p3] -> ["BARBA",'SHAVE',7] </p>
	 * <p>Product [p4] -> ["SHAMPOO MEN",'EXTRA',12] </p>
	 * 
	 */
	@Before
	public void setUp() {
		productService = new ProductService();
		productRepository = new ProductRepositoryClass();
		productService.setRepository(productRepository);
		prc = new ProductRestController(productService);
	
		expectedList = new ArrayList<>();
		Product.setStartIdGenerator(1);
		
		p1 = productService.createProduct("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = productService.createProduct("CORTE SIMPLES",productType.HAIRCUT,10);
		p3 = productService.createProduct("BARBA",productType.SHAVE,7);
		p4 = productService.createProduct("SHAMPOO MEN",productType.EXTRA,12);
		
	}

	
	/**
	 * testListAllProducts() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call listAllProducts() controller</p>
	 * <p>THEN: Get a list of those products</p>
	 */
	@Test
	public void testListAllProducts() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		responseList = prc.listAllProducts();
		
		//THEN
		expectedList.add(productService.findProductById(1).toRestDTO());
		expectedList.add(productService.findProductById(2).toRestDTO());
		expectedList.add(productService.findProductById(3).toRestDTO());
		expectedList.add(productService.findProductById(4).toRestDTO());
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(responseList.getBody(),expectedList);
	}

	
	/**
	 * testAddProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call addProducts() controller with valid input fields</p>
	 * <p>THEN: get response code OK and List of products has increased to 5</p>
	 */
	@Test
	public void testAddProductSuccess() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		ProductRestDTO newProduct = new ProductRestDTO();
		newProduct.setName("Product Test");
		newProduct.setPrice(10);
		newProduct.setType("shave");

		response = prc.addProduct(newProduct);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(productService.getAllProducts().size(),5);	
	}
	
	/**
	 * testAddProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call addProducts() controller with invalid input type</p>
	 * <p>THEN: get response code BAD_REQUEST and List of products still has 4 length</p>
	 */
	@Test
	public void testAddProductBadRequestInvalidType() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		ProductRestDTO newProduct = new ProductRestDTO();
		newProduct.setName("Product Test");
		newProduct.setPrice(10);
		newProduct.setType("invalid TYPE");

		response = prc.addProduct(newProduct);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
		assertEquals(productService.getAllProducts().size(),4);	
	}
	
	/**
	 * testAddProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call addProducts() controller with invalid input type</p>
	 * <p>THEN: get response code BAD_REQUEST and List of products still has 4 length</p>
	 */
	@Test
	public void testAddProductBadRequestEmptyType() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		ProductRestDTO newProduct = new ProductRestDTO();
		newProduct.setName("Product Test");
		newProduct.setPrice(10);
		

		response = prc.addProduct(newProduct);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
		assertEquals(productService.getAllProducts().size(),4);	
	}
	
	/**
	 * testAddProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call addProducts() controller with invalid input price</p>
	 * <p>THEN: get response code BAD_REQUEST and List of products still has 4 length</p>
	 */
	@Test
	public void testAddProductBadRequestEmptyPrice() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		ProductRestDTO newProduct = new ProductRestDTO();
		newProduct.setName("Product Test");
		newProduct.setType("shave");

		response = prc.addProduct(newProduct);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
		assertEquals(productService.getAllProducts().size(),4);	
	}

	/**
	 * testAddProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call addProducts() controller with invalid input name</p>
	 * <p>THEN: get response code BAD_REQUEST and List of products still has 4 length</p>
	 */
	@Test
	public void testAddProductBadRequestEmptyName() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		ProductRestDTO newProduct = new ProductRestDTO();
		
		newProduct.setPrice(10);
		newProduct.setType("haircut");

		response = prc.addProduct(newProduct);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
		assertEquals(productService.getAllProducts().size(),4);	
	}
	
	/**
	 * testListActiveProducts() controller
	 * 
	 * <p>GIVEN: Added 4 products to service which 3 are active</p>
	 * <p>WHEN: call listACtiveProducts() controller</p>
	 * <p>THEN: Get a list of those 3 products active</p>
	 */
	@Test
	public void testListActiveProducts() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		productService.findProductById(3).deactivate();
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		responseList = prc.listActiveProducts();
		
		//THEN
		expectedList.add(productService.findProductById(1).toRestDTO());
		expectedList.add(productService.findProductById(2).toRestDTO());
		expectedList.add(productService.findProductById(4).toRestDTO());
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(responseList.getBody(),expectedList);		
	}

	
	/**
	 * testGetProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service which 3 are active</p>
	 * <p>WHEN: call getProduct() controller with valid ID</p>
	 * <p>THEN: Get a respective ProductRestDTO and response code OK</p>
	 */
	@Test
	public void testGetProductSuccess() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		response = prc.getProduct(1);
		
		//THEN
		expectedRestDTO = productService.findProductById(1).toRestDTO();
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(response.getBody(),expectedRestDTO);			
	}
	
	/**
	 * testGetProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service which 3 are active</p>
	 * <p>WHEN: call getProduct() controller with invalid ID</p>
	 * <p>THEN: Get response code NOT FOUND</p>
	 */
	@Test
	public void testGetProductNotFoundId() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		response = prc.getProduct(12);
		
		//THEN
	
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
				
	}

	
	/**
	 * testListProductsByType() controller
	 * 
	 * <p>GIVEN: Added 4 products of different types</p>
	 * <p>WHEN: call listProductsByType() controller</p>
	 * <p>THEN: Get a list of 2 products of type specified</p>
	 */
	@Test
	public void testListProductsByTypeSuccess() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		responseList = prc.listProductsByType("haircut");
		
		//THEN
		expectedList.add(productService.findProductById(1).toRestDTO());
		expectedList.add(productService.findProductById(2).toRestDTO());
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(responseList.getBody(),expectedList);		
	}
	
	/**
	 * testListProductsByType() controller
	 * 
	 * <p>GIVEN: Added 4 products of different types</p>
	 * <p>WHEN: call listProductsByType() controller with invalid type</p>
	 * <p>THEN: response code BAD_REQUEST</p>
	 */
	@Test
	public void testListProductsByTypeBadRequest() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		
		//WHEN
		responseList = prc.listProductsByType("bad request");
		
		//THEN
		assertEquals(responseList.getStatusCode(),HttpStatus.BAD_REQUEST);
	
	}


	/**
	 * testRemoveProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call removeProducts() controller with valid ID</p>
	 * <p>THEN: get response code OK and List of Active products has decreased to 3</p>
	 */
	@Test
	public void testRemoveProductSuccess() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		assertEquals(productService.getActiveProducts().size(),4);	
		
		//WHEN

		response = prc.removeProduct(2);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		assertEquals(productService.getAllProducts().size(),4);	
		assertEquals(productService.getActiveProducts().size(),3);			
	}
	
	/**
	 * testRemoveProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call removeProducts() controller with valid ID</p>
	 * <p>THEN: get response code NOT_FOUND and List of Active products still has 4 products</p>
	 */
	@Test
	public void testRemoveProductNotFoundInvalidId() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		assertEquals(productService.getActiveProducts().size(),4);	
		
		//WHEN

		response = prc.removeProduct(22);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
		assertEquals(productService.getAllProducts().size(),4);	
		assertEquals(productService.getActiveProducts().size(),4);			
	}

	/**
	 * testEditProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call editProduct() controller with valid input fields on a ProductRestDTO</p>
	 * <p>THEN: get response code ACCEPTED and updated ProductRestDTO</p>
	 */
	@Test
	public void testEditProductSuccess() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		assertEquals(productService.getActiveProducts().size(),4);	
		
		//WHEN
		ProductRestDTO editedProduct = new ProductRestDTO();
		editedProduct.setName("Product Test");
		editedProduct.setPrice(10);
		editedProduct.setType("shave");
		response = prc.editProduct(2, editedProduct);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
		assertEquals(response.getBody(),productService.findProductById(2).toRestDTO());	
				
	}
	
	
	/**
	 * testEditProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call editProduct() controller with invalid input type</p>
	 * <p>THEN: get response code BAD_REQUEST </p>
	 */
	@Test
	public void testEditProductInvalidType() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		assertEquals(productService.getActiveProducts().size(),4);	
		
		//WHEN
		ProductRestDTO editedProduct = new ProductRestDTO();
		editedProduct.setName("Product Test");
		editedProduct.setPrice(10);
		editedProduct.setType("Invalid TYPE");
		response = prc.editProduct(2, editedProduct);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
				
	}
	
	
	/**
	 * testEditProduct() controller
	 * 
	 * <p>GIVEN: Added 4 products to service</p>
	 * <p>WHEN: call editProduct() controller with invalid input ID</p>
	 * <p>THEN: get response code NOT_FOUND </p>
	 */
	@Test
	public void testEditProductNotFoundInvalidID() {
		//GIVEN
		assertEquals(productService.getAllProducts().isEmpty(),true);
		assertEquals(productService.addProduct(p1),true);
		assertEquals(productService.addProduct(p2),true);
		assertEquals(productService.addProduct(p3),true);
		assertEquals(productService.addProduct(p4),true);
		assertEquals(productService.getAllProducts().size(),4);	
		assertEquals(productService.getActiveProducts().size(),4);	
		
		//WHEN
		ProductRestDTO editedProduct = new ProductRestDTO();
		editedProduct.setName("Product Test");
		editedProduct.setPrice(10);
		editedProduct.setType("shave");
		response = prc.editProduct(22, editedProduct);
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
				
	}
	
	

}

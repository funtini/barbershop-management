package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import dbcmanagement.model.Customer;
import dbcmanagement.model.Product.productType;
import dbcmanagement.model.Product;
import dbcmanagement.model.Sale;


/**
 * 
 * Unit tests for Sale Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class SaleTest {

	
	Customer c1;
	Customer c2;
	
	LocalDate birthdate1;
	LocalDate birthdate2;
	
	LocalDateTime d1;
	LocalDateTime d2;
	LocalDateTime d3;

	Product p1;
	Product p2;
	
	Sale s1;
	Sale s2;
	Sale s3;
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>DateTime [d1] : 2018/03/11 - 10:30 </p>
	 * <p>DateTime [d2] : 2018/03/12 - 11:35 </p>
	 * <p>DateTime [d3] : 2018/03/12 - 12:15</p>
	 * <p>Product [p1] : ["CORTE COM LAVAGEM",'HAIRCUT',15] </p>
	 * <p>Product [p2] : ["CORTE SIMPLES",'HAIRCUT',10] </p>
	 * <p>Customer [c1] -> ['Joao',birthdate1,"Mangualde","914047935"] </p>
	 * <p>Customer [c2] -> ['Ana',birthdate2,"Porto","966677722"] </p>
	 * 
	 * <p>Sale [s1] -> [d1,c1,p1] </p>
	 * <p>Sale [s2] -> [d2,c2,p2] </p>
	 * <p>Sale [s3] -> [d3,p1] </p>
	 */
	@Before
	public void setUp() {
		
		Sale.setStartIdGenerator(1);
		
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		
		d1 = LocalDateTime.of(2018, 3, 11,10,30);
		d2 = LocalDateTime.of(2018, 3, 12,11,35);
		d3 = LocalDateTime.of(2018, 3, 12,12,15);
		
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		
		s1 = new Sale(d1,c1,p1);
		s2 = new Sale(d2,c2,p2);
		s3 = new Sale(d3,p1);

	}
	
	/**
	 * <h2>getId() method test</h2>
	 * 
	 * <p>Description
	 * 
	 */
	@Test 
	public void testGetId() {
		
		assertEquals(s1.getId(),1);
		
		assertEquals(s2.getId(),2);
		
		assertEquals(s3.getId(),3);
	}
	
	
	/**
	 * <h2>setStartIdGenerator() method test</h2>
	 * 
	 */
	@Test 
	public void testSetStartIdGenerator() {
		
		//Given
		assertEquals(s1.getId(),1);
		assertEquals(s2.getId(),2);
		assertEquals(s3.getId(),3);
		//When
		Sale.setStartIdGenerator(10);
		Sale s4 = new Sale(d3,p1);
		//Then
		assertEquals(s4.getId(),10);
	}
	
	
	
	/**
	 * <h2>getDate() and setDate method test</h2>
	 */
	@Test 
	public void testGetnSetDate() {
		
		//Given
		assertEquals(s1.getDate(),d1);
		assertEquals(s2.getDate(),d2);
		assertEquals(s3.getDate(),d3);
		//When
		s1.setDate(d2);
		s3.setDate(d1);
		//Then
		assertEquals(s1.getDate(),d2);
		assertEquals(s3.getDate(),d1);
	}
	
	/**
	 * <h2>getCustomer() and setCustomer method test</h2>
	 */
	@Test 
	public void testGetnSetCustomer() {
		
		//Given
		assertEquals(s1.getCustomer(),c1);
		assertEquals(s2.getCustomer(),c2);
		assertEquals(s3.getCustomer(),null);
		//When
		s1.setCustomer(c2);
		s3.setCustomer(c1);
		//Then
		assertEquals(s1.getCustomer(),c2);
		assertEquals(s3.getCustomer(),c1);
	}
	
	/**
	 * <h2>getProduct() and setProduct method test</h2>
	 */
	@Test 
	public void testGetnSetProduct() {
		
		//Given
		assertEquals(s1.getProduct(),p1);
		assertEquals(s2.getProduct(),p2);
		assertEquals(s3.getProduct(),p1);
		//When
		s1.setProduct(p2);
		s3.setProduct(p2);
		//Then
		assertEquals(s1.getProduct(),p2);
		assertEquals(s3.getProduct(),p2);
	}
	
	
	/**
	 * <h2>getAmount() and setAmount method test</h2>
	 */
	@Test 
	public void testGetnSetAmount() {
		
		//Given
		assertEquals(s1.getAmount(),15,0.0);
		assertEquals(s2.getAmount(),10,0.0);
		assertEquals(s3.getAmount(),15,0.0);
		//When
		s1.setAmount(20.2);
		s3.setAmount(13.1);
		//Then
		assertEquals(s1.getAmount(),20.2,0.0);
		assertEquals(s3.getAmount(),13.1,0.0);
	}

}

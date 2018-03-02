package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dbcmanagement.model.Customer;
import dbcmanagement.model.Product;
import dbcmanagement.model.Sale;
import dbcmanagement.model.SaleRegistry;
import dbcmanagement.model.Product.productType;


/**
 * 
 * Unit tests for SaleList Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class SaleRegistryTest {
	
	Customer c1;
	Customer c2;
	
	LocalDate birthdate1;
	LocalDate birthdate2;
	
	LocalDateTime d1;
	LocalDateTime d2;
	LocalDateTime d3;
	LocalDateTime d4;

	Product p1;
	Product p2;
	
	Sale s1;
	Sale s2;
	Sale s3;
	Sale s4;
	
	List<Sale> expect;
	List<Sale> result;
	List<Sale> emptyList;
	SaleRegistry saleList;

	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>DateTime [d1] : 2018/03/11 - 10:30 </p>
	 * <p>DateTime [d2] : 2018/03/12 - 11:35 </p>
	 * <p>DateTime [d3] : 2018/03/12 - 12:15</p>
	 * <p>DateTime [d4] : 2017/02/22 - 15:15</p>
	 * 
	 * <p>Product [p1] : ["CORTE COM LAVAGEM",'HAIRCUT',15] </p>
	 * <p>Product [p2] : ["CORTE SIMPLES",'HAIRCUT',10] </p>
	 * 
	 * <p>Customer [c1] -> ['Joao',birthdate1,"Mangualde","914047935"] </p>
	 * <p>Customer [c2] -> ['Ana',birthdate2,"Porto","966677722"] </p>
	 * 
	 * <p>Sale [s1] -> [d1,c1,p1] </p>
	 * <p>Sale [s2] -> [d2,c2,p2] </p>
	 * <p>Sale [s3] -> [d3,p1] </p>
	 * <p>Sale [s3] -> [d4,c1,p2] </p>
	 */
	@Before
	public void setUp(){
		expect = new ArrayList<Sale>();
		result = new ArrayList<Sale>();
		emptyList = new ArrayList<Sale>();
		saleList = new SaleRegistry();
		saleList.getSaleList().clear();
		expect.clear();
		result.clear();
		
		Sale.setStartIdGenerator(1);
		
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		
		d1 = LocalDateTime.of(2018, 3, 11,10,30);
		d2 = LocalDateTime.of(2018, 3, 12,11,35);
		d3 = LocalDateTime.of(2018, 3, 12,12,15);
		d4 = LocalDateTime.of(2018, 2, 22,15,15);
		
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		
		s1 = new Sale(d1,c1,p1);
		s2 = new Sale(d2,c2,p2);
		s3 = new Sale(d3,p1);
		s4 = new Sale(d4,c1,p2);
		
		
	}

	/**
	 * <h2>getSaleList() and setSaleList method test</h2>
	 */
	@Test 
	public void testGetnSetSaleList() {
		
		//Given: empty list's
		assertEquals(saleList.getSaleList(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: set a list with 3 sales
		expect.add(s1);
		expect.add(s2);
		expect.add(s3);
		saleList.setListOfSales(expect);
		//Then: get a list with that 3 sales
		result = saleList.getSaleList();
		assertEquals(result,expect);
		assertEquals(expect.equals(emptyList),false);
	}
	
	/**
	 * <h2>addSale() method test</h2>
	 */
	@Test 
	public void testAddSale() {
		
		//Given: empty list's
		assertEquals(saleList.getSaleList(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: add 3 sales
		Sale.setStartIdGenerator(1);
		saleList.addSale(saleList.createSale(d1,c1,p1));
		saleList.addSale(saleList.createSale(d2,c2,p2));
		saleList.addSale(saleList.createSale(d3,p1));
		expect.add(s1);
		expect.add(s2);
		expect.add(s3);
		//Then: get a list with that 3 sales
		result = saleList.getSaleList();
		assertEquals(result,expect);
	}
	

}

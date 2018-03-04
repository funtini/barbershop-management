package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
	
		//When: add 3 sales (first one is added 2 times - "saleTest" - to verify the false result)
		Sale.setStartIdGenerator(1);
		Sale saleTest = saleList.createSale(d1,c1,p1);
		assertEquals(saleList.addSale(saleTest),true);
		assertEquals(saleList.addSale(saleTest),false); //already added this sale - false
		assertEquals(saleList.addSale(saleList.createSale(d2,c2,p2)),true);
		assertEquals(saleList.addSale(saleList.createSale(d3,p1)),true);
		expect.add(s1);
		expect.add(s2);
		expect.add(s3);
		//Then: get a list with that 3 sales
		result = saleList.getSaleList();
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>findSaleOf() method test</h2>
	 */
	@Test 
	public void testFindSaleOf() {
		
		//Given: saleList with 4 sales (3 of March/2018 and 1 of February/2017)
		assertEquals(saleList.getSaleList(),emptyList);
		assertEquals(emptyList,expect);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleList.addSale(saleList.createSale(d1,c1,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d2,c2,p2)),true);
		assertEquals(saleList.addSale(saleList.createSale(d3,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d4,p1)),true);
	
		//When: find sales of March/2018
		result = saleList.findSalesOf(YearMonth.of(2018, 3));
		expect.add(s1);
		expect.add(s2);
		expect.add(s3);
		//Then: get a list with that 3 sales
		
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>findSaleOf() method test</h2>
	 */
	@Test 
	public void testFindSaleByCustomer() {
		
		//Given: saleList with 4 sales (2 of Customer (c1), 1 of Customer (c2) and other with no customer)
		assertEquals(saleList.getSaleList(),emptyList);
		assertEquals(emptyList,expect);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleList.addSale(saleList.createSale(d1,c1,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d2,c2,p2)),true);
		assertEquals(saleList.addSale(saleList.createSale(d3,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d4,c1,p2)),true);
	
		//When: find sales of Customer c1
		result = saleList.findSalesByCustomer(c1);
		
		//Then: get a list of 2 sales
		expect.add(s1);
		expect.add(s4);
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>sumAllAmounts() method test</h2>
	 */
	@Test 
	public void testSumAllAmounts() {
		
		//Given: saleList with 4 sales (2 of Customer (c1), 1 of Customer (c2) and other with no customer)
		assertEquals(saleList.getSaleList(),emptyList);
		assertEquals(emptyList,expect);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleList.addSale(saleList.createSale(d1,c1,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d2,c2,p2)),true);
		assertEquals(saleList.addSale(saleList.createSale(d3,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d4,c1,p2)),true);
	
		//When: get the sum of all sales amounts
		double sumResult = saleList.sumAllAmounts();
		//Then: get a result of 50 euros
		double sumExpect = 50;
		assertEquals(sumResult,sumExpect,0.0);
	}
	
	/**
	 * <h2>findSaleById() method test</h2>
	 */
	@Test 
	public void testFindSaleById() {
		
		//Given: saleList with 2 sales
		assertEquals(saleList.getSaleList(),emptyList);
		assertEquals(emptyList,expect);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleList.addSale(saleList.createSale(d1,c1,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d2,c2,p2)),true);
		//When: find sales by ID
		Sale res = saleList.findSaleById(2);
		Sale res2 = saleList.findSaleById(1);
		//Then: get the expected sales
		
		assertEquals(res,s2);
		assertEquals(res2,s1);
	}
	
	/**
	 * <h2>findSaleById()  method test</h2>
	 * 
	 * Null case - no sale found
	 */
	@Test 
	public void testFindSaleByIdNullCase() {
		
		//Given: saleList with 2 sales
		assertEquals(saleList.getSaleList(),emptyList); //check empty saleList
		assertEquals(emptyList,expect);					//check empty test lists
		Sale.setStartIdGenerator(1);		
		assertEquals(saleList.addSale(saleList.createSale(d1,c1,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d2,c2,p2)),true);
		
		
		//When: find sales by ID
		Sale res = saleList.findSaleById(20);
		//Then: get the expected sales
		
		assertEquals(res,null);
	}
	
	/**
	 * <h2>findSaleBetweenDates() method test</h2>
	 */
	@Test 
	public void testFindSaleBetweenDates() {
		
		//Given: saleList with 4 sales
		assertEquals(saleList.getSaleList(),emptyList);	//check empty saleList
		assertEquals(emptyList,expect);					//check empty test lists
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleList.addSale(saleList.createSale(d1,c1,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d2,c2,p2)),true);
		assertEquals(saleList.addSale(saleList.createSale(d3,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d4,c1,p2)),true);
		//When: find sales between dates
		result = saleList.findSalesBetweenDates(LocalDate.of(2017, 2, 1), LocalDate.of(2018, 3, 11));
		expect.add(s1);
		expect.add(s4);
		//Then: get the expected sales
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>findSaleBetweenDates() method test</h2>
	 * 
	 * Invalid input date - give emptyList
	 */
	@Test 
	public void testFindSaleBetweenDatesInvalidDates() {
		
		//Given: saleList with 4 sales
		assertEquals(saleList.getSaleList(),emptyList);	//check empty saleList
		assertEquals(emptyList,expect);					//check empty test lists
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleList.addSale(saleList.createSale(d1,c1,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d2,c2,p2)),true);
		assertEquals(saleList.addSale(saleList.createSale(d3,p1)),true);
		assertEquals(saleList.addSale(saleList.createSale(d4,c1,p2)),true);
		//When: find sales between invalid dates
		result = saleList.findSalesBetweenDates(LocalDate.of(2019, 2, 1), LocalDate.of(2017, 3, 11));
		//Then: get an empty List
		assertEquals(result,expect);
	}
	

}

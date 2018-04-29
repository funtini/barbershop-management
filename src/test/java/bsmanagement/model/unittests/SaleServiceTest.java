package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.jparepositories.classtests.PaymentRepositoryClass;
import bsmanagement.jparepositories.classtests.SaleRepositoryClass;
import bsmanagement.model.Customer;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;
import bsmanagement.model.Product.productType;


/**
 * 
 * Unit tests for Sale Service Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class SaleServiceTest {
	
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
	
	PaymentMethod cash;
	
	Sale s1;
	Sale s2;
	Sale s3;
	Sale s4;
	
	List<Sale> expect;
	List<Sale> result;
	List<Sale> emptyList;
	SaleService saleService;
	SaleRepositoryClass saleRepository;
	PaymentRepositoryClass paymentRepository;

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
	 * <p>Payment [cash] -> ['CASH',0,0] </p>
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
		saleService = new SaleService();
		saleRepository = new SaleRepositoryClass();
		paymentRepository = new PaymentRepositoryClass();
		saleService.setRepository(saleRepository);
		saleService.setPaymentRepository(paymentRepository);
		saleService.getSales().clear();
		
		expect.clear();
		result.clear();
		
		Sale.setStartIdGenerator(1);
		
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		c1.setId(1);
		c2.setId(2);
		
		d1 = LocalDateTime.of(2018, 3, 11,10,30);
		d2 = LocalDateTime.of(2018, 3, 12,11,35);
		d3 = LocalDateTime.of(2018, 3, 12,12,15);
		d4 = LocalDateTime.of(2018, 2, 22,15,15);
		
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		p1.setId(1);
		p2.setId(2);
		
		cash = new PaymentMethod("CASH",0.0,0.0);
		
		s1 = new Sale(d1,c1,p1,cash,null);
		s2 = new Sale(d2,c2,p2,cash,null);
		s3 = new Sale(d3,p1,cash,null);
		s4 = new Sale(d4,c1,p2,cash,null);
		s1.setId(1);
		s2.setId(2);
		s3.setId(3);
		s4.setId(4);
		
		
	}
	
	

	/**
	 * <h2>setRepository() method test</h2>
	 */
	@Test 
	public void testSetRepository() {
		
		//Given: empty service, and a repository with 3 products
		assertEquals(saleService.getSales().isEmpty(),true);
		SaleRepositoryClass saleRepoTest = new SaleRepositoryClass();
		saleRepoTest.save(s1);
		saleRepoTest.save(s2);
		saleRepoTest.save(s3);
		
		//When: set repository to service
		expect.add(s1);
		expect.add(s2);
		expect.add(s3);
		saleService.setRepository(saleRepoTest);
		//Then: service has 3 products
		result = saleService.getSales();
		assertEquals(result,expect);				
	}
	
	
	/**
	 * <h2>setStartIdGenerator() method test</h2>
	 * 
	 */
	@Test 
	public void testSetStartIdGenerator() {
		saleService.addSale(s1);
		saleService.addSale(s2);
		saleService.addSale(s3);
		//Given
		assertEquals(s1.getId(),1);
		assertEquals(s2.getId(),2);
		assertEquals(s3.getId(),3);
		//When
		Sale.setStartIdGenerator(10);
		Sale s4 = new Sale(d3,p1,cash,null);
		saleService.addSale(s4);
		//Then
		assertEquals(s4.getId(),10);
	}
	
	/**
	 * <h2>addSale() method test</h2>
	 */
	@Test 
	public void testAddSale() {
		
		//Given: empty list's
		assertEquals(saleService.getSales(),emptyList);
		assertEquals(emptyList,expect);
	
		//When: add 3 sales (first one is added 2 times - "saleTest" - to verify the false result)
		Sale.setStartIdGenerator(1);
		Sale saleTest = saleService.createSale(d1,c1,p1,cash,null);
		assertEquals(saleService.addSale(saleTest),true);
		assertEquals(saleService.addSale(saleTest),false); //already added this sale - false
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		expect.add(s1);
		expect.add(s2);
		expect.add(s3);
		//Then: get a list with that 3 sales
		result = saleService.getSales();
		assertEquals(result,expect);
	}
	
	
	/**
	 * <h2>findSaleOf() method test</h2>
	 */
	@Test 
	public void testFindSaleOf() {
		
		//Given: saleList with 4 sales (3 of March/2018 and 1 of February/2017)
		assertEquals(saleService.getSales(),emptyList);
		assertEquals(emptyList,expect);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4,p1,cash,null)),true);
	
		//When: find sales of March/2018
		result = saleService.findSalesOf(YearMonth.of(2018, 3));
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
		assertEquals(saleService.getSales(),emptyList);
		assertEquals(emptyList,expect);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4,c1,p2,cash,null)),true);
	
		//When: find sales of Customer c1
		result = saleService.findSalesByCustomer(c1);
		
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
		assertEquals(saleService.getSales(),emptyList);
		assertEquals(emptyList,expect);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4,c1,p2,cash,null)),true);
	
		//When: get the sum of all sales amounts
		double sumResult = saleService.sumAllAmounts();
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
		assertEquals(saleService.getSales(),emptyList);
		assertEquals(emptyList,expect);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,cash,null)),true);
		//When: find sales by ID
		Sale res = saleService.findSaleById(2);
		Sale res2 = saleService.findSaleById(1);
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
		assertEquals(saleService.getSales(),emptyList); //check empty saleList
		assertEquals(emptyList,expect);					//check empty test lists
		Sale.setStartIdGenerator(1);		
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,cash,null)),true);
		
		
		//When: find sales by ID
		Sale res = saleService.findSaleById(20);
		//Then: get the expected sales
		
		assertEquals(res,null);
	}
	
	/**
	 * <h2>findSaleBetweenDates() method test</h2>
	 */
	@Test 
	public void testFindSaleBetweenDates() {
		
		//Given: saleList with 4 sales
		assertEquals(saleService.getSales(),emptyList);	//check empty saleList
		assertEquals(emptyList,expect);					//check empty test lists
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4,c1,p2,cash,null)),true);
		//When: find sales between dates
		result = saleService.findSalesBetweenDates(LocalDate.of(2017, 2, 1), LocalDate.of(2018, 3, 11));
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
		assertEquals(saleService.getSales(),emptyList);	//check empty saleList
		assertEquals(emptyList,expect);					//check empty test lists
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4,c1,p2,cash,null)),true);
		//When: find sales between invalid dates
		result = saleService.findSalesBetweenDates(LocalDate.of(2019, 2, 1), LocalDate.of(2017, 3, 11));
		//Then: get an empty List
		assertEquals(result,expect);
	}
	
	
	/**
	 * <h2>calculateTotalFeeAmounts() method test</h2>
	 */
	@Test 
	public void testCalculateTotalFeeAmounts() {
		
		//Given: saleList with 6 sales (5 payed by creditcard, 1 payed by cash)
		assertEquals(saleService.getSales(),emptyList);
		assertEquals(emptyList,expect);
		PaymentMethod card = new PaymentMethod("CREDIT CARD",2.0,0.55);
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,card,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,card,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4,c1,p2,card,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4.plusDays(5),c1,p2,card,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4.plusDays(10),c1,p2,card,null)),true);
	
		//When: get the sum of all Fee amounts
		double sumResult = saleService.calculateAllTimeFeeAmount();
		//Then: get a total fee amount of 2.75 euros
		double sumExpect = 2.75;
		assertEquals(sumResult,sumExpect,0.0);
	}
	
	
	/**
	 * <h2>findSalesPayedBy() method test</h2>
	 */
	@Test 
	public void testFindSalesPayedBy() {
		List<Sale> creditSales = new ArrayList<>();
		List<Sale> expectSales = new ArrayList<>();
		//Given: saleList with 6 sales (5 payed by creditcard, 1 payed by cash)
		assertEquals(saleService.getSales(),emptyList);
		assertEquals(emptyList,expect);
		PaymentMethod creditCard = new PaymentMethod("CREDIT CARD",2.0,0.55);
		
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,creditCard,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,creditCard,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4,c1,p2,creditCard,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4.plusDays(5),c1,p2,creditCard,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4.plusDays(10),c1,p2,creditCard,null)),true);
	
		//When: find all sales payed by credit
		creditSales = saleService.findSalesPayedBy(creditCard);
		expectSales.add(saleService.getSales().get(0));
		expectSales.add(saleService.getSales().get(1));
		expectSales.add(saleService.getSales().get(3));
		expectSales.add(saleService.getSales().get(4));
		expectSales.add(saleService.getSales().get(5));
		//Then: get a list of sales payed by credit card
		
		assertEquals(creditSales,expectSales);
	}
	
	
	/**
	 * <h2>summAllAmountsPayedBy() method test</h2>
	 */
	@Test 
	public void testSumAmountsPayedBy() {
		//Given: saleList with 6 sales (5 payed by creditcard, 1 payed by cash)
		assertEquals(saleService.getSales(),emptyList);
		assertEquals(emptyList,expect);
		PaymentMethod creditCard = new PaymentMethod("CREDIT CARD",2.0,0.55);
		
		
		Sale.setStartIdGenerator(1);
		assertEquals(saleService.addSale(saleService.createSale(d1,c1,p1,creditCard,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d2,c2,p2,creditCard,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d3,p1,cash,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4,c1,p2,creditCard,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4.plusDays(5),c1,p2,creditCard,null)),true);
		assertEquals(saleService.addSale(saleService.createSale(d4.plusDays(10),c1,p2,creditCard,null)),true);
	
		//When: summ all sale's amounts payed by credit
		double creditSales = saleService.sumAllAmountsPayedBy(creditCard);
		double expected = 55;
		
		//Then: get a total amount of 55 euros
		
		assertEquals(creditSales,expected,0.0);
	}
	
	/**
	 * <h2>getAvailabePaymentMethods() method test</h2>
	 */
	@Test 
	public void testGetAvailabePaymentMethods() {
		//Given: saleList with 2 available payment methods (cash and card)
		PaymentMethod p1 = saleService.createPaymentMethod("CASH",0.0,0.0);
		PaymentMethod p2 = saleService.createPaymentMethod("CREDIT CARD",2.0,0.55);
		saleService.addPaymentMethod(p1);
		saleService.addPaymentMethod(p2);
		
	
		//When: get available payment methods
		List<PaymentMethod> payments = saleService.getAvailablePaymentMethods();
		List<PaymentMethod> expected = new ArrayList<>();
		expected.add(cash);
		expected.add(new PaymentMethod("CREDIT CARD",2.0,0.55));
		
		//Then: compare list
		
		assertEquals(payments,expected);
	}
	
	/**
	 * <h2>getAvailabePaymentMethods() method test</h2>
	 */
	@Test 
	public void testAddPaymentMethods() {
		//Given: create 5  payment methods (cash, card, sameName, emptyName and nullName)
		PaymentMethod p1 = saleService.createPaymentMethod("CASH",0.0,0.0);
		PaymentMethod p2 = saleService.createPaymentMethod("CREDIT CARD",2.0,0.55);
		PaymentMethod sameName = saleService.createPaymentMethod("CREDIT CARD",0,0);
		PaymentMethod emptyName = saleService.createPaymentMethod("",0,0);
		PaymentMethod nullName = saleService.createPaymentMethod(null,0,0);
		
		//When:add all this payment methods
		
		assertEquals(saleService.addPaymentMethod(p1),true);
		assertEquals(saleService.addPaymentMethod(p1),false);
		assertEquals(saleService.addPaymentMethod(p2),true);
		assertEquals(saleService.addPaymentMethod(sameName),false);
		assertEquals(saleService.addPaymentMethod(emptyName),false);
		assertEquals(saleService.addPaymentMethod(nullName),false);
		
		List<PaymentMethod> payments = saleService.getAvailablePaymentMethods();
		List<PaymentMethod> expected = new ArrayList<>();
		expected.add(p1);
		expected.add(p2);
		
		//Then: compare list
		
		assertEquals(payments,expected);
	}
	
	
	

}

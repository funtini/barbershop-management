package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Customer;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product.productType;
import bsmanagement.model.Product;
import bsmanagement.model.Sale;
import bsmanagement.model.User;


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
	
	PaymentMethod cash;
	PaymentMethod card;
	
	User u1;
	
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
	 * <p>Payment [cash] -> ['CASH',0,0] </p>
	 * <p>Payment [card] -> ['CREDIT CARD',2,0.55] </p>
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
		c1.setId(1);
		c2.setId(2);
		
		u1 = new User("JOAO",birthdate1,"joao@domain.com","914047935","324666433");
		
		d1 = LocalDateTime.of(2018, 3, 11,10,30);
		d2 = LocalDateTime.of(2018, 3, 12,11,35);
		d3 = LocalDateTime.of(2018, 3, 12,12,15);
		
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		p1.setId(1);
		p2.setId(2);
		
		cash = new PaymentMethod("CASH",0.0,0.0);
		card = new PaymentMethod("CREDIT CARD",2.0,0.55);
		
		s1 = new Sale(d1,c1,p1,cash,null);
		s2 = new Sale(d2,c2,p2,cash,null);
		s3 = new Sale(d3,p1,cash,null);
		s1.setId(1);
		s2.setId(2);
		s3.setId(3);
		

	}
	
	/**
	 * <h2>getId() and setID() method test</h2>
	 * 
	 * <p>Description
	 * 
	 */
	@Test 
	public void testGetId() {
		
		assertEquals(s1.getId(),1);
		
		assertEquals(s2.getId(),2);
		
		assertEquals(s3.getId(),3);
		//When: we set new ID's
		s1.setId(5);
		s2.setId(10);
		s3.setId(22);
		//Then: they are changed
		assertEquals(s1.getId(),5);
		
		assertEquals(s2.getId(),10);
		
		assertEquals(s3.getId(),22);
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

	/**
	 * <h2>equals() method test</h2>
	 * <p>test true cases - same instance</p>
	 */
	@Test
	public void testEqualsTrue() {
		assertEquals(s1.equals(s1),true);
		assertEquals(s2.equals(s2),true);

	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - different instances</p>
	 */
	@Test
	public void testEqualsFalse() {
		assertEquals(s1.equals(s2),false);
		assertEquals(s3.equals(s2),false);
		assertEquals(s2.equals(s3),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null cases</p>
	 */
	@Test
	public void testEqualsNull() {
		assertEquals(s1.equals(null),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - different class</p>
	 */
	@Test
	public void testEqualsFalseDifferentClass() {
		assertEquals(s1.equals(c1),false);
	}
	
	/**
	 * <h2>toString() method test</h2>
	 */
	@Test
	public void testToString() {
		String expect = "Sale [id=" + 1 + ", date=" + s1.getDate() + ", customer=" + s1.getCustomer() + ", product=" + s1.getProduct() + ", amount="
				+ s1.getAmount() + "]";
		assertEquals(s1.toString(),expect);
	}

	
	/**
	 * <h2>hashCode() method test</h2>
	 */
	@Test
	public void testHashCode() {
		Sale s4 = new Sale(d3,p1,cash,null);
		s4.setId(3);
		assertEquals(s3.hashCode(),s4.hashCode());
		assertNotEquals(s1.hashCode(),s2.hashCode());
	}
	
	/**
	 * <h2>compareTo() method test</h2>
	 * 
	 * Negative cases - the instance is more recent than parameter sale
	 */
	@Test
	public void testCompareToNegative() {
		assertEquals(s3.compareTo(s2),-1);
		
	}
	
	/**
	 * <h2>compareTo() method test</h2>.
	 * 
	 * Positive cases - the instance is  more older than parameter sale
	 */
	@Test
	public void testCompareToPositive() {
		assertEquals(s1.compareTo(s2),1);
	}
	
	/**
	 * <h2>compareTo() method test</h2>.
	 * 
	 * zero cases - the sales have the same dates
	 */
	@Test
	public void testCompareToSameDate() {
		Sale s4 = new Sale(d3,p1,cash,null);
		assertEquals(s3.compareTo(s4),0);
	}
	
	/**
	 * <h2>GetPayment() method test</h2>.
	 * 
	 */
	@Test
	public void testGetPayment() {
		PaymentMethod expected = new PaymentMethod("CASH",0,0);
		assertEquals(s3.getPayment(),expected);
	}
	
	/**
	 * <h2>SetPayment() method test</h2>.
	 * 
	 */
	@Test
	public void testSetPayment() {
		assertEquals(s3.getPayment(),cash);
		PaymentMethod expected = new PaymentMethod("CREDITCARD",0,0);
		s3.payedBy(expected);
		assertEquals(s3.getPayment(),expected);
	}
	
	
	/**
	 * <h2>calculateFeeValueAmount() method test</h2>.
	 * 
	 * Fee amount is below Fee minimum Value
	 */
	@Test
	public void testCalculateFeeValueAmountMinValue() {
		Sale s4 = new Sale(d3,p1,card,null);
		double expect = s4.calculateFeeValue();
		assertEquals(0.55,expect,0.00);
	}
	
	/**
	 * <h2>calculateFeeValueAmount() method test</h2>.
	 * 
	 * Fee amount is over Fee minimum Value
	 */
	@Test
	public void testCalculateFeeValueAmount() {
		p1.setPrice(41.5);
		Sale s4 = new Sale(d3,p1,card,null);
		double expect = s4.calculateFeeValue();
		assertEquals(0.83,expect,0.00);
	}
	
	/**
	 * <h2>getUser() method test</h2>.
	 * 
	 */
	@Test
	public void testGetUser() {
		//GIVEN
		Sale s4 = new Sale(d3,p1,card,u1);
		//WHEN
		User expect = s4.getUser();
		//THEN
		assertEquals(expect,u1);
	}
	
	/**
	 * <h2>getIncrementId() method test</h2>.
	 * 
	 */
	@Test
	public void testGetIncrement() {
		//GIVEN
		Sale s4 = new Sale(d3,p1,card,u1);
		assertEquals(s4.getId(),4);
		//WHEN
		assertEquals(Sale.getAndIncrementId(),5);
		assertEquals(Sale.getAndIncrementId(),6);
		assertEquals(Sale.getAndIncrementId(),7);
		Sale s5 = new Sale(d3,p1,card,u1);
		//THEN
		assertEquals(s5.getId(),8);
	}
}

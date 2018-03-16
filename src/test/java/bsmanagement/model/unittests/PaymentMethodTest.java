package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.PaymentMethod;


/**
 * 
 * Unit tests for PaymentMethod Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class PaymentMethodTest {
	
	
	PaymentMethod cash;
	PaymentMethod creditCard;

	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>PaymentMethod [cash] -> ['CASH',0,0] </p>
	 * <p>PaymentMethod [creditCard] -> ['CREDIT CARD',1.5,0.50] </p>
	 * 
	 */
	@Before
	public void setUp(){
		
		cash = new PaymentMethod("CASH",0.0,0.0);
		creditCard = new PaymentMethod("CREDIT CARD",1.5,0.50);
	}

	@Test
	public void testHashCode() {
		PaymentMethod expectPayment = new PaymentMethod("CASH",0.0,0.0);
		assertEquals(cash.hashCode(),expectPayment.hashCode());
	}

	@Test
	public void testGetType() {
		String expectedType = "CASH";
		assertEquals(cash.getType(),expectedType);
	}

	@Test
	public void testGetFee() {
		double expectedFee = 1.5;
		assertEquals(creditCard.getFee(),expectedFee,0.0);
	}

	@Test
	public void testGetMinFeeValue() {
		double expectedMinFeeValue = 0.50;
		assertEquals(creditCard.getMinFeeValue(),expectedMinFeeValue,0.0);
	}

	@Test
	public void testSetType() {
		assertEquals(cash.getType(),"CASH");
		String expectedType = "DEBIT CARD";
		cash.setType(expectedType);
		assertEquals(cash.getType(),expectedType);
	}

	@Test
	public void testSetFee() {
		assertEquals(creditCard.getFee(),1.5,0.0);
		double expectedFee = 2;
		creditCard.setFee(2);
		assertEquals(creditCard.getFee(),expectedFee,0.0);
	}

	@Test
	public void testSetMinFeeValue() {
		assertEquals(creditCard.getMinFeeValue(),0.50,0.0);
		double expectedMinFeeValue = 1.20;
		creditCard.setMinFeeValue(1.20);
		assertEquals(creditCard.getMinFeeValue(),expectedMinFeeValue,0.0);
	}

	@Test
	public void testEqualsObjectTrueCase() {
		PaymentMethod expectPayment = new PaymentMethod("CASH",0.0,0.0);
		assertEquals(expectPayment.equals(cash),true);
	}
	
	@Test
	public void testEqualsObjectFalseCase() {
		assertEquals(creditCard.equals(cash),false);
		assertEquals(creditCard.equals(LocalTime.of(2, 2)),false);
	}
	
	@Test
	public void testEqualsObjectNullCases() {
		cash.setType(null);
		assertEquals(creditCard.equals(cash),false);
		assertEquals(creditCard.equals(null),false);
	}

	@Test
	public void testToString() {
		String expected = "PaymentMethod [name=" + "CREDIT CARD" + ", fee=" + 1.5 + ", minFeeValue=" + 0.5 + "]";
		assertEquals(expected,creditCard.toString());
	}

}

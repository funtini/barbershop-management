package bsmanagement.controllers.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bsmanagement.dto.rest.PaymentRestDTO;
import bsmanagement.jparepositories.classtests.PaymentRepositoryClass;
import bsmanagement.jparepositories.classtests.SaleRepositoryClass;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.SaleService;

public class PaymentMethodRestControllerTest {
	
	PaymentMethod cash;
	PaymentMethod credit;
	
	ResponseEntity<List<PaymentRestDTO>> responseList;
	List<PaymentRestDTO> expectedList;
	ResponseEntity<PaymentRestDTO> response;
	
	SaleService saleService;
	SaleRepositoryClass saleRepository;
	PaymentRepositoryClass paymentRepository;
	PaymentMethodRestController prc;

	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Payment [cash] -> ['CASH',0,0] </p>
	 * <p>Payment [credit] -> ['CREDIT',0.5,0.5] </p>
	 * 
	 */
	@Before
	public void setUp(){
		
		saleService = new SaleService();
		saleRepository = new SaleRepositoryClass();
		paymentRepository = new PaymentRepositoryClass();
		saleService.setSaleRepository(saleRepository);
		saleService.setPaymentRepository(paymentRepository);
		prc = new PaymentMethodRestController(saleService);
		expectedList = new ArrayList<>();
		
		cash = saleService.createPaymentMethod("CASH",0.0,0.0);
		credit = saleService.createPaymentMethod("CREDIT", 0.5, 0.5);
	}

	/**
	 * testListAvailablePaymentMethods() controller
	 * 
	 * <p>GIVEN: Added 2 PaymentMethods to service</p>
	 * <p>WHEN: call ListAvailablePaymentMethods() controller</p>
	 * <p>THEN: Get a list of those PaymentMethods</p>
	 */
	@Test
	public void testListAvailablePaymentMethods() {
		//GIVEN
		assertEquals(saleService.getAvailablePaymentMethods().isEmpty(),true);
		assertEquals(saleService.addPaymentMethod(cash), true);
		assertEquals(saleService.addPaymentMethod(credit), true);
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);
		
		//WHEN
		responseList = prc.listAvailablePaymentMethods();
		
		//THEN
		expectedList.add(cash.toRestDTO());
		expectedList.add(credit.toRestDTO());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(expectedList,responseList.getBody());
	}

	
	/**
	 * testGetPaymentMethodsByName() controller
	 * 
	 * <p>GIVEN: Added 2 PaymentMethods to service</p>
	 * <p>WHEN: call GetPaymentMethodsByName() controller filled with valid name</p>
	 * <p>THEN: Get response code OK and respective PaymentMethod</p>
	 */
	@Test
	public void testGetPaymentMethodByNameSuccess() {
		//GIVEN
		assertEquals(saleService.getAvailablePaymentMethods().isEmpty(),true);
		assertEquals(saleService.addPaymentMethod(cash), true);
		assertEquals(saleService.addPaymentMethod(credit), true);
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);
		
		//WHEN
		response = prc.getPaymentMethodByName("CASH");
		
		//THEN
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(cash.toRestDTO(),response.getBody());		
	}
	
	/**
	 * testGetPaymentMethodsByName() controller
	 * 
	 * <p>GIVEN: Added 2 PaymentMethods to service</p>
	 * <p>WHEN: call GetPaymentMethodsByName() controller filled with invalid name</p>
	 * <p>THEN: Get response code NOT_FOUND</p>
	 */
	@Test
	public void testGetPaymentMethodByNameNotFound() {
		//GIVEN
		assertEquals(saleService.getAvailablePaymentMethods().isEmpty(),true);
		assertEquals(saleService.addPaymentMethod(cash), true);
		assertEquals(saleService.addPaymentMethod(credit), true);
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);
		
		//WHEN
		response = prc.getPaymentMethodByName("VISA");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());		
	}

	
	/**
	 * testAddPaymentMethod() controller
	 * 
	 * <p>GIVEN: Added 2 PaymentMethods to service</p>
	 * <p>WHEN: call AddPaymentMethod() controller filled with valid name</p>
	 * <p>THEN: Get response code CREATED and the list of PaymentMethod has increased to 3</p>
	 */
	@Test
	public void testAddPaymentMethodSuccess() {
		//GIVEN
		assertEquals(saleService.getAvailablePaymentMethods().isEmpty(),true);
		assertEquals(saleService.addPaymentMethod(cash), true);
		assertEquals(saleService.addPaymentMethod(credit), true);
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);
		
		//WHEN
		PaymentRestDTO payment = new PaymentRestDTO();
		payment.setName("VISA");
		payment.setFee(0.5);
		payment.setMinFeeValue(0.5);
		response = prc.addPaymentMethod(payment);
		
		//THEN
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals(saleService.getAvailablePaymentMethods().size(),3);		
	}
	
	/**
	 * testAddPaymentMethod() controller
	 * 
	 * <p>GIVEN: Added 2 PaymentMethods to service</p>
	 * <p>WHEN: call AddPaymentMethod() controller filled with a name already exisiting in repository</p>
	 * <p>THEN: Get response code NOT_ACCEPTABLE and the list of PaymentMethod still has the same size</p>
	 */
	@Test
	public void testAddPaymentMethodNameAlreadyExists() {
		//GIVEN
		assertEquals(saleService.getAvailablePaymentMethods().isEmpty(),true);
		assertEquals(saleService.addPaymentMethod(cash), true);
		assertEquals(saleService.addPaymentMethod(credit), true);
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);
		
		//WHEN
		PaymentRestDTO payment = new PaymentRestDTO();
		payment.setName("CASH");
		payment.setFee(0.5);
		payment.setMinFeeValue(0.5);
		response = prc.addPaymentMethod(payment);
		
		//THEN
		assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);		
	}
	
	/**
	 * testAddPaymentMethod() controller
	 * 
	 * <p>GIVEN: Added 2 PaymentMethods to service</p>
	 * <p>WHEN: call AddPaymentMethod() controller filled with a no name</p>
	 * <p>THEN: Get response code BAD_REQUEST and the list of PaymentMethod still has the same size</p>
	 */
	@Test
	public void testAddPaymentMethodInvalidName() {
		//GIVEN
		assertEquals(saleService.getAvailablePaymentMethods().isEmpty(),true);
		assertEquals(saleService.addPaymentMethod(cash), true);
		assertEquals(saleService.addPaymentMethod(credit), true);
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);
		
		//WHEN
		PaymentRestDTO payment = new PaymentRestDTO();
		payment.setFee(0.5);
		payment.setMinFeeValue(0.5);
		response = prc.addPaymentMethod(payment);
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);		
	}
	
	/**
	 * testAddPaymentMethod() controller
	 * 
	 * <p>GIVEN: Added 2 PaymentMethods to service</p>
	 * <p>WHEN: call AddPaymentMethod() controller filled with an empty name</p>
	 * <p>THEN: Get response code BAD_REQUEST and the list of PaymentMethod still has the same size</p>
	 */
	@Test
	public void testAddPaymentMethodEmptyName() {
		//GIVEN
		assertEquals(saleService.getAvailablePaymentMethods().isEmpty(),true);
		assertEquals(saleService.addPaymentMethod(cash), true);
		assertEquals(saleService.addPaymentMethod(credit), true);
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);
		
		//WHEN
		PaymentRestDTO payment = new PaymentRestDTO();
		payment.setName("");
		payment.setFee(0.5);
		payment.setMinFeeValue(0.5);
		response = prc.addPaymentMethod(payment);
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		assertEquals(saleService.getAvailablePaymentMethods().size(),2);		
	}

}

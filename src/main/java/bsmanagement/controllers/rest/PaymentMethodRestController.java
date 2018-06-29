package bsmanagement.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.PaymentRestDTO;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.SaleService;

@RestController
public class PaymentMethodRestController {
	
	@Autowired
	SaleService saleService;
	
	public PaymentMethodRestController(SaleService saleService) {
		this.saleService = saleService;
	}
	
	/**
	 * Rest Controller to list available payment methods
	 * 
	 * @return ResponseEntity with a List of PaymentRestDTO
	 */
	@RequestMapping("/paymentMethods")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<PaymentRestDTO>> listAvailablePaymentMethods()
	{
		List<PaymentRestDTO> paymentsRestDTO = new ArrayList<>();
		for (PaymentMethod p : saleService.getAvailablePaymentMethods())
		{
			PaymentRestDTO payment = p.toRestDTO();
			paymentsRestDTO.add(payment);
		}
		return new ResponseEntity<>(paymentsRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get payment method by ID
	 * 
	 * @return ResponseEntity with a PaymentRestDTO if id is valid, otherwise returned NOT_FOUND
	 */
	@RequestMapping("/paymentMethods/{id}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<PaymentRestDTO> getPaymentMethodByName(@PathVariable(value = "id") String paymentName)
	{
		PaymentMethod p = saleService.findPaymentMethodById(paymentName);
		if (p==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		PaymentRestDTO payment = p.toRestDTO();
		
		return new ResponseEntity<>(payment,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to add new Payment Method
	 * 
	 * @return ResponseEntity with a PaymentRestDTO created if name is valid, otherwise returns BAD_REQUEST.
	 * If name is valid but already exists in repository, returned NOT_ACCEPTABLE
	 */
	@PostMapping("/paymentMethods")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<PaymentRestDTO> addPaymentMethod(@RequestBody PaymentRestDTO payment)
	{
		if (payment.getName() == null || payment.getName().isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		PaymentMethod p = saleService.createPaymentMethod(payment.getName(),payment.getFee(), payment.getMinFeeValue());
		if (saleService.addPaymentMethod(p))
		{
			PaymentRestDTO paymentRestDTO = p.toRestDTO();
			return new ResponseEntity<>(paymentRestDTO,HttpStatus.CREATED);
		}
			
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	/**
	 * Rest Controller to remove Payment Method
	 * 
	 * @return ResponseEntity with a response code OK if name exists in repository, otherwise returns NOT_FOUND
	 * 
	 */
	@DeleteMapping("/paymentMethods/{id}")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<PaymentRestDTO> removePaymentMethod(@PathVariable(value = "id") String paymentName)
	{
		PaymentMethod p = saleService.findPaymentMethodById(paymentName);
		if (p==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		saleService.removePaymentMethod(p);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}

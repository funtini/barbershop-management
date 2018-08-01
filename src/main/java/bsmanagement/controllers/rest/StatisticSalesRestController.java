package bsmanagement.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.SaleRestDTO;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.SaleService;

@RestController
public class StatisticSalesRestController {
	
	@Autowired
	SaleService saleService;
	
	
	/**
	 * Rest Controller to get a sum of all amounts payed by specific paymentMethod
	 * 
	 * <p>If PaymentMethod doesnt exist returned as NOT_FOUND</p>
	 * 
	 * @return HttpStatus OK with a amount of sales payed by specific paymentMethod
	 */
	@GetMapping(value = "sales/amount", params = "payedBy")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<SaleRestDTO> sumAllAmountsPayedBy(@RequestParam(value = "payedBy", required = true) String paymentId)
	{	
		PaymentMethod p = saleService.findPaymentMethodById(paymentId.toUpperCase());
		
		if ( p == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		SaleRestDTO amount = new SaleRestDTO();
		amount.setAmount(saleService.sumAllAmountsPayedBy(p));
		
		return new ResponseEntity<>(amount,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a sum of all time sales amounts 
	 * 
	 * @return HttpStatus OK with a amount of sales 
	 */
	@GetMapping("sales/amount")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<SaleRestDTO> sumAllTimeSalesAmounts()
	{		
		SaleRestDTO amount = new SaleRestDTO();
		amount.setAmount(saleService.sumAllAmounts());
		
		return new ResponseEntity<>(amount,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a sum of all time fee's amounts 
	 * 
	 * @return HttpStatus OK with a amount of sales 
	 */
	@GetMapping("sales/fee")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<SaleRestDTO> sumAllTimeFeeAmounts()
	{		
		SaleRestDTO amount = new SaleRestDTO();
		amount.setAmount(saleService.calculateAllTimeFeeAmount());
		
		return new ResponseEntity<>(amount,HttpStatus.OK);
	}

}

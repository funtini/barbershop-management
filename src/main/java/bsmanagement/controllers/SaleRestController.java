package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;

@RestController
public class SaleRestController {

	@Autowired
	SaleService saleService;

	protected SaleRestController() {

	}
	
	@RequestMapping("/sales")
	List<Sale> listAllSales()
	{
		return saleService.getSales();
	}
	
}

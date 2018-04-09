package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.model.Expense;
import bsmanagement.model.ExpenseService;

@RestController
public class ExpenseRestController {

	@Autowired
	ExpenseService expenseService;

	public ExpenseRestController() {

	}
	
	@RequestMapping("/expenses")
	List<Expense> listAllExpenses()
	{
		return expenseService.getExpenses();
	}
	
}

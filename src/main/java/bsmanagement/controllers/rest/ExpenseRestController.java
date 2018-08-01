package bsmanagement.controllers.rest;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.ExpenseRestDTO;
import bsmanagement.model.Expense;
import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.ExpenseService;

@RestController
public class ExpenseRestController {

	@Autowired
	ExpenseService expenseService;

	
	public ExpenseRestController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}


	/**
	 * Rest Controller to list all expenses
	 * 
	 * @return ResponseEntity with a List of ExpenseRestDTO
	 */
	@RequestMapping("/expenses")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<ExpenseRestDTO>> listAllExpenses()
	{
		List<ExpenseRestDTO> expensesRestDTO = new ArrayList<>();
		for (Expense e : expenseService.getExpenses())
		{
			ExpenseRestDTO expense = e.toRestDTO();
			expensesRestDTO.add(expense);
		}
		return new ResponseEntity<>(expensesRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to list all expenses of current Month
	 * 
	 * @return ResponseEntity with a List of ExpenseRestDTO
	 */
	@RequestMapping("/expenses/current")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<ExpenseRestDTO>> listCurrentMonthExpenses()
	{
		List<ExpenseRestDTO> expensesRestDTO = new ArrayList<>();
		for (Expense e : expenseService.findExpensesOfMonth(YearMonth.now()))
		{
			ExpenseRestDTO expense = e.toRestDTO();
			expensesRestDTO.add(expense);
		}
		return new ResponseEntity<>(expensesRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to list all expenses of specific Month
	 * 
	 * @return ResponseEntity with a List of ExpenseRestDTO if month inputed is valid, otherwise return BAD_REQUEST
	 */
	@RequestMapping(value = "expenses", params = "month")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<ExpenseRestDTO>> listExpensesOfMonth(@RequestParam(value = "month", required = true) String month)
	{
		YearMonth dateConverted;
		List<ExpenseRestDTO> expensesRestDTO = new ArrayList<>();
		try {
			dateConverted = YearMonth.parse(month);
		}
		catch(DateTimeParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		for (Expense e : expenseService.findExpensesOfMonth(dateConverted))
		{
			ExpenseRestDTO expense = e.toRestDTO();
			expensesRestDTO.add(expense);
		}
		return new ResponseEntity<>(expensesRestDTO,HttpStatus.OK);
	}
	
	
//	/**
//	 * Rest Controller to add new expense
//	 * 
//	 * @return ResponseEntity with a ExpenseRestDTO added if all fields inputed are valid, otherwise return BAD_REQUEST
//	 */
//	@PostMapping("/expenses")
//	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
//	public ResponseEntity<ExpenseRestDTO> addExpense(@RequestBody ExpenseRestDTO expenseDTO)
//	{
//		List<String> availableTypes = Arrays.asList("FIXED", "ONEOFF");
//		if (expenseDTO.getType() == null || !availableTypes.contains(expenseDTO.getType().toUpperCase()) || 
//				expenseDTO.getDateOfPayment()==null || expenseDTO.getValueOfPayment()<=0.0 || expenseDTO.getName()==null )
//		{
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		Expense e = expenseService.createExpense(expenseDTO.getName(),expenseType.valueOf(expenseDTO.getType().toUpperCase()), 
//				expenseDTO.getValueOfPayment(), expenseDTO.getDateOfPayment(),expenseDTO.getDescription());
//		
//		ExpenseRestDTO expensesRestDTO = expenseService.addExpense(e).toRestDTO();
//		return new ResponseEntity<>(expensesRestDTO,HttpStatus.OK);
//	}
//	
//	
//	/**
//	 * Rest Controller to remove an expense
//	 * 
//	 * @return ResponseEntity with code status OK if id exists, otherwise return NOT_FOUND
//	 */
//	@DeleteMapping("/expenses/{id}")
//	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
//	public ResponseEntity<ExpenseRestDTO> removeExpense(@PathVariable(value = "id") int id)
//	{
//		if (expenseService.findExpenseById(id)==null)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		
//		expenseService.removeExpense(expenseService.findExpenseById(id));
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	/**
	 * Rest Controller to get an expense by id
	 * 
	 * @return ResponseEntity with an ExpenseRestDTO if id exists, otherwise return NOT_FOUND
	 */
	@RequestMapping("/expenses/{id}")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<ExpenseRestDTO> getExpenseById(@PathVariable(value = "id") int id)
	{
		if (expenseService.findExpenseById(id)==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		ExpenseRestDTO expensesRestDTO = expenseService.findExpenseById(id).toRestDTO();		
		return new ResponseEntity<>(expensesRestDTO,HttpStatus.OK);
	}
	
}

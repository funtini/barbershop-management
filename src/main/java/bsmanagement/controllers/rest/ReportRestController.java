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
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.ExpenseRestDTO;
import bsmanagement.dto.rest.ReportRestDTO;
import bsmanagement.dto.rest.SaleRestDTO;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;
import bsmanagement.model.Expense;
import bsmanagement.model.ExpenseService;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.ProductService;
import bsmanagement.model.Report;
import bsmanagement.model.ReportSaleExpenseService;
import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.Expense.expenseType;

@RestController
public class ReportRestController {

	@Autowired
	ReportSaleExpenseService reportSaleExpenseService;
	
	@Autowired
	SaleService saleService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BookingCustomerService bookingCustomerService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ExpenseService expenseService;




	public ReportRestController(ReportSaleExpenseService reportSaleExpenseService, ProductService productService,
			BookingCustomerService bookingCustomerService, UserService userService, ExpenseService expenseService, SaleService saleService) {
		this.reportSaleExpenseService = reportSaleExpenseService;
		this.productService = productService;
		this.bookingCustomerService = bookingCustomerService;
		this.userService = userService;
		this.expenseService = expenseService;
		this.saleService = saleService;
	}


	/**
	 * Rest Controller to list all reports
	 * 
	 * @return ResponseEntity with status code OK and a List of ReportRestDTO
	 */
	@RequestMapping("/reports")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<List<ReportRestDTO>> listAllReports()
	{
		List<ReportRestDTO> reports = new ArrayList<>();
		for (Report r : reportSaleExpenseService.getAllReports())
		{
			reports.add(r.toRestDTO());
		}

		return new ResponseEntity<>(reports,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to list open reports
	 * 
	 * @return ResponseEntity with status code OK and a List of ReportRestDTO
	 */
	@RequestMapping("/reports/open")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<List<ReportRestDTO>> listOpenReports()
	{
		List<ReportRestDTO> reports = new ArrayList<>();
		for (Report r : reportSaleExpenseService.getOpenReports())
		{
			reports.add(r.toRestDTO());
		}

		return new ResponseEntity<>(reports,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to list closed reports
	 * 
	 * @return ResponseEntity with status code OK and a List of ReportRestDTO
	 */
	@RequestMapping("/reports/closed")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<List<ReportRestDTO>> listClosedReports()
	{
		List<ReportRestDTO> reports = new ArrayList<>();
		for (Report r : reportSaleExpenseService.getClosedReports())
		{
			reports.add(r.toRestDTO());
		}

		return new ResponseEntity<>(reports,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a single report by id
	 * 
	 * @return ResponseEntity with status code OK and a respective ReportRestDTO
	 */
	@RequestMapping("/reports/{reportId}")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	ResponseEntity<ReportRestDTO> getReportById(@PathVariable(value = "reportId") String reportId)
	{
		YearMonth dateConverted;
		try {
			dateConverted = YearMonth.parse(reportId);
		}
		catch(DateTimeParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Report r = reportSaleExpenseService.getReport(dateConverted);
		if (r==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	

		return new ResponseEntity<>(r.toRestDTO(),HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to add new sale
	 * 
	 * @return ResponseEntity with a SaleRestDTO created, if some parm is invalid return BAD_REQUEST
	 */
	@PostMapping("/sales")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<SaleRestDTO> addSale(@RequestBody SaleRestDTO saleRestDTO)
	{
		Customer customer = bookingCustomerService.findCustomerById(saleRestDTO.getCustomerId());
		Product product = productService.findProductById(saleRestDTO.getProductId());
		PaymentMethod payment = saleService.findPaymentMethodById(saleRestDTO.getPaymentMethod());
		User user = userService.findUserByEmail(saleRestDTO.getUserEmail());
		
		Sale sale = saleService.createSale(saleRestDTO.getDate(), customer, product, payment, user);
		
		if (reportSaleExpenseService.addSale(sale))
			return new ResponseEntity<>(sale.toRestDTO(),HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		
	}
	
	/**
	 * Rest Controller to add new expense
	 * 
	 * @return ResponseEntity with a ExpenseRestDTO added if all fields inputed are valid, otherwise return BAD_REQUEST
	 */
	@PostMapping("/expenses")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<ExpenseRestDTO> addExpense(@RequestBody ExpenseRestDTO expenseDTO)
	{
		List<String> availableTypes = Arrays.asList("FIXED", "ONEOFF");
		if (expenseDTO.getType() == null || !availableTypes.contains(expenseDTO.getType().toUpperCase()) || 
				expenseDTO.getDateOfPayment()==null || expenseDTO.getValueOfPayment()<=0.0 || expenseDTO.getName()==null )
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Expense e = expenseService.createExpense(expenseDTO.getName(),expenseType.valueOf(expenseDTO.getType().toUpperCase()), 
				expenseDTO.getValueOfPayment(), expenseDTO.getDateOfPayment(),expenseDTO.getDescription());
		reportSaleExpenseService.addExpense(e);
		ExpenseRestDTO expensesRestDTO = e.toRestDTO();
		return new ResponseEntity<>(expensesRestDTO,HttpStatus.CREATED);
	}
	
	
	/**
	 * Rest Controller to remove an expense
	 * 
	 * @return ResponseEntity with code status OK if id exists,
	 * <p> response code NOT_ACCETABLE if respective expense's report is already closed,
	 * <p> response code NOT_FOUND if expense id doesn't exist 
	 */
	@DeleteMapping("/expenses/{id}")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<ExpenseRestDTO> removeExpense(@PathVariable(value = "id") int id)
	{
		if (expenseService.findExpenseById(id)==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(reportSaleExpenseService.removeExpense(expenseService.findExpenseById(id)))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);			
	}
	
	
}

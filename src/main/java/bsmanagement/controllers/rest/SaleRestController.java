package bsmanagement.controllers.rest;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.SaleRestDTO;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.ProductService;
import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;
import bsmanagement.model.User;
import bsmanagement.model.UserService;

@RestController
public class SaleRestController {

	@Autowired
	SaleService saleService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BookingCustomerService bookingCustomerService;
	
	@Autowired
	UserService userService;

	public SaleRestController(SaleService saleService,ProductService productService,BookingCustomerService bookingCustomerService,UserService userService) {
		this.saleService = saleService;
		this.productService = productService;
		this.bookingCustomerService = bookingCustomerService;
		this.userService = userService;
	}
	
	
	/**
	 * Rest Controller to list all sales
	 * 
	 * @return ResponseEntity with a List of SaleRestDTO
	 */
	@RequestMapping("/sales")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<SaleRestDTO>> listAllSales()
	{
		List<SaleRestDTO> salesRestDTO = new ArrayList<>();
		for (Sale s : saleService.getSales())
		{
			SaleRestDTO sale = s.toRestDTO();
			salesRestDTO.add(sale);
		}
		return new ResponseEntity<>(salesRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to add new sale
	 * 
	 * @return ResponseEntity with a SaleRestDTO created, if some parm is invalid return BAD_REQUEST
	 */
	@PostMapping("/sales")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<SaleRestDTO> addSale(@RequestBody SaleRestDTO saleRestDTO)
	{
		Customer customer = bookingCustomerService.findCustomerById(saleRestDTO.getCustomerId());
		Product product = productService.findProductById(saleRestDTO.getProductId());
		PaymentMethod payment = saleService.findPaymentMethodById(saleRestDTO.getPaymentMethod());
		User user = userService.findUserByEmail(saleRestDTO.getUserEmail());
		
		Sale sale = saleService.createSale(saleRestDTO.getDate(), customer, product, payment, user);
		
		if (saleService.addSale(sale))
			return new ResponseEntity<>(sale.toRestDTO(),HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	/**
	 * Rest Controller to find sale by ID
	 * 
	 * @return ResponseEntity with a SaleRestDTO if id is valid, otherwise return NOT_FOUND
	 */
	@RequestMapping("/sales/{id}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<SaleRestDTO> findSaleById(@PathVariable(value = "id") int id)
	{
		Sale sale = saleService.findSaleById(id);
		if(sale == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(sale.toRestDTO(),HttpStatus.OK);
		
		
	}
	
	
	/**
	 * Rest Controller to get a List of sales of specific customer
	 * 
	 * <p>If customer ID doesn't exist, the response entity will be returned as NOT_FOUND</p>
	 * 
	 * @return HttpStatus OK with a list of saleRestDTO if customerID is valid, otherwise HttpStatus NOT_FOUND
	 */
	@GetMapping(value = "sales", params = "customerId")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<SaleRestDTO>> listSalesOfCustomer(@RequestParam(value = "customerId", required = true) int customerId)
	{
		
		List<SaleRestDTO> salesRestDTO = new ArrayList<>();
		Customer c = bookingCustomerService.findCustomerById(customerId);
		if ( c == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		for (Sale s : saleService.findSalesByCustomer(c))
		{
			SaleRestDTO sale = s.toRestDTO();
			salesRestDTO.add(sale);		
		}
		return new ResponseEntity<>(salesRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a List of sales of specific user
	 * 
	 * <p>If user email doesn't exist, the response entity will be returned as NOT_FOUND</p>
	 * 
	 * @return HttpStatus OK with a list of saleRestDTO if userID is valid, otherwise HttpStatus NOT_FOUND
	 */
	@GetMapping(value = "sales", params = "userId")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<SaleRestDTO>> listSalesOfUser(@RequestParam(value = "userId", required = true) String userId)
	{
		
		List<SaleRestDTO> salesRestDTO = new ArrayList<>();
		User u = userService.findUserByEmail(userId);
		if ( u == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		for (Sale s : saleService.findSalesByUser(u))
		{
			SaleRestDTO sale = s.toRestDTO();
			salesRestDTO.add(sale);		
		}
		return new ResponseEntity<>(salesRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a List of sales of specific day
	 * 
	 * <p>If date is after today's date, the response entity will be returned as BAD_REQUEST</p>
	 * 
	 * @return HttpStatus OK with a list of saleRestDTO if date is valid, otherwise HttpStatus BAD_REQUEST
	 */
	@GetMapping(value = "sales", params = "date")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<SaleRestDTO>> listSalesOfDay(@RequestParam(value = "date", required = true) String date)
	{
		
		LocalDate dateConverted;
		
		try {
			dateConverted = LocalDate.parse(date);
		}
		catch(DateTimeParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<SaleRestDTO> salesRestDTO = new ArrayList<>();
		if ( dateConverted.isAfter(LocalDate.now()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		for (Sale s : saleService.findSalesOfDay(dateConverted))
		{
			SaleRestDTO sale = s.toRestDTO();
			salesRestDTO.add(sale);		
		}
		return new ResponseEntity<>(salesRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a List of sales of specific month
	 * 
	 * <p>If date is after current month, the response entity will be returned as BAD_REQUEST</p>
	 * 
	 * @return HttpStatus OK with a list of saleRestDTO if YearMonth is valid, otherwise HttpStatus BAD_REQUEST
	 */
	@GetMapping(value = "sales", params = "month")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<SaleRestDTO>> listSalesOfMonth(@RequestParam(value = "month", required = true) String month)
	{
		
		YearMonth monthConverted;
		
		try {
			monthConverted = YearMonth.parse(month);
		}
		catch(DateTimeParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<SaleRestDTO> salesRestDTO = new ArrayList<>();
		if ( monthConverted.isAfter(YearMonth.now()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		for (Sale s : saleService.findSalesOfMonth(monthConverted))
		{
			SaleRestDTO sale = s.toRestDTO();
			salesRestDTO.add(sale);		
		}
		return new ResponseEntity<>(salesRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a List of sales between two days
	 * 
	 * <p>If date is after current month or if startDate is after endDate, the response entity will be returned as BAD_REQUEST</p>
	 * 
	 * @return HttpStatus OK with a list of saleRestDTO if both dates are valid, otherwise HttpStatus BAD_REQUEST
	 */
	@RequestMapping("/sales")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<SaleRestDTO>> listSalesBetweenDates(@RequestParam(value = "startDay", required = true) String startDate,@RequestParam(value = "endDay", required = true) String endDate)
	{
		
		LocalDate startDateConverted;
		LocalDate endDateConverted;
		
		try {
			startDateConverted = LocalDate.parse(startDate);
			endDateConverted = LocalDate.parse(endDate);
		}
		catch(DateTimeParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (startDateConverted.isAfter(endDateConverted) || startDateConverted.isAfter(LocalDate.now()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		List<SaleRestDTO> salesRestDTO = new ArrayList<>();

		for (Sale s : saleService.findSalesBetweenDates(startDateConverted, endDateConverted))
		{
			SaleRestDTO sale = s.toRestDTO();
			salesRestDTO.add(sale);		
		}
		return new ResponseEntity<>(salesRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get a List of sales payed by specific paymentmethod
	 * 
	 * <p>If paymentMethod doesn't exist, the response entity will be returned as NOT_FOUND</p>
	 * 
	 * @return HttpStatus OK with a list of saleRestDTO if paymentId is valid, otherwise HttpStatus NOT_FOUND
	 */
	@GetMapping(value = "sales", params = "payedBy")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<SaleRestDTO>> listSalesPayedBy(@RequestParam(value = "payedBy", required = true) String paymentId)
	{
		
		List<SaleRestDTO> salesRestDTO = new ArrayList<>();
		PaymentMethod p = saleService.findPaymentMethodById(paymentId.toUpperCase());
		if ( p == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		for (Sale s : saleService.findSalesPayedBy(p))
		{
			SaleRestDTO sale = s.toRestDTO();
			salesRestDTO.add(sale);		
		}
		return new ResponseEntity<>(salesRestDTO,HttpStatus.OK);
	}
	
}

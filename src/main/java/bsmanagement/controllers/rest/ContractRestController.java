package bsmanagement.controllers.rest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.ContractRestDTO;
import bsmanagement.dto.rest.SalaryRestDTO;
import bsmanagement.model.Contract;
import bsmanagement.model.User;
import bsmanagement.model.UserService;

@RestController
public class ContractRestController {
	
	@Autowired
	private UserService userService;
	
	
	
	public ContractRestController(UserService userService) {
		this.userService = userService;
	}


	/**
	 * Rest Controller to get all contracts 
	 * 
	 * @return ResponseEntity with a list of ContractRestDTO
	 */
	@GetMapping("/contracts")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<ContractRestDTO>> listAllContracts() {

		ContractRestDTO contract;
		List<ContractRestDTO> contractsList = new ArrayList<>();
		for (User u : userService.listAllUsers()) {
			for (Contract c : userService.listUserContracts(u.getEmailAddress()))
			{
				contract = c.toRestDTO();
				contract.setEmail(u.getEmailAddress());
				contract.setName(u.getName());
				contract.setRole(u.getRoles().toString());
				contractsList.add(contract);
			}
			
		}

		return new ResponseEntity<>(contractsList, HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to get all open contracts 
	 * 
	 * @return ResponseEntity with a list of ContractRestDTO
	 */
	@GetMapping("/contracts/open")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<ContractRestDTO>> listOpenContracts() {

		List<ContractRestDTO> contractsList = userService.listOpenContractsRestDTO();

		return new ResponseEntity<>(contractsList, HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get all contracts of a specific user
	 * 
	 * @return ResponseEntity with a list of ContractRestDTO and response code OK if user exists, otherwise return NOT_FOUND
	 */
	@GetMapping("/users/{email}/contracts")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<ContractRestDTO>> listUserContracts(@PathVariable(value = "email") String email) {

		if (userService.findUserByEmail(email) == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ContractRestDTO> contractsList = new ArrayList<>();
		ContractRestDTO contract;
		for (Contract c : userService.listUserContracts(email))
		{
			contract = c.toRestDTO();
			contract.setEmail(userService.findUserByEmail(email).getEmailAddress());
			contract.setName(userService.findUserByEmail(email).getName());
			contract.setRole(userService.findUserByEmail(email).getRoles().toString());
			contractsList.add(contract);
		}

		return new ResponseEntity<>(contractsList, HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get Current/Last contract of an User
	 * 
	 * @return ResponseEntity with last ContractRestDTO of User and response code OK,
	 * <p> if User doesn't exists or User has no contracts return NOT_FOUND
	 */
	@GetMapping("/users/{email}/contracts/current")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<ContractRestDTO> getCurrentUserContract(@PathVariable(value = "email") String email) {

		if (userService.getUserLastContract(email) == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		ContractRestDTO contract;	
		contract = userService.getUserLastContract(email).toRestDTO();
		contract.setEmail(userService.findUserByEmail(email).getEmailAddress());
		contract.setName(userService.findUserByEmail(email).getName());
		contract.setRole(userService.findUserByEmail(email).getRoles().toString());	

		return new ResponseEntity<>(contract, HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to add contract to an User
	 * 
	 * @return ResponseEntity with ContractRestDTO added and response code CREATED,
	 *  <p>if User doesn't exists or User has no contracts return NOT_FOUND,
	 *  <p>if user has a already a open report return NOT_ACCEPTABLE,
	 *  <p>if salesCommission value is out of limits ([0,100]) return BAD_REQUEST
	 */
	@PostMapping("/users/{email}/contracts")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<ContractRestDTO> addContract(@PathVariable(value = "email") String email,@RequestBody ContractRestDTO contract) {

		double baseSalary = contract.getBaseSalary();
		double salesCommission = contract.getSalesCommission();
		if (userService.getUserLastContract(email) == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (salesCommission>100.00 || salesCommission<0.0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		if (userService.addContract(userService.findUserByEmail(email), baseSalary, salesCommission))
			return new ResponseEntity<>(userService.getUserLastContract(email).toRestDTO(), HttpStatus.CREATED);

		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	/**
	 * Rest Controller to close last contract of an User
	 * 
	 * @return ResponseEntity with ContractRestDTO closed and response code OK,
	 *  <p>if User has no contracts or has all contracts already closed return BAD_REQUEST,
	 *  <p>if user doesnt exists return NOT_FOUND,
	 */
	@GetMapping("/users/{email}/contracts/close")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<ContractRestDTO> closeContract(@PathVariable(value = "email") String email) {

	
		if (userService.getUserLastContract(email) == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (userService.closeContract(email))
			return new ResponseEntity<>(userService.getUserLastContract(email).toRestDTO(), HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * Rest Controller to get salesCommission of specific user and month
	 * 
	 * @return ResponseEntity with SalaryRestDTO and response code OK,
	 *  <p>if date inputed is invalid return BAD_REQUEST,
	 *  <p>if user doesnt exists return NOT_FOUND,
	 */
	@GetMapping(value = "/users/{email}/salesComission", params = "date")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<SalaryRestDTO> getComissionSalesOfMonth(@PathVariable(value = "email") String email, @RequestParam(value = "date", required = true) String date) {

		YearMonth dateConverted;
		SalaryRestDTO salary = new SalaryRestDTO();
		try {
			dateConverted = YearMonth.parse(date);
		}
		catch(DateTimeParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (userService.getUserLastContract(email) == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		salary.setCommissionSale(userService.findUserByEmail(email).getSalesCommissionOfMonth(dateConverted));

		return new ResponseEntity<>(salary,HttpStatus.OK);
	}

}

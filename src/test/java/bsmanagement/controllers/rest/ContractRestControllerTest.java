package bsmanagement.controllers.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bsmanagement.dto.rest.ContractRestDTO;
import bsmanagement.dto.rest.SalaryRestDTO;
import bsmanagement.jparepositories.classtests.RoleRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.Address;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.roles.RoleName;

public class ContractRestControllerTest {

	UserService userService;
	ContractRestController crc;
	UserRepositoryClass userRepositoryClass;
	RoleRepositoryClass roleRepositoryClass;
	User u1, u2, u3;
	LocalDate birth1,birth2;
	Address a1,a2,a3;
	
	ResponseEntity<ContractRestDTO> response;
	ResponseEntity<SalaryRestDTO> responseSalary;
	ResponseEntity<SalaryRestDTO> expectedSalary;
	ResponseEntity<ContractRestDTO> expected;
	ResponseEntity<List<ContractRestDTO>> responseList;
	List<ContractRestDTO> expectedList;
	
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>BirthDate [birth1] : 17/03/1989 </p>
	 * <p>BirthDate [birth2] : 21/07/1988 </p>
	 * 
	 * <p>User [u1] : ["JOAO",birth1,"joao@gmail.com","914047935","324666433"] </p>
	 * <p>User [u2] : ["PEDRO",birth2,"pedro@domain.uk","915557911","123555433"] </p>
	 * <p>User [u3] : ["SARA",birth2,"sara@gmail.com","932132179","321321332"] </p>
	 * 
	 */
	@Before
	public void setUp(){
		
		userService = new UserService();
		userRepositoryClass = new UserRepositoryClass();
		roleRepositoryClass = new RoleRepositoryClass();
		userService.setUserRepository(userRepositoryClass);
		userService.setRoleRepository(roleRepositoryClass);
		Address.setStartIdGenerator(1);
		crc = new ContractRestController(userService);
		expectedList = new ArrayList<>();
		
		a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		
		birth1 = LocalDate.of(1998, 3, 17);
		birth2 = LocalDate.of(1988, 7, 21);
		
		userService.addRole(RoleName.ROLE_USER);
    	userService.addRole(RoleName.ROLE_STOREMANAGER);
    	userService.addRole(RoleName.ROLE_ADMINISTRATOR);
		
		u1 = userService.createUser("JOAO",birth1,"joao@domain.com","914047935","324666433");
		u2 = userService.createUser("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		u3 = userService.createUser("SARA",birth2,"sara@gmail.com","93213217911","32132133");
		
		userService.addUser(u1);
		userService.addUser(u2);
		userService.addUser(u3);
		
		u1 = userService.findUserByEmail("joao@domain.com");
		u2 = userService.findUserByEmail("pedro@gmail.uk");
		u3 = userService.findUserByEmail("sara@gmail.com");
		
		u1.setProfileEmployer();
		u2.setProfileStoreManager();
		u3.setProfileEmployer();
		
		userService.updateUser(u1);
		userService.updateUser(u2);
		userService.updateUser(u3);
	}
	
	/**
	 * Smoke test to userService
	 * 
	 */
	@Test
	public void smokeTestUserService() {
		assertThat(userService).isNotNull();
	}
	

	/**
	 * testListAllContracts() controller
	 * 
	 * <p>GIVEN: 2 users with 1 contract each </p>
	 * <p>WHEN: get a list of all contracts</p>
	 * <p>THEN: controller return response code OK with respective list of ContractRestDTO in body</p>
	 */
	@Test
	public void testListAllContracts() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.addContract(u3, 300, 50);
		
		//WHEN
		responseList = crc.listAllContracts();
		
		//THEN
		expectedList.add(userService.findUserByEmail("joao@domain.com").getLastContract().toRestDTO());
		expectedList.add(userService.findUserByEmail("sara@gmail.com").getLastContract().toRestDTO());
		assertEquals(expectedList,responseList.getBody());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		
	}

	/**
	 * testListOpenContracts() controller
	 * 
	 * <p>GIVEN: 1 user with 1 open contract and other user with a closed one</p>
	 * <p>WHEN: get a list of open contracts</p>
	 * <p>THEN: controller return response code OK with respective list of ContractRestDTO in body</p>
	 */
	@Test
	public void testListOpenContracts() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.addContract(u3, 300, 50);
		userService.closeContract("joao@domain.com");
		
		//WHEN
		responseList = crc.listOpenContracts();
		
		//THEN
		expectedList.add(userService.findUserByEmail("sara@gmail.com").getLastContract().toRestDTO());
		assertEquals(expectedList,responseList.getBody());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());		
	}

	
	/**
	 * testListUserContracts() controller
	 * 
	 * <p>GIVEN: 1 user with 2 contracts </p>
	 * <p>WHEN: get a list of user contracts</p>
	 * <p>THEN: controller return response code OK with respective list of ContractRestDTO in body</p>
	 */
	@Test
	public void testListUserContractsSuccess() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
		userService.addContract(u1, 1000, 0);
		
		//WHEN
		responseList = crc.listUserContracts("joao@domain.com");
		
		//THEN
		expectedList.add(userService.findUserByEmail("joao@domain.com").getAllContracts().get(0).toRestDTO());
		expectedList.add(userService.findUserByEmail("joao@domain.com").getAllContracts().get(1).toRestDTO());
		assertEquals(expectedList,responseList.getBody());
		assertEquals(HttpStatus.OK,responseList.getStatusCode());		
	}
	
	/**
	 * testListUserContracts() controller
	 * 
	 * <p>GIVEN: 1 user with 2 contracts </p>
	 * <p>WHEN: get contracts list of unknown user</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testListUserContractsNotFound() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
		userService.addContract(u1, 1000, 0);
		
		//WHEN
		responseList = crc.listUserContracts("unknown@domain.com");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,responseList.getStatusCode());		
	}

	/**
	 * testGetCurrentUserContract() controller
	 * 
	 * <p>GIVEN: 1 user with 2 contracts </p>
	 * <p>WHEN: get current contract of unknown user</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testGetCurrentUserContractNotFound() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
		userService.addContract(u1, 1000, 0);
		
		//WHEN
		response = crc.getCurrentUserContract("unknown@domain.com");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());		
	}
	
	/**
	 * testGetCurrentUserContract() controller
	 * 
	 * <p>GIVEN: 1 user with 2 contracts </p>
	 * <p>WHEN: get current contract of an user</p>
	 * <p>THEN: controller return response code OK with respective ContractRestDTO in body</p>
	 */
	@Test
	public void testGetCurrentUserContractSuccess() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
		userService.addContract(u1, 1000, 0);
		
		//WHEN
		response = crc.getCurrentUserContract("joao@domain.com");
		
		//THEN

		ContractRestDTO expected = userService.findUserByEmail("joao@domain.com").getAllContracts().get(1).toRestDTO();
		assertEquals(expected,response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());		
	}

	/**
	 * testAddContract() controller
	 * 
	 * <p>GIVEN: 1 user with 1 contract </p>
	 * <p>WHEN: add second contract to that user</p>
	 * <p>THEN: controller return response code OK with respective new ContractRestDTO in body</p>
	 */
	@Test
	public void testAddContract() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
		
		//WHEN
		ContractRestDTO contract = new ContractRestDTO();
		contract.setBaseSalary(700);
		contract.setSalesCommission(10);
		response = crc.addContract("joao@domain.com", contract);
		
		//THEN

		ContractRestDTO expected = userService.findUserByEmail("joao@domain.com").getLastContract().toRestDTO();
		assertEquals(expected,response.getBody());
		assertEquals(HttpStatus.CREATED,response.getStatusCode());			
	}
	
	/**
	 * testAddContract() controller
	 * 
	 * <p>GIVEN: 1 user with 1 contract </p>
	 * <p>WHEN: add contract to unknown user</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testAddContractNotFound() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
		
		//WHEN
		ContractRestDTO contract = new ContractRestDTO();
		contract.setBaseSalary(700);
		contract.setSalesCommission(10);
		response = crc.addContract("rogerio@domain.com", contract);
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());				
	}
	

	/**
	 * testAddContract() controller
	 * 
	 * <p>GIVEN: 1 user with 2 contract </p>
	 * <p>WHEN: add contract to an user that has already an open contract</p>
	 * <p>THEN: controller return response code NOT ACCEPTABLE</p>
	 */
	@Test
	public void testAddContractNotAcceptable() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
		userService.addContract(u1, 1000, 0);
		
		//WHEN
		ContractRestDTO contract = new ContractRestDTO();
		contract.setBaseSalary(700);
		contract.setSalesCommission(10);
		response = crc.addContract("joao@domain.com", contract);
		
		//THEN
		assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());			
	}
	
	/**
	 * testAddContract() controller
	 * 
	 * <p>GIVEN: 1 user with 2 contract </p>
	 * <p>WHEN: add contract to an user with invalid comission sale</p>
	 * <p>THEN: controller return response code BAD_REQUEST</p>
	 */
	@Test
	public void testAddContractBadRequest() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
		userService.addContract(u1, 1000, 0);
		
		//WHEN
		ContractRestDTO contract = new ContractRestDTO();
		contract.setBaseSalary(700);
		contract.setSalesCommission(125);
		response = crc.addContract("joao@domain.com", contract);
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());			
	}

	/**
	 * testCloseContract() controller
	 * 
	 * <p>GIVEN: 1 user with 2 contract </p>
	 * <p>WHEN: close contract to that user</p>
	 * <p>THEN: controller return response code OK and contract of user was closed</p>
	 */
	@Test
	public void testCloseContractSuccess() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		assertEquals(userService.findUserByEmail("joao@domain.com").hasOpenContract(),true);
	
		//WHEN

		response = crc.closeContract("joao@domain.com");
		
		//THEN
		assertEquals(userService.findUserByEmail("joao@domain.com").hasOpenContract(),false);
		ContractRestDTO expected = userService.findUserByEmail("joao@domain.com").getLastContract().toRestDTO();
		assertEquals(expected,response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());			
	}
	
	/**
	 * testCloseContract() controller
	 * 
	 * <p>GIVEN: 1 user with 2 contract </p>
	 * <p>WHEN: close contract to a unknown user</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testCloseContractNotFound() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		assertEquals(userService.findUserByEmail("joao@domain.com").hasOpenContract(),true);
	
		//WHEN

		response = crc.closeContract("unknown@domain.com");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());				
	}
	
	/**
	 * testCloseContract() controller
	 * 
	 * <p>GIVEN: 1 user with no contracts </p>
	 * <p>WHEN: try to close contract of that user</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testCloseContractNotFoundContracts() {
		//GIVEN
		assertEquals(userService.findUserByEmail("joao@domain.com").getAllContracts().size(),0);
	
		//WHEN

		response = crc.closeContract("joao@domain.com");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());			
	}
	
	/**
	 * testCloseContract() controller
	 * 
	 * <p>GIVEN: 1 user with 1 closed contract </p>
	 * <p>WHEN: try to "over" close contract of that user</p>
	 * <p>THEN: controller return response code BAD REQUEST</p>
	 */
	@Test
	public void testCloseContractBadRequest() {
		//GIVEN
		userService.addContract(u1, 500, 30);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));
		userService.findUserByEmail("joao@domain.com").getLastContract().closeAt(LocalDate.now().minusMonths(6));
	
		//WHEN

		response = crc.closeContract("joao@domain.com");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());			
	}

	/**
	 * testGetComissionSalesOfMonth() controller
	 * 
	 * <p>GIVEN: 1 user with 1 contract [ 500 base salary and 50 percent of comissionSales ] and with 5 sales in this month</p>
	 * <p>WHEN: get comission sales amount of that user in current month</p>
	 * <p>THEN: controller return response code OK and respective SalaryRestDTO</p>
	 */
	@Test
	public void testGetComissionSalesOfMonthSuccess() {
		//GIVEN
		userService.addContract(u1, 500, 50);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));

	
		//WHEN

		responseSalary = crc.getComissionSalesOfMonth("joao@domain.com","2017-10");
		
		//THEN
		assertEquals(HttpStatus.OK,responseSalary.getStatusCode());			
	}
	
	/**
	 * testGetComissionSalesOfMonth() controller
	 * 
	 * <p>GIVEN: 1 user with 1 contract [ 500 base salary and 50 percent of comissionSales ] and with 5 sales in this month</p>
	 * <p>WHEN: get comission sales amount of that user in current month with invalid date</p>
	 * <p>THEN: controller return response code OK and respective SalaryRestDTO</p>
	 */
	@Test
	public void testGetComissionSalesOfMonthBadRequest() {
		//GIVEN
		userService.addContract(u1, 500, 50);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));

		//WHEN

		responseSalary = crc.getComissionSalesOfMonth("joao@domain.com","2017-101");
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,responseSalary.getStatusCode());			
	}
	
	/**
	 * testGetComissionSalesOfMonth() controller
	 * 
	 * <p>GIVEN: 1 user with 1 contract [ 500 base salary and 50 percent of comissionSales ] and with 5 sales in this month</p>
	 * <p>WHEN: get comission sales amount of that unknown user in current month with invalid date</p>
	 * <p>THEN: controller return response code OK and respective SalaryRestDTO</p>
	 */
	@Test
	public void testGetComissionSalesOfMonthNotFound() {
		//GIVEN
		userService.addContract(u1, 500, 50);
		userService.findUserByEmail("joao@domain.com").getLastContract().setStartDate(LocalDate.now().minusYears(2));

		//WHEN

		responseSalary = crc.getComissionSalesOfMonth("unknwon@domain.com","2017-10");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,responseSalary.getStatusCode());			
	}

}

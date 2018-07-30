package bsmanagement.controllers.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import bsmanagement.dto.rest.AddressRestDTO;
import bsmanagement.jparepositories.classtests.RoleRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.Address;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.roles.RoleName;

public class AddressRestControllerTest {
	
	UserService userService;
	AddressRestController arc;
	UserRepositoryClass userRepositoryClass;
	RoleRepositoryClass roleRepositoryClass;
	User u1, u2, u3;
	LocalDate birth1,birth2;
	
	ResponseEntity<AddressRestDTO> response;
	ResponseEntity<AddressRestDTO> expected;
	ResponseEntity<List<AddressRestDTO>> responseList;
	ResponseEntity<List<AddressRestDTO>> expectedList;
	
	AddressRestDTO address_dto1;
	AddressRestDTO address_dto2;
	AddressRestDTO address_dto3;

	@Before
	public void setUp(){
		userService = new UserService();
		userRepositoryClass = new UserRepositoryClass();
		roleRepositoryClass = new RoleRepositoryClass();
		userService.setUserRepository(userRepositoryClass);
		userService.setRoleRepository(roleRepositoryClass);
		Address.setStartIdGenerator(1);
		arc = new AddressRestController(userService);
		address_dto1 = new AddressRestDTO();
		
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
	 * testAddAddress() controller
	 * 
	 * <p>GIVEN: 1 user with no addresses</p>
	 * <p>WHEN: add a valid address to an user</p>
	 * <p>THEN: controller return response code CREATED and respective new address in body</p>
	 */
	@Test
	public void testAddAddressSuccess() {
		//GIVEN
		assertEquals(u1.getAddressList().size(),0);
		
		//WHEN
		
		address_dto1.setAddressDescription("CASA");
		address_dto1.setCity("Mangualde");
		address_dto1.setCountry("Portugal");
		address_dto1.setPostalCode("3530");
		address_dto1.setStreet("Passal 3b");
		response = arc.addAddress("joao@domain.com", address_dto1);
		
		//THEN
		assertEquals(address_dto1,response.getBody());
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals(u1.getAddressList().size(),1);
	}
	
	/**
	 * testAddAddress() controller
	 * 
	 * <p>GIVEN: 1 user with no addresses</p>
	 * <p>WHEN: add a valid address to an unknown user</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testAddAddressNotFound() {
		//GIVEN
		assertEquals(u1.getAddressList().size(),0);
		
		//WHEN
		
		address_dto1.setAddressDescription("CASA");
		address_dto1.setCity("Mangualde");
		address_dto1.setCountry("Portugal");
		address_dto1.setPostalCode("3530");
		address_dto1.setStreet("Passal 3b");
		response = arc.addAddress("unknown@domain.com", address_dto1);
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
		assertEquals(u1.getAddressList().size(),0);
	}
	
	/**
	 * testAddAddress() controller
	 * 
	 * <p>GIVEN: 1 user with no addresses</p>
	 * <p>WHEN: add a invalid address to an user - invalid address description</p>
	 * <p>THEN: controller return response code BAD REQUEST</p>
	 */
	@Test
	public void testAddAddressBadRequest() {
		//GIVEN
		assertEquals(u1.getAddressList().size(),0);
		
		//WHEN
		address_dto1.setCity("Mangualde");
		address_dto1.setCountry("Portugal");
		address_dto1.setPostalCode("3530");
		address_dto1.setStreet("Passal 3b");
		response = arc.addAddress("joao@domain.com", address_dto1);
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		assertEquals(u1.getAddressList().size(),0);
	}

	/**
	 * testListAddresses() controller
	 * 
	 * <p>GIVEN: 1 user with 2 addresses</p>
	 * <p>WHEN: list all addresses of that user</p>
	 * <p>THEN: controller return response code OK and respective list of addresses</p>
	 */
	@Test
	public void testListAddresses() {
		//GIVEN
		assertEquals(u1.getAddressList().size(),0);
		Address a1 = u1.createAddress("Casa", "Mangualde", "3530", "Mangualde", "Portugal");
		Address a2 = u1.createAddress("Trabalho", "Porto", "4425", "Porto", "Portugal");
		u1.addAddress(a1);
		u1.addAddress(a2);
		
		//WHEN
		responseList = arc.listAddresses("joao@domain.com");
		
		//THEN
		assertEquals(HttpStatus.OK,responseList.getStatusCode());
		assertEquals(u1.getAddressList().size(),2);		
	}
	
	/**
	 * testListAddresses() controller
	 * 
	 * <p>GIVEN: 1 user with 2 addresses</p>
	 * <p>WHEN: list all addresses of unknown user</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testListAddressesUserNotFound() {
		//GIVEN
		assertEquals(u1.getAddressList().size(),0);
		Address a1 = u1.createAddress("Casa", "Mangualde", "3530", "Mangualde", "Portugal");
		Address a2 = u1.createAddress("Trabalho", "Porto", "4425", "Porto", "Portugal");
		u1.addAddress(a1);
		u1.addAddress(a2);
		
		//WHEN
		responseList = arc.listAddresses("unknown@domain.com");
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,responseList.getStatusCode());		
	}
	
	/**
	 * testRemoveress() controller
	 * 
	 * <p>GIVEN: 1 user with no addresses</p>
	 * <p>WHEN: remove address of an user without addresses</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testRemoveAddressNoAddresses() {
		//GIVEN
		assertEquals(u1.getAddressList().size(),0);	
		//WHEN
		response = arc.removeAddress("pedro@gmail.uk", 1);
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());		
	}
	
	/**
	 * testRemoveAddress() controller
	 * 
	 * <p>GIVEN: 1 user with no addresses</p>
	 * <p>WHEN: remove address of an user without addresses</p>
	 * <p>THEN: controller return response code NOT FOUND</p>
	 */
	@Test
	public void testRemoveAddressUserNotFound() {
		//GIVEN
		assertEquals(u1.getAddressList().size(),0);	
		//WHEN
		response = arc.removeAddress("unknwon@gmail.uk", 1);
		
		//THEN
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());		
	}
	

	/**
	 * testRemoveAddress() controller
	 * 
	 * <p>GIVEN: 1 user with 2 addresses</p>
	 * <p>WHEN: remove address</p>
	 * <p>THEN: controller return response code OK</p>
	 */
	@Test
	public void testRemoveAddressSuccess() {
		//GIVEN
		assertEquals(u1.getAddressList().size(),0);
		Address a1 = u1.createAddress("Casa", "Mangualde", "3530", "Mangualde", "Portugal");
		Address a2 = u1.createAddress("Trabalho", "Porto", "4425", "Porto", "Portugal");
		u1.addAddress(a1);
		u1.addAddress(a2);
		assertEquals(u1.getAddressList().size(),2);
		
		//WHEN
		response = arc.removeAddress("joao@domain.com", 1);
		
		//THEN
		assertEquals(HttpStatus.OK,response.getStatusCode());	
		assertEquals(u1.getAddressList().size(),1);
	}

}

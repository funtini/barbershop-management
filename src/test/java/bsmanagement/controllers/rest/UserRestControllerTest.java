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

import bsmanagement.dto.rest.UserRestDTO;
import bsmanagement.jparepositories.classtests.RoleRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.Address;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.roles.Role;
import bsmanagement.model.roles.RoleName;
import bsmanagement.payload.UserIdentityAvailability;

public class UserRestControllerTest {

	UserService userService;
	UserRestController urc;
	UserRepositoryClass userRepositoryClass;
	RoleRepositoryClass roleRepositoryClass;
	User u1, u2, u3;
	LocalDate birth1,birth2;
	Address a1,a2,a3;
	
	ResponseEntity<UserRestDTO> response;
	ResponseEntity<UserRestDTO> expected;
	ResponseEntity<List<UserRestDTO>> responseList;
	ResponseEntity<List<UserRestDTO>> expectedList;
	
	UserRestDTO u1_DTO;
	UserRestDTO u2_DTO;
	UserRestDTO u3_DTO;
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>BirthDate [birth1] : 17/03/1989 </p>
	 * <p>BirthDate [birth2] : 21/07/1988 </p>
	 * 
	 * <p>User [u1] : ["JOAO",birth1,"joao@gmail.com","914047935","324666433"] </p>
	 * <p>User [u2] : ["PEDRO",birth2,"pedro@domain.uk","915557911","123555433"] </p>
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
		urc = new UserRestController(userService);
		
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
		userService.deactivateUser(u3);
		
		u1_DTO = u1.toRestDTO();
		u2_DTO = u2.toRestDTO();
		u3_DTO = u3.toRestDTO();
		
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
	 * testCheckEmailAvailability() controller
	 * 
	 * <p>GIVEN: 3 users added to userService</p>
	 * <p>WHEN: check availability of an existing email by controller</p>
	 * <p>THEN: payload of controller return false</p>
	 */
	@Test
	public void testCheckEmailAvailabilityExistingEmail() {
		//GIVEN
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserIdentityAvailability result = urc.checkEmailAvailability("joao@domain.com");
		
		//THEN
		UserIdentityAvailability notAvailable = new UserIdentityAvailability(false);
		assertEquals(result.getAvailable(),notAvailable.getAvailable());
	}
	
	/**
	 * testCheckEmailAvailability() controller
	 * 
	 * <p>GIVEN: 3 users added to userService</p>
	 * <p>WHEN: check availability of new email by controller</p>
	 * <p>THEN: payload of controller return true</p>
	 */
	@Test
	public void testCheckEmailAvailabilityNewEmail() {
		//GIVEN
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserIdentityAvailability result = urc.checkEmailAvailability("rogerio@gmail.com");
		
		//THEN
		UserIdentityAvailability isAvailable = new UserIdentityAvailability(true);
		assertEquals(result.getAvailable(),isAvailable.getAvailable());
	}

	
	/**
	 * testGetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService</p>
	 * <p>WHEN: get user profile by email</p>
	 * <p>THEN: controller return response code OK with respective UserRestDTO in body</p>
	 */
	@Test
	public void testGetUserProfileSuccess() {
		//GIVEN
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		response = urc.getUserProfile("joao@domain.com");
		
		//THEN
		assertEquals(response.getStatusCode(),HttpStatus.OK);		
		assertEquals(response.getBody(),u1_DTO);		
	}
	
	/**
	 * testGetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService</p>
	 * <p>WHEN: get user profile by a non existing email</p>
	 * <p>THEN: controller return response code NOTFOUND</p>
	 */
	@Test
	public void testGetUserProfileNotFound() {
		//GIVEN
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		response = urc.getUserProfile("rogerio@domain.com");
		
		//THEN

		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);				
	}

	/**
	 * testListAllUsers() controller
	 * 
	 * <p>GIVEN: 3 users added to userService</p>
	 * <p>WHEN: get list of all users</p>
	 * <p>THEN: controller return all users registered on service</p>
	 */
	@Test
	public void testListAllUsers() {
		//GIVEN
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		responseList = urc.listAllUsers();
		
		//THEN
		
		List<UserRestDTO> expectedBody = new ArrayList<>();
		expectedBody.add(u1_DTO);
		expectedBody.add(u2_DTO);
		expectedBody.add(u3_DTO);
		
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(expectedBody,responseList.getBody());
	
	}

	/**
	 * testListActiveUsers() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is inactive</p>
	 * <p>WHEN: get list of all active users</p>
	 * <p>THEN: controller return all current active users</p>
	 */
	@Test
	public void testListActiveUsers() {
		//GIVEN
		assertEquals(userService.listActiveUsers().size(),2);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		responseList = urc.listActiveUsers();
		
		//THEN
		
		List<UserRestDTO> expectedBody = new ArrayList<>();
		expectedBody.add(u1_DTO);
		expectedBody.add(u2_DTO);
		
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(expectedBody,responseList.getBody());		
	}

	
	/**
	 * testListInactiveUsers() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is inactive</p>
	 * <p>WHEN: get list of all inactive users</p>
	 * <p>THEN: controller return all current inactive users</p>
	 */
	@Test
	public void testListInactiveUsers() {
		//GIVEN
		assertEquals(userService.listActiveUsers().size(),2);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		responseList = urc.listInactiveUsers();
		
		//THEN
		
		List<UserRestDTO> expectedBody = new ArrayList<>();
		expectedBody.add(u3_DTO);
		
		assertEquals(responseList.getStatusCode(),HttpStatus.OK);
		assertEquals(expectedBody,responseList.getBody());		
	}

	/**
	 * testActivateUser() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is inactive</p>
	 * <p>WHEN: activate an user</p>
	 * <p>THEN: controller return response code ACCEPTED and the list of active users increase to 3</p>
	 */
	@Test
	public void testActivateUserSuccess() {
		//GIVEN
		assertEquals(userService.listActiveUsers().size(),2);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		response = urc.activateUser("sara@gmail.com");
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
		assertEquals(userService.listActiveUsers().size(),3);
		
	}
	
	/**
	 * testActivateUser() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is inactive</p>
	 * <p>WHEN: activate an user with an invalid email</p>
	 * <p>THEN: controller return response code NOT_FOUND and the list of active users still unchanged</p>
	 */
	@Test
	public void testActivateUserNotFound() {
		//GIVEN
		assertEquals(userService.listActiveUsers().size(),2);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		response = urc.activateUser("notfound@email.com");
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
		assertEquals(userService.listActiveUsers().size(),2);
		
	}

	/**
	 * testDeactivateUser() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is inactive</p>
	 * <p>WHEN: deactivate an user with an invalid email</p>
	 * <p>THEN: controller return response code NOT_FOUND and the list of inactive users still unchanged</p>
	 */
	@Test
	public void testDeactivateUserNotFound() {
		//GIVEN
		assertEquals(userService.listInactiveUsers().size(),1);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		response = urc.deactivateUser("notfound@email.com");
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
		assertEquals(userService.listInactiveUsers().size(),1);		
	}
	
	/**
	 * testDeactivateUser() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is inactive</p>
	 * <p>WHEN: deactivate an user</p>
	 * <p>THEN: controller return response code ACCEPTED and the list of inactive users increased to 2</p>
	 */
	@Test
	public void testDeactivateUserSuccess() {
		//GIVEN
		assertEquals(userService.listInactiveUsers().size(),1);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		response = urc.deactivateUser("pedro@gmail.uk");
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
		assertEquals(userService.listInactiveUsers().size(),2);		
	}
	
	/**
	 * testSetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is employer</p>
	 * <p>WHEN: set profile employer to an existing user</p>
	 * <p>THEN: controller return response code ACCEPTED and the list of employers increased to 2</p>
	 */
	@Test
	public void testSetUserProfileEmployerSuccess() {
		//GIVEN
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_USER)).size(),1);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserRestDTO profile = new UserRestDTO();
		profile.setProfile("EmPlOyER");
		response = urc.setUserProfile("pedro@gmail.uk", profile);
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_USER)).size(),2);
	}
	
	/**
	 * testSetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is employer</p>
	 * <p>WHEN: set profile employer to a non existing email</p>
	 * <p>THEN: controller return response code NOT_FOUND and the list of employers still unchanged</p>
	 */
	@Test
	public void testSetUserProfileNotFound() {
		//GIVEN
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_USER)).size(),1);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserRestDTO profile = new UserRestDTO();
		profile.setProfile("EmPlOyER");
		response = urc.setUserProfile("notfound@gmail.uk", profile);
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_USER)).size(),1);
	}
	
	/**
	 * testSetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is employer</p>
	 * <p>WHEN: set profile to an user with no existing role</p>
	 * <p>THEN: controller return response code BAD_REQUEST</p>
	 */
	@Test
	public void testSetUserProfileBadRequest() {
		//GIVEN
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserRestDTO profile = new UserRestDTO();
		profile.setProfile("master");
		response = urc.setUserProfile("pedro@gmail.uk", profile);
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * testSetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is employer</p>
	 * <p>WHEN: set profile to an user with no existing role</p>
	 * <p>THEN: controller return response code BAD_REQUEST</p>
	 */
	@Test
	public void testSetUserProfileBadRequestProfileNull() {
		//GIVEN
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserRestDTO profile = new UserRestDTO();
		response = urc.setUserProfile("pedro@gmail.uk", profile);
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * testSetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which no one is admin</p>
	 * <p>WHEN: set profile administrator to an existing user</p>
	 * <p>THEN: controller return response code ACCEPTED and the list of administrators increased to 1</p>
	 */
	@Test
	public void testSetUserProfileAdminSuccess() {
		//GIVEN
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_ADMINISTRATOR)).size(),0);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserRestDTO profile = new UserRestDTO();
		profile.setProfile("AdmiNistRatoR");
		response = urc.setUserProfile("pedro@gmail.uk", profile);
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_ADMINISTRATOR)).size(),1);
	}
	
	/**
	 * testSetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is storeManager</p>
	 * <p>WHEN: set profile store manager to an existing user</p>
	 * <p>THEN: controller return response code ACCEPTED and the list of storemanagers increased to 2</p>
	 */
	@Test
	public void testSetUserProfileStoreManagerSuccess() {
		//GIVEN
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_STOREMANAGER)).size(),1);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserRestDTO profile = new UserRestDTO();
		profile.setProfile("stOreManagEr");
		response = urc.setUserProfile("joao@domain.com", profile);
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_STOREMANAGER)).size(),2);
	}
	
	/**
	 * testSetUserProfile() controller
	 * 
	 * <p>GIVEN: 3 users added to userService, which one is storeManager</p>
	 * <p>WHEN: set profile store manager to an existing user that is already store manager</p>
	 * <p>THEN: controller return response code ACCEPTED and the list of storemanagers still unchanged</p>
	 */
	@Test
	public void testSetUserProfileSameProfile() {
		//GIVEN
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_STOREMANAGER)).size(),1);
		assertEquals(userService.listAllUsers().size(),3);
		
		//WHEN
		UserRestDTO profile = new UserRestDTO();
		profile.setProfile("stOreManagEr");
		response = urc.setUserProfile("pedro@gmail.uk", profile);
		
		//THEN	
		assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
		assertEquals(userService.searchUserByProfile(new Role(RoleName.ROLE_STOREMANAGER)).size(),1);
	}

}

package bsmanagement.controllers.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import bsmanagement.model.Address;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.payload.LoginRequest;
import bsmanagement.payload.SignUpRequest;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class AuthRestControllerTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
    AuthRestController authController;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	User u1, u2;
	LocalDate birth1,birth2;
	Address a1,a2,a3;
	
	ResponseEntity<?> expected;
	ResponseEntity<?> result;
	
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
    public void setUp() {
		userService.clearData();
		birth1 = LocalDate.of(1998, 3, 17);
		birth2 = LocalDate.of(1988, 7, 21);

		u1 = userService.createUser("JOAO",birth1,"joao@gmail.com","914047935","324666433");
		u2 = userService.createUser("PEDRO",birth2,"pedro@domain.uk","915557911","123555433");

		
		userService.addUser(u1);
		userService.addUser(u2);

		a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");

		u1.addAddress(a1);
		u1.addAddress(a2);
		u2.addAddress(a3);

		userService.setUserProfileEmployer(u1);
		userService.setUserProfileStoreManager(u2);

		u1 = userService.findUserByEmail("joao@gmail.com");
		u2 = userService.findUserByEmail("pedro@domain.uk");

		u1.setPassword(passwordEncoder.encode("12345"));
		u2.setPassword(passwordEncoder.encode("12345"));
	
		userService.updateUser(u1);
		userService.updateUser(u2);	
	}

	/**
	 * Smoke tests to userService and AuthRestController
	 * 
	 */
	@Test
	public void smokeTestController() {
		assertThat(userService).isNotNull();
		assertThat(authController).isNotNull();
		assertEquals(userService.listActiveUsers().size(),2);
	}
	
	
	/**
	 * testAuthenticateUser() controller
	 * 
	 * <p>GIVEN: LoginRequest payload filled by u1 credentials</p>
	 * <p>WHEN: call authenticateUser with LoginRequest as Body</p>
	 * <p>THEN: get response code OK </p>
	 */
	@Test
	public void testAuthenticateUserSuccess() {
		//GIVEN
		LoginRequest login = new LoginRequest();
		login.setPassword("12345");
		login.setUsernameOrEmail("joao@gmail.com");
		
		//WHEN
		result = authController.authenticateUser(login);
		
		//THEN
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}
	

	/**
	 * testRegisterUser() controller
	 * 
	 * <p>GIVEN: SignupRequest payload filled by new user credentials and a service with only 2 users</p>
	 * <p>WHEN: call registerUser method with SignupRequest as his Body</p>
	 * <p>THEN: get response code CREATED and userlist increased to 3 </p>
	 */
	@Test
	public void testRegisterUserSuccess() {
		//GIVEN
		SignUpRequest signupRequest = new SignUpRequest();
		signupRequest.setName("ROGERIO");
		signupRequest.setEmail("rogerio@gmail.com");
		signupRequest.setBirth("1999-11-22");
		signupRequest.setPassword("qwerty1!");
		signupRequest.setPhone("910403032");
		signupRequest.setTaxPayerId("321321312");
		assertEquals(userService.listActiveUsers().size(),2);
		
		//WHEN
		result = authController.registerUser(signupRequest);
		
		//THEN
		assertEquals(HttpStatus.CREATED,result.getStatusCode());
		assertEquals(userService.listActiveUsers().size(),3);
	}
	
	/**
	 * testRegisterUser() controller
	 * 
	 * <p>GIVEN: SignupRequest payload filled by an existing email and a service with only 2 users</p>
	 * <p>WHEN: call registerUser method with SignupRequest as his Body</p>
	 * <p>THEN: get response code BAD_REQUEST and userlist stil has 2 </p>
	 */
	@Test
	public void testRegisterUserEmailInUse() {
		//GIVEN
		SignUpRequest signupRequest = new SignUpRequest();
		signupRequest.setName("Joao");
		signupRequest.setEmail("joao@gmail.com");
		signupRequest.setBirth("1999-11-22");
		signupRequest.setPassword("qwerty1!");
		signupRequest.setPhone("910403032");
		signupRequest.setTaxPayerId("321321312");
		assertEquals(userService.listActiveUsers().size(),2);
		
		//WHEN
		result = authController.registerUser(signupRequest);
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
		assertEquals(userService.listActiveUsers().size(),2);
	}

	

}

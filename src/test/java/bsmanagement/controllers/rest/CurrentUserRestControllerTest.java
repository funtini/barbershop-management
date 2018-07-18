package bsmanagement.controllers.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import bsmanagement.dto.rest.UserRestDTO;
import bsmanagement.model.Address;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.payload.JwtAuthenticationResponse;
import bsmanagement.payload.LoginRequest;
import bsmanagement.security.UserPrincipal;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class CurrentUserRestControllerTest {
	
	
	@LocalServerPort
	private int port;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CurrentUserRestController curc;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	JwtAuthenticationResponse jwt;
	ResponseEntity<JwtAuthenticationResponse> responseEntity;
	
	UserPrincipal userPrincipal;
	
	User u1, u2;
	LocalDate birth1,birth2;
	Address a1,a2,a3;
	
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>BirthDate [birth1] : 17/03/1989 </p>
	 * <p>BirthDate [birth2] : 21/07/1988 </p>
	 * 
	 * <p>User [u1] : ["JOAO",birth1,"joao@gmail.com","914047935","324666433"] </p>
	 * 
	 */
	@Before
    public void setUp() {
		birth1 = LocalDate.of(1998, 3, 17);
		birth2 = LocalDate.of(1988, 7, 21);

		u1 = userService.createUser("JOAO",birth1,"joao@gmail.com","914047935","324666433");
		
		userService.addUser(u1);

		a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");

		u1.addAddress(a1);
		u1.addAddress(a2);
		
		userService.setUserProfileEmployer(u1);

		u1 = userService.findUserByEmail("joao@gmail.com");

		u1.setPassword(passwordEncoder.encode("1234567"));
	
		userService.updateUser(u1);
		
		LoginRequest login = new LoginRequest();
		login.setPassword("1234567");
		login.setUsernameOrEmail("joao@gmail.com");
		
		responseEntity = restTemplate.postForEntity("/api/auth/signin",
				login, JwtAuthenticationResponse.class);
		
		jwt = responseEntity.getBody();	
		
	}
	

	/**
	 * Smoke tests to userService and CurrentUserRestController
	 * 
	 */
	@Test
	public void smokeTestController(){
		assertThat(userService).isNotNull();
		assertThat(curc).isNotNull();
	}
	
	
	/**
	 * testGetCurrentUser() controller
	 * 
	 * <p>GIVEN: Logged user joao@gmail.com</p>
	 * <p>WHEN: call getCurrentUser controller by restTemplate</p>
	 * <p>THEN: get response code OK and credentials information on body</p>
	 */
	@Test
	public void testGetCurrentUser() {
		//GIVEN
		LoginRequest login = new LoginRequest();
		login.setPassword("1234567");
		login.setUsernameOrEmail("joao@gmail.com");
		
		//WHEN
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + jwt.getAccessToken());
		HttpEntity<String> entity = new HttpEntity<>(null,headers);
		
		ResponseEntity<UserRestDTO> response = restTemplate.exchange("/api/users/me", HttpMethod.GET
				,entity,UserRestDTO.class);
		
		//THEN
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(login.getUsernameOrEmail(),response.getBody().getEmail());
		assertEquals(u1.getName(),response.getBody().getName());
		
	}

	
	/**
	 * testEditPersonalData() controller
	 * 
	 * <p>GIVEN: Logged user joao@gmail.com</p>
	 * <p>WHEN: call editPersonalData controller by restTemplate and edit name and phone</p>
	 * <p>THEN: get response code ACCEPTED and fields were changed successfully</p>
	 */
	@Test
	public void testEditPersonalDataSuccess() {
		//GIVEN
		UserRestDTO userDTO = new UserRestDTO();
		userDTO.setName("New Name");
		userDTO.setPhone("9100099900");
		
		//WHEN
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + jwt.getAccessToken());
		HttpEntity<UserRestDTO> entity = new HttpEntity<>(userDTO,headers);
		
		ResponseEntity<UserRestDTO> response = restTemplate.exchange("/api/users/me", HttpMethod.PUT
				,entity,UserRestDTO.class);
		
		//THEN
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
		assertEquals("New Name",response.getBody().getName());
		assertEquals("9100099900",response.getBody().getPhone());		

	}
	
	/**
	 * testEditPersonalData() controller
	 * 
	 * <p>GIVEN: Logged user joao@gmail.com</p>
	 * <p>WHEN: call editPersonalData controller by restTemplate and edit name,phone,birth and tpi</p>
	 * <p>THEN: get response code ACCEPTED and fields were changed successfully</p>
	 */
	@Test
	public void testEditPersonalDataSuccessFullFields() {
		//GIVEN
		UserRestDTO userDTO = new UserRestDTO();
		userDTO.setName("New Name");
		userDTO.setPhone("9100099900");
		userDTO.setBirth(LocalDate.of(1989, 11, 30));
		userDTO.setTaxPayerId("321321321");
		
		//WHEN
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + jwt.getAccessToken());
		HttpEntity<UserRestDTO> entity = new HttpEntity<>(userDTO,headers);
		
		ResponseEntity<UserRestDTO> response = restTemplate.exchange("/api/users/me", HttpMethod.PUT
				,entity,UserRestDTO.class);
		
		//THEN
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
		assertEquals("New Name",response.getBody().getName());
		assertEquals("9100099900",response.getBody().getPhone());	
		assertEquals("321321321",response.getBody().getTaxPayerId());	
		assertEquals(LocalDate.of(1989, 11, 30),response.getBody().getBirth());	
	}
	
	
	/**
	 * testEditPersonalData() controller - bad request
	 * 
	 * <p>GIVEN: Logged user joao@gmail.com</p>
	 * <p>WHEN: call editPersonalData controller by restTemplate and edit birth with invalid date</p>
	 * <p>THEN: get response code BAD REQUEST and fields still unchanged</p>
	 */
	@Test
	public void testEditPersonalDataBadRequestInvalidDate() {
		//GIVEN
		UserRestDTO userDTO = new UserRestDTO();
		userDTO.setName("New Name");
		userDTO.setBirth(LocalDate.of(2289, 11, 30));
		
		//WHEN
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + jwt.getAccessToken());
		HttpEntity<UserRestDTO> entity = new HttpEntity<>(userDTO,headers);
		
		ResponseEntity<UserRestDTO> response = restTemplate.exchange("/api/users/me", HttpMethod.PUT
				,entity,UserRestDTO.class);
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

	}
	
	
	/**
	 * testEditPersonalData() controller - bad request
	 * 
	 * <p>GIVEN: Logged user joao@gmail.com</p>
	 * <p>WHEN: call editPersonalData controller by restTemplate with empty body</p>
	 * <p>THEN: get response code BAD REQUEST and fields still unchanged</p>
	 */
	@Test
	public void testEditPersonalDataBadRequestNoFieldsChanged() {
		//GIVEN
		UserRestDTO userDTO = new UserRestDTO();
		
		//WHEN
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + jwt.getAccessToken());
		HttpEntity<UserRestDTO> entity = new HttpEntity<>(userDTO,headers);
		
		ResponseEntity<UserRestDTO> response = restTemplate.exchange("/api/users/me", HttpMethod.PUT
				,entity,UserRestDTO.class);
		
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

	}


}

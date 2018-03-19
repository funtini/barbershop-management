package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Address;
import bsmanagement.model.User;
import bsmanagement.model.User.UserProfile;
import bsmanagement.model.UserRegistry;
import system.dto.UserLoginDTO;



/**
 * 
 * Unit tests for UserRegistry Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class UserRegistryTest {
	
	UserRegistry uReg;

	Address a1;
	Address a2;
	Address a3;
	
	LocalDate birth1;
	LocalDate birth2;
	LocalDate birth3;
	
	User u1;
	User u2;
	User u3;
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Date [birth1] : 1998/03/17 </p>
	 * <p>Date [birth2] : 1988/07/21 </p>
	 * <p>Date [birth3] : 1968/09/25 </p>
	 * 
	 * <p>User [u1] : ["JOAO",birth1,"joao@domain.com","914047935","324666433"] </p>
	 * <p>User [u2] : ["PEDRO",birth2,"pedro@dgmail.uk","915557911","123555433"] </p>
	 * <p>User [u3] : ["ANTONIO",birth3,"antonio@domain","962337135","367876433"] </p>
	 * 
	 * <p>Address [ad1] -> ['CASA','RUA DO AMARO','3550-444','VISEU','PORTUGAL'] </p>
	 * <p>Address [ad2] -> ['TRABALHO','RUA DO PASSAL','3530-194','MANGUALDE','PORTUGAL'] </p>
	 * <p>Address [ad3] -> ['CASA','RUA LUIS CAMOES','4425-651','PORTO','PORTUGAL'] </p>
	 * 
	 * 
	 */
	@Before
	public void setUp() {
		uReg = new UserRegistry();
		User.setStartIdGenerator(1);
		Address.setStartIdGenerator(1);
		a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		
		birth1 = LocalDate.of(1998, 3, 17);
		birth2 = LocalDate.of(1988, 7, 21);
		birth3 = LocalDate.of(1968, 9, 25);
		
		u1 = uReg.createUser("JOAO",birth1,"joao@domain.com","914047935","324666433");
		u2 = uReg.createUser("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		u3 = uReg.createUser("ANTONIO",birth3,"antonio@domain","962337135","367876433");
		
	}

	@Test
	public void testGetUsersList() {
		//Given: 2 users added
		assertEquals(uReg.addUser(u1),true);
		assertEquals(uReg.addUser(u2),true);
		//When: get users list
		List<User> expect = new ArrayList<>();
		expect.add(u1);
		expect.add(u2);
		List<User> result = uReg.getUsersList();
		//Then: expect and result are same
		assertEquals(expect,result);
	}

	@Test
	public void testAddUser() {
		assertEquals(uReg.addUser(u1),true);
		assertEquals(uReg.addUser(u1),false);
		assertEquals(uReg.addUser(u2),true);
		assertEquals(uReg.addUser(u3),false);
	}
	
	@Test
	public void testAddUserAlreadyExistMail() {
		assertEquals(uReg.addUser(u1),true);
		User sameEmail =  uReg.createUser("ROGERIO",birth2,"joao@domain.com","93127935","3321321433");
		assertEquals(uReg.addUser(sameEmail),false);
		
	}

	@Test
	public void testGetUserByEmail() {
		
		//Given: 2 users added
		assertEquals(uReg.addUser(u1),true);
		assertEquals(uReg.addUser(u2),true);
		//When: get user by email
		User expect = uReg.getUserByEmail("joao@domain.com");
		User nullUser = uReg.getUserByEmail("joao@dsin.com");
		//Then users are the same
		assertEquals(expect,u1);
		assertEquals(nullUser,null);
	}

	@Test
	public void testSearchUserByProfile() {
		//Given: 2 users employer added and 1 user admin added
		assertEquals(uReg.addUser(u1),true);
		assertEquals(uReg.addUser(u2),true);
		u3.setEmail("antonio@gmail.com");
		assertEquals(uReg.addUser(u3),true);
		uReg.setUserProfileAdmin(u3);
		//When: get users list
		List<User> expectEmployer = new ArrayList<>();
		List<User> expectAdmin = new ArrayList<>();
		expectEmployer.add(u1);
		expectEmployer.add(u2);
		expectAdmin.add(u3);
		List<User> resultEmployer = uReg.searchUserByProfile(UserProfile.EMPLPOYER);
		List<User> resultAdmin = uReg.searchUserByProfile(UserProfile.ADMINISTRATOR);
		//Then: expect and result are same
		assertEquals(expectEmployer,resultEmployer);	
		assertEquals(expectAdmin,resultAdmin);
		uReg.setUserProfileEmployer(u3);
		assertEquals(u3.getProfile(),UserProfile.EMPLPOYER);
	}

	@Test
	public void testSetUserActiveAndDeactivate() {
		//Given: 1 user added and deactivated
		assertEquals(uReg.addUser(u1),true);
		assertEquals(uReg.deactivateUser(u1),true);
		assertEquals(uReg.deactivateUser(u1),false);
		assertEquals(u1.isActive(),false);
		//When: active user again

		assertEquals(uReg.setUserActive(u1),true);
		assertEquals(uReg.setUserActive(u1),false);
		
		//Then user is active
		assertEquals(u1.isActive(),true);	
	}


	@Test
	public void testValidateDataValidLogin() {
		//Given: 1 user added and deactivated
		assertEquals(uReg.addUser(u1),true);
		u1.setPassword("qwerty");
		//When: validate user
		UserLoginDTO validLogin = uReg.validateData("joao@domain.com", "qwerty");
		UserLoginDTO expectValid = new UserLoginDTO(u1.getName(), u1.getEmailAddress(), u1.getProfile().toString(),
				"\n" + u1.getProfile().toString() + " " + u1.getName() + " Successfully Logged\n");

		//Then: user is sucessfull logged
		assertEquals(validLogin.getMessage(),expectValid.getMessage());			
	}
	
	@Test
	public void testValidateDataInvalidMail() {
		//Given: 1 user added and deactivated
		assertEquals(uReg.addUser(u1),true);
		u1.setPassword("qwerty");
		//When: validate user
		UserLoginDTO invalidLogin = uReg.validateData("joao@main.com", "qwerty");
		
		UserLoginDTO expectInvalid = new UserLoginDTO("Invalid Email or Password\n");
		
		//Then: user got invalid email or password
		assertEquals(invalidLogin.getMessage(),expectInvalid.getMessage());				
	}
	
	@Test
	public void testValidateDataInvalidPassword() {
		//Given: 1 user added and deactivated
		assertEquals(uReg.addUser(u1),true);
		u1.setPassword("qwerty");
		//When: validate user
		
		UserLoginDTO invalidLogin = uReg.validateData("joao@domain.com", "qdsrty");
		UserLoginDTO expectInvalid = new UserLoginDTO("Invalid Email or Password\n");
		
		//Then: user got invalid email or password
		assertEquals(invalidLogin.getMessage(),expectInvalid.getMessage());			
	}

}

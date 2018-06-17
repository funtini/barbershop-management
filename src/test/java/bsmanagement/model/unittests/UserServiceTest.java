package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.jparepositories.classtests.RoleRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.Address;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.roles.Role;
import bsmanagement.model.roles.RoleName;

/**
 * 
 * Unit tests for User Service Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class UserServiceTest {
	
	UserService userService;
	UserRepositoryClass userRepositoryClass;
	RoleRepositoryClass roleRepositoryClass;
	

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
		userService = new UserService();
		userRepositoryClass = new UserRepositoryClass();
		roleRepositoryClass = new RoleRepositoryClass();
		userService.setUserRepository(userRepositoryClass);
		userService.setRoleRepository(roleRepositoryClass);
		Address.setStartIdGenerator(1);
		a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		
		birth1 = LocalDate.of(1998, 3, 17);
		birth2 = LocalDate.of(1988, 7, 21);
		birth3 = LocalDate.of(1968, 9, 25);
		
		userService.addRole(RoleName.ROLE_USER);
    	userService.addRole(RoleName.ROLE_STOREMANAGER);
    	userService.addRole(RoleName.ROLE_ADMINISTRATOR);
		
		u1 = userService.createUser("JOAO",birth1,"joao@domain.com","914047935","324666433");
		u2 = userService.createUser("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		u3 = userService.createUser("ANTONIO",birth3,"antonio@domain","962337135","367876433");
		
	}

	@Test
	public void testListAllUsers() {
		//Given: 2 users added
		assertEquals(userService.addUser(u1),true);
		assertEquals(userService.addUser(u2),true);
		//When: get users list
		List<User> expect = new ArrayList<>();
		expect.add(u1);
		expect.add(u2);
		List<User> result = userService.listAllUsers();
		//Then: expect and result are same
		assertEquals(expect,result);
	}

	@Test
	public void testAddUser() {
		assertEquals(userService.addUser(u1),true);
		assertEquals(userService.addUser(u1),false);
		assertEquals(userService.addUser(u2),true);
		assertEquals(userService.addUser(u3),false);
	}
	
	@Test
	public void testAddUserAlreadyExistMail() {
		assertEquals(userService.addUser(u1),true);
		User sameEmail =  userService.createUser("ROGERIO",birth2,"joao@domain.com","93127935","3321321433");
		assertEquals(userService.addUser(sameEmail),false);
		
	}

	@Test
	public void testFindUserByEmail() {
		
		//Given: 2 users added
		assertEquals(userService.addUser(u1),true);
		assertEquals(userService.addUser(u2),true);
		//When: get user by email
		User expect = userService.findUserByEmail("joao@domain.com");
		User nullUser = userService.findUserByEmail("joao@dsin.com");
		//Then users are the same
		assertEquals(expect,u1);
		assertEquals(nullUser,null);
	}

	@Test
	public void testSearchUserByProfile() {
		//Given: 2 users employer added and 1 user admin added
		assertEquals(userService.addUser(u1),true);
		assertEquals(userService.addUser(u2),true);
		userService.setUserProfileEmployer(u1);
		userService.setUserProfileEmployer(u2);
		u3.setEmail("antonio@gmail.com");
		assertEquals(userService.addUser(u3),true);
		userService.setUserProfileAdmin(u3);
		//When: get users list
		List<User> expectEmployer = new ArrayList<>();
		List<User> expectAdmin = new ArrayList<>();
		expectEmployer.add(u1);
		expectEmployer.add(u2);
		expectAdmin.add(u3);
		List<User> resultEmployer = userService.searchUserByProfile(new Role(RoleName.ROLE_USER));
		List<User> resultAdmin = userService.searchUserByProfile(new Role(RoleName.ROLE_ADMINISTRATOR));
		//Then: expect and result are same
		assertEquals(expectEmployer,resultEmployer);	
		assertEquals(expectAdmin,resultAdmin);
		userService.setUserProfileEmployer(u3);
		assertEquals(u3.getRoles().contains(new Role(RoleName.ROLE_USER)),true);
	}

	@Test
	public void testSetUserActiveAndDeactivate() {
		//Given: 1 user added and deactivated
		assertEquals(userService.addUser(u1),true);
		assertEquals(userService.deactivateUser(u1),true);
		assertEquals(userService.deactivateUser(u1),false);
		assertEquals(u1.isActive(),false);
		//When: active user again

		assertEquals(userService.setUserActive(u1),true);
		assertEquals(userService.setUserActive(u1),false);
		
		//Then user is active
		assertEquals(u1.isActive(),true);	
	}


	
	@Test
	public void testListActiveUsers() {
		//Given: 2 users added
		assertEquals(userService.addUser(u1),true);
		assertEquals(userService.addUser(u2),true);
		assertEquals(userService.deactivateUser(u2),true);
		//When: get users list
		List<User> expect = new ArrayList<>();
		expect.add(u1);
		List<User> result = userService.listActiveUsers();
		//Then: expect and result are same
		assertEquals(expect,result);
	}
	
	@Test
	public void testListInactiveUsers() {
		//Given: 2 users added
		assertEquals(userService.addUser(u1),true);
		assertEquals(userService.addUser(u2),true);
		assertEquals(userService.deactivateUser(u2),true);
		//When: get users list
		List<User> expect = new ArrayList<>();
		expect.add(u2);
		List<User> result = userService.listInactiveUsers();
		//Then: expect and result are same
		assertEquals(expect,result);
	}
	
	@Test
	public void testSetProfileStoreManager() {
		//Given: 1 employer added 
		
		assertEquals(userService.addUser(u1),true);
		assertEquals(userService.setUserProfileEmployer(u1),true);
		assertEquals(userService.findUserByEmail("joao@domain.com").hasRoleUser(),true);
		//When: set profile to Store Manager
		assertEquals(userService.setUserProfileStoreManager(u1),true);
		//Then: expect and result are same
		assertEquals(userService.findUserByEmail("joao@domain.com").hasRoleStoreManager(),true);
	}
	
	
	@Test
	public void testMethodOnNonRegisteredUser() {	
		assertEquals(userService.setUserProfileStoreManager(u1),false);
		assertEquals(userService.setUserProfileEmployer(u1),false);
		assertEquals(userService.setUserProfileAdmin(u1),false);
		assertEquals(userService.updateUser(u1),false);
	}
	
	
}

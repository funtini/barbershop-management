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
import system.dto.UserLoginDTO;


/**
 * 
 * Unit tests for User Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class UserTest {

	
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
	public void setUp(){
		
		User.setStartIdGenerator(1);
		Address.setStartIdGenerator(1);
		a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		
		birth1 = LocalDate.of(1998, 3, 17);
		birth2 = LocalDate.of(1988, 7, 21);
		birth3 = LocalDate.of(1968, 9, 25);
		
		u1 = new User("JOAO",birth1,"joao@domain.com","914047935","324666433");
		u2 = new User("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		u3 = new User("ANTONIO",birth3,"antonio@domain","962337135","367876433");
		
	}

	@Test
	public void testHashCode() {
		User.setStartIdGenerator(1);
		User userTest = new User("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		assertEquals(userTest.hashCode(),u1.hashCode());
	}

	@Test
	public void testIsValid() {
		//valid users
		assertEquals(u1.isValid(),true);
		assertEquals(u2.isValid(),true);
		//Invalid user by empty name
		u1.setName("");
		assertEquals(u1.isValid(),false);
		//Invalid user by empty phone
		u2.setPhone("");
		assertEquals(u2.isValid(),false);
		//Invalid user by invalid email
		assertEquals(u3.isValid(),false);
	}
	
	@Test
	public void testIsValidNullCases() {
		//Invalid user by null name
		u1.setName(null);
		assertEquals(u1.isValid(),false);
		//Invalid user by null phone
		u2.setPhone(null);
		assertEquals(u2.isValid(),false);
	}

	@Test
	public void testHasValidEmail() {
		assertEquals(u1.hasValidEmail(),true);
		assertEquals(u3.hasValidEmail(),false);
		u2.setEmail("dsada");
		assertEquals(u2.hasValidEmail(),false);
		u1.setEmail("d@d.c");
		assertEquals(u1.hasValidEmail(),false);
	}

	@Test
	public void testCreateAddress() {
		Address.setStartIdGenerator(1);
		Address testAddress = u1.createAddress("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		assertEquals(testAddress.equals(a1),true);
	}

	@Test
	public void testAddAddress() {
		//Given 2 users (u1) (u2)
		List<Address> expect = new ArrayList<>();
		List<Address> expect2 = new ArrayList<>();
		//When add address
		u1.addAddress(a1);
		u1.addAddress(a2);
		u2.addAddress(a3);
		Address invalidAddress  = new Address("","","4425-651","PORTO","PORTUGAL");
		u2.addAddress(invalidAddress);
		expect.add(a1);
		expect.add(a2);
		expect2.add(a3);
		//Then they added to address list of user
		assertEquals(u1.getAddressList(),expect);
		assertEquals(u2.getAddressList(),expect2);
		
	}

	@Test
	public void testRemoveAddress() {
		//Given 2 users (u1) (u2) with 3 address
		List<Address> expect = new ArrayList<>();
		List<Address> expect2 = new ArrayList<>();
		
		u1.addAddress(a1);
		u1.addAddress(a2);
		u2.addAddress(a3);
		//When remove address
		u1.removeAddress(a1);
		u2.removeAddress(a3);
		u2.removeAddress(a2);
		expect.add(a2);
		//Then they added to address list of user
		assertEquals(u1.getAddressList(),expect);
		assertEquals(u2.getAddressList(),expect2);		
	}

	@Test
	public void testSetStartIdGeneratorAndGetID() {
		//Given
		assertEquals(u1.getId(),1);
		assertEquals(u2.getId(),2);
		assertEquals(u3.getId(),3);
		//When
		User.setStartIdGenerator(10);
		User u4 = new User("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		//Then
		assertEquals(u4.getId(),10);		
	}

	@Test
	public void testGetName() {
		
		assertEquals(u1.getName(),"JOAO");
		assertEquals(u2.getName(),"PEDRO");
		assertEquals(u3.getName(),"ANTONIO");
			
	}

	@Test
	public void testGetBirthDate() {
		assertEquals(u1.getBirthDate(),birth1);
		assertEquals(u2.getBirthDate(),birth2);
		assertEquals(u3.getBirthDate(),birth3);
	}

	@Test
	public void testGetEmailAddress() {
		assertEquals(u1.getEmailAddress(),"joao@domain.com");
		assertEquals(u2.getEmailAddress(),"pedro@gmail.uk");
		assertEquals(u3.getEmailAddress(),"antonio@domain");
	}

	@Test
	public void testGetPhone() {
		assertEquals(u1.getPhone(),"914047935");
		assertEquals(u2.getPhone(),"915557911");
		assertEquals(u3.getPhone(),"962337135");
	}

	@Test
	public void testGetTaxPayerId() {
		assertEquals(u1.getTaxPayerId(),"324666433");
		assertEquals(u2.getTaxPayerId(),"123555433");
		assertEquals(u3.getTaxPayerId(),"367876433");
	}

	@Test
	public void testGetAddressList() {
		u1.addAddress(a1);
		u1.addAddress(a1); // repeated address -> no add
		u1.addAddress(a2);
		List<Address> expect = new ArrayList<>();
		expect.add(a1);
		expect.add(a2);
		assertEquals(u1.getAddressList(),expect);

	}

	@Test
	public void testIsActive() {
		assertEquals(u1.isActive(),true);
		assertEquals(u2.isActive(),true);

	}

	@Test
	public void testGetProfile() {
		assertEquals(u1.getProfile(),UserProfile.EMPLPOYER);
		u2.setProfileAdmin();
		assertEquals(u2.getProfile(),UserProfile.ADMINISTRATOR);
	}

	@Test
	public void testSetName() {
		u1.setName("ROGERIO");
		assertEquals(u1.getName(),"ROGERIO");
	}

	@Test
	public void testSetBirth() {
		u1.setBirth(birth3);
		assertEquals(u1.getBirthDate(),birth3);
	}

	@Test
	public void testSetEmail() {
		u1.setEmail("jo@uk.com");
		assertEquals(u1.getEmailAddress(),"jo@uk.com");
		u2.setEmail("jdsa.com");
		assertEquals(u2.getEmailAddress(),"jdsa.com");
	}

	@Test
	public void testSetPhone() {
		u1.setPhone("867764634532");
		assertEquals(u1.getPhone(),"867764634532");
	}

	@Test
	public void testSetTaxPayerId() {
		u1.setTaxPayerId("23213213131");
		assertEquals(u1.getTaxPayerId(),"23213213131");
	}

	@Test
	public void testSetActiveAndDeactivate() {
		assertEquals(u1.isActive(),true);
		u1.deactivate();
		
		assertEquals(u1.isActive(),false);
		u1.setActive();
		assertEquals(u1.isActive(),true);
	}



	@Test
	public void testSetProfileEmployer() {
		
		u2.setProfileAdmin();
		assertEquals(u2.getProfile(),UserProfile.ADMINISTRATOR);
		u2.setProfileEmployer();
		assertEquals(u2.getProfile(),UserProfile.EMPLPOYER);
	}

	@Test
	public void testSetProfileAdmin() {
		assertEquals(u2.getProfile(),UserProfile.EMPLPOYER);
		u2.setProfileAdmin();
		assertEquals(u2.getProfile(),UserProfile.ADMINISTRATOR);
	}

	@Test
	public void testValidatePassword() {
		u1.setPassword("qwerty");
		UserLoginDTO dto = new UserLoginDTO(u1.getName(), u1.getEmailAddress(), u1.getProfile().toString(),"\n" + u1.getProfile().toString().toString() + " " + u1.getName() + " Successfully Logged\n");
		UserLoginDTO wrongPass = new UserLoginDTO("Invalid Email or Password\n");
		assertEquals(u1.validatePassword("qwerty").getMessage(),dto.getMessage());
		assertEquals(u1.validatePassword("dsa2").getMessage(),wrongPass.getMessage());
	}

	@Test
	public void testToString() {
		assertEquals(u1.toString(),"User [" + u1.getId() + "]-[name: " + u1.getName() + ", birth: " + u1.getBirthDate() + ", email: " + u1.getEmailAddress() + ", phone: " + u1.getPhone()
				+ ", taxPayerId: " + u1.getTaxPayerId() + ", ActivationStatus: " + u1.isActive() + ", profile: " + u1.getProfile() + "]");
	}

	@Test
	public void testEqualsObject() {
		assertEquals(u1.equals(u1),true);
		assertEquals(u1.equals(u2),false);
		u2.setName(null);
		assertEquals(u1.equals(u2),false);
		u3.setPhone(null);
		assertEquals(u1.equals(u3),false);
		assertEquals(u1.equals(a1),false);
		User.setStartIdGenerator(1);
		User u4 = new User("JOAO",birth1,"joao@domain.com","914047935","324666433");
		assertEquals(u1.equals(u4),true);
		assertEquals(u1.equals(null),false);
	}

}

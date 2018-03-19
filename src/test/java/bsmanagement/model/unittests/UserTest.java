package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bsmanagement.model.Address;
import bsmanagement.model.Sale;
import bsmanagement.model.User;


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
		fail("Not yet implemented");
	}

	@Test
	public void testIsActive() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProfile() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBirth() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPhone() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTaxPayerId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetActive() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeactivate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetProfileEmployer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetProfileAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidatePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}

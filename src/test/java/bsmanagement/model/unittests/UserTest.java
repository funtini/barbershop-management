package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Address;
import bsmanagement.model.Contract;
import bsmanagement.model.User;
import bsmanagement.model.roles.Role;
import bsmanagement.model.roles.RoleName;


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
	
	Contract c1;
	Contract c2;
	Contract c3;
	
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
	 * <p>Contract [c1] -> ['700','10'] </p>
	 * <p>Contract [c2] -> ['350','60'] </p>
	 * <p>Contract [c3] -> ['350','50'] </p>
	 * 
	 */
	@Before
	public void setUp(){
		
		Address.setStartIdGenerator(1);
		Contract.setStartIdGenerator(1);
		a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		
		birth1 = LocalDate.of(1998, 3, 17);
		birth2 = LocalDate.of(1988, 7, 21);
		birth3 = LocalDate.of(1968, 9, 25);
		
		u1 = new User("JOAO",birth1,"joao@domain.com","914047935","324666433");
		u2 = new User("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		u3 = new User("ANTONIO",birth3,"antonio@domain","962337135","367876433");
		
		c1 = new Contract(700, 10);
		c2 = new Contract(350,	60);
		
		u1.addContract(c1);
		u2.addContract(c2);
		
	}

	@Test
	public void testHashCode() {
		User userTest = new User("PEDRO",birth2,"pedro@gmail.uk","915557911","123555433");
		assertEquals(userTest.hashCode(),u2.hashCode());
		u2.setEmail(null);
		userTest.setEmail(null);
		assertEquals(userTest.hashCode(),u2.hashCode());
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
	public void testGetRole() {
		u1.setProfileEmployer();
		assertEquals(u1.getRoles().contains(new Role(RoleName.ROLE_USER)),true);
		u2.setProfileAdmin();
		assertEquals(u2.getRoles().contains(new Role(RoleName.ROLE_ADMINISTRATOR)),true);
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
		assertEquals(u2.getRoles().contains(new Role(RoleName.ROLE_ADMINISTRATOR)),true);
		u2.setProfileEmployer();
		assertEquals(u2.getRoles().contains(new Role(RoleName.ROLE_USER)),true);
	}
	
	@Test
	public void testSetProfileStoreManager() {
		
		u2.setProfileStoreManager();
		assertEquals(u2.getRoles().contains(new Role(RoleName.ROLE_STOREMANAGER)),true);
	}

	@Test
	public void testSetProfileAdmin() {
		u2.setProfileEmployer();
		assertEquals(u2.getRoles().contains(new Role(RoleName.ROLE_USER)),true);
		u2.setProfileAdmin();
		assertEquals(u2.getRoles().contains(new Role(RoleName.ROLE_ADMINISTRATOR)),true);
	}
	
	@Test
	public void testHasOpenContract() {
		
		//Given
		assertEquals(u1.hasOpenContract(), true);
		//When
		assertEquals(u1.addContract(new Contract(200, 10)),false);
		assertEquals(u1.closeContract(),true);
		assertEquals(u1.closeContract(),false);
		//Then
		assertEquals(u1.hasOpenContract(),false);
	}
	
	@Test
	public void testContractMethodsNoContracts() {

		assertEquals(u3.hasOpenContract(), false);
		assertEquals(u3.getLastContract(),null);
		assertEquals(u3.closeContract(),false);
	
	}
	
	@Test
	public void testGetAllContracts() {
		
		//Given
		List<Contract> expectedList = new ArrayList<>();
		expectedList.add(u1.getLastContract());
		assertEquals(u1.hasOpenContract(), true);		
		
		//When
		assertEquals(u1.closeContract(),true);
		Contract expected = new Contract(100,20);
		assertEquals(u1.addContract(expected),true);
		
		expectedList.add(expected);
		assertEquals(u1.getLastContract(),expected);
		
		//Then
		assertEquals(u1.getAllContracts(),expectedList);
	}
	
	@Test
	public void testCalculateCurrentAccumulateComissionAmountOfMonth() {
		

		assertEquals(u1.getCurrentSalesCommission(), 10.0,0.0);
		
		assertEquals(u2.getCurrentSalesCommission(), 60.0,0.0);
		u2.closeContract();
		assertEquals(u2.getCurrentSalesCommission(), 0.0,0.0);
		
	}
	
	
	@Test
	public void testCalculateAccumulateComissionAmountOfMonth() {
		
		u1.getLastContract().setStartDate(LocalDate.of(2017, 9, 1));
		u1.getLastContract().closeAt(LocalDate.of(2018, 1, 30));
		Contract c1 = new Contract(1000, 35);
		u1.addContract(c1);
		Contract c2 = u1.getLastContract();
		assertEquals(c1,c2);
		u1.getLastContract().setStartDate(LocalDate.of(2018, 2, 1));

		assertEquals(u1.getSalesCommissionOfMonth(YearMonth.of(2017, 9)), 10.0,0.0);
		assertEquals(u1.getSalesCommissionOfMonth(YearMonth.of(2017, 8)), 0.0,0.0);
		assertEquals(u1.getSalesCommissionOfMonth(YearMonth.of(2017, 12)), 10.0,0.0);
		assertEquals(u1.getSalesCommissionOfMonth(YearMonth.of(2018, 2)), 35.0,0.0);
	
		
	}
	

	@Test
	public void testToString() {
		assertEquals(u1.toString(),"User [" + u1.getEmailAddress() + "]-[name: " + u1.getName() + ", birth: " + u1.getBirthDate() + ", phone: " + u1.getPhone()
				+ ", taxPayerId: " + u1.getTaxPayerId() + ", ActivationStatus: " + u1.isActive() + ", profile: " + u1.getRoles().toString() + "]");

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
		User u4 = new User("JOAO",birth1,"joao@domain.com","914047935","324666433");
		assertEquals(u1.equals(u4),true);
		assertEquals(u1.equals(null),false);
		u1.setEmail(null);
		assertEquals(u1.equals(u3),false);
	}

}

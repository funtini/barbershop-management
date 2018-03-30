package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import bsmanagement.model.BookingService;
import bsmanagement.model.Company;
import bsmanagement.model.CustomerService;
import bsmanagement.model.ReportService;
import bsmanagement.model.UserService;

public class CompanyTest {
	
	Company company;

	@Test
	public void testGetTheInstance() {
		company = Company.getTheInstance();
		Company expect = Company.getTheInstance();
		assertEquals(expect,company);
	}

	@Test
	public void testGetBookingRegistry() {
		company = Company.getTheInstance();
		BookingService expect = company.getBookingRegistry();
		assertEquals(expect.getBookings().isEmpty(),true);
		
	}

	@Test
	public void testGetCustomerRegistry() {
		company = Company.getTheInstance();
		ReportService expect = company.getReportRegistry();
		assertEquals(expect.getReports().isEmpty(),true);
	}

	@Test
	public void testGetReportRegistry() {
		company = Company.getTheInstance();
		CustomerService expect = company.getCustomerRegistry();
		assertEquals(expect.getCustomers().isEmpty(),true);
	}
	
	@Test
	public void testGetUserRegistry() {
		company = Company.getTheInstance();
		UserService expect = company.getUserRegistry();
		assertEquals(expect.getUsersList().isEmpty(),true);
	}

}

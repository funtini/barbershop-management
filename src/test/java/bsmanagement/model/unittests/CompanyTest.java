package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import bsmanagement.model.BookingRegistry;
import bsmanagement.model.Company;
import bsmanagement.model.CustomerRegistry;
import bsmanagement.model.ReportRegistry;
import bsmanagement.model.UserRegistry;

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
		BookingRegistry expect = company.getBookingRegistry();
		assertEquals(expect.getBookings().isEmpty(),true);
		
	}

	@Test
	public void testGetCustomerRegistry() {
		company = Company.getTheInstance();
		ReportRegistry expect = company.getReportRegistry();
		assertEquals(expect.getReportS().isEmpty(),true);
	}

	@Test
	public void testGetReportRegistry() {
		company = Company.getTheInstance();
		CustomerRegistry expect = company.getCustomerRegistry();
		assertEquals(expect.getCustomers().isEmpty(),true);
	}
	
	@Test
	public void testGetUserRegistry() {
		company = Company.getTheInstance();
		UserRegistry expect = company.getUserRegistry();
		assertEquals(expect.getUsersList().isEmpty(),true);
	}

}

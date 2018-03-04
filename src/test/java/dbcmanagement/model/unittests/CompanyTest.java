package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import dbcmanagement.model.BookingRegistry;
import dbcmanagement.model.Company;
import dbcmanagement.model.CustomerRegistry;
import dbcmanagement.model.ReportRegistry;

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
		assertEquals(expect.getBookingRegistry().isEmpty(),true);
		
	}

	@Test
	public void testGetCustomerRegistry() {
		company = Company.getTheInstance();
		ReportRegistry expect = company.getReportRegistry();
		assertEquals(expect.getReportList().isEmpty(),true);
	}

	@Test
	public void testGetReportRegistry() {
		company = Company.getTheInstance();
		CustomerRegistry expect = company.getCustomerRegistry();
		assertEquals(expect.getCustomerList().isEmpty(),true);
	}

}

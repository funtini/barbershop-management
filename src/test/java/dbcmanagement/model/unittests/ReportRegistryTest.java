package dbcmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dbcmanagement.model.Customer;
import dbcmanagement.model.Expense;
import dbcmanagement.model.ExpenseRegistry;
import dbcmanagement.model.Product;
import dbcmanagement.model.Sale;
import dbcmanagement.model.SaleRegistry;
import dbcmanagement.model.Expense.expenseType;
import dbcmanagement.model.Product.productType;
import dbcmanagement.model.Report;
import dbcmanagement.model.ReportRegistry;



/**
 * 
 * Unit tests for ReportRegistry Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ReportRegistryTest {
	
	YearMonth ym17;
	YearMonth ym18;
	LocalDateTime dt1;
	LocalDateTime dt2;
	LocalDateTime dt3;
	LocalDateTime dt4;
	LocalDate birthdate1;
	LocalDate birthdate2;
	LocalDate d1;
	LocalDate d2;
	LocalDate d3;
	LocalDate d4;
	LocalDate d5;
	Product p1;
	Product p2;
	Customer c1;
	Customer c2;
	Expense e1;
	Expense e2;
	Expense e3;
	Sale s1;
	Sale s2;
	Sale s3;
	Sale s4;

	SaleRegistry saleList;
	ExpenseRegistry expenseList;
	ReportRegistry reportRegistry;
	List<Report> expect;
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Date [d1] : 05/03/2018 </p>
	 * <p>Date [d2] : 10/04/2018 </p>
	 * <p>ExpenseType [et1] : FIXED </p>
	 * <p>ExpenseType [et2] : ONE-OFF</p>
	 * 
	 * <p>Expense [e1] : ["Agua",'FIXED','35'] </p>
	 * <p>Expense [e2] : ["Internet",'FIXED','50',d2,"6 meses de contrato"] </p>
	 * <p>Expense [e3] : ["Secadores",'ONE-OFF','80',d2,"3 unidades"] </p>
	 * <p>Expense [e4] : ["Shampoos",'ONE-OFF','50',d2,"3 unidades"] </p>
	 * 
	 * 
	 * 
	 */
	@Before
	public void setUp(){
		
		expenseList = new ExpenseRegistry();
		saleList = new SaleRegistry();
		reportRegistry = new ReportRegistry();
		expect = new ArrayList<>();
		
		ym17 = YearMonth.of(2017, 12);
		ym18= YearMonth.of(2018, 1);
		dt1 = LocalDateTime.of(2018,1,10,10,30);
		dt2 = LocalDateTime.of(2018,1,12,11,30);
		dt3 = LocalDateTime.of(2017,12,22,11,30);
		dt4 = LocalDateTime.of(2018,2,5,11,30);
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		d1 = LocalDate.of(2018,2,10);
		d2 = LocalDate.of(2018,2,15);
		d3 = LocalDate.of(2017,12,21);
		d4 = LocalDate.of(2017,10,21);
		d5 = LocalDate.of(2018, 1, 10);
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		
		e1 = expenseList.createExpense("Agua",expenseType.FIXED,35,d1);
		e2 = expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
		e3 = expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades");
		
		s1 = saleList.createSale(dt1, c1,p1);
		s2 = saleList.createSale(dt2, c2,p2);
		
		expenseList.addExpense(e1);
		expenseList.addExpense(e2);
		expenseList.addExpense(e3);
	}

	/**
	 * <h2>getReportList() method test</h2>
	 */
	@Test 
	public void testGetReportList() {
		
		//Given: empty list's
		assertEquals(reportRegistry.getReportList().isEmpty(),true);
	
		//When: set a list with 3 sales
		
		assertEquals(reportRegistry.addReport(ym17),true);	
		assertEquals(reportRegistry.addReport(ym18),true);	
		//Then: get a list with that 3 sales
		expect.add(new Report(ym17));
		expect.add(new Report(ym18));
		assertEquals(expect.equals(reportRegistry.getReportList()),true);
	}
	
	/**
	 * <h2>addReport()  method test</h2>
	 * 
	 * <p>True result returned</p>
	 */
	@Test 
	public void testAddReportTrue() {
		/* Given: 
		 * 		- empty reportRegistry
		 */
		assertEquals(reportRegistry.getReportList().isEmpty(),true);
		/* When: 
		 * 		- add a report
		 */	
		assertEquals(reportRegistry.addReport(ym17),true);	
		/* Then: 
		 * 		- Report is added with YearMonth ym17
		 */
		Report expect = new Report(ym17);
		Report result = reportRegistry.getReportList().get(0);
		
		assertEquals(result,expect);
	}
	
	/**
	 * <h2>addReport()  method test</h2>
	 * 
	 * <p>False result returned</p>
	 */
	@Test 
	public void testAddReportFalse() {
		/* Given: 
		 * 		- empty reportRegistry
		 */
		assertEquals(reportRegistry.getReportList().isEmpty(),true);
		/* When: 
		 * 		- try add 2x same report
		 */	
		assertEquals(reportRegistry.addReport(ym17),true);
		assertEquals(reportRegistry.addReport(ym18),true);
		assertEquals(reportRegistry.addReport(ym17),false);
		assertEquals(reportRegistry.addReport(ym17),false);
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		int expect = 2;
		int result = reportRegistry.getReportList().size();
		
		assertEquals(result,expect);
	}
	
	
	/**
	 * <h2>getReport()  method test</h2>
	 * 
	 * <p>get Report by YearMonth</p>
	 */
	@Test 
	public void testgetReportByYearMonth() {
		/* Given: 
		 * 		- empty reportRegistry
		 */
		assertEquals(reportRegistry.getReportList().isEmpty(),true);
		/* When: 
		 * 		- add a report
		 */	
		assertEquals(reportRegistry.addReport(ym17),true);
		/* Then: 
		 * 		- I can Get report by YearMonth
		 */
		Report expect = new Report(ym17);
		Report result = reportRegistry.getReport(ym17);
		
		assertEquals(result,expect);
	}
	
	
	/**
	 * <h2>addSale()  method test</h2>
	 */
	@Test 
	public void testgetAddSale() {
		
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		assertEquals(reportRegistry.getReportList().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);						//add report 2018/12
		assertEquals(reportRegistry.getReportList().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(reportRegistry.getReportList().size(),2);					//check there is only 2 report on list
		/* When: 
		 * 		- addSale in 12/2017(dt3), 01/2018(dt1 e dt2) and 02/2018 (dt4)
		 */	
		reportRegistry.addSale(dt1, p1);
		reportRegistry.addSale(dt2, c1, p2);
		reportRegistry.addSale(dt3, p1);
		reportRegistry.addSale(dt4, p1);
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(reportRegistry.getReportList().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(reportRegistry.getReportList().size(),3);					//check that number of reports have increased to 3
	}
	
	

	/**
	 * <h2>addExpense()  method test</h2>
	 */
	@Test 
	public void testgetAddExpense() {
		//TODO: TUDO
		
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		assertEquals(reportRegistry.getReportList().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);						//add report 2018/12
		assertEquals(reportRegistry.getReportList().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(reportRegistry.getReportList().size(),2);							//check there is only 2 report on list
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenseList().isEmpty(),true);		//check if expenseList are empty
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenseList().isEmpty(),true);	
		/* When: 
		 * 		- add FIXED expenses at d1(2018/2/10), d3(2017/12/21) and d4(2017/10/21) and ONEOFF at  d2(2018/2/15) and d5(2018/1/10)
		 * 		
		 * 		- d3 should add to rep of ym17 and ym18
		 * 		- d5 should add to rep of ym18
		 * 		- d1 and d2 should create a new report
		 * 		- d4 should create a new report and add to ym17 and ym18
		 */	
		reportRegistry.addExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
		reportRegistry.addExpense("Internet",expenseType.FIXED,50,d3,"6 meses de contrato");
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenseList().size(),1);		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenseList().size(),2);
		assertEquals(reportRegistry.getReportList().size(),2);
		reportRegistry.addExpense("Agua",expenseType.FIXED,35,d1);
		assertEquals(reportRegistry.getReportList().size(),3);	
		reportRegistry.addExpense("Tesouras",expenseType.ONEOFF,70,d2,"5 unidades");
		
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(reportRegistry.getReportList().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(reportRegistry.getReportList().size(),3);					//check that number of reports have increased to 3
		
		reportRegistry.addExpense("MILHA",expenseType.FIXED,70,d4);
		assertEquals(reportRegistry.getReportList().size(),4);	
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenseList().size(),2);		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenseList().size(),3);
		
	}
	
	
	

}

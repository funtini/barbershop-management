package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.model.Customer;
import bsmanagement.model.Expense;
import bsmanagement.model.ExpenseService;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;
import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.Product.productType;
import bsmanagement.model.Report;
import bsmanagement.model.ReportService;



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
	PaymentMethod cash;
	PaymentMethod card;
	Sale s1;
	Sale s2;
	Sale s3;
	Sale s4;

	SaleService saleList;
	ExpenseService expenseList;
	ReportService reportRegistry;
	List<Report> expect;
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Date [d1] : 05/03/2018 </p>
	 * <p>Date [d2] : 10/04/2018 </p>
	 * <p>ExpenseType [et1] : FIXED </p>
	 * <p>ExpenseType [et2] : ONE-OFF</p>
	 * 
	 * <p>Payment [cash] -> ['CASH',0,0] </p>
	 * <p>Payment [card] -> ['CREDIT CARD',0,0] </p>
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
		
		expenseList = new ExpenseService();
		saleList = new SaleService();
		reportRegistry = new ReportService();
		expect = new ArrayList<Report>();
		Expense.setStartIdGenerator(0);
		Sale.setStartIdGenerator(0);
		Product.setStartIdGenerator(0);
		Customer.setStartIdGenerator(0);
		
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
		
		cash = new PaymentMethod("CASH",0.0,0.0);
		card = new PaymentMethod("CASH",1.5,0.5);
		
		s1 = saleList.createSale(dt1, c1,p1,cash);
		s2 = saleList.createSale(dt2, c2,p2,card);
		
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
		assertEquals(reportRegistry.getReports().isEmpty(),true);	
		//When: set a list with 3 sales		
		assertEquals(reportRegistry.addReport(ym17),true);	
		assertEquals(reportRegistry.addReport(ym18),true);	
		//Then: get a list with that 3 sales
		expect.add(new Report(ym17));
		expect.add(new Report(ym18));
		assertEquals(expect.equals(reportRegistry.getReports()),true);
	}
	
	/**
	 * <h2>getSaleList() method test</h2>
	 */
	@Test 
	public void testGetSaleList() {
		
		//Given: empty list's
		assertEquals(reportRegistry.getSaleRegistry().getSales().isEmpty(),true);	
		//When: add 1 sale		
		reportRegistry.addSale(dt1, p1,cash);
		//Then: get size of list with 1 sale
		assertEquals(reportRegistry.getSaleRegistry().getSales().size(),1);
	}
	
	/**
	 * <h2>getExpenseList() method test</h2>
	 */
	@Test 
	public void testGetexpenseList() {
		
		//Given: empty list's
		assertEquals(reportRegistry.getExpenseRegistry().getExpenses().isEmpty(),true);	
		//When: add 1 sale
		reportRegistry.addExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
		//Then: get size of list with 1 sale
		assertEquals(reportRegistry.getExpenseRegistry().getExpenses().size(),1);
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
		assertEquals(reportRegistry.getReports().isEmpty(),true);
		/* When: 
		 * 		- add a report
		 */	
		assertEquals(reportRegistry.addReport(ym17),true);	
		/* Then: 
		 * 		- Report is added with YearMonth ym17
		 */
		Report expect = new Report(ym17);
		Report result = reportRegistry.getReports().get(0);
		
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
		assertEquals(reportRegistry.getReports().isEmpty(),true);
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
		int result = reportRegistry.getReports().size();
		
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
		assertEquals(reportRegistry.getReports().isEmpty(),true);
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
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);						//add report 2018/12
		assertEquals(reportRegistry.getReports().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(reportRegistry.getReports().size(),2);					//check there is only 2 report on list
		/* When: 
		 * 		- addSale in 12/2017(dt3), 01/2018(dt1 e dt2) and 02/2018 (dt4)
		 */	
		reportRegistry.addSale(dt1, p1,cash);
		reportRegistry.addSale(dt2, c1, p2,card);
		reportRegistry.addSale(dt3, p1,cash);
		reportRegistry.addSale(dt4, p1,cash);
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(reportRegistry.getReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(reportRegistry.getReports().size(),3);					//check that number of reports have increased to 3
	}
	
	
	/**
	 * <h2>addSale()  method test</h2>
	 * 
	 * -adding first sale in a month
	 */
	@Test 
	public void testgetAddSaleFirstInMonth() {
		
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		Report rep1801 = new Report(YearMonth.of(2018, 1));
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
	
		/* When: 
		 * 		- addSale in 12/2017(dt3), 01/2018(dt1 e dt2) and 02/2018 (dt4)
		 */	
		reportRegistry.addSale(dt1, c1,p1,cash);
		reportRegistry.addSale(dt4, p1,cash);
		/* Then: 
		 * 		- Reports are created for YearMonths associated to this sales
		 */
		assertEquals(reportRegistry.getReports().isEmpty(),false);
		assertEquals(reportRegistry.getReports().size(),2);				//check that number of reports have increased to 1
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 1)),rep1801);	
	
	}

	/**
	 * <h2>addExpense()  method test</h2>
	 */
	@Test 
	public void testgetAddExpense() {	
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);						//add report 2018/12
		assertEquals(reportRegistry.getReports().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(reportRegistry.getReports().size(),2);							//check there is only 2 report on list
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().isEmpty(),true);		//check if expenseList are empty
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().isEmpty(),true);	
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
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().size(),1);		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().size(),2);
		assertEquals(reportRegistry.getReports().size(),2);
		reportRegistry.addExpense("Agua",expenseType.FIXED,35,d1);
		assertEquals(reportRegistry.getReports().size(),3);	
		reportRegistry.addExpense("Tesouras",expenseType.ONEOFF,70,d2,"5 unidades");
		
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(reportRegistry.getReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(reportRegistry.getReports().size(),3);					//check that number of reports have increased to 3
		
		reportRegistry.addExpense("MILHA",expenseType.FIXED,70,d4);
		assertEquals(reportRegistry.getReports().size(),4);	
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().size(),2);		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().size(),3);
		
	}
	
	/**
	 * <h2>calculateAvgRoi()  method test</h2>
	 * 
	 * Test method without any expenses
	 * 
	 */
	@Test 
	public void testCalculateAvgRoiWithoutExpenses() {
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 * 		- report 2017/12 : has 45 sales (loop)
		 * 		- report 2018/12 : has 105 sales (loop)
		 */
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);					//add report 2018/12
		
		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
			
		assertEquals(reportRegistry.getReport(ym17).getSalesList().getSales().size(),0);		//verify that saleList of each report is empty
		assertEquals(reportRegistry.getReport(ym18).getSalesList().getSales().size(),0);
		
		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
		{
			reportRegistry.addSale(dt1, p1,card);
			reportRegistry.addSale(dt1.plusHours(1), c1, p2,cash);
			reportRegistry.addSale(dt1.plusHours(3), p1,cash);
			reportRegistry.addSale(dt1.plusHours(4), p2,card);
			reportRegistry.addSale(dt1.plusHours(5), p1,cash);
			
		}		
		assertEquals(reportRegistry.getReport(ym18).getSalesList().getSales().size(),105);
	
		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
		{
			reportRegistry.addSale(dt3, p1,card);
			reportRegistry.addSale(dt3.plusHours(1), c1, p2,cash);
			reportRegistry.addSale(dt3.plusHours(3), p2,card);
			reportRegistry.addSale(dt3.plusHours(4), p1,card);
			reportRegistry.addSale(dt3.plusHours(5), p1,cash);
		}
		assertEquals(reportRegistry.getReport(ym17).getSalesList().getSales().size(),45);
		
		/* When: 
		 * 		- calculate average ROI without any expense added
		 */
		double result = reportRegistry.calculateAvgMonthlyRoi();
		/* When: 
		 * 		- the value expected is 0!
		 */
		double expected = 0;
		assertEquals(result,expected,0.0);	
	
	}
	
	
	/**
	 * <h2>calculateAvgRoi()  method test</h2>
	 * 
	 * Test method without any expenses
	 * 
	 */
	@Test 
	public void testCalculateAvgRoiTwoMonths() {
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 * 		- report 2017/12 : has 45 sales (loop) and 3
		 * 		- report 2018/12 : has 105 sales (loop)
		 */
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);					//add report 2018/12
		
		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
			
		assertEquals(reportRegistry.getReport(ym17).getSalesList().getSales().size(),0);		//verify that saleList of each report is empty
		assertEquals(reportRegistry.getReport(ym18).getSalesList().getSales().size(),0);
		
		
		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
		{
			reportRegistry.addSale(dt1, p1,cash);
			reportRegistry.addSale(dt1.plusHours(1), c1, p2,cash);
			reportRegistry.addSale(dt1.plusHours(3), p1,cash);
			reportRegistry.addSale(dt1.plusHours(4), p2,cash);
			reportRegistry.addSale(dt1.plusHours(5), p1,cash);
			
		}		
		assertEquals(reportRegistry.getReport(ym18).getSalesList().getSales().size(),105);
	
		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
		{
			reportRegistry.addSale(dt3, p1,cash);
			reportRegistry.addSale(dt3.plusHours(1), c1, p2,cash);
			reportRegistry.addSale(dt3.plusHours(3), p2,card);
			reportRegistry.addSale(dt3.plusHours(4), p1,cash);
			reportRegistry.addSale(dt3.plusHours(5), p1,card);
		}
		assertEquals(reportRegistry.getReport(ym17).getSalesList().getSales().size(),45);
		
			
		reportRegistry.addExpense("Agua",expenseType.FIXED,35,d3);						//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
		reportRegistry.addExpense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato");
		reportRegistry.addExpense("Luz",expenseType.FIXED,25,d3);
		reportRegistry.addExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().size(),4);
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().size(),3);
		/* When: 
		 * 		- calculate average ROI 
		 */
			double result = reportRegistry.calculateAvgMonthlyRoi();
		/* When: 
		 * 		- the value expected is 667.1!
		 */
		double expected = 667.1;
		assertEquals(Math.round(result*100.0)/100.0,expected,0.0);	
	
	}
	
	
	/**
	 * <h2>calculateAvgMonthlyProfit() method test</h2>
	 * 
	 * 
	 */
	@Test 
	public void testCalculateAvgMonthlyProfitnExpenses() {
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 * 		- report 2017/12 : has 45 sales (loop) and 3
		 * 		- report 2018/12 : has 105 sales (loop)
		 */
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);					//add report 2018/12
		
		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
			
		assertEquals(reportRegistry.getReport(ym17).getSalesList().getSales().size(),0);		//verify that saleList of each report is empty
		assertEquals(reportRegistry.getReport(ym18).getSalesList().getSales().size(),0);
		
		
		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
		{
			reportRegistry.addSale(dt1, p1,cash);
			reportRegistry.addSale(dt1.plusHours(1), c1, p2,cash);
			reportRegistry.addSale(dt1.plusHours(3), p1,cash);
			reportRegistry.addSale(dt1.plusHours(4), p2,cash);
			reportRegistry.addSale(dt1.plusHours(5), p1,cash);
			
		}		
		assertEquals(reportRegistry.getReport(ym18).getSalesList().getSales().size(),105);
	
		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
		{
			reportRegistry.addSale(dt3, p1,card);
			reportRegistry.addSale(dt3.plusHours(1), c1, p2,card);
			reportRegistry.addSale(dt3.plusHours(3), p2,cash);
			reportRegistry.addSale(dt3.plusHours(4), p1,cash);
			reportRegistry.addSale(dt3.plusHours(5), p1,card);
		}
		assertEquals(reportRegistry.getReport(ym17).getSalesList().getSales().size(),45);
		
			
		reportRegistry.addExpense("Agua",expenseType.FIXED,35,d3);						//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
		reportRegistry.addExpense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato");
		reportRegistry.addExpense("Luz",expenseType.FIXED,25,d3);
		reportRegistry.addExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().size(),4);
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().size(),3);
		/* When: 
		 * 		- calculate average PROFIT 
		 */
			double result = reportRegistry.calculateAvgMonthlyProfit();
		/* When: 
		 * 		- the value expected is 850!
		 */
		double expected = 850.0;
		assertEquals(Math.round(result*100.0)/100.0,expected,0.0);	
	
	}
	
	
	/**
	 * <h2>calculateAvgMonthlyProfit() method test</h2>
	 * 
	 * 
	 */
	@Test 
	public void testCalculateAvgMonthlyIncomeAndExpenses() {
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 * 		- report 2017/12 : has 45 sales (loop) and 3
		 * 		- report 2018/12 : has 105 sales (loop)
		 */
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);					//add report 2018/12
		
		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
			
		assertEquals(reportRegistry.getReport(ym17).getSalesList().getSales().size(),0);		//verify that saleList of each report is empty
		assertEquals(reportRegistry.getReport(ym18).getSalesList().getSales().size(),0);
		
		
		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
		{
			reportRegistry.addSale(dt1, p1,cash);
			reportRegistry.addSale(dt1.plusHours(1), c1, p2,cash);
			reportRegistry.addSale(dt1.plusHours(3), p1,card);
			reportRegistry.addSale(dt1.plusHours(4), p2,card);
			reportRegistry.addSale(dt1.plusHours(5), p1,cash);
			
		}		
		assertEquals(reportRegistry.getReport(ym18).getSalesList().getSales().size(),105);
	
		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
		{
			reportRegistry.addSale(dt3, p1,cash);
			reportRegistry.addSale(dt3.plusHours(1), c1, p2,card);
			reportRegistry.addSale(dt3.plusHours(3), p2,card);
			reportRegistry.addSale(dt3.plusHours(4), p1,card);
			reportRegistry.addSale(dt3.plusHours(5), p1,cash);
		}
		assertEquals(reportRegistry.getReport(ym17).getSalesList().getSales().size(),45);
		
			
		reportRegistry.addExpense("Agua",expenseType.FIXED,35,d3);						//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
		reportRegistry.addExpense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato");
		reportRegistry.addExpense("Luz",expenseType.FIXED,25,d3);
		reportRegistry.addExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().size(),4);
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().size(),3);
		/* When: 
		 * 		- calculate average INCOME and EXPENSES
		 */
			double resultIncome = reportRegistry.calculateAvgMonthlySalesAmount();
			double resultExpense = reportRegistry.calculateAvgMonthlyExpensesValue();
		/* When: 
		 * 		- the value's income expected is 850!
		 * 		- the expense amount expected is 850!
		 */
		double expectedIncome = 975.0;
		double expectedExpenses = 125.0;
		assertEquals(Math.round(resultIncome*100.0)/100.0,expectedIncome,0.0);	
		assertEquals(Math.round(resultExpense*100.0)/100.0,expectedExpenses,0.0);	
	
	}
	
	/**
	 * <h2>calculate methods test</h2>
	 * 
	 * - tests for situations when we got only 1 month
	 */
	@Test 
	public void testCalculateMethodsOnlyOneMonth() {
		/* Given: 
		 * 		- reportRegistry with 1 report (2050/03)
		 * 		- report of today month : has 150 sales (loop) and 2 expenses
		 * 
		 */
		YearMonth today = YearMonth.of(2050, 3);
		LocalDateTime dateSale = LocalDateTime.of(2050, 3, 9, 10, 50);
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(today),true);						//add report of today's YearMonth
		
		int monthLen =today.getMonth().length(Year.isLeap(today.getYear()));	// save month length of today
			
		assertEquals(reportRegistry.getReport(today).getSalesList().getSales().size(),0);		//verify that saleList is empty
		
		for (int i=dateSale.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report of this month
		{
			reportRegistry.addSale(dateSale, p1,cash);
			reportRegistry.addSale(dateSale.plusMinutes(1), c1, p2,card);
			reportRegistry.addSale(dateSale.plusMinutes(3), p1,cash);
			reportRegistry.addSale(dateSale.plusMinutes(4), p2,card);
			reportRegistry.addSale(dateSale.plusMinutes(5), p1,cash);
			
		}		
		assertEquals(reportRegistry.getReport(today).getSalesList().getSales().size(),110);

		
			
		reportRegistry.addExpense("Agua",expenseType.FIXED,35,LocalDate.now());		
		reportRegistry.addExpense("LUZ",expenseType.FIXED,55,LocalDate.now());		// add expense
		Expense e = reportRegistry.getExpenseRegistry().searchExpenseByName("LUZ").get(0);//remove expense
		reportRegistry.removeExpense(e);
		reportRegistry.addExpense("Secadores",expenseType.ONEOFF,90,LocalDate.of(2050, 3, 1),"3 unidades");
		
		assertEquals(reportRegistry.getReport(today).getExpensesList().getExpenses().size(),2);		//add 2 expenses
		/* When: 
		 * 		- calculate any average statistic
		 */
			double resultIncome = reportRegistry.calculateAvgMonthlySalesAmount();
			double resultExpense = reportRegistry.calculateAvgMonthlyExpensesValue();
			double resultRoi = reportRegistry.calculateAvgMonthlyRoi();
			double resultProfit = reportRegistry.calculateAvgMonthlyProfit();


		/* When: 
		 * 		- the value's income expected is 850!
		 * 		- the expense amount expected is 850!
		 */
		double expectedIncome = 1430.0;
		double expectedExpense = 125.0;
		double expectedRoi = 1044.0;
		double expectedProfit = 1305.0;
		assertEquals(Math.round(resultIncome*100.0)/100.0,expectedIncome,0.0);	
		assertEquals(Math.round(resultExpense*100.0)/100.0,expectedExpense,0.0);
		assertEquals(Math.round(resultRoi*100.0)/100.0,expectedRoi,0.0);
		assertEquals(Math.round(resultProfit*100.0)/100.0,expectedProfit,0.0);
	
	}
	
	
	
	/**
	 * <h2>calculate methods test</h2>
	 * 
	 * - tests for situations when we got only 1 month
	 */
	@Test 
	public void testCalculateMethodsAtCurrentMonth() {
		/* Given: 
		 * 		- reportRegistry with 1 report (2050/03)
		 * 		- report of today month : has 150 sales (loop) and 2 expenses
		 * 
		 */
		YearMonth today = YearMonth.now();
		LocalDateTime dateSale = LocalDateTime.now();
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(today),true);						//add report of today's YearMonth
		
		int monthLen =today.getMonth().length(Year.isLeap(today.getYear()));	// save month length of today
			
		assertEquals(reportRegistry.getReport(today).getSalesList().getSales().size(),0);		//verify that saleList is empty
		
		for (int i=dateSale.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report of this month
		{
			reportRegistry.addSale(dateSale, p1,cash);
			reportRegistry.addSale(dateSale.plusMinutes(1), c1, p2,card);
			reportRegistry.addSale(dateSale.plusMinutes(3), p1,cash);
			reportRegistry.addSale(dateSale.plusMinutes(4), p2,card);
			reportRegistry.addSale(dateSale.plusMinutes(5), p1,cash);
			
		}		

		reportRegistry.addExpense("Agua",expenseType.FIXED,35,LocalDate.now());		
		reportRegistry.addExpense("Secadores",expenseType.ONEOFF,90,LocalDate.now(),"3 unidades");
		
		/* When: 
		 * 		- calculate any average statistic
		 */
			double resultIncome = reportRegistry.calculateAvgMonthlySalesAmount();
			double resultExpense = reportRegistry.calculateAvgMonthlyExpensesValue();
			double resultRoi = reportRegistry.calculateAvgMonthlyRoi();
			double resultProfit = reportRegistry.calculateAvgMonthlyProfit();


		/* When: 
		 * 		- the value's of calculate has to be 0! Calculates average methods only works in past months
		 */
		double expected = 0.0;

		assertEquals(Math.round(resultIncome*100.0)/100.0,expected,0.0);	
		assertEquals(Math.round(resultExpense*100.0)/100.0,expected,0.0);
		assertEquals(Math.round(resultRoi*100.0)/100.0,expected,0.0);
		assertEquals(Math.round(resultProfit*100.0)/100.0,expected,0.0);
	
	}
	
	
	
	/**
	 * <h2>calculate methods test</h2>
	 * 
	 * - tests for situations when we got only 1 month
	 */
	@Test 
	public void testCalculateMethodsEmptyReportLists() {
		/* Given: 
		 * 		- reportRegistry empty of reports
		 * 		- report 2017/12 : has 45 sales (loop) and 3
		 * 		- report 2018/12 : has 105 sales (loop)
		 */
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
					
		
		/* When: 
		 * 		- calculate any average statistic
		 */
			double resultIncome = reportRegistry.calculateAvgMonthlySalesAmount();
			double resultExpense = reportRegistry.calculateAvgMonthlyExpensesValue();
			double resultRoi = reportRegistry.calculateAvgMonthlyRoi();
			double resultProfit = reportRegistry.calculateAvgMonthlyProfit();


		/* When: 
		 * 		- the value's income expected is 850!
		 * 		- the expense amount expected is 850!
		 */
		double expected = 0.0;
		assertEquals(Math.round(resultIncome*100.0)/100.0,expected,0.0);	
		assertEquals(Math.round(resultExpense*100.0)/100.0,expected,0.0);
		assertEquals(Math.round(resultRoi*100.0)/100.0,expected,0.0);
		assertEquals(Math.round(resultProfit*100.0)/100.0,expected,0.0);
	
	}
	
	
	/**
	 * <h2>addExpense()  method test</h2>
	 */
	@Test 
	public void testAddReport() {	
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		assertEquals(reportRegistry.getReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(reportRegistry.addReport(ym17),true);						//add report 2017/12
		assertEquals(reportRegistry.addReport(ym18),true);						//add report 2018/12
		assertEquals(reportRegistry.getReports().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(reportRegistry.getReports().size(),2);							//check there is only 2 report on list
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().isEmpty(),true);		//check if expenseList are empty
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().isEmpty(),true);	
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
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().size(),1);		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().size(),2);
		assertEquals(reportRegistry.getReports().size(),2);
		reportRegistry.addExpense("Agua",expenseType.FIXED,35,d1);
		assertEquals(reportRegistry.getReports().size(),3);
		reportRegistry.addExpense("Tesouras",expenseType.ONEOFF,70,d2,"5 unidades");
		
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(reportRegistry.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(reportRegistry.getReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(reportRegistry.getReports().size(),3);					//check that number of reports have increased to 3
		
		reportRegistry.addExpense("MILHA",expenseType.FIXED,70,d4);
		assertEquals(reportRegistry.getReports().size(),4);	
		assertEquals(reportRegistry.getReport(ym17).getExpensesList().getExpenses().size(),2);		
		assertEquals(reportRegistry.getReport(ym18).getExpensesList().getExpenses().size(),3);
		
	}
	
	
	

}

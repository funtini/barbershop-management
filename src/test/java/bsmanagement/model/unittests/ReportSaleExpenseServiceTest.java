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

import bsmanagement.jparepositories.classtests.BookingRepositoryClass;
import bsmanagement.jparepositories.classtests.CustomerRepositoryClass;
import bsmanagement.jparepositories.classtests.ExpenseRepositoryClass;
import bsmanagement.jparepositories.classtests.ProductRepositoryClass;
import bsmanagement.jparepositories.classtests.ReportRepositoryClass;
import bsmanagement.jparepositories.classtests.SaleRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Customer;
import bsmanagement.model.Expense;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.ProductService;
import bsmanagement.model.Report;
import bsmanagement.model.ReportSaleExpenseService;
import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.ExpenseService;
import bsmanagement.model.Product.productType;


/**
 * 
 * Unit tests for ReportSaleExpense Service Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ReportSaleExpenseServiceTest {
	
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
	Expense e4;
	PaymentMethod cash;
	PaymentMethod card;
	Sale s1;
	Sale s2;
	Sale s3;
	Sale s4;
	User u1;
	User u2;
	
	Report r1;
	Report r2;
	
	UserService userService;
	ProductService prodService;
	SaleService saleService;
	ExpenseService expenseService;
	BookingCustomerService bookingCustomerService;
	SaleRepositoryClass saleRepository;
	ExpenseRepositoryClass expenseRepository;
	ReportRepositoryClass reportRepository;
	UserRepositoryClass userRepository;
	ProductRepositoryClass productRepository;
	BookingRepositoryClass bookRepository;
	CustomerRepositoryClass customerRepository;
	ReportSaleExpenseService repSaleExpService;
	List<Report> result;
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
		

		
		userService = new UserService();
		prodService = new ProductService();
		expenseService = new ExpenseService();
		bookingCustomerService = new BookingCustomerService();
		repSaleExpService = new ReportSaleExpenseService();
		saleService = new SaleService();
		
		expenseRepository = new ExpenseRepositoryClass();
		saleRepository = new SaleRepositoryClass();
		reportRepository = new ReportRepositoryClass();
		userRepository = new UserRepositoryClass();
		productRepository = new ProductRepositoryClass();
		bookRepository = new BookingRepositoryClass();
		customerRepository = new CustomerRepositoryClass();
		
		userService.setUserRepository(userRepository);
		repSaleExpService.setExpRepo(expenseRepository);
		repSaleExpService.setReportRepo(reportRepository);
		repSaleExpService.setSaleRepo(saleRepository);
		expenseService.setRepository(expenseRepository);
		prodService.setRepository(productRepository);
		saleService.setSaleRepository(saleRepository);
		bookingCustomerService.setBookRepository(bookRepository);
		bookingCustomerService.setCustomersRepository(customerRepository);
		expect = new ArrayList<Report>();
		result = new ArrayList<Report>();
		Expense.setStartIdGenerator(1);
		Sale.setStartIdGenerator(1);
		Product.setStartIdGenerator(1);
		Customer.setStartIdGenerator(1);
		
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
		u1 = new User("JOAO",birthdate1,"joao@domain.com","914047935","324666433");
		u2 = new User("PEDRO",birthdate2,"pedro@hotmail.com","914047935","324666433");
		userService.addUser(u1);
		userService.addUser(u2);
		r1 = new Report(ym17);
		r2 = new Report(ym18);
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");

		e1 = expenseService.createExpense("Agua",expenseType.FIXED,35,d1);
		e2 = expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
		e3 = expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades");
		
		cash = new PaymentMethod("CASH",0.0,0.0);
		card = new PaymentMethod("CASH",1.5,0.5);
		
		s1 = saleService.createSale(dt1, c1,p1,cash,u1);
		s2 = saleService.createSale(dt2, c2,p2,card,u1);

	}
	
	/**
	 * <h2>getReportList() method test</h2>
	 */
	@Test 
	public void testGetReportList() {
		
		//Given: empty list's
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);	
		//When: set a list with 3 sales		
		assertEquals(repSaleExpService.addReport(ym17),true);	
		assertEquals(repSaleExpService.addReport(ym18),true);	
		//Then: get a list with that 3 sales
		expect.add(new Report(ym17));
		expect.add(new Report(ym18));
		assertEquals(expect.equals(repSaleExpService.getAllReports()),true);
	}

	
	/**
	 * <h2>setRepository() method test</h2>
	 */
	@Test 
	public void testSetRepository() {
		
		//Given: empty service, and a repository with 2 reports
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);
		ReportRepositoryClass reportRepoTest = new ReportRepositoryClass();
		reportRepoTest.save(r1);
		reportRepoTest.save(r2);

		//When: set repository to service
		expect.add(r1);
		expect.add(r2);

		repSaleExpService.setReportRepo(reportRepoTest);
		//Then: service has 2 reports
		result = repSaleExpService.getAllReports();
		assertEquals(result,expect);		
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
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);
		/* When: 
		 * 		- add a report
		 */	
		assertEquals(repSaleExpService.addReport(ym17),true);	
		/* Then: 
		 * 		- Report is added with YearMonth ym17
		 */
		Report expect = new Report(ym17);
		Report result = repSaleExpService.getAllReports().get(0);
		
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
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);
		/* When: 
		 * 		- try add 2x same report
		 */	
		assertEquals(repSaleExpService.addReport(ym17),true);
		assertEquals(repSaleExpService.addReport(ym18),true);
		assertEquals(repSaleExpService.addReport(ym17),false);
		assertEquals(repSaleExpService.addReport(ym17),false);
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		int expect = 2;
		int result = repSaleExpService.getAllReports().size();
		
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
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);
		/* When: 
		 * 		- add a report
		 */	
		assertEquals(repSaleExpService.addReport(ym17),true);
		/* Then: 
		 * 		- I can Get report by YearMonth
		 */
		Report expect = new Report(ym17);
		Report result = repSaleExpService.getReport(ym17);
		
		assertEquals(result,expect);
	}
	
	
	/**
	 * <h2>loadSale()  method test</h2>
	 */
	@Test 
	public void testLoadSale() {
		
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(ym17),true);						//add report 2017/12
		assertEquals(repSaleExpService.addReport(ym18),true);						//add report 2018/12
		assertEquals(repSaleExpService.getAllReports().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(repSaleExpService.getAllReports().size(),2);					//check there is only 2 report on list
		/* When: 
		 * 		- addSale in 12/2017(dt3), 01/2018(dt1 e dt2) and 02/2018 (dt4)
		 */	
		repSaleExpService.loadSale(new Sale(dt1, p1,cash,u1));
		repSaleExpService.loadSale(new Sale(dt2, c1, p2,card,u1));
		repSaleExpService.loadSale(new Sale(dt3, p1,cash,u1));
		repSaleExpService.loadSale(new Sale(dt4, p1,cash,u1));
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(repSaleExpService.getAllReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(repSaleExpService.getAllReports().size(),3);					//check that number of reports have increased to 3
	}
	
	
	/**
	 * <h2>addSale()  method test</h2>
	 */
	@Test 
	public void testAddSale() {
		
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(ym17),true);						//add report 2017/12
		assertEquals(repSaleExpService.addReport(ym18),true);						//add report 2018/12
		assertEquals(repSaleExpService.getAllReports().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(repSaleExpService.getAllReports().size(),2);					//check there is only 2 report on list
		/* When: 
		 * 		- addSale in 12/2017(dt3), 01/2018(dt1 e dt2) and 02/2018 (dt4)
		 */	
		Sale s1 = new Sale(dt1, p1,cash,u1);
		Sale s2 = new Sale(dt2, c1, p2,card,u1);
		Sale s3 = new Sale(dt3, p1,cash,u1);
		Sale s4 = new Sale(dt4, p1,cash,u1);
		
		assertEquals(repSaleExpService.addSale(s1),true);
		assertEquals(repSaleExpService.addSale(s1),false); //repeated sale
		assertEquals(repSaleExpService.addSale(s2),true);
		assertEquals(repSaleExpService.addSale(s3),true);
		assertEquals(repSaleExpService.addSale(s4),true);
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(repSaleExpService.getAllReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(repSaleExpService.getAllReports().size(),3);					//check that number of reports have increased to 3
	}
	
	
	/**
	 * <h2>addSale()  method test</h2>
	 * 
	 * -adding first sale in a month
	 */
	@Test 
	public void testLoadSaleFirstInMonth() {
		
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		Report rep1801 = new Report(YearMonth.of(2018, 1));
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
	
		/* When: 
		 * 		- addSale in 12/2017(dt3), 01/2018(dt1 e dt2) and 02/2018 (dt4)
		 */	
		repSaleExpService.loadSale(new Sale(dt1, c1,p1,cash,u1));
		repSaleExpService.loadSale(new Sale(dt4, p1,cash,u1));
		/* Then: 
		 * 		- Reports are created for YearMonths associated to this sales
		 */
		assertEquals(repSaleExpService.getAllReports().isEmpty(),false);
		assertEquals(repSaleExpService.getAllReports().size(),2);				//check that number of reports have increased to 1
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 1)),rep1801);	
	
	}

	/**
	 * <h2>calculate methods test</h2>
	 * 
	 * - tests for situations when we got only 1 month
	 */
	@Test 
	public void testCalculateMethodsOnlyOneMonth() {
		/* Given: 
		 * 		- report service with 1 report of current month
		 * 		- report of today month : has 100 sales (loop) and 2 expenses
		 * 
		 */
		YearMonth today = YearMonth.now();
		LocalDateTime dateSale = LocalDateTime.now();
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(today),true);						//add report of today's YearMonth
	
			
		assertEquals(repSaleExpService.getReport(today).getSales().size(),0);		//verify that saleList is empty
		
		for (int i=1;i<21;i++)					//Loop to put 100 sales into report of this month
		{
			dateSale.withDayOfMonth(i);
			repSaleExpService.loadSale((new Sale(dateSale, p1,cash,u1)));
			repSaleExpService.loadSale((new Sale(dateSale.plusMinutes(1), c1, p2,card,u1)));
			repSaleExpService.loadSale((new Sale(dateSale.plusMinutes(3), p1,cash,u1)));
			repSaleExpService.loadSale((new Sale(dateSale.plusMinutes(4), p2,card,u1)));
			repSaleExpService.loadSale((new Sale(dateSale.plusMinutes(5), p1,cash,u1)));
			
		}		
		assertEquals(repSaleExpService.getReport(today).getSales().size(),100);		//Check 100 sales in report
																					//add 2 expenses with another date
		Expense e1 = expenseService.createExpense("Agua",expenseType.FIXED,35,LocalDate.of(2018,2, 22));
		Expense e2 = expenseService.createExpense("LUZ",expenseType.FIXED,55,LocalDate.of(2018,2, 25));
		
		repSaleExpService.addExpense(e1);
		repSaleExpService.addExpense(e2);
		
		assertEquals(repSaleExpService.getReport(today).getExpenses().size(),2);	//Check 2 expenses in report
		
		Expense ex = repSaleExpService.getReport(today).findExpensesByName("LUZ").get(0);

		repSaleExpService.removeExpense(ex);

		assertEquals(repSaleExpService.getReport(today).getExpenses().size(),1);
		
		repSaleExpService.addExpense(expenseService.createExpense("Secadores",expenseType.ONEOFF,90,dateSale.toLocalDate(),"3 unidades"));
		repSaleExpService.updateReport(repSaleExpService.getReport(today));
	
		assertEquals(repSaleExpService.getReport(today).getExpenses().size(),2);		//add 2 expenses
		/* When: 
		 * 		- calculate any average statistic
		 */
			double resultIncome = repSaleExpService.calculateAvgMonthlySalesAmount();
			double resultExpense = repSaleExpService.calculateAvgMonthlyExpensesValue();
			double resultRoi = repSaleExpService.calculateAvgMonthlyRoi();
			double resultProfit = repSaleExpService.calculateAvgMonthlyProfit();


		/* When: 
		 * 		- the value's  expected are all 0.0!
		 * 
		 */
		double expectedIncome = 0.0;
		double expectedExpense = 0.0;
		double expectedRoi = 0.0;
		double expectedProfit = 0.0;
		assertEquals(Math.round(resultIncome*100.0)/100.0,expectedIncome,0.0);	
		assertEquals(Math.round(resultExpense*100.0)/100.0,expectedExpense,0.0);
		assertEquals(Math.round(resultRoi*100.0)/100.0,expectedRoi,0.0);
		assertEquals(Math.round(resultProfit*100.0)/100.0,expectedProfit,0.0);
	
	}
	
	
	/**
	 * <h2>calculateAvgMonthlyProfit() method test</h2>
	 * 
	 */
	@Test 
	public void testCalculateAvgMonthlyIncomeAndExpenses() {
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 * 		- report 2017/12 : has 45 sales (loop) and 3
		 * 		- report 2018/12 : has 105 sales (loop)
		 */
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(ym17),true);						//add report 2017/12
		assertEquals(repSaleExpService.addReport(ym18),true);					//add report 2018/12
		
		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
			
		assertEquals(repSaleExpService.getReport(ym17).getSales().size(),0);		//verify that saleList of each report is empty
		assertEquals(repSaleExpService.getReport(ym18).getSales().size(),0);
		
		
		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
		{
			repSaleExpService.loadSale(new Sale(dt1, p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(1), c1, p2,cash,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(3), p1,cash,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(4), p2,card,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(5), p1,cash,u1));
			
		}		
		assertEquals(repSaleExpService.getReport(ym18).getSales().size(),105);
	
		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
		{
			repSaleExpService.loadSale(new Sale(dt3, p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(1), c1, p2,cash,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(3), p2,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(4), p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(5), p1,cash,u1));
		}
		assertEquals(repSaleExpService.getReport(ym17).getSales().size(),45);
		
		e1 = expenseService.createExpense("Agua",expenseType.FIXED,35,d3);		//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
		e2 = expenseService.createExpense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato");
		e3 = expenseService.createExpense("Luz",expenseType.FIXED,25,d3);
		e4 = expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");

		repSaleExpService.addExpense(e1);						
		repSaleExpService.addExpense(e2);
		repSaleExpService.addExpense(e3);
		repSaleExpService.addExpense(e4);
		
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),4);
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().size(),3);
		/* When: 
		 * 		- calculate average INCOME and EXPENSES
		 */
			double resultIncome = repSaleExpService.calculateAvgMonthlySalesAmount();
			double resultExpense = repSaleExpService.calculateAvgMonthlyExpensesValue();
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
	 * <h2>addExpense()  method test</h2>
	 */
	@Test 
	public void testAddExpense() {	
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(ym17),true);						//add report 2017/12
		assertEquals(repSaleExpService.addReport(ym18),true);						//add report 2018/12
		assertEquals(repSaleExpService.getAllReports().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(repSaleExpService.getAllReports().size(),2);							//check there is only 2 report on list
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().isEmpty(),true);		//check if expenseList are empty
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().isEmpty(),true);	
		/* When: 
		 * 		- add FIXED expenses at d1(2018/2/10), d3(2017/12/21) and d4(2017/10/21) and ONEOFF at  d2(2018/2/15) and d5(2018/1/10)
		 * 		
		 * 		- d3 should add to rep of ym17 and ym18
		 * 		- d5 should add to rep of ym18
		 * 		- d1 and d2 should create a new report
		 * 		- d4 should create a new report and add to ym17 and ym18
		 */	
		Expense e = new Expense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
		repSaleExpService.addExpense(e);
		repSaleExpService.addExpense(new Expense("Internet",expenseType.FIXED,50,d3,"6 meses de contrato"));
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().size(),1);		
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),2);
		assertEquals(repSaleExpService.getAllReports().size(),2);
		repSaleExpService.addExpense(new Expense("Agua",expenseType.FIXED,35,d1));
		assertEquals(repSaleExpService.getAllReports().size(),3);	
		repSaleExpService.addExpense(new Expense("Tesouras",expenseType.ONEOFF,70,d2,"5 unidades"));
		
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(repSaleExpService.getAllReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(repSaleExpService.getAllReports().size(),3);					//check that number of reports have increased to 3
		
		repSaleExpService.addExpense(new Expense("MILHA",expenseType.FIXED,70,d4));
		assertEquals(repSaleExpService.getAllReports().size(),4);	
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().size(),2);		
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),3);
		
	}
	
	
	/**
	 * <h2>removeExpense()  method test</h2>
	 */
	@Test 
	public void testRemoveExpense() {	
		/* Given: 
		 * 		- reportService with 1 open report (2018/01) with 2 expenses added
		 */
		
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);	
		assertEquals(repSaleExpService.addReport(ym18),true);						
		assertEquals(repSaleExpService.getAllReports().size(),1);							
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().isEmpty(),true);	
		Expense e1 = expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
		Expense e2 = expenseService.createExpense("Internet",expenseType.FIXED,50,d5,"6 meses de contrato");
		repSaleExpService.addExpense(e1);
		repSaleExpService.addExpense(e2);
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),2);
		assertEquals(repSaleExpService.getReport(ym18).getReportState().isClosed(),false);
		/* When: 
		 * 	
		 * 		- remove expense (e1) from open report
		 */	
		assertEquals(repSaleExpService.removeExpense(e1),true);
		/* Then: 
		 * 	
		 * 		- expense (e1) was sucessfully removed
		 */	
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),1);
		/* but When: 
		 * 	
		 * 		- remove expense (e2) from closed report
		 */	
		assertEquals(repSaleExpService.closeReport(repSaleExpService.getReport(ym18)),true);
		assertEquals(repSaleExpService.getReport(ym18).getReportState().isClosed(),true);
		assertEquals(repSaleExpService.removeExpense(e2),false);
		/* Then: 
		 * 	
		 * 		- expense (e2) was not removed and report still got 1 expense
		 */	
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),1);		
	}
	
	
	/**
	 * <h2>closeReport() method test</h2>
	 */
	@Test 
	public void testCloseReport() {	
		/* Given: 
		 * 		- reportService with 1 open report (2018/01)
		 */
		
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);	
		assertEquals(repSaleExpService.addReport(ym18),true);						
		assertEquals(repSaleExpService.getAllReports().size(),1);							
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().isEmpty(),true);	
		assertEquals(repSaleExpService.getReport(ym18).getReportState().isOpen(),true);
		/* When: 
		 * 		- try to close report
		 */
		assertEquals(repSaleExpService.closeReport(repSaleExpService.getReport(ym18)),false);
		/* Then: 
		 * 		- still open because only waitingApprovement status can be closed
		 */
		assertEquals(repSaleExpService.getReport(ym18).getReportState().isOpen(),true);
		/* but When: 
		 * 		-  try to close report after refresh status to waiting for approvement
		 */
		repSaleExpService.refreshReportStatus();
		assertEquals(repSaleExpService.getReport(ym18).getReportState().isWaitingForApprovement(),true);
		assertEquals(repSaleExpService.closeReport(repSaleExpService.getReport(ym18)),true);
		/* Then: 
		 * 		- report is closed
		 */	
		assertEquals(repSaleExpService.getReport(ym18).getReportState().isClosed(),true);
		expect.add(repSaleExpService.getReport(ym18));
		assertEquals(repSaleExpService.getClosedReports(),expect);
	}
	
	
	/**
	 * <h2>addReport()  method test</h2>
	 */
	@Test 
	public void testAddReport() {	
		/* Given: 
		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
		 */
		Report rep1802 = new Report(YearMonth.of(2018, 2));
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(ym17),true);						//add report 2017/12
		assertEquals(repSaleExpService.addReport(ym18),true);						//add report 2018/12
		assertEquals(repSaleExpService.getAllReports().contains(rep1802),false);		//check that list dont containst rep1802
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
		assertEquals(repSaleExpService.getAllReports().size(),2);							//check there is only 2 report on list
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().isEmpty(),true);		//check if expenseList are empty
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().isEmpty(),true);	
		/* When: 
		 * 		- add FIXED expenses at d1(2018/2/10), d3(2017/12/21) and d4(2017/10/21) and ONEOFF at  d2(2018/2/15) and d5(2018/1/10)
		 * 		
		 * 		- d3 should add to rep of ym17 and ym18
		 * 		- d5 should add to rep of ym18
		 * 		- d1 and d2 should create a new report
		 * 		- d4 should create a new report and add to ym17 and ym18
		 */	
		repSaleExpService.addExpense(new Expense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades"));
		repSaleExpService.addExpense(new Expense("Internet",expenseType.FIXED,50,d3,"6 meses de contrato"));
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().size(),1);		
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),2);
		assertEquals(repSaleExpService.getAllReports().size(),2);
		repSaleExpService.addExpense(new Expense("Agua",expenseType.FIXED,35,d1));
		assertEquals(repSaleExpService.getAllReports().size(),3);
		repSaleExpService.addExpense(new Expense("Tesouras",expenseType.ONEOFF,70,d2,"5 unidades"));
		
		/* Then: 
		 * 		- Reports are added with same YearMonth ym17
		 */
		assertEquals(repSaleExpService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
		assertEquals(repSaleExpService.getAllReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
		assertEquals(repSaleExpService.getAllReports().size(),3);					//check that number of reports have increased to 3
		
		repSaleExpService.addExpense(new Expense("MILHA",expenseType.FIXED,70,d4));
		assertEquals(repSaleExpService.getAllReports().size(),4);	
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().size(),2);		
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),3);
		
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
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(ym17),true);						//add report 2017/12
		assertEquals(repSaleExpService.addReport(ym18),true);					//add report 2018/12
		
		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
			
		assertEquals(repSaleExpService.getReport(ym17).getSales().size(),0);		//verify that saleList of each report is empty
		assertEquals(repSaleExpService.getReport(ym18).getSales().size(),0);
		
		
		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
		{
			repSaleExpService.loadSale(new Sale(dt1, p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(1), c1, p2,cash,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(3), p1,cash,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(4), p2,card,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(5), p1,cash,u1));
			
		}		
		assertEquals(repSaleExpService.getReport(ym18).getSales().size(),105);
	
		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
		{
			repSaleExpService.loadSale(new Sale(dt3, p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(1), c1, p2,cash,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(3), p2,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(4), p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(5), p1,cash,u1));
		}
		assertEquals(repSaleExpService.getReport(ym17).getSales().size(),45);
		
			
		repSaleExpService.addExpense(new Expense("Agua",expenseType.FIXED,35,d3));						//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
		repSaleExpService.addExpense(new Expense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato"));
		repSaleExpService.addExpense(new Expense("Luz",expenseType.FIXED,25,d3));
		repSaleExpService.addExpense(new Expense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades"));
		
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),4);
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().size(),3);
		/* When: 
		 * 		- calculate average ROI 
		 */
			double result = repSaleExpService.calculateAvgMonthlyRoi();
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
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(ym17),true);						//add report 2017/12
		assertEquals(repSaleExpService.addReport(ym18),true);					//add report 2018/12
		
		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
			
		assertEquals(repSaleExpService.getReport(ym17).getSales().size(),0);		//verify that saleList of each report is empty
		assertEquals(repSaleExpService.getReport(ym18).getSales().size(),0);
		
		
		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
		{
			repSaleExpService.loadSale(new Sale(dt1, p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(1), c1, p2,cash,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(3), p1,cash,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(4), p2,card,u1));
			repSaleExpService.loadSale(new Sale(dt1.plusHours(5), p1,cash,u1));
			
		}		
		assertEquals(repSaleExpService.getReport(ym18).getSales().size(),105);
	
		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
		{
			repSaleExpService.loadSale(new Sale(dt3, p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(1), c1, p2,cash,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(3), p2,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(4), p1,card,u1));
			repSaleExpService.loadSale(new Sale(dt3.plusHours(5), p1,cash,u1));
		}
		assertEquals(repSaleExpService.getReport(ym17).getSales().size(),45);
		
			
		repSaleExpService.addExpense(new Expense("Agua",expenseType.FIXED,35,d3));						//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
		repSaleExpService.addExpense(new Expense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato"));
		repSaleExpService.addExpense(new Expense("Luz",expenseType.FIXED,25,d3));
		repSaleExpService.addExpense(new Expense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades"));
		
		assertEquals(repSaleExpService.getReport(ym18).getExpenses().size(),4);
		assertEquals(repSaleExpService.getReport(ym17).getExpenses().size(),3);
		/* When: 
		 * 		- calculate average PROFIT 
		 */
			double result = repSaleExpService.calculateAvgMonthlyProfit();
		/* When: 
		 * 		- the value expected is 850!
		 */
		double expected = 850.0;
		assertEquals(Math.round(result*100.0)/100.0,expected,0.0);	
	
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
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
		assertEquals(repSaleExpService.addReport(ym17),true);						//add report 2017/12
		assertEquals(repSaleExpService.addReport(ym18),true);					//add report 2018/12
		
		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
			
		assertEquals(repSaleExpService.getReport(ym17).getSales().size(),0);		//verify that saleList of each report is empty
		assertEquals(repSaleExpService.getReport(ym18).getSales().size(),0);
		
		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
		{
			Sale s1 = new Sale(dt1, p1,card,u1);
			repSaleExpService.addSale(s1);
			repSaleExpService.addSale(s1);
			repSaleExpService.addSale(new Sale(dt1.plusHours(1), c1, p2,cash,u1));
			repSaleExpService.addSale(new Sale(dt1.plusHours(3), p1,cash,u1));
			repSaleExpService.addSale(new Sale(dt1.plusHours(4), p2,card,u1));
			repSaleExpService.addSale(new Sale(dt1.plusHours(5), p1,cash,u1));
			
		}		
		assertEquals(repSaleExpService.getReport(ym18).getSales().size(),105);
	
		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
		{
			repSaleExpService.addSale(new Sale(dt3, p1,card,u1));
			repSaleExpService.addSale(new Sale(dt3.plusHours(1), c1, p2,cash,u1));
			repSaleExpService.addSale(new Sale(dt3.plusHours(3), p2,card,u1));
			repSaleExpService.addSale(new Sale(dt3.plusHours(4), p1,card,u1));
			repSaleExpService.addSale(new Sale(dt3.plusHours(5), p1,cash,u1));
		}
		assertEquals(repSaleExpService.getReport(ym17).getSales().size(),45);
		
		/* When: 
		 * 		- calculate average ROI without any expense added
		 */
		double result = repSaleExpService.calculateAvgMonthlyRoi();
		/* When: 
		 * 		- the value expected is 0!
		 */
		double expected = 0;
		assertEquals(result,expected,0.0);	
	
	}
	
	
	/**
	 * <h2>calculate method test for empty reports</h2>
	 * 
	 * Test method without any expenses
	 * 
	 */
	@Test 
	public void testCalculateAllValuesWithNoReports() {
		
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			
		assertEquals(repSaleExpService.calculateAvgMonthlyExpensesValue(),0,0.0);	
		assertEquals(repSaleExpService.calculateAvgMonthlyProfit(),0,0.0);	
		assertEquals(repSaleExpService.calculateAvgMonthlyRoi(),0,0.0);	
		assertEquals(repSaleExpService.calculateAvgMonthlySalesAmount(),0,0.0);	
	}
	
	
	/**
	 * <h2>Get Current Report open</h2>
	 * 
	 */
	@Test 
	public void testGetCurrentReport() {
		/* Given: 
		 * 		- add 2 sales with different dates to create 2 reports
		 */
		Report expected = new Report(YearMonth.now());
		s1.setDate(LocalDateTime.now());
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			
		repSaleExpService.addSale(s1);
		repSaleExpService.addSale(s2);
		assertEquals(repSaleExpService.getAllReports().size(),2);
		/* When: 
		 * 		- invoke getCurrentReport()
		 */
		Report result = repSaleExpService.getCurrentOpenReport();
		/* Then: 
		 * 		- expected report is returned
		 */
		assertEquals(expected,result);
	}
	
	/**
	 * <h2>Get Current Report open</h2>
	 * 
	 */
	@Test 
	public void testGetCurrentReportNullCase() {
		/* Given: 
		 * 		- add 2 sales with same old dates to create 1 report, but the current report wasnt created
		 */
		Report expected = null;
		assertEquals(repSaleExpService.getAllReports().isEmpty(),true);			
		repSaleExpService.addSale(s1);
		repSaleExpService.addSale(s2);
		assertEquals(repSaleExpService.getAllReports().size(),1);
		/* When: 
		 * 		- invoke getCurrentReport()
		 */
		Report result = repSaleExpService.getCurrentOpenReport();
		/* Then: 
		 * 		- expected report is returned
		 */
		assertEquals(expected,result);
	}
	

}

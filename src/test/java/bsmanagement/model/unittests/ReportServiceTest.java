//package bsmanagement.model.unittests;
//
//import static org.junit.Assert.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Year;
//import java.time.YearMonth;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import bsmanagement.jparepositories.classtests.BookingRepositoryClass;
//import bsmanagement.jparepositories.classtests.CustomerRepositoryClass;
//import bsmanagement.jparepositories.classtests.ExpenseRepositoryClass;
//import bsmanagement.jparepositories.classtests.ProductRepositoryClass;
//import bsmanagement.jparepositories.classtests.ReportRepositoryClass;
//import bsmanagement.jparepositories.classtests.SaleRepositoryClass;
//import bsmanagement.jparepositories.classtests.UserRepositoryClass;
//import bsmanagement.model.BookingCustomerService;
//import bsmanagement.model.Customer;
//import bsmanagement.model.Expense;
//import bsmanagement.model.ExpenseService;
//import bsmanagement.model.PaymentMethod;
//import bsmanagement.model.Product;
//import bsmanagement.model.Sale;
//import bsmanagement.model.SaleService;
//import bsmanagement.model.User;
//import bsmanagement.model.UserService;
//import bsmanagement.model.Expense.expenseType;
//import bsmanagement.model.Product.productType;
//import bsmanagement.model.ProductService;
//import bsmanagement.model.Report;
//import bsmanagement.model.ReportSaleExpenseService;
//import bsmanagement.model.ReportService;
//
//
//
///**
// * 
// * Unit tests for Report Service Class methods
// * 
// * @author JOAO GOMES
// *
// */
//public class ReportServiceTest {
//	
//	YearMonth ym17;
//	YearMonth ym18;
//	LocalDateTime dt1;
//	LocalDateTime dt2;
//	LocalDateTime dt3;
//	LocalDateTime dt4;
//	LocalDate birthdate1;
//	LocalDate birthdate2;
//	LocalDate d1;
//	LocalDate d2;
//	LocalDate d3;
//	LocalDate d4;
//	LocalDate d5;
//	Product p1;
//	Product p2;
//	Customer c1;
//	Customer c2;
//	Expense e1;
//	Expense e2;
//	Expense e3;
//	Expense e4;
//	PaymentMethod cash;
//	PaymentMethod card;
//	Sale s1;
//	Sale s2;
//	Sale s3;
//	Sale s4;
//	User u1;
//	User u2;
//	
//	Report r1;
//	Report r2;
//
//	SaleService saleService;
//	ExpenseService expenseService;
//	ReportService reportService;
//	UserService userService;
//	ProductService prodService;
//	BookingCustomerService bookingCustomerService;
//	SaleRepositoryClass saleRepository;
//	ExpenseRepositoryClass expenseRepository;
//	ReportRepositoryClass reportRepository;
//	UserRepositoryClass userRepository;
//	ProductRepositoryClass productRepository;
//	BookingRepositoryClass bookRepository;
//	CustomerRepositoryClass customerRepository;
//	ReportSaleExpenseService repSaleExpService;
//	List<Report> result;
//	List<Report> expect;
//	/**
//	 * <h2>Setup for all unit tests: </h2>
//	 * 
//	 * <p>Date [d1] : 05/03/2018 </p>
//	 * <p>Date [d2] : 10/04/2018 </p>
//	 * <p>ExpenseType [et1] : FIXED </p>
//	 * <p>ExpenseType [et2] : ONE-OFF</p>
//	 * 
//	 * <p>Payment [cash] -> ['CASH',0,0] </p>
//	 * <p>Payment [card] -> ['CREDIT CARD',0,0] </p>
//	 * 
//	 * <p>Expense [e1] : ["Agua",'FIXED','35'] </p>
//	 * <p>Expense [e2] : ["Internet",'FIXED','50',d2,"6 meses de contrato"] </p>
//	 * <p>Expense [e3] : ["Secadores",'ONE-OFF','80',d2,"3 unidades"] </p>
//	 * <p>Expense [e4] : ["Shampoos",'ONE-OFF','50',d2,"3 unidades"] </p>
//	 * 
//	 * 
//	 * 
//	 */
//	@Before
//	public void setUp(){
//		
//		expenseService = new ExpenseService();
//		saleService = new SaleService();
//		reportService = new ReportService();
//		userService = new UserService();
//		prodService = new ProductService();
//		bookingCustomerService = new BookingCustomerService();
//		repSaleExpService = new ReportSaleExpenseService();
//		expenseRepository = new ExpenseRepositoryClass();
//		saleRepository = new SaleRepositoryClass();
//		reportRepository = new ReportRepositoryClass();
//		userRepository = new UserRepositoryClass();
//		productRepository = new ProductRepositoryClass();
//		bookRepository = new BookingRepositoryClass();
//		customerRepository = new CustomerRepositoryClass();
//		
//		userService.setUserRepository(userRepository);
//		saleService.setRepository(saleRepository);
//		expenseService.setRepository(expenseRepository);
//		reportService.setRepository(reportRepository);
//		repSaleExpService.setExpRepo(expenseRepository);
//		repSaleExpService.setReportRepo(reportRepository);
//		repSaleExpService.setSaleRepo(saleRepository);
//		prodService.setRepository(productRepository);
//		bookingCustomerService.setBookRepository(bookRepository);
//		bookingCustomerService.setCustomersRepository(customerRepository);
//		expect = new ArrayList<Report>();
//		result = new ArrayList<Report>();
//		Expense.setStartIdGenerator(1);
//		Sale.setStartIdGenerator(1);
//		Product.setStartIdGenerator(1);
//		Customer.setStartIdGenerator(1);
//		
//		ym17 = YearMonth.of(2017, 12);
//		ym18= YearMonth.of(2018, 1);
//		dt1 = LocalDateTime.of(2018,1,10,10,30);
//		dt2 = LocalDateTime.of(2018,1,12,11,30);
//		dt3 = LocalDateTime.of(2017,12,22,11,30);
//		dt4 = LocalDateTime.of(2018,2,5,11,30);
//		birthdate1 = LocalDate.of(1989, 11, 30);
//		birthdate2 = LocalDate.of(1984, 02, 15);
//		d1 = LocalDate.of(2018,2,10);
//		d2 = LocalDate.of(2018,2,15);
//		d3 = LocalDate.of(2017,12,21);
//		d4 = LocalDate.of(2017,10,21);
//		d5 = LocalDate.of(2018, 1, 10);
//		u1 = new User("JOAO",birthdate1,"joao@domain.com","914047935","324666433");
//		u2 = new User("PEDRO",birthdate2,"pedro@hotmail.com","914047935","324666433");
//		userService.addUser(u1);
//		userService.addUser(u2);
//		r1 = new Report(ym17);
//		r2 = new Report(ym18);
//		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
//		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
////		prodService.addProduct(p1);
////		prodService.addProduct(p2);
//		
//		
//		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
//		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
////		bookingCustomerService.addCustomer(c1);
////		bookingCustomerService.addCustomer(c2);
//		
//		e1 = expenseService.createExpense("Agua",expenseType.FIXED,35,d1);
//		e2 = expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
//		e3 = expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades");
//		
//		cash = new PaymentMethod("CASH",0.0,0.0);
//		card = new PaymentMethod("CASH",1.5,0.5);
//		
//		s1 = saleService.createSale(dt1, c1,p1,cash,u1);
//		s2 = saleService.createSale(dt2, c2,p2,card,u1);
//		
////		expenseService.addExpense(e1);
////		expenseService.addExpense(e2);
////		expenseService.addExpense(e3);
//	}
//
//	/**
//	 * <h2>getReportList() method test</h2>
//	 */
//	@Test 
//	public void testGetReportList() {
//		
//		//Given: empty list's
//		assertEquals(reportService.getAllReports().isEmpty(),true);	
//		//When: set a list with 3 sales		
//		assertEquals(reportService.addReport(ym17),true);	
//		assertEquals(reportService.addReport(ym18),true);	
//		//Then: get a list with that 3 sales
//		expect.add(new Report(ym17));
//		expect.add(new Report(ym18));
//		assertEquals(expect.equals(reportService.getAllReports()),true);
//	}
//
//	
//	/**
//	 * <h2>setRepository() method test</h2>
//	 */
//	@Test 
//	public void testSetRepository() {
//		
//		//Given: empty service, and a repository with 2 reports
//		assertEquals(reportService.getAllReports().isEmpty(),true);
//		ReportRepositoryClass reportRepoTest = new ReportRepositoryClass();
//		reportRepoTest.save(r1);
//		reportRepoTest.save(r2);
//
//		//When: set repository to service
//		expect.add(r1);
//		expect.add(r2);
//
//		reportService.setRepository(reportRepoTest);
//		//Then: service has 2 reports
//		result = reportService.getAllReports();
//		assertEquals(result,expect);		
//	}
//	
//	/**
//	 * <h2>addReport()  method test</h2>
//	 * 
//	 * <p>True result returned</p>
//	 */
//	@Test 
//	public void testAddReportTrue() {
//		/* Given: 
//		 * 		- empty reportRegistry
//		 */
//		assertEquals(reportService.getAllReports().isEmpty(),true);
//		/* When: 
//		 * 		- add a report
//		 */	
//		assertEquals(reportService.addReport(ym17),true);	
//		/* Then: 
//		 * 		- Report is added with YearMonth ym17
//		 */
//		Report expect = new Report(ym17);
//		Report result = reportService.getAllReports().get(0);
//		
//		assertEquals(result,expect);
//	}
//	
//	/**
//	 * <h2>addReport()  method test</h2>
//	 * 
//	 * <p>False result returned</p>
//	 */
//	@Test 
//	public void testAddReportFalse() {
//		/* Given: 
//		 * 		- empty reportRegistry
//		 */
//		assertEquals(reportService.getAllReports().isEmpty(),true);
//		/* When: 
//		 * 		- try add 2x same report
//		 */	
//		assertEquals(reportService.addReport(ym17),true);
//		assertEquals(reportService.addReport(ym18),true);
//		assertEquals(reportService.addReport(ym17),false);
//		assertEquals(reportService.addReport(ym17),false);
//		/* Then: 
//		 * 		- Reports are added with same YearMonth ym17
//		 */
//		int expect = 2;
//		int result = reportService.getAllReports().size();
//		
//		assertEquals(result,expect);
//	}
//	
//	
//	/**
//	 * <h2>getReport()  method test</h2>
//	 * 
//	 * <p>get Report by YearMonth</p>
//	 */
//	@Test 
//	public void testgetReportByYearMonth() {
//		/* Given: 
//		 * 		- empty reportRegistry
//		 */
//		assertEquals(reportService.getAllReports().isEmpty(),true);
//		/* When: 
//		 * 		- add a report
//		 */	
//		assertEquals(reportService.addReport(ym17),true);
//		/* Then: 
//		 * 		- I can Get report by YearMonth
//		 */
//		Report expect = new Report(ym17);
//		Report result = reportService.getReport(ym17);
//		
//		assertEquals(result,expect);
//	}
//	
//	
//	/**
//	 * <h2>addSale()  method test</h2>
//	 */
//	@Test 
//	public void testLoadSale() {
//		
//		/* Given: 
//		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
//		 */
//		Report rep1802 = new Report(YearMonth.of(2018, 2));
//		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
//		assertEquals(reportService.addReport(ym17),true);						//add report 2017/12
//		assertEquals(reportService.addReport(ym18),true);						//add report 2018/12
//		assertEquals(reportService.getAllReports().contains(rep1802),false);		//check that list dont containst rep1802
//		assertEquals(reportService.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
//		assertEquals(reportService.getAllReports().size(),2);					//check there is only 2 report on list
//		/* When: 
//		 * 		- addSale in 12/2017(dt3), 01/2018(dt1 e dt2) and 02/2018 (dt4)
//		 */	
//		reportService.loadSale(new Sale(dt1, p1,cash,u1));
//		reportService.loadSale(new Sale(dt2, c1, p2,card,u1));
//		reportService.loadSale(new Sale(dt3, p1,cash,u1));
//		reportService.loadSale(new Sale(dt4, p1,cash,u1));
//		/* Then: 
//		 * 		- Reports are added with same YearMonth ym17
//		 */
//		assertEquals(reportService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
//		assertEquals(reportService.getAllReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
//		assertEquals(reportService.getAllReports().size(),3);					//check that number of reports have increased to 3
//	}
//	
//	
//	/**
//	 * <h2>addSale()  method test</h2>
//	 * 
//	 * -adding first sale in a month
//	 */
//	@Test 
//	public void testLoadSaleFirstInMonth() {
//		
//		/* Given: 
//		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
//		 */
//		Report rep1802 = new Report(YearMonth.of(2018, 2));
//		Report rep1801 = new Report(YearMonth.of(2018, 1));
//		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
//	
//		/* When: 
//		 * 		- addSale in 12/2017(dt3), 01/2018(dt1 e dt2) and 02/2018 (dt4)
//		 */	
//		reportService.loadSale(new Sale(dt1, c1,p1,cash,u1));
//		reportService.loadSale(new Sale(dt4, p1,cash,u1));
//		/* Then: 
//		 * 		- Reports are created for YearMonths associated to this sales
//		 */
//		assertEquals(reportService.getAllReports().isEmpty(),false);
//		assertEquals(reportService.getAllReports().size(),2);				//check that number of reports have increased to 1
//		assertEquals(reportService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
//		assertEquals(reportService.getReport(YearMonth.of(2018, 1)),rep1801);	
//	
//	}
//
////	/**
////	 * <h2>addExpense()  method test</h2>
////	 */
////	@Test 
////	public void testAddExpense() {	
////		/* Given: 
////		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
////		 */
////		Report rep1802 = new Report(YearMonth.of(2018, 2));
////		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
////		assertEquals(reportService.addReport(ym17),true);						//add report 2017/12
////		assertEquals(reportService.addReport(ym18),true);						//add report 2018/12
////		assertEquals(reportService.getAllReports().contains(rep1802),false);		//check that list dont containst rep1802
////		assertEquals(reportService.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
////		assertEquals(reportService.getAllReports().size(),2);							//check there is only 2 report on list
////		assertEquals(reportService.getReport(ym17).getExpenses().isEmpty(),true);		//check if expenseList are empty
////		assertEquals(reportService.getReport(ym18).getExpenses().isEmpty(),true);	
////		/* When: 
////		 * 		- add FIXED expenses at d1(2018/2/10), d3(2017/12/21) and d4(2017/10/21) and ONEOFF at  d2(2018/2/15) and d5(2018/1/10)
////		 * 		
////		 * 		- d3 should add to rep of ym17 and ym18
////		 * 		- d5 should add to rep of ym18
////		 * 		- d1 and d2 should create a new report
////		 * 		- d4 should create a new report and add to ym17 and ym18
////		 */	
////		reportService.addExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
////		reportService.addExpense("Internet",expenseType.FIXED,50,d3,"6 meses de contrato");
////		assertEquals(reportService.getReport(ym17).getExpenses().size(),1);		
////		assertEquals(reportService.getReport(ym18).getExpenses().size(),2);
////		assertEquals(reportService.getAllReports().size(),2);
////		reportService.addExpense("Agua",expenseType.FIXED,35,d1);
////		assertEquals(reportService.getAllReports().size(),3);	
////		reportService.addExpense("Tesouras",expenseType.ONEOFF,70,d2,"5 unidades");
////		
////		/* Then: 
////		 * 		- Reports are added with same YearMonth ym17
////		 */
////		assertEquals(reportService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
////		assertEquals(reportService.getAllReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
////		assertEquals(reportService.getAllReports().size(),3);					//check that number of reports have increased to 3
////		
////		reportService.addExpense("MILHA",expenseType.FIXED,70,d4);
////		assertEquals(reportService.getAllReports().size(),4);	
////		assertEquals(reportService.getReport(ym17).getExpenses().size(),2);		
////		assertEquals(reportService.getReport(ym18).getExpenses().size(),3);
////		
////	}
//	
//	/**
//	 * <h2>calculateAvgRoi()  method test</h2>
//	 * 
//	 * Test method without any expenses
//	 * 
//	 */
//	@Test 
//	public void testCalculateAvgRoiWithoutExpenses() {
//		/* Given: 
//		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
//		 * 		- report 2017/12 : has 45 sales (loop)
//		 * 		- report 2018/12 : has 105 sales (loop)
//		 */
//		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
//		assertEquals(reportService.addReport(ym17),true);						//add report 2017/12
//		assertEquals(reportService.addReport(ym18),true);					//add report 2018/12
//		
//		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
//		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
//			
//		assertEquals(reportService.getReport(ym17).getSales().size(),0);		//verify that saleList of each report is empty
//		assertEquals(reportService.getReport(ym18).getSales().size(),0);
//		
//		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
//		{
//			reportService.loadSale(new Sale(dt1, p1,card,u1));
//			reportService.loadSale(new Sale(dt1.plusHours(1), c1, p2,cash,u1));
//			reportService.loadSale(new Sale(dt1.plusHours(3), p1,cash,u1));
//			reportService.loadSale(new Sale(dt1.plusHours(4), p2,card,u1));
//			reportService.loadSale(new Sale(dt1.plusHours(5), p1,cash,u1));
//			
//		}		
//		assertEquals(reportService.getReport(ym18).getSales().size(),105);
//	
//		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
//		{
//			reportService.loadSale(new Sale(dt3, p1,card,u1));
//			reportService.loadSale(new Sale(dt3.plusHours(1), c1, p2,cash,u1));
//			reportService.loadSale(new Sale(dt3.plusHours(3), p2,card,u1));
//			reportService.loadSale(new Sale(dt3.plusHours(4), p1,card,u1));
//			reportService.loadSale(new Sale(dt3.plusHours(5), p1,cash,u1));
//		}
//		assertEquals(reportService.getReport(ym17).getSales().size(),45);
//		
//		/* When: 
//		 * 		- calculate average ROI without any expense added
//		 */
//		double result = reportService.calculateAvgMonthlyRoi();
//		/* When: 
//		 * 		- the value expected is 0!
//		 */
//		double expected = 0;
//		assertEquals(result,expected,0.0);	
//	
//	}
//	
//	
////	/**
////	 * <h2>calculateAvgRoi()  method test</h2>
////	 * 
////	 * Test method without any expenses
////	 * 
////	 */
////	@Test 
////	public void testCalculateAvgRoiTwoMonths() {
////		/* Given: 
////		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
////		 * 		- report 2017/12 : has 45 sales (loop) and 3
////		 * 		- report 2018/12 : has 105 sales (loop)
////		 */
////		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
////		assertEquals(reportService.addReport(ym17),true);						//add report 2017/12
////		assertEquals(reportService.addReport(ym18),true);					//add report 2018/12
////		
////		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
////		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
////			
////		assertEquals(reportService.getReport(ym17).getSales().size(),0);		//verify that saleList of each report is empty
////		assertEquals(reportService.getReport(ym18).getSales().size(),0);
////		
////		
////		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
////		{
////			reportService.loadSale(new Sale(dt1, p1,card,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(1), c1, p2,cash,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(3), p1,cash,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(4), p2,card,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(5), p1,cash,u1));
////			
////		}		
////		assertEquals(reportService.getReport(ym18).getSales().size(),105);
////	
////		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
////		{
////			reportService.loadSale(new Sale(dt3, p1,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(1), c1, p2,cash,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(3), p2,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(4), p1,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(5), p1,cash,u1));
////		}
////		assertEquals(reportService.getReport(ym17).getSales().size(),45);
////		
////			
////		reportService.addExpense("Agua",expenseType.FIXED,35,d3);						//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
////		reportService.addExpense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato");
////		reportService.addExpense("Luz",expenseType.FIXED,25,d3);
////		reportService.addExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
////		
////		assertEquals(reportService.getReport(ym18).getExpenses().size(),4);
////		assertEquals(reportService.getReport(ym17).getExpenses().size(),3);
////		/* When: 
////		 * 		- calculate average ROI 
////		 */
////			double result = reportService.calculateAvgMonthlyRoi();
////		/* When: 
////		 * 		- the value expected is 667.1!
////		 */
////		double expected = 667.1;
////		assertEquals(Math.round(result*100.0)/100.0,expected,0.0);	
////	
////	}
//	
//	
////	/**
////	 * <h2>calculateAvgMonthlyProfit() method test</h2>
////	 * 
////	 * 
////	 */
////	@Test 
////	public void testCalculateAvgMonthlyProfitnExpenses() {
////		/* Given: 
////		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
////		 * 		- report 2017/12 : has 45 sales (loop) and 3
////		 * 		- report 2018/12 : has 105 sales (loop)
////		 */
////		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
////		assertEquals(reportService.addReport(ym17),true);						//add report 2017/12
////		assertEquals(reportService.addReport(ym18),true);					//add report 2018/12
////		
////		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
////		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
////			
////		assertEquals(reportService.getReport(ym17).getSales().size(),0);		//verify that saleList of each report is empty
////		assertEquals(reportService.getReport(ym18).getSales().size(),0);
////		
////		
////		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
////		{
////			reportService.loadSale(new Sale(dt1, p1,card,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(1), c1, p2,cash,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(3), p1,cash,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(4), p2,card,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(5), p1,cash,u1));
////			
////		}		
////		assertEquals(reportService.getReport(ym18).getSales().size(),105);
////	
////		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
////		{
////			reportService.loadSale(new Sale(dt3, p1,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(1), c1, p2,cash,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(3), p2,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(4), p1,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(5), p1,cash,u1));
////		}
////		assertEquals(reportService.getReport(ym17).getSales().size(),45);
////		
////			
////		reportService.addExpense("Agua",expenseType.FIXED,35,d3);						//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
////		reportService.addExpense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato");
////		reportService.addExpense("Luz",expenseType.FIXED,25,d3);
////		reportService.addExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
////		
////		assertEquals(reportService.getReport(ym18).getExpenses().size(),4);
////		assertEquals(reportService.getReport(ym17).getExpenses().size(),3);
////		/* When: 
////		 * 		- calculate average PROFIT 
////		 */
////			double result = reportService.calculateAvgMonthlyProfit();
////		/* When: 
////		 * 		- the value expected is 850!
////		 */
////		double expected = 850.0;
////		assertEquals(Math.round(result*100.0)/100.0,expected,0.0);	
////	
////	}
//	
//	
////	/**
////	 * <h2>calculateAvgMonthlyProfit() method test</h2>
////	 * 
////	 * 
////	 */
////	@Test 
////	public void testCalculateAvgMonthlyIncomeAndExpenses() {
////		/* Given: 
////		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
////		 * 		- report 2017/12 : has 45 sales (loop) and 3
////		 * 		- report 2018/12 : has 105 sales (loop)
////		 */
////		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
////		assertEquals(reportService.addReport(ym17),true);						//add report 2017/12
////		assertEquals(reportService.addReport(ym18),true);					//add report 2018/12
////		
////		int monthLen =dt1.getMonth().length(Year.isLeap(dt1.getYear()));	// save month length of each date (dt1 and dt3)
////		int monthLen2 =dt3.getMonth().length(Year.isLeap(dt3.getYear()));
////			
////		assertEquals(reportService.getReport(ym17).getSales().size(),0);		//verify that saleList of each report is empty
////		assertEquals(reportService.getReport(ym18).getSales().size(),0);
////		
////		
////		for (int i=dt1.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report 'ym18'
////		{
////			reportService.loadSale(new Sale(dt1, p1,card,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(1), c1, p2,cash,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(3), p1,cash,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(4), p2,card,u1));
////			reportService.loadSale(new Sale(dt1.plusHours(5), p1,cash,u1));
////			
////		}		
////		assertEquals(reportService.getReport(ym18).getSales().size(),105);
////	
////		for (int i=dt3.getDayOfMonth();i<monthLen2;i++)					//Loop to put 45 sales into report 'ym17'
////		{
////			reportService.loadSale(new Sale(dt3, p1,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(1), c1, p2,cash,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(3), p2,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(4), p1,card,u1));
////			reportService.loadSale(new Sale(dt3.plusHours(5), p1,cash,u1));
////		}
////		assertEquals(reportService.getReport(ym17).getSales().size(),45);
////		
////		
////		e1 = expenseService.createExpense("Agua",expenseType.FIXED,35,d3);		//add 3 expenses fixed on 12/2017 and 1 oneoff at 01/2018
////		e2 = expenseService.createExpense("Internet",expenseType.FIXED,20,d3,"6 meses de contrato");
////		e3 = expenseService.createExpense("Luz",expenseType.FIXED,25,d3);
////		e4 = expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
////		expenseService.addExpense(e1);
////		expenseService.addExpense(e2);
////		expenseService.addExpense(e3);
////		expenseService.addExpense(e4);
////		reportService.addExpense(e1);						
////		reportService.addExpense(e2);
////		reportService.addExpense(e3);
////		reportService.addExpense(e4);
////		
////		assertEquals(reportService.getReport(ym18).getExpenses().size(),4);
////		assertEquals(reportService.getReport(ym17).getExpenses().size(),3);
////		/* When: 
////		 * 		- calculate average INCOME and EXPENSES
////		 */
////			double resultIncome = reportService.calculateAvgMonthlySalesAmount();
////			double resultExpense = reportService.calculateAvgMonthlyExpensesValue();
////		/* When: 
////		 * 		- the value's income expected is 850!
////		 * 		- the expense amount expected is 850!
////		 */
////		double expectedIncome = 975.0;
////		double expectedExpenses = 125.0;
////		assertEquals(Math.round(resultIncome*100.0)/100.0,expectedIncome,0.0);	
////		assertEquals(Math.round(resultExpense*100.0)/100.0,expectedExpenses,0.0);	
////	
////	}
//	
////	/**
////	 * <h2>calculate methods test</h2>
////	 * 
////	 * - tests for situations when we got only 1 month
////	 */
////	@Test 
////	public void testCalculateMethodsOnlyOneMonth() {
////		/* Given: 
////		 * 		- reportRegistry with 1 report (2050/03)
////		 * 		- report of today month : has 150 sales (loop) and 2 expenses
////		 * 
////		 */
////		YearMonth today = YearMonth.of(2050, 3);
////		LocalDateTime dateSale = LocalDateTime.of(2050, 3, 9, 10, 50);
////		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
////		assertEquals(reportService.addReport(today),true);						//add report of today's YearMonth
////		
////		int monthLen =today.getMonth().length(Year.isLeap(today.getYear()));	// save month length of today
////			
////		assertEquals(reportService.getReport(today).getSales().size(),0);		//verify that saleList is empty
////		
////		for (int i=dateSale.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report of this month
////		{
////			reportService.loadSale((new Sale(dateSale, p1,cash,u1)));
////			reportService.loadSale((new Sale(dateSale.plusMinutes(1), c1, p2,card,u1)));
////			reportService.loadSale((new Sale(dateSale.plusMinutes(3), p1,cash,u1)));
////			reportService.loadSale((new Sale(dateSale.plusMinutes(4), p2,card,u1)));
////			reportService.loadSale((new Sale(dateSale.plusMinutes(5), p1,cash,u1)));
////			
////		}		
////		assertEquals(reportService.getReport(today).getSales().size(),110);
////		
////		System.out.println(expenseService.getExpenses());
////		
////		Expense e1 = expenseService.createExpense("Agua",expenseType.FIXED,35,LocalDate.now());
////		Expense e2 = expenseService.createExpense("LUZ",expenseType.FIXED,55,LocalDate.now());
////		expenseService.addExpense(e1);
////		expenseService.addExpense(e2);
////		
////		System.out.println(expenseService.getExpenses());
////		
////		reportService.addExpense(e1);		
////		reportService.addExpense(e2);		// add expense
////////		Expense e = reportService.getExpenseRegistry().searchExpenseByName("LUZ").get(0);//remove expense
////		System.out.println("::::"+reportService.getReport(today).getExpenses()+"::::::");
////		assertEquals(reportService.getReport(today).getExpenses().size(),2);
////		Expense ex = reportService.getReport(today).findExpensesByName("LUZ").get(0);
////		System.out.println(ex);
////		System.out.println(expenseService.getExpenses());
////		expenseService.removeExpense(ex);
////		System.out.println(expenseService.getExpenses());
////		assertEquals(reportService.getReport(today).getExpenses().size(),2);
//////		expenseService.removeExpense(expenseService.getExpenses().get(4));
////		
////		reportService.addExpense("Secadores",expenseType.ONEOFF,90,dateSale.toLocalDate(),"3 unidades");
////		reportService.updateReport(reportService.getReport(today));
//////		System.out.println(reportService.getReports());
//////		System.out.println(expenseService.getExpenses());
//////		System.out.println(reportService.getReport(today).getExpenses());
////		assertEquals(reportService.getReport(today).getExpenses().size(),2);		//add 2 expenses
////		/* When: 
////		 * 		- calculate any average statistic
////		 */
////			double resultIncome = reportService.calculateAvgMonthlySalesAmount();
////			double resultExpense = reportService.calculateAvgMonthlyExpensesValue();
////			double resultRoi = reportService.calculateAvgMonthlyRoi();
////			double resultProfit = reportService.calculateAvgMonthlyProfit();
////
////
////		/* When: 
////		 * 		- the value's income expected is 850!
////		 * 		- the expense amount expected is 850!
////		 */
////		double expectedIncome = 1430.0;
////		double expectedExpense = 125.0;
////		double expectedRoi = 1044.0;
////		double expectedProfit = 1305.0;
////		assertEquals(Math.round(resultIncome*100.0)/100.0,expectedIncome,0.0);	
////		assertEquals(Math.round(resultExpense*100.0)/100.0,expectedExpense,0.0);
////		assertEquals(Math.round(resultRoi*100.0)/100.0,expectedRoi,0.0);
////		assertEquals(Math.round(resultProfit*100.0)/100.0,expectedProfit,0.0);
////	
////	}
//	
//	
//	
//	/**
//	 * <h2>calculate methods test</h2>
//	 * 
//	 * - tests for situations when we got only 1 month
//	 */
//	@Test 
//	public void testCalculateMethodsAtCurrentMonth() {
//		/* Given: 
//		 * 		- reportRegistry with 1 report (2050/03)
//		 * 		- report of today month : has 150 sales (loop) and 2 expenses
//		 * 
//		 */
//		YearMonth today = YearMonth.now();
//		LocalDateTime dateSale = LocalDateTime.now();
//		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
//		assertEquals(reportService.addReport(today),true);						//add report of today's YearMonth
//		
//		int monthLen =today.getMonth().length(Year.isLeap(today.getYear()));	// save month length of today
//			
//		assertEquals(reportService.getReport(today).getSales().size(),0);		//verify that saleList is empty
//		
//		for (int i=dateSale.getDayOfMonth();i<monthLen;i++)					//Loop to put 105 sales into report of this month
//		{
//			reportService.loadSale(new Sale(dateSale, p1,cash,u1));
//			reportService.loadSale(new Sale(dateSale.plusMinutes(1), c1, p2,card,u1));
//			reportService.loadSale(new Sale(dateSale.plusMinutes(3), p1,cash,u1));
//			reportService.loadSale(new Sale(dateSale.plusMinutes(4), p2,card,u1));
//			reportService.loadSale(new Sale(dateSale.plusMinutes(5), p1,cash,u1));
//			
//		}		
//
//		reportService.addExpense("Agua",expenseType.FIXED,35,LocalDate.now());		
//		reportService.addExpense("Secadores",expenseType.ONEOFF,90,LocalDate.now(),"3 unidades");
//		
//		/* When: 
//		 * 		- calculate any average statistic
//		 */
//			double resultIncome = reportService.calculateAvgMonthlySalesAmount();
//			double resultExpense = reportService.calculateAvgMonthlyExpensesValue();
//			double resultRoi = reportService.calculateAvgMonthlyRoi();
//			double resultProfit = reportService.calculateAvgMonthlyProfit();
//
//
//		/* When: 
//		 * 		- the value's of calculate has to be 0! Calculates average methods only works in past months
//		 */
//		double expected = 0.0;
//
//		assertEquals(Math.round(resultIncome*100.0)/100.0,expected,0.0);	
//		assertEquals(Math.round(resultExpense*100.0)/100.0,expected,0.0);
//		assertEquals(Math.round(resultRoi*100.0)/100.0,expected,0.0);
//		assertEquals(Math.round(resultProfit*100.0)/100.0,expected,0.0);
//	
//	}
//	
//	
//	
//	/**
//	 * <h2>calculate methods test</h2>
//	 * 
//	 * - tests for situations when we got only 1 month
//	 */
//	@Test 
//	public void testCalculateMethodsEmptyReportLists() {
//		/* Given: 
//		 * 		- reportRegistry empty of reports
//		 * 		- report 2017/12 : has 45 sales (loop) and 3
//		 * 		- report 2018/12 : has 105 sales (loop)
//		 */
//		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
//					
//		
//		/* When: 
//		 * 		- calculate any average statistic
//		 */
//			double resultIncome = reportService.calculateAvgMonthlySalesAmount();
//			double resultExpense = reportService.calculateAvgMonthlyExpensesValue();
//			double resultRoi = reportService.calculateAvgMonthlyRoi();
//			double resultProfit = reportService.calculateAvgMonthlyProfit();
//
//
//		/* When: 
//		 * 		- the value's income expected is 850!
//		 * 		- the expense amount expected is 850!
//		 */
//		double expected = 0.0;
//		assertEquals(Math.round(resultIncome*100.0)/100.0,expected,0.0);	
//		assertEquals(Math.round(resultExpense*100.0)/100.0,expected,0.0);
//		assertEquals(Math.round(resultRoi*100.0)/100.0,expected,0.0);
//		assertEquals(Math.round(resultProfit*100.0)/100.0,expected,0.0);
//	
//	}
//	
//	
////	/**
////	 * <h2>addExpense()  method test</h2>
////	 */
////	@Test 
////	public void testAddReport() {	
////		/* Given: 
////		 * 		- reportRegistry with 2 report (2017/12 & 2018/01)
////		 */
////		Report rep1802 = new Report(YearMonth.of(2018, 2));
////		assertEquals(reportService.getAllReports().isEmpty(),true);			//check that report list is empty before adding reports
////		assertEquals(reportService.addReport(ym17),true);						//add report 2017/12
////		assertEquals(reportService.addReport(ym18),true);						//add report 2018/12
////		assertEquals(reportService.getAllReports().contains(rep1802),false);		//check that list dont containst rep1802
////		assertEquals(reportService.getReport(YearMonth.of(2018, 2)),null);			//check that cant find a report of 2018/02
////		assertEquals(reportService.getAllReports().size(),2);							//check there is only 2 report on list
////		assertEquals(reportService.getReport(ym17).getExpenses().isEmpty(),true);		//check if expenseList are empty
////		assertEquals(reportService.getReport(ym18).getExpenses().isEmpty(),true);	
////		/* When: 
////		 * 		- add FIXED expenses at d1(2018/2/10), d3(2017/12/21) and d4(2017/10/21) and ONEOFF at  d2(2018/2/15) and d5(2018/1/10)
////		 * 		
////		 * 		- d3 should add to rep of ym17 and ym18
////		 * 		- d5 should add to rep of ym18
////		 * 		- d1 and d2 should create a new report
////		 * 		- d4 should create a new report and add to ym17 and ym18
////		 */	
////		reportService.addExpense("Secadores",expenseType.ONEOFF,90,d5,"3 unidades");
////		reportService.addExpense("Internet",expenseType.FIXED,50,d3,"6 meses de contrato");
////		assertEquals(reportService.getReport(ym17).getExpenses().size(),1);		
////		assertEquals(reportService.getReport(ym18).getExpenses().size(),2);
////		assertEquals(reportService.getAllReports().size(),2);
////		reportService.addExpense("Agua",expenseType.FIXED,35,d1);
////		assertEquals(reportService.getAllReports().size(),3);
////		reportService.addExpense("Tesouras",expenseType.ONEOFF,70,d2,"5 unidades");
////		
////		/* Then: 
////		 * 		- Reports are added with same YearMonth ym17
////		 */
////		assertEquals(reportService.getReport(YearMonth.of(2018, 2)),rep1802);		//check that can fin a report of 2018/02
////		assertEquals(reportService.getAllReports().contains(rep1802),true);	//check that report list cointains report of 2018/02
////		assertEquals(reportService.getAllReports().size(),3);					//check that number of reports have increased to 3
////		
////		reportService.addExpense("MILHA",expenseType.FIXED,70,d4);
////		assertEquals(reportService.getAllReports().size(),4);	
////		assertEquals(reportService.getReport(ym17).getExpenses().size(),2);		
////		assertEquals(reportService.getReport(ym18).getExpenses().size(),3);
////		
////	}
//	
//	
//	
//
//}

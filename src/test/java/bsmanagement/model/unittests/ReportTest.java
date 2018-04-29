package bsmanagement.model.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import bsmanagement.jparepositories.classtests.ExpenseRepositoryClass;
import bsmanagement.jparepositories.classtests.PaymentRepositoryClass;
import bsmanagement.jparepositories.classtests.SaleRepositoryClass;
import bsmanagement.jparepositories.classtests.UserRepositoryClass;
import bsmanagement.model.Contract;
import bsmanagement.model.Customer;
import bsmanagement.model.Expense;
import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.Product.productType;
import bsmanagement.model.Report;
import bsmanagement.model.ExpenseService;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.Sale;
import bsmanagement.model.SaleService;
import bsmanagement.model.User;
import bsmanagement.model.UserService;



/**
 * 
 * Unit tests for Report Class methods
 * 
 * @author JOAO GOMES
 *
 */
public class ReportTest {

	YearMonth ym1;
	YearMonth ym2;
	LocalDateTime dt1;
	LocalDateTime dt2;
	LocalDateTime dt3;
	LocalDateTime dt4;
	LocalDate birthdate1;
	LocalDate birthdate2;
	LocalDate d1;
	LocalDate d2;
	LocalDate d3;
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
	Sale s5;
	User u1;
	User u2;
	PaymentMethod cash;
	PaymentMethod card;
	Contract contract1;
	Contract contract2;
	
	SaleService saleService;
	ExpenseService expenseService;
	UserService userService;
	SaleRepositoryClass saleRepository;
	PaymentRepositoryClass paymentRepository;
	ExpenseRepositoryClass expenseRepository;
	UserRepositoryClass userRepository;

	
	Report rep17;
	Report rep18;
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>YearMonth [ym18] : 2018/01 </p>
	 * <p>YearMonth [ym17] : 2017/12 </p>
	 * 
	 * <p>Services : [saleService] ; [reportService] ; [userService] ; [expenseService] </p>
	 * 
	 * 
	 * <p>User [u1] : ["JOAO",birth1,"joao@domain.com","914047935","324666433"] </p>
	 * <p>User [u2] : ["PEDRO",birth2,"pedro@dgmail.uk","915557911","123555433"] </p>
	 * 
	 * <p>Customer [c1] -> ['Joao',d1,a1,p1] </p>
	 * <p>Customer [c2] -> ['Ana',d2,a2,p2] </p>
	 * 
	 * <p>Product [p1] -> ['CORTE COM LAVAGEM','HAIRCUT',15] </p>
	 * <p>Product [p2] -> ['CORTE SIMPLES','HAIRCUT',10] </p>
	 * 
	 * <p>Payment [cash] -> ['CASH',0,0] </p>
	 * <p>Payment [card] -> ['CREDIT CARD',0,0] </p>
	 * 
	 * <p>Report [rep1] : [y1,m1,sl1,ep1,bd1] </p>
	 * <p>Report [rep2] : [y2,m2,sl2,ep2,bd2] </p>
	 * 
	 * 
	 */
	@Before
	public void setUp() {
		
		expenseService = new ExpenseService();
		saleService = new SaleService();
		expenseRepository = new ExpenseRepositoryClass();
		saleRepository = new SaleRepositoryClass();
		userRepository = new UserRepositoryClass();
		userService = new UserService();
		paymentRepository = new PaymentRepositoryClass();
		expenseService.setRepository(expenseRepository);
		saleService.setRepository(saleRepository);
		saleService.setPaymentRepository(paymentRepository);
		userService.setUserRepository(userRepository);
		Expense.setStartIdGenerator(0);
		Sale.setStartIdGenerator(0);
		Product.setStartIdGenerator(0);
		Customer.setStartIdGenerator(0);

		ym1 = YearMonth.of(2017, 12);
		ym2 = YearMonth.of(2018, 1);
		dt1 = LocalDateTime.of(2018,1,10,10,30);
		dt2 = LocalDateTime.of(2018,1,12,11,30);
		dt3 = LocalDateTime.of(2018,1,12,17,30);
		dt4 = LocalDateTime.of(2017,12,20,10,30);
		birthdate1 = LocalDate.of(1989, 11, 30);
		birthdate2 = LocalDate.of(1984, 02, 15);
		d1 = LocalDate.of(2018,2,10);
		d2 = LocalDate.of(2018,2,15);
		d3 = LocalDate.of(2017,12,21);
		
		p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		c1 = new Customer("Joao",birthdate1,"Mangualde","914047935");
		c2 = new Customer("Ana",birthdate2,"Porto","966677722");
		u1 = new User("JOAO",birthdate1,"joao@domain.com","914047935","324666433");
		u2 = new User("PEDRO",birthdate2,"pedro@hotmail.com","914047935","324666433");
		userService.addUser(u1);
		userService.addUser(u2);
		contract1 = u1.createContract(400, 20);
		contract2 = u1.createContract(200, 60);
		u1.addContract(contract1);
		u2.addContract(contract2);
		userService.updateUser(u1);
		userService.updateUser(u2);
		
		e1 = expenseService.createExpense("Agua",expenseType.FIXED,35,d1);
		e2 = expenseService.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
		e3 = expenseService.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades");
		
		cash = saleService.createPaymentMethod("CASH",0.0,0.0);
		card = saleService.createPaymentMethod("CARD",1.5,0.5);
		saleService.addPaymentMethod(cash);
		saleService.addPaymentMethod(card);
		s1 = saleService.createSale(dt1, c1,p1,cash,u1);
		s2 = saleService.createSale(dt2, c2,p2,card,u1);
		s3 = saleService.createSale(dt3, c1,p1,card,u2);
		s4 = saleService.createSale(dt4, c1,p1,card,u1);
		s5 = saleService.createSale(dt4, c1,p1,card,u2);
		
		expenseService.addExpense(e1);
		expenseService.addExpense(e2);
		expenseService.addExpense(e3);
		saleService.addSale(s1);
		saleService.addSale(s2);
		saleService.addSale(s3);
		saleService.addSale(s4);
		
		
		rep17 = new Report(ym1);
		rep18 = new Report(ym2);
		
	}
	
	/**
	 * <h2>changeStatus() method test</h2>
	 */
	@Test 
	public void testChangeStatus() {

		/**
		 * GIVEN: Report of December/2017 and a Report of Today with both Status OPEN
		 */
		Report repToday = new Report(YearMonth.now()); 
		assertEquals(rep17.getReportState().isOpen(),true);
		assertEquals(repToday.getReportState().isOpen(),true);
		/**
		 * WHEN: setStatusClosd() 
		 */
		assertEquals(rep17.setStatusClosed(),false);
		assertEquals(repToday.setStatusClosed(),false);
		
		/**
		 * THEN: both still opened
		 */	
		assertEquals(rep17.getReportState().isOpen(),true);
		assertEquals(repToday.getReportState().isOpen(),true);
		/**
		 * and WHEN: changeStatus()
		 */
		rep17.changeStatus();
		repToday.changeStatus();
		/**
		 * THEN: rep17 is changed to waitingConfirmation, and todayRep still open
		 */
		assertEquals(rep17.getReportState().isWaitingForApprovement(),true);
		assertEquals(repToday.getReportState().isOpen(),true);
		/**
		 * and WHEN: setStatusClosed()
		 */
		assertEquals(rep17.setStatusClosed(),true);
		assertEquals(repToday.setStatusClosed(),false);
		/**
		 * THEN: rep17 is changed to closed, and todayRep still open
		 */
		assertEquals(rep17.getReportState().isClosed(),true);
		rep17.setReportState(null);
		assertEquals(rep17.getReportState().isClosed(),true);
		assertEquals(repToday.getReportState().isOpen(),true);
		System.out.println(rep17.getStatus());
	}
	
	
	/**
	 * <h2>updateBusinessDays() method test</h2>
	 * 
	 * <p> This test verify if 2 different reports with different number of day sales are correctly updated
	 */
	@Test 
	public void testUpdateBusinessDays() {
		/* Given: 
		 * 		- 2 reports with no sales and 0 businessDays
		 * 		- Add sales s1,s2,s3 to rep17 and s4 to rep18
		 * 		- and both reports still got 0 businessDays
		 */
		assertEquals(rep18.getBusinessDays(),0);
		assertEquals(rep17.getBusinessDays(),0);
		
		rep18.addSale(s1);
		rep18.addSale(s2);		
		rep18.addSale(s3);
		rep17.addSale(s4);
		
		assertEquals(rep18.getBusinessDays(),0);
		assertEquals(rep17.getBusinessDays(),0);
		/* When: 
		 * 		- updateBussinessDays() is executed
		 */
		assertEquals(rep18.updateBusinessDays(),true);
		assertEquals(rep17.updateBusinessDays(),true);
		assertEquals(rep17.updateBusinessDays(),false); //already updated - false
		/* Then: 
		 * 		- Both reports has your businessDays updated
		 */
		assertEquals(rep18.getBusinessDays(),2);
		assertEquals(rep17.getBusinessDays(),1);
		
	}
	
	/**
	 * <h2>removeSale() method test</h2>
	 * 
	 * <p> This test verify if 2 different reports with different status are able to remove sales
	 */
	@Test 
	public void testRemoveSale() {
		/* Given: 
		 * 		- 1 report closed with 2 sales and 1 report open with 3 sales
		 */
		assertEquals(rep18.getSales().isEmpty(),true);
		assertEquals(rep17.getSales().isEmpty(),true);
		
		rep18.addSale(s1);
		rep18.addSale(s2);		
		rep18.addSale(s3);
		rep17.addSale(s4);
		rep17.addSale(s5);
		
		assertEquals(rep18.getSales().size(),3);
		assertEquals(rep17.getSales().size(),2);
		rep17.changeStatus();
		assertEquals(rep17.setStatusClosed(),true);
		/* When: 
		 * 		- removeSale() is executed in both reports
		 */
		assertEquals(rep18.removeSale(s1),true);
		assertEquals(rep17.removeSale(s3),false);
		/* Then: 
		 * 		- only rep18 (opened) has sale removed, rep17 still got same 2 sales on list
		 */
		assertEquals(rep18.getSales().size(),2);
		assertEquals(rep17.getSales().size(),2);
		
	}
	
	/**
	 * <h2>getUserSales() method test</h2>
	 * 
	 */
	@Test 
	public void testGetUserSales() {
		/* Given: 
		 * 		- 1 open report with 3 sales 
		 */
		List<Sale> expected = new ArrayList<>();
		assertEquals(rep18.getSales().isEmpty(),true);
		
		rep18.addSale(s1);
		rep18.addSale(s2);		
		rep18.addSale(s3);
		expected.add(s1);
		expected.add(s2);
		
		assertEquals(rep18.getSales().size(),3);
		/* When: 
		 * 		- getUserSales() is executed
		 */
		assertEquals(rep18.getUserSales(u1),expected);
		assertEquals(rep18.removeSale(s2),true);
		/* Then: 
		 * 		- expected sales list is given
		 */
		expected.remove(s2);
		assertEquals(rep18.getSales().size(),2);
		assertEquals(rep18.getUserSales(u1),expected);
	}
	
	/**
	 * <h2>getUserSalaries() method test</h2>
	 * 
	 */
	@Test 
	public void testGetUserSalaries() {
		/* Given: 
		 * 		- 1 open report with 3 sales 
		 */
		Map<User,Double> expected = new HashMap<User,Double>();
		Map<User,Double> result = new HashMap<User,Double>();
		assertEquals(rep18.getSales().isEmpty(),true);
		
		rep18.addSale(s1);
		rep18.addSale(s2);		
		rep18.addSale(s3);
		expected.put(u1,405.0); 	//400 + 0.2*15 + 0.2*10 [base salary + 2 comission sales of 20% == 400 + 3 + 2
		expected.put(u2,209.0);		//200 + 0.6*15  [base salary + 1 comission sales of 60% == 200 + 9
		
		assertEquals(rep18.getSales().size(),3);
		/* When: 
		 * 		- getUserSaries() is executed
		 */
		result = rep18.getUsersSalariesInThisMonth();
		/* Then: 
		 * 		- got a map with all users and respective salaries
		 */

		assertEquals(expected,result);
	}
	
	
	/**
	 * <h2>getActiveUsersInThisMonth() method test</h2>
	 * 
	 */
	@Test 
	public void testGetActiveUsersInThisMonth() {
		/* Given: 
		 * 		- 1 open report with 3 sales 
		 */
	
		List<User> expectedUsers = new ArrayList<>();
		List<User> result = new ArrayList<>();
		assertEquals(rep18.getSales().isEmpty(),true);
		
		rep18.addSale(s1);
		rep18.addSale(s2);		
		rep18.addSale(s3);
		
		expectedUsers.add(u1);
		expectedUsers.add(u2);
	
		
		assertEquals(rep18.getActiveUsersInThisMonth().size(),2);
		/* When: 
		 * 		- getActiveUserInThisMonth() is executed
		 */
		result = rep18.getActiveUsersInThisMonth();
		/* Then: 
		 * 		- got all users with activity in that mounth
		 */

		assertEquals(expectedUsers,result);
	}
	
	/**
	 * <h2>getActiveUsersInThisMonth() method test</h2>
	 * 
	 */
	@Test 
	public void testCalculateTotalFeeAmount() {
		/* Given: 
		 * 		- 1 open report with 3 sales 
		 */
	
		assertEquals(rep18.getSales().isEmpty(),true);
		
		rep18.addSale(s1);
		rep18.addSale(s2);		
		rep18.addSale(s3);
		
		assertEquals(rep18.getSales().size(),3);
		/* When: 
		 * 		- getActiveUserInThisMonth() is executed
		 */
		double result = rep18.calculateTotalFeeAmount();
		/* Then: 
		 * 		- got all users with activity in that mounth
		 */

		assertEquals(1,result,0.0);
	}
	
	/**
	 * <h2>getSumAllAmountsPayedBy() method test</h2>
	 * 
	 */
	@Test 
	public void testSumAllAmountsPayedBy() {
		/* Given: 
		 * 		- 1 open report with 3 sales 
		 */
	
		assertEquals(rep18.getSales().isEmpty(),true);
		
		rep18.addSale(s1);
		rep18.addSale(s2);		
		rep18.addSale(s3);
		
		assertEquals(rep18.getSales().size(),3);
		/* When: 
		 * 		- getActiveUserInThisMonth() is executed
		 */
		double result = rep18.sumAllAmountsPayedBy(card);
		/* Then: 
		 * 		- got all users with activity in that mounth
		 */

		assertEquals(25,result,0.0);
	}
	
	
	/**
	 * <h2>getFixedExpenses() method test</h2>
	 * 
	 */
	@Test 
	public void testGetFixedExpenses() {
		/* Given: 
		 * 		- 1 report closed with 2 sales and 1 report open with 3 sales
		 */
		List<Expense> expected = new ArrayList<>();
		List<Expense> result = new ArrayList<>();
		assertEquals(rep17.getExpenses().isEmpty(),true);
		
		assertEquals(rep17.addExpense(e1),true);
		assertEquals(rep17.addExpense(e3),true);
		assertEquals(rep17.addExpense(e2),true);
		expected.add(e1);
		expected.add(e2);
		
		assertEquals(rep17.getExpenses().size(),3);

		/* When: 
		 * 		- getFixedExpenses() is executed
		 */
		result = rep17.getFixedExpenses();
		
		/* Then: 
		 * 		- get expected list of fixed expenses
		 */
		assertEquals(result,expected);
	
		
	}

	
	/**
	 * <h2>removeExpense() method test</h2>
	 * 
	 * <p> This test verify if 2 different reports with different status are able to remove expenses
	 */
	@Test 
	public void testRemoveExpense() {
		/* Given: 
		 * 		- 1 report closed with 2 sales and 1 report open with 3 sales
		 */
		assertEquals(rep18.getExpenses().isEmpty(),true);
		assertEquals(rep17.getExpenses().isEmpty(),true);
		
		assertEquals(rep18.addExpense(e1),true);
		assertEquals(rep17.addExpense(e3),true);
		assertEquals(rep17.addExpense(e2),true);
		

		
		assertEquals(rep18.getExpenses().size(),1);
		assertEquals(rep17.getExpenses().size(),2);
		rep17.changeStatus();
		assertEquals(rep17.setStatusClosed(),true);
		/* When: 
		 * 		- removeExpense() is executed in both reports
		 */
		assertEquals(rep18.removeExpense(e1),true);
		assertEquals(rep17.removeExpense(e3),false);
		/* Then: 
		 * 		- only rep18 (opened) has expense removed, rep17 still got same 2 sales on list
		 */
		assertEquals(rep18.getExpenses().isEmpty(),true);
		assertEquals(rep17.getExpenses().size(),2);
		
	}
	
	/**
	 * <h2>getYearMonth() and setYearMonth() method test</h2>
	 */
	@Test 
	public void testGetnSetYearMonth() {
		//Given
		assertEquals(rep17.getYearMonth(),YearMonth.of(2017, 12));
		assertEquals(rep18.getYearMonth(),YearMonth.of(2018, 1));
		//When
		rep17.setYearMonth(YearMonth.of(2017, 5));
		rep18.setYearMonth(YearMonth.of(2018, 2));
		//Then
		assertEquals(rep17.getYearMonth(),YearMonth.of(2017, 5));
		assertEquals(rep18.getYearMonth(),YearMonth.of(2018, 2));
	}
		
	/**
	 * <h2>getBusinessDays() and setBusinessDays() method test</h2>
	 */
	@Test 
	public void testGetnSetBusinessDays() {
		//Given
		assertEquals(rep17.getBusinessDays(),0);
		//When
		rep17.setBusinessDays(5);
		//Then
		assertEquals(rep17.getBusinessDays(),5);
	}
	
	/**
	 * <h2>addSale() method test</h2>
	 * 
	 * <p>This test verify if addSale() method add only sales that has the same date as YearMonth of Report
	 */
	@Test 
	public void testAddSale() {
		/* Given: 
		 * 		- 2 empty reports (rep17 and rep18)
		 */
		List<Sale> expectedSales18 = new ArrayList<Sale>();
		List<Sale> expectedSales17 = new ArrayList<Sale>();
		assertEquals(rep18.getSales().isEmpty(),true);
		assertEquals(rep17.getSales().isEmpty(),true);
		
		/* When: 
		 * 		- Try to Add sales s1,s2,s3,s4 to rep17 and rep18
		 */	
		rep18.addSale(s1);
		rep18.addSale(s2);		
		rep18.addSale(s3);
		rep18.addSale(s4);
		expectedSales18.add(s1);
		expectedSales18.add(s2);
		expectedSales18.add(s3);
		rep17.addSale(s1);
		rep17.addSale(s2);		
		rep17.addSale(s3);
		rep17.addSale(s4);
		expectedSales17.add(s4);
		
		/* Then: 
		 * 		- Report18 has only s1,s2,s3 and Report17 has s4
		 */
		assertEquals(rep18.getSales(),expectedSales18);
		assertEquals(rep17.getSales(),expectedSales17);
	}
	
	/**
	 * <h2>addExpense() method test</h2>
	 * 
	 * <p>This test verify if addExpense() method add only expenses that has the same date as YearMonth of Report
	 */
	@Test 
	public void testAddExpenseSuccessCases() {
		/* Given: 
		 * 		- 2 empty reports (rep17 and rep18)
		 */
		List<Expense> expectedExpenses18 = new ArrayList<Expense>();
		List<Expense> expectedExpenses17 = new ArrayList<Expense>();
		assertEquals(rep18.getSales().isEmpty(),true);
		assertEquals(rep17.getSales().isEmpty(),true);
		
		/* When: 
		 * 		- Try to Add fixed expense: e1 to rep18 
		 * 		- Try to Add fixed expense: e2 to rep17
		 * 		- Try to add oneoff expense: e3 to rep17 
		 */	
		assertEquals(rep18.addExpense(e1),true);
		assertEquals(rep18.addExpense(e1),false);
		assertEquals(rep17.addExpense(e2),true);	
		assertEquals(rep17.addExpense(e3),true);
		expectedExpenses18.add(e1);
		expectedExpenses17.add(e2);
		expectedExpenses17.add(e3);
		
		/* Then: 
		 * 		- Report18 has fixed expenses: e1
		 * 		- Report17 has fixed expense: e2 AND oneoff expense: e3
		 */
		assertEquals(rep18.getExpenses(),expectedExpenses18);
		assertEquals(rep17.getExpenses(),expectedExpenses17);
	}
	
	/**
	 * <h2>addExpense() method test</h2>
	 * 
	 * <p>This test verify if addExpense() method add only expenses that has the same date as YearMonth of Report
	 */
	@Test 
	public void testAddExpenseInvalidDateTypeOneOff() {
		/* Given: 
		 * 		- 1 empty reports of 2018/01 (rep18)
		 */
		assertEquals(rep18.getSales().isEmpty(),true);

		/* When: 
		 * 		- Try to add oneoff expense of 2017/12 and 2018/02: e3 to rep18
		 */	
		assertEquals(rep18.addExpense(e3),false);
		e3.setDate(e3.getDate().plusYears(1));
		assertEquals(rep18.addExpense(e3),false);
		
		/* Then: 
		 * 		- Report18 still have a empty list of expenses
		 */
		assertEquals(rep18.getSales().isEmpty(),true);

	}
	
	
	/**
	 * <h2>calculateTotalSalesAmount() method test</h2>
	 * 
	 */
	@Test 
	public void testCalculateTotalSalesAmount() {
		/* Given: 
		 * 		- Report of 2018/01, with 3 sales (rep18)
		 */
		assertEquals(rep18.getSales().isEmpty(),true);
		assertEquals(rep18.getExpenses().isEmpty(),true);
		assertEquals(rep18.addExpense(e1),true);
		assertEquals(rep18.addSale(s1),true);
		assertEquals(rep18.addSale(s2),true);	
		assertEquals(rep18.addSale(s3),true);

		/* When: 
		 * 		- calculate total sales amout of rep18 (Sales: 2*15+10)
		 */	
		double expectProfit = rep18.calculateTotalSalesAmount();
		
		/* Then: 
		 * 		- Report18 has 40 euros of total sales amout
		 */
		assertEquals(expectProfit,40,0.0);

	}
	
	/**
	 * <h2>calculateTotalSalesAmount() method test</h2>
	 * 
	 */
	@Test 
	public void testCalculateTotalExpensesAmount() {
		/* Given: 
		 * 		- Report of 2018/01, with 1 expense (rep17)
		 */
		assertEquals(rep17.getExpenses().isEmpty(),true);
		assertEquals(rep17.addExpense(e2),true);	
		assertEquals(rep17.addExpense(e3),true);
		
		/* When: 
		 * 		- calculate value of all expenses in rep17 (Expense: 90+50)
		 */	
		double expectProfit = rep17.calculateTotalExpensesValue();
		
		/* Then: 
		 * 		- Report17 has 140 euros of expenses
		 */
		assertEquals(expectProfit,140,0.0);

	}
	
	
	/**
	 * <h2>calculateProfit() method test</h2>
	 * 
	 */
	@Test 
	public void testCalculateProfit() {
		/* Given: 
		 * 		- Report of 2018/01, with  (rep18)
		 */
		assertEquals(rep18.getSales().isEmpty(),true);
		assertEquals(rep18.getExpenses().isEmpty(),true);
		assertEquals(rep18.addExpense(e1),true);
		assertEquals(rep18.addSale(s1),true);
		assertEquals(rep18.addSale(s2),true);	
		assertEquals(rep18.addSale(s3),true);

		/* When: 
		 * 		- calculate profit of rep18 (Sales: 2*15+10 // Expenses: 35)
		 */	
		double expectProfit = rep18.calculateProfit();
		
		/* Then: 
		 * 		- Report18 has 5 euros of profit
		 */
		assertEquals(expectProfit,5,0.0);

	}
	
	/**
	 * <h2>calculateRoi() method test</h2>
	 * 
	 */
	@Test 
	public void testCalculateRoi() {
		/* Given: 
		 * 		- Report of 2018/01, with  (rep18)
		 */
		assertEquals(rep18.getSales().isEmpty(),true);
		assertEquals(rep18.getExpenses().isEmpty(),true);
		assertEquals(rep18.addExpense(e1),true);
		assertEquals(rep18.addSale(s1),true);
		assertEquals(rep18.addSale(s2),true);	
		assertEquals(rep18.addSale(s3),true);

		/* When: 
		 * 		- calculate ROI of rep18 (Sales: 2*15+10 // Expenses: 35)
		 */	
		double expectRoi = rep18.calculateRoi();
		double result = (((40.0-35.0)/35.0)*100.0);
		
		/* Then: 
		 * 		- Report18 has a ROI of 14,28% 
		 */
		assertEquals(expectRoi,result,0.0);

	}
	
	/**
	 * <h2>calculateRoi() method test</h2>
	 * 
	 * <p>This test verify null expenses cases
	 */
	@Test 
	public void testCalculateRoiNullExpenses() {
		/* Given: 
		 * 		- Report of 2018/01, with 3 sales and 0 expenses (rep18)
		 */
		assertEquals(rep18.getSales().isEmpty(),true);
		assertEquals(rep18.getExpenses().isEmpty(),true);
		assertEquals(rep18.addSale(s1),true);
		assertEquals(rep18.addSale(s2),true);	
		assertEquals(rep18.addSale(s3),true);

		/* When: 
		 * 		- calculate ROI of rep18
		 */	
		double expectRoi = rep18.calculateRoi();
		double result = 0.0;
		
		/* Then: 
		 * 		- Report18 has a ROI of 0% because has no expenses
		 */
		assertEquals(expectRoi,result,0.0);

	}
	
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test true cases</p>
	 */
	@Test
	public void testEqualsTrue() {
		Report expect = new Report(YearMonth.of(2017, 12));
		assertEquals(rep17.equals(expect),true);
		assertEquals(rep18.equals(rep18),true);

	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - Different Instances</p>
	 */
	@Test
	public void testEqualsFalseDifferentInstances() {
		assertEquals(rep17.equals(rep18),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test false cases - Different Classes</p>
	 */
	@Test
	public void testEqualsFalseDifferentClasses() {
		assertEquals(rep17.equals(1),false);
	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null cases</p>
	 */
	@Test
	public void testEqualsNull() {
		assertEquals(rep17.equals(null),false);

	}
	
	/**
	 * <h2>equals() method test</h2>
	 * <p>test null yearMonth</p>
	 */
	@Test
	public void testEqualsNullDate() {
		rep17.setYearMonth(null);
		assertEquals(rep17.equals(rep18),false);

	}
	
	
	/**
	 * <h2>toString() method test</h2>
	 */
	@Test
	public void testToString() {
		String expect = "Report [year=" + rep17.getYearMonth().getYear() + ", month=" + rep17.getYearMonth().getMonth().toString() + ", salesNumber=" + rep17.getSales().size() + ", expensesNumber=" + rep17.getExpenses().size() + ", businessDays="
				+ rep17.getBusinessDays() + "]";
		assertEquals(rep17.toString(),expect);
	}
	
	/**
	 * <h2>hashCode() method test</h2>
	 */
	@Test
	public void testHashCode() {
		Report repTest = new Report(ym2);
		assertEquals(rep18.hashCode(),repTest.hashCode());
		rep18.setYearMonth(null);
		repTest.setYearMonth(null);
		assertEquals(rep18.hashCode(),repTest.hashCode());
		assertNotEquals(rep18.hashCode(),rep17.hashCode());
	}
	
	

}

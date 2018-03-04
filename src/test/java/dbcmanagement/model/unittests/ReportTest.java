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
import dbcmanagement.model.Expense.expenseType;
import dbcmanagement.model.Product.productType;
import dbcmanagement.model.Report;
import dbcmanagement.model.ExpenseRegistry;
import dbcmanagement.model.Product;
import dbcmanagement.model.Sale;
import dbcmanagement.model.SaleRegistry;



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
	
	SaleRegistry saleList;
	ExpenseRegistry expenseList;

	
	Report rep17;
	Report rep18;
	
	/**
	 * <h2>Setup for all unit tests: </h2>
	 * 
	 * <p>Year [y1] : 2018 </p>
	 * <p>Year [y2] : 2017 </p>
	 * <p>Month [m1] : January </p>
	 * <p>Month [m2] : December </p>
	 * <p>SaleList [sl1] : </p>
	 * <p>SaleList [sl2] : </p>
	 * <p>ExpenseList [ep1] : [d1,t1,c1] </p>
	 * <p>ExpenseList [ep1] : [d2,t2,c2] </p>
	 * <p>businessDays [bd1] : 22 </p>
	 * <p>ExpenseList [bd2] : 21 </p>
	 * 
	 * <p>Report [rep1] : [y1,m1,sl1,ep1,bd1] </p>
	 * <p>Report [rep2] : [y2,m2,sl2,ep2,bd2] </p>
	 * 
	 * 
	 */
	@Before
	public void setUp() {
		
		expenseList = new ExpenseRegistry();
		saleList = new SaleRegistry();

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
		
		e1 = expenseList.createExpense("Agua",expenseType.FIXED,35,d1);
		e2 = expenseList.createExpense("Internet",expenseType.FIXED,50,d2,"6 meses de contrato");
		e3 = expenseList.createExpense("Secadores",expenseType.ONEOFF,90,d3,"3 unidades");
		
		s1 = saleList.createSale(dt1, c1,p1);
		s2 = saleList.createSale(dt2, c2,p2);
		s3 = saleList.createSale(dt3, c1,p1);
		s4 = saleList.createSale(dt4, c1,p1);
		
		expenseList.addExpense(e1);
		expenseList.addExpense(e2);
		expenseList.addExpense(e3);
		saleList.addSale(s1);
		saleList.addSale(s2);
		saleList.addSale(s3);
		saleList.addSale(s4);
		
		
		rep17 = new Report(ym1);
		rep18 = new Report(ym2);
		
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
	 * <h2>getSales() and setSales() method test</h2>
	 */
	@Test 
	public void testGetnSetSalesList() {
		//Given
		List<Sale> expect = new ArrayList<Sale>();
		List<Sale> result = rep18.getSalesList().getSaleList();
		assertEquals(result,expect);
		//When
		expect.add(s1);
		expect.add(s2);
		rep18.setSales(expect);
		result = rep18.getSalesList().getSaleList();
		//Then
		assertEquals(result,expect);		
	}
	
	/**
	 * <h2>getExpenses() and setExpenses() method test</h2>
	 */
	@Test 
	public void testGetnSetExpensesList() {
		//Given
		List<Expense> expect = new ArrayList<Expense>();
		List<Expense> result = rep18.getExpensesList().getExpenseList();
		assertEquals(result,expect);
		//When
		expect.add(e1);
		expect.add(e2);
		rep18.setExpenses(expect);
		result = rep18.getExpensesList().getExpenseList();
		//Then
		assertEquals(result,expect);	
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
		assertEquals(rep18.getSalesList().getSaleList().isEmpty(),true);
		assertEquals(rep17.getSalesList().getSaleList().isEmpty(),true);
		
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
		assertEquals(rep18.getSalesList().getSaleList(),expectedSales18);
		assertEquals(rep17.getSalesList().getSaleList(),expectedSales17);
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
		assertEquals(rep18.getSalesList().getSaleList().isEmpty(),true);
		assertEquals(rep17.getSalesList().getSaleList().isEmpty(),true);
		
		/* When: 
		 * 		- Try to Add fixed expense: e1 to rep18 
		 * 		- Try to Add fixed expense: e2 to rep17
		 * 		- Try to add oneoff expense: e3 to rep17 
		 */	
		assertEquals(rep18.addExpense(e1),true);
		assertEquals(rep17.addExpense(e2),true);	
		assertEquals(rep17.addExpense(e3),true);
		expectedExpenses18.add(e1);
		expectedExpenses17.add(e2);
		expectedExpenses17.add(e3);
		
		/* Then: 
		 * 		- Report18 has fixed expenses: e1
		 * 		- Report17 has fixed expense: e2 AND oneoff expense: e3
		 */
		assertEquals(rep18.getExpensesList().getExpenseList(),expectedExpenses18);
		assertEquals(rep17.getExpensesList().getExpenseList(),expectedExpenses17);
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
		assertEquals(rep18.getSalesList().getSaleList().isEmpty(),true);

		/* When: 
		 * 		- Try to add oneoff expense of 2017/12 and 2018/02: e3 to rep18
		 */	
		assertEquals(rep18.addExpense(e3),false);
		e3.setDate(e3.getDate().plusYears(1));
		assertEquals(rep18.addExpense(e3),false);
		
		/* Then: 
		 * 		- Report18 still have a empty list of expenses
		 */
		assertEquals(rep18.getSalesList().getSaleList().isEmpty(),true);

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
		assertEquals(rep18.getSalesList().getSaleList().isEmpty(),true);
		assertEquals(rep18.getExpensesList().getExpenseList().isEmpty(),true);
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
		assertEquals(rep17.getExpensesList().getExpenseList().isEmpty(),true);
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
		assertEquals(rep18.getSalesList().getSaleList().isEmpty(),true);
		assertEquals(rep18.getExpensesList().getExpenseList().isEmpty(),true);
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
		assertEquals(rep18.getSalesList().getSaleList().isEmpty(),true);
		assertEquals(rep18.getExpensesList().getExpenseList().isEmpty(),true);
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
		assertEquals(rep18.getSalesList().getSaleList().isEmpty(),true);
		assertEquals(rep18.getExpensesList().getExpenseList().isEmpty(),true);
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
		assertEquals(rep17.equals(e1),false);
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
		String expect = "Report [year=" + rep17.getYearMonth().getYear() + ", month=" + rep17.getYearMonth().getMonth().toString() + ", sales=" + rep17.getSalesList() + ", expenses=" + rep17.getExpensesList() + ", businessDays="
				+ rep17.getBusinessDays() + "]";
		assertEquals(rep17.toString(),expect);
	}
	
	
	

}

package bsmanagement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import bsmanagement.model.Address;
import bsmanagement.model.Booking;
import bsmanagement.model.BookingCustomerService;
import bsmanagement.model.Contract;
import bsmanagement.model.Customer;
import bsmanagement.model.Expense;
import bsmanagement.model.ExpenseService;
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
import bsmanagement.model.Product.productType;
import bsmanagement.model.roles.RoleName;

@Component
public class PopulateMockData implements CommandLineRunner{
	
	@Autowired
    private UserService userService;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
    private ProductService productService;
	
	@Autowired
    private SaleService saleService;
	
	@Autowired
    private ExpenseService expenseService;
	
	@Autowired
    private BookingCustomerService bookingCustomerService;
	
	@Autowired
    private ReportSaleExpenseService repSaleExpService;

	private Scanner scan = new Scanner(System.in);
	
	

	public PopulateMockData(UserService userService, ProductService productService, SaleService saleService,
			ExpenseService expenseService, BookingCustomerService bookingCustomerService,
			ReportSaleExpenseService repSaleExpService) {

		this.userService = userService;
		this.productService = productService;
		this.saleService = saleService;
		this.expenseService = expenseService;
		this.bookingCustomerService = bookingCustomerService;
		this.repSaleExpService = repSaleExpService;
	}

	@Override
	public void run(String... args) throws Exception {
		populateDB(false);

	}
	
	
	
	public void populateDB(boolean yesOrNo) {
		
		
        /**
         * populate database from hard coded data
         * 
         * 
         */
		
    	//Add Roles to System
    	userService.addRole(RoleName.ROLE_USER);
    	userService.addRole(RoleName.ROLE_STOREMANAGER);
    	userService.addRole(RoleName.ROLE_ADMINISTRATOR);
    	
		//Register Employers
    	if (yesOrNo)
    	{
    		
    	
		LocalDate birth1 = LocalDate.of(1998, 3, 17);
		LocalDate birth2 = LocalDate.of(1988, 7, 21);
		LocalDate birth3 = LocalDate.of(1968, 9, 25);
		User u1 = userService.createUser("JOAO",birth1,"joao@gmail.com","914047935","324666433");
		User u2 = userService.createUser("PEDRO",birth2,"pedro@domain.uk","915557911","123555433");
		User u3 = userService.createUser("ROGERIO",birth3,"rogerio@net.com","962337135","367876433");
		User u4 = userService.createUser("FUNTINI",birth1,"admin@bsm.com","9090909090","3030303030");
		
		userService.addUser(u1);
		userService.addUser(u2);
		userService.addUser(u3);
		userService.addUser(u4);
		
		Address a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		Address a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		Address a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		Address a4 = new Address("CASA","RUA DOS COMBATENTES","3530-221","MANGUALDE","PORTUGAL");
	
		
		u1.addAddress(a1);
		u1.addAddress(a2);
		u2.addAddress(a3);
		u3.addAddress(a4);
		
		
		userService.setUserProfileEmployer(u1);
		userService.setUserProfileEmployer(u2);
		userService.setUserProfileStoreManager(u3);
		userService.setUserProfileAdmin(u4);
		
		
		u1 = userService.findUserByEmail("joao@gmail.com");
		u2 = userService.findUserByEmail("pedro@domain.uk");
		u3 = userService.findUserByEmail("rogerio@net.com");
		u4 = userService.findUserByEmail("admin@bsm.com");
		
		u1.setPassword(passwordEncoder.encode("12345"));
		u2.setPassword(passwordEncoder.encode("12345"));
		u3.setPassword(passwordEncoder.encode("12345"));
		u4.setPassword(passwordEncoder.encode("12345"));
		
		Contract contract1 = new Contract(300, 25);
		Contract contract2 = new Contract(0, 75);
		u1.addContract(contract1);
		u2.addContract(contract2);
	
		userService.updateUser(u1);
		userService.updateUser(u2);
		userService.updateUser(u3);
		userService.updateUser(u4);
		
		
		//Register Customers
		Customer c1 = bookingCustomerService.createCustomer("LUIS CARLOS");
		Customer c2 = bookingCustomerService.createCustomer("ANA",LocalDate.of(1985, 5, 12),"Porto","966677722");
		Customer c3 = bookingCustomerService.createCustomer("JOAO GOMES",LocalDate.of(1989, 11, 30),"Mangualde","914047935");
		Customer c4 = bookingCustomerService.createCustomer("JOAO SANTOS",LocalDate.of(1990, 12, 5),"Mangualde","912314875");
		Customer c5 = bookingCustomerService.createCustomer("IVO CANELAS",LocalDate.of(1988, 4, 1),"Mangualde","961117922");
		Customer c6 = bookingCustomerService.createCustomer("FILIPE PEREIRA",LocalDate.of(1988, 5, 4),"Mangualde","916553422");
		Customer c7 = bookingCustomerService.createCustomer("JOAO PEREIRA",LocalDate.of(1992, 11, 22),"Mangualde","964144411");
		Customer c8 = bookingCustomerService.createCustomer("PEDRO MARTINS",LocalDate.of(1985, 11, 22),"Nelas","916217930");
		Customer c9 = bookingCustomerService.createCustomer("RICARDO TEXUGO",LocalDate.of(1988, 11, 22),"Vila Ruiva","917017932");
		Customer c10 = bookingCustomerService.createCustomer("LEONARDO",LocalDate.of(1992, 4, 2),"Viseu","918767912");
		Customer c11 = bookingCustomerService.createCustomer("VITOR HUGO",LocalDate.of(1989, 5, 21),"Mangualde","963232222");
		Customer c12 = bookingCustomerService.createCustomer("ROGERIO ALVES",LocalDate.of(1969, 5, 13),"Mangualde","963336651");
		Customer c13 = bookingCustomerService.createCustomer("ANTONIO PEDRO");
		Customer c14 = bookingCustomerService.createCustomer("RUBEN DIAS");
		Customer c15 = bookingCustomerService.createCustomer("CARLOS MIGUEL",LocalDate.of(1977, 10, 15),"Nelas","962216651");
		
		bookingCustomerService.addCustomer(c1);
		bookingCustomerService.addCustomer(c2);
		bookingCustomerService.addCustomer(c3);
		bookingCustomerService.addCustomer(c4);
		bookingCustomerService.addCustomer(c5);
		bookingCustomerService.addCustomer(c6);
		bookingCustomerService.addCustomer(c7);
		bookingCustomerService.addCustomer(c8);
		bookingCustomerService.addCustomer(c9);
		bookingCustomerService.addCustomer(c10);
		bookingCustomerService.addCustomer(c11);
		bookingCustomerService.addCustomer(c12);
		bookingCustomerService.addCustomer(c13);
		bookingCustomerService.addCustomer(c14);
		bookingCustomerService.addCustomer(c15);
		
		//Register Bookings
		Booking b1 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(9), c1,u1);
		Booking b2 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(10), c2,u2);
		Booking b3 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(11), c3,u1);
		Booking b4 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(13).withMinute(30), c4,u1);
		Booking b5 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(14).withMinute(30), c5,u1);
		Booking b6 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(16), c6,u2);
		Booking b7 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(2).withHour(10), c12,u1);
		Booking b8 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(17), c7,u1);
		Booking b9 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(2).withHour(11).withMinute(30), c11,u1);
		Booking b10 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(18), c8,u2);
		Booking b11 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(2).withHour(14), c9,u1);
		Booking b12 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(2).withHour(16).withMinute(30), c10,u1);
		
		bookingCustomerService.addBooking(b1);
		bookingCustomerService.addBooking(b2);
		bookingCustomerService.addBooking(b3);
		bookingCustomerService.addBooking(b4);
		bookingCustomerService.addBooking(b5);
		bookingCustomerService.addBooking(b6);
		bookingCustomerService.addBooking(b7);
		bookingCustomerService.addBooking(b8);
		bookingCustomerService.addBooking(b9);
		bookingCustomerService.addBooking(b10);
		bookingCustomerService.addBooking(b11);
		bookingCustomerService.addBooking(b12);
		
		//Register Products to sell
		Product p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		Product p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		Product p3 = new Product("BARBA",productType.SHAVE,7);
		Product p4 = new Product("SHAMPOO MEN",productType.EXTRA,12);
		
		PaymentMethod cash = new PaymentMethod("CASH",0,0);
		saleService.addPaymentMethod(cash);
		
		productService.addProduct(p1);
		productService.addProduct(p2);
		productService.addProduct(p3);
		productService.addProduct(p4);
		
//		//Register expenses
		Expense e5 = expenseService.createExpense("Internet",expenseType.FIXED,40,LocalDate.now().minusMonths(3),"6 meses de contrato");
		Expense e6 = expenseService.createExpense("Maquina Barbear",expenseType.ONEOFF,60,LocalDate.now().minusMonths(3),"2 unidades");
		
//		repSaleExpService.addExpense(e5);
//		repSaleExpService.addExpense(e6);
//		
		repSaleExpService.addExpense(e5);
		repSaleExpService.addExpense(e6);
		
		
		
		
		
		
		/*
		 * First month sales:
		 */
		int dayCount = 0;
		int firstMonthLen = LocalDate.now().minusMonths(3).lengthOfMonth();
		for (int i = 1; i<firstMonthLen/2; i++)
		{
			if (dayCount != 6 && dayCount !=7 && dayCount !=13 && dayCount !=14)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c3,p3, cash, u1);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c4,p2, cash,u1);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c5,p1, cash,u2);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash,u2);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash,u2);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c7,p1, cash,u1);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c9,p3, cash,u2);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);

				repSaleExpService.loadSale(s1);
				repSaleExpService.loadSale(s2);
				repSaleExpService.loadSale(s3);
				repSaleExpService.loadSale(s4);
				repSaleExpService.loadSale(s5);
				repSaleExpService.loadSale(s6);
				repSaleExpService.loadSale(s7);

				
			}
			
			dayCount++;
		}
		
		for (int i = firstMonthLen/2; i<=firstMonthLen; i++)
		{
			if (dayCount != 20 && dayCount !=21 && dayCount !=27 && dayCount !=28)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c1,p3, cash,u1);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c2,p1, cash,u2);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c8,p1, cash,u1);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash,u2);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash,u2);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c11,p1, cash,u1);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c10,p2, cash,u2);
				
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repSaleExpService.loadSale(s1);
				repSaleExpService.loadSale(s2);
				repSaleExpService.loadSale(s3);
				repSaleExpService.loadSale(s4);
				repSaleExpService.loadSale(s5);
				repSaleExpService.loadSale(s6);
				repSaleExpService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		
		/*
		 * Second month sales:
		 */
		int secondMonthLen = LocalDate.now().minusMonths(2).lengthOfMonth();
		for (int i = 1; i<secondMonthLen/2; i++)
		{
			if (dayCount != 33 && dayCount !=34 && dayCount !=40 && dayCount !=41)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c12,p3, cash,u2);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c13,p1, cash,u2);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c14,p1, cash,u2);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash,u2);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash,u1);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c15,p1, cash,u1);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c4,p1, cash,u1);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repSaleExpService.loadSale(s1);
				repSaleExpService.loadSale(s2);
				repSaleExpService.loadSale(s3);
				repSaleExpService.loadSale(s4);
				repSaleExpService.loadSale(s5);
				repSaleExpService.loadSale(s6);
				repSaleExpService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		for (int i = secondMonthLen/2; i<=secondMonthLen; i++)
		{
			if (dayCount != 47 && dayCount !=48 && dayCount !=54 && dayCount !=55)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c3,p2, cash,null);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c6,p1, cash,null);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c7,p1, cash,null);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash,u2);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash,u2);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c10,p1, cash,u2);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c9,p2, cash,u2);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repSaleExpService.loadSale(s1);
				repSaleExpService.loadSale(s2);
				repSaleExpService.loadSale(s3);
				repSaleExpService.loadSale(s4);
				repSaleExpService.loadSale(s5);
				repSaleExpService.loadSale(s6);
				repSaleExpService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		/*
		 * Third month sales:
		 */
		int thirdMonthLen = LocalDate.now().minusMonths(1).lengthOfMonth();
		for (int i = 1; i<thirdMonthLen/2; i++)
		{
			if (dayCount != 61 && dayCount !=62 && dayCount !=68 && dayCount !=69)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c1,p2, cash,u2);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c2,p1, cash,u2);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c3,p2, cash,u1);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash,u1);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash,u1);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c5,p2, cash,u2);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c4,p3, cash,u1);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repSaleExpService.loadSale(s1);
				repSaleExpService.loadSale(s2);
				repSaleExpService.loadSale(s3);
				repSaleExpService.loadSale(s4);
				repSaleExpService.loadSale(s5);
				repSaleExpService.loadSale(s6);
				repSaleExpService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		for (int i = thirdMonthLen/2; i<=thirdMonthLen; i++)
		{
			if (dayCount != 75 && dayCount !=76 && dayCount !=82 && dayCount !=83)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c10,p2, cash,u2);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c11,p1, cash,u2);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c12,p1, cash,u2);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash,u2);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash,u2);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c15,p1, cash,u2);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c7,p2, cash,u2);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repSaleExpService.loadSale(s1);
				repSaleExpService.loadSale(s2);
				repSaleExpService.loadSale(s3);
				repSaleExpService.loadSale(s4);
				repSaleExpService.loadSale(s5);
				repSaleExpService.loadSale(s6);
				repSaleExpService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		
		
		/*
		 * Current month sales:
		 */
		int currentMonthDay = LocalDate.now().getDayOfMonth();
		for (int i = 1; i<currentMonthDay; i++)
		{
			if (dayCount != 89 && dayCount !=90 && dayCount !=96 && dayCount !=97)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),c1,p2, cash,u2);
				Sale s2 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),c2,p1, cash,u2);
				Sale s3 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),c7,p2, cash,u2);
				Sale s4 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash,u2);
				Sale s5 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash,u2);
				Sale s6 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash,u2);
				Sale s7 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),p1, cash,u2);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repSaleExpService.loadSale(s1);
				repSaleExpService.loadSale(s2);
				repSaleExpService.loadSale(s3);
				repSaleExpService.loadSale(s4);
				repSaleExpService.loadSale(s5);
				repSaleExpService.loadSale(s6);
				repSaleExpService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		Expense e1 = expenseService.createExpense("Luz",expenseType.FIXED,30,LocalDate.now().minusMonths(2),"6 meses de contrato");
		Expense e2 = expenseService.createExpense("Secadores",expenseType.ONEOFF,90,LocalDate.now().minusMonths(1),"3 unidades");
		Expense e3 = expenseService.createExpense("Shampoos",expenseType.ONEOFF,60,LocalDate.now(),"10 unidades");
		Expense e4 = expenseService.createExpense("Agua",expenseType.FIXED,35,LocalDate.now(),null);

//		repSaleExpService.addExpense(e1);
//		repSaleExpService.addExpense(e2);
//		repSaleExpService.addExpense(e3);
//		repSaleExpService.addExpense(e4);

		repSaleExpService.addExpense(e1);
		repSaleExpService.addExpense(e2);
		repSaleExpService.addExpense(e3);
		repSaleExpService.addExpense(e4);
	
		Report r0 = repSaleExpService.getReport(YearMonth.of(2018, 01));
		Report r1 = repSaleExpService.getReport(YearMonth.of(2018, 02));
		Report r2 = repSaleExpService.getReport(YearMonth.of(2018, 03));
		Report r3 = repSaleExpService.getReport(YearMonth.of(2018, 04));
		
//		System.out.println(r0.calculateTotalExpensesValue()+" --- "+r0.calculateTotalSalesAmount()+" --- "+r0.calculateRoi());
//		System.out.println(r1.calculateTotalExpensesValue()+" --- "+r1.calculateTotalSalesAmount()+" --- "+r1.calculateRoi());
//		System.out.println(r2.calculateTotalExpensesValue()+" --- "+r2.calculateTotalSalesAmount()+" --- "+r2.calculateRoi());
//		System.out.println(r3.calculateTotalExpensesValue()+" --- "+r3.calculateTotalSalesAmount()+" --- "+r3.calculateRoi());
		
//		System.out.println(repService.getReport(YearMonth.of(2018, 04)).getSales().get(0).getCustomer());
		System.out.println(saleService.getSales().get(470).getCustomer());
//		bookingCustomerService.removeCustomer(c1);
		User u5 =userService.listAllUsers().get(0);
		System.out.println(userService.listAllUsers().get(0));
		System.out.println(u5.getRoles().toString()+" ... "+u5.getLastContract());
//		System.out.println(expenseType.values().toString()+"***/n****");
//		System.out.println(expenseType.valueOf("FIXED").toString()+"***/n****");
//		
//		System.out.println(Arrays.asList(expenseType.values()).contains(expenseType.valueOf("FIXED")));
//		System.out.println(Arrays.asList(expenseType.values()).contains(expenseType.valueOf("NAO EXISTE")));
//		String choice = scan.nextLine();

//		System.out.println(repSaleExpService.getReport(YearMonth.of(2018, 04)).getExpenses().size());
//		e4 = repSaleExpService.getExpenses().get(repSaleExpService.getExpenses().size()-1);
//		repSaleExpService.removeExpense(e4);
//		
//		System.out.println(repSaleExpService.getReport(YearMonth.of(2018, 04)).getExpenses().size());
//		System.out.println(repSaleExpService.getReport(YearMonth.of(2018, 04)).getExpenses());
//		
//		System.out.println("/n************************/n"+"EXPENSES AVG: "+repSaleExpService.calculateAvgMonthlyExpensesValue()+"/n************************/n");
//		System.out.println("/n************************/n"+"ROI: "+repSaleExpService.calculateRoiAllTime()+"/n************************/n");
//		System.out.println("/n************************/n"+"PROFIT AVG: "+repSaleExpService.calculateAvgMonthlyProfit()+"/n************************/n");
//		System.out.println("/n************************/n"+"ROI AVG: "+repSaleExpService.calculateAvgMonthlyRoi()+"/n************************/n");
//		System.out.println("/n************************/n"+"INCOME AVG: "+repSaleExpService.calculateAvgMonthlySalesAmount()+"/n************************/n");
//		System.out.println("/n************************/n"+repSaleExpService.getSales().size()+"/n************************/n");
////		System.out.println("/n************************/n"+paymentMethodService.listAvailablePaymentMethods()+"/n************************/n");
//		System.out.println("/n************************/n"+repSaleExpService.getExpenses().size()+"/n************************/n");
//		System.out.println("/n************************/n"+bookingCustomerService.getAllCustomers()+"/n************************/n");
////		String choice = scan.nextLine();
//		System.out.println("/n************************/n"+r1.getExpenses()+"/n************************/n");
//		System.out.println("/n************************/n"+r2.getExpenses()+"/n************************/n");
//		System.out.println("/n************************/n"+r3.getExpenses()+"/n************************/n");

		/**
		 * Verificar BusinessDays.. Esta a 0 na base de dados...
		 */	
    	}
    }

}

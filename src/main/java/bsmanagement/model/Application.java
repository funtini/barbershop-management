package bsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.Product.productType;

@ComponentScan({ "bsmanagement", "system" })
@EntityScan(basePackageClasses = {Application.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class Application{
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private ReportService repService;
	
	@Autowired
    private ProductService productService;
	
	@Autowired
    private SaleService saleService;
	
	@Autowired
    private ExpenseService expService;
	
	@Autowired
    private BookingCustomerService bookingCustomerService;

	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {

		SpringApplication.run(Application.class);
	}
	
	@Bean
	public CommandLineRunner demo() {
		return (String... args) -> {

			populateDB();
			};
	}
	
	
	
    public void populateDB() {
		
		
        /**
         * populate database from hard coded data
         * 
         * 
         */
		
		//Register Employers
		LocalDate birth1 = LocalDate.of(1998, 3, 17);
		LocalDate birth2 = LocalDate.of(1988, 7, 21);
		LocalDate birth3 = LocalDate.of(1968, 9, 25);
		User u1 = userService.createUser("JOAO",birth1,"joao@gmail.com","914047935","324666433");
		User u2 = userService.createUser("PEDRO",birth2,"pedro@domain.uk","915557911","123555433");
		User u3 = userService.createUser("ROGERIO",birth3,"rogerio@net.com","962337135","367876433");
		
		userService.addUser(u1);
		userService.addUser(u2);
		userService.addUser(u3);
		
		Address a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		Address a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		Address a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		Address a4 = new Address("CASA","RUA DOS COMBATENTES","3530-221","MANGUALDE","PORTUGAL");
		
		u1.addAddress(a1);
		u1.addAddress(a2);
		u2.addAddress(a3);
		u3.addAddress(a4);
	
		userService.updateUser(u1);
		userService.updateUser(u2);
		userService.updateUser(u1);
		userService.updateUser(u2);
		userService.updateUser(u3);
		
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
		Booking b1 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(9), c1);
		Booking b2 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(10), c2);
		Booking b3 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(11), c3);
		Booking b4 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(13).withMinute(30), c4);
		Booking b5 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(14).withMinute(30), c5);
		Booking b6 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(16), c6);
		Booking b7 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(2).withHour(10), c12);
		Booking b8 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(17), c7);
		Booking b9 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(2).withHour(11).withMinute(30), c11);
		Booking b10 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(1).withHour(18), c8);
		Booking b11 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(2).withHour(14), c9);
		Booking b12 = bookingCustomerService.createBooking(LocalDateTime.now().plusDays(2).withHour(16).withMinute(30), c10);
		
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
		
		productService.addProduct(p1);
		productService.addProduct(p2);
		productService.addProduct(p3);
		productService.addProduct(p4);
		
//		//Register expenses
		repService.addExpense("Internet",expenseType.FIXED,40,LocalDate.now().minusMonths(3),"6 meses de contrato");
		repService.addExpense("Maquina Barbear",expenseType.ONEOFF,60,LocalDate.now().minusMonths(3),"2 unidades");
		
		
		
		
		
		
		/*
		 * First month sales:
		 */
		int dayCount = 0;
		int firstMonthLen = LocalDate.now().minusMonths(3).lengthOfMonth();
		for (int i = 1; i<firstMonthLen/2; i++)
		{
			if (dayCount != 6 && dayCount !=7 && dayCount !=13 && dayCount !=14)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c3,p3, cash);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c4,p2, cash);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c5,p1, cash);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c7,p1, cash);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c9,p3, cash);
	
				repService.loadSale(s1);
				repService.loadSale(s2);
				repService.loadSale(s3);
				repService.loadSale(s4);
				repService.loadSale(s5);
				repService.loadSale(s6);
				repService.loadSale(s7);

				
			}
			
			dayCount++;
		}
		
		for (int i = firstMonthLen/2; i<=firstMonthLen; i++)
		{
			if (dayCount != 20 && dayCount !=21 && dayCount !=27 && dayCount !=28)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c1,p3, cash);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c2,p1, cash);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c8,p1, cash);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c11,p1, cash);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c10,p2, cash);
				
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repService.loadSale(s1);
				repService.loadSale(s2);
				repService.loadSale(s3);
				repService.loadSale(s4);
				repService.loadSale(s5);
				repService.loadSale(s6);
				repService.loadSale(s7);
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
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c12,p3, cash);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c13,p1, cash);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c14,p1, cash);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c15,p1, cash);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c4,p1, cash);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repService.loadSale(s1);
				repService.loadSale(s2);
				repService.loadSale(s3);
				repService.loadSale(s4);
				repService.loadSale(s5);
				repService.loadSale(s6);
				repService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		for (int i = secondMonthLen/2; i<=secondMonthLen; i++)
		{
			if (dayCount != 47 && dayCount !=48 && dayCount !=54 && dayCount !=55)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c3,p2, cash);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c6,p1, cash);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c7,p1, cash);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c10,p1, cash);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c9,p2, cash);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repService.loadSale(s1);
				repService.loadSale(s2);
				repService.loadSale(s3);
				repService.loadSale(s4);
				repService.loadSale(s5);
				repService.loadSale(s6);
				repService.loadSale(s7);
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
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c1,p2, cash);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c2,p1, cash);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c3,p2, cash);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c5,p2, cash);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c4,p3, cash);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repService.loadSale(s1);
				repService.loadSale(s2);
				repService.loadSale(s3);
				repService.loadSale(s4);
				repService.loadSale(s5);
				repService.loadSale(s6);
				repService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		for (int i = thirdMonthLen/2; i<=thirdMonthLen; i++)
		{
			if (dayCount != 75 && dayCount !=76 && dayCount !=82 && dayCount !=83)
			{
				Sale s1 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c10,p2, cash);
				Sale s2 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c11,p1, cash);
				Sale s3 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c12,p1, cash);
				Sale s4 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash);
				Sale s5 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash);
				Sale s6 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c15,p1, cash);
				Sale s7 = saleService.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c7,p2, cash);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repService.loadSale(s1);
				repService.loadSale(s2);
				repService.loadSale(s3);
				repService.loadSale(s4);
				repService.loadSale(s5);
				repService.loadSale(s6);
				repService.loadSale(s7);
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
				Sale s1 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),c1,p2, cash);
				Sale s2 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),c2,p1, cash);
				Sale s3 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),c7,p2, cash);
				Sale s4 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash);
				Sale s5 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash);
				Sale s6 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash);
				Sale s7 = saleService.createSale(LocalDateTime.now().withDayOfMonth(i),p1, cash);
				
				saleService.addSale(s1);
				saleService.addSale(s2);
				saleService.addSale(s3);
				saleService.addSale(s4);
				saleService.addSale(s5);
				saleService.addSale(s6);
				saleService.addSale(s7);
				
				repService.loadSale(s1);
				repService.loadSale(s2);
				repService.loadSale(s3);
				repService.loadSale(s4);
				repService.loadSale(s5);
				repService.loadSale(s6);
				repService.loadSale(s7);
			}
			
			dayCount++;
		}
		
		
		repService.addExpense("Luz",expenseType.FIXED,30,LocalDate.now().minusMonths(2),"6 meses de contrato");
		repService.addExpense("Secadores",expenseType.ONEOFF,90,LocalDate.now().minusMonths(1),"3 unidades");
		repService.addExpense("Shampoos",expenseType.ONEOFF,60,LocalDate.now(),"10 unidades");
		repService.addExpense("Agua",expenseType.FIXED,35,LocalDate.now());
		
		Report r0 = repService.getReport(YearMonth.of(2018, 01));
		Report r1 = repService.getReport(YearMonth.of(2018, 02));
		Report r2 = repService.getReport(YearMonth.of(2018, 03));
		Report r3 = repService.getReport(YearMonth.of(2018, 04));
		
		
		System.out.println(r0.calculateTotalExpensesValue()+" --- "+r0.calculateTotalSalesAmount()+" --- "+r0.calculateRoi());
		System.out.println(r1.calculateTotalExpensesValue()+" --- "+r1.calculateTotalSalesAmount()+" --- "+r1.calculateRoi());
		System.out.println(r2.calculateTotalExpensesValue()+" --- "+r2.calculateTotalSalesAmount()+" --- "+r2.calculateRoi());
		System.out.println(r3.calculateTotalExpensesValue()+" --- "+r3.calculateTotalSalesAmount()+" --- "+r3.calculateRoi());
		
		
		System.out.println("/n************************/n"+"EXPENSES AVG: "+repService.calculateAvgMonthlyExpensesValue()+"/n************************/n");
		System.out.println("/n************************/n"+"ROI: "+repService.calculateRoiAllTime()+"/n************************/n");
		System.out.println("/n************************/n"+"PROFIT AVG: "+repService.calculateAvgMonthlyProfit()+"/n************************/n");
		System.out.println("/n************************/n"+"ROI AVG: "+repService.calculateAvgMonthlyRoi()+"/n************************/n");
		System.out.println("/n************************/n"+"INCOME AVG: "+repService.calculateAvgMonthlySalesAmount()+"/n************************/n");
		System.out.println("/n************************/n"+saleService.getSales().size()+"/n************************/n");
//		System.out.println("/n************************/n"+paymentMethodService.listAvailablePaymentMethods()+"/n************************/n");
		System.out.println("/n************************/n"+expService.getExpenses().size()+"/n************************/n");
		System.out.println("/n************************/n"+bookingCustomerService.getAllCustomers()+"/n************************/n");
		String choice = scan.nextLine();
		System.out.println("/n************************/n"+r1.getExpensesList()+"/n************************/n");
		System.out.println("/n************************/n"+r2.getExpensesList()+"/n************************/n");
		System.out.println("/n************************/n"+r3.getExpensesList()+"/n************************/n");

		/**
		 * Verificar BusinessDays.. Esta a 0 na base de dados...
		 */	

    }
	
	
	

}

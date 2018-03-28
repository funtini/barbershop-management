package bsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import bsmanagement.model.Expense.expenseType;
import bsmanagement.model.Product.productType;

@EntityScan(basePackageClasses = {Application.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class Application implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
    private UserRegistry uReg;
	
	@Autowired
    private ReportRegistry repReg;
	
	@Autowired
    private ProductList prodList;
	
	@Autowired
    private SaleRegistry saleReg;
	
	@Autowired
    private ExpenseRegistry expReg;
	
	@Autowired
    private BookingRegistry bookReg;
	
	@Autowired
    private CustomerRegistry customerReg;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		
		

	}
	
	@Override
	@Transactional
    public void run(String... strings) throws Exception {
		
		
        /**
         * populate database from hard coded data
         * 
         * 
         */
		
		LocalDate birth1 = LocalDate.of(1998, 3, 17);
		LocalDate birth2 = LocalDate.of(1988, 7, 21);
		LocalDate birth3 = LocalDate.of(1968, 9, 25);
		User u1 = uReg.createUser("JOAO",birth1,"joao@gmail.com","914047935","324666433");
		User u2 = uReg.createUser("PEDRO",birth2,"pedro@domain.uk","915557911","123555433");
		User u3 = uReg.createUser("ROGERIO",birth3,"rogerio@net.com","962337135","367876433");
		
		uReg.addUser(u1);
		uReg.addUser(u2);
		uReg.addUser(u3);
		
		Address a1 = new Address("CASA","RUA DO AMARO","3550-444","VISEU","PORTUGAL");
		Address a2 = new Address("TRABALHO","RUA DO PASSAL","3530-194","MANGUALDE","PORTUGAL");
		Address a3 = new Address("CASA","RUA LUIS CAMOES","4425-651","PORTO","PORTUGAL");
		Address a4 = new Address("CASA","RUA DOS COMBATENTES","3530-221","MANGUALDE","PORTUGAL");
		
		u1.addAddress(a1);
		u1.addAddress(a2);
		u2.addAddress(a3);
		u3.addAddress(a4);
	
		uReg.updateUser(u1);
		uReg.updateUser(u2);
		uReg.updateUser(u1);
		uReg.updateUser(u2);
		uReg.updateUser(u3);
		
		Customer c1 = customerReg.createCustomer("LUIS CARLOS");
		Customer c2 = customerReg.createCustomer("ANA",LocalDate.of(1985, 5, 12),"Porto","966677722");
		Customer c3 = customerReg.createCustomer("JOAO GOMES",LocalDate.of(1989, 11, 30),"Mangualde","914047935");
		Customer c4 = customerReg.createCustomer("JOAO SANTOS",LocalDate.of(1990, 12, 5),"Mangualde","912314875");
		Customer c5 = customerReg.createCustomer("IVO CANELAS",LocalDate.of(1988, 4, 1),"Mangualde","961117922");
		Customer c6 = customerReg.createCustomer("FILIPE PEREIRA",LocalDate.of(1988, 5, 4),"Mangualde","916553422");
		Customer c7 = customerReg.createCustomer("JOAO PEREIRA",LocalDate.of(1992, 11, 22),"Mangualde","964144411");
		Customer c8 = customerReg.createCustomer("PEDRO MARTINS",LocalDate.of(1985, 11, 22),"Nelas","916217930");
		Customer c9 = customerReg.createCustomer("RICARDO TEXUGO",LocalDate.of(1988, 11, 22),"Vila Ruiva","917017932");
		Customer c10 = customerReg.createCustomer("LEONARDO",LocalDate.of(1992, 4, 2),"Viseu","918767912");
		Customer c11 = customerReg.createCustomer("VITOR HUGO",LocalDate.of(1989, 5, 21),"Mangualde","963232222");
		Customer c12 = customerReg.createCustomer("ROGERIO ALVES",LocalDate.of(1969, 5, 13),"Mangualde","963336651");
		Customer c13 = customerReg.createCustomer("ANTONIO PEDRO");
		Customer c14 = customerReg.createCustomer("RUBEN DIAS");
		Customer c15 = customerReg.createCustomer("CARLOS MIGUEL",LocalDate.of(1977, 10, 15),"Nelas","962216651");
		
		customerReg.addCustomer(c1);
		customerReg.addCustomer(c2);
		customerReg.addCustomer(c3);
		customerReg.addCustomer(c4);
		customerReg.addCustomer(c5);
		customerReg.addCustomer(c6);
		customerReg.addCustomer(c7);
		customerReg.addCustomer(c8);
		customerReg.addCustomer(c9);
		customerReg.addCustomer(c10);
		customerReg.addCustomer(c11);
		customerReg.addCustomer(c12);
		customerReg.addCustomer(c13);
		customerReg.addCustomer(c14);
		customerReg.addCustomer(c15);
		
		Booking b1 = bookReg.createBooking(LocalDateTime.now().plusDays(1).withHour(9), c1);
		Booking b2 = bookReg.createBooking(LocalDateTime.now().plusDays(1).withHour(10), c2);
		Booking b3 = bookReg.createBooking(LocalDateTime.now().plusDays(1).withHour(11), c3);
		Booking b4 = bookReg.createBooking(LocalDateTime.now().plusDays(1).withHour(13).withMinute(30), c4);
		Booking b5 = bookReg.createBooking(LocalDateTime.now().plusDays(1).withHour(14).withMinute(30), c5);
		Booking b6 = bookReg.createBooking(LocalDateTime.now().plusDays(1).withHour(16), c6);
		Booking b7 = bookReg.createBooking(LocalDateTime.now().plusDays(2).withHour(10), c12);
		Booking b8 = bookReg.createBooking(LocalDateTime.now().plusDays(1).withHour(17), c7);
		Booking b9 = bookReg.createBooking(LocalDateTime.now().plusDays(2).withHour(11).withMinute(30), c11);
		Booking b10 = bookReg.createBooking(LocalDateTime.now().plusDays(1).withHour(18), c8);
		Booking b11 = bookReg.createBooking(LocalDateTime.now().plusDays(2).withHour(14), c9);
		Booking b12 = bookReg.createBooking(LocalDateTime.now().plusDays(2).withHour(16).withMinute(30), c10);
		
		bookReg.addBooking(b1);
		bookReg.addBooking(b2);
		bookReg.addBooking(b3);
		bookReg.addBooking(b4);
		bookReg.addBooking(b5);
		bookReg.addBooking(b6);
		bookReg.addBooking(b7);
		bookReg.addBooking(b8);
		bookReg.addBooking(b9);
		bookReg.addBooking(b10);
		bookReg.addBooking(b11);
		bookReg.addBooking(b12);
		
		Product p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		Product p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		Product p3 = new Product("BARBA",productType.SHAVE,7);
		Product p4 = new Product("SHAMPOO MEN",productType.EXTRA,12);
		PaymentMethod cash = new PaymentMethod("CASH",0,0);
		
		prodList.addProduct(p1);
		prodList.addProduct(p2);
		prodList.addProduct(p3);
		prodList.addProduct(p4);
		
		
		repReg.addExpense("Internet",expenseType.FIXED,40,LocalDate.now().minusMonths(3),"6 meses de contrato");
		repReg.addExpense("Maquina Barbear",expenseType.ONEOFF,60,LocalDate.now().minusMonths(3),"2 unidades");
		/*
		 * First month sales:
		 */
		int dayCount = 0;
		int firstMonthLen = LocalDate.now().minusMonths(3).lengthOfMonth();
		for (int i = 1; i<firstMonthLen/2; i++)
		{
			if (dayCount != 6 && dayCount !=7 && dayCount !=13 && dayCount !=14)
			{
				Sale s1 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c3,p3, cash);
				Sale s2 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c4,p2, cash);
				Sale s3 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c5,p1, cash);
				Sale s4 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash);
				Sale s5 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash);
				Sale s6 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c7,p1, cash);
				Sale s7 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c9,p3, cash);
				
				repReg.loadSale(s1);
				repReg.loadSale(s2);
				repReg.loadSale(s3);
				repReg.loadSale(s4);
				repReg.loadSale(s5);
				repReg.loadSale(s6);
				repReg.loadSale(s7);
			}
			
			dayCount++;
		}
		
		for (int i = firstMonthLen/2; i<=firstMonthLen; i++)
		{
			if (dayCount != 20 && dayCount !=21 && dayCount !=27 && dayCount !=28)
			{
				Sale s1 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c1,p3, cash);
				Sale s2 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c2,p1, cash);
				Sale s3 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c8,p1, cash);
				Sale s4 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash);
				Sale s5 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),p2, cash);
				Sale s6 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c11,p1, cash);
				Sale s7 = saleReg.createSale(LocalDateTime.now().minusMonths(3).withDayOfMonth(i),c10,p2, cash);
				
				repReg.loadSale(s1);
				repReg.loadSale(s2);
				repReg.loadSale(s3);
				repReg.loadSale(s4);
				repReg.loadSale(s5);
				repReg.loadSale(s6);
				repReg.loadSale(s7);
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
				Sale s1 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c12,p3, cash);
				Sale s2 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c13,p1, cash);
				Sale s3 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c14,p1, cash);
				Sale s4 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash);
				Sale s5 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash);
				Sale s6 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c15,p1, cash);
				Sale s7 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c4,p1, cash);
				
				repReg.loadSale(s1);
				repReg.loadSale(s2);
				repReg.loadSale(s3);
				repReg.loadSale(s4);
				repReg.loadSale(s5);
				repReg.loadSale(s6);
				repReg.loadSale(s7);
			}
			
			dayCount++;
		}
		
		for (int i = secondMonthLen/2; i<=secondMonthLen; i++)
		{
			if (dayCount != 47 && dayCount !=48 && dayCount !=54 && dayCount !=55)
			{
				Sale s1 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c3,p2, cash);
				Sale s2 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c6,p1, cash);
				Sale s3 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c7,p1, cash);
				Sale s4 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash);
				Sale s5 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),p2, cash);
				Sale s6 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c10,p1, cash);
				Sale s7 = saleReg.createSale(LocalDateTime.now().minusMonths(2).withDayOfMonth(i),c9,p2, cash);
				
				repReg.loadSale(s1);
				repReg.loadSale(s2);
				repReg.loadSale(s3);
				repReg.loadSale(s4);
				repReg.loadSale(s5);
				repReg.loadSale(s6);
				repReg.loadSale(s7);
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
				Sale s1 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c1,p2, cash);
				Sale s2 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c2,p1, cash);
				Sale s3 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c3,p2, cash);
				Sale s4 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash);
				Sale s5 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash);
				Sale s6 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c5,p2, cash);
				Sale s7 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c4,p3, cash);
				
				repReg.loadSale(s1);
				repReg.loadSale(s2);
				repReg.loadSale(s3);
				repReg.loadSale(s4);
				repReg.loadSale(s5);
				repReg.loadSale(s6);
				repReg.loadSale(s7);
			}
			
			dayCount++;
		}
		
		for (int i = thirdMonthLen/2; i<=thirdMonthLen; i++)
		{
			if (dayCount != 75 && dayCount !=76 && dayCount !=82 && dayCount !=83)
			{
				Sale s1 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c10,p2, cash);
				Sale s2 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c11,p1, cash);
				Sale s3 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c12,p1, cash);
				Sale s4 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash);
				Sale s5 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),p2, cash);
				Sale s6 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c15,p1, cash);
				Sale s7 = saleReg.createSale(LocalDateTime.now().minusMonths(1).withDayOfMonth(i),c7,p2, cash);
				
				repReg.loadSale(s1);
				repReg.loadSale(s2);
				repReg.loadSale(s3);
				repReg.loadSale(s4);
				repReg.loadSale(s5);
				repReg.loadSale(s6);
				repReg.loadSale(s7);
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
				Sale s1 = saleReg.createSale(LocalDateTime.now().withDayOfMonth(i),c1,p2, cash);
				Sale s2 = saleReg.createSale(LocalDateTime.now().withDayOfMonth(i),c2,p1, cash);
				Sale s3 = saleReg.createSale(LocalDateTime.now().withDayOfMonth(i),c7,p2, cash);
				Sale s4 = saleReg.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash);
				Sale s5 = saleReg.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash);
				Sale s6 = saleReg.createSale(LocalDateTime.now().withDayOfMonth(i),p2, cash);
				Sale s7 = saleReg.createSale(LocalDateTime.now().withDayOfMonth(i),p1, cash);
				
				repReg.loadSale(s1);
				repReg.loadSale(s2);
				repReg.loadSale(s3);
				repReg.loadSale(s4);
				repReg.loadSale(s5);
				repReg.loadSale(s6);
				repReg.loadSale(s7);
			}
			
			dayCount++;
		}
		

		
				
		
		
		
		repReg.addExpense("Luz",expenseType.FIXED,30,LocalDate.now().minusMonths(2),"6 meses de contrato");
		repReg.addExpense("Secadores",expenseType.ONEOFF,90,LocalDate.now().minusMonths(1),"3 unidades");
		repReg.addExpense("Shampoos",expenseType.ONEOFF,60,LocalDate.now(),"10 unidades");
		repReg.addExpense("Agua",expenseType.FIXED,35,LocalDate.now());
		
		Report r0 = repReg.getReport(YearMonth.of(2017, 12));
		Report r1 = repReg.getReport(YearMonth.of(2018, 01));
		Report r2 = repReg.getReport(YearMonth.of(2018, 02));
		Report r3 = repReg.getReport(YearMonth.of(2018, 03));
		
		
		System.out.println(r0.calculateTotalExpensesValue()+" --- "+r0.calculateTotalSalesAmount()+" --- "+r0.calculateRoi());
		System.out.println(r1.calculateTotalExpensesValue()+" --- "+r1.calculateTotalSalesAmount()+" --- "+r1.calculateRoi());
		System.out.println(r2.calculateTotalExpensesValue()+" --- "+r2.calculateTotalSalesAmount()+" --- "+r2.calculateRoi());
		System.out.println(r3.calculateTotalExpensesValue()+" --- "+r3.calculateTotalSalesAmount()+" --- "+r3.calculateRoi());
		
		
		System.out.println("/n************************/n"+"EXPENSES AVG: "+repReg.calculateAvgMonthlyExpensesValue()+"/n************************/n");
		System.out.println("/n************************/n"+"ROI: "+repReg.calculateRoiAllTime()+"/n************************/n");
		System.out.println("/n************************/n"+"PROFIT AVG: "+repReg.calculateAvgMonthlyProfit()+"/n************************/n");
		System.out.println("/n************************/n"+"ROI AVG: "+repReg.calculateAvgMonthlyRoi()+"/n************************/n");
		System.out.println("/n************************/n"+"INCOME AVG: "+repReg.calculateAvgMonthlySalesAmount()+"/n************************/n");
//		System.out.println("/n************************/n"+r0.getExpensesList()+"/n************************/n");
//		System.out.println("/n************************/n"+r1.getExpensesList()+"/n************************/n");
//		System.out.println("/n************************/n"+r2.getExpensesList()+"/n************************/n");
//		System.out.println("/n************************/n"+r3.getExpensesList()+"/n************************/n");

		/**
		 * Verificar BusinessDays.. Esta a 0 na base de dados...
		 */	
		
//        authorA.setBooks(bl);
//
//        Author authorB = new Author("Bauer","Blabla");
//		List<Book> bll = new ArrayList<>();
//        bll.add(new Book("Cristo Rei",authorB));
//        bll.add(new Book("Paixao Fatal",authorB));
//        authorB.setBooks(bll);
//
//        List<Author> aul = new ArrayList<>();
//        aul.add(authorA);
//        aul.add(authorB);
//        authorRepository.save(aul);
//
//        // fetch all categories
//        log.info("Authors found with findAll():");
//		log.info("-------------------------------");
//		for (Author author : authorRepository.findAll()) {
//			log.info(author.toString());
//		}
//		log.info("");
    }
	
	
	

}

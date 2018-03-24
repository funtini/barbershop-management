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
		
		
        // save a couple of categories.
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
		
		Customer c1 = customerReg.createCustomer("LUIS CARLOS");
		Customer c2 = customerReg.createCustomer("ANA",LocalDate.of(1985, 5, 12),"Porto","966677722");
		Customer c3 = customerReg.createCustomer("JOAO PEDRO",LocalDate.of(1989, 11, 22),"Mangualde","914047935");
		
		customerReg.addCustomer(c1);
		customerReg.addCustomer(c2);
		customerReg.addCustomer(c3);
		
		Booking b1 = bookReg.createBooking(LocalDateTime.now().plusHours(12), c1);
		Booking b2 = bookReg.createBooking(LocalDateTime.now().plusDays(2), c2);
		
		bookReg.addBooking(b1);
		bookReg.addBooking(b2);
		
		u1.addAddress(a1);
		u1.addAddress(a2);
		u2.addAddress(a3);
		uReg.updateUser(u1);
		uReg.updateUser(u2);
		uReg.updateUser(u1);
		uReg.updateUser(u2);
		
		
		Product p1 = new Product("CORTE COM LAVAGEM",productType.HAIRCUT,15);
		Product p2 = new Product("CORTE SIMPLES",productType.HAIRCUT,10);
		Product p3 = new Product("BARBA",productType.SHAVE,7);
		Product p4 = new Product("SHAMPOO MEN",productType.EXTRA,12);
		PaymentMethod cash = new PaymentMethod("CASH",0,0);
		
		
		prodList.addProduct(p1);
		prodList.addProduct(p2);
		prodList.addProduct(p3);
		prodList.addProduct(p4);
		
//		Sale s1 = new Sale(LocalDateTime.now(),p1,cash);
//		saleReg.addSale(s1);

		Sale s1 = saleReg.createSale(LocalDateTime.now().minusMonths(2),c3,p3, cash);
		Sale s2 = saleReg.createSale(LocalDateTime.now().minusMonths(1),c3,p1, cash);
		Sale s3 = saleReg.createSale(LocalDateTime.now().minusMonths(1),c3,p1, cash);
		Sale s4 = saleReg.createSale(LocalDateTime.now(),p2, cash);
		Sale s5 = saleReg.createSale(LocalDateTime.now(),p2, cash);
		Sale s6 = saleReg.createSale(LocalDateTime.now(),c1,p1, cash);
		Sale s7 = saleReg.createSale(LocalDateTime.now(),c3,p3, cash);
		
//		saleReg.addSale(s1);
//		saleReg.addSale(s2);
//		saleReg.addSale(s3);
//		saleReg.addSale(s4);
//		saleReg.addSale(s5);
//		saleReg.addSale(s6);
//		saleReg.addSale(s7);
		
		repReg.loadSale(s1);
		repReg.loadSale(s2);
		repReg.loadSale(s3);
		repReg.loadSale(s4);
		repReg.loadSale(s5);
		repReg.loadSale(s6);
		repReg.loadSale(s7);
		
		
		System.out.println("/n************************/n"+saleReg.getSales()+"/n************************/n");
		
		repReg.addExpense("Internet",expenseType.FIXED,50,LocalDate.now().minusMonths(2),"6 meses de contrato");
		repReg.addExpense("Secadores",expenseType.ONEOFF,90,LocalDate.now().minusMonths(1),"3 unidades");
		repReg.addExpense("Shampoos",expenseType.ONEOFF,60,LocalDate.now(),"10 unidades");
		repReg.addExpense("Agua",expenseType.FIXED,35,LocalDate.now());
		
		
		
		Report r1 = repReg.getReport(YearMonth.of(2018, 01));
		Report r2 = repReg.getReport(YearMonth.of(2018, 02));
		System.out.println("/n************************/n"+r1.getExpensesList()+"/n************************/n");
		System.out.println("/n************************/n"+r2.getExpensesList()+"/n************************/n");

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

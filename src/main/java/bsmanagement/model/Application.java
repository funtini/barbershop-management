package bsmanagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
    private AuthorRepository authorRepository;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		
		

	}
	
	@Override
	@Transactional
    public void run(String... strings) throws Exception {
        // save a couple of categories.
		Author authorA = new Author("Bauer","Blabla");
		List<Book> bl = new ArrayList<>();
        bl.add(new Book("O Messias",authorA));
        bl.add(new Book("O Crime de Verao",authorA));
        bl.add(new Book("Passeio nos Montes",authorA));
        authorA.setBooks(bl);

        Author authorB = new Author("Bauer","Blabla");
		List<Book> bll = new ArrayList<>();
        bll.add(new Book("Cristo Rei",authorB));
        bll.add(new Book("Paixao Fatal",authorB));
        authorB.setBooks(bll);

        List<Author> aul = new ArrayList<>();
        aul.add(authorA);
        aul.add(authorB);
        authorRepository.save(aul);

        // fetch all categories
        log.info("Authors found with findAll():");
		log.info("-------------------------------");
		for (Author author : authorRepository.findAll()) {
			log.info(author.toString());
		}
		log.info("");
    }
	
	
	

}

package bsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({ "bsmanagement", "system" })
@EnableJpaRepositories({"bsmanagement"})
@SpringBootApplication()
public class Application{
	
	public static void main(String[] args) {

		SpringApplication.run(Application.class);
	}
	

}

package bsmanagement.model.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	boolean existsByName(String name);
	
	boolean existsByEmail(String email);
	
	Customer getOneByName(String name);
	
	Customer getOneByEmail(String email);

}

package bsmanagement.model.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}

package bsmanagement.model.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}

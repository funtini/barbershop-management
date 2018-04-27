package bsmanagement.model.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.Sale;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Integer> {

}

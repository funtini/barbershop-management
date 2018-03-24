package bsmanagement.model.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

}

package bsmanagement.jparepositories.classtests;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import bsmanagement.model.Booking;
import bsmanagement.model.Expense;
import bsmanagement.model.jparepositories.ExpenseRepository;

public class ExpenseRepositoryClass implements ExpenseRepository{
	
	Set<Expense> expenses = new LinkedHashSet<>();

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Expense arg0) {
		expenses.remove(arg0);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Expense> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer arg0) {
		for (Expense expense : expenses) {
            if (expense.getId()==(arg0)) {
                return true;
            }
        }
        return false;
	}

	@Override
	public Iterable<Expense> findAll() {
		return new ArrayList<Expense>(expenses);
	}

	@Override
	public Iterable<Expense> findAllById(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Expense> findById(Integer arg0) {
		for (Expense expense : expenses) {
            if (expense.getId()==(arg0)) {
                return Optional.of(expense);
            }
        }
        return null;
	}

	@Override
	public <S extends Expense> S save(S arg0) {
		if (existsById(arg0.getId())) {
			expenses.remove(arg0);
			expenses.add(arg0);
		}
		arg0.setId(Expense.getAndIncrementId());
		expenses.add(arg0);
		return arg0;
	}

	@Override
	public <S extends Expense> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

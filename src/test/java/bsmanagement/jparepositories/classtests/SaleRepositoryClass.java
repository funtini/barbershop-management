package bsmanagement.jparepositories.classtests;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import bsmanagement.model.Booking;
import bsmanagement.model.Sale;
import bsmanagement.model.jparepositories.SaleRepository;

public class SaleRepositoryClass implements SaleRepository{
	
	Set<Sale> sales = new LinkedHashSet<>();

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Sale arg0) {
		sales.remove(arg0);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Sale> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer arg0) {
		for (Sale sale : sales) {
            if (sale.getId()==(arg0)) {
                return true;
            }
        }
        return false;
	}

	@Override
	public Iterable<Sale> findAll() {
		return new ArrayList<Sale>(sales);
	}

	@Override
	public Iterable<Sale> findAllById(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Sale> findById(Integer arg0) {
		for (Sale sale : sales) {
            if (sale.getId()==(arg0)) {
                return Optional.of(sale);
            }
        }
        return null;
	}

	@Override
	public <S extends Sale> S save(S arg0) {
		if (existsById(arg0.getId())) {
			sales.remove(arg0);
			sales.add(arg0);
		}
		sales.add(arg0);
		return arg0;
	}

	@Override
	public <S extends Sale> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

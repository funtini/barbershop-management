package bsmanagement.jparepositories.classtests;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import bsmanagement.model.Booking;
import bsmanagement.model.Product;
import bsmanagement.model.jparepositories.ProductRepository;

public class ProductRepositoryClass implements ProductRepository{

	Set<Product> products = new LinkedHashSet<>();
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Product arg0) {
		products.remove(arg0);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Product> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer arg0) {
		for (Product prod : products) {
            if (prod.getId()==(arg0)) {
                return true;
            }
        }
        return false;
	}

	@Override
	public Iterable<Product> findAll() {
		return new ArrayList<Product>(products);
	}

	@Override
	public Iterable<Product> findAllById(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findById(Integer arg0) {
		for (Product prod : products) {
            if (prod.getId()==(arg0)) {
                return Optional.of(prod);
            }
        }
        return null;
	}

	@Override
	public <S extends Product> S save(S arg0) {
		if (existsById(arg0.getId())) {
			products.remove(arg0);
			products.add(arg0);
			return arg0;
		}
		arg0.setId(Product.getAndIncrementId());
		products.add(arg0);
		return arg0;
	}

	@Override
	public <S extends Product> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

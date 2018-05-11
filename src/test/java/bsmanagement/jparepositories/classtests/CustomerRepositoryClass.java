package bsmanagement.jparepositories.classtests;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import bsmanagement.model.Booking;
import bsmanagement.model.Customer;
import bsmanagement.model.jparepositories.CustomerRepository;

public class CustomerRepositoryClass implements CustomerRepository {

	Set<Customer> list = new LinkedHashSet<>();
	
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<Customer> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> allCustomers = new ArrayList<>(list);
		return allCustomers;
	}

	@Override
	public List<Customer> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findAllById(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getOne(Integer arg0) {
		for (Customer customer : list) {
            if (customer.getId()==arg0) {
                return customer;
            }
        }
		return null;
	}

	@Override
	public <S extends Customer> List<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Customer arg0) {
		list.remove(arg0);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer arg0) {
		for (Customer customer : list) {
            if (customer.getId()==arg0) {
                list.remove(customer);
            }
        }
		
	}

	@Override
	public boolean existsById(Integer arg0) {
		for (Customer customer : list) {
            if (customer.getId()==arg0) {
                return true;
            }
        }
        return false;
	}

	@Override
	public Optional<Customer> findById(Integer arg0) {
		for (Customer customer : list) {
            if (customer.getId()==arg0) {
                return Optional.of(customer);
            }
        }
        return null;
	}

	@Override
	public <S extends Customer> S save(S arg0) {
		if (existsById(arg0.getId())) {
			list.remove(arg0);
			list.add(arg0);
		}
		arg0.setId(Customer.getAndIncrementId());
		list.add(arg0);
		return arg0;
	}

	@Override
	public <S extends Customer> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Customer> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Customer> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> Optional<S> findOne(Example<S> arg0) {
		
        return null;
	}

	@Override
	public boolean existsByName(String name) {
		for (Customer customer : list) {
            if (customer.getName().equals(name)) {
                return true;
            }
        }
        return false;
	}

	@Override
	public Customer getOneByName(String name) {
		for (Customer customer : list)
			if (customer.getName().equals(name))
				return customer;
		return null;
	}

}

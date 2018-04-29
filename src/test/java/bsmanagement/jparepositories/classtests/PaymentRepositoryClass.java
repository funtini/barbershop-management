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

import bsmanagement.model.Customer;
import bsmanagement.model.Expense;
import bsmanagement.model.PaymentMethod;
import bsmanagement.model.Product;
import bsmanagement.model.jparepositories.PaymentRepository;

public class PaymentRepositoryClass implements PaymentRepository{
	
	Set<PaymentMethod> payments = new LinkedHashSet<>();

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<PaymentMethod> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaymentMethod> findAll() {
		return new ArrayList<PaymentMethod>(payments);
	}

	@Override
	public List<PaymentMethod> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PaymentMethod> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PaymentMethod> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMethod> findAllById(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PaymentMethod getOne(String arg0) {
		for (PaymentMethod payment : payments) {
            if (payment.getType()==arg0) {
                return payment;
            }
        }
		return null;
	}

	@Override
	public <S extends PaymentMethod> List<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PaymentMethod> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PaymentMethod> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PaymentMethod> S save(S entity) {
		if (existsById(entity.getType())) {
			payments.remove(entity);
			payments.add(entity);
		}
		payments.add(entity);
		return entity;
	}

	@Override
	public Optional<PaymentMethod> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		for (PaymentMethod payment : payments) {
            if (payment.getType()==id) {
                return true;
            }
        }
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PaymentMethod entity) {
		payments.remove(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends PaymentMethod> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends PaymentMethod> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PaymentMethod> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PaymentMethod> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends PaymentMethod> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

}

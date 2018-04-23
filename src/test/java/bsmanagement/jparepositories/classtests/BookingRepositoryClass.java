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
import bsmanagement.model.jparepositories.BookingRepository;

public class BookingRepositoryClass implements BookingRepository{

	Set<Booking> list = new LinkedHashSet<>();
	
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<Booking> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Booking> findAll() {
		List<Booking> allBookings = new ArrayList<>(list);
		return allBookings;
	}

	@Override
	public List<Booking> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Booking> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Booking> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> findAllById(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Booking getOne(Integer arg0) {
		for (Booking booking : list) {
            if (booking.getId()==arg0) {
                return booking;
            }
        }
		return null;
	}

	@Override
	public <S extends Booking> List<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Booking> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Booking> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Booking arg0) {
		list.remove(arg0);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Booking> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer arg0) {
		for (Booking booking : list) {
            if (booking.getId()==arg0) {
                list.remove(booking);
            }
        }
		
	}

	@Override
	public boolean existsById(Integer arg0) {
		for (Booking booking : list) {
            if (booking.getId()==arg0) {
                return true;
            }
        }
		return false;
	}

	@Override
	public Optional<Booking> findById(Integer arg0) {
		for (Booking booking : list) {
            if (booking.getId()==arg0) {
                return Optional.of(booking);
            }
        }
		return null;
	}

	@Override
	public <S extends Booking> S save(S arg0) {
		list.add(arg0);
		return arg0;
	}

	@Override
	public <S extends Booking> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Booking> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Booking> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Booking> Optional<S> findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

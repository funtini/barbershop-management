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

import bsmanagement.model.User;
import bsmanagement.model.jparepositories.UserRepository;



public class UserRepositoryClass implements UserRepository {

	Set<User> list = new LinkedHashSet<>();

	

	@Override
	public void delete(User arg0) {
		list.remove(arg0);
		
	}


	@Override
	public boolean existsById(String arg0) {
		for (User userA : list) {
            if (userA.getEmailAddress().equals(arg0)) {
                return true;
            }
        }
        return false;
	}

	

	@Override
	public Optional<User> findById(String arg0) {
		for (User userA : list) {
            if (userA.getEmailAddress().equals(arg0)) {
                return Optional.of(userA);
            }
        }
        return null;
	}

	@Override
	public <S extends User> S save(S arg0) {
		list.add(arg0);
        return arg0;
	}


	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteInBatch(Iterable<User> arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<User>(list);
	}


	@Override
	public List<User> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends User> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends User> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findAllById(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User getOne(String arg0) {
		for (User userA : list) {
            if (userA.getEmailAddress().equals(arg0)) {
                return userA;
            }
        }
        return null;
	}


	@Override
	public <S extends User> List<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends User> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void deleteById(String id) {
		for (User userA : list) {
            if (userA.getEmailAddress().equals(id)) {
                list.remove(userA);
            }
        }		
	}


	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends User> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <S extends User> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Optional<User> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<User> findByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<User> findByUsernameOrEmail(String username, String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}




   
}

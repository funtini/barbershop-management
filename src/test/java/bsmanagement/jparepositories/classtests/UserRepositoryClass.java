package bsmanagement.jparepositories.classtests;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import bsmanagement.model.User;
import bsmanagement.model.jparepositories.UserRepository;



public class UserRepositoryClass implements UserRepository {

	Set<User> list = new LinkedHashSet<>();

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(User arg0) {
		list.remove(arg0);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends User> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String arg0) {
		// TODO Auto-generated method stub
		
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
	public Iterable<User> findAll() {
		return new ArrayList<User>(list);
	}


	@Override
	public Iterable<User> findAllById(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
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
	public <S extends User> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

   
}

package bsmanagement.model.jparepositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.User;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findByName(String name);
	
	Optional<User> findByPhone(String phone);

    Optional<User> findByNameOrEmail(String username, String email);

    List<User> findByNameIn(List<String> userIds);

    Boolean existsByPhone(String phone);

    Boolean existsByEmail(String email);

}

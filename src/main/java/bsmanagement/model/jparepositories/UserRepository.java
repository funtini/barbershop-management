package bsmanagement.model.jparepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.User;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}

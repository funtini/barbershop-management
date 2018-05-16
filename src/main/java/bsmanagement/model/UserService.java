package bsmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.exception.AppException;
import bsmanagement.model.Role.RoleName;
import bsmanagement.model.User.UserProfile;
import bsmanagement.model.jparepositories.RoleRepository;
import bsmanagement.model.jparepositories.UserRepository;
import system.dto.UserLoginDTO;




/**
 * <h1> UserService </h1>
 * <p>
 * UserService is a service class that manage all users registered in application.
 * This class contains information like:
 * <ul>
 * <li>userRepository - Repository of users 
 * </ul>
 * <p>
 * UserService has annotation 'Autowired' from springboot framework, so this class doesn't need to be constructed in Spring Boot Applications.
 * 
 * @author JOAO GOMES
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository rolesRepository;

	/**
	 * Default Constructor of userService
	 */
	public UserService() {
		
	}
	
	/**
	 * Creates an instance of a User.
	 *
	 * @param name
	 * @param birthDate
	 * @param email
	 * @param phone
	 * @param taxPayerId
	 * 
	 * @return User
	 */
	public User createUser(String name, LocalDate birth, String email, String phone, String taxPayerId)
	{
		return new User(name,birth,email,phone,taxPayerId);
	}
	
	/**
	 * Add an user to list of users registered in company.
	 * The user is added if only the user instance is valid and the emailaddress is unique in List
	 * 
	 *
	 * @param User
	 *            to be added to the list.
	 * @return true if the user is successfully added, false otherwise.
	 */
	public boolean addUser(User user) {

		if (userRepo.existsById(user.getEmailAddress())) {
			return false;
		}

		if (user.isValid()) {
			userRepo.save(user);
			return true;
		}
		return false;
	}
	
	/**
	 * Searches all company registered users taking "email" as search parameter.
	 *
	 * @param email
	 *            Search parameter.
	 * @return User that matches search parameter or null if no user is found.
	 */
	public User findUserByEmail(String email) {
		for (User u : listAllUsers())
			if (u.getEmailAddress().equals(email))
				return u;
		return null;

	}
	
	/**
	 * Searches all registered users as a specific "profile"
	 *
	 * @param profile
	 *            Search parameter.
	 * @return List of users that match the search parameter.
	 */
	public List<User> searchUserByProfile(UserProfile userProfile) {

		List<User> profileList = new ArrayList<>();

		for (User user : listAllUsers())
			if (user.getProfile().equals(userProfile))
				profileList.add(user);
		return profileList;
	}
	
	
	/**
	 * Sets an user to active status
	 *
	 * @param user
	 *            user to set Inactive
	 * @return True if assignment is successful, false otherwise.
	 */
	public boolean setUserActive(User user) {

		for (User u : listAllUsers())
			if (user.getEmailAddress() == u.getEmailAddress() && !user.isActive()) {
				user.setActive();
				updateUser(user);
				return true;
			}
		
		return false;
	}

	/**
	 * Sets an user to inactive status
	 *
	 * @param user
	 *            user to set inactive
	 * @return True if assignment is successful, false otherwise.
	 */
	public boolean deactivateUser(User user) {
		for (User u : listAllUsers())
			if (user.getEmailAddress() == u.getEmailAddress() && user.isActive()) {
				user.deactivate();
				updateUser(user);
				return true;
			}
		
		return false;
	}
	
	/**
	 * Sets a registered user as Employer
	 *
	 * @param user
	 *            User to which we will assign the Employer profile
	 * @return True if assignment is successful, false otherwise.
	 */
	public boolean setUserProfileEmployer(User user) {
		for (User u : listAllUsers())
			if (user.getEmailAddress() == u.getEmailAddress()) {
				user.setProfileEmployer();
				updateUser(user);
				return true;
			}
		return false;
	}
	
	/**
	 * Sets a registered user as Administrator
	 *
	 * @param user
	 *            User to which we will assign the administrator profile
	 * @return True if assignment is successful, false otherwise.
	 */
	public boolean setUserProfileAdmin(User user) {
		for (User u : listAllUsers())
			if (user.getEmailAddress() == u.getEmailAddress()) {
				user.setProfileAdmin();
				updateUser(user);
				return true;
			}
		return false;
	}
	
	
	/**
	 * Sets a registered user as Store Manager
	 *
	 * @param user
	 *            User to which we will assign the store manager profile
	 * @return True if assignment is successful, false otherwise.
	 */
	public boolean setUserProfileStoreManager(User user) {
		for (User u : listAllUsers())
			if (user.getEmailAddress() == u.getEmailAddress()) {
				user.setProfileStoreManager();
				updateUser(user);
				return true;
			}
		return false;
	}
	
	/**
	 * Find user by email and, if successful, validate his password.
	 *
	 * @param email
	 *            Required login parameter.
	 * @param password
	 *            Parameter to be passed on to validatePassword method (in User
	 *            class), only if email is found.
	 * @return UserLoginDTO - Object that behaves like Data Transfer Object
	 */
	public UserLoginDTO validateData(String email, String password) {

		User user = findUserByEmail(email);
		if (user == null)
			return new UserLoginDTO("Invalid Email or Password\n");
		return user.validatePassword(password);
	}
	
	/**
	 * Method to update an existing user in DataBase
	 * 
	 * @param user
	 * 
	 * @return true if user exists and is successfully updated, false otherwise
	 */
	public boolean updateUser(User user)
	{
		if (userRepo.existsById(user.getEmailAddress()) && user.isValid()) {
            userRepo.save(user);
            return true;
        }
        return false;
	}

	/**
	 * Method to get a list of registered users
	 * 
	 * @return the usersList
	 */
	public List<User> listAllUsers() {
		List<User> usersList = new ArrayList<>();
		for(User u : userRepo.findAll())
			usersList.add(u);
		return usersList;
	}
	
	/**
	 * Method to get a list of registered users
	 * 
	 * @return the usersList
	 */
	public List<User> listActiveUsers() {
		List<User> usersList = new ArrayList<>();
		for(User u : listAllUsers())
			if (u.isActive())
				usersList.add(u);
		return usersList;
	}
	
	
	/**
	 * Method to get a list of registered users
	 * 
	 * @return the usersList
	 */
	public List<User> listInactiveUsers() {
		List<User> usersList = new ArrayList<>();
		for(User u : listAllUsers())
			if (!u.isActive())
				usersList.add(u);
		return usersList;
	}
	
	
	public void setUserRole(String email)
	{
		 Role userRole = rolesRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User Role not set."));
		 User user = findUserByEmail(email);
	     user.setRoles(Collections.singleton(userRole));
	     updateUser(user);
	}
	
	public void setManagerRole(String email)
	{
		Role managerRole = rolesRepository.findByName(RoleName.ROLE_MANAGER).orElseThrow(() -> new AppException("User Role not set."));
		User user = findUserByEmail(email);
	    user.setRoles(Collections.singleton(managerRole));
	    updateUser(user);
	}
	
	public void setAdminRole(String email)
	{
		Role adminRole = rolesRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new AppException("User Role not set."));
		User user = findUserByEmail(email);
	    user.setRoles(Collections.singleton(adminRole));
	    updateUser(user);
	}
	
	public void addRole(RoleName role)
	{
		 Role newRole = new Role(role);
		 if (!rolesRepository.existsByName(role))
			 rolesRepository.save(newRole); 
		 
	}
	
	public void setUserRepository(UserRepository userRepository)
	{
		this.userRepo = userRepository;
	}
	
	public void setRoleRepository(RoleRepository roleRepository)
	{
		this.rolesRepository = roleRepository;
	}
	
	
	public void clearData()
	{
		userRepo.deleteAll();
	}
	
}

package bsmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.User.UserProfile;
import bsmanagement.model.jparepositories.UserRepository;
import system.dto.UserLoginDTO;




/**
 * <h1> UserRegistry </h1>
 * <p>
 * UserRegistry is the abstract base class for manage all users registered in application.
 * This class contains information like:
 * <ul>
 * <li>userList - List of Users
 * </ul>
 * <p>
 * To create an instance of UserRegistry you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
@Service
public class UserRegistry {

	@Autowired
	private UserRepository userRepo;

	
	/**
	 * Constructor of userRegistry
	 */
	public UserRegistry() {
		
	}

	
	public void clearData()
	{
		userRepo.deleteAll();
	}
	
	public boolean updateUser(User user)
	{
		if (userRepo.exists(user.getEmailAddress()) && user.isValid()) {
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
	public List<User> getUsersList() {
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
	public UserRepository getUserRepository() {
		return userRepo;
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

		if (userRepo.exists(user.getEmailAddress())) {
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
	public User getUserByEmail(String email) {
		for (User u : getUsersList())
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

		for (User user : getUsersList())
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

		for (User u : getUsersList())
			if (user.getEmailAddress() == u.getEmailAddress() && !user.isActive()) {
				user.setActive();
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
		for (User u : getUsersList())
			if (user.getEmailAddress() == u.getEmailAddress() && user.isActive()) {
				user.deactivate();
				return true;
			}
		
		return false;
	}
	
	/**
	 * Sets a registered user as Employer
	 *
	 * @param user
	 *            User to which we will assign the director profile
	 */
	public void setUserProfileEmployer(User user) {
		user.setProfileEmployer();
	}
	
	/**
	 * Sets a registered user as Administrator
	 *
	 * @param user
	 *            User to which we will assign the director profile
	 */
	public void setUserProfileAdmin(User user) {
		user.setProfileAdmin();
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

		User user = getUserByEmail(email);
		if (user == null)
			return new UserLoginDTO("Invalid Email or Password\n");
		return user.validatePassword(password);
	}
	
}

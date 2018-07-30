package bsmanagement.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.dto.rest.ContractRestDTO;
import bsmanagement.model.jparepositories.RoleRepository;
import bsmanagement.model.jparepositories.UserRepository;
import bsmanagement.model.roles.Role;
import bsmanagement.model.roles.RoleName;



/**
 * <h1> UserService </h1>
 * <p>
 * UserService is a service class that manage all users registered in application.
 * This class contains information like:
 * <ul>
 * <li>userRepository - Repository of users 
 * <li>rolesRepository - Repository of role profiles
 * </ul>
 * <p>
 * 
 * @author JOAO GOMES
 *
 */
@Service
public class UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository rolesRepository;

	/**
	 * Default Constructor of userService
	 */
	public UserService() {
		
	}
	
	
	
	public UserService(UserRepository userRepo, RoleRepository rolesRepository) {
		super();
		this.userRepo = userRepo;
		this.rolesRepository = rolesRepository;
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
	public List<User> searchUserByProfile(Role role) {

		List<User> profileList = new ArrayList<>();

		for (User user : listAllUsers())
			if (user.getRoles().contains(role) && user.isActive())
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
		
		if (user != null && userRepo.existsById(user.getEmailAddress())) {
            if (user.getRoles().contains(rolesRepository.getOneByName((RoleName.ROLE_USER)))) {
                return false;
            } else {
                Set<Role> userRoles = new LinkedHashSet<>();
                userRoles.add(rolesRepository.getOneByName((RoleName.ROLE_USER)));
                user.setRoles(userRoles);
                userRepo.save(user);
                return true;
            }
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
		if (user != null && userRepo.existsById(user.getEmailAddress())) {
            if (user.getRoles().contains(rolesRepository.getOneByName((RoleName.ROLE_ADMINISTRATOR)))) {
                return false;
            } else {
                Set<Role> userRoles = new LinkedHashSet<>();
                userRoles.add(rolesRepository.getOneByName((RoleName.ROLE_ADMINISTRATOR)));
                user.setRoles(userRoles);
                userRepo.save(user);
                return true;
            }
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
		if (user != null && userRepo.existsById(user.getEmailAddress())) {
            if (user.getRoles().contains(rolesRepository.getOneByName((RoleName.ROLE_STOREMANAGER)))) {
                return false;
            } else {
                Set<Role> userRoles = new LinkedHashSet<>();
                userRoles.add(rolesRepository.getOneByName((RoleName.ROLE_STOREMANAGER)));
                user.setRoles(userRoles);
                userRepo.save(user);
                return true;
            }
        }
        return false;
	}
	
	public void addRole(RoleName role)
    {
    	Role newRole = new Role(role);
    	if (!rolesRepository.existsByName(role))
    		rolesRepository.save(newRole);
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
	
	/*****************************
	 * 
	 * 	CONTRACTS
	 * 
	 * ******************
	 */
	
	/**
	 * Method to get sales commission of an user in a specific month
	 * 
	 * @param userId - user email
	 * @param yearMonth - month
	 * 
	 * @return commission in percentage (double)
	 */
	public double getUserSalesCommissionOfMonth(String userId,YearMonth yearMonth)
	{
		User u = findUserByEmail(userId);
		if (u == null)
			return 0.0;
		return u.getSalesCommissionOfMonth(yearMonth);
	}	
	
	/**
	 * Method to list all user contracts
	 * 
	 * @param userId - user email
	 * 
	 * @return List of Contracts, null if userId doesnt exists
	 */
	public List<Contract> listUserContracts(String userId)
	{
		if (findUserByEmail(userId)==null)
			return null;
		return findUserByEmail(userId).getAllContracts();
	}
	
	/**
	 * Method to list all active contracts
	 * 
	 * @return List of ContractsRestDTO
	 */
	public List<ContractRestDTO> listOpenContractsRestDTO()
	{
		List<ContractRestDTO> contractsList= new ArrayList<>();
		ContractRestDTO contract;
 		for (User u : listAllUsers())
 			if (u.hasOpenContract()) {
 				contract = u.getLastContract().toRestDTO();
 				contract.setEmail(u.getEmailAddress());
 				contract.setName(u.getName());
 				contract.setRole(u.getRoles().toString());
 				contractsList.add(contract);
 			}
 				
 		return contractsList;
	}
	
	/**
	 * Method to add new contract to an user
	 * 
	 * @param user
	 * @param baseSalary
	 * @param salesCommission
	 * 
	 * @return true if new contract was successfully added, false if user has already an open contract
	 */
	public boolean addContract(User user,double baseSalary, double salesCommission)
	{
		if (user == null)
			return false;
		
		return user.addContract(new Contract(baseSalary,salesCommission));
	}
	
	/**
	 * Method to close current contract of an user
	 * 
	 * @return false if user has no contracts or has all contracts already closed, true if contract was successfully closed
	 */
	public boolean closeContract(String userId)
	{
		if (findUserByEmail(userId)==null)
			return false;
		return findUserByEmail(userId).closeContract();
	}
	
	/**
	 * Method to get last contract of an User
	 * 
	 * @return Contract if the user has at least one contract, NULL if user has no contracts or if UserId doesn't exist
	 */
	public Contract getUserLastContract(String userId)
	{
		if (findUserByEmail(userId)==null)
			return null;
		return findUserByEmail(userId).getLastContract();
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


	/**
	 * Method to check if an email exists on repository
	 * 
	 * @return true if email doesnt exists, false otherwise.
	 */
	public Boolean isEmailAvailable(String email) {
		return !userRepo.existsById(email);
	}
    
	
}

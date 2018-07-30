package bsmanagement.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.UserRestDTO;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.payload.UserIdentityAvailability;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	

	/**
	 * Rest Controller to check if some user has specific email
	 * 
	 * @return Payload boolean - UserIdentityAvailability
	 */
	@GetMapping("/users/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {

		Boolean isAvailable = userService.isEmailAvailable(email);
		return new UserIdentityAvailability(isAvailable);
	}

	/**
	 * Rest Controller to get a user profile by id
	 * 
	 * @return ResponseEntity with a UserRestDTO if email is valid, otherwise return
	 *         NOT_FOUND
	 */
	@GetMapping("/users/{email}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<UserRestDTO> getUserProfile(@PathVariable(value = "email") String email) {

		User user = userService.findUserByEmail(email);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user.toRestDTO(), HttpStatus.OK);
	}

	/**
	 * Rest Controller to get all registered users
	 * 
	 * @return ResponseEntity with a list of UserRestDTO
	 */
	@GetMapping("/users")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<UserRestDTO>> listAllUsers() {

		List<UserRestDTO> usersList = new ArrayList<>();
		for (User u : userService.listAllUsers()) {
			usersList.add(u.toRestDTO());
		}

		return new ResponseEntity<>(usersList, HttpStatus.OK);
	}

	/**
	 * Rest Controller to get all active users
	 * 
	 * @return ResponseEntity with a list of UserRestDTO
	 */
	@GetMapping("/users/active")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<UserRestDTO>> listActiveUsers() {

		List<UserRestDTO> usersList = new ArrayList<>();
		for (User u : userService.listActiveUsers()) {
			usersList.add(u.toRestDTO());
		}

		return new ResponseEntity<>(usersList, HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get all inactive users
	 * 
	 * @return ResponseEntity with a list of UserRestDTO
	 */
	@GetMapping("/users/inactive")
	@PreAuthorize("hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<UserRestDTO>> listInactiveUsers() {

		List<UserRestDTO> usersList = new ArrayList<>();
		for (User u : userService.listInactiveUsers()) {
			usersList.add(u.toRestDTO());
		}

		return new ResponseEntity<>(usersList, HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to activate a specific user
	 * 
	 * @return ResponseEntity with updated profile of UserRestDTO and HttpStatus.ACCEPTED
	 */
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(value = "/users/{userId}/activate")
    public ResponseEntity<UserRestDTO> activateUser(@PathVariable("userId") String userId) {
    	User user = userService.findUserByEmail(userId);
        if ( user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);     
        userService.setUserActive(user);     
        UserRestDTO userOutDTO = userService.findUserByEmail(userId).toRestDTO();  
        return new ResponseEntity<>(userOutDTO,HttpStatus.ACCEPTED);

    }
	
	
	/**
	 * Rest Controller to deactivate a specific user
	 * 
	 * @return ResponseEntity with updated profile of UserRestDTO and HttpStatus.ACCEPTED
	 */
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(value = "/users/{userId}/deactivate")
    public ResponseEntity<UserRestDTO> deactivateUser(@PathVariable("userId") String userId) {
    	User user = userService.findUserByEmail(userId);
        if ( user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);     
        userService.deactivateUser(user);     
        UserRestDTO userOutDTO = userService.findUserByEmail(userId).toRestDTO();  
        return new ResponseEntity<>(userOutDTO,HttpStatus.ACCEPTED);

    }
	
	/**
     * Rest Controller to change role profile of a specific user
     *
     * @param userId (email)
     * @param userRestDTO
     * @return response code ACCEPTED if profile is valid, otherwise return BAD_REQUEST
     */
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(value = "/users/{userId}/profile")
    public ResponseEntity<UserRestDTO> setUserProfile(@PathVariable("userEmail") String userId, @RequestBody UserRestDTO userDTO) {
		UserRestDTO userOutDTO;
        String profile = userDTO.getProfile();
        
        if (userService.findUserByEmail(userId) == null)
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        if (profile == null)
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        switch (profile.toUpperCase()) {

            case "ADMINISTRATOR":
                userService.setUserProfileAdmin(userService.findUserByEmail(userId));
                userOutDTO = userService.findUserByEmail(userId).toRestDTO();
                return new ResponseEntity<>(userOutDTO, HttpStatus.ACCEPTED);

            case "STOREMANAGER":
                userService.setUserProfileStoreManager(userService.findUserByEmail(userId));
                userOutDTO = userService.findUserByEmail(userId).toRestDTO();
                return new ResponseEntity<>(userOutDTO, HttpStatus.ACCEPTED);

            case "EMPLOYER":
                userService.setUserProfileEmployer(userService.findUserByEmail(userId));
                userOutDTO = userService.findUserByEmail(userId).toRestDTO();
                return new ResponseEntity<>(userOutDTO, HttpStatus.ACCEPTED);

            default:
            	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

package bsmanagement.controllers.rest;

import java.time.LocalDate;
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
import bsmanagement.dto.rest.UserSummaryRestDTO;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.payload.UserIdentityAvailability;
import bsmanagement.security.CurrentUser;
import bsmanagement.security.UserPrincipal;

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
	 * Rest Controller to get credentials of current user
	 * 
	 * @return UserSummaryDTO with credentials of current logged user
	 */
	@GetMapping("/users/me")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public UserSummaryRestDTO getCurrentUser(@CurrentUser UserPrincipal currentUser) {

		return new UserSummaryRestDTO(currentUser.getEmail(), currentUser.getName(),
				currentUser.getAuthorities().toString());
	}
	
	/**
	 * Rest Controller to edit personal data of current user
	 * 
	 * @return UserRestDTO with updated data of current logged user and Response ACCEPTED if all data inputed is valid. If birthdate
	 * inputed is invalid or no valid field was really changed, then return BAD_REQUEST
	 */
	@PutMapping("/users/me")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<UserRestDTO> editPersonalData(@CurrentUser UserPrincipal currentUser, @RequestBody UserRestDTO userInDTO) {

		User user = userService.findUserByEmail(currentUser.getEmail());
		boolean fieldChanged = false;
		
        
        if (userInDTO.getBirth() != null && userInDTO.getBirth().isAfter(LocalDate.now()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	
        if (userInDTO.getPhone() != null){
        	user.setPhone(userInDTO.getPhone());
        	fieldChanged = true;
        }        	
		
        if ( userInDTO.getTaxPayerId()!=null){
        	user.setTaxPayerId(userInDTO.getTaxPayerId());
        	fieldChanged = true;
        }        	
        
        if ( userInDTO.getName()!= null){
        	user.setName(userInDTO.getName());
        	fieldChanged = true;
        }       	
        
        if ( userInDTO.getBirth()!= null){
        	user.setBirth(userInDTO.getBirth());
        	fieldChanged = true;
        }
        
        if (fieldChanged) {
        	userService.updateUser(user);
        	UserRestDTO userOutDTO = userService.findUserByEmail(currentUser.getEmail()).toDTO();
        	return new ResponseEntity<>(userOutDTO, HttpStatus.ACCEPTED);
        }
        	
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

		return new ResponseEntity<>(user.toDTO(), HttpStatus.OK);
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
			usersList.add(u.toDTO());
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
			usersList.add(u.toDTO());
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
			usersList.add(u.toDTO());
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
        UserRestDTO userOutDTO = userService.findUserByEmail(userId).toDTO();  
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
        UserRestDTO userOutDTO = userService.findUserByEmail(userId).toDTO();  
        return new ResponseEntity<>(userOutDTO,HttpStatus.ACCEPTED);

    }

}

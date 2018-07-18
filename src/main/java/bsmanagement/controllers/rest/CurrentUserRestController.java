package bsmanagement.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bsmanagement.dto.rest.UserRestDTO;
import bsmanagement.dto.rest.UserSummaryRestDTO;
import bsmanagement.exception.AppException;
import bsmanagement.model.User;
import bsmanagement.model.UserService;
import bsmanagement.model.jparepositories.RoleRepository;
import bsmanagement.model.jparepositories.UserRepository;
import bsmanagement.model.roles.Role;
import bsmanagement.model.roles.RoleName;
import bsmanagement.payload.ApiResponse;
import bsmanagement.payload.JwtAuthenticationResponse;
import bsmanagement.payload.LoginRequest;
import bsmanagement.payload.SignUpRequest;
import bsmanagement.security.CurrentUser;
import bsmanagement.security.JwtTokenProvider;
import bsmanagement.security.UserPrincipal;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;


@RestController
@RequestMapping("/api")
public class CurrentUserRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    
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
}

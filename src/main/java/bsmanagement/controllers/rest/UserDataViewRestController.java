package bsmanagement.controllers.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.UserRestDTO;
import bsmanagement.model.User;
import bsmanagement.model.UserService;

@RestController
@RequestMapping("/api")
public class UserDataViewRestController {

	@Autowired
	UserService userService;

	protected UserDataViewRestController() {
		
	}
	
	@RequestMapping("/users")
	HttpEntity<List<UserRestDTO>> listAllUsers()
	{
		List<UserRestDTO> users = new ArrayList<>();
		for (User u : userService.listActiveUsers())
		{
			users.add(u.toDTO());
		}
		addSelfLinkToEachElement(users);
		return new ResponseEntity<>(users,HttpStatus.OK);
		
	}
	
	
	
	
//	@RequestMapping("/users")
//	List<User> listUsersByStatus(@RequestParam(value = "status", required = false, defaultValue = "all") String status)
//	{
//		if (status.toUpperCase().equals("ACTIVE"))
//		{
//			return userService.listActiveUsers();
//		}
//		if (status.toUpperCase().equals("INACTIVE"))
//		{
//			return userService.listInactiveUsers();
//		}
//		return userService.listAllUsers();
//		
//	}
	
	
	@RequestMapping("/users/{id}")
	HttpEntity<UserRestDTO> getUserByEmail(@PathVariable("id") String email)
	{
		UserRestDTO userOut = userService.findUserByEmail(email).toDTO();
		if (userOut == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userOut.add(linkTo(methodOn(UserDataViewRestController.class).getUserByEmail(email)).withSelfRel());
		return new ResponseEntity<>(userOut,HttpStatus.OK);
	}
	
	
	/**
     * Auxiliary method that adds HATEOAS self links to userDTO elements of a given list
     *
     * @param userList
     * @return list with self links added.
     */
    public static List<UserRestDTO> addSelfLinkToEachElement(List<UserRestDTO> userList) {

        for (UserRestDTO userDTO : userList) {
            userDTO.add(linkTo(methodOn(UserDataViewRestController.class).getUserByEmail(userDTO.getEmail()))
                    .withSelfRel());
        }
        return userList;
    }
	
	
}

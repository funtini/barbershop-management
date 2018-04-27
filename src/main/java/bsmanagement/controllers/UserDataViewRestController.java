package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.model.User;
import bsmanagement.model.UserService;

@RestController
public class UserDataViewRestController {

	@Autowired
	UserService userService;

	protected UserDataViewRestController() {
		
	}
	
	@RequestMapping("/users")
	List<User> listUsersByStatus(@RequestParam(value = "status", required = false, defaultValue = "all") String status)
	{
		if (status.toUpperCase().equals("ACTIVE"))
		{
			return userService.listActiveUsers();
		}
		if (status.toUpperCase().equals("INACTIVE"))
		{
			return userService.listInactiveUsers();
		}
		return userService.listAllUsers();
		
	}
	
	
	@RequestMapping("/users/{id}")
	User getUserByEmail(@PathVariable("id") String email)
	{
		return userService.findUserByEmail(email);
	}
	
	
}

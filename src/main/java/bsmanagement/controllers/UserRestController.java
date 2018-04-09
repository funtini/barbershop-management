package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.model.User;
import bsmanagement.model.UserService;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;

	protected UserRestController() {
		
	}
	
	@RequestMapping("/users")
	List<User> listAllUsers()
	{
		return userService.getUsersList();
	}
	
	
	
}

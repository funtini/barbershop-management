package bsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.UserRestDTO;
import bsmanagement.model.User;
import bsmanagement.model.UserService;

@RestController
public class UserDataManagementRestController {
	
	@Autowired
	UserService userService;

	protected void UserDataViewRestController() {
		
	}
	
//	@PutMapping("/users/{id}")
//	HttpEntity<User> editPersonalData(@PathVariable("id") String email, @RequestBody UserRestDTO userDTO)
//	{
//		User user = userService.findUserByEmail(email);
//		user.setName(userDTO.getName());
//		user.setBirth(userDTO.getBirth());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setPhone(userDTO.getPhone());
//		user.setTaxPayerId(userDTO.getTaxPayerId());
//		userService.updateUser(user);
//		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
//	}
//	
//	@PutMapping("/users/{id}")
//	HttpEntity<User> editBirthDate(@PathVariable("id") String email, @RequestBody UserRestDTO userDTO)
//	{
//		User user = userService.findUserByEmail(email);
//		user.setBirth(userDTO.getBirth());
//		userService.updateUser(user);
//		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
//	}
//	
//	@PutMapping("/users/{id}")
//	HttpEntity<User> editTaxPayerId(@PathVariable("id") String email, @RequestBody UserRestDTO userDTO)
//	{
//		User user = userService.findUserByEmail(email);
//		user.setTaxPayerId(userDTO.getTaxPayerId());
//		userService.updateUser(user);
//		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
//	}
//	
//	@PutMapping("/users/{id}")
//	HttpEntity<User> editPhone(@PathVariable("id") String email, @RequestBody UserRestDTO userDTO)
//	{
//		User user = userService.findUserByEmail(email);
//		user.setPhone(userDTO.getPhone());
//		userService.updateUser(user);
//		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
//	}
//	
////	@PutMapping("/users/{id}")
////	HttpEntity<User> editPhone(@PathVariable("id") String email, @RequestBody UserRestDTO userDTO)
////	{
////		User user = userService.getUserByEmail(email);
////		user.setPhone(userDTO.getPhone());
////		userService.updateUser(user);
////		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
////	}
	


	
	
	
	 
}

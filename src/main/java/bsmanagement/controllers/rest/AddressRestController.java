package bsmanagement.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.AddressRestDTO;
import bsmanagement.model.Address;
import bsmanagement.model.User;
import bsmanagement.model.UserService;

@RestController
public class AddressRestController {

	@Autowired
	private UserService userService;

	public AddressRestController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Rest Controller to add address to an user
	 * 
	 * @return AddressRestDTO and response Accepted, if addressDescription is null return BAD_REQUEST
	 */
	@PostMapping("/users/{userId}/address")
	public ResponseEntity<AddressRestDTO> addAddress(@PathVariable("userId") String userId, @RequestBody AddressRestDTO addressDTO) {

		User user = userService.findUserByEmail(userId);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (addressDTO.getAddressDescription() == null || addressDTO.getAddressDescription().isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Address address = user.createAddress(addressDTO.getAddressDescription(), addressDTO.getStreet(), addressDTO.getPostalCode(),
				addressDTO.getCity(), addressDTO.getCountry());
		user.addAddress(address);
		userService.updateUser(user);
		return new ResponseEntity<>(address.toRestDTO(),HttpStatus.CREATED);
	}
	
	
	/**
	 * Rest Controller to list all addresses to an user
	 * 
	 * @return List of AddressRestDTO and response code OK, if userId doesnt exists return NOT_FOUND
	 */
	@GetMapping("/users/{userId}/address")
	public ResponseEntity<List<AddressRestDTO>> listAddresses(@PathVariable("userId") String userId) {

		List<AddressRestDTO> addresses = new ArrayList<>();
		User user = userService.findUserByEmail(userId);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		for (Address a : user.getAddressList())
		{
			addresses.add(a.toRestDTO());
		}

		return new ResponseEntity<>(addresses,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to remove address of an user
	 * 
	 * @return AddressRestDTO and response code OK, if userId or addressId doesnt exists return NOT_FOUND
	 */
	@DeleteMapping("/users/{userId}/address/{addressId}")
	public ResponseEntity<AddressRestDTO> removeAddress(@PathVariable("userId") String userId,@PathVariable("addressId") int addressId) {

		User user = userService.findUserByEmail(userId);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		for (Address a : user.getAddressList())
		{
			if (a.getId()==addressId) {
				user.removeAddress(a);
				return new ResponseEntity<>(a.toRestDTO(),HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
//	/**
//	 * Rest Controller to edit address of an user
//	 * 
//	 * @return AddressRestDTO and response Accepted, if addressDescription is null return BAD_REQUEST
//	 */
//	@GetMapping("/users/{userId}/address")
//	public ResponseEntity<AddressRestDTO> editAddress(@PathVariable("userId") String userId) {
//
//		List<AddressRestDTO> addresses = new ArrayList<>();
//		User user = userService.findUserByEmail(userId);
//		if (user == null)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		for (Address a : user.getAddressList())
//		{
//			addresses.add(a.toRestDTO());
//		}
//
//		return new ResponseEntity<>(addresses,HttpStatus.OK);
//	}
	
}

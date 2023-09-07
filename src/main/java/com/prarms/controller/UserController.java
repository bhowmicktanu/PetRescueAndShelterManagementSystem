package com.prarms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prarms.dto.UserDTO;
import com.prarms.entity.User;
import com.prarms.service.UserService;
import com.prarms.util.UserConverter;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserConverter userConverter;
	
	
	@PostMapping("/saveUser")
	public UserDTO saveUser(@RequestBody UserDTO usDto) 
	{
		final User usr = userConverter.convertDtoToUsrEntity(usDto);
		return userService.saveUser(usr);
	}
	
	@GetMapping("/getAllUser")
	public List<UserDTO> getAllDetails(){
		return userService.getAllDetails();
	}
	
	@PutMapping("/updateUser/{id}")
	public UserDTO updateUser( @RequestBody UserDTO uDto,@PathVariable("id")int uId) {
	final User user = userConverter.convertDtoToUsrEntity(uDto);
	return userService.updateUser(user, uId);
	}
	@GetMapping("/getUserById/{id}")
	public UserDTO findUserById(@PathVariable("id")int id){
	return userService.getUserById(id);
	}

	@GetMapping("/getUserByName/{n}")
	public List<UserDTO> findUserByName(@PathVariable("n")String name){
	return userService.getUserByName(name);
	}

	@GetMapping("/getByEmail/{e}")
	public List<UserDTO> findtUserByEmail(@PathVariable("e")String email){
	return userService.getUserByEmail(email);
	}

	

	@GetMapping("/countOfUsers")
	public String countOfUsers() {
	String msg="Number of users available are :"+userService.countOfUser();

	return msg;
	}

	@GetMapping("/sortUserByName")
	public List<UserDTO> findAllSortedByName(){
	return userService.sortUserByName();
	}

	
	@PostMapping("/assignUser/{uId}/toPet/{pId}")
	public ResponseEntity<String> assignUserToPet(@PathVariable("uId") int uId, @PathVariable("pId") int pId) {

		userService.assignUserToPet(uId,pId);
		return new ResponseEntity<String>("User with id: " + uId + " adopted pet id " +pId,
				HttpStatus.OK);

	}
}
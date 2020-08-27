package com.sabrinafz.tasklistmanagerapi.controller.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabrinafz.tasklistmanagerapi.dto.user.UserDTO;
import com.sabrinafz.tasklistmanagerapi.entity.user.User;
import com.sabrinafz.tasklistmanagerapi.error.CustomNotFoundException;
import com.sabrinafz.tasklistmanagerapi.service.user.UserService;

@RestController
@RequestMapping("/users")
@Transactional
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<UserDTO> getUsers() {
		List<User> users = userService.getUsers();
		return users.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@PostMapping
	public UserDTO addUser(@RequestBody User user) throws Exception {

		User tempUser = userService.getUserByEmail(user.getEmail());
		
		if(tempUser != null) {
			throw new Exception("Email " + user.getEmail() + " exists!");
		}
		
		try {
			tempUser = userService.addUser(user);
			
		} catch (Exception exc) {
			throw new Exception(exc.getMessage());
		}
		
		return convertToDTO(tempUser);
	}

	@PutMapping("/{id}")
	public UserDTO updateUser(@PathVariable int id, @RequestBody User user) throws Exception {

		User tempUser = userService.getUserById(id);

		if (tempUser == null) {
			throw new CustomNotFoundException("User with id " + id + " do not exist");
		}
		
		try {
			tempUser = userService.updateUser(id, user);
		} catch (Exception exc) {
			throw new Exception(exc.getMessage());
		}
		
		return convertToDTO(tempUser);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) throws Exception {

		User tempUser = userService.getUserById(id);

		if (tempUser == null) {
			throw new CustomNotFoundException("User with id " + id + " do not exist");
		}
		
		try {
			userService.deleteUser(id);
		} catch (Exception exc) {
			throw new Exception(exc.getMessage());
		}
		

		return new ResponseEntity<>("User with id " + id + " deleted", HttpStatus.NO_CONTENT);
	}

	private UserDTO convertToDTO(User user) {
		return modelMapper.map(user, UserDTO.class);		
	}

}

package com.sabrinafz.tasklistmanagerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sabrinafz.tasklistmanagerapi.dao.UserRepository;
import com.sabrinafz.tasklistmanagerapi.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User addUser(User user) {
		
		User userTemp = new User();
		
		userTemp.setFirstName(user.getFirstName());
		userTemp.setLastName(user.getLastName());
		userTemp.setEmail(user.getEmail());
		userTemp.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(userTemp);
		
		return userTemp;
	}
	
	@Override
	public User getUserById(int id) {
		
		User userTemp = userRepository.findById(id);
		
		return userTemp;
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public User updateUser(int id, User user) {
		
		User userTemp = userRepository.findById(id);
						
		userTemp.setPassword(user.getPassword() != null ? passwordEncoder.encode(user.getPassword()) : userTemp.getPassword());
		userTemp.setEmail(user.getEmail() != null ? user.getEmail() : userTemp.getEmail());
		userTemp.setFirstName(user.getFirstName() != null ? user.getFirstName() : userTemp.getFirstName());
		userTemp.setLastName(user.getLastName() != null ? user.getLastName() : userTemp.getLastName());
		
		return userRepository.save(userTemp);
	}

	@Override
	public void deleteUser(int id) {
		
		User userTemp = userRepository.findById(id);		
		
		userRepository.delete(userTemp);
		
	}

}

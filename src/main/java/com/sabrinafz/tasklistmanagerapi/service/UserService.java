package com.sabrinafz.tasklistmanagerapi.service;

import java.util.List;
import java.util.Optional;

import com.sabrinafz.tasklistmanagerapi.entity.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public User addUser(User user);
	
	public User getUserByEmail(String email);
	
	public User updateUser(int id, User user);

	public User getUserById(int id);
	
	public void deleteUser(int id);

}

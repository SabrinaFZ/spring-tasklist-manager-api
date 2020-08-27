package com.sabrinafz.tasklistmanagerapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabrinafz.tasklistmanagerapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByEmail(String email);
	
	public User findById(int id);
	
}

package com.sabrinafz.tasklistmanagerapi.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabrinafz.tasklistmanagerapi.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByEmail(String email);
	
	public User findById(int id);
	
}

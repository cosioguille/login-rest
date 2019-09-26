package com.cosioguille.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.cosioguille.model.User;

public interface UserService {
	
   public List<User> getUsers();
   
   public User saveUser(User user);
 
   public User getUser(int id);
   
   public void updateUser(int id, User user);
 
   public void deleteUser(int id);
   
   public boolean existsUser(int id);
   
   public boolean existsUserByUsername(String username);
   
   public boolean authenticate(User user);

}
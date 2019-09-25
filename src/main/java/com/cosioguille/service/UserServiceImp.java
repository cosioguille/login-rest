package com.cosioguille.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cosioguille.model.User;
import com.cosioguille.repository.UserRepository;
import com.cosioguille.service.UserService;

@Service
@Transactional
public class UserServiceImp implements UserService {
 
   @Autowired
   private UserRepository userRepository;
 
   @Override
   public List<User> getUsers() {
      return userRepository.findAll();
   }
 
   @Override
    public User saveUser(User user) {
       return (User) userRepository.save(user);
    }
   
   @Override
    public User getUser(int id) {
       return userRepository.findById(id).get();
    }
 
    @Override
    public void updateUser(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }
   
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public boolean existsUser(int id) {
    	return userRepository.existsById(id);
    }
    
    @Override
    public boolean existsUserByUsername(String username) {
    	return userRepository.existsUserByUsername(username);
    }

	@Override
	public boolean authenticate(User user) {
		return userRepository.authenticate(user.getUsername(), user.getPassword());
	}
}
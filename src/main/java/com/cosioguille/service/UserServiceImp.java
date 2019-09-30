package com.cosioguille.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cosioguille.model.User;
import com.cosioguille.repository.UserRepository;
import com.cosioguille.service.UserService;

@Service(value = "userService")
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {
 
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return authorities;
	}
}
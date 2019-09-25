package com.cosioguille.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cosioguille.model.User;
import com.cosioguille.pojo.StringResponse;
import com.cosioguille.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
 
   @Autowired
   private UserService userService;
 
   @RequestMapping(value = "", method = RequestMethod.GET)
   public ResponseEntity<?> getUsers()
   {  
      List<User> users = userService.getUsers();
      
      if(users.isEmpty()) {
    	  
    	  return new ResponseEntity<>("There is no users in the database yet!", HttpStatus.NOT_FOUND);
    	  
      } else {
    	  
          return new ResponseEntity<>(users, HttpStatus.OK);
          
      }
   }
   
   @RequestMapping(value = "", method = RequestMethod.POST)
   public ResponseEntity<?> saveUser(@RequestBody User user)
   {
	   user.setId(0);
	   
	   String error = "";
	   StringResponse message = new StringResponse("");
	   HttpStatus status = HttpStatus.OK;
	   
	   if(user.getUsername() == null) {
		   
		   error += "\nUser's username cannot be empty!";
		   
	   } else if (user.getUsername().length() > 128) {
		   
		   error += "\nUser's username cannot be longer than 128 characters!";
		   
	   } else if (user.getUsername().length() < 4) {
		   
		   error += "\nUser's username cannot be shorter than 4 characters!";
		   
	   } else if (userService.existsUserByUsername(user.getUsername())) {
		   
		   message.setResponse("Username already taken!");
		   status = HttpStatus.OK;
		   return new ResponseEntity<>(message, status);
		   
	   }
	   
	   if(user.getPassword() == null) {
		   
		   error += "\nUser's password cannot be empty!";
		   
	   } else if (user.getPassword().length() > 128) {
		   
		   error += "\nUser's password cannot be longer than 128 characters!";
		   
	   } else if (user.getPassword().length() < 8) {
		   
		   error += "\nUser's password cannot be shorter than 8 characters!";
		   
	   }
	   
	   if(user.getRol() == null) {
		   
		   error += "\nUser's rol cannot be empty!";
		   
	   }
	   
	   if(!error.equals("")) {
		   
		   message.setResponse(String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error));
		   status = HttpStatus.BAD_REQUEST;
	   
	   } else {
		   
		   try {
			   
			   User saved = userService.saveUser(user);
			   message.setResponse(String.format("New user %s created!", saved.getUsername()));
			   status = HttpStatus.OK;
			   
		   } catch (Exception ex) {
			   
			   message.setResponse(String.format("[INTERNAL SERVER ERROR] \n%s", ex));
			   status = HttpStatus.INTERNAL_SERVER_ERROR;
			   
		   }
		   
	   }
	   
	   return new ResponseEntity<>(message, status);
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> getUser (@PathVariable int id)
   {
	   try {
		   
		   User user = userService.getUser(id);
		   return new ResponseEntity<>(user, HttpStatus.OK);
		   
	   } catch (NoSuchElementException ex) {
		   
		   String message = String.format("[BAD REQUEST] User with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
       
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user)
   {
	   user.setId(id);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(user.getUsername() == null) {
		   
		   error += "\nUser's username cannot be empty!";
		   
	   } else if (user.getUsername().length() > 128) {
		   
		   error += "\nUser's username cannot be longer than 128 characters!";
		   
	   } else if (user.getUsername().length() < 8) {
		   
		   error += "\nUser's username cannot be shorter than 8 characters!";
		   
	   }
	   
	   if(user.getPassword() == null) {
		   
		   error += "\nUser's password cannot be empty!";
		   
	   } else if (user.getPassword().length() > 128) {
		   
		   error += "\nUser's password cannot be longer than 128 characters!";
		   
	   } else if (user.getPassword().length() < 8) {
		   
		   error += "\nUser's password cannot be shorter than 8 characters!";
		   
	   }
	   
	   if(user.getRol() == null) {
		   
		   error += "\nUser's rol cannot be empty!";
		   
	   }
	   
	   if(!error.equals("")) {
		   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
	   
	   } else {
		   
		   if(!userService.existsUser(id)) {
			   
			   message = String.format("[BAD REQUEST] User with the Id: %s do not exist in the database!", id);
			   status = HttpStatus.BAD_REQUEST;
			   
		   } else {
		   
			  try {
				  
				  userService.updateUser(id, user);
				  message = String.format("User with the Id: %s successfully updated!\n%s", id, user);
			      status = HttpStatus.OK;
				   
			  } catch (Exception ex){
				  
				   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
				   status = HttpStatus.INTERNAL_SERVER_ERROR;
				   
			  }
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> deleteUser (@PathVariable int id)
   {
	   try {
		   
		   userService.deleteUser(id);
		   String message = String.format("User with the Id: %s successfully erased!", id);
		   return new ResponseEntity<>(message, HttpStatus.OK);
		   
	   } catch (EmptyResultDataAccessException ex) {
		   
		   String message = String.format("[BAD REQUEST] User with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
   }
}

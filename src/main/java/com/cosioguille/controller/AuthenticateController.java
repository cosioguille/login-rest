package com.cosioguille.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cosioguille.model.User;
import com.cosioguille.pojo.StringResponse;
import com.cosioguille.service.UserService;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> authenticate(@RequestBody User user) {
		
		return new ResponseEntity<>(userService.authenticate(user), HttpStatus.OK);
		
	}

}
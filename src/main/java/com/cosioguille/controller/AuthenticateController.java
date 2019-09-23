package com.cosioguille.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AuthenticateController {
	
	  @RequestMapping(value = {"authenticate{username}{password}"}, method = RequestMethod.POST)
	  public String getLoginPage(@PathVariable String username, @PathVariable String password) {
		  return "LOGGED Username: "+username+" Password: "+password;
	  }

}
package com.cosioguille.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class IndexController {
 
  @RequestMapping(value = {"login"}, method = RequestMethod.GET)
  public String getLoginPage() {
	  return "login";
  }
  
  @RequestMapping(value = {"register"}, method = RequestMethod.GET)
  public String getRegisterPage() {
	  return "register";
  }
  
  @RequestMapping(value = {"admin"}, method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('ADMIN')")
  public String getAdminPage() {
	  return "admin";
  }
  
  @RequestMapping(value = {"user"}, method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STANDARD')")
  public String getUserPage() {
	  return "user";
  }
 
}
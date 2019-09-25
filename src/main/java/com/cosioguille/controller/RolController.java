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
 
import com.cosioguille.model.Rol;
import com.cosioguille.service.RolService;

@RestController
@RequestMapping("/roles")
public class RolController {
 
   @Autowired
   private RolService rolService;
 
   @RequestMapping(value = "", method = RequestMethod.GET)
   public ResponseEntity<?> getRols()
   {  
      List<Rol> roles = rolService.getRoles();
      
      if(roles.isEmpty()) {
    	  
    	  return new ResponseEntity<>("There is no rols in the database yet!", HttpStatus.NOT_FOUND);
    	  
      } else {
    	  
          return new ResponseEntity<>(roles, HttpStatus.OK);
      }
   }
   
   @RequestMapping(value = "", method = RequestMethod.POST)
   public ResponseEntity<String> saveRol(@RequestBody Rol rol)
   {
	   
	   rol.setId(0);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(rol.getName() == null) {
		   
		   error += "\nRol's name cannot be empty!";
		   
	   } else if (rol.getName().length() > 128) {
		   
		   error += "\nRol's name cannot be longer than 128 characters!";
		   
	   } else {
		   
		   if(!error.equals("")) {
			   
			   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
			   status = HttpStatus.BAD_REQUEST;
			   
		   } else {
		   
			   try {
			   
				   Rol saved = rolService.saveRol(rol);
				   message = String.format("New rol added correctly!\n%s", saved);
				   status = HttpStatus.OK;
				   
			   } catch (Exception ex) {
				   
				   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
				   status = HttpStatus.INTERNAL_SERVER_ERROR;
				   
			   }
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
	   
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> getRol (@PathVariable int id)
   {
	   try {
		   
		   Rol rol = rolService.getRol(id);
		   return new ResponseEntity<>(rol, HttpStatus.OK);
		   
	   } catch (NoSuchElementException ex) {
		   
		   String message = String.format("[BAD REQUEST] Rol with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
       
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<?> updateRol(@PathVariable int id, @RequestBody Rol rol)
   {
	   rol.setId(id);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(rol.getName() == null) {
		   
		   error += "\nRol's name cannot be empty!";
		   
	   } else if (rol.getName().length() > 128) {
		   
		   error += "\nRol's name cannot be longer than 128 characters!";
		   
	   } else {
		   
		   if(!error.equals("")) {
			   
			   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
			   status = HttpStatus.BAD_REQUEST;
			   
		   } else {
			   
			   if(!rolService.existsRol(id)) {
				   
				   message = String.format("[BAD REQUEST] Rol with the Id: %s do not exist in the database!", id);
				   status = HttpStatus.BAD_REQUEST;
				   
			   } else {
		   
				  try {
					  
					  rolService.updateRol(id, rol);
					  message = String.format("Rol with the Id: %s successfully updated!\n%s", id, rol);
				      status = HttpStatus.OK;
					   
				  } catch (Exception ex){
					  
					   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
					   status = HttpStatus.INTERNAL_SERVER_ERROR;
					   
				  }
			   }
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
	   
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> deleteRol (@PathVariable int id)
   {
	   try {
		   
		   rolService.deleteRol(id);
		   
		   try {
			   
			   rolService.getRol(id);
			   String message = String.format("[BAD REQUEST] Rol with the Id: %s cannot be erased because is related to a plan!", id);
			   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			   
		   } catch (Exception ex) {
			   
			   String message = String.format("Rol with the Id: %s successfully erased!", id);
			   return new ResponseEntity<>(message, HttpStatus.OK);
			   
		   }
		   
	   } catch (EmptyResultDataAccessException ex) {
		   
		   String message = String.format("[BAD REQUEST] Rol with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
   }
}
angular.module('myApp').controller('UserController', ['$scope', '$window', 'UserService',
	function($scope, $window, UserService) {
	    var self = this;
	    self.users;
	    self.error;
	    self.usernameLogin;
	    self.passwordLogin;
	    self.usernameRegister;
	    self.passwordRegister;
	    self.confirmPasswordRegister;
	    
	    //Methods
	    self.init = init;
	    self.getUsers = getUsers;
	    self.goLogin = goLogin;
	    self.goRegister = goRegister;
	    self.login = login;
	    self.register = register;
	    
	    //Reset controller
	    function init(){
	    	self.error = "";
	    	self.usernameLogin = "";
	    	self.passwordLogin = "";
		    self.usernameRegister = "";
		    self.passwordRegister = "";
		    self.confirmPasswordRegister = "";
	    }
	    
	    function getUsers(){
	        UserService.getUsers()
	            .then(
	            function(response){
	                self.users = response;
	            },
	            function(errResponse){
	                console.error("Error while getting Users");
	            }
	        );
	    }
	    
	    function goLogin(){
	    	$window.location.href = 'login';
	    }
	    
	    function goRegister(){
	    	$window.location.href = 'register';
	    }
	    
	    function login(){
	    	
	    	self.error = "";
	    		
	    	if(!self.usernameLogin){
	    		self.error += "Username cannot be empty! ";
	    	} else if (self.usernameLogin.length < 5){
	    		self.error += "Username cannot be less than 5 characters! ";
	    	}
	    	
	    	if(!self.passwordLogin){
	    		self.error += "Password cannot be empty! ";
	    	} else if (self.passwordLogin.length < 8){
	    		self.error += "Password cannot be less than 8 characters! ";
	    	}
	    	
	    	if(!self.error){
	    		//Login Service
	    		UserService.login(self.usernameLogin, self.passwordLogin)
	            .then(
	            function(response){
		    		console.log("Login Valid");
		    		console.log(response);
	            },
	            function(errResponse){
	                console.error("Error while getting Users");
	            }
	        );
	    	}
	    }
	    
	    function register(){
	    	
	    	self.error = "";
	    	
	    	if(!self.usernameRegister){
	    		self.error += "Username cannot be empty! ";
	    	} else if (self.usernameRegister.length < 5){
	    		self.error += "Username cannot be less than 5 characters! ";
	    	}
	    	
	    	if(!self.passwordRegister){
	    		self.error += "Password cannot be empty! ";
	    	} else if (self.passwordRegister.length < 8){
	    		self.error += "Password cannot be less than 8 characters! ";
	    	} else if(!self.confirmPasswordRegister){
	    		self.error += "Please confirm your password! ";
	    	} else if (self.passwordRegister != self.confirmPasswordRegister){
	    		self.error += "Password confirmation is not the same! ";
	    	}
	    	
	    	if(!self.error){
	    		//Register Service
	    		console.log("Register Valid");
	    	}
	    	
	    }
	}
]);
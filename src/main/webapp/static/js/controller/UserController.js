angular.module('myApp').controller('UserController', ['$scope', '$window', 'UserService',
	function($scope, $window, UserService) {
	    var self = this;
	    self.users;
	    self.alert;
	    self.user;
	    self.userRegister;
	    self.confirmPassword;
	    
	    //Methods
	    self.init = init;
	    self.getUsers = getUsers;
	    self.goLogin = goLogin;
	    self.goRegister = goRegister;
	    self.login = login;
	    self.register = register;
	    
	    //Reset controller
	    function init(){
	    	self.alert = '';
	    	self.user = {id: 0, username: '', password: ''};
	    	self.userRegister = { id: 0, username: '', password: ''};
		    self.confirmPassword = '';
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
	    	
	    	self.alert = "";
	    		
	    	if(!self.user.username){
	    		self.alert += "Username cannot be empty! ";
	    	} else if (self.user.username.length < 5){
	    		self.alert += "Username cannot be less than 5 characters! ";
	    	}
	    	
	    	if(!self.user.password){
	    		self.alert += "Password cannot be empty! ";
	    	} else if (self.user.password.length < 8){
	    		self.alert += "Password cannot be less than 8 characters! ";
	    	}
	    	
	    	if(!self.alert){
	    		//Login Service
	    		UserService.login(self.user)
	            .then(
	            function(response){
	            	if(response){
	            		//Correct Authenticate
	            		self.alert += "Login Successful!";
	            	} else {
	            		self.alert += "Incorrect Login!";
	            	}
	            },
	            function(errResponse){
	                console.error("Error on Login: " + errResponse);
	            }
	        );
	    	}
	    }
	    
	    function register(){
	    	
	    	self.alert = "";
	    	
	    	if(!self.userRegister.username){
	    		self.alert += "Username cannot be empty! ";
	    	} else if (self.userRegister.username.length < 5){
	    		self.alert += "Username cannot be less than 5 characters! ";
	    	}
	    	
	    	if(!self.userRegister.password){
	    		self.alert += "Password cannot be empty! ";
	    	} else if (self.userRegister.password.length < 8){
	    		self.alert += "Password cannot be less than 8 characters! ";
	    	} else if(!self.confirmPassword){
	    		self.alert += "Please confirm your password! ";
	    	} else if (self.userRegister.password != self.confirmPassword){
	    		self.alert += "Password confirmation is not the same! ";
	    	}
	    	
	    	if(!self.alert){
	    		//Register Service
	    		UserService.register(self.userRegister)
	            .then(
	            function(response){
		    		self.alert += response;
	            },
	            function(errResponse){
	            	console.error("Error on Register: " + errResponse);
	            }
            );
	    	}
	    }
	}
]);
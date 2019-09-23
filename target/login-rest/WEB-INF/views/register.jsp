<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	
    	<title>Register</title>
      	<meta charset="UTF-8">
       	<link href="<c:url value='/static/css/app.css'/>" rel="stylesheet"></link>
       	
	</head>
	
	<body ng-app="myApp">
	
		<h1>REGISTER</h1>
        
    	<div class="generic-container" ng-controller="UserController as ctrl" ng-init="ctrl.init()">
			<form name="register">
				Username: <input type="text" ng-model="ctrl.usernameRegister" placeholder="Username" required/>
				<br>
				Password: <input type="password" ng-model="ctrl.passwordRegister" placeholder="Password" required/>
				<br>
				Confirm Password: <input type="password" ng-model="ctrl.confirmPasswordRegister" placeholder="Confirm Password" required/>
				<br>
            	<button type="button" ng-click="ctrl.register()">Register</button>
            	<br>
            	<button type="button" ng-click="ctrl.goLogin()">Cancel</button>
            	<br>
            	<p ng-hide="ctrl.error == ''">{{ctrl.error}}</p>
			</form>
		</div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-sanitize.js"></script>
        <script src="<c:url value='/static/js/App.js'/>"></script>
        <script src="<c:url value='/static/js/service/UserService.js'/>"></script>
        <script src="<c:url value='/static/js/controller/UserController.js'/>"></script>
        
	</body>
</html>
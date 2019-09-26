angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){
 
    var BODY = 'users/'
 
    var factory = {
		getUsers: getUsers,
		login: login,
		register: register
    };
 
    return factory;
    
    function getUsers(){
        
        var deferred = $q.defer();
        
        $http.get(REST_SERVICE_URI+BODY).
            then(
                function (response){
                    deferred.resolve(response.data);
                },
                function (error){
                    deferred.reject(error);
                }
        );
        
        return deferred.promise;
        
    }
    
    function login(user){
    	
    	var deferred = $q.defer();
        
        $http.post(REST_SERVICE_URI+'authenticate', user).
            then(
                function (response){
                    deferred.resolve(response.data);
                },
                function (error){
                    deferred.reject(error);
                }
        );
        
        return deferred.promise;
    	
    }
    
    function register(user){
    	
    	var deferred = $q.defer();
        
        $http.post(REST_SERVICE_URI+BODY, user).
            then(
                function (response){
                    deferred.resolve(response.data.response);
                },
                function (error){
                    deferred.reject(error);
                }
        );
        
        return deferred.promise;
    	
    }

}]);
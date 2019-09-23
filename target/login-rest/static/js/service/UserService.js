angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){
 
    var BODY = 'users/'
 
    var factory = {
		getUsers: getUsers,
		login: login
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
    
    function login(user, pass){
    	
    	var deferred = $q.defer();
        
        $http.get(REST_SERVICE_URI+'authenticate',
        		{params: {username: user, password: pass} }).
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

}]);
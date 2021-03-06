var app = angular.module('demoApp', [ 'ngRoute' ]);


app.config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
      templateUrl : 'blogger.html',
      controller : 'home',
      controllerAs: 'controller'
    }).when('/login.do', {
      templateUrl : 'login.html',
      controller : 'navigation',
      controllerAs: 'controller'
    }).when('/users.do', {
        templateUrl : 'users.html',
        controller : 'users',
        controllerAs: 'controller'
    }).when('/register.do', {
        templateUrl : 'user-register.html',
        controller : 'register',
        controllerAs: 'controller'
    }).when('/account.do', {
        templateUrl : 'account.html',
        controller : 'account',
        controllerAs: 'controller'
    }).when('/logout.do', {
        templateUrl : 'logout.html',
        controller : 'logout',
        controllerAs: 'controller'
    })
    .otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  })
  //home controller
  .controller('home', function($rootScope, $http) {
    var self = this;
    $http.get('/resource').then(function(response) {
      self.greeting = response.data;
    })
    
    $rootScope.current = 'blogger';
    
  })
  
  //users controller
  .controller('users', function($rootScope, $http) {
    var self = this;
    $rootScope.current = 'users';    
  })
  
  .controller('register', function($rootScope, $scope, $http, $location,  $timeout) {
    var self = this;
    $rootScope.current = 'user-register';  
    $scope.registerForm = {};
    $scope.actionStatus = "New";
    $scope.saveUser = function(){    	 
    	 $http.post('/saveUser', $scope.registerForm ).then(function(response) {
    		 if(response.data== true){
    			 $scope.actionStatus = "Successful";
    			 $scope.registerForm = {};
    			 //$location.path("/register.do");
    		 }else {
    			 $scope.registerForm = {};
    			 $scope.actionStatus = "UnSuccessful";    			 
    		 }
    	 }), function (response) { // optional
             // failed
             console.log('failed');
             console.log(JSON.stringify(response));
         }
    }
  })
  
  .controller('account', function($rootScope, $http) {
    var self = this;
    $rootScope.current = 'account';    
  })
  
  .controller('navigation',

  function($rootScope, $http, $location) {

  var self = this

  self.authenticationSuccessHandler= function(isAuthenticated) {
	  if(isAuthenticated){
		  $location.path("/home");
		  $rootScope.current = 'blogger';
	  }else {
		  $location.path("/login");
		  $rootScope.current = 'login';
	  }
	  
  }
  
  var authenticate = function(credentials, callback) {

    var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
    } : {};

    $http.get('user', {headers : headers}).then(function(response) {
      if (response.data.name) {
        $rootScope.authenticated = true;
      } else {
        $rootScope.authenticated = false;
      }
      callback && callback($rootScope.authenticated);
    }, function() {
      $rootScope.authenticated = false;
      callback && callback($rootScope.authenticated);
    });

  };

  authenticate(undefined, self.authenticationSuccessHandler);
  self.credentials = {};
  self.login = function() {
      authenticate(self.credentials, function() {
        if ($rootScope.authenticated) {
          $location.path("/");
          self.error = false;
        } else {
          $location.path("/login");
          self.error = true;
        }
      });
  };
  

  self.logout = function() {
    $http.post('/logout', {}).finally(function() {
      $rootScope.authenticated = false;
      $location.path("/home");
    });
  };
  
 
  
});
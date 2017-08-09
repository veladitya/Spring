var app = angular.module('demoApp', [ 'ngRoute' ]);


app.config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
      templateUrl : 'home.html',
      controller : 'home',
      controllerAs: 'controller'
    }).when('/login', {
      templateUrl : 'login.html',
      controller : 'navigation',
      controllerAs: 'controller'
    }).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  })
  .controller('home', function($http) {
    var self = this;
    $http.get('/resource').then(function(response) {
      self.greeting = response.data;
    })
  })
  .controller('navigation',

  function($rootScope, $http, $location) {

  var self = this

  self.authenticationSuccessHandler= function(isAuthenticated) {
	  if(isAuthenticated){
		  $location.path("/home");
	  }else {
		  $location.path("/login");
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
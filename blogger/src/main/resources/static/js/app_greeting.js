/**
 * Sample AngularJS
 */
define(function(require) {
	'use strict';

	var angular = require('angular');

	var app = angular.module('demoApp', []);

	app.controller('demoController', function($http) {
		var self = this;
		$http.get('/resource').then(function(response) {
			self.greeting = response.data;
		})
	});

	app.init = function() {
		// angular.bootstrap(document, [ 'demoApp' ]);
		console.log("Application Initialized...!");
	};

	return app;
});
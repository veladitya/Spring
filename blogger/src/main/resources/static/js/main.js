/**
 * 
 */
require.config({
	paths : {
		'angular' : '../libraries/angular-1.5.7/angular',
		'angular-resource' : '../libraries/angular-1.5.7/angular-resource',
		'angular-route' : '../libraries/angular-1.5.7/angular-route'
	},
	shim : {
		'angular-resource' : {
			deps : [ 'angular' ],
			exports : 'angular'
		},
		'angular-route' : {
			deps : [ 'angular' ],
			exports : 'angular'
		},
		'angular' : {
			exports : 'angular'
		}
	},
	baseUrl : '/js'
});

require([ 'app' ], function(app) {
	app.init();
});
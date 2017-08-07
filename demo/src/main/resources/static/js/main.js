/**
 * 
 */
require.config({
	paths : {
		'angular' : '../libraries/angular-1.5.7/angular',
		'ngResource' : '../libraries/angular-1.5.7/angular-resource'
	},
	shim : {
		ngResource : {
			deps : [ 'angular' ],
			exports : 'angular'
		},
		angular : {
			exports : 'angular'
		}
	},
	baseUrl : '/js'
});

require([ 'app' ], function(app) {
	app.init();
});
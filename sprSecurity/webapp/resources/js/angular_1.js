var app = angular.module('myApp_service', []);

app.controller("Main_controller",
		[ "log_sout", "$scope", function($scope, log_sout) {
			$scope.name = "Marwan lazo".toUpperCase();
			console.log(log_sout(""));
		} ]).factory("log_sout", function() {
	return function(data) {
		if (data == null) {
			return "ERROR";
		} else {
			return " OK ";
		}
	}
});
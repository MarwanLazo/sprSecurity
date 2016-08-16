var app = angular.module('myApp_service', []);

app.controller("Main_controller", [ "$scope", function($scope) {
	$scope.name = "Marwan lazo".toUpperCase();

} ]);

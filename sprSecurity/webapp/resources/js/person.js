var app = angular.module('myApp_service', []);

app.controller("Main_controller", function($scope, $http) {

	// -------------- variables -------------------
	$scope.host = "http://" + window.location.hostname;
	$scope.person = {};

	// --------------- load All person --------------------
	$http.get($scope.host + ":7007/sprSecurity/rest/person").then(function(response) {
		console.log("Done !");
		$scope.personList = response.data;
	});

	$scope.submit = function() {

	}

});

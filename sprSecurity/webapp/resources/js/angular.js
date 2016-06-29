var app = angular.module('myApp', []);

app.run(function($rootScope) {
	$rootScope.color = 'blue';
});

app.controller('myCtrl', function($scope) {
	$scope.firstName = "John";
	$scope.lastName = "Doe";
	$scope.color = "red";
});

app.controller('myCtrl1', function($scope) {
	$scope.firstName = "John";
	$scope.lastName = "Doe";
});

app
		.directive(
				"w3TestDirective",
				function() {
					return {
						template : "<h1 style='color: green; text-decoration: underline;'>Made by a directive!</h1>"
					};
				});

app.controller('customersCtrl', function($scope, $http) {
	$scope.click = function() {
		$http.get("http://localhost:7007/sprSecurity/rest/temptable").then(
				function(response) {
					$scope.names = response.data;
				});
	};
});

app.controller("calc", function($scope) {
	$scope.quantity = 0;
	$scope.price = 0;
	$scope.click = function() {
		$scope.Total_in_dollar = $scope.quantity * $scope.price;

	};
});
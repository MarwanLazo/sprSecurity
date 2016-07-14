var app = angular.module('myApp', []);

app.run(function($rootScope) {
	$rootScope.color = 'blue';
});

app.controller('myCtrl', function($scope, $location, $http) {
	$scope.color = $location.absUrl();
	$scope.tempTable = {

	};
	$scope.submit = function() {
		var rs = JSON.stringify($scope.tempTable)
		console.log(rs);
		$http.post("http://localhost:7007/sprSecurity/rest/addtemptable/", rs)
				.success(function(rs, status, headers) {
					$scope.PostDataResponse = rs;
					console.log($scope.PostDataResponse);
					$scope.$emit('load_all_temptebles');
				}).error(
						function(rs, status, header) {
							$scope.ResponseDetails = "Data: " + rs
									+ "<br />status: " + status
									+ "<br />headers: " + header;
							console.log($scope.ResponseDetails);
						});

	}

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
		load_all_temptebles();
	};

	$scope.items = [ 'one', 'two', 'three', 'four' ]
	$scope.tempTable = {

	};
	$scope.submit = function() {
		var rs = JSON.stringify($scope.tempTable)
		console.log(rs);
		$http.post("http://localhost:7007/sprSecurity/rest/addtemptable/", rs)
				.success(function(rs, status, headers) {
					$scope.PostDataResponse = rs;
					console.log($scope.PostDataResponse);
					load_all_temptebles();
				}).error(
						function(rs, status, header) {
							$scope.ResponseDetails = "Data: " + rs
									+ "<br />status: " + status
									+ "<br />headers: " + header;
							console.log($scope.ResponseDetails);
						});

	}

	function load_all_temptebles() {
		$http.get("http://localhost:7007/sprSecurity/rest/temptable").then(
				function(response) {
					console.log("data");
					$scope.names = response.data;
					console.log($scope.names);
				});
	}

	$scope.deleteItem = function(item) {
		console.log(item);
		$.ajax({
			type : 'DELETE',
			url : 'http://localhost:7007/sprSecurity/rest/deleteTemptable/'
					+ item,
			success : function(data, textStatus, jqXHR) {
				alert('Wine deleted successfully');
				$scope.click();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('deleteWine error');
			}
		});

	}
});

app.controller("calc", function($scope) {
	$scope.quantity = 0;
	$scope.price = 0;
	$scope.click = function() {
		$scope.Total_in_dollar = $scope.quantity * $scope.price;

	};
});

// ------------------------------

app.controller('Test', function($scope) {
	$scope.items = [ 'one', 'two', 'three', 'four' ]
});
var app = angular.module('myApp', []);

app
		.directive(
				"w3TestDirective",
				function() {
					return {
						template : "<h1 style='color: green; text-decoration: underline;'>Made by a directive!</h1>"
					};
				});
// ====================================================
app.controller('customersCtrl', function($scope, $http) {

	$scope.tempTable = {};
	$scope.click = function() {
		load_all_temptebles();
	};

	$scope.submit = function() {
		console.log($scope.tempTable);
		if ($scope.tempTable.person != null) {
			$scope.tempTable.person = JSON.parse($scope.tempTable.person);
		}
		if ($scope.tempTable.tempRef != null) {
			$scope.tempTable.tempRef = JSON.parse($scope.tempTable.tempRef);
		}
		console.log($scope.tempTable);
		var rs = JSON.stringify($scope.tempTable);
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
			url : 'http://localhost:7007/sprSecurity/rest/deleteTemptable/'	+ item,
			success : function(data, textStatus, jqXHR) {
				load_all_temptebles();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('deleteWine error');
			}
		});
	}

	$http.get("http://localhost:7007/sprSecurity/rest/person").then(
			function(response) {
				console.log("data");
				$scope.person = response.data;
				console.log($scope.person);
			});
});

app.controller("calc", function($scope) {
	$scope.quantity = 0;
	$scope.price = 0;
	$scope.click = function() {
		$scope.Total_in_dollar = $scope.quantity * $scope.price;

	};
});

// ------------------------------

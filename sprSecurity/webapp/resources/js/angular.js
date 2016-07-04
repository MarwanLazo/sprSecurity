var app = angular.module('myApp', []);

app.run(function($rootScope) {
	$rootScope.color = 'blue';
});

/*app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.xsrfCookieName = 'csrftoken';
    $httpProvider.defaults.xsrfHeaderName = 'X-CSRFToken';
}]);
*/
app
		.controller(
				'myCtrl',
				function($scope, $location, $http) {
					$scope.color = $location.absUrl();
					$scope.tempTable = {
						tempTableName : "",
						tempEmail : " "
					};
					$scope.submit = function() {
						var rs = JSON.stringify($scope.tempTable)
						console.log(rs);
						$http
								.post(
										"http://localhost:7007/sprSecurity/rest/addtemptable/",
										rs)
								.success(function(rs, status, headers) {
									$scope.PostDataResponse = rs;
									console.log($scope.PostDataResponse);
								})
								.error(
										function(rs, status, header) {
											$scope.ResponseDetails = "Data: "
													+ rs + "<br />status: "
													+ status
													+ "<br />headers: "
													+ header;
											console.log($scope.ResponseDetails);
										});
						// var request = $http(
						// {
						// method : "post",
						// url :
						// "http://localhost:7007/sprSecurity/rest/addtemptable/",
						// headers : {
						// 'Content-Type' : 'application/x-www-form-urlencoded'
						// },
						// transformRequest : function(obj) {
						// var str = [];
						// for ( var p in obj)
						// str
						// .push(encodeURIComponent(p)
						// + "="
						// + encodeURIComponent(obj[p]));
						// return str.join("&");
						// },
						// data : rs
						// }).success(function(data) {
						// console.log(rs);
						// console.log("Done !");
						// }).error(function(data) {
						// console.log(rs);
						// console.log("errooooor!");
						// });

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
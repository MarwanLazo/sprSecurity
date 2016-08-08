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

		if ($scope.tempTable.createTime != null) {
			$scope.tempTable.createTime = new Date($scope.tempTable.createTime)
					.getTime();
		}
		var rs = JSON.stringify($scope.tempTable);

		$http.post("http://localhost:7007/sprSecurity/rest/addtemptable/", rs)
				.success(function(rs, status, headers) {
					$scope.PostDataResponse = rs;
					load_all_temptebles();
					$scope.tempTable = {};
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
					$scope.names = response.data;
				});
	}

	$scope.deleteItem = function(item) {
		console.log(item);
		$.ajax({
			type : 'DELETE',
			url : 'http://localhost:7007/sprSecurity/rest/deleteTemptable/'
					+ item,
			success : function(data, textStatus, jqXHR) {
				load_all_temptebles();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('deleteWine error');
			}
		});

		$scope.load_item = function(x) {
			alert("rotfgwedrfg" + x);
			console.log("clicked");
			console.log(x);
		};
	}

	$http.get("http://localhost:7007/sprSecurity/rest/person").then(
			function(response) {
				console.log("data");
				$scope.person = response.data;
				console.log($scope.person);
			});

	$scope.date_format = function date_format(milli_sec) {
		if (milli_sec == null) {
			return "";
		}
		var d = new Date(milli_sec);
		var datestring = d.getDate() + "/" + (d.getMonth() + 1) + "/"
				+ d.getFullYear();
		return datestring;
	};
});

// ------------------------------

app.controller(
		'DoubleController',
		[
				'$scope',
				'notify',
				function($scope, notify) {
					$scope.double = function(value) {
						return value * 2;
					};
					$scope.date_format = function date_format(milli_sec) {
						var d = new Date(milli_sec);
						var datestring = d.getDate() + "/" + (d.getMonth() + 1)
								+ "/" + d.getFullYear();
						return datestring;
					};

					$scope.callNotify = function() {
						$scope.css_class = notify($scope.name);
					}
				} ]).factory('notify', [ '$window', function(win) {
	return function(msg) {
		if (msg == null || msg.length < 3) {
			return "red";
		}
		return "";
	};
} ]);

// ================

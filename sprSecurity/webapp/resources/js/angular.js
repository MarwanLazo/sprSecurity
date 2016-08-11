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

	$scope.host = "http://" + window.location.hostname;
	$scope.tempTable = {};
	$scope.click = function() {
		load_all_temptebles();
	};

	$scope.submit = function() {
		
		if ($scope.tempTable.createTime != null) {
			if (!$.isNumeric($scope.tempTable.createTime)) {
				console.log($scope.tempTable.createTime);
				console.log(Date.parse($scope.tempTable.createTime));	
				$scope.tempTable.createTime = new Date($scope.tempTable.createTime).getTime();
				console.log($scope.tempTable.createTime);
			}
			
		}
		var rs = JSON.stringify($scope.tempTable);

		$http.post($scope.host + ":7007/sprSecurity/rest/addtemptable/", rs)
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
		$http.get($scope.host + ":7007/sprSecurity/rest/temptable").then(
				function(response) {
					$scope.names = response.data;
				});
	}

	$scope.deleteItem = function(item) {
		console.log(item);
		$.ajax({
			type : 'DELETE',
			url : $scope.host + ':7007/sprSecurity/rest/deleteTemptable/' + item,
			success : function(data, textStatus, jqXHR) {
				load_all_temptebles();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('deleteWine error');
			}
		});

	}

	$scope.loadItem = function(x) {
		
		$scope.tempTable = x;
		$scope.tempTable.createTime =   $.datepicker.formatDate('mm/dd/yyyy',$scope.tempTable.createTime);
	};
	
	$http.get($scope.host + ":7007/sprSecurity/rest/person").then(
			function(response) {
				$scope.person = response.data;
			});

	
});



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

	$scope.getFormattedDate=function (input) {
	    var pattern = /(.*?)\/(.*?)\/(.*?)$/;
	    var result = input.replace(pattern,function(match,p1,p2,p3){
	        var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	        return (p1<10?"0"+p1:p1) + " " + months[(p2-1)] + " " + p3;
	    });

	    return new Date(result).getTime();
	}
	
	$scope.submit = function() {
		
		if ($scope.tempTable.createTime != null) {
			
			if (!$.isNumeric($scope.tempTable.createTime)) {
				$scope.tempTable.createTime=$scope.getFormattedDate($scope.tempTable.createTime);	
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
		console.log($scope.tempTable.createTime);
		
		var singledate =  "/Date("+$scope.tempTable.createTime+")/";//$scope.tempTable.createTime;
		var changeddate = singledate.match(/\d+/g).map(function (s) { return new Date(+s); });
		var date = new Date(changeddate);
		var mnth = ("0" + (date.getMonth() + 1)).slice(-2);
		var day = ("0" + date.getDate()).slice(-2);
		$scope.tempTable.createTime = [day, mnth,date.getFullYear()].join("/");
		
		
	};
	
	$http.get($scope.host + ":7007/sprSecurity/rest/person").then(
			function(response) {
				$scope.person = response.data;
			});

	
});



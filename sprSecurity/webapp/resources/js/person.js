var app = angular.module('myApp_service', []);

app.controller("Main_controller", function($scope, $http) {

	// -------------- variables -------------------
	$scope.host = "http://" + window.location.hostname;
	$scope.person = {};

	// --------------- load All person --------------------

	$scope.load_all_Persons = function() {
		$http.get($scope.host + ":7007/sprSecurity/rest/person").then(function(response) {
			console.log("Done !");
			$scope.personList = response.data;
		});
	};

	$scope.submit = function() {

	};

	$scope.deleteItem = function(person) {
		console.log(person);
		$.ajax({
			type : 'DELETE',
			url : $scope.host + ':7007/sprSecurity/rest/deleteperson/' + person.id.name + "/" + person.id.fullName,
			success : function(data, textStatus, jqXHR) {
				$scope.load_all_Persons();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('deleteWine error');
			}
		});
	};

	$scope.loadItem = function(person) {

	};

});

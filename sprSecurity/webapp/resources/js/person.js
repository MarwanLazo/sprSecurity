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

		/*
		 * console.log("Done ! 1"); var data =
		 * 'username=temporary&password=temporary'; $.ajax({ data: data,
		 * timeout: 1000, type: 'GET', url: $scope.host +
		 * ":7007/sprSecurity/rest/person"
		 * 
		 * }).done(function(data, textStatus, jqXHR) { console.log("Done ! 2");
		 * $scope.personList = data;
		 * 
		 * }).fail(function(jqXHR, textStatus, errorThrown) { alert('Booh! Wrong
		 * credentials, try again!'); }); console.log("Done !3 end");
		 */

	};
	// -----------------save or update---------------------------------
	$scope.submit = function() {
		var rs = JSON.stringify($scope.person);
		$http.post($scope.host + ":7007/sprSecurity/rest/addPerson/", rs).success(function(rs, status, headers) {
			$scope.person = {};
			$scope.load_all_Persons();
		}).error(function(rs, status, header) {
			$scope.ResponseDetails = "Data: " + rs + "<br />status: " + status + "<br />headers: " + header;
			console.log($scope.ResponseDetails);
		});
	};
	// -------------------delete-----------------------------------
	$scope.deleteItem = function() {
		console.log($scope.person);
		if ($scope.person == null) {
			return;
		}
		$.ajax({
			type : 'DELETE',
			url : $scope.host + ':7007/sprSecurity/rest/deleteperson/' + $scope.person.id.name + "/" + $scope.person.id.fullName,
			success : function(data, textStatus, jqXHR) {
				$scope.load_all_Persons();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('deleteWine error');
			}
		});
	};

	$scope.loadItem = function(person) {
		$scope.person = person;
	};

});

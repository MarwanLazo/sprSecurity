var myViewModel = {
	personName : 'Boooooooooooooooooob',
	personAge : 123,
	
};

ko.applyBindings(myViewModel);

function testFunc() {
	myViewModel.personName=('Ali') ;
	alert(myViewModel.personName);
}

// ---------- consume WS

var out;

function consumeWS(ele_id) {

	var value = document.getElementById(ele_id).value;
	console.log(value);
	var url = 'http://localhost:7007/sprSecurity/rest/temptable/'.concat(value);
	$.ajax({
		type : 'Get',
		url : url,
		success : function(data) {
			view_data(data);

			console.log(data.tempEmail);
			Employee.LastName = ko.observable(data.tempEmail);
			console.log(Employee().LastName());
			view_model.email = data.tempEmail;

		}

	});
}

function view_data(data) {
	console.log(data);
}

var view_model
{
	email: "";
};


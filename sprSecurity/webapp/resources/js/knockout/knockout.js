
function Employee() {
	var that = this;
	that.LastName = ko.observable("");
	that.FirstName = ko.observable("");
	
	that.FullName = ko.computed(function() {
		return that.FirstName() + " " + that.LastName();	
	});
	that.DateOfBirth = ko.observable("");
	that.EducationList = ko.observableArray();
	that.Gender = ko.observable("0");
	that.DepartmentList = ko.observableArray([ 
	                                           {Id : '0',Name : "CSE"},
	                                           {Id : '1',Name : "MBA"} 
	                                           ]);
	that.DepartmentId = ko.observable("1");
	that.submit=function(){
		console.log(that.FirstName());
		console.log(that.LastName());
		console.log(that.FullName());
		console.log(that.DateOfBirth());
		console.log(that.EducationList());
		console.log(that.Gender());
		console.log(that.DepartmentId());
		
		return;
	};
}
ko.applyBindings(Employee());
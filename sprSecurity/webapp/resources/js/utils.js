function getFormattedDate(input) {
	// date fromate dd/MM/yyyy to date milli Second
	var pattern = /(.*?)\/(.*?)\/(.*?)$/;
	var result = input.replace(pattern, function(match, p1, p2, p3) {
		var months = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
		return (p1 < 10 ? "0" + p1 : p1) + " " + months[(p2 - 1)] + " " + p3;
	});

	return new Date(result).getTime();
}

function getFormattedDateFromMilliSCND(date) {
	// date in Milli second fromate to ==>> dd/MM/yyyy
	var singledate = "/Date(" + date + ")/";
	var changeddate = singledate.match(/\d+/g).map(function(s) {
		return new Date(+s);
	});
	var date = new Date(changeddate);
	var mnth = ("0" + (date.getMonth() + 1)).slice(-2);
	var day = ("0" + date.getDate()).slice(-2);
	// return date formatted 'dd/MM/yyyy'
	return [ day, mnth, date.getFullYear() ].join("/");

}

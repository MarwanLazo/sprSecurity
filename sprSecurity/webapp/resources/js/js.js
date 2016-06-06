
person = {
	name : "Tim Scarfe",
	height : "6Ft",
	run : function() {
		this.state = "running";
		this.speed = "4ms^-1";
		x=this.speed;
	}
};

function test() {
	x=10;
	console.log(person.name);

	console.log(person);
	console.log(x);
	person.run();
	console.log(x);
	console.log("=====================");
	

}
// var course = {
// 	name: "Networks", 
// 	credits: 5,
// 	num: 341,
// 	type: "core"
// }

// var courses = []

// for (var i = 0; i < 4; i++) {
// 	courses.push(course);
// }

// span {

// }

$(".checkbox").click(
	function() {
		if (
			$(this).checked
		) {
			$(this).parent().css("background-color", "red" )
		}
	}
);
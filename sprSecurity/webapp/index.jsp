<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Hello ! Srv</title>
<script type="text/javascript"
	src="resources/js/knockout/knockout-3.4.0.js"></script>

</head>
<body>

	You've clicked
	<span data-bind="text: numberOfClicks"></span> times
	<button data-bind="click: incrementClickCounter">Click me</button>

	<script type="text/javascript">
		var viewModel = {
			numberOfClicks : ko.observable(0),
			incrementClickCounter : function() {
				var previousCount = this.numberOfClicks();
				this.numberOfClicks(previousCount + 1);
			}
		};
	</script>
</body>
</html>
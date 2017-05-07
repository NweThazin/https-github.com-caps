<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : "yy-mm-dd",
			changeMonth : true,
			changeYear : true
		});
	});
</script>
<fmt:setBundle basename="messages" />
<c:set var="t" value="true" />
<title><fmt:message key="title" /></title>
<script src="gen_validatorv4.js" type="text/javascript"></script>
</head>
<body>
<div style="text-align:center;">
	<form id="search" action="/Team04CAPS/searchC" method=post>
		<table style="cellpadding: 4; cellspacing: 2; border: 0">
			<tr>
				<th width="45%">Criteria</th>
				<th width="55%">Search</th>
			</tr>
			<tr>
				<td><fmt:message key="label.course.courseID" /></td>
				<td><input name="courseID" type="text"
					value="${param['courseID']}" size=15 class="txtGroup"/></td>
			</tr>
			<tr>
				<td><fmt:message key="setup.courseName" /></td>
				<td><input type="text" name="courseName"
					value="${param['courseName']}" size=15 maxlength=20 class="txtGroup"/></td>
			</tr>
			<tr>
				<td><fmt:message key="setup.startDate" /></td>
				<td><input type="text" name="startDate" id="datepicker"
					value="2012-01-01" size=15 maxlength=20 ></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" class="buttonGroup" /></td>
				<td><input type="reset" value="Reset" class="buttonGroup" /></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>
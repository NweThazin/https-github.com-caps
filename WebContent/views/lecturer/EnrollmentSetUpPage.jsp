<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<fmt:setBundle basename="messages" />
<c:set var="t" value="true" />
<title><fmt:message key="title" /></title>
</head>
<body>
<div style="text-align:center;">
	<form action="/Team04CAPS/EnrollmentProcess" method=post>

		<!--<jsp:useBean id="enrollment" class="model.EnrollmentDTO"/> -->
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Detail</th>
				</tr>
				<tr>
					<td><fmt:message key="setup.courseID" /></td>
					<td>
					<input type="text" name="courseID" value="${param['courseID'] }" size=15 maxlength=20 readonly class="txtGroup"/>
						</td>
				</tr>
				<tr>
					<td><fmt:message key="setup.studentID" /></td>
					<td><input type="text" name="studentID" value="${param['studentID']}" size=15
								maxlength=20 readonly class="txtGroup">
				</tr>
		<%-- 		<tr>
					<td><fmt:message key="setup.studentName" /></td>
					<td><input type="text" name="studentName"
						value="${param['studentName']}"+"${param['studentSecondName']}" size=15 maxlength=20 readonly></td>
				</tr>	 --%>				
				<tr>
					<td><fmt:message key="setup.grade" /></td>
					<td><input type="text" name="grade"
						value="${param['studentGrade']}" size=15 maxlength=20 required></td>
				</tr>	
				<tr>
				<td><input type="submit" value="Submit" class="buttonGroup"></td>
				<td><input type="reset" value="Reset" class="buttonGroup"/></td>
				</tr>						
			</table>
		</center>
	</form>
	</div>
</body>
</html>
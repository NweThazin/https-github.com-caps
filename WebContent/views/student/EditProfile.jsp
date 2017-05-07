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
	<h2>Student Information Page</h2>
	<form action="/Team04CAPS/StudentCenter?action=1" method="post"
		name="studentForm">
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="35%">Description</th>
					<th width="65%">Detail</th>
				</tr>
				<tr>
					<td><fmt:message key="student.id" /></td>
					<td>
						<c:if test="${param['update']==t}">
							<input type="text" name="studentID" value="${param['studentID']}"
								size=25 maxlength=20 readonly="readonly" />
							<input type="hidden" name="ins" value="false" />
						</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="student.firstName" /></td>
					<td><input type="text" name="FirstName"
						value="${param['FirstName']}" size=25 maxlength=20 readonly="readonly"/></td>
				</tr>
				<tr>
					<td><fmt:message key="student.secondName" /></td>
					<td><input type="text" name="SecondName"
						value="${param['SecondName']}" size=25 maxlength=20 readonly="readonly"/></td>
				</tr>
				<tr>
					<td><fmt:message key="student.address" /></td>
					<td><input type="text" name="Address"
						value="${param['Address']}" size=25 maxlength=35 /></td>
				</tr>
				<tr>
					<td><fmt:message key="student.yearStudy" /></td>
					<td><input type="text" name="YearofStudy"
						value="${param['YearofStudy']}" size=25 maxlength=20 readonly="readonly"/></td>
				</tr>
				<tr>
					<td><fmt:message key="student.email" /></td>
					<td><input type="text" name="Email" value="${param['Email']}"
						size=25 maxlength=35/></td>
				</tr>
				<tr>
<%-- 					<c:if test="${param['insert']==t}">
							<input type="hidden" name="ins" value="true" />
					</c:if>  --%>
					<c:if test="${param['update']==t}">
							<td><fmt:message key="student.gpa" /></td>
							<td><input type="text" name="CGPA" value="${param['CGPA']}" size=25 maxlength=20 readonly="readonly"/></td>
							<input type="hidden" name="ins" value="false" />
						</c:if>
				</tr>
				<tr>
					<td>
<%-- 						<c:if test="${param['insert']==t}">
							<input class="buttonGroup" type="submit" value="Add Student">
							<input type="hidden" name="ins" value="true" />
						</c:if>  --%>
						<c:if test="${param['update']==t}">
							<input class="buttonGroup" type="submit" value="Update My Information">
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
					<td><input class="buttonGroup" type="reset" value="Clear"></td>
				</tr>
			</table>
		</center>

	</form>
</div>
</body>
</html>
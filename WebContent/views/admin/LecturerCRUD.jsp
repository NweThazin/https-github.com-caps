<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<fmt:setBundle basename="messages" />
<title><fmt:message key="title" />Lecturer Information</title>
</head>
<body>
	<div style="text-align: center;">
		<h1>
			<fmt:message key="label.lecturer" />
		</h1>
		<table>
			<tr>
				<td>To add new lecturer</td>
				<td><c:url var="url" scope="page" value="/GenerateController">
						<c:param name="lecturerID" value="${lecturer.lecturerID}" />
						<c:param name="insert" value="true" />
						<c:param name="autoGenerate" value="lecturer" />
					</c:url> <a href="${url}" class="lnkGroup"><fmt:message
							key="label.lecturer.add" /></a></td>
			</tr>
			<tr>
				<td>Search Course:</td>
				<td><c:url var="searchurl" scope="page"
						value="/views/admin/SearchLPage.jsp">
					</c:url> <a href="${searchurl}" class="lnkGroup"><fmt:message
							key="label.lecturer.search" /></a></td>
			</tr>
		</table>
		<%
			if (request.getParameter("lect").equals("lsearch")) {
		%>
		<display:table name="lecturers" pagesize="7" requestURI="/searchL"
			id="lecturers" class="borderAll">
			<display:column property="lecturerID" title="Lecturer ID"
				class="text-center" />
			<display:column property="firstName" title="First Name"
				class="text-center" />
			<display:column property="secondName" title="Second Name"
				class="text-center" />
			<display:column property="phone" title="Phone" class="text-center" />
			<display:column property="address" title="Address"
				class="text-center" />
			<display:column>
				<c:url var="updurl" scope="page"
					value="/views/admin/AddLecturer.jsp">
					<c:param name="lecturerID" value="${lecturers.lecturerID}" />
					<c:param name="firstName" value="${lecturers.firstName}" />
					<c:param name="secondName" value="${lecturers.secondName}" />
					<c:param name="phone" value="${lecturers.phone}" />
					<c:param name="address" value="${lecturers.address}" />
					<c:param name="update" value="true" />
				</c:url>
				<a href="${updurl}"><fmt:message key="label.edit" /></a>&nbsp;&nbsp;
			</display:column>
		</display:table>
		<%
			}
		%>
		<%
			if (request.getParameter("lect").equals("lload")) {
		%>
		<display:table name="lecturers" pagesize="7" requestURI="/load"
			id="lecturers" class="borderAll">
			<display:column property="lecturerID" title="Lecturer ID"
				class="text-center" />
			<display:column property="firstName" title="First Name"
				class="text-center" />
			<display:column property="secondName" title="Second Name"
				class="text-center" />
			<display:column property="phone" title="Phone" class="text-center" />
			<display:column property="address" title="Address"
				class="text-center" />
			<display:column>
				<c:url var="updurl" scope="page"
					value="/views/admin/AddLecturer.jsp">
					<c:param name="lecturerID" value="${lecturers.lecturerID}" />
					<c:param name="firstName" value="${lecturers.firstName}" />
					<c:param name="secondName" value="${lecturers.secondName}" />
					<c:param name="phone" value="${lecturers.phone}" />
					<c:param name="address" value="${lecturers.address}" />
					<c:param name="update" value="true" />
				</c:url>
				<a href="${updurl}"><fmt:message key="label.edit" /></a>&nbsp;&nbsp;
			</display:column>
		</display:table>
		<%
			}
		%>


		<%-- 	<c:url var="delurl" scope="page" value="/LecturerController">
				<c:param name="lecturerID" value="${lecturers.lecturerID}" />
				<c:param name="action" value="delete" />
			</c:url> --%>
		<%-- 	<a href="${delurl}"><fmt:message key="label.delete" /></a> --%>


		<%--         <table class="borderAll" width="100%" height="100%" border="1" align="center">
            <tr>
                <th><fmt:message key="label.lecturer.lecturerID"/></th>
                <th><fmt:message key="label.lecturer.firstname"/></th>
                <th><fmt:message key="label.lecturer.secondname"/></th>
                <th><fmt:message key="label.lecturer.phone"/></th>
                <th><fmt:message key="label.lecturer.address"/></th>
                <th><fmt:message key="label.edit"/> </th>
                <th><fmt:message key="label.delete"/></th>
            </tr>
            <c:forEach var="lecturer" items="${lecturers}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${lecturer.lecturerID}</td>
                    <td class="nowrap">${lecturer.firstName}</td>
                    <td class="nowrap">${lecturer.secondName}</td>
                    <td class="nowrap">${lecturer.phone}</td>
                    <td class="nowrap">${lecturer.address}</td>
                    <td class="nowrap">
                        <c:url var="updurl" scope="page" value="/views/AddLecturer.jsp">
                            <c:param name="lecturerID" value="${lecturer.lecturerID}"/>
                            <c:param name="firstName" value="${lecturer.firstName}"/>
                            <c:param name="secondName" value="${lecturer.secondName}"/>
                            <c:param name="phone" value="${lecturer.phone}"/>
                            <c:param name="address" value="${lecturer.address}"/>
                            <c:param name="update" value="true"/>
                        </c:url>
                        <a href="${updurl}"><fmt:message key="label.edit"/></a>
                        &nbsp;&nbsp;&nbsp;
                        </td>
                        <td class="nowrap">
                        <c:url var="delurl" scope="page" value="/LecturerController">
                            <c:param name="lecturerID" value="${lecturer.lecturerID}"/>
                            <c:param name="action" value="delete" />
                        </c:url>
                        <a href="${delurl}"><fmt:message key="label.delete"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table> --%>
	</div>
</body>
</html>
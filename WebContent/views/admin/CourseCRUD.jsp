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
<title><fmt:message key="title" />Course Information</title>
</head>
<body>
	<div style="text-align: center;">
		<h1>
			<fmt:message key="label.course" />
		</h1>
		<table>
			<tr>
				<td>To Add New Course:</td>
				<td><c:url var="url" scope="page" value="/GenerateController">
						<c:param name="insert" value="true" />
						<c:param name="autoGenerate" value="course" />
					</c:url> <a href="${url}" class="lnkGroup"><fmt:message
							key="label.course.add" /></a></td>
			</tr>
			<tr>
				<td>Search Course:</td>
				<td><c:url var="searchurl" scope="page"
						value="/views/admin/SearchCPage.jsp">
					</c:url> <a href="${searchurl}" class="lnkGroup"><fmt:message
							key="label.course.search" /></a></td>
			</tr>
		</table>
		<%
			if (request.getParameter("check").equals("nav")) {
		%>
		<display:table name="courses" pagesize="4" requestURI="/load"
			id="courses" class="borderAll">
			<display:column property="courseID" title="Course ID"
				class="text-center" />
			<display:column property="lecturer.lecturerID" title="Lecturer ID"
				class="text-center" />
			<display:column property="courseName" title="Course Name"
				class="text-center" />
			<display:column property="startDate" title="Start Date"
				class="text-center" />
			<display:column property="endDate" title="End Date"
				class="text-center" />
			<display:column property="courseFees" title="Course Fees"
				class="text-center" />
			<display:column property="courseCredits" title="Course Credits"
				class="text-center" />
			<display:column property="comments" title="Comments"
				class="text-center" />
			<display:column property="courseSize" title="Course Size"
				class="text-center" />
			<display:column>
				<c:url var="updurl" scope="page" value="/GenerateController">
					<c:param name="courseID" value="${courses.courseID}" />
					<c:param name="lecturer.lecturerID"
						value="${courses.lecturer.lecturerID}" />
					<c:param name="courseName" value="${courses.courseName}" />
					<c:param name="startDate" value="${courses.startDate}" />
					<c:param name="endDate" value="${courses.endDate}" />
					<c:param name="courseFees" value="${courses.courseFees}" />
					<c:param name="courseCredits" value="${courses.courseCredits}" />
					<c:param name="comments" value="${courses.comments}" />
					<c:param name="courseSize" value="${courses.courseSize}" />
					<c:param name="autoGenerate" value="course" />
					<c:param name="update" value="true" />
				</c:url>
				<a href="${updurl}"><fmt:message key="label.edit" /></a>&nbsp;&nbsp;
				<c:url var="delurl" scope="page" value="/CourseController">
					<c:param name="courseID" value="${courses.courseID}" />
					<c:param name="action" value="delete" />
				</c:url>
				<a href="${delurl}"><fmt:message key="label.delete" /></a>

				<c:url var="checkurl" scope="page" value="/check">
					<c:param name="courseID" value="${courses.courseID}" />
				</c:url>
				<a href="${checkurl}"><fmt:message key="label.course.check" /></a>
			</display:column>
		</display:table>

		<%
			}
		%>
		<%
			if (request.getParameter("check").equals("search")) {
		%>
		<display:table name="courses" pagesize="4" requestURI="/searchC"
			id="courses" class="borderAll">
			<display:column property="courseID" title="Course ID"
				class="text-center" />
			<display:column property="lecturer.lecturerID" title="Lecturer ID"
				class="text-center" />
			<display:column property="courseName" title="Course Name"
				class="text-center" />
			<display:column property="startDate" title="Start Date"
				class="text-center" />
			<display:column property="endDate" title="End Date"
				class="text-center" />
			<display:column property="courseFees" title="Course Fees"
				class="text-center" />
			<display:column property="courseCredits" title="Course Credits"
				class="text-center" />
			<display:column property="comments" title="Comments"
				class="text-center" />
			<display:column property="courseSize" title="Course Size"
				class="text-center" />
			<display:column>
				<c:url var="updurl" scope="page" value="/GenerateController">
					<c:param name="courseID" value="${courses.courseID}" />
					<c:param name="lecturer.lecturerID"
						value="${courses.lecturer.lecturerID}" />
					<c:param name="courseName" value="${courses.courseName}" />
					<c:param name="startDate" value="${courses.startDate}" />
					<c:param name="endDate" value="${courses.endDate}" />
					<c:param name="courseFees" value="${courses.courseFees}" />
					<c:param name="courseCredits" value="${courses.courseCredits}" />
					<c:param name="comments" value="${courses.comments}" />
					<c:param name="courseSize" value="${courses.courseSize}" />
					<c:param name="autoGenerate" value="course" />
					<c:param name="update" value="true" />
				</c:url>
				<a href="${updurl}"><fmt:message key="label.edit" /></a>&nbsp;&nbsp;
				<c:url var="delurl" scope="page" value="/CourseController">
					<c:param name="courseID" value="${courses.courseID}" />
					<c:param name="action" value="delete" />
				</c:url>
				<a href="${delurl}"><fmt:message key="label.delete" /></a>

				<c:url var="checkurl" scope="page" value="/check">
					<c:param name="courseID" value="${courses.courseID}" />
				</c:url>
				<a href="${checkurl}"><fmt:message key="label.course.check" /></a>
			</display:column>
		</display:table>



		<%
			}
		%>


		<%--              <table class="showAll" width="100%" height="100%" border="1" align="center">
             <tr>
                <th><fmt:message key="label.course.courseID"/></th>
                <th><fmt:message key="label.course.lecturerID"/></th>
                <th><fmt:message key="label.course.courseName"/></th>
                <th><fmt:message key="label.course.startDate"/></th>
                <th><fmt:message key="label.course.endDate"/></th>
                <th><fmt:message key="label.course.courseFees"/></th>
                <th><fmt:message key="label.course.courseCredits"/></th>
                 <th><fmt:message key="label.course.comments"/></th>
                  <th><fmt:message key="label.course.courseSize"/></th>
                <th><fmt:message key="label.course.edit"/> </th>
                <th><fmt:message key="label.course.delete"/></th>
                <th><fmt:message key="label.course.enrolment"/></th>
            </tr>
            <c:forEach var="course" items="${courses}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${course.courseID}</td>
                    <td class="nowrap">${course.lecturer.lecturerID}</td>
                    <td class="nowrap">${course.courseName}</td>
                    <td class="nowrap">${course.startDate}</td>
                    <td class="nowrap">${course.endDate}</td>
                    <td class="nowrap">${course.courseFees}</td>
                    <td class="nowrap">${course.courseCredits}</td>
                    <td class="nowrap">${course.comments}</td>
                    <td class="nowrap">${course.courseSize}</td>
                    <td class="nowrap">
                        <c:url var="updurl" scope="page" value="/views/AddCourse.jsp">
                            <c:param name="courseID" value="${course.courseID}"/>
                            <c:param name="lecturerID" value="${course.lecturer.lecturerID}"/>
                            <c:param name="courseName" value="${course.courseName}"/>
                            <c:param name="startDate" value="${course.startDate}"/>
                            <c:param name="endDate" value="${course.endDate}"/>
                            <c:param name="courseFees" value="${course.courseFees}"/>
                            <c:param name="courseCredits" value="${course.courseCredits}"/>
                            <c:param name="comments" value="${course.comments}"/>
                            <c:param name="courseSize" value="${course.courseSize}"/>             
                            <c:param name="update" value="true"/>
                        </c:url>
                        <a href="${updurl}"><fmt:message key="label.course.edit"/></a>
                        &nbsp;&nbsp;&nbsp;
                        </td>
                        <td class="nowrap">
                        <c:url var="delurl" scope="page" value="/CourseController">
                            <c:param name="courseID" value="${course.courseID}"/>
                            <c:param name="action" value="delete" />
                        </c:url>
                        <a href="${delurl}"><fmt:message key="label.course.delete"/></a>
                    </td>
                      <td class="nowrap">
                        <c:url var="checkurl" scope="page" value="/loadL">
                            <c:param name="courseID" value="${course.courseID}"/>
                            
                        </c:url>
                        <a href="${checkurl}"><fmt:message key="label.course.check"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table> --%>
	</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="<c:url value='/styles/style.css'/>" rel="stylesheet" type="text/css"/>
<fmt:setBundle basename="messages" />
<title> <fmt:message key="title"/>Lecturer Page</title>
</head>
<body>
<div style="text-align:center;">
        <h1><fmt:message key="label.course"/></h1>
        <table class="borderAll">
            <tr> 
            <th>No</th>       
                <th><fmt:message key="label.course.courseID"/></th>
                <th><fmt:message key="label.course.name"/></th>
                <th><fmt:message key="label.course.size"/></th>
                <th><fmt:message key="label.course.courseStart"/></th>
                <th><fmt:message key="label.course.enrollmentDetails"/>
            </tr>
            <c:forEach var="course" items="${courses}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${status.index + 1}</td>
                    <td class="nowrap">${course.courseID}</td>
                    <td class="nowrap">${course.courseName}</td>
                    <td class="nowrap">${course.courseSize}</td>
                    <td class="nowrap">${course.startDate}</td>
                    <td class="nowrap">
                        <c:url var="updurl" scope="page" value="/process">
                            <c:param name="courseID" value="${course.courseID}"/>
                          <c:param name="name" value="${course.courseName}"/>
                          <c:param name="size" value="${course.courseSize}"/>
                        </c:url>
                        <a href="${updurl}"><fmt:message key="label.course.view"/></a>
                        &nbsp;&nbsp;&nbsp;                        
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.profile.userRole == 'lecturer'}">
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li class="sidebar-brand">Lecturer</br>${sessionScope.profile.userID}</li>
					<li class="nav-divider"></li>
					<li><a href="<%=request.getContextPath()%>/lecturerload">View
							Course Taught & Enrollment</a></li>

					<li><a href="<%=request.getContextPath()%>/enrollment">Grade
							a Course</a></li>
					<li><a
						href="<%=request.getContextPath()%>/views/lecturer/SearchPage.jsp">View
							Student Performance</a></li>
					<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
				</ul>
			</div>
		</c:when>
		<c:when test="${sessionScope.profile.userRole == 'student'}">
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li class="sidebar-brand">Student</br>${sessionScope.profile.userID}</li>
					<li class="nav-divider"></li>
					<li><a href="<%=request.getContextPath()%>/StudentCenter?action=1">
						My Profile</a></li>
					<li><a
						href="<%=request.getContextPath()%>/StudentCenter?action=2">
						My Grades & GPA</a></li>
					<%-- <li><a
						href="<%=request.getContextPath()%>/StudentCenter?action=3">
						View My Course</a></li> --%>
					<li><a
						href="<%=request.getContextPath()%>/StudentCenter?action=4">
						View/Enroll Course</a></li>
					<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
				</ul>
			</div>
		</c:when>
		<c:when test="${sessionScope.profile.userRole == 'admin'}">
			<div id="sidebar-wrapper">
				<ul class="sidebar-nav">
					<li class="sidebar-brand">Admin</br>${sessionScope.profile.userID}</li>
					<li class="nav-divider"></li>
					<li><c:url var="managestudent" scope="page" value="/load">
							<c:param name="admin" value="student" />
						</c:url> <a href="${managestudent}">Manage Student</a></li>

					<li><c:url var="managelecturer" scope="page" value="/load">
							<c:param name="admin" value="lecturer" />
						</c:url><a href="${managelecturer}">Manage Lecturer</a></li>

					<li><c:url var="managecourse" scope="page" value="/load">
							<c:param name="admin" value="course" />
						</c:url><a href="${managecourse}">Manage Course</a></li>

					<li><c:url var="manageenroll" scope="page" value="/load">
							<c:param name="admin" value="enroll" />
						</c:url><a href="${manageenroll}">Manage Enrollment</a></li>

					<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
				</ul>
			</div>
		</c:when>
	</c:choose>
</body>
</html>
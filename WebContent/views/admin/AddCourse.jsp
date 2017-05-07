<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.io.*,java.util.*,javax.servlet.*"%>
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
$(document).ready(function () {
    $("#startDate").datepicker({ dateFormat: "yy-mm-dd",  yearRange: '2010:2060', changeMonth: true,
        changeYear: true, value: "2016-12-10"
    });
    $("#endDate").datepicker({ dateFormat: "yy-mm-dd",  yearRange: '2010:2060',changeMonth: true,
        changeYear: true, value: "2016-12-11"
    });
});
</script>
<script>
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
</script>
<script type="text/javascript">
    function validate() 
    {
        if(SetUpC.courseCredits.value<=0||SetUpC.courseCredits.value>20)
        { 
         document.getElementById('errfn').innerHTML="this is out of range(0-20)";
           return false;
        }  
        if(SetUpC.courseSize.value<=0||SetUpC.courseSize.value>300)
        {
          	document.getElementById('errfn1').innerHTML="this is out of range(0-300)";
            return false;
        }  
        var startDate = new Date($('#startDate').val());
    	var endDate = new Date($('#endDate').val());
        if(endDate<startDate)
        {
        	document.getElementById('errfn2').innerHTML="this is invalid date";
            return false;
        }        
    }
</script>
<fmt:setBundle basename="messages" />
<c:set var="t" value="true" />
<title><fmt:message key="title" />Add Course Page</title>
</head>
<body>
<div style="text-align:center;">
	<form action="/Team04CAPS/CourseController" id="SetUpC" onsubmit="validate()"
		method=post>
		<center>
			<table cellpadding=2 cellspacing=2 border=0 class="inputTable">
				<tr>
					<th width="45%">Course</th>
					<th width="55%">Information</th>
				</tr>
				<tr>
					<td><fmt:message key="setup.courseID" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="courseID" value="${courseID}"
								size=15 maxlength=20 class="txtGroup"/>
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<input type="text" name="courseID" value="${param['courseID']}"
								size=15 maxlength=20 readonly="readonly">
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.lecturerID" /></td>
					<td><c:if test="${param['insert']==t}">
							<select class="form-control" name="lecturerID" id="lecturerID"
								style="width: 125px;">
								<option value=null></option>
								<c:forEach var="list" items="${list}">
									<option value="${list.lecturerID}">${list.firstName}
										${list.secondName}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<select class="form-control" name="lecturerID" id="lecturerID"
								style="width: 125px;">
								<c:forEach var="list" items="${list}">
									<c:if test="${list.lecturerID==param['lecturerID']}">
										<option value="${list.lecturerID}">${list.firstName}
											${list.secondName}</option>
									</c:if>
								</c:forEach>
								<c:forEach var="list" items="${list}">
									<c:if test="${list.lecturerID!=param['lecturerID']}">
										<option value="${list.lecturerID}">${list.firstName}
											${list.secondName}</option>
									</c:if>
								</c:forEach>
							</select>
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.courseName" /></td>
					<td><input type="text" name="courseName"
						value="${param['courseName']}" size=15 maxlength=20 class="txtGroup"/></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.startDate" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="startDate" id="startDate"
								value="2016-12-10" size=15 maxlength=20 class="txtGroup" />
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<input name="startDate" type="text" id="startDate"
								value="${param['startDate']}" size=15 readonly="readonly" />
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.endDate" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="endDate" id="endDate" value="2016-12-11"
								size=15 maxlength=20 class="txtGroup" />
							<span id="errfn2" style="color: red"></span>
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<input name="endDate" type="text" id="endDate"
								value="${param['endDate']}" size=15 readonly="readonly" style="color: red"/>
							<span id="errfn2"></span>
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.courseFees" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="courseFees" value="0" size=15
								onkeypress="return isNumber(event)" class="txtGroup">
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<input name="courseFees" type="text"
								value="${param['courseFees']}" size=15 class="txtGroup"/>
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.courseCredits" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="courseCredits" id="courseCredits"
								value="0" size=15 onkeypress="return isNumber(event)" class="txtGroup"/>
							<span id="errfn" style="color: red"></span>
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<input name="courseCredits" id="courseCredits" type="text"
								value="${param['courseCredits']}" size=15 />
							<span id="errfn"></span>
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.comments" /></td>
					<td><input type="text" name="comments"
						value="${param['comments']}" size=15 maxlength=20 class="txtGroup"/></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.courseSize" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="courseSize" id="courseSize" value="0"
								size=15 maxlength=20 onkeypress="return isNumber(event)" class="txtGroup">
							<span id="errfn1" style="color: red"></span>
							<input type="hidden" name="ins" value="true" />

						</c:if> <c:if test="${param['update']==t}">
							<input name="courseSize" type="text" id="courseSize"
								value="${param['courseSize']}" size=15 class="txtGroup"/>
							<span id="errfn1" style="color: red"></span>
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
				</tr>
				<tr>
					<td><input type="submit" value="Add Course"
						onclick="return validate();" class="buttonGroup"></td>
					<td><input type="reset" value="Reset" class="buttonGroup"/></td>
				</tr>
			</table>
			</center>
	</form>
	</div>
</body>
</html>
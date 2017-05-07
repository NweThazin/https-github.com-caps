<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript">
function validate() 
{
    if(setup.firstName.value=="")
    {
       document.getElementById('errfn').innerHTML="this is needed";
       return false;
    } 
    if(setup.phone.value.length!=8||setup.phone.value/10000000<6 ||setup.phone.value/10000000>10 || setup.phone.value/10000000>7&&setup.phone.value/10000000<8)
    {
    	document.getElementById('errfn1').innerHTML="this is invalid phonenumber";
        return false;
    } 
}
</script>
<script>
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
        alert="please enter number"
    }
    return true;
}
</script>
<fmt:setBundle basename="messages" />
<c:set var="t" value="true" />
<title><fmt:message key="title" /></title>
</head>
<body>
<div style="text-align:center;">
	<form action="/Team04CAPS/LecturerController" name="setup"
		onsubmit="validate()" method=post>

		<!--<jsp:useBean id="lecturer" class="model.LecturerDTO"/> -->
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Lecturers</th>
					<th width="55%">Information</th>
				</tr>
				<tr>
					<td><fmt:message key="setup.lecturerID" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="lecturerID" value="${lecturerID}"
								size=15 maxlength=20 class="txtGroup" readonly>
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<input type="text" name="lecturerID"
								value="${param['lecturerID']}" size=15 maxlength=20
								readonly="readonly">
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
						<td></td>
						
				</tr>
				<tr>
					<td><fmt:message key="setup.firstname" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="firstName" value="${param['firstName']}"
								size=15 maxlength=20 class="txtGroup">
							<input type="hidden" name="ins" value="true" />
							<span id="errfn" style="color: red"></span>
						</c:if> <c:if test="${param['update']==t}">
							<input type="text" name="firstName" value="${param['firstName']}"
								size=15 maxlength=20 class="txtGroup" readonly>
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
						
				</tr>
				<tr>
					<td><fmt:message key="setup.secondname" /></td>
					<td><c:if test="${param['insert']==t}">
							<input type="text" name="secondName"
								value="${param['secondName']}" size=15 maxlength=20 class="txtGroup">
							<input type="hidden" name="ins" value="true" />
						</c:if> <c:if test="${param['update']==t}">
							<input type="text" name="secondName"
								value="${param['secondName']}" size=15 maxlength=20
								class="txtGroup" readonly>
							<input type="hidden" name="ins" value="false" />
						</c:if></td>
				</tr>
				<tr>
					<td><fmt:message key="setup.phone" /></td>
					<td><input id="phone" type="text" name="phone"
						onkeypress="return isNumber(event)" value="${param['phone']}"
						size=15 maxlength=20 class="txtGroup">
						<span id="errfn1" style="color: red"></span>	
					</td>
					
				</tr>
				<tr>
					<td><fmt:message key="setup.address" /></td>
					<td><input type="text" name="address"
						value="${param['address']}" size=15 maxlength=20 class="txtGroup"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"
						onclick="return validate();" class="buttonGroup" /></td>
					<td><input type="reset" value="Reset" class="buttonGroup" /></td>

				</tr>
			</table>
		</center>

	</form>
	</div>
</body>
</html>
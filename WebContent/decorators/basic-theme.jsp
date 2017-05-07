<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../styles/team4Style.css" />
<link rel="stylesheet" type="text/css" href="../styles/style.css" />
<link rel="stylesheet" type="text/css"
	href="../styles/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script>
	$(function() {
		$(".enrollmentDate").datepicker({
			dateFormat : "yy-mm-dd"
		});
	});
</script>
<title><decorator:title /></title>
<link rel="stylesheet" type="text/css" href="../styles/style.css" />
<link rel="stylesheet" type="text/css"
	href="../styles/bootstrap.min.css" />
<decorator:head />
</head>
<body>
	<table class="mainTable" id="page-container" cellpadding="5"
		cellspacing="0" border="1" align="center" width="100%">
		<tr>
			<td colspan="2" id="page-header"><%@ include
					file="/includes/header.jsp"%></td>
		</tr>
		<tr>
			<td id="left-nav-container"><%@ include
					file="/includes/navigationLeft.jsp"%></td>
			<td id="content-container"><decorator:body /></td>
		</tr>
		<tr>
			<td colspan="2" id="page-footer"><%@ include
					file="/includes/footer.jsp"%></td>
		</tr>
	</table>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</body>
</html>


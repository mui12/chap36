<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="alpha_letters_icon.png">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script src="/webjars/jquery/jquery.min.js"></script>
<title>language</title>
</head>
<body>
	<h1>Language List</h1>
	<hr>
	<a href="/">Home</a>
	<hr>
	<section class="container table-responsive" style="border: 10px solid red">
		<table class="table table-dark table-striped table-hover">
			<thead>
				<tr>
					<th>countryCode</th>
					<th>language</th>
					<th>isOfficial</th>
					<th>percentage</th>
			</thead>
			
			<tbody>
			<c:forEach var="e" items="${list}">
				<tr>
					<td>${e.countryCode}</td>
					<td><a href="/language/detail/${e.language}">${e.language}</a></td>
					<td>${e.isOfficial}</td>
					<td>${e.percentage}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</section>
	<hr>

</body>
</html>


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
<title>page.jsp</title>
</head>
<body>
	<h1>Language Page <span>pageNum = ${paging.pageNum}</span> </h1>
	<hr>
	<a href="/">Home</a>
	<hr>
	<section class="container table-responsive">
		<ul  class="list-group list-group-horizontal">
			<li><a href="/language/page/${paging.navigateFirstPage-1}/${paging.pageSize}" class="list-group-item ">Previous</a></li>
			<c:forEach var="n" items="${paging.navigatepageNums}">
				<c:choose>
					<c:when test="${n eq paging.pageNum}">
						<li class="list-group-item active"><a href="/language/page/${n}/${paging.pageSize}" class="text-warning">${n}</a></li>
					</c:when>
					<c:when test="${n ne paging.pageNum}">
						<li class="list-group-item       "><a href="/language/page/${n}/${paging.pageSize}">${n}</a></li>
					</c:when>
				</c:choose>
			</c:forEach>
			<li><a href="/language/page/${paging.navigateLastPage+1}/${paging.pageSize}" class="list-group-item">Next</a></li>
		</ul>

		<table class="table table-dark table-striped table-hover">
			<thead>
				<tr>
					<th>countryCode</th>
					<th>language</th>
					<th>isOfficial</th>
					<th>percentage</th>
				</tr>
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
	<div>
 		<pre>
		${json}
		</pre>
	</div>
	

</body>
</html>


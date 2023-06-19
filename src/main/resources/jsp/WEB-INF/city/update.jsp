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
<title>update.jsp</title>
</head>
<body>
	<h1>City Update 부서 추가</h1>
	<hr>
	<a href="/">Home</a>
	<hr>
	<a href="/city/list">/city/list</a>
	<hr>
	<section class="container">
		<form action="/city/update" method="post">
			<div class="mb-3">
				<label class="form-lable mb-2" for="id">id<span>*</span></label>			
				<input class="form-control"    id="id"	    name="id" value="${city.id}" disabled="disabled"/>
				<input class="form-control"    id="id"	    name="id" value="${city.id}" type="hidden"/>
			</div>
			<div class="mb-3">
				<label class="form-lable mb-2" for="name">name<span>*</span></label>			
				<input class="form-control"    id="name"	    name="name"	      placeholder="도시이름을 입력하세요." value="${city.name}"/>
			</div>
			<div class="mb-3">
				<label class="form-lable mb-2" for="countryCode">countryCode<span>*</span></label>			
				<input class="form-control"    id="countryCode" name="countryCode" placeholder="도시코드를 입력하세요." value="${city.countryCode}"/>
			</div>				
			<div class="mb-3">
				<label class="form-lable mb-2" for="district">district</label>			
				<input class="form-control"    id="district"    name="district"    placeholder="지역이름 입력하세요." value="${city.district}"/>
			</div>		
			<div class="mb-3">
				<label class="form-lable mb-2" for="population">population</label>			
				<input class="form-control"    id="population"  name="population"  placeholder="인구수를 입력하세요." value="${city.population}"/>
			</div>		
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</section>
	<hr>

	<!-- 	error 접근	 -->
	<c:if test="${binding.hasErrors()}">
		<h2>Error Massage</h2>
		<hr>
		<c:forEach var="g" items="${binding.globalErrors}">
			<div>${g.code} ${g.defaultMessage}</div>
		</c:forEach>
		<hr>
		<c:forEach var="f" items="${binding.fieldErrors}">
			<div>${f.field} ${f.defaultMessage}</div>
		</c:forEach>
	</c:if>

</body>
</html>


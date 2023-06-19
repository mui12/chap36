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
<title>success.jsp</title>
</head>
<body>
	<h1>Country Success 성공</h1>
	<hr>
	<a href="/">Home</a>
	<hr>
	<a href="/country/list">/country/list</a>
	<hr>
	<br>
	<br>
	<br>
	<c:choose>
		<c:when test="${param.create ne null}"><h1>country 추가 성공</h1></c:when>
		<c:when test="${param.update ne null}"><h1>country 수정 성공</h1></c:when>
		<c:when test="${param.delete ne null}"><h1>country 삭제 성공</h1></c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	<hr>
	
	<section class="container">
		<table class="table">
			<tbody>
				<tr><th>code</th>            <td>${country.code}			 </td></tr>             
				<tr><th>name</th>            <td>${country.name}			 </td></tr>             
				<tr><th>continent</th>       <td>${country.continent.symbol} </td></tr> 
				<tr><th>region</th>          <td>${country.region}			 </td></tr>           
				<tr><th>surfaceArea</th>     <td>${country.surfaceArea}		 </td></tr>      
				<tr><th>indepYear</th>       <td>${country.indepYear}		 </td></tr>        
				<tr><th>population</th>      <td>${country.population}		 </td></tr>       
				<tr><th>lifeExpectancy</th>  <td>${country.lifeExpectancy}	 </td></tr>   
				<tr><th>gnp</th>             <td>${country.gnp}				 </td></tr>              
				<tr><th>gnpOld</th>          <td>${country.gnpOld}			 </td></tr>           
				<tr><th>localName</th>       <td>${country.localName}		 </td></tr>        
				<tr><th>governmentForm</th>  <td>${country.governmentForm}	 </td></tr>   
				<tr><th>headOfState</th>     <td>${country.headOfState}		 </td></tr>      
				<tr><th>capital</th>         <td>${country.capital}			 </td></tr>          
			</tbody>
		</table>
	</section>
	<hr>
	

</body>
</html>


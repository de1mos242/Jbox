<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-app>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<spring:url value="/resources/css/app.css" var="appCSS" />
<spring:url value="/resources/js/app.js" var="appJS" />
<link rel="stylesheet" href="${appCSS}">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.js"></script>
<script type="text/javascript" src=${appJS} }></script>
</head>
<body>
	hello
	<c:choose>
		<c:when test="${not empty VKUser}">
			<div>vkuser: ${VKUser.getFullName()}</div>
			<div>vktoken: ${VKUser.getTokenString()}</div>
		</c:when>

		<c:otherwise>
			<div>
				<a href="vk/sign">войти</a>
			</div>
		</c:otherwise>
	</c:choose>
	<div>
		<c:if test="${not empty musicList}">
			<f:forEach var="track" begin="0" step="1" items="${musicList}">
				<div>
					<p>${track.getArtist()} - ${track.getTitle()}</p>
				</div>
			</f:forEach>
		</c:if>
	</div>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: anonymous
  Date: 13.05.2018
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>
<!--
<form method="get" action="/getItems">
    <input type="submit" name="getItems" value="Get Items"/>
</form>
<form method="get" action="/addItem">
    <input type="submit" name="addItem" value="Add Item"/>
</form>
-->
<h4><c:out value="${sessionScope.firstName}"/> <c:out value="${sessionScope.secondName}"/>
    Birthday: <c:out value="${sessionScope.bdate}"/></h4>

<button onclick="location.href='/getItems'">Get Items</button>
<button onclick="location.href='/addItem'">Add Item</button>
</body>
</html>

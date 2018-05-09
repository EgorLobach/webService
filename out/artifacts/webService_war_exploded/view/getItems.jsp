<%--
  Created by IntelliJ IDEA.
  User: anonymous
  Date: 06.05.2018
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Get Items</title>
</head>
<body>
    <c:forEach var="item" items="${requestScope.items}">
        <c:out value="${item.itemName}"/></br>
        Year of publication: <c:out value="${item.yearOfPublication}"/></br>
        Author: <c:out value="${item.author.firstName}"/>
                <c:out value="${item.author.secondName}"/>
                </br>
        Chapters:
            <c:forEach items="${item.chapters}" var="chapter">
                <li>
                    <c:out value="${chapter.chapterName}"/></br>
                    <c:out value="${chapter.text}"/>
                </li>
            </c:forEach>
        <form method="post" action="/deleteItem">
            <input type = "hidden" name = "itemName" value="${item.itemName}"/>
            <input type="submit" name="delete" value="Delete Item"/>
        </form>
        <form method="get" action="/updateItem">
            <input type = "hidden" name = "itemName" value="${item.itemName}"/>
            <input type="submit" name="update" value="Update Item"/>
        </form>
        <hr/>
    </c:forEach>
</body>
</html>

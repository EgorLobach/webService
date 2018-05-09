<%--
  Created by IntelliJ IDEA.
  User: anonymous
  Date: 06.05.2018
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Item</title>
</head>
<body>
<form method="post" action="/addItem">
    Item Name: <input type = "text" name="itemName" size="50"/><br/>
    Year of publication: <input type = "text" name="yearOfPublication" size="50"/><br/>
    Author: <input type = "text" name="firstName" size="20"/>
    <input type = "text" name="secondName" size="20"/><br/>
    First Chapter: <input type = "text" name="chapterName" size="50"/><br/>
    <textarea name="text" rows="35" cols="50"></textarea><br/>
    <input type="submit" name="add" value="Add Item"/>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: anonymous
  Date: 09.05.2018
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Item</title>
    <script language="javascript">
        var chaptersNumber=1;
        var number = -1;
        function DeleteChapter(id) {
            var el=document.getElementById(id);
            el.parentNode.removeChild(el);
        }
        function AddChapter() {
            div=document.getElementById("chaptersItem");
            button=document.getElementById("add");
            chaptersNumber++;
            newitem="<div id = \""+number+"\"> <input type = \"text\" name=\"chapterName\" size=\"20\" value=\"new chapter\"/>";
            newitem+="<input type=\"button\" value=\"Delete Chapter\" onClick=\"DeleteChapter("+number+");\" ID=\"delete\"><br/>";
            newitem+="<textarea name=\"text\" rows=\"35\" cols=\"50\"></textarea><br/></div>";
            newnode=document.createElement("span");
            newnode.innerHTML=newitem;
            div.insertBefore(newnode,button);
            number--;
        }
    </script>
</head>
<body>
<form method="post" action="/updateItem">
    <input type = "hidden" name = "itemName" value="${item.itemName}"/>
    Item Name: <c:out value="${requestScope.item.itemName}"/><br/>
    Year of publication: <input type = "text" name="yearOfPublication" size="50" value="${requestScope.item.yearOfPublication}"/><br/>
    Author: <input type = "text" name="firstName" size="20" value="${requestScope.item.author.firstName}"/>
    <input type = "text" name="secondName" size="20" value="${requestScope.item.author.secondName}"/><br/>
    Chapters:<br/>
    <c:set var="number " value="0"/>
    <c:forEach var="chapter" items="${requestScope.item.chapters}">
        <div ID="${number = number + 1}">
            <input type = "text" name="chapterName" size="20" value="${chapter.chapterName}"/>
            <input type="button" value="Delete Chapter" onClick="DeleteChapter(${number});" ID="delete"><br/>
            <textarea name="text" rows="35" cols="50"><c:out value="${chapter.text}"/></textarea><br/>
        </div>
    </c:forEach>
    <div ID="chaptersItem">
        <input type="button" value="Add Chapter" onClick="AddChapter();" ID="add">
    </div>
    <input type="submit" name="update" value="Update Item"/>
</form>
</body>
</html>

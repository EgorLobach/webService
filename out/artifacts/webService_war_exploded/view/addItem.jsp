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
<form method="post" action="/addItem">
    Item Name: <input type = "text" name="itemName" size="50"/><br/>
    Year of publication: <input type = "text" name="yearOfPublication" size="50"/><br/>
    Author: <input type = "text" name="firstName" size="20"/>
    <input type = "text" name="secondName" size="20"/><br/>
    Chapters:<br/>
    <div ID="0">
        <input type = "text" name="chapterName" size="20" value="new chapter"/>
        <input type="button" value="Delete Chapter" onClick="DeleteChapter(0);" ID="delete"><br/>
        <textarea name="text" rows="35" cols="50"></textarea><br/>
    </div>
    <div ID="chaptersItem">
        <input type="button" value="Add Chapter" onClick="AddChapter();" ID="add">
    </div>
    </br><input type="submit" name="add" value="Add Item"/>
</form>
</body>
</html>

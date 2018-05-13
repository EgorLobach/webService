<%--
  Created by IntelliJ IDEA.
  User: anonymous
  Date: 06.05.2018
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sing In</title>
</head>
<body>
<form action="https://oauth.vk.com/authorize" method="get">
  <input type="hidden" name="client_id" value="6477509">
  <input type="hidden" name="redirect_uri" value="http://localhost:8888/vk">
  <input type="hidden" name="display" value="page">
  <input type="hidden" name="response_type" value="code">
  <input type="hidden" name="v" value="5.74">
  <input type="hidden" name="scope" value="friends">
  <input type="submit" value="VK">
</form>
<form action="https://oauth.yandex.ru/authorize" method="get">
  <input type="hidden" name="client_id" value="0f93b88929f44b5192e981adde363711">
  <input type="hidden" name="display" value="page">
  <input type="hidden" name="response_type" value="code">
  <input type="submit" value="Yandex">
</form>
</body>
</html>
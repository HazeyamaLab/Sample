<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="model.User" %>
<% User user = (User) request.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ユーザー編集画面</title>
</head>
<body>
<jsp:include page="./../helperUIkit.html"/>
<div class="uk-container uk-container-large">
    <form action="/Sample/User/Update" class="uk-grid-small" method="post">
        <div class="uk-margin">
            名前：<input class="uk-text" type="text" id="name" name="name" value="<%= user.getName()%>">
        </div>
        <div class="uk-margin">
            パスワード:<input class="uk-text" type="password" id="pass" name="pass" value="<%= user.getPass()%>">
        </div>
        <input class="uk-text" type="hidden" id="id" name="id" value="<%= user.getId()%>">
        <div class="uk-margin">
            <button class="uk-button uk-button-primary">編集</button>
        </div>
    </form>
</div>
</body>
</html>
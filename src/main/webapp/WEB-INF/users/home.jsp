<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="model.User" %>
<% User user = (User) request.getAttribute("user");%>
<% String name = null;
    if (request.getAttribute("login") != null) {
        name = (String) request.getAttribute("login");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ホーム画面</title>
</head>
<body>
<jsp:include page="../helperUIkit.html"/>
<div class="uk-container uk-container-large">
    <h2><a href="" class=""></a>Hello Gradle!!</h2>
    <h4>
        ログイン状況<br>
        ・ユーザ名:<%= user.getName()%><br>
        ・パスワード:<%= user.getPass()%>
        <form action="/Sample/User/Update" class="uk-grid-small" method="get">
            <input class="uk-text" type="hidden" id="name" name="name" value="<%= user.getName()%>">
            <input class="uk-text" type="hidden" id="pass" name="pass" value="<%= user.getPass()%>">
            <input class="uk-text" type="hidden" id="id" name="id" value="<%= user.getId()%>">
            <button class="uk-button uk-button-link">編集はこちらから</button>
        </form>
    </h4>
    <a href="/Sample/User/Delete?id=<%=user.getId()%>" class="uk-button uk-button-danger">削除</a>
</div>
</body>
<script>
    <% if(name != null){
    %>
    var json = [
        {
            name: "ログインに成功しました！"
        }
    ]
    alert(json[0].name);
    <%}else{
    }%>
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<% List<User> userList = (ArrayList<User>) request.getAttribute("userList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ユーザー検索画面</title>
</head>
<body>
<jsp:include page="./../helperUIkit.html"/>
<div class="uk-container uk-container-large">
    <% if (userList.size() == 0) {
    %>
    　<h3>該当するユーザはいません.</h3>
    <%} else {%>
    <table class="uk-table uk-table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>ユーザ名</th>
            <th>パスワード</th>
        </tr>
        </thead>
        <tbody>
        <% for (User user : userList) {%>
        <tr>
            <td><%= user.getId()%>
            </td>
            <td><%= user.getName()%>
            </td>
            <td><%= user.getPass()%>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <%}%>
</div>
</body>
</html>
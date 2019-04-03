<%@ page import="model.Bbs" %>
<%@page import="java.util.ArrayList"%>
<%@ page import = "java.util.List" %>
<% List<Bbs> bbsList = (ArrayList<Bbs>)request.getAttribute("bbs"); %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: keychi
  Date: 2019/04/02
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BBS</title>
</head>
<body>
<jsp:include page="./../helperUIkit.html"/>

<div class="uk-container uk-container-large">
    <h2 class="page-header">新規BBSの作成</h2>
    <hr>
    <form action="/Sample/bbs" method="post">
        <div class="uk-margin">
            <label for="title">掲示板タイトル</label>
            <input class="form-control" type="text" name="title" id="title">
        </div>
        <div class="uk-margin">
            <label for="description">説明</label>
            <textarea class="form-control" name="description" id="description" rows="3" cols="60"></textarea>
        </div>
        <div class="uk-margin">
            <button class="uk-button uk-button-primary">作成</button>
        </div>
    </form>
    <% if(bbsList.size() > 0) { %>
    <h2 class="page-header">作成済みBBS:<%=bbsList.size() %>件</h2>
    <hr>
    <% for(Bbs bbs : bbsList) { %>
    <div class="card border-secondary mb-3">
        <div class="card-header">
            <h4 class="panel-title"><a class="text-dark" href="/Sample/bbs/post?id=<%=bbs.getBbsId()%>"><%=bbs.getTitle() %></a>
                &nbsp;
                <a class="text-info" href="/Sample/bbs/update?id=<%=bbs.getBbsId() %>"><span class="oi oi-pencil"></span></a></h4>
        </div>
        <div class="card-body">
            <p><%=bbs.getDescription() %></p>
            <div class="text-right"><a class="btn btn-danger" href="/Sample/bbs/delete?id=<%=bbs.getBbsId() %>"><span class="oi oi-trash"></span></a></div>
        </div>
        <div class="card-fotter">
            <div class=text-right>作成日：<%=bbs.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) %></div>
        </div>
    </div>
    <% } %>
    <% }else{ %>
    <hr>
    <div class="alert alert-warning">
        掲示板はありません．
    </div>
    <% } %>
</div>
</body>

</html>

<%--
  Created by IntelliJ IDEA.
  User: keychi
  Date: 2019/04/02
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "model.Bbs" %>
<% Bbs bbs = (Bbs)request.getAttribute("bbs"); %>
<html>
<head>
    <title>編集</title>
</head>
<body>
<jsp:include page="./../helperUIkit.html"/>
<div class="uk-container uk-container-large">
    <h2 class="page-header">作成済みBBSの編集</h2>
    <hr>
    <form action="/Sample/bbs/update" method="post">
        <div class="uk-margin">
            <label for="title">掲示板タイトル</label>
            <input class="formcontrol" type="text" name="title" id="title"  value="<%=bbs.getTitle() %>">
        </div>
        <div class="uk-margin">
            <label for="description">説明</label>
            <textarea class="form-control" name="description" id="description" rows="3" cols="60"><%=bbs.getDescription() %></textarea>
        </div>
        <div class="uk-margin">
            <input type="hidden" name="bbsId" value="<%=bbs.getBbsId() %>" >
            <button class="uk-button uk-button-primary">編集</button>
        </div>
    </form>
</div>
</body>
</html>

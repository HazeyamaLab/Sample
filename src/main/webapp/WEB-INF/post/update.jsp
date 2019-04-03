<%--
  Created by IntelliJ IDEA.
  User: keychi
  Date: 2019/04/02
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "model.Post" %>
<% Post post = (Post)request.getAttribute("post"); %>
<html>
<head>
    <title>掲示板編集</title>
</head>
<body>
<jsp:include page="./../helperUIkit.html"/>
<div class="uk-container uk-container-large">
    <h2 class="page-header">投稿内容の編集</h2>
    <hr>
    <form action="/Sample/bbs/post/update" class="uk-grid-small" method="post">
        <div class="uk-margin">
            <label for="title">投稿タイトル</label>
            <input class="formcontrol" type="text" name="title" id="title" value="<%=post.getTitle() %>">
        </div>
        <div class="uk-margin">
            <label for="body">投稿内容</label>
            <textarea class="form-control" name="body" id="body" rows="5" cols="60"><%=post.getBody() %></textarea>
        </div>
        <div class="uk-margin">
            <input type="hidden" name="postId" value="<%=post.getPostId() %>">
            <input type="hidden" name="bbsId" value="<%=post.getBbsId() %>" >
            <button class="uk-button uk-button-primary">編集</button>
        </div>
    </form>
</div>

</body>
</html>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="model.Post" %>
<%@ page import = "model.Bbs" %>
<%@ page import = "java.util.List" %>
<% List<Post> posts = (ArrayList<Post>)request.getAttribute("posts"); %>
<% Bbs bbs = (Bbs)request.getAttribute("bbs"); %>
<%--
  Created by IntelliJ IDEA.
  User: keychi
  Date: 2019/04/02
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="./../helperUIkit.html"/>
<div class="uk-container uk-container-large">
    <h2 class="page-header"><%=bbs.getTitle() %></h2>
    <hr>
    <h4 class="page-header">新規投稿</h4>
    <form action="/Sample/bbs/post" class="uk-grid-small" method="post" >
        <div class="uk-margin">
            <label for="title">投稿タイトル</label>
            <input class="formcontrol" type="text" name="title" id="title">
        </div>
        <div class="uk-margin">
            <label for="body">投稿内容</label>
            <textarea class="form-control" name="body" id="body" rows="5" cols="60"></textarea>
        </div>
        <div class="uk-margin">
            <input type="hidden" name="bbsId" value="<%=bbs.getBbsId() %>" >
            <button class="uk-button uk-button-primary">投稿</button>
        </div>
    </form>
    <hr>
    <% if(posts.size() > 0) { %>
    <% for(Post post : posts){ %>
    <div class="card">
        <div class="card-header">
            <%=post.getContributorId() %> さんの書き込み
        </div>
        <div class="card-body">
            <h5 class="card-title"><%=post.getTitle() %></h5>
            <hr>
            <p class="card-text">
                <%=post.getBody() %>
            </p>

            <div class="text-right">
                <a href="/Sample/bbs/post/update?id=<%=post.getPostId() %>" class="btn btn-secondary"><span class="oi oi-pencil">編集</span></a>
                <a href="/Sample/bbs/post/delete?id=<%=post.getPostId() %>&bbsId=<%=post.getBbsId() %>" class="btn btn-danger"><span class="oi oi-trash"></span></a>
            </div>

        </div>
    </div>
    <br>
    <% } %>
    <% }else{ %>
    <div class="alert alert-warning">
        投稿はありません．
    </div>
    <% } %>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ログイン画面</title>
</head>
<body>
<jsp:include page="./helperUIkit.html"/>
<div class="uk-container uk-container-large">
    <form class="uk-grid-small" action="/Sample/Login"
          method="post">
        <div class="uk-margin">
            名前：<input class="uk-text" type="text" id="name" name="name">
        </div>
        <div class="uk-margin">
            パスワード:<input class="uk-text" type="password" id="pass" name="pass">
        </div>
        <div class="uk-margin">
            <button class="uk-button uk-button-primary">ログイン</button>
        </div>
    </form>
    <a href="/Sample/User/Create" class="uk-button uk-button-link">新規作成はこちらから</a>
</div>
</body>
</html>
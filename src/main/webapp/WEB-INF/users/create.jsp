<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新規作成</title>
</head>
<body>
<jsp:include page="./../helperUIkit.html"/>
<div class="uk-container uk-container-large">
    <form action="/Sample/User/Create" class="uk-grid-small" method="post">
        <div class="uk-margin">
            名前：<input class="uk-text" type="text" id="name" name="name">
        </div>
        <div class="uk-margin">
            パスワード:<input class="uk-text" type="password" id="pass" name="pass">
        </div>
        <div class="uk-margin">
            <button class="uk-button uk-button-primary">新規作成</button>
        </div>
    </form>
</div>
</body>
</html>

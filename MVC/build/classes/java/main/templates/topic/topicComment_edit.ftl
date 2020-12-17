<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>修改 评论 的页面</title>
</head>
<body>
    <h1>修改 评论</h1>
    <form action="/topicComment/update" method="post">
        <input name="id" placeholder="id" value="${comment.id}">
        <input name="content" placeholder="content" value="${comment.content}">
        <input type="text" name="topicId" value="${topicId}" hidden>
        <br>
        <button type="submit">提交修改</button>
    </form>
</body>
</html>

<!DOCTYPE HTML>
<html>
<head>
    <title>${topicWithComments.title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
    <h1>${topicWithComments.title}</h1>
    <p>
        ${topicWithComments.content}
    </p>
    <form action="/topicComment/add" method="post">
        <input type="text" name="topicId" value="${topicId}" hidden>
        <input type="text" name="content" placeholder="请输入正文">
        <br>
        <button type="submit">评论</button>
    </form>

    <#list topicWithComments.commentList as c>
        ${c.id}: ${c.user.username}:${c.content}<a href="/topicComment/edit?id=${c.id}&topicId=${topicId}">编辑</a>:<a href="/topicComment/delete?id=${c.id}&topicId=${topicId}">删除</a>
        <br>

    </#list>

</body>
</html>
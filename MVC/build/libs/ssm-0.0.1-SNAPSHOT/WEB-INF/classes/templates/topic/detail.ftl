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

    <#list topicWithComments.commentList as c>
        ${c.id}: ${c.user.username}:${c.content}
        <br>

    </#list>

</body>
</html>
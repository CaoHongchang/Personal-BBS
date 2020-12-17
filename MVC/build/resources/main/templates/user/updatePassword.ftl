<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
</head>
<body>
    <form action="/reset/update" method="post">
        <input type="text" name="token" value="${token}" hidden>
        <input type="text" name="password" placeholder="请输入新密码">
        <br>
        <button type="submit">确认</button>
    </form>
</body>
</html>
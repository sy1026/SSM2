<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/13
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/student/addStu" method="get">
    姓名：<input type="text" name="sname">
    年龄：<input type="text" name="age">

    性别：<input type="radio" name="gender" value="男">男
    <input type="radio" name="gender" value="女">女
    地址：<input type="text" name="address">
    <input type="submit" value="新增">
</form>
</body>
</html>

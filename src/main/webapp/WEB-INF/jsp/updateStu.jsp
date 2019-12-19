<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/13
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/student/updateStu" method="post">
    <input type="hidden" name="id" value="${stu.id}">
    姓名：<input type="text" name="sname" value="${stu.sname}">
    年龄：<input type="text" name="age" value="${stu.age}">

    性别：<input type="text" name="gender" value="${stu.gender}">

    地址：<input type="text" name="address" value="${stu.address}">
    <input type="submit" value="修改">
</form>
</body>
</html>

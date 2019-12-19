<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/10
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="/js/jquery-3.2.1.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap-table.js"></script>
    <script src="/js/jqPaginator.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-table.css">
</head>
<body>
<a href="/toAddStu">新增</a>
<form id="frm" action="/getAllStus" method="post">
    性别<input type="text" name="gender" value="${gender}" id="gender">
    地址<input type="text" name="address" value="${address}" id="address">
    <input type="hidden" id="pageNum" name="pageNum">
    <input type="submit" value="查询">

</form>
<a href="/student/exportExcel">导出学生数据</a>
<table border="1">
    <thead>
    <tr>
        <td>id</td>
        <td>sname</td>
        <td>age</td>
        <td>gender</td>
        <td>address</td>
        <td>operation</td>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${pageInfo.list}" var="stu">
        <tr>
            <td>${stu.id}</td>
            <td>${stu.sname}</td>
            <td>${stu.age}</td>
            <td>${stu.gender}</td>
            <td>${stu.address}</td>
            <td>
                    <%--onclick="updateStu(${stu.id})"--%>
                <%--<shiro:hasPermission name="student:update">--%>
                    <a href="/student/toUpdateStu/${stu.id}">修改</a>
                <%--</shiro:hasPermission>--%>
                <shiro:hasPermission name="student:delete">
                    <a href="/student/delStus/${stu.id}">删除</a>
                </shiro:hasPermission>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination-layout">

    <div class="pagination">
        <ul class="pagination">

        </ul>


    </div>
</div>
<script>
    window.onload = function () {
        var if_fistime = true;
        $(".pagination").jqPaginator({
            totalPages:${pageInfo.pages},
            visiblePages: 5,
            currentPage:${pageInfo.pageNum},
            first: '<li class="first"><a href="javascript:void(0);">第一页</a></li>',
            prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
            last: '<li class="last"><a href="javascript:void(0);">最后一页</a></li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',

            onPageChange: function (num) {


                /* alert(num); */
                if (if_fistime) {
                    if_fistime = false;
                } else {
                    changePage(num);
                }
            }

        })

    }

    function changePage(num) {

        $("#pageNum").val(num);
        $("#frm").submit();


//        location.href="/stuPage?pageNum="+num+"&gender="+$("#gender").val()+"&address="+$("#address").val();


    }
</script>
</body>
</html>

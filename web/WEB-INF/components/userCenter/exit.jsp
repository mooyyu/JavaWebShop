<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-25
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="alert alert-info" role="alert">
    退出登录
</div>
<hr>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">是否确认退出登录?</h1>
        <button id="exit" onclick="window.location.href = '/shop/exitLoginServlet';" type="button" class="btn btn-outline-danger">退出登录</button>
    </div>
</div>
<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-27
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/resource/css/bootstrap.min.css">

    <title></title>
</head>
<body style="min-height: 100vh; position: relative;">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/resource/js/jquery.min.js"></script>
<script src="/resource/js/popper.min.js"></script>
<script src="/resource/js/bootstrap.min.js"></script>

<!-- 开发环境版本，包含了有帮助的命令行警告 -->
<script src="/resource/js/vue.js"></script>

<script src="/resource/js/axios.min.js"></script>

<c:choose>
    <c:when test="${requestScope.ans == 1}">
        <div class="container jumbotron" style="max-width: 800px;">
            <h1 class="display-3">验证成功</h1>
            <hr class="my-4">
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/login.jsp" role="button">点此登录</a>
            </p>
        </div>
    </c:when>
    <c:when test="${requestScope.ans == 0}">
        <div class="container jumbotron" style="max-width: 800px;">
            <h1 class="display-3">链接已过期</h1>
            <hr class="my-4">
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/index.jsp" role="button">点此返回首页</a>
            </p>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container jumbotron" style="max-width: 800px;">
            <h1 class="display-3">验证失败</h1>
            <hr class="my-4">
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/index.jsp" role="button">点此返回首页</a>
            </p>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
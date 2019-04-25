<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-24
  Time: 15:09
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

<jsp:include page="../components/header.jsp"></jsp:include>

<div class="container row mx-auto border py-5" style="max-width: 1200px;">
    <div class="col-3">
        <c:choose>
            <c:when test="${empty param.aside}">
                <jsp:include page="../components/userCenter/aside.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="../components/userCenter/aside.jsp?aside=${param.aside}"></jsp:include>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="col-1"></div>
    <div class="col-8">
        <c:choose>
            <c:when test="${param.aside.equals('bookshelf')}">
                <jsp:include page="../components/userCenter/bookshelf.jsp"></jsp:include>
            </c:when>
            <c:when test="${param.aside.equals('order')}">
                <jsp:include page="../components/userCenter/order.jsp"></jsp:include>
            </c:when>
            <c:when test="${param.aside.equals('collect')}">
                <jsp:include page="../components/userCenter/collect.jsp"></jsp:include>
            </c:when>
            <c:when test="${param.aside.equals('message')}">
                <jsp:include page="../components/userCenter/message.jsp"></jsp:include>
            </c:when>
            <c:when test="${param.aside.equals('standings')}">
                <jsp:include page="../components/userCenter/standings.jsp"></jsp:include>
            </c:when>
            <c:when test="${param.aside.equals('exit')}">
                <jsp:include page="../components/userCenter/exit.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="../components/userCenter/default.jsp"></jsp:include>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
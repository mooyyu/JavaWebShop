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
<body style="min-height: 100vh; min-width: 1100px; position: relative;">
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
        <c:set var="aside" value="${empty param.aside ? 'default' : param.aside}"></c:set>
        <c:choose>
            <c:when test="${aside.equals('default') ||
                            aside.equals('balance') ||
                            aside.equals('bookshelf') ||
                            aside.equals('sold') ||
                            aside.equals('bought') ||
                            aside.equals('collect') ||
                            aside.equals('message') ||
                            aside.equals('standings') ||
                            aside.equals('exit')}">
                <jsp:include page="../components/userCenter/${aside}.jsp"></jsp:include>
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
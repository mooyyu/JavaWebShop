<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-05-02
  Time: 21:24
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

<div class="container" style="max-width: 900px;">
    <p class="badge">分类: <span class="badge badge-pill badge-info">${item.catagory.name}</span></p>
    <div class="row">
        <div class="col-3">
            <img class="rounded w-100 my-3" src="/resource/book_img/${item.image_w}">
            <div class="w-100">
                <button type="button" class="btn btn-outline-primary d-block m-auto">☆</button>
            </div>
        </div>
        <div class="col-1"></div>
        <div class="col-8">
            <p class="h3">${item.name}</p>
            <hr class="my-3">
            <p>作者: ${item.author}</p>
            <p>新旧程度: ${item.hownew}</p>
            <p class="text-success">¥<text class="h3">${item.price}</text></p>
            <button type="button" class="btn btn-outline-warning">积分兑换</button>
            <button type="button" class="btn btn-success">立即购买</button>
            <hr class="my-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active" aria-current="page">内容简介</li>
                </ol>
            </nav>
            <div class="text-muted" style="white-space: pre-wrap;">${item.info}</div>
        </div>
    </div>
</div>

<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
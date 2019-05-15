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

<script>
    var curPage = "showBookItem";
    var isLogin = ${empty cookie.isLogin ? false : cookie.isLogin.value};
    if (isLogin) { var logined_email = "${cookie.logined_email.value}"; var check_str = "${cookie.check_str.value}"; var userId = "${cookie.userId.value}"; }
    var collectStatus = ${collectStatus};
    var uuid = "${param.uuid}";
</script>

<div class="container" style="max-width: 900px;">
    <p class="badge">分类: <a href="/?cataId=${item.catagoryId}" class="badge badge-pill badge-info">${item.catagory.name}</a></p>
    <div class="row">
        <div id="collect" class="col-3">
            <img class="rounded w-100 my-3" src="/book_img/${item.image}">
            <div class="w-100">
                <button v-on:click="doCollect" type="button" class="btn btn-outline-primary d-block m-auto">{{curStatus}}</button>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="collectAlert" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">收藏</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            {{collectAns}}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
        <div id="order" class="col-8">
            <p class="h3">${item.name}</p>
            <hr class="my-3">
            <p>作者: ${item.author}</p>
            <p>新旧程度: ${item.hownew}</p>
            <p class="text-success h3">¥${item.price}</p>
            <button v-on:click="exchange" type="button" class="btn btn-outline-warning">积分兑换</button>
            <button v-on:click="buy" type="button" class="btn btn-success">立即购买</button>
            <hr class="my-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active" aria-current="page">内容简介</li>
                </ol>
            </nav>
            <div class="text-muted" style="white-space: pre-wrap;">${item.info}</div>

            <!-- Modal -->
            <div class="modal fade" id="orderAlert" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">订单</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            {{orderAns}}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/resource/function/collect.js?v=1.4"></script>
<script src="/resource/function/order.js?v=1.2"></script>

<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
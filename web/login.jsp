<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-18
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div id="login" class="container jumbotron">
    <div class="alert alert-primary text-center" role="alert">登录</div>

    <div class="form-group row">
        <label for="email" class="col-sm-2 col-form-label">邮箱:</label>
        <div class="col-sm-10">
            <input id="email" v-model:value="email" type="text" class="form-control" placeholder="请输入注册邮箱">
        </div>
    </div>
    <div class="form-group row">
        <label for="password" class="col-sm-2 col-form-label">密码:</label>
        <div class="col-sm-10 input-group">
            <input v-on:keyup.enter="submit" id="password" v-model:value="password" type="password" class="form-control" placeholder="请输入密码">
            <div class="input-group-prepend">
                <button class="btn bg-danger" type="button" v-on:click="submit">Submit</button>
            </div>
        </div>
    </div>

    <a href="?method=register" class="badge badge-pill badge-warning float-right">还没有账号,注册一个</a>
</div>

<!-- Modal -->
<div class="modal fade" id="modalalert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">登录失败</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                邮箱或密码错误！
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="./WEB-INF/components/footer.jsp"></jsp:include>

<script src="resource/function/login.js"></script>
</body>
</html>
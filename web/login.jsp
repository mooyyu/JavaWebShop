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

<div id="login" class="container jumbotron ${param.method == "register" ? "d-none" : ""}">
    <div class="alert alert-primary text-center" role="alert">登录</div>

    <div class="form-group row">
        <label for="email" class="col-sm-2 col-form-label">邮箱:</label>
        <div class="col-sm-10">
            <input v-model:value="email" type="text" class="form-control" placeholder="请输入注册邮箱">
        </div>
    </div>
    <div class="form-group row">
        <label for="password" class="col-sm-2 col-form-label">密码:</label>
        <div class="col-sm-10 input-group">
            <input v-on:keyup.enter="submit" v-model:value="password" type="password" class="form-control" placeholder="请输入密码">
            <div class="input-group-prepend">
                <button class="btn bg-danger" type="button" v-on:click="submit">Submit</button>
            </div>
        </div>
    </div>

    <a href="?method=register" class="badge badge-pill badge-warning float-right">还没有账号,注册一个</a>

    <!-- Modal -->
    <div class="modal fade" id="loginalert" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">登录失败</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    {{loginAlert}}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="register" class="container jumbotron ${param.method == "register" ? "" : "d-none"}">
    <div class="alert alert-info text-center" role="alert">注册</div>

    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">*昵称:</label>
        <div class="col-sm-10">
            <input id="name" v-model:value="name" type="text" class="form-control" placeholder="请输入昵称">
        </div>
    </div>
    <div class="form-group row">
        <label for="email" class="col-sm-2 col-form-label">*邮箱:</label>
        <div class="col-sm-10">
            <input id="email" v-model:value="email" type="text" class="form-control" placeholder="请输入注册邮箱">
        </div>
    </div>
    <fieldset class="form-group">
        <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">*性别:</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input v-model="sex" class="form-check-input" type="radio" name="sex" value="1" checked>
                    <label class="form-check-label" for="gridRadios1">
                        小公子
                    </label>
                </div>
                <div class="form-check">
                    <input v-model="sex" class="form-check-input" type="radio" name="sex" value="2">
                    <label class="form-check-label" for="gridRadios2">
                        小姑凉
                    </label>
                </div>
            </div>
        </div>
    </fieldset>
    <div class="form-group row">
        <label for="inputphone" class="col-sm-2 col-form-label">手机号:</label>
        <div class="col-sm-10">
            <input v-model="phone" type="number" oninput="if(value.length>11)value=value.slice(0,11)" class="form-control" name="phone" placeholder="请输入手机号">
        </div>
    </div>
    <div class="form-group row">
        <label for="inputarea" class="col-sm-2 col-form-label">收货地址:</label>
        <div class="col-sm-10">
            <textarea id="address" maxlength="100" rows="2" class="form-control" name="address" placeholder="请输入收货地址"></textarea>
        </div>
    </div>
    <div class="form-group row">
        <label for="inputinfo" class="col-sm-2 col-form-label">个人简介:</label>
        <div class="col-sm-10">
            <textarea id="info" maxlength="250" rows="6" class="form-control" name="info" placeholder="介绍你自己吧"></textarea>
        </div>
    </div>
    <div class="form-group row">
        <label for="pwd" class="col-sm-2 col-form-label">*密码:</label>
        <div class="col-sm-10">
            <input id="pwd" v-model:value="pwd" type="password" maxlength="26" class="form-control" placeholder="请输入密码">
        </div>
    </div>
    <div class="form-group row">
        <label for="confirmPwd" class="col-sm-2 col-form-label">*确认密码:</label>
        <div class="col-sm-10 input-group">
            <input v-on:keyup.enter="register" id="confirmPwd" v-model:value="confirmPwd" type="password" maxlength="26" class="form-control" placeholder="请再次输入密码">
            <div class="input-group-prepend">
                <button id="registerButton" class="btn bg-danger" type="button" v-on:click="register">Register</button>
            </div>
        </div>
    </div>

    <a href="login.jsp" class="badge badge-pill badge-warning float-right">已有账号,直接登录</a>

    <!-- Modal -->
    <div class="modal fade" id="registeralert" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">注册结果</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    {{registerAns}}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="./WEB-INF/components/footer.jsp"></jsp:include>

<script src="resource/function/login.js?v=1.7"></script>
</body>
</html>
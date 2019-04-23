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
<body>
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

    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <button class="btn btn-outline-secondary" type="button">邮箱</button>
        </div>
        <input id="email" v-model:value="email" type="text" class="form-control" placeholder="请输入注册邮箱">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <button class="btn btn-outline-secondary" type="button">密码</button>
        </div>
        <input id="password" v-model:value="password" type="password" class="form-control" placeholder="请输入密码">
        <div class="input-group-prepend">
            <button class="btn bg-danger" type="button" v-on:click="submit">Submit</button>
        </div>
    </div>

    <a href="?medthod=register" class="badge badge-pill badge-warning float-right">还没有账号,注册一个</a>
</div>

<script>
    var loginapp = new Vue({
        el: "div#login",
        data: {
            email: "",
            password: ""
        },
        methods: {
            submit: function() {
                axios.post('/shop/checkLoginServlet?method=login', {
                    email: loginapp.email,
                    password: loginapp.password
                }).then(function(res) {
                    if (res.data == "yes") {
                        window.location.href = "/";
                    } else {
                        alert("邮箱或密码错误！");
                    }
                }).catch(function(error) {
                    console.info(error);
                });
            }
        }
    })
</script>
</body>
</html>
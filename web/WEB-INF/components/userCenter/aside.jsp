<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-24
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    var curItem = "${param.aside}";
</script>

<div class="card" style="width: 18rem;">
    <img class="card-img-top w-50 mt-5 mx-auto" src="/resource/img/${cookie.userSex.value == "2" ? "female" : "male"}.svg" alt="Card image cap">
    <div class="card-body">
        <p class="card-text text-center">${cookie.userName.value}</p>
    </div>
    <div class="list-group mb-5">
        <a id="default" href="userCenterServlet" class="list-group-item list-group-item-action active disabled">我的内容</a>
        <a id="bookshelf" href="?aside=bookshelf" class="list-group-item list-group-item-action">我的书架</a>
        <a id="order" href="?aside=order" class="list-group-item list-group-item-action">我的订单</a>
        <a id="collect" href="?aside=collect" class="list-group-item list-group-item-action">收藏阁</a>
        <a id="message" href="?aside=message" class="list-group-item list-group-item-action">消息通知</a>
        <a id="standings" href="?aside=standings" class="list-group-item list-group-item-action">积分</a>
        <a id="exit" href="?aside=exit" class="list-group-item list-group-item-action">退出登录</a>
    </div>
</div>

<script src="/resource/function/asideCurActive.js?v=1.0"></script>
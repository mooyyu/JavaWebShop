<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-15
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mb-4" style="height: 56px;"></div>
<nav class="fixed-top navbar navbar-expand-lg navbar-dark bg-dark" id="header">
    <span class="navbar-brand">分享书城</span>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" target="_blank" href="https://github.com/mooyyu/JavaWebShop">
                    <img class="d-block" style="height: 24px;" src="/resource/img/github.svg">
                </a>
            </li>
            <li class="nav-item dropdown">
                <a href="#" class="btn btn-outline-dark text-white dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">友链</a>
                <div class="dropdown-menu" aria-labelledby="friendsDropdown">
                    <a href="https://mooyyu.cn" target="_blank" class="dropdown-item">mooyyu.cn</a>
                    <div class="dropdown-divider"></div>
                    <a href="https://blog.mooyyu.cn" target="_blank" class="dropdown-item">Blog</a>
                    <a href="http://toys.mooyyu.cn" target="_blank" class="dropdown-item">Toys!</a>
                </div>
            </li>
            <li id="user_center" class="nav-item ${empty cookie.userName.value ? "" : "dropdown"}">
                <c:choose>
                    <c:when test="${empty cookie.userName.value}">
                        <a class="nav-link" href="/login.jsp">游客.请登录</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-outline-dark text-white dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${cookie.userName.value}</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/shop/userCenterServlet">个人中心</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/shop/userCenterServlet?aside=balance">账户余额</a>
                            <a class="dropdown-item" href="/shop/userCenterServlet?aside=bookshelf">我的书架</a>
                            <a class="dropdown-item" href="/shop/userCenterServlet?aside=sold">我卖出的</a>
                            <a class="dropdown-item" href="/shop/userCenterServlet?aside=bought">我买入的</a>
                            <a class="dropdown-item" href="/shop/userCenterServlet?aside=collect">收藏阁</a>
                            <a class="dropdown-item" href="/shop/userCenterServlet?aside=message">消息通知</a>
                            <a class="dropdown-item" href="/shop/userCenterServlet?aside=standings">积分</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/shop/userCenterServlet?aside=exit">退出登录</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
        <span class="form-inline my-2 my-lg-0" id="search">
            <input maxlength="10" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" v-model="searchString" v-on:keyup.enter="dosearch">
            <button class="btn btn-outline-success my-2 my-sm-0" v-on:click="dosearch">Search</button>
        </span>
    </div>
</nav>

<!-- Modal -->
<div class="modal fade" id="searchalert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">搜索</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                请输入搜索内容！
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script src="/resource/function/search.js?v=1.6"></script>
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
    <a class="navbar-brand" href="#">分享书城</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li id="user_center" class="nav-item">
                <c:choose>
                    <c:when test="${empty cookie.userName.value}">
                        <a class="nav-link" href="/login.jsp">游客.请登录</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="/shop/userCenterServlet">${cookie.userName.value}</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
        <span class="form-inline my-2 my-lg-0" id="search">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" v-model="searchString" v-on:keyup.enter="dosearch">
            <button class="btn btn-outline-success my-2 my-sm-0" v-on:click="dosearch">Search</button>
        </span>
    </div>
</nav>

<script src="/resource/function/search.js?v=1.4"></script>
<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-15
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4" id="header">
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
                <a class="nav-link" v-bind:href="hrefValue">{{cur_user}}.{{center_link}}</a>
            </li>
        </ul>
        <span class="form-inline my-2 my-lg-0" id="search">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" v-model="searchString">
            <button class="btn btn-outline-success my-2 my-sm-0" v-on:click="dosearch">Search</button>
        </span>
    </div>
</nav>

<script src="/resource/function/getCurUser.js?v=1.3"></script>
<script src="/resource/function/search.js?v=1.4"></script>
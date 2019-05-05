<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-25
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="shop.Dao.BookItemDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="userId" value="${cookie.userId.value}"></c:set>
<c:set var="bookshelf" value='<%=new BookItemDao().findAllfromUserId(Integer.valueOf((String)pageContext.getAttribute("userId")))%>'></c:set>

<script>
    var itemlistsize = ${bookshelf.size()};
</script>

<div class="alert alert-info row" role="alert">
    <div class="col-6 my-auto">我的书架</div>
    <div class="d-flex align-items-end col-6">
        <button type="button" class="btn btn-lg btn-outline-success btn-sm ml-auto">新建分享</button>
    </div>
</div>
<hr>

<div class="jumbotron jumbotron-fluid ${bookshelf.size() != 0 ? "d-none" : ""}">
    <div class="container">
        <h1 class="display-4">你的书架空空如也,快来分享一本吧～～</h1>
    </div>
</div>

<div id="pagination" class="container">
    <c:forEach var="item" items="${bookshelf}">
        <div id="${item.rownum}" class="row border rounded my-1 py-1 d-none" style="height: 75px;" >
            <div class="col-2" style="min-width: 80px;">
                <img uuid="${item.uuid}" class="h-100" v-on:click="showItem" src="/resource/book_img/${item.image}">
            </div>
            <div class="col-10 my-auto">
                <p class="card-text text-nowrap text-truncate h5" style="font-weight: normal;">${item.name}</p>
                <div class="row">
                    <p class="text-success h6 col">¥ ${item.price}</p>
                    <div class="d-flex align-items-end col">
                        <a href="/shop/showBookItemServlet?uuid=${item.uuid}" class="btn btn-info btn-lg btn-sm ml-auto" role="button">查看详情</a>
                        <a href="/shop/editBookItemServlet?uuid=${item.uuid}" class="btn btn-outline-warning btn-lg btn-sm ml-3" role="button">编辑</a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="container float-left">
        <jsp:include page="../../components/pagination.jsp?v=1.1"></jsp:include>
    </div>
</div>

<script src="/resource/function/pagination.js?v=1.5"></script>
<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-25
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="shop.Dao.collectDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="userId" value="${cookie.userId.value}"></c:set>
<c:set var="collectList" value='<%=new collectDao().getList(Integer.valueOf((String)pageContext.getAttribute("userId")))%>'></c:set>

<script>
    var curPage = "userCollect";
    var itemlistsize = ${collectList.size()};
</script>

<div class="alert alert-info row" role="alert">
    <div class="col-6 my-auto">收藏阁</div>
    <div class="d-flex align-items-end col-6">
        <button id="clearAll" data-toggle="modal" data-target="div#userCollectAlert" type="button" class="btn btn-lg btn-outline-danger btn-sm ml-auto ${collectList.size() == 0 ? "d-none" : ""}">清空收藏阁</button>
    </div>
</div>
<hr>

<div class="jumbotron jumbotron-fluid ${collectList.size() != 0 ? "d-none" : ""}">
    <div class="container">
        <h1 class="display-4">你的收藏阁空空如也,快去找找书吧～～</h1>
    </div>
</div>

<div id="pagination" class="container">
    <c:forEach var="item" items="${collectList}">
        <div id="${item.rownum}" class="row border rounded my-1 py-1 d-none" style="height: 75px;" >
            <div class="col-2" style="min-width: 80px;">
                <img uuid="${item.uuid}" class="h-100" v-on:click="showItem" src="/book_img/${item.image}">
            </div>
            <div class="col-10 my-auto">
                <p class="card-text text-nowrap text-truncate h5" style="font-weight: normal;">${item.name}</p>
                <div class="row">
                    <p class="text-success h6 col">¥ ${item.price}</p>
                    <div class="d-flex align-items-end col">
                        <button type="button" onclick="clearCollect(event)" uuid="${item.uuid}" class="btn btn-lg btn-outline-warning btn-sm ml-auto">取消收藏</button>
                        <a href="/shop/showBookItemServlet?uuid=${item.uuid}" class="btn btn-info btn-lg btn-sm ml-3" role="button">详情</a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="container float-left">
        <jsp:include page="../../components/pagination.jsp?v=1.1"></jsp:include>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="userCollectAlert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">取消收藏</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                即将清空收藏阁,是否确认？
            </div>
            <div class="modal-footer">
                <button type="button" onclick="clearAll()" class="btn btn-outline-danger" data-dismiss="modal">确认</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script src="/resource/function/pagination.js?v=1.5"></script>
<script src="/resource/function/collect.js?v=1.9"></script>
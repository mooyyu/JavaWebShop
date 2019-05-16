<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-05-16
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="shop.Dao.orderDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="buyerId" value="${cookie.userId.value}"></c:set>
<c:set var="bought" value='<%=new orderDao().getBought(Integer.valueOf((String)pageContext.getAttribute("buyerId")))%>'></c:set>

<script>
    var itemlistsize = ${bought.size()};
</script>

<div class="alert alert-success" role="alert">
    我买到的
</div>
<hr>
<div class="jumbotron jumbotron-fluid ${bought.size() != 0 ? "d-none" : ""}">
    <div class="container">
        还没有买到一本书哦～～
    </div>
</div>

<div id="pagination" class="container">
    <c:set var="statusInfo" value='<%=new String[] {"已取消", "等待发货", "等待收货", "已完成"}%>'></c:set>
    <c:set var="statusColor" value='<%=new String[] {"secondary", "primary", "warning", "success"}%>'></c:set>
    <c:forEach var="item" items="${bought}">
        <div id="${item.rownum}" class="row border rounded my-1 py-1 d-none" style="height: 75px;" >
            <div class="col-2" style="min-width: 80px;">
                <img uuid="${item.uuid}" class="h-100" v-on:click="showItem" src="/book_img/${item.image}">
            </div>
            <div class="col-10 my-auto">
                <p class="card-text text-nowrap text-truncate h5" style="font-weight: normal;"><span class="badge badge-pill badge-${statusColor[item.orderStatus]}">${statusInfo[item.orderStatus]}</span>${item.name}</p>
                <div class="d-flex justify-content-end w-100">
                    <c:choose>
                        <c:when test="${item.orderStatus == 1}">
                            <button type="button" class="btn btn-sm btn-outline-danger">取消</button>
                        </c:when>
                        <c:when test="${item.orderStatus == 2}">
                            <button type="button" class="btn btn-sm btn-outline-dark">确认收货</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="container float-left">
        <jsp:include page="../../components/pagination.jsp?v=1.1"></jsp:include>
    </div>
</div>

<script src="/resource/function/pagination.js?v=1.5"></script>
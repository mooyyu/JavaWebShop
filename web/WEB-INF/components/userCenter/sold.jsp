<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-25
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="shop.Dao.orderDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="sellerId" value="${cookie.userId.value}"></c:set>
<c:set var="sold" value='<%=new orderDao().getSold(Integer.valueOf((String)pageContext.getAttribute("sellerId")))%>'></c:set>

<script>
    var itemlistsize = ${sold.size()};
    var curPage = "sold";
</script>

<div class="alert alert-primary" role="alert">
    我卖出的
</div>
<hr>
<div class="jumbotron jumbotron-fluid ${sold.size() != 0 ? "d-none" : ""}">
    <div class="container">
        还没有卖出去一本书哦～～
    </div>
</div>

<div id="pagination" class="container">
    <c:set var="statusInfo" value='<%=new String[] {"已取消", "等待发货", "等待收货", "已完成"}%>'></c:set>
    <c:set var="statusColor" value='<%=new String[] {"secondary", "primary", "warning", "success"}%>'></c:set>
    <c:forEach var="item" items="${sold}">
        <div id="${item.rownum}" class="row border rounded my-1 py-1 d-none" style="height: 75px;" >
            <div class="col-2" style="min-width: 80px;">
                <img uuid="${item.uuid}" class="h-100" src="${StaticResourceLocation}${item.image}">
            </div>
            <div class="col-10 my-auto">
                <p class="card-text text-nowrap text-truncate h5" style="font-weight: normal;"><span class="badge badge-pill badge-${statusColor[item.orderStatus]}">${statusInfo[item.orderStatus]}</span>${item.name}</p>
                <div class="d-flex justify-content-end w-100">
                    <c:if test="${item.orderStatus == 1}">
                        <button onclick="updateOrder('shipping', '${item.orderId}')" type="button" class="btn btn-sm btn-outline-dark">发货</button>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="container float-left">
        <jsp:include page="../../components/pagination.jsp?v=1.1"></jsp:include>
    </div>
</div>

<script src="/resource/function/pagination.js?v=1.5"></script>
<script src="/resource/function/order.js?v=1.2"></script>
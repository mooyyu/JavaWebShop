<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-25
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="shop.Dao.noticeDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="userId" value="${cookie.userId.value}"></c:set>
<c:set var="notices" value='<%=new noticeDao().getNotices(Integer.valueOf((String)pageContext.getAttribute("userId")))%>'></c:set>

<script>
    var itemlistsize = ${notices.size()};
</script>

<div class="alert alert-info" role="alert">
    消息通知
</div>
<hr>

<div class="jumbotron jumbotron-fluid ${notices.size() != 0 ? "d-none" : ""}">
    <div class="container">
        <h1 class="display-4">还没有任何消息哦～～</h1>
    </div>
</div>

<div id="pagination" class="container">
    <c:forEach var="item" items="${notices}">
        <div id="${item.rownum}" class="rounded mb-1 d-none">
            <div class="alert alert-success" role="alert">
                <h6 class="alert-heading text-dark">${item.time}</h6>
                <hr>
                <p class="mb-0">${item.info}</p>
            </div>
        </div>
    </c:forEach>

    <div class="container float-left">
        <jsp:include page="../../components/pagination.jsp?v=1.1"></jsp:include>
    </div>
</div>

<script src="/resource/function/pagination.js?v=1.5"></script>
<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-15
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, shop.obj.catagory, shop.Dao.catagoryDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="cataList" value="<%=new catagoryDao().getAll()%>"></c:set>

<script>
    var curPage = "index";
    var curItem = "${param.cataId}";
</script>

<div class="col-2 pl-5" id="aside">
    <div class="alert alert-dark" role="alert">
        图书分类
    </div>
    <div class="list-group mb-5">
        <a id="cataNew" href="/" class="list-group-item list-group-item-action">最新</a>
        <c:forEach var="item" items="${cataList}">
            <a id="cata${item.id}" href="?cataId=${item.id}" class="list-group-item list-group-item-action">${item.name}</a>
        </c:forEach>
    </div>
</div>

<script src="/resource/function/asideCurActive.js?v=1.4"></script>
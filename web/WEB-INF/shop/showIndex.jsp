<!--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-15
  Time: 08:31
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/resource/css/bootstrap.min.css">
    
    <title>分享商城</title>
  </head>
  <body style="min-height: 100vh; position: relative;">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="/resource/js/jquery.min.js"></script>
	<script src="/resource/js/popper.min.js"></script>
	<script src="/resource/js/bootstrap.min.js"></script>
    
    <!-- 开发环境版本，包含了有帮助的命令行警告 -->
    <script src="/resource/js/vue.js"></script>
    
    <script src="/resource/js/axios.min.js"></script>

    <script>
      var itemlistsize = ${itemlist.size()};
    </script>

    <jsp:include page="../components/header.jsp"></jsp:include>

    <div class="row">
      <jsp:include page="../components/aside.jsp"></jsp:include>

      <div class="col-9" style="min-height: 80vh;" id="pagination">
        <div class="container">
          <div class="alert alert-primary" role="alert">
            <c:out value="${showTitle}"></c:out>
          </div>
        </div>

        <div class="container-fluid">
          <c:forEach var="item" items="${itemlist}">
            <div class="card float-left m-1 d-none" style="width: 12rem;" id="${item.rownum}" >
              <img uuid="${item.uuid}" v-on:click="showItem" class="card-img-top" src="/resource/book_img/${item.image_b}" alt="Card image cap">
              <div class="card-body">
                <p class="card-text text-nowrap text-truncate">${item.name}</p>
                <ul class="list-group list-group-flush text-nowrap text-truncate">
                  <li class="list-group-item figure-caption">作者:${item.author}</li>
                  <li class="list-group-item figure-caption">新旧程度:${item.hownew}</li>
                  <li class="list-group-item figure-caption">价格:${item.price}</li>
                </ul>
              </div>
            </div>
          </c:forEach>
        </div>

        <div class="container float-left">
          <jsp:include page="../components/pagination.jsp?v=1.0"></jsp:include>
        </div>
      </div>

      <script src="/resource/function/pagination.js?v=1.2"></script>

      <jsp:include page="../components/right_side.jsp"></jsp:include>
    </div>

    <jsp:include page="../components/footer.jsp"></jsp:include>
  </body>
</html>
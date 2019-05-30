<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-05-28
  Time: 08:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/resource/css/bootstrap.min.css">

    <title></title>
</head>
<body style="min-height: 100vh; min-width: 1100px; position: relative;">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/resource/js/jquery.min.js"></script>
<script src="/resource/js/popper.min.js"></script>
<script src="/resource/js/bootstrap.min.js"></script>

<!-- 开发环境版本，包含了有帮助的命令行警告 -->
<script src="/resource/js/vue.js"></script>

<script src="/resource/js/axios.min.js"></script>

<jsp:include page="../components/header.jsp"></jsp:include>

<div id="createBookItem" class="container" style="max-width: 900px;">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelectCatagory">分类:</label>
        </div>
        <select class="custom-select" id="inputGroupSelectCatagory">
            <c:forEach var="cata" items="${cataList}">
                <option ${cata.id == 1 ? "selected" : ""} value="${cata.id}">${cata.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="row">
        <div class="col-3">
            <img id="bookIMG" class="rounded w-100 my-3" src="/resource/img/bookshelf.svg">
            <div class="custom-file">
                <label class="custom-file-label" for="inputGroupIMG" style="font-size: 12px;">350*500;128k</label>
                <input type="file" onchange="checkIMG(this)" accept="image/jpg" class="custom-file-input" id="inputGroupIMG">
            </div>
            <hr>

            <!-- Modal -->
            <div class="modal fade" id="imgModal" tabindex="-1" role="dialog" aria-labelledby="imgModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="imgModalLabel">更新图片</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div id="imgModalBody" class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
        <div class="col-8">
            <div class="form-group row">
                <label for="inputName" class="col-sm-2 col-form-label text-right">书名:</label>
                <div class="col-sm-10">
                    <input v-model="name" maxlength="20" type="text" class="form-control" name="name">
                </div>
            </div>
            <hr class="my-3">
            <div class="form-group row">
                <label for="inputAuthor" class="col-sm-2 col-form-label text-right">作者:</label>
                <div class="col-sm-10">
                    <input v-model="author" maxlength="20" type="text" class="form-control" name="author">
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelectHownew">新旧程度:</label>
                </div>
                <select class="custom-select" id="inputGroupSelectHownew">
                    <c:forEach var="hownew" items="<%=new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9 ,10}%>">
                        <option ${hownew == 10 ? "selected" : ""} value="${hownew}">${hownew}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <label for="inputPrice" class="col-sm-2 col-form-label text-right">价格:</label>
                <div class="col-sm-10">
                    <input v-model="price" type="number" oninput="if(value.length>5)value=value.slice(0,5);if(value<0)value=-value;" class="form-control" name="prce">
                </div>
            </div>

            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active" aria-current="page">
                        <label for="bookInfo">内容简介</label>
                    </li>
                </ol>
            </nav>
            <hr class="my-3">
            <textarea id="bookInfo" maxlength="300" rows="10" class="form-control" name="info"></textarea>
            <hr class="my-3">
            <div class="w-100">
                <button type="button" v-on:click="submit" class="btn btn-outline-danger d-block ml-auto">创建</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="createModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createModalLabel">更新图片</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="createModalBody" class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script src="/resource/function/createBookItem.js?v=1.2"></script>
<script src="/resource/function/uploadIMG.js?v=1.3"></script>

<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: mooyyu
  Date: 2019-04-25
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="shop.obj.user, shop.Dao.userDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="email" value="${cookie.logined_email.value}"></c:set>
<% user u = new userDao().getUser((String)pageContext.getAttribute("email")); %>
<c:set var="user" value="<%=u%>"></c:set>

<div class="alert alert-info" role="alert">我的内容</div>
<hr>
<div id="updateUserForm">
    <div class="form-group row">
        <label for="inputname" class="col-sm-2 col-form-label text-right">昵称:</label>
        <div class="col-sm-10">
            <input v-model="name" type="text" maxlength="10" class="form-control" name="name">
        </div>
    </div>
    <div class="form-group row">
        <label for="inputemail" class="col-sm-2 col-form-label text-right">注册邮箱:</label>
        <div class="col-sm-10">
            <div class="alert alert-light border" role="alert">${user.email}</div>
        </div>
    </div>
    <fieldset class="form-group">
        <div class="row">
            <legend class="col-form-label col-sm-2 pt-0 text-right">性别:</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input v-model="sex" class="form-check-input" type="radio" name="sex" value="1" ${user.sex == 1 ? "checked" : ""}>
                    <label class="form-check-label" for="gridRadios1">
                        小公子
                    </label>
                </div>
                <div class="form-check">
                    <input v-model="sex" class="form-check-input" type="radio" name="sex" value="2" ${user.sex == 2 ? "checked" : ""}>
                    <label class="form-check-label" for="gridRadios2">
                        小姑凉
                    </label>
                </div>
            </div>
        </div>
    </fieldset>
    <div class="form-group row">
        <label for="inputphone" class="col-sm-2 col-form-label text-right">手机号:</label>
        <div class="col-sm-10">
            <input v-model="phone" type="number" oninput="if(value.length>11)value=value.slice(0,11)" class="form-control" name="phone">
        </div>
    </div>
    <div class="form-group row">
        <label for="inputarea" class="col-sm-2 col-form-label text-right">收货地址:</label>
        <div class="col-sm-10">
            <textarea id="address" maxlength="100" rows="2" class="form-control" name="address">${user.address}</textarea>
        </div>
    </div>
    <div class="form-group row">
        <label for="inputinfo" class="col-sm-2 col-form-label text-right">个人简介:</label>
        <div class="col-sm-10">
            <textarea id="info" maxlength="250" rows="6" class="form-control" name="info">${user.info}</textarea>
        </div>
    </div>
    <div class="form-group row justify-content-end">
        <button v-on:click="submit" class="btn btn-primary mr-sm-4">Submit</button>
    </div>
</div>

<script>
    var updateUserApp = new Vue({
        el: "div#updateUserForm",
        data: {
            name: "${user.name}",
            sex: "${user.sex}",
            phone: "${user.phone}"
        },
        methods: {
            submit: function() {
                axios.post('/shop/updateUserServlet', {
                    name: updateUserApp.name,
                    email: "${user.email}",
                    check_str: "${cookie.check_str.value}",
                    sex: updateUserApp.sex,
                    phone: updateUserApp.phone,
                    address: $("textarea#address")[0].value,
                    info: $("textarea#info")[0].value
                }).then(function(res) {
                    if (res.data == "yes") {
                        location.reload();
                    } else {
                        window.location.href = "/";
                    }
                }).catch(function(error) {
                    console.info(error);
                })
            }
        }
    });
</script>

<hr>

<div class="alert alert-danger" role="alert">更改密码</div>
<hr>
<div id="updatePwdForm">
    <div class="form-group row">
        <label for="oldpwd" class="col-sm-2 col-form-label text-right">原密码:</label>
        <div class="col-sm-10">
            <input v-model="oldpwd" type="password" maxlength="26" class="form-control" id="oldpwd" name="oldpwd">
        </div>
    </div>
    <div class="form-group row">
        <label for="newpwd" class="col-sm-2 col-form-label text-right">新密码:</label>
        <div class="col-sm-10">
            <input v-model="newpwd" type="password" maxlength="26" class="form-control" id="newpwd" name="newpwd">
        </div>
    </div>
    <div class="form-group row">
        <label for="confirmpwd" class="col-sm-2 col-form-label text-right">确认密码:</label>
        <div class="col-sm-10">
            <input v-model="confirmpwd" type="password" maxlength="26" class="form-control" id="confirmpwd" name="confirmpwd">
        </div>
    </div>
    <div class="form-group row justify-content-end">
        <button v-on:click="submit" class="btn btn-danger mr-sm-4">确认更改</button>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="updatePwdAns" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">更改结果</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    {{ans}}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">{{closeButtonValue}}</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/resource/function/updatePwd.js"></script>
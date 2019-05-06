var updateUserApp = new Vue({
    el: "div#updateUserForm",
    data: {
        name: user_name,
        sex: user_sex,
        phone: user_phone
    },
    methods: {
        submit: function() {
            axios.post('/shop/updateUserServlet', {
                name: updateUserApp.name,
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

var updatePwdApp = new Vue({
    el: "div#updatePwdForm",
    data: {
        oldpwd: "",
        newpwd: "",
        confirmpwd: "",
        ans: "",
        closeButtonValue: "close"
    },
    methods: {
        submit: function() {
            if (updatePwdApp.oldpwd == "" || updatePwdApp.newpwd == "" || updatePwdApp.confirmpwd == "") {
                updatePwdApp.ans = "请填写完整!";
                $('div#updatePwdAns').modal('show');
            } else if (updatePwdApp.newpwd != updatePwdApp.confirmpwd) {
                updatePwdApp.ans = "两次密码不匹配!";
                $('div#updatePwdAns').modal('show');
            } else if (updatePwdApp.newpwd == updatePwdApp.oldpwd) {
                updatePwdApp.ans = "新密码与旧密码相同!";
                $('div#updatePwdAns').modal('show');
            } else {
                axios.post('/shop/updatePwdServlet', {
                    oldpwd: updatePwdApp.oldpwd,
                    newpwd: updatePwdApp.newpwd,
                    confirmpwd: updatePwdApp.confirmpwd
                }).then(function(res) {
                    if (res.data == "yes") {
                        updatePwdApp.ans = "更改成功.";
                        updatePwdApp.closeButtonValue = "重新登录";
                        $('div#updatePwdAns').on('hidden.bs.modal', function () {
                            window.location.href = '/shop/exitLoginServlet?method=relogin';
                        });
                        $('div#updatePwdAns').modal('show');
                    } else {
                        updatePwdApp.ans = "更改失败!";
                        $('div#updatePwdAns').modal('show');
                    }
                }).catch(function(error) {
                    console.info(error);
                    updatePwdApp.ans = "Error";
                    $('div#updatePwdAns').modal('show');
                });
            }
        },
        closeButton: function() {
            if (updatePwdApp.closeButtonValue == "重新登录") {
                window.location.href = '/shop/exitLoginServlet';
            }
        }
    }
});
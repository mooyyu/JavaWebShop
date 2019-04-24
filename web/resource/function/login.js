var loginapp = new Vue({
    el: "div#login",
    data: {
        email: "",
        password: ""
    },
    methods: {
        submit: function() {
            axios.post('/shop/checkLoginServlet?method=login', {
                email: loginapp.email,
                password: loginapp.password
            }).then(function(res) {
                if (res.data == "yes") {
                    window.location.href = "/";
                } else {
                    alert("邮箱或密码错误！");
                }
            }).catch(function(error) {
                console.info(error);
            });
        }
    }
})
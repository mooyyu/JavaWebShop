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
                    $('div#loginalert').modal('show');
                }
            }).catch(function(error) {
                console.info(error);
            });
        }
    }
});

var registerapp = new Vue({
    el: "div#register",
    data: {
        name: "",
        email: "",
        sex: "1",
        phone: "",
        pwd: "",
        confirmPwd: "",
        registerAns: ""
    },
    methods: {
        register: function() {
            axios.post('/shop/checkLoginServlet?method=register', {
                name: registerapp.name,
                email: registerapp.email,
                sex: registerapp.sex,
                phone: registerapp.phone,
                pwd: registerapp.pwd,
                confirmPwd: registerapp.confirmPwd,
                address: $('textarea#address')[0].value,
                info: $('textarea#info')[0].value
            }).then(function(res) {
                registerapp.registerAns = res.data;
                $('div#registeralert').modal('show');
            }).catch(function(error) {
                console.info(error);
            })
        }
    }
});
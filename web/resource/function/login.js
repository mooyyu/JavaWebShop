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
                    $('div#modalalert').modal('show');
                }
            }).catch(function(error) {
                console.info(error);
            });
        }
    }
})
var user_center = new Vue({
    el: "li#user_center",
    data: {
        hrefValue: "/login.jsp",
        cur_user: "游客",
        center_link: "请登录"
    }
});

axios.get('/shop/getUserForHeaderServlet')
    .then(function(res) {
        if (res.data.isLogin) {
            user_center.hrefValue = "/shop/userCenter?cur_user_email=" + res.data.email;
            user_center.cur_user = res.data.email;
            user_center.center_link = "个人中心";
        }
    }).catch(function(error) {
    console.info(error);
})
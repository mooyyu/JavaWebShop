var collectapp = new Vue({
    el: "div#collect",
    data: {
        curStatus: "",
        collectAns: ""
    },
    methods: {
        doCollect: function() {
            if (isLogin) {
                axios.post('/shop/api/doCollectServlet?method=toggleCollect', {
                    logined_email: logined_email,
                    check_str: check_str,
                    uuid: uuid,
                    userId: userId
                }).then(function(res) {
                    if (res.data == -1) {
                        collectapp.collectAns = "操作失败！";
                    } else if (res.data == 0) {
                        collectapp.collectAns = "取消收藏成功！";
                        collectapp.curStatus = "☆";
                    } else if (res.data == 1) {
                        collectapp.collectAns = "收藏成功！";
                        collectapp.curStatus = "★";
                    }
                    $('div#collectAlert').modal('show');
                }).catch(function(error) {
                    console.info(error);
                });
            } else {
                collectapp.collectAns = "请登录后操作!";
            }
            $('div#collectAlert').modal('show');
        }
    }
});

collectapp.curStatus = collectStatus ? "★" : "☆";
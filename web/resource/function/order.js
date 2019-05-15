var orderapp = new Vue({
    el: "div#order",
    data: {
        orderAns: ""
    },
    methods: {
        buy: function() {
            var that = this;
            axios.post('/shop/doOrderServlet?method=buy', {
                uuid: uuid
            }).then(function(res) {
                that.orderAns = res.data;
            }).catch(function(error) {
                console.info(error);
            });
            $('div#orderAlert').modal('show');
        },
        exchange: function() {
            var that = this;
            axios.post('/shop/doOrderServlet?method=exchange', {
                uuid: uuid
            }).then(function(res) {
                that.orderAns = res.data;
            }).catch(function(error) {
                console.info(error);
            });
            $('div#orderAlert').modal('show');
        }
    }
});
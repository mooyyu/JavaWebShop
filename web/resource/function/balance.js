var rechargeapp = new Vue({
    el: "div#accordionRecharge",
    methods: {
        recharge: function(money) {
            axios.get('/shop/rechargeServlet?money=' + money)
                .then(function() {
                    $('div#rechargeModal').modal('show');
                }).catch(function(error) {
                    console.info(error);
            });
        }
    }
});

$('div#rechargeModal').on('hidden.bs.modal', function() {
    window.location.reload();
});
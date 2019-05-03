var searchapp = new Vue({
    el: "span#search",
    data: {
        searchString: ""
    },
    methods: {
        dosearch: function() {
            if (searchapp.searchString == "") {
                $('div#searchalert').modal('show');
            } else {
                window.location.href = "/shop/searchServlet?searchString=" + searchapp.searchString;
            }
        }
    }
})
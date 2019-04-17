var searchapp = new Vue({
    el: "span#search",
    data: {
        searchString: "Search"
    },
    methods: {
        dosearch: function() {
            window.location.href = "/shop/searchServlet?searchString=" + searchapp.searchString;
        }
    }
})
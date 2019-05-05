var editBookItemapp = new Vue({
    el: "div#editBookItem",
    data: {
        name: book_name,
        author: book_author,
        price: book_price
    },
    methods: {
        submit: function() {
            axios.post('/shop/updateBookItemServlet', {
                uuid: uuid,
                logined_email: cookie_logined_email,
                check_str: cookie_check_str,
                name: editBookItemapp.name,
                author: editBookItemapp.author,
                price: editBookItemapp.price,
                info: $('textarea#bookInfo')[0].value,
                catagoryId: $('select#inputGroupSelectCatagory')[0].value,
                hownew: $('select#inputGroupSelectHownew')[0].value
            }).then(function(res) {
                window.location.reload();
            }).catch(function(error) {
                console.info(error);
            })
        }
    }
});
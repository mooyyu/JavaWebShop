var createBookItemapp = new Vue({
    el: "div#createBookItem",
    data: {
        name: "",
        author: "",
        price: 0
    },
    methods: {
        submit: function() {
            if (imgFile != null && this.checkAll()) {
                var formData = new FormData();
                formData.append('img', imgFile);
                var that = this;
                axios.post('/shop/bookIMGServlet?method=create', formData, {
                    headers: {'Content-Type': 'multipart/form-data'}
                }).then(function(res) {
                    if (res.data != null && res.data != "") {
                        axios.post('/shop/createBookItemServlet', {
                            image: res.data,
                            name: that.name,
                            author: that.author,
                            price: that.price,
                            info: $('textarea#bookInfo')[0].value,
                            catagoryId: $('select#inputGroupSelectCatagory')[0].value,
                            hownew: $('select#inputGroupSelectHownew')[0].value
                        }).then(function(response) {
                            window.location.href = "/shop/editBookItemServlet?uuid=" + response.data;
                        }).catch(function(error) {
                            console.info(error);
                        })
                    } else {
                        $('div#createModalBody')[0].innerText = "出现错误,请刷新后重试。若问题仍然存在,请联系网站管理员。";
                        $('div#createModal').modal('show');
                    }
                }).catch(function(error) {
                    console.info(error);
                });
            } else {
                $('div#createModalBody')[0].innerText = "请选择图片并将信息填写完整!";
                $('div#createModal').modal('show');
            }
        },
        checkAll: function() {
            if (this.name != "" && this.author != "" && this.price >= 0
                && $('textarea#bookInfo')[0].value != ""
                && $('select#inputGroupSelectCatagory')[0].value != ""
                && $('select#inputGroupSelectHownew')[0].value != "") {
                return true;
            } else {
                return false;
            }
        }
    }
});
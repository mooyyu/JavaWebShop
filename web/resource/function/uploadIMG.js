var rawIMGsrc = $('img#bookIMG')[0].src;
var imgFile = null;
var image = new Image();

var checkIMG = function(o) {
    if (o.files[0] != null) {
        if (o.files[0].size <= 128000) {
            imgFile = o.files[0];
            if(window.FileReader) {
                var fr = new FileReader();
                fr.onloadend = function(e) {
                    image.onload = function() {
                        if (image.height <= 500 && image.width <= 350) {
                            $('img#bookIMG')[0].src = e.target.result;
                        } else {
                            $('img#bookIMG')[0].src = rawIMGsrc;
                            imgFile = null;
                            $('div#imgModalBody')[0].innerText = "请选择尺寸为小于350*500的图片!";
                            $('div#imgModal').modal('show');
                        }
                    }
                    image.src = e.target.result;
                }
                fr.readAsDataURL(imgFile);
            }
        } else {
            $('div#imgModalBody')[0].innerText = "图片过大,请选择小于128k的图片!";
            $('div#imgModal').modal('show');
        }
    }
};

var submitIMG = function() {
    if (imgFile != null) {
        var formData = new FormData();
        formData.append('img', imgFile);
        axios.post('/shop/bookIMGServlet?method=update', formData, {
            headers: {'Content-Type': 'multipart/form-data'}
        }).then(function(res) {
            console.info(res.data);
        }).catch(function(error) {
            console.info(error);
        })
    } else {

    }
}
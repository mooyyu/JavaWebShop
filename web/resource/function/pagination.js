var curpage = 1;
var peritem = 20;
var sumpages = Math.ceil(itemlistsize / peritem);

var app = new Vue({
    el: "div#pagination",
    data: {
        next_page: "page-item",
        prev_page: "page-item disabled",
        link_1: -1,
        link_2: 0,
        link_3: 1,
        link_4: 2,
        link_5: 3
    },
    methods: {
        nextpage: function() {
            for (i = (curpage - 1) * peritem + 1; i <= Math.min(curpage * peritem, itemlistsize); i++) {
                $("div#" + i).addClass("d-none");
            }
            curpage++;
            app.updatepagelink();
            for (i = (curpage - 1) * peritem + 1; i <= Math.min(curpage * peritem, itemlistsize); i++) {
                $("div#" + i).removeClass("d-none");
            }
            app.checkcurpage();
            app.check_link();
        },
        prevpage: function() {
            for (i = (curpage - 1) * peritem + 1; i <= Math.min(curpage * peritem, itemlistsize); i++) {
                $("div#" + i).addClass("d-none");
            }
            curpage--;
            app.updatepagelink();
            for (i = (curpage - 1) * peritem + 1; i <= Math.min(curpage * peritem, itemlistsize); i++) {
                $("div#" + i).removeClass("d-none");
            }
            app.checkcurpage();
            app.check_link();
        },
        checkcurpage: function() {
            if (curpage == 1) {
                app.prev_page = "page-item disabled";
            } else {
                app.prev_page = "page-item";
            }
            if (curpage == sumpages) {
                app.next_page = "page-item disabled";
            } else {
                app.next_page = "page-item";
            }
        },
        page_link: function(idx) {
            for (i = (curpage - 1) * peritem + 1; i <= Math.min(curpage * peritem, itemlistsize); i++) {
                $("div#" + i).addClass("d-none");
            }
            curpage += idx;
            app.updatepagelink();
            for (i = (curpage - 1) * peritem + 1; i <= Math.min(curpage * peritem, itemlistsize); i++) {
                $("div#" + i).removeClass("d-none");
            }
            app.checkcurpage();
            app.check_link();
        },
        check_link: function() {
            if (app.link_1 < 1) {
                $("li#link_1").addClass("d-none");
            } else {
                $("li#link_1").removeClass("d-none");
            }
            if (app.link_2 < 1) {
                $("li#link_2").addClass("d-none");
            } else {
                $("li#link_2").removeClass("d-none");
            }
            if (app.link_4 > sumpages) {
                $("li#link_4").addClass("d-none");
            } else {
                $("li#link_4").removeClass("d-none");
            }
            if (app.link_5 > sumpages) {
                $("li#link_5").addClass("d-none");
            } else {
                $("li#link_5").removeClass("d-none");
            }
        },
        updatepagelink: function() {
            app.link_1 = curpage - 2;
            app.link_2 = curpage - 1;
            app.link_3 = curpage;
            app.link_4 = curpage + 1;
            app.link_5 = curpage + 2;
        },
        showItem: function(e) {
            window.location.href = '/shop/showBookItemServlet?uuid=' + e.target.getAttribute("uuid");
        }
    }
});

for (i = 1; i <= Math.min(peritem, itemlistsize); i++) {
    $("div#" + i).removeClass("d-none");
}
app.check_link();

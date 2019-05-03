if (curPage == "index") {
    if (curItem == "") {
        $("a#cataNew").addClass("active");
        $("a#cataNew").addClass("disabled");
    } else {
        $("a#cata" + curItem).addClass("active");
        $("a#cata" + curItem).addClass("disabled");
    }
} else if (curPage == "userCenter") {
    if (curItem != "") {
        $("a#default").removeClass("active");
        $("a#default").removeClass("disabled");
        $("a#" + curItem).addClass("active");
        $("a#" + curItem).addClass("disabled");
    }
}
if (curItem != "") {
    $("a#default").removeClass("active");
    $("a#default").removeClass("disabled");
    $("a#" + curItem).addClass("active");
    $("a#" + curItem).addClass("disabled");
}
layui.config({
    base: basePath + '/layui/build/js/'
}).use(['element', 'jquery', 'form', 'jquery'], function () {
    var element = layui.element;
    var $ = jQuery = layui.jquery
        , form = layui.form
        , common = layui.common;

    FrameWH();

    function FrameWH() {
        var h = $(window).height() - 70
        $("iframe").css("height", h + "px");
    }

    $(window).resize(function () {
        FrameWH();
    });

    element.on('tab(tagTabFilter)', function (data) {
        if (data.index == 1) {
            if (!$("#deviceTag").attr("src")) {
                $('#deviceTag').attr('src', basePath + '/pf/p/room/device/page');
            }
        }
    });


});



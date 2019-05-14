layui.config({
    base: basePath + '/layui/build/js/'
}).use(['element', 'jquery', 'form', 'jquery', 'common'], function () {
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
            if (!$("#itemTag").attr("src")) {
                $('#itemTag').attr('src', basePath + '/pf/p/item/device/form?idItemStore=' + $('#idItemStore').val());
            }
        }
    });

    form.verify({
        naItemStore: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        commonLength255: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        }
    });

    //监听提交
    form.on('submit(addItem)', function (data) {
        if (!data.field.fgActive) {
            data.field.fgActive = '0';
        }
        var url = basePath + '/pf/r/item/';
        if (formType == 'add') {
            url += 'add';
        } else if (formType == 'edit') {
            url += 'edit';
        }
        return _addItem(url, data.field, formType, 'itemTableId', '保存');
    });

    var _addItem = function (url, bizData, formType, tableId, msg) {
        layer.load(2);
        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(bizData),
            success: function (data) {
                layer.closeAll('loading');
                if (data.code != 0) {
                    common.errorMsg(data.msg);
                    return false;
                } else {
                    common.sucMsg(msg + "成功");
                    if (formType == 'add') {
                       element.tabAdd('tagTabFilter', {
                           title: '固定设备'
                           , content: '<iframe id="itemTag" class=\'layui-col-xs12\' frameborder="0" src= "'+ basePath +'/pf/p/room/device/page"></iframe>'
                           , id: 'itemTag'
                       });
                       FrameWH();
                    }
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        $('#idItemStore').val(data.data);
                        parent.layui.table.reload(tableId, {
                            height: 'full-68'
                        });
                    }
                    return false;
                }
            },
            error: function () {
                layer.closeAll('loading');
                common.errorMsg(msg + "失败");
                return false;
            }
        });
        return false;
    }


});



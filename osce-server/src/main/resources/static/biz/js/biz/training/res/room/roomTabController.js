layui.config({
    base: basePath + '/layui/build/js/'
}).use(['element', 'jquery', 'form', 'jquery', 'common', "upload"], function () {
    var element = layui.element;
    var $ = jQuery = layui.jquery
        , form = layui.form
        , common = layui.common
        , upload = layui.upload;

    FrameWH();

    function FrameWH() {
        var h = $(window).height() - 60
        $("iframe").css("height", h + "px");
    }

    $(window).resize(function () {
        FrameWH();
    });

    element.on('tab(tagTabFilter)', function (data) {
        if (data.index == 1) {
            if (!$("#deviceTag").attr("src")) {
                $('#deviceTag').attr('src', basePath + '/pf/p/room/device/page?idRoom=' + $('#idRoom').val());
            }
        }
    });

    form.verify({
        naRoom: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        desRoom: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        }
    });

    //监听提交
    form.on('submit(addRoom)', function (data) {
        if (!data.field.fgActive) {
            data.field.fgActive = '0';
        }
        var url = basePath + '/pf/r/room/';
        if (formType == 'add') {
            url += 'add';
        } else if (formType == 'edit') {
            url += 'edit';
        }
        return _addRoom(url, data.field, formType, 'roomTableId', '保存');
    });

    var _addRoom = function (url, bizData, formType, tableId, msg) {
        var idRoom =  $('#idRoom').val();
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
                            , content: '<iframe id="deviceTag" class=\'layui-col-xs12\' ' +
                                'frameborder="0" src= "' + basePath + '/pf/p/room/device/page?idRoom=' + data.data + '"></iframe>'
                            , id: 'deviceTag'
                        })
                        FrameWH();
                    }
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        $('#idRoom').val(data.data);
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

    // 上传头像
    upload.render({
        elem: '#LAY_avatarUpload'
        , url: basePath + '/upload'
        , field: 'file'
        , accept: 'images' //普通文件
        , exts: 'jpg|png|bmp|jpeg'
        , before: function (obj) {
            layer.msg('正在上传布局图', {icon: 16, shade: 0.01});
        }
        , done: function (res) {
            if (res.code != '0') {
                layer.tips(res.msg, '#LAY_avatarUpload', {
                    tips: [1, '#FF5722'],
                    time: 5000
                });
                return;
            }
            $('#picRoom').val(res.data.path);
            layer.closeAll('loading');
        }
        , error: function () {
            layer.closeAll('loading');
        }
    });

    $('#reviewPhoto').on('click', function () {
        var i = $("#picRoom").val();
        if (!i) {
            layer.tips("请先上传布局图", '#reviewPhoto', {
                tips: [1, '#FF5722']
            });
            return;
        }
        layui.layer.photos({
            photos: {
                title: "查看布局图",
                data: [{
                    src: i
                }]
            },
            shade: .01,
            closeBtn: 1,
            anim: 5
        })
    });

});



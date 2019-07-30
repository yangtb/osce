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
            if (!$("#deviceTag").attr("src")) {
                $('#deviceTag').attr('src', basePath + '/pf/p/model/device/form?idDevice=' + $('#idDevice').val());
            }
        }
    });

    form.verify({
        naDevice: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        desDevice: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        },
        numCheck :function (value) {
            var fgConsumables = $('input[name="fgConsumables"]:checked ').val();
            if (fgConsumables == 1 && !value) {
                return '警戒数量不能为空';
            }
        }
    });


    form.on('radio(fgConsumablesFilter)', function(data){
        if (data.value == 0) {
            $('#unmStock').val('');
            $('#numWarn').val('');
            $('#unmStock').attr("readonly","readonly");
            $('#unmStock').addClass("layui-disabled");
            $('#numWarn').attr("disabled","disabled");
            $('#numWarn').addClass("layui-disabled");
        } else {
            $('#unmStock').removeAttr("readonly","readonly");
            $('#unmStock').removeClass("layui-disabled");
            $('#numWarn').removeAttr("disabled","disabled");
            $('#numWarn').removeClass("layui-disabled");
        }
    });

    //监听提交
    form.on('submit(addModel)', function (data) {
        if (!data.field.fgActive) {
            data.field.fgActive = '0';
        }
        var url = basePath + '/pf/r/model/';
        if (formType == 'add') {
            url += 'add';
        } else if (formType == 'edit') {
            url += 'edit';
        }
        return _addRoom(url, data.field, formType, 'modelTableId', '保存');
    });

    var _addRoom = function (url, bizData, formType, tableId, msg) {
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
                    console.log(formType)
                    if (!$('#idDevice').val()) {
                        element.tabAdd('tagTabFilter', {
                            title: '固定设备'
                            , content: '<iframe id="deviceTag" class=\'layui-col-xs12\' ' +
                                'frameborder="0" src= "' + basePath + '/pf/p/model/device/form?idDevice=' + data.data + '"></iframe>'
                            , id: 'deviceTag'
                        })
                        FrameWH();
                    }
                    //刷新父页面table
                    if (formType == 'edit') {
                        parent.layui.common.refreshCurrentPage();
                    } else {
                        $('#idDevice').val(data.data);
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



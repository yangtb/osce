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
            if (!$("#scoreSheetTag").attr("src")) {
                $('#scoreSheetTag').attr('src', basePath + '/pf/p/case/item/page?idCase=' + $('#idCase').val());
            }
        }
    });

    form.verify({
        commonLength64: function (value) {
            if (value.length > 64) {
                return '长度不能超过64个字';
            }
        },
        commonLength255: function (value) {
            if (value && value.length > 255) {
                return '长度不能超过255个字';
            }
        },
        sort:  function (value) {
            if (value && !value.match(/^[1-9]\d*$/)) {
                return '排序必须是正整数';
            }
        }
    });

    //监听提交
    form.on('submit(addItem)', function (data) {
        if (!data.field.fgActive) {
            data.field.fgActive = '0';
        }
        var url = basePath + '/pf/r/case/';
        if (formType == 'add') {
            url += 'add';
        } else if (formType == 'edit') {
            url += 'edit';
        }
        return _addItem(url, data.field, formType, 'caseTableId', '保存');
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
                    if (!$('#idItemStore').val()) {
                        element.tabAdd('tagTabFilter', {
                            title: '评分表'
                            , content: '<iframe id="scoreSheetTag" class=\'layui-col-xs12\' frameborder="0" ' +
                                'src= "' + basePath + '/pf/p/case/item/page?idCase=' + data.data + '"></iframe>'
                            , id: 'scoreSheetTag'
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

    // 上传剧本
    upload.render({
        elem: '#LAY_avatarUpload'
        , url: basePath + '/upload'
        , field: 'file'
        , accept: 'file' //普通文件
        , number : 0
        , exts: 'doc|docx|jpg|jpeg|png|mp3|wav|ogg|mp4|avi|wmv|3gp|mkv|f4v|rmvb|word'
        , before: function (obj) {
            layer.msg('正在上传剧本，若文件过大，请耐心等待', {
                icon: 16,
                shade: 0.01,
                time: false
            });
        }
        , done: function (res) {
            if (res.code != '0') {
                layer.tips(res.msg, '#LAY_avatarUpload', {
                    tips: [1, '#FF5722'],
                    time: 5000
                });
                return;
            }
            $('#docSp').val(res.data.path);
            layer.closeAll();
        }
        , error: function(index, upload) {
            layer.closeAll();
        }
    });

    $('#preview').on('click', function () {
        var path = $("#docSp").val();
        if (!path) {
            layer.tips("请先上传剧本", '#LAY_avatarUpload', {
                tips: [1, '#FF5722']
            });
            return;
        }

        var fileType = path.substring(path.lastIndexOf(".") + 1 , path.length);

        if (fileType == 'jpg' || fileType == 'jpeg' || fileType == 'png') {
            common.openSinglePhoto(path);
        } else if (fileType == 'mp3' || fileType == 'wav' || fileType == 'ogg') {
            common.openAudio(path.substring(0, path.lastIndexOf(".")));
        } else if (fileType == 'mp4' || fileType == 'avi' || fileType == 'wmv'
            || fileType == 'gp3' || fileType == 'mkv' || fileType == 'f4v' || fileType == 'rmvb') {
            common.openTopVideo(basePath + '/video/form?path=' + path, 890, 504);
        } else {
            layer.tips("该文件类型暂不支持预览，请下载后浏览", '#preview', {tips: 1});
        }
    });

    $('#downLoadDocSp').on('click', function () {
        if (!$("#docSp").val()) {
            layer.tips("请先上传剧本", '#LAY_avatarUpload', {
                tips: [1, '#FF5722']
            });
           return false;
        }
        window.open($("#docSp").val());
    });

});




function fullForm(data) {
    $(document).ready(function(){
        $("#spCaseForm").autofill(data);
        layui.use('form',function(){
            layui.form.render();
        });
    });
}
